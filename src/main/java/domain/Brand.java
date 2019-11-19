package domain;


public class Brand {
    private int id_brand;
    private String name;
    private String origin_country;
    private String owner;
    private int creation_year;
    
    public Brand(){}
    
    public Brand(int id_brand, String name, String origin_country, String owner, int creation_year) {
        this.id_brand = id_brand;
        this.name = name;
        this.origin_country = origin_country;
        this.owner = owner;
        this.creation_year = creation_year;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public void setId_brand(int id_brand) {
        this.id_brand = id_brand;
    }

    public int getId_brand() {
        return id_brand;
    }

    public int getCreation_year() {
        return creation_year;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public String getOwner() {
        return owner;
    }

    public void setCreation_year(int creation_year) {
        this.creation_year = creation_year;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id_brand=" + id_brand +
                ", name='" + name + '\'' +
                ", origin_country='" + origin_country + '\'' +
                ", owner='" + owner + '\'' +
                ", creation_year=" + creation_year +
                '}';
    }

}
