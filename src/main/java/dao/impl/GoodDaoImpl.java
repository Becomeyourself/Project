package dao.impl;

import dao.GoodDao;
import entity.Good;
import entity.Role;
import util.DBUtil;
import util.DBUtil2;
import util.DBUtil3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodDaoImpl implements GoodDao {
    @Override
    public void add(Good r) {
        String a = r.goodname;
        String b = r.goodinform;
        String sql = "insert into good values('"+a+"','"+b+"')";
        DBUtil3 db = new DBUtil3();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public void remove(String name) {
        String sql = "delete from good where 货品名称 = '"+name+"'";
        DBUtil3 db = new DBUtil3();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public List<Good> getAll(String sql) {
        DBUtil3 db = new DBUtil3();
        ResultSet rs = db.executeQuery(sql);
        List<Good> list = new ArrayList<>();
        try {
            String rname;
            String inform;
            while(rs.next()) {
                rname =rs.getString("货品名称");
                inform = rs.getString("货品介绍");
                list.add(new Good(rname,inform));
            }
            db.close();
            return list;
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Good getByName(String name) {
        List<Good> roles = new GoodDaoImpl().getAll("select * from good");
        for(Good r : roles){
            if(r.goodname.equals(name)){
                return r;
            }
        }
        return null;
    }
}
