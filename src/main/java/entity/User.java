package entity;

public class User {
    private String username;
    private String password;
    private String date;
	public User(String username,String password,String date) {
		this.username = username;
		this.password = password;
		this.date = date;
	}
	public String getName() {
		return username;
	}
	public String getPassword(){return password;}
	public String getDate(){return date;}
	public void setPwd(String pwd) {
		this.password = pwd;
	}
	public void setName(String name) {
		this.username = name;
	}
}
