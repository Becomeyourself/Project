package dao.impl;

import dao.UserRoleDao;
import entity.User;
import entity.UserRole;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
    @Override
    public void add(UserRole ur) {
        String uname = ur.getUser();
        String rname = ur.getRole();
        String sql = "insert into 用户角色关联表 values('"+uname+"','"+rname+"')";
        DBUtil db = new DBUtil();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public List<UserRole> getAll(String sql) {
        DBUtil db = new DBUtil();
        ResultSet rs = db.executeQuery(sql);
        List<UserRole> list = new ArrayList<>();
        try {
            String uname;
            String rname;
            while(rs.next()) {
                uname =rs.getString("用户账号");
                rname = rs.getString("角色名称");
                list.add(new UserRole(uname,rname));
            }
            db.close();
            return list;
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> getByUser(String uname) {
        List<UserRole> urs = new UserRoleDaoImpl().getAll("select * from 用户角色关联表");
        List<String> roles = new ArrayList<>();
        for(UserRole ur : urs){
            if(ur.getUser().equals(uname)){
                roles.add(ur.getRole());
            }
        }
        return roles;
    }

    @Override
    public List<String> getByRole(String rname) {
        List<UserRole> urs = new UserRoleDaoImpl().getAll("select * from 用户角色关联表");
        List<String> roles = new ArrayList<>();
        for(UserRole ur : urs){
            if(ur.getRole().equals(rname)){
                roles.add(ur.getUser());
            }
        }
        return roles;
    }

    @Override
    public void removeUser(String uname) {
        String sql = "delete from 用户角色关联表 where 用户账号 = '"+uname+"'";
        DBUtil db = new DBUtil();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public void removerRole(String rname) {
        String sql = "delete from 用户角色关联表 where 角色名称 = '"+rname+"'";
        DBUtil db = new DBUtil();
        db.executeUpdate(sql);
        db.close();
    }
}
