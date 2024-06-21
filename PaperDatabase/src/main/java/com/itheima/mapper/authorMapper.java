package com.itheima.mapper;

import com.itheima.pojo.Journal;
import com.itheima.pojo.Paper_author;
import com.itheima.pojo.author;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Mapper
public interface authorMapper {


    @Insert("insert into authors(name)" +
            "values(#{name})")
    void add(author Author);

    @Select("select * from authors")
    List<author> findall();

    @Select("select name from authors where id = #{Id}")
    String findById(Integer Id);

    //根据name查询
    @Select("select id from authors where name = #{name}")
    Integer findByname(String name);

    //修改
    @Update("update authors SET name = #{name} where id =#{id}")
    void update(author temp);
    //删除
    //根据id删除
    @Delete("delete from authors where id=#{id}")
    void deleteById(Integer id);
}
