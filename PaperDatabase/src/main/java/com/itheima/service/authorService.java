package com.itheima.service;

import com.itheima.pojo.Paper_author;
import com.itheima.pojo.author;

import java.util.List;


public interface authorService {
    //列表查询
    List<author> list() ;

    //根据id查询分类信息
    String findById(Integer id);

    //根据id查询分类信息
    Integer findByname(String name);

    void add(author Author);
    void update(author Author);
    void delete(Integer id);
}

