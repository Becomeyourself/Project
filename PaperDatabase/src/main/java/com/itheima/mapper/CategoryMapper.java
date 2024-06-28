package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增
    @Insert("insert into categories(parent_id, name) " +
            "values(#{parentId},#{name})")
    void add(Category category);

    //查询所有
    @Select("select * from categories ")
    List<Category> list();

    //根据id查询
    @Select("select * from categories where id = #{id}")
    Category findById(Integer id);
    //查询父节点
    @Select("select * from categories where parent_id = #{id}")
    List<Category> findByfather(Integer id);



    //根据id删除
    @Delete("delete from categories where id=#{id}")
    void deleteById(Integer id);

    @Select("select * from categories where name= #{name}")
    Category findByname(String name);

    @Update("update categories SET parent_id = #{parentId},name = #{name} where id =#{id}")
    void update(Category category);
}
