package entity;

public class UserRole {
    private String uname;
    private String rname;
    public UserRole(String uname,String rname) {
        this.uname = uname;
        this.rname = rname;
    }
    public String getUser() {
        return uname;
    }
    public String getRole(){return rname;}
    public void setUser (String uanme) {
        this.uname = uname;
    }
    public void setRole(String rname) {
        this.rname = rname;
    }
}
