package com.itheima.service;

import com.itheima.pojo.Paper_author;
import com.itheima.pojo.Journal;

import java.util.List;


public interface paper_authorService {
    //列表查询
    List<Paper_author> list() ;

    //根据id查询分类信息
    List<Paper_author> findBypId(Integer id);

    //根据id查询分类信息
    public List<Paper_author> findByaid(int id);

    void add(Integer paper_id, Integer author_id);

    void delete(Integer name);
}
