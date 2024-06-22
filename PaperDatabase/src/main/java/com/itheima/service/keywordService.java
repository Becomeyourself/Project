package com.itheima.service;

import com.itheima.pojo.Keywords;

import java.util.List;


public interface keywordService {
    //列表查询
    List<Keywords> list() ;

    //根据id查询分类信息
    List<Keywords> findById(Integer id);

    //根据id查询分类信息
    List<Keywords> findBykeyword(String keyword);

    //添加
    void add(Keywords keyword);
    //删除
    void delete(Integer id);
    //不知道
    void delete1(Keywords Keywords);
    //修改
    void update(Keywords Keywords);
}
