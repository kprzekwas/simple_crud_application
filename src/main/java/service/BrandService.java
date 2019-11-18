package service;

import domain.Brand;

import java.sql.*;

public class BrandService {

    private Connection connection;
    private Statement statement;
    private final String DB_URL = "jdbc:hsqldb:hsql://localhost/workdb";

    private final String Create_Query = "CREATE TABLE Brand (\n" +
            "id_brand INT,\n" +
            "name VARCHAR(30),\n" +
            "origin_country VARCHAR(30),\n" +
            "owner VARCHAR(30),\n" +
            "creation_year DECIMAL,\n" +
            "PRIMARY KEY (id_brand)" +
            ")";

    private final String Insert_Query = "INSERT INTO Brand(id_brand, name, origin_country, owner, creation_year)" +
            "VALUES(?, ?, ?, ?, ?);";

    private final String Update_Query = "UPDATE Brand SET name = ?, origin_country = ?, owner = ?, creation_year = ? WHERE id_brand = ?";
    private final String Delete_Query = "DELETE FROM Brand WHERE id_brand = ?";

    public BrandService() {
        try {
            connection = DriverManager.getConnection(DB_URL);

            System.out.println("Connected");

            statement = connection.createStatement();

            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);

            boolean tableExists = false;

            while (rs.next()) {
                if ("Brand".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    System.out.println("tabela Brand juz istnieje...");
                    break;
                }
            }

            if (!tableExists) {
                statement.executeUpdate(Create_Query);
                System.out.println("Utworzono tabele.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addBrand(Brand brand) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Query);

            preparedStatement.setInt(1, Brand.getId_brand());
            preparedStatement.setString(2, Brand.getName());
            preparedStatement.setString(3, Brand.getOrigin_country());
            preparedStatement.setString(4, Brand.getOwner());
            preparedStatement.setInt(5, Brand.getcreation_year());

            preparedStatement.execute();

        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void updateBrand(int id, Brand brand) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Update_Query);

            preparedStatement.setInt(5, id);
            preparedStatement.setString(1, Brand.getName());
            preparedStatement.setString(2, Brand.getOrigin_country());
            preparedStatement.setString(3, Brand.getOwner());
            preparedStatement.setInt(4, Brand.getcreation_year());

            preparedStatement.execute();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteBrand(int id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Delete_Query);

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

        } catch(SQLException e){
            e.printStackTrace();
        }

    }
}
