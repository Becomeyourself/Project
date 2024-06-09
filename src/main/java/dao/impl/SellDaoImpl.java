package dao.impl;

import dao.SellDao;
import entity.Good;
import entity.Sell;
import util.DBUtil3;
import util.DBUtil4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellDaoImpl implements SellDao {
    @Override
    public void add(Sell r) {
        String a = r.date;
        String b = r.name;
        String c = r.address;
        String d = r.telephone;
        String e = r.price;
        String sql = "insert into sell values('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"')";
        DBUtil4 db = new DBUtil4();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public void remove(String rname) {
        String sql = "delete from sell where 货物信息 = '"+rname+"'";
        DBUtil4 db = new DBUtil4();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public List<Sell> getAll(String sql) {
        DBUtil4 db = new DBUtil4();
        ResultSet rs = db.executeQuery(sql);
        List<Sell> list = new ArrayList<>();
        try {
            String a,b,c,d,e;
            while(rs.next()) {
                a =rs.getString("日期");
                b =rs.getString("货物信息");
                c =rs.getString("客户地址");
                d =rs.getString("客户电话");
                e =rs.getString("货品销售总价");
                list.add(new Sell(a,b,c,d,e));
            }
            db.close();
            return list;
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Sell getByName(String rname) {
        List<Sell> roles = new SellDaoImpl().getAll("select * from sell");
        for(Sell r : roles){
            if(r.name.equals(rname)){
                return r;
            }
        }
        return null;
    }
}
