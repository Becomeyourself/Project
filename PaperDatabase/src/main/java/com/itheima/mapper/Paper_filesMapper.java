package com.itheima.mapper;


import com.itheima.pojo.paper_files;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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


}
