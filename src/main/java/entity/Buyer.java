package entity;

public class Buyer {
    private String name;
    private String telephone;
    private String method;
    private String type;
    private String address;
    private String category;
    public Buyer(String name, String telephone, String method, String type, String address,String category) {
        this.name = name;
        this.telephone = telephone;
        this.method = method;
        this.type = type;
        this.address = address;
        this.category=category;
    }
    public String getName() {
        return name;
    }
    public String getTelephone(){return telephone;}
    public String getMethod(){return method;}
    public String getType(){return type;}
    public String getAddress(){return address;}
    public String getCategory(){return category;}
}
