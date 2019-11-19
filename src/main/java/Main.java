import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import domain.Brand;
import domain.Model;
import service.BrandService;
import service.ModelService;

import javax.swing.*;

public class Main {
    public static void main(String[] args){

        Brand ford = new Brand(5, "Ford", "USA", "PSA", 1900);
        Brand AUDI = new Brand(2,"AUDI","Niemcy", "VW", 1902 );
        Brand Fiat = new Brand(3, "Fiat", "Wlochy", "Fiat", 1800);
        Brand Opel = new Brand(4,"Opel", "Niemcy", "GM", 1999);
        Brand BMW = new Brand(1,"BMW", "Niewmcy", "BMW", 1993);

        Model a6 = new Model(2,"A6", 453627, 6799, AUDI);
        Model a3 = new Model(3,"A3", 451627, 799, AUDI);
        Model a8 = new Model(4,"A8", 153627, 116799, AUDI);
        Model f90 = new Model(1,"f90", 467283, 23900, BMW);
        Model f10 = new Model(5,"f10", 987283, 123900, BMW);
        Model e46 = new Model(6,"e46", 467323, 29900, BMW);
        Model e34 = new Model(7,"e34", 111183, 25550, BMW);
        Model multipla = new Model(8,"Multipla", 460280, 11900, Fiat);
        Model maluch = new Model(9,"126p", 161210, 100, Fiat);
        Model astra = new Model(10,"Astra", 453197, 116799, Opel);
        Model vectra = new Model(11,"Vectra", 453190, 116399, Opel);

        Model e38 = new Model(12,"e38", 461383, 23900, BMW);


        BrandService edit_marka = new BrandService();
        ModelService edit_model = new ModelService();
 //tworzenie marek
//        edit_marka.addBrand(AUDI);
//        edit_marka.addBrand(ford);
//        edit_marka.addBrand(Fiat);
//        edit_marka.addBrand(Opel);
//        edit_marka.addBrand(BMW);

//tworzenie modeli
//        edit_model.addModel(a6);
//        edit_model.addModel(a3);
//        edit_model.addModel(a8);
//
//        edit_model.addModel(f90);
//        edit_model.addModel(f10);
//        edit_model.addModel(e46);
//        edit_model.addModel(e34);
//
//        edit_model.addModel(multipla);
//        edit_model.addModel(maluch);
//
//        edit_model.addModel(astra);
//        edit_model.addModel(vectra);

        //wyswietlenie modeli
//        edit_model.selectAllModel();
        //wyswietlenie marek
//        edit_marka.selectAllBrand();

        //upadate
//        edit_model.updateModel(1,e38);

        //delete
            //edit_model.deleteModel(11);
//        edit_marka.deleteBrand(5);

        //zapytanie biznesowe auta z danego kraju

//        edit_model.country_cars("Niemcy");

        //top 1

        edit_model.top_cars();
    }
}
