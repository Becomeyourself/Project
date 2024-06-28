package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //添加分类
    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return Result.success();
    }
    //查询所有分类
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    @GetMapping("/root")
    public Result<List<String>> findroot(){
        List<Category> c = categoryService.findbyfather(0);
        List<String> temp=new ArrayList<>();
        for(Category a:c){
            temp.add(a.getName());
        }
        return Result.success(temp);
    }

    //按照id查询分类
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    @GetMapping("/season")
    public Result<List<String>> detail(String name){
        Integer id = categoryService.findByname(name).getId();
        List<Category> c = categoryService.findbyfather(id);
        List<String> temp=new ArrayList<>();
        for(Category a:c){
            temp.add(a.getName());
        }
        return Result.success(temp);
    }

    @PutMapping
    public Result update(@RequestBody Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }
}
