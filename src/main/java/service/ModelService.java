package service;

import domain.Model;
import domain.Brand;

import java.sql.*;

public class ModelService {

    private Connection connection;
    private Statement statement;
    private final String DB_URL = "jdbc:hsqldb:hsql://localhost/workdb";

    private final String Create_Query = "CREATE TABLE Model(\n" +
            "id_model INT NOT NULL PRIMARY KEY ,\n" +
            "name VARCHAR(40) NOT NULL,\n" +
            "serial_number CHAR(6) UNIQUE NOT NULL,\n" +
            "price BIGINT not null ,\n" +
            "brand_id INT NOT NULL,\n" +
            "FOREIGN KEY (brand_id) REFERENCES BRAND(id_brand) ON DELETE CASCADE" +
            ")";
    private final String Insert_Query = "INSERT INTO Model(id_model, name, serial_number, price, brand_id)" +
            "VALUES(?, ?, ?, ?, ?);";
    private final String Select_All_Query = "SELECT id_model, name, serial_number, price, brand_id FROM Model";
    private final String Update_Query = "UPDATE Model SET name = ?, serial_number = ?, price = ?, brand_id = ? WHERE id_model = ?";
    private final String Delete_Query = "DELETE FROM Model WHERE id_model = ?";
    private final String Select_all_Query = "SELECT * FROM MODEL";
    private final String DropTable_Query = "DROP TABLE MODEL";

    private final String select_country_cars = "select BRAND.NAME as Brand, M.NAME as Model,origin_country as country, owner, serial_number, price from BRAND inner join MODEL M on BRAND.ID_BRAND = M.BRAND_ID\n" +
            "where ORIGIN_COUNTRY = ? \n" +
            "order by PRICE DESC";




    private final String top_cars = "select top 1 BRAND.NAME as Brand, M.NAME as Model,origin_country as country, owner, serial_number, price from BRAND inner join MODEL M on BRAND.ID_BRAND = M.BRAND_ID\n" +
            "order by PRICE DESC";


    public ModelService() {
        try {
            connection = DriverManager.getConnection(DB_URL);

            System.out.println("Connected");

            statement = connection.createStatement();


            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);

            boolean tableExists = false;

            while (rs.next()) {
                if ("Model".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    break;
                }
            }

            if (!tableExists) {
                statement.executeUpdate(Create_Query);
                System.out.println("Stworzono tabele.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//transakcje
    public void addModel(Model model) {

        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Query);

            preparedStatement.setInt(1, model.getid_model());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setInt(3, model.getSerial_number());
            preparedStatement.setDouble(4, model.getPrice());
            preparedStatement.setInt(5, model.getBrand().getId_brand());

            preparedStatement.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }

    }

    public void updateModel(int id, Model model) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Update_Query);

            preparedStatement.setInt(5, id);
            preparedStatement.setString(1, model.getName());
            preparedStatement.setInt(2, model.getSerial_number());
            preparedStatement.setDouble(3, model.getPrice());
            preparedStatement.setInt(4, model.getBrand().getId_brand());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void deleteModel(int id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Delete_Query);

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectAllModel() {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Select_all_Query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                int id_model = resultSet.getInt("ID_MODEL");
                String name = resultSet.getString("NAME");
                String serial_number = resultSet.getString("SERIAL_NUMBER");
                Double price = resultSet.getDouble("PRICE");
                int brand = resultSet.getInt("BRAND_ID");

                System.out.println("Model{" +
                        "id_model=" + id_model +
                        ", name='" + name + '\'' +
                        ", serial_number='" + serial_number + '\'' +
                        ", price='" + price + '\'' +
                        ", brand=" + brand +
                        '}');
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() throws SQLException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DropTable_Query);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void country_cars(String country) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(select_country_cars);

            preparedStatement.setString(1, country);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                String brand = resultSet.getString("BRAND");
                String model = resultSet.getString("MODEL");
                String owner = resultSet.getString("OWNER");
                int number = resultSet.getInt("SERIAL_NUMBER");
                Double price = resultSet.getDouble("PRICE");

                System.out.println(
                        "marka " + brand +
                                "| model '" + model + '\'' +
                                "| countru '" + country + '\'' +
                                "| owner '" + owner + '\'' +
                                "| number '" + number + '\'' +
                                "| price " + price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void top_cars() {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(top_cars);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {


                String brand = resultSet.getString("BRAND");
                String model = resultSet.getString("MODEL");
                String country = resultSet.getString("COUNTRY");
                String owner = resultSet.getString("OWNER");
                int number = resultSet.getInt("SERIAL_NUMBER");
                Double price = resultSet.getDouble("PRICE");

                System.out.println(
                        "marka " + brand +
                                "| model '" + model + '\'' +
                                "| countru '" + country + '\'' +
                                "| owner '" + owner + '\'' +
                                "| number '" + number + '\'' +
                                "| price " + price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
