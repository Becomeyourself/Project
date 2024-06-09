package entity;

public class Role {
    private String rolename;
    private String inform;
    public String power;
    public Role(String rolename,String inform,String power) {
        this.rolename = rolename;
        this.inform = inform;
        this.power = power;
    }
    public String getName() {
        return rolename;
    }
    public String getInform(){return inform;}
    public String getPower(){ return power;}
    public void setInform(String inform) {
        this.inform = inform;
    }
    public void setName(String name) {
        this.rolename = name;
    }
    public void setPower(String power){this.power = power;}
}
