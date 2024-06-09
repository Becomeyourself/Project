package entity;

public class Indata {
    public String date;
    public String goodname;
    public String inprice;
    public String price;
    public String storename;
    public String people;
    public String check;
    public Indata(String date,String goodname,String inprice,String price,String storename,String people){
        this.date=date;
        this.goodname = goodname;
        this.inprice = inprice;
        this.price = price;
        this.storename = storename;
        this.people = people;
        this.check = "待付款";
    }
}
