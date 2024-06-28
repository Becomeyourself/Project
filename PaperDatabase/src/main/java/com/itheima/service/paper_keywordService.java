package com.itheima.service;

import com.itheima.pojo.Paper_author;
import com.itheima.pojo.Paper_keywords;

import java.util.List;


public interface paper_keywordService {


    //根据id查询分类信息
    List<Paper_keywords> searchp(Integer pid);

    //根据id查询分类信息
    public List<Paper_keywords> searchk(Integer kid);

    void add(Integer paper_id, Integer key_id);

    void deletep(Integer pid);

    void delete(Integer pid,Integer kid);
}
