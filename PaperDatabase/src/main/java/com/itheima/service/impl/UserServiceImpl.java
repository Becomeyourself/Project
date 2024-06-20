package com.itheima.service.impl;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

   /* @Override
    public void register(String username, String password) {
        //加密
        String md5String = Md5Util.getMD5String(password);
        //System.out.println(username+md5String);
        //添加
        userMapper.add(username,md5String);
        System.out.println(333);
    }*/

    @Override
    public void register(User u) {
        //加密
        String md5String = Md5Util.getMD5String(u.getPassword());
        //System.out.println(username+md5String);
        u.setPassword(md5String);
        //添加
        //userMapper.add(username,md5String);
        userMapper.add(u);

    }



    @Override
    public void updatePwd(String newPwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        User u=new User();
        u.setId(id);
        u.setPassword(Md5Util.getMD5String(newPwd));
        userMapper.updatePwd(u);
    }


}
