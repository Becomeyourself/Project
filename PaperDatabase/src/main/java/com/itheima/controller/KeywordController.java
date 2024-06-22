package com.itheima.controller;



import com.itheima.pojo.Keywords;
import com.itheima.pojo.Result;
import com.itheima.service.impl.KeywordServiceImpl;
import com.itheima.service.impl.PaperServiceImpl;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/keyword")
public class KeywordController {
    @Autowired
    private KeywordServiceImpl keywordService;

    @Autowired
    private PaperServiceImpl PaperService;
    @GetMapping()
    Result<List<Keywords>> list(){
        return Result.success(keywordService.list());
    }

    @GetMapping("/id")
    //根据id查询分类信息
    Result<List<Keywords>> findById(String name){
        Integer id;
        if(PaperService.find_by_title(name).isEmpty())  return Result.error("No such files!");
        id=PaperService.find_by_title(name).get(0).getId();
        return Result.success(keywordService.findById(id));
    }

    //根据id查询分类信息
    @GetMapping("/keyword")
    Result<List<Keywords>> findBykeyword(String keyword){
        return Result.success(keywordService.findBykeyword(keyword));
    }

    //添加
    @PostMapping()
    Result add(@RequestBody Keywords keyword){
        keywordService.add(keyword);
        return Result.success();
    }
    //删除
    @DeleteMapping()
    Result delete(Integer id){
        keywordService.delete(id);
        return Result.success();
    }
    @DeleteMapping("/delete1")
    Result delete1(@RequestBody Keywords Keywords){
        keywordService.delete1(Keywords);
        return Result.success();
    }

    @PutMapping()
    Result update(@RequestBody Keywords Keywords){
        keywordService.update(Keywords);
        return Result.success();
    }
}
