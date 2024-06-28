package com.itheima.service.impl;

import com.itheima.mapper.Paper_keyMapper;
import com.itheima.mapper.Paper_refeMapper;
import com.itheima.pojo.Paper_keywords;
import com.itheima.pojo.paper_refe;
import com.itheima.service.Paper_refeService;
import com.itheima.service.paper_keywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Paper_keyServiceImpl implements paper_keywordService {

    @Autowired
    Paper_keyMapper paper_keyMapper;


    @Override
    public List<Paper_keywords> searchp(Integer pid) {
        return paper_keyMapper.searchp(pid);
    }

    @Override
    public List<Paper_keywords> searchk(Integer kid) {
        return paper_keyMapper.searchk(kid);
    }

    @Override
    public void add(Integer paper_id, Integer key_id) {
        Paper_keywords temp=new Paper_keywords();
        temp.setPaperId(paper_id);
        temp.setKeywordId(key_id);
        paper_keyMapper.add(temp);
    }

    @Override
    public void deletep(Integer pid) {
        paper_keyMapper.deletep(pid);
    }

    @Override
    public void delete(Integer pid, Integer kid) {
        Paper_keywords temp=new Paper_keywords();
        temp.setPaperId(pid);
        temp.setKeywordId(kid);
        paper_keyMapper.delete(temp);
    }
}
