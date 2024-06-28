package com.itheima.controller;



import com.itheima.pojo.Keywords;
import com.itheima.pojo.Paper_keywords;
import com.itheima.pojo.Result;
import com.itheima.service.impl.KeywordServiceImpl;
import com.itheima.service.impl.PaperServiceImpl;
import com.itheima.service.impl.Paper_keyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/paper_key")
public class paperkeywordController {
    @Autowired
    private Paper_keyServiceImpl paper_keyService;

    @Autowired
    private KeywordServiceImpl KeywordService;

    @Autowired
    private PaperServiceImpl PaperService;


    @GetMapping("/id")
    //根据论文id查询关键字
    Result<List<String>> findBypid(String name){
        Integer id;
        if(PaperService.find_by_title(name).isEmpty()) {
            return Result.error("No such files!");
        }
        id=PaperService.find_by_title(name).get(0).getId();
        List<Paper_keywords> temp=paper_keyService.searchp(id);
        //提取id
        List<String> result=new ArrayList<>();
        for(Paper_keywords a:temp){
            result.add(KeywordService.findById(a.getKeywordId()).get(0).getKeyword());
        }
        return Result.success(result);
    }

    //根据关键词查询论文name
    @GetMapping("/keyword")
    Result<List<String>> findBykeyword(String keyword){
        Integer temp=KeywordService.findBykeyword(keyword).get(0).getId();
        List<Paper_keywords> tt=paper_keyService.searchk(temp);
        List<String> result=new ArrayList<>();
        for(Paper_keywords a:tt){
            result.add(PaperService.find_by_id(a.getPaperId()).get(0).getTitle());
        }
        return Result.success(result);
    }

    //添加
    @PostMapping("/add")
    Result add(String name1,String name2){
        Integer id=PaperService.find_by_title(name1).get(0).getId();
        if(KeywordService.findBykeyword(name2).isEmpty()){
            Keywords k=new Keywords();
            k.setKeyword(name2);
            KeywordService.add(k);
        }
        Integer temp=KeywordService.findBykeyword(name2).get(0).getId();
        paper_keyService.add(id,temp);
        return Result.success();
    }
    //删除
    @DeleteMapping("/deletep")
    Result deletep(String name){
        Integer id=PaperService.find_by_title(name).get(0).getId();
        paper_keyService.deletep(id);
        return Result.success();
    }

    @DeleteMapping("/delete")
    Result delete1(String name1,String name2){
        Integer id=PaperService.find_by_title(name1).get(0).getId();
        Integer temp=KeywordService.findBykeyword(name2).get(0).getId();
        paper_keyService.delete(id,temp);
        return Result.success();
    }

}
