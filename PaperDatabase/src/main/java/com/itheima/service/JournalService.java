package com.itheima.service;

import com.itheima.pojo.journal;

import java.util.List;


public interface JournalService {
    //列表查询
    List<journal> list() ;

    //根据id查询分类信息
    journal findById(Integer id);

    //根据id查询分类信息
    journal findByname(String name);
}
