package dao.impl;

import dao.IndataDao;
import entity.Good;
import entity.Indata;
import util.DBUtil3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndataDaoImpl implements IndataDao {
    @Override
    public void add(Indata in) {
        String a = in.date;
        String b = in.goodname;
        String c = in.inprice;
        String d = in.price;
        String e = in.storename;
        String f = in.people;
        String g = in.check;
        String sql = "insert into indata values('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"')";
        DBUtil3 db = new DBUtil3();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public void remove(String name,String storename) {
        String sql = "delete from indata where 货品信息 = '"+name+"'AND 仓库信息 = '"+storename+"'";
        DBUtil3 db = new DBUtil3();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public List<Indata> getAll(String sql) {
        DBUtil3 db = new DBUtil3();
        ResultSet rs = db.executeQuery(sql);
        List<Indata> list = new ArrayList<>();
        try {
            String date,goodname,inprice,price,store,people,check;
            while(rs.next()) {
                date =rs.getString("日期");
                goodname = rs.getString("货品信息");
                inprice =rs.getString("进货价格");
                price = rs.getString("总价格");
                store =rs.getString("仓库信息");
                people = rs.getString("供货商");
                check = rs.getString("付款状态");
                Indata a = new Indata(date,goodname,inprice,price,store,people);
                a.check = check;
                list.add(a);
            }
            db.close();
            return list;
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
