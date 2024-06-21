package com.itheima.mapper;

import com.itheima.pojo.Journal;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface journalMapper {

    //查询所有
    @Select("select * from journals")
    List<Journal> findall();

    @Select("select * from journals where id = #{Id}")
    List<Journal> findById(Integer Id);

    //根据name查询
    @Select("select * from journals where name = #{name}")
    List<Journal> findByName(String name);

    //修改
    @Update("update journals SET name = #{name} where id =#{id}")
    void update(Journal temp);
    //删除
    //根据id删除
    @Delete("delete from journals where id=#{id}")
    void deleteById(Integer id);
    //增加
    @Insert("insert into journals(name) " +
            "values(#{name})")
    void add(Journal temp);



}
