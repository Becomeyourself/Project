package dao.impl;

import dao.BuyerDao;
import entity.Buyer;
import util.DBUtil2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyerDaoImpl implements BuyerDao {

    @Override
    public void add(Buyer b) {
        String name = b.getName();
        String telephone = b.getTelephone();
        String method = b.getMethod();
        String type = b.getType();
        String address = b.getAddress();
        String category=b.getCategory();
        String sql = "insert into buyer values('"+name+"','"+telephone+"','"+method+"','"+type+"','"+address+"','"+category+"')";
        DBUtil2 db = new DBUtil2();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public void remove(String name) {
        String sql = "delete from buyer where 客户名称 = '"+name+"'";
        DBUtil2 db = new DBUtil2();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public List<Buyer> getAll(String sql) {
        DBUtil2 db = new DBUtil2();
        ResultSet rs = db.executeQuery(sql);
        List<Buyer> list = new ArrayList<>();
        try {
            String name;
            String telephone;
            String method;
            String type;
            String address;
            String category;
            while(rs.next()) {
                name =rs.getString("客户名称");
                telephone = rs.getString("联系方式");
                method = rs.getString("支付方式");
                type = rs.getString("客户类别");
                address = rs.getString("地址");
                category=rs.getString("类别");
                list.add(new Buyer(name,telephone,method,type,address,category));
            }
            db.close();
            return list;
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Buyer getByName(String name) {
        List<Buyer> roles = new BuyerDaoImpl().getAll("select * from buyer");
        for(Buyer b : roles){
            if(b.getName().equals(name)){
                return b;
            }
        }
        return null;
    }
}
