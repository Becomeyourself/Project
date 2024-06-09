package dao.impl;

import dao.LoginDao;
import util.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDao {

    @Override
    public boolean login(String uid, String pwd) {
        String sql = "select * from 用户信息 where 用户账号 = '"+uid+"' and 用户密码 = '"+pwd+"'";
        DBUtil db = new DBUtil();
        ResultSet rs = db.executeQuery(sql);
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

