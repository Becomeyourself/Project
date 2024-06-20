package com.itheima.controller;


import com.itheima.mapper.Paper_authorMapper;
import com.itheima.pojo.*;
import com.itheima.service.PaperService;
import com.itheima.service.authorService;
import com.itheima.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Paper")
public class PaperController {
    @Autowired
    PaperServiceImpl paperService;

    @Autowired
    CategoryServiceImpl CategoryService;

    @Autowired
    JournalServiceImpl JournalService;

    @Autowired

    authorServiceImpl authorService;

    @Autowired
    paper_authorServiceImpl paper_authorService;
    //根据作者查论文
    // 在URL栏中输入你的API的URL，http://localhost:8080/Paper/Author?author_name=Alice
    //在URL的末尾添加?author_name=your_author_name，其中your_author_name是你想要查询的作者名字。
    @GetMapping("/Author")
    public Result<List<Papers>> Find_By_Author_Name( String author_name){
        List<Papers> temp = paperService.find_by_author(author_name);
        return Result.success(temp);
    }
    //根据分类查论文
    @GetMapping("/Categories")
    public Result<List<Papers>> Find_By_Categories(String Categories){
        List<Papers> temp = paperService.find_by_category(Categories);
        return Result.success(temp);
    }
    //查询全部论文
    @GetMapping
    public Result<List<Papers>> Get_ALL(){
        List<Papers> temp = paperService.find_all();
        return Result.success(temp);
    }

    //根据论文名字查论文
    @GetMapping("/Title")
    public Result<Papers> Find_By_Title(String Title){
        Papers temp = paperService.find_by_title(Title);
        return Result.success(temp);
    }
    //根据关键字查论文
    @GetMapping("/Keyword")
    public Result<List<Papers>> Find_By_Keyword(String Keyword){
        List<Papers> temp = paperService.find_by_keyword(Keyword);
        return Result.success(temp);
    }
    //根据刊物查论文
    @GetMapping("/Journal")
    public Result<List<Papers>> Find_By_Journal(String Journal){
        List<Papers> temp = paperService.find_by_journal(Journal);
        return Result.success(temp);
    }
    //添加


    //会添加paper_author和author两个表格，还有papers
    @PostMapping("/add")
    public Result<String> add_paper(@RequestBody PapersDo paperDo){

        Category c=CategoryService.findByname(paperDo.getCategory());

        Papers p=new Papers();
        p.setTitle(paperDo.getTitle());
        p.setAbstract(paperDo.getAbstractText());
        p.setCategoryId(c.getId());
        journal j=JournalService.findByname(paperDo.getJournal());
        p.setJournalId(j.getId());
        p.setFilePath(paperDo.getFile_path());
        p.setPublicationDate(paperDo.getPublicationDate());
        Papers papers=paperService.find_by_title(p.getTitle());
        String author1= paperDo.getAuthor1();
        String author2= paperDo.getAuthor2();
        Integer aid1=null;
        Integer aid2=null;
        if(!Objects.equals(author1, "")){
            aid1= authorService.findByname(author1);
            if(aid1==null){
                authorService.add(author1);
                aid1=authorService.findByname(author1);
            }
        }
        System.out.println(author2);

        if(!Objects.equals(author2, "")){
            aid2=authorService.findByname(author2);
            if(aid2==null){
                authorService.add(author2);
                aid2=authorService.findByname(author2);
            }
        }
        Integer id;
        if(papers==null){
            paperService.add_paper(p);
            id=paperService.find_by_title(p.getTitle()).getId();
            Paper_author pp=new Paper_author();
            pp.setPaperId(id);
            pp.setAuthorId(aid1);
            paper_authorService.add(id,aid1);
            if(aid2!=null){
                pp.setAuthorId(aid2);
                paper_authorService.add(id,aid2);
            }
            return Result.success("add success!");
        }
       else {
           return Result.error("the paper cant add twice!");
        }
    }

    //删除,默认论文名字唯一，只删除了paper_authors和papers两个表格
    @PostMapping("/delete")
    public Result<String> delete_paper(String name){
        Papers p=paperService.find_by_title(name);
        if(p==null)  return Result.error("no such file");
        else{
            Integer pid=p.getId();
            paper_authorService.delete(pid);
            paperService.dele_paper(pid);

            return Result.success();

        }



    }


    //关系网络要调用的接口，大概是根据名字找到相联系的name列表
    @GetMapping("/search")
    public Result<List<String>> social_search(String name){
        List<String> result;
        int aid=authorService.findByname(name);
        System.out.println(aid);
        List<Paper_author> pa=paper_authorService.findByaid(aid);

        List<Integer>  pids = new ArrayList<>();
        //拿到相关文章id
        for (Paper_author paperAuthor : pa) {
            //System.out.println(paperAuthor.getAuthorId());
            pids.add(paperAuthor.getPaperId());
        }
        System.out.println(222);
        System.out.println(pids);

        Set<Integer> aids = new HashSet<>();

        // 遍历每个 pid 并获取相应的 Paper_author 列表
        for (int pid : pids) {
            List<Paper_author> paList = paper_authorService.findBypId(pid);
            // 提取每个 Paper_author 的 aid 并加入到 aids 集合中
            for (Paper_author paperAuthor : paList) {
                aids.add(paperAuthor.getAuthorId());
            }

        }

        // 移除初始 aid
        aids.remove(aid);

        // 将 Set 转换为 List
        List<Integer> finalAidList = aids.stream().toList();
        List<String> names = finalAidList.stream()
                .map(authorService::findById)
                .toList();


        return Result.success(names);





    }


}
