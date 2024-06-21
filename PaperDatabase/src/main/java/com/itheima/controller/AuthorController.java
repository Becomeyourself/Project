package com.itheima.controller;

import com.itheima.pojo.author;
import com.itheima.pojo.Result;
import com.itheima.service.authorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private authorService authorService;

    @PostMapping
    public Result add(@RequestBody author author) {
        authorService.add(author);
        return Result.success();
    }

    // 查询所有作者
    @GetMapping
    public Result<List<author>> list() {
        List<author> authors = authorService.list();
        return Result.success(authors);
    }

    // 修改
    @PutMapping
    public Result update(@RequestBody author author) {
        authorService.update(author);
        return Result.success();
    }

    // 删除
    @DeleteMapping
    public Result delete(Integer id) {
        authorService.delete(id);
        return Result.success();
    }
}
