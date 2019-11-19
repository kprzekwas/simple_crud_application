//import domain.*;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import service.*;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
//public class BrandServiceTest {
//    private static Connection connection;
//
//    @BeforeClass
//    public static void setUp() throws Exception {
//        Class.forName("org.hsqldb.jdbc.JDBCDriver");
//        connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
//    }
//
//    @AfterClass
//    public static void tearDown() throws SQLException {
//        connection.close();
//    }
//
//    @After
//    public void AfterTest() throws SQLException {
//        BrandService brandService = new BrandService(connection);
//        ModelService modelService = new ModelService(connection);
//
//        modelService.dropTable();
//        brandService.dropTable();
//    }
//
//    private boolean isIn(Brand brand, ArrayList<Brand> brands) {
//        for (Brand d : brands) {
//            if (d.equals(brand)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Test
//    public void testCreateRecord() throws SQLException {
//        BrandService BrandService = new BrandService(connection);
//        Brand newBrand = new Brand(1, "Name", "USA", "FORD", 1800);
//        BrandService.addBrand(newBrand);
//
//        ArrayList<Brand> yachtclubs = BrandService.selectAll();
//        assertTrue(isIn(newBrand,yachtclubs));
//    }
//
//    @Test
//    public void testUpdateRecord() throws SQLException {
//        BrandService BrandService = new BrandService(connection);
//        Brand newBrand = new Brand(10, "Name", "USA", "FORD", 1800);
//        BrandService.addBrand(newBrand);
//
//        ArrayList<Brand> brands = BrandService.selectAll();
//        assertFalse(isIn(newBrand, brands));
//        newBrand.setName("newName");
//        brands = BrandService.selectAll();
//        assertFalse(isIn(newBrand, brands));
//    }
//
//}
