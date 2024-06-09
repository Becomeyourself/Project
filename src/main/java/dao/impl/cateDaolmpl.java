package dao.impl;

import dao.cateDao;
import entity.cate;
import util.DBUtil2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class cateDaolmpl implements cateDao {
        @Override
        public void add(cate b) {
            String name = b.getName();
            String ceng = b.getCeng();
            String child = b.getChild();
            String sibling = b.getSibling();
            String father = b.getFather();
            String sql = "insert into buyer values('"+name+"','"+ceng+"','"+father+"','"+sibling+"','"+child+"')";
            DBUtil2 db = new DBUtil2();
            db.executeUpdate(sql);
            db.close();
        }

        @Override
        public void remove(String name) {
            String sql = "delete from buyer where 类别名称 = '"+name+"'";
            DBUtil2 db = new DBUtil2();
            db.executeUpdate(sql);
            db.close();
        }

        @Override
        public List<cate> getAll(String sql) {
            DBUtil2 db = new DBUtil2();
            ResultSet rs = db.executeQuery(sql);
            List<cate> list = new ArrayList<>();
            try {
                 String name;
                 String ceng;
                 String father;
                 String sibling;
                 String child;
                while(rs.next()) {
                    name =rs.getString("类别名称");
                    ceng = rs.getString("层数");
                    father = rs.getString("父类别名称");
                    sibling = rs.getString("右兄弟类别名称");
                    child = rs.getString("最左子类别名称");
                    list.add(new cate(name,ceng,father,sibling,child));
                }
                db.close();
                return list;
            } catch (SQLException |NullPointerException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public cate getByName(String name) {
            List<cate> roles = new cateDaolmpl().getAll("select * from category");
            for(cate b : roles){
                if(b.getName().equals(name)){
                    return b;
                }
            }
            return null;
        }
}


