package com.itheima.mapper;


import com.itheima.pojo.paper_refe;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface Paper_refeMapper {


    @Insert("insert into paper_references(paper_id,reference_id)" +
            "values(#{paperId},#{referenceId})")
    void add(paper_refe p);


    @Select("select * from paper_references where paper_id=#{id1} and reference_id = #{id2}")
    paper_refe search(paper_refe p);


    @Delete("delete from paper_references where  paper_id=#{id1} and reference_id = #{id2}")
    void delete(paper_refe p);

    @Select("select * from paper_references where paper_id=#{id1}")
    List<paper_refe> searchlist(Integer id1);

}
