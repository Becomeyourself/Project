package com.itheima.mapper;

import com.itheima.pojo.Category;
import com.itheima.pojo.journal;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface journalMapper {

    //查询所有
    @Select("select * from journals")
    List<journal> findall();

    @Select("select * from journals where id = #{userId}")
    journal findById(Integer userId);

    //根据name查询
    @Select("select * from journals where name = #{name}")
    journal findByname(String name);
}
