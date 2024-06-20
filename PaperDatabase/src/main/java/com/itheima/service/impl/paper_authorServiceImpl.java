package com.itheima.service.impl;


import com.itheima.mapper.Paper_authorMapper;
import com.itheima.pojo.Paper_author;
import com.itheima.service.paper_authorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class paper_authorServiceImpl implements paper_authorService {

    @Autowired
    private Paper_authorMapper Paper_authorMapper;
    @Override
    public List<Paper_author> list() {
        return Paper_authorMapper.findall();
    }

    @Override
    public List<Paper_author> findBypId(Integer id) {
        return  Paper_authorMapper.findBypId(id);
    }

    @Override
    public List<Paper_author> findByaid(int id) {
        List<Paper_author>  result= Paper_authorMapper.findByaid(id);
        return result;
    }

    @Override
    public void add(Integer paper_id, Integer author_id) {
        Paper_authorMapper.add(paper_id, author_id);
    }

    @Override
    public void delete(Integer name) {
        Paper_authorMapper.delete(name);
    }


}
