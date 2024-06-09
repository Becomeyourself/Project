package dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RoleDao;
import entity.Role;
import entity.User;
import util.DBUtil;

public class RoleDaoImpl implements RoleDao {

	@Override
	public void add(Role r) {
		String rname = r.getName();
		String inform = r.getInform();
		String power = r.getPower();
		String sql = "insert into 角色管理 values('"+rname+"','"+inform+"','"+power+"')";
		DBUtil db = new DBUtil();
		db.executeUpdate(sql);
		db.close();
	}

	@Override
	public void remove(String rname) {
		String sql = "delete from 角色管理 where 角色名称 = '"+rname+"'";
		DBUtil db = new DBUtil();
		db.executeUpdate(sql);
		db.close();
	}

	@Override
	public List<Role> getAll(String sql) {
		DBUtil db = new DBUtil();
		ResultSet rs = db.executeQuery(sql);
		List<Role> list = new ArrayList<>();
		try {
			String rname;
			String inform;
			String power;
			while(rs.next()) {
				rname =rs.getString("角色名称");
				inform = rs.getString("角色说明");
				power = rs.getString("角色权限");
				list.add(new Role(rname,inform,power));
			}
			db.close();
			return list;
		} catch (SQLException |NullPointerException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Role getByName(String rname) {
		List<Role> roles = new RoleDaoImpl().getAll("select * from 角色管理");
		for(Role r : roles){
			if(r.getName().equals(rname)){
				return r;
			}
		}
		return null;
	}
}
