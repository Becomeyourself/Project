package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.User;
import util.DBUtil;
import dao.UserDao;

public class UserDaoImpl implements UserDao {

	@Override
	public void add(User u) {
		String uname = u.getName();
		String pwd = u.getPassword();
		String date = u.getDate();
		String sql = "insert into 用户信息 values('"+uname+"','"+pwd+"','"+date+"')";
		DBUtil db = new DBUtil();
		db.executeUpdate(sql);
		db.close();
	}

	@Override
	public void remove(String uid) {
		String sql = "delete from 用户信息 where 用户账号 = '"+uid+"'";
		DBUtil db = new DBUtil();
		db.executeUpdate(sql);
		db.close();
	}

	@Override
	public List<User> getAll(String sql) {
		DBUtil db = new DBUtil();
		ResultSet rs = db.executeQuery(sql);
		List<User> list = new ArrayList<>();
		try {
			String uname;
			String pwd;
			String date;
			while(rs.next()) {
				uname =rs.getString("用户账号");
			    pwd = rs.getString("用户密码");
			    date = rs.getString("注册时间");
			    list.add(new User(uname,pwd,date));
			}
			db.close();
			return list;
		} catch (SQLException |NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getByName(String username) {
		String sql = "select *from 用户信息 where 用户账号 ='"+username+"'";
		DBUtil db = new DBUtil();
		ResultSet rs = db.executeQuery(sql);
		try {
			String uname =rs.getString("用户账号");
			String pwd = rs.getString("用户密码");
			String date = rs.getString("注册时间");
			db.close();
			return new User(uname,pwd,date);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
