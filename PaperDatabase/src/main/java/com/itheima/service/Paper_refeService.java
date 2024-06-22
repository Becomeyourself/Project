package com.itheima.service;

import com.itheima.pojo.paper_refe;

import java.util.List;


public interface Paper_refeService {


    void add(paper_refe p);

    paper_refe search(paper_refe p);

    void delete(paper_refe p);

    List<paper_refe> searchlist(Integer id);
}
