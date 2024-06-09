package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    private Connection con;//链接对象
    private Statement stmt;

    private static final String URL = "jdbc:mysql://localhost:3306/power";
    private static final String USER = "root";
    private static final String PASSWORD = "123";
    public DBUtil() {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			stmt = con.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
    }
    public void executeUpdate(String sql) {
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException  | NullPointerException e) {
				e.printStackTrace();
			}
    }
    public ResultSet executeQuery(String sql) {
    	try {
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
		} catch (SQLException |NullPointerException e) {
			e.printStackTrace();
			return null;
		}
    }
    public void close() {
    	try {
    		if(!stmt.isClosed())stmt.close();
    		if(!con.isClosed())con.close();
		} catch (SQLException |NullPointerException e) {
			e.printStackTrace();
		}
    }
}
