package entity;

public class Seller {
    private String name;
    private String telephone;
    private String method;
    private String type;
    private String address;
    public Seller(String name, String telephone, String method, String type, String address) {
        this.name = name;
        this.telephone = telephone;
        this.method = method;
        this.type = type;
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public String getTelephone(){return telephone;}
    public String getMethod(){return method;}
    public String getType(){return type;}
    public String getAddress(){return address;}
}
