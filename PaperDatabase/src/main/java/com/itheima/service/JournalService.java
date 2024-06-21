package com.itheima.service;

import com.itheima.pojo.Journal;

import java.util.List;


public interface JournalService {
    //列表查询
    List<Journal> list() ;

    //根据id查询分类信息
    List<Journal> findById(Integer id);

    //根据id查询分类信息
    List<Journal> findByName(String name);

    //添加
    void add(Journal journal);
    //删除
    void delete(Integer id);
    //修改
    void update(Journal journal);

}
