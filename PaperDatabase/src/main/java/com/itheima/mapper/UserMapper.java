package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    //添加
   /* @Insert("insert into user(username,password)" +
            " values(#{username},#{password})")
    void add(String username, String password);*/

    @Insert("insert into user(username,password)" +
            " values(#{username},#{password})")
    void add(User u);

    @Update("update user set password=#{password} where id=#{id}")
    void updatePwd(User u);
}
