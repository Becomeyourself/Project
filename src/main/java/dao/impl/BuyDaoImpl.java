package dao.impl;

import dao.BuyDao;
import entity.Buy;
import entity.Sell;
import util.DBUtil4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyDaoImpl implements BuyDao {
    @Override
    public void add(Buy r) {
        String a = r.date;
        String b = r.name;
        String c = r.reason;
        String d = r.amount;
        String e = r.price;
        String f = r.check;
        String g = r.reson2;
        String sql = "insert into buys values('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"')";
        DBUtil4 db = new DBUtil4();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public void remove(String rname) {
        String sql = "delete from buys where 货品信息 = '"+rname+"'";
        DBUtil4 db = new DBUtil4();
        db.executeUpdate(sql);
        db.close();
    }

    @Override
    public List<Buy> getAll(String sql) {
        DBUtil4 db = new DBUtil4();
        ResultSet rs = db.executeQuery(sql);
        List<Buy> list = new ArrayList<>();
        try {
            String a,b,c,d,e,f,g;
            while(rs.next()) {
                a =rs.getString("日期");
                b =rs.getString("货品信息");
                c =rs.getString("退货原因");
                d =rs.getString("货品总数");
                e =rs.getString("总价格");
                f =rs.getString("审核状态");
                g =rs.getString("驳回原因");
                list.add(new Buy(a,b,c,d,e,f,g));
            }
            db.close();
            return list;
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Buy getByName(String rname) {
        List<Buy> roles = new BuyDaoImpl().getAll("select * from buys");
        for(Buy r : roles){
            if(r.name.equals(rname)){
                return r;
            }
        }
        return null;
    }
}
