package com.itheima.mapper;

import com.itheima.pojo.Paper_author;
import com.itheima.pojo.Journal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface Paper_authorMapper {

    //查询所有
    @Select("select * from paper_authors")
    List<Paper_author> findall();

    @Select("select * from paper_authors where paper_id = #{Id}")
    List<Paper_author> findBypId(Integer Id);

    //根据name查询
    @Select("select * from paper_authors where paper_authors.author_id =#{id}")
    List<Paper_author> findByaid(int id);

   /* @Insert("insert into paper_authors(paper_id, author_id)" +
            "values(#{paper_id},#{author_id})")
    void add(Integer paper_id, Integer author_id);*/

    @Insert("insert into paper_authors(paper_id, author_id)" +
            "values(#{paperId},#{authorId})")
    void add(Paper_author p);

    @Delete("delete from paper_authors where paper_id=#{name}")
    void delete(Integer name);


}
