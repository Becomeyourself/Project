package com.itheima.service.impl;

import com.itheima.mapper.Paper_filesMapper;
import com.itheima.pojo.paper_files;
import com.itheima.service.Paper_filesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Paper_filesServiceImpl implements Paper_filesService {
    @Autowired
    Paper_filesMapper Paper_filesMapper;

    @Override
    public void add(paper_files p) {
        List<paper_files> results = Paper_filesMapper.search(p.getPaperId());
        if (results.isEmpty()) {
            Paper_filesMapper.add(p);
        } else {
            Paper_filesMapper.update(p);
        }
    }


    @Override
    public void delete(Integer id) {
        Paper_filesMapper.delete(id);
    }

    @Override
    public List<paper_files> search(Integer id) {
        return Paper_filesMapper.search(id);
    }
    //根据作者查论文

}
