package com.itheima.mapper;


import com.itheima.pojo.Keywords;
import com.itheima.pojo.paper_files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Paper_filesMapper {


    @Insert("insert into paper_files(paper_id, file_path, upload_date)" +
            "values(#{paperId},#{filePath},#{uploadDate})")
    void add(paper_files p);

    @Delete("delete from paper_files where paper_id=#{id}")
    void delete(Integer id);


    @Select("select * from paper_files where paper_id=#{id}")
    List<paper_files> search(Integer id);

    @Update("update paper_files SET file_path = #{filePath} where paper_id = #{paperId}")
    void update(paper_files temp);


}
