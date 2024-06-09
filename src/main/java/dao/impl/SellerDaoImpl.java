package dao.impl;

import dao.SellerDao;
import entity.Seller;
import util.DBUtil2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoImpl implements SellerDao {
    @Override
    public void add(Seller s) {
        String name = s.getName();
        String telephone = s.getTelephone();
        String method = s.getMethod();
        String type = s.getType();
        String address = s.getAddress();
        String sql = "insert into seller values('"+name+"','"+telephone+"','"+method+"','"+type+"','"+address+"')";
        DBUtil2 db = new DBUtil2();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public void remove(String name) {
        String sql = "delete from seller where 供货商名称 = '"+name+"'";
        DBUtil2 db = new DBUtil2();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public List<Seller> getAll(String sql) {
        DBUtil2 db = new DBUtil2();
        ResultSet rs = db.executeQuery(sql);
        List<Seller> list = new ArrayList<>();
        try {
            String name;
            String telephone;
            String method;
            String type;
            String address;
            while(rs.next()) {
                name =rs.getString("供货商名称");
                telephone = rs.getString("联系方式");
                method = rs.getString("支付方式");
                type = rs.getString("供应类别");
                address = rs.getString("地址");
                list.add(new Seller(name,telephone,method,type,address));
            }
            db.close();
            return list;
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Seller getByName(String name) {
        List<Seller> roles = new SellerDaoImpl().getAll("select * from seller");
        for(Seller b : roles){
            if(b.getName().equals(name)){
                return b;
            }
        }
        return null;
    }
}
