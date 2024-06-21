package com.itheima.service.impl;


import com.itheima.mapper.Paper_authorMapper;
import com.itheima.mapper.authorMapper;
import com.itheima.pojo.Paper_author;
import com.itheima.pojo.author;
import com.itheima.service.authorService;
import com.itheima.service.paper_authorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class authorServiceImpl implements authorService {

    @Autowired
    private authorMapper authorMapper;
    @Override
    public List<author> list() {
        return authorMapper.findall();
    }

    @Override
    public String findById(Integer id) {
        return authorMapper.findById(id);
    }

    @Override
    public Integer findByname(String name) {
        return authorMapper.findByname(name);
    }

    @Override
    public void add(author Author) {
        authorMapper.add(Author);
    }

    @Override
    public void update(author Author) {
        authorMapper.update(Author);
    }

    @Override
    public void delete(Integer id) {
        authorMapper.deleteById(id);
    }

}
