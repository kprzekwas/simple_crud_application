package service;

import domain.Model;
import domain.Brand;

import java.sql.*;

public class ModelService {

    private Connection connection;
    private Statement statement;
    private final String DB_URL = "jdbc:hsqldb:hsql://localhost/workdb";

    private final String Create_Query = "CREATE TABLE Model(\n"+
            "id_model INT NOT NULL PRIMARY KEY ,\n"+
            "name VARCHAR(40) NOT NULL,\n"+
            "serial_number CHAR(6) UNIQUE NOT NULL,\n"+
            "price BIGINT not null ,\n"+
            "brand_id INT NOT NULL,\n"+
            "FOREIGN KEY (brand_id) REFERENCES BRAND(id_brand) ON DELETE CASCADE"+
            ")";
    private final String Insert_Query = "INSERT INTO Model(id_model, name, serial_number, price, brand_id)" +
            "VALUES(?, ?, ?, ?, ?);";
    private final String Select_All_Query = "SELECT id_model, name, serial_number, price, brand_id FROM Model";
    private final String Update_Query = "UPDATE Model SET name = ?, serial_number = ?, price = ?, brand_id = ? WHERE id_model = ?";
    private final String Delete_Query = "DELETE FROM Model WHERE id_model = ?";

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


    public void addModel(Model model) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Query);

            preparedStatement.setInt(1, model.getid_model());
            preparedStatement.setString(2, model.getName());
            preparedStatement.setInt(3, model.getSerial_number());
            preparedStatement.setDouble(4, model.getPrice());
            preparedStatement.setInt(5, model.getBrand().getId_brand());

            preparedStatement.execute();

        } catch(SQLException e){
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

        } catch(SQLException e){
            e.printStackTrace();
        }

    }


    public void deleteModel(int id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Delete_Query);

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch(SQLException e){
            e.printStackTrace();
        }

    }


}
