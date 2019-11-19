package service;

import domain.Model;
import domain.Brand;

import java.sql.*;

public class BrandService {

    private Connection connection;
    private Statement statement;
    private final String DB_URL = "jdbc:hsqldb:hsql://localhost/workdb";

    private final String Create_Query = "CREATE TABLE Brand (\n" +
            "id_brand INT NOT NULL,\n" +
            "name VARCHAR(30) NOT NULL,\n" +
            "origin_country VARCHAR(30) NOT NULL,\n" +
            "owner VARCHAR(30),\n" +
            "creation_year DECIMAL,\n" +
            "PRIMARY KEY (id_brand)" +
            ")";

    private final String Insert_Query = "INSERT INTO Brand(id_brand, name, origin_country, owner, creation_year)" +
            "VALUES(?, ?, ?, ?, ?);";

    private final String Update_Query = "UPDATE Brand SET name = ?, origin_country = ?, owner = ?, creation_year = ? WHERE id_brand = ?";
    private final String Delete_Query = "DELETE FROM Brand WHERE id_brand = ?";
    private final String Select_all_Query = "SELECT * FROM Brand";

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

            preparedStatement.setInt(1, brand.getId_brand());
            preparedStatement.setString(2, brand.getName());
            preparedStatement.setString(3, brand.getOrigin_country());
            preparedStatement.setString(4, brand.getOwner());
            preparedStatement.setInt(5, brand.getCreation_year());

            preparedStatement.execute();

        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void updateBrand(int id, Brand brand) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Update_Query);

            preparedStatement.setInt(5, id);
            preparedStatement.setString(1, brand.getName());
            preparedStatement.setString(2, brand.getOrigin_country());
            preparedStatement.setString(3, brand.getOwner());
            preparedStatement.setInt(4, brand.getCreation_year());

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
    public void selectAllBrand() {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(Select_all_Query);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {

                        int id_brand = resultSet.getInt("ID_BRAND");
                        String name = resultSet.getString("NAME");
                        String origin_country = resultSet.getString("ORIGIN_COUNTRY");
                        String owner = resultSet.getString("NAME");
                        int creation_year = resultSet.getInt("CREATION_YEAR");


                        Brand obj = new Brand();
                        obj.setId_brand(id_brand);
                        obj.setName(name);
                        obj.setOrigin_country(origin_country);
                        obj.setOwner(owner);
                        obj.setCreation_year(creation_year);

                        System.out.println(obj);
                    }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}

//    private int id_brand;
//    private String name;
//    private String origin_country;
//    private String owner;
//    private int creation_year;
