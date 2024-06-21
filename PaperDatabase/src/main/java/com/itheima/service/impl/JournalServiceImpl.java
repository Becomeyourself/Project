package com.itheima.service.impl;

import com.itheima.mapper.journalMapper;
import com.itheima.pojo.Journal;
import com.itheima.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private journalMapper journalMapper;
    @Override
    public List<Journal> list() {
        return journalMapper.findall();
    }

    @Override
    public List<Journal> findById(Integer id) {
        return  journalMapper.findById(id);
    }

    @Override
    public List<Journal> findByName(String name) {
        return journalMapper.findByName(name);
    }

    @Override
    public void add(Journal journal) {
        journalMapper.add(journal);
    }

    @Override
    public void delete(Integer id) {
        journalMapper.deleteById(id);
    }

    @Override
    public void update(Journal journal) {
        journalMapper.update(journal);

    }
}
