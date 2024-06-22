package com.itheima.mapper;

import com.itheima.pojo.Keywords;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface keywordMapper {

    //查询所有
    @Select("select * from keywords")
    List<Keywords> findall();

    @Select("select * from keywords where id = #{Id}")
    List<Keywords> findById(Integer Id);

    //根据name查询
    @Select("select * from keywords where keyword = #{keyword}")
    List<Keywords> findBykeyword(String keyword);

    //修改

    @Update("update keywords SET keyword = #{keyword} where id = #{id}")
    void update(Keywords temp);
    //不知道什么东西
    @Delete("delete from keywords where id=#{id} and keyword=#{keyword}")
    void delete1(Keywords temp);
    //删除
    //根据id删除
    @Delete("delete from keywords where id=#{id}")
    void delete(Integer id);
    //增加
    @Insert("insert into keywords(id,keyword) " +
            "values(#{id},#{keyword})")
    void add(Keywords temp);



}
