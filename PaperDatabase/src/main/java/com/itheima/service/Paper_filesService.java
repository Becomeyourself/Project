package com.itheima.service;

import com.itheima.pojo.paper_files;

import java.util.List;


public interface Paper_filesService {


    void add(paper_files p);
    void delete(Integer id);
    List<paper_files> search(Integer id);

}
