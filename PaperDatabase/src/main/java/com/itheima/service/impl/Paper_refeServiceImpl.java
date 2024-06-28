package com.itheima.service.impl;

import com.itheima.mapper.Paper_refeMapper;
import com.itheima.pojo.paper_refe;
import com.itheima.service.Paper_refeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Paper_refeServiceImpl implements Paper_refeService {

    @Autowired
    Paper_refeMapper Paper_refeMapper;

    @Override
    public void add(paper_refe p) {
        Paper_refeMapper.add(p);
    }

    @Override
    public paper_refe search(paper_refe p) {
        return Paper_refeMapper.search(p);
    }

    @Override
    public void delete(paper_refe p) {
        Paper_refeMapper.delete(p);
    }

    @Override
    public List<paper_refe> searchlist(Integer id) {
        return Paper_refeMapper.searchlist(id);
    }

    @Override
    public List<paper_refe> searchrlist(Integer id) {
        return Paper_refeMapper.searchrlist(id);
    }
}
