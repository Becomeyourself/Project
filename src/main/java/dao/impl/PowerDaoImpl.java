package dao.impl;

import dao.PowerDao;
import entity.Power;
import entity.Role;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PowerDaoImpl implements PowerDao {

    @Override
    public List<Power> getAll(String sql) {
        DBUtil db = new DBUtil();
        ResultSet rs = db.executeQuery(sql);
        List<Power> list = new ArrayList<>();
        try {
            String pname;
            String inform;
            while(rs.next()) {
                pname =rs.getString("权限名称");
                inform = rs.getString("权限说明");
                list.add(new Power(pname,inform));
            }
            db.close();
            return list;
        } catch (SQLException |NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
