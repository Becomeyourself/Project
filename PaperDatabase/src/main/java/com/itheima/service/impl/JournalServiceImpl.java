package com.itheima.service.impl;

import com.itheima.mapper.journalMapper;
import com.itheima.pojo.journal;
import com.itheima.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private journalMapper journalMapper;
    @Override
    public List<journal> list() {
        return journalMapper.findall();
    }

    @Override
    public journal findById(Integer id) {
        return  journalMapper.findById(id);
    }

    @Override
    public journal findByname(String name) {
        return journalMapper.findByname(name);
    }
}
