package com.itheima.controller;


import com.itheima.pojo.Category;
import com.itheima.pojo.Journal;
import com.itheima.pojo.Result;
import com.itheima.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {
    @Autowired
    private JournalService journalService;

    @PostMapping
    public Result add(@RequestBody Journal journal) {
        journalService.add(journal);
        return Result.success();
    }

    //查询所有期刊
    @GetMapping
    public Result<List<Journal>> list(){
        List<Journal> cs = journalService.list();
        return Result.success(cs);
    }
    //修改
    @PutMapping
    public Result update(@RequestBody Journal journal){
        journalService.update(journal);
        return Result.success();
    }
    //删除
    @DeleteMapping
    public Result delete(Integer id){
        journalService.delete(id);
        return Result.success();
    }
}
