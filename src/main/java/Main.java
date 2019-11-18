import java.sql.SQLException;
import domain.Brand;
import domain.Model;
import service.BrandService;
import service.ModelService;

public class Main {
    public static void main(String[] args){
        Brand brand = new Brand(1, "BMW", "Niemcy", "BMW", 1919);
        BrandService BMW_udate = new BrandService();
        BMW_udate.deleteBrand(1);
    }
}
