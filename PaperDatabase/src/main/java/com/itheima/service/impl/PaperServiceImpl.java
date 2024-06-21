package com.itheima.service.impl;

import com.itheima.mapper.PaperMapper;
import com.itheima.pojo.Papers;
import com.itheima.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    PaperMapper paperMapper;
    //根据作者查论文
    @Override
    public List<Papers> find_by_author(String author){
        List<Papers> result= paperMapper.findByAuthorName(author);
        return result;
    }

    @Override
    public List<Papers> find_all(){

        return paperMapper.GetALL();
    }

    @Override
    public List<Papers> find_by_category(String category) {

        return paperMapper.FindByCategories(category);
    }

    @Override
    public List<Papers> find_by_date(Date date) {
        return paperMapper.FindByDate(date);
    }

    @Override
    public List<Papers> find_by_title(String title) {
        return paperMapper.FindByTitle(title);
    }

    @Override
    public List<Papers> find_by_keyword(String keyword) {
        return paperMapper.FindByKeywords(keyword);
    }

    @Override
    public List<Papers> find_by_journal(String journal) {
        return paperMapper.FindByJournal(journal);
    }

    @Override
    public List<Papers> find_by_id(Integer id) {
        return paperMapper.findById(id);
    }

    @Override
    public void add_paper(Papers paper){paperMapper.add(paper);}

    @Override
    public void dele_paper(Integer id) {
        paperMapper.delete(id);
    }
}
