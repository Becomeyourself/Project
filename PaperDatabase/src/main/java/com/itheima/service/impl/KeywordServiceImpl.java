package com.itheima.service.impl;

import com.itheima.mapper.keywordMapper;
import com.itheima.pojo.Keywords;
import com.itheima.service.keywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements keywordService {

    @Autowired
    private keywordMapper keywordMapper;


    @Override
    public List<Keywords> list() {
        return keywordMapper.findall();
    }

    @Override
    public List<Keywords> findById(Integer id) {

        return keywordMapper.findById(id);
    }

    @Override
    public List<Keywords> findBykeyword(String keyword) {
        return keywordMapper.findBykeyword(keyword);
    }


    @Override
    public void add(Keywords keyword) {
        keywordMapper.add(keyword);
    }

    @Override
    public void delete(Integer id) {
        keywordMapper.delete(id);
    }

    @Override
    public void delete1(Keywords Keywords) {
        keywordMapper.delete1(Keywords);
    }

    @Override
    public void update(Keywords Keywords) {
        keywordMapper.update(Keywords);
    }
}
