package domain;


public class Brand {
    private static int id_brand;
    private static String name;
    private static String origin_country;
    private static String owner;
    private static int creation_year;
    
    public Brand(){}
    
    public Brand(int id_brand, String name, String origin_country, String owner, int creation_year) {
        Brand.id_brand = id_brand;
        Brand.name = name;
        Brand.origin_country = origin_country;
        Brand.owner = owner;
        Brand.creation_year = creation_year;
    }

    public static int getId_brand() {
        return id_brand;
    }
    
    public static void setId_brand(int id_brand) {
        Brand.id_brand = id_brand;
    }

    public static String getName() {
        return name;
    }
    
    public static void setName(String name) {
        Brand.name = name;
    }
    
    public static String getOrigin_country() {
        return origin_country;
    }

    public static void setOrigin_country(String origin_country) {
        Brand.origin_country = origin_country;
    }

    public static String getOwner() {
        return owner;
    }
    
    public static void setOwner(String owner) {
        Brand.owner = owner;
    }

    public static int getcreation_year() {
        return creation_year;
    }
    
    public static void setcreation_year(int creation_year) {
        Brand.creation_year = creation_year;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id_brand=" + id_brand +
                ", name='" + name + '\'' +
                ", origin_country='" + origin_country + '\'' +
                ", owner='" + owner + '\'' +
                ", creation_year=" + creation_year +
                '}';
    }

}
