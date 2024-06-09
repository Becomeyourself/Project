package dao.impl;

import dao.StoreDao;
import entity.Good;
import entity.Store;
import util.DBUtil;
import util.DBUtil3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreDaoImpl implements StoreDao {
    @Override
    public void add(Store s) {
        String a = s.storename;
        String b = s.goodnumber;
        String c = s.goodname;
        String d = s.inprice;
        String e = s.number;
        String f = s.outprice;
        String g = s.people;
        String sql = "insert into stores values('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"')";
        DBUtil3 db = new DBUtil3();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public void remove(String name,String number) {
        String sql = "delete from stores where 货品编号 = '"+name+"' AND 仓库名称 = '"+number+"'";
        DBUtil3 db = new DBUtil3();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public List<Store> getAll(String sql,String name) {
        DBUtil3 db = new DBUtil3();
        ResultSet rs = db.executeQuery(sql);
        List<Store> list = new ArrayList<>();
        List<Store> list2 = new ArrayList<>();
        try {
            String a,b,c,d,e,f,g;
            while(rs.next()) {
                a =rs.getString("仓库名称");
                b = rs.getString("货品编号");
                c =rs.getString("货品名称");
                d = rs.getString("进货价格");
                e = rs.getString("货品数量");
                f =rs.getString("销售单价");
                g = rs.getString("供货商");
                list.add(new Store(a,b,c,d,e,f,g));
            }
            db.close();
            for(Store s :list){
                if(s.storename.equals(name)){
                    list2.add(s);
                }
            }
            return list2;
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Store getByNumber(String number,String name) {
        List<Store> roles = new StoreDaoImpl().getAll("select * from stores",name);
        for(Store r : roles){
            if(r.goodnumber.equals(number)){
                return r;
            }
        }
        return null;
    }

    @Override
    public Double getByName(String name) {
        List<Store> roles = new StoreDaoImpl().getAll("select * from stores","仓库1");
        for(Store s : roles){
            if(s.goodname.equals(name)){
                String a = s.outprice.substring(0,s.outprice.length()-3);
                Double x = Double.valueOf(a);
                return x;
            }
        }
        return null;
    }
    public Store getByNamea(String name,String number){
        List<Store> roles = new StoreDaoImpl().getAll("select * from stores",number);
        for(Store r : roles){
            if(r.goodname.equals(name)){
                return r;
            }
        }
        return null;
    }
}
