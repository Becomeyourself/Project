package com.itheima.service;

import com.itheima.pojo.Papers;

import java.util.List;

public interface PaperService {
    //根据作者查论文
    public List<Papers> find_by_author(String author);

    //查询所有论文
    public List<Papers> find_all();

    //根据分类查论文
    public List<Papers> find_by_category(String category);

    //根据论文名字查论文
    public Papers find_by_title(String title);
    //根据关键字查论文
    public List<Papers> find_by_keyword(String keyword);
    //根据刊物查论文
    public List<Papers> find_by_journal(String journal);
    //根据id查论文
    public Papers find_by_id(Integer id);
    //添加
    public void add_paper(Papers paper);
    //删除
    public void dele_paper(Integer id);
}
