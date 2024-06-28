package com.itheima.mapper;


import com.itheima.pojo.Paper_keywords;
import com.itheima.pojo.paper_refe;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Paper_keyMapper {


    @Insert("insert into paper_keywords(paper_id,keyword_id)" +
            "values(#{paperId},#{keywordId})")
    void add(Paper_keywords p);


    @Select("select * from paper_keywords where paper_id=#{p}")
    List<Paper_keywords> searchp(Integer p);

    @Select("select * from paper_keywords where keyword_id=#{p}")
    List<Paper_keywords> searchk(Integer p);


    @Delete("delete from paper_keywords where  paper_id=#{paperId} and keyword_id = #{keywordId}")
    void delete(Paper_keywords p);

    @Delete("delete from paper_keywords where  paper_id=#{p} ")
    void deletep(Integer p);



}
