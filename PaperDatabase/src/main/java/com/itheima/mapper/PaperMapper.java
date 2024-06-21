package com.itheima.mapper;

import com.itheima.pojo.Papers;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Mapper
public interface PaperMapper {
    //根据作者查论文
    @Select("SELECT * FROM papers WHERE papers.id " +
            "IN (SELECT paper_authors.paper_id FROM paper_authors " +
            "JOIN authors ON paper_authors.author_id = authors.id " +
            "WHERE authors.name = #{AuthorName})")
    List<Papers> findByAuthorName(String AuthorName);
    //根据分类查论文
    @Select("SELECT * FROM papers WHERE papers.category_id " +
            "IN ( SELECT id FROM categories  " +
            "WHERE categories.name = #{Categories})")
    List<Papers> FindByCategories(String Categories);
    //查询所有论文
    @Select("SELECT * FROM papers")
    List<Papers> GetALL();
    //根据论文名字查论文
    @Select("SELECT * FROM papers WHERE papers.title = #{Title}")
    List<Papers> FindByTitle(String Title);

    //根据关键字查论文
    @Select("SELECT * FROM papers WHERE papers.id " +
            "IN ( SELECT id FROM paper_keywords NATURAL JOIN keywords  " +
            "WHERE keywords.keyword = #{Keywords})")
    List<Papers> FindByKeywords(String Keywords);
    //根据id查论文
    @Select("SELECT * from papers WHERE id = #{id}")
    List<Papers> findById(Integer id);
    //根据时间查论文
    @Select("SELECT * FROM papers WHERE DATE(papers.publication_date)  =  DATE(#{date})")
    List<Papers> FindByDate(@Param("date") @DateTimeFormat(pattern = "yyyy-MM-dd")Date date);
    //根据刊物查论文
    @Select("SELECT * FROM papers WHERE papers.id " +
            "IN ( SELECT id FROM journals  " +
            "WHERE journals.name = #{Journal})")
    List<Papers> FindByJournal(String Journal);
    //添加
    @Insert("insert into papers(title, Abstract, journal_id, category_id, file_path, publication_date)" +
            " values(#{title}, #{Abstract}, #{journalId}, #{categoryId}, #{filePath}, #{publicationDate})")
    void add(Papers paper);

    @Update("UPDATE papers SET title = #{title}, abstract = #{abstractText}, journal_id = #{journalId}, " +
            "category_id = #{categoryId}, file_path = #{filePath}, publication_date = #{publicationDate} " +
            "WHERE id = #{id}")
    void update(Papers paper);

    @Delete("delete from papers where id=#{id}")
    void delete(Integer id);
}
