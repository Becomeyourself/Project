package entity;

public class Power {
    private String powername;
    private String inform;
    public Power(String powername,String inform) {
        this.powername = powername;
        this.inform = inform;
    }
    public String getName() {
        return powername;
    }
    public String getInform(){return inform;}
    public void setInform(String inform) {
        this.inform = inform;
    }
    public void setName(String name) {
        this.powername = name;
    }
}
