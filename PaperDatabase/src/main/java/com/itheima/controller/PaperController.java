package com.itheima.controller;


import com.itheima.pojo.*;
import com.itheima.service.Paper_filesService;
import com.itheima.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
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

    @Autowired
    Paper_filesServiceImpl Paper_filesService;

    @Autowired
    Paper_refeServiceImpl Paper_refeService;
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
    public Result<List<Papers>> Find_By_Categories(String categories){
        List<Papers> temp = paperService.find_by_category(categories);
        return Result.success(temp);
    }
    //查询全部论文
    @GetMapping
    public Result<List<Papers>> Get_ALL(){
        List<Papers> temp = paperService.find_all();
        return Result.success(temp);
    }
    //根据时间查论文
    @GetMapping("/date")
    public Result<List<Papers>> Find_By_Date( @DateTimeFormat(pattern = "yyyy-MM-dd")Date date){
        List<Papers> temp = paperService.find_by_date(date);
        return Result.success(temp);
    }
    //根据论文名字查论文
    @GetMapping("/Title")
    public Result<List<Papers>> Find_By_Title(String title){
        List<Papers> temp = paperService.find_by_title(title);
        return Result.success(temp);
    }
    //根据关键字查论文
    @GetMapping("/Keyword")
    public Result<List<Papers>> Find_By_Keyword(String keyword){
        List<Papers> temp = paperService.find_by_keyword(keyword);
        return Result.success(temp);
    }
    //根据刊物查论文
    @GetMapping("/Journal")
    public Result<List<Papers>> Find_By_Journal(String journal){
        List<Papers> temp = paperService.find_by_journal(journal);
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
        List<Journal> j=JournalService.findByName(paperDo.getJournal());

        p.setJournalId(j.get(0).getId());
        p.setFilePath(paperDo.getFile_path());
        p.setPublicationDate(paperDo.getPublicationDate());
        List<Papers> papers=paperService.find_by_title(p.getTitle());
        author author1 = null,author2 = null;
        author1.setName(paperDo.getAuthor1()) ;
        author2.setName(paperDo.getAuthor2()) ;
        Integer aid1=null;
        Integer aid2=null;
        if(!Objects.equals(author1.getName(), "")){
            aid1= authorService.findByname(author1.getName());
            if(aid1==null){
                authorService.add(author1);
                aid1=authorService.findByname(author1.getName());
            }
        }
        System.out.println(author2);

        if(!Objects.equals(author2.getName(), "")){
            aid2=authorService.findByname(author2.getName());
            if(aid2==null){
                authorService.add(author2);
                aid2=authorService.findByname(author2.getName());
            }
        }
        Integer id;
        if(papers == null || papers.isEmpty()){
            paperService.add_paper(p);
            List<Papers> temp = paperService.find_by_title(p.getTitle());
            id = temp.get(0).getId();
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

    //删除，只删除了paper_authors和papers两个表格
    @DeleteMapping
    public Result<String> delete_paper(Integer id){
        List<Papers> p=paperService.find_by_id(id);
        if(p != null && !p.isEmpty())  return Result.error("no such paper");
        else{
            paper_authorService.delete(id);
            paperService.dele_paper(id);

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
    //上传文件
    @PostMapping("/upload")
    public  Result<String> upload(MultipartFile file, String papername)throws IOException {
        //打包成jar包前记得修改URL！！！！！！！！
        String name=file.getOriginalFilename();
        String UID= UUID.randomUUID().toString()+name.substring(name.lastIndexOf("."));
        file.transferTo(new File("C:\\Users\\kjhgb\\Desktop\\java\\files\\"+UID));
        //根据filename找到paperid
        List<Papers> temp=paperService.find_by_title(papername);
        Integer paperid=temp.get(0).getId();
        paper_files pf=new paper_files();
        pf.setPaperId(paperid);
        pf.setFilePath("C:\\Users\\kjhgb\\Desktop\\java\\files\\"+UID);
        pf.setUploadDate(LocalDateTime.now().toString());
        Paper_filesService.add(pf);

        return Result.success();

    }

    //返回文件本地地址
    @GetMapping("/download")
    public  Result<String> dowload(String name){
        //papername->id
        Integer id=paperService.find_by_title(name).get(0).getId();
        String filepath="";
        if(!Paper_filesService.search(id).isEmpty()) {
            filepath = Paper_filesService.search(id).get(0).getFilePath();
            return Result.success(filepath);
        }
        return Result.error("This file is not upload yet!");
    }

    @PostMapping("/addrefe")
    public  Result<String> addrefe(String name1,String name2){
        List<Papers> p=paperService.find_by_title(name1);
        if(p.isEmpty()) return Result.error("no such files!");
        List<Papers> p1=paperService.find_by_title(name2);
        if(p1.isEmpty()){
            //在paper插入参考文献
            Papers paper=new Papers();
            paper.setTitle(name2);
            paper.setCategoryId(1);
            paper.setJournalId(1);
            paperService.add_paper(paper);
        }
        Integer id1=p.get(0).getId();
        Integer id2=paperService.find_by_title(name2).get(0).getId();
        paper_refe pp=new paper_refe();
        pp.setPaperId(id1);
        pp.setReferenceId(id2);
        Paper_refeService.add(pp);

        return Result.success();

    }

    @PostMapping("/delerefe")
    public  Result<String> delerefe(String name1,String name2){
        paper_refe pp=new paper_refe();
        List<Papers> p=paperService.find_by_title(name1);
        if(p.isEmpty()) return Result.error("no such files!");
        pp.setPaperId(p.get(0).getId());

        p=paperService.find_by_title(name1);
        if(p.isEmpty()) return Result.error("no such files!");
        pp.setReferenceId(p.get(0).getId());


        Paper_refeService.delete(pp);

        return Result.success();

    }

    @GetMapping("/searchrefe")
    Result<List<String>> searchrefe(String name){
        if(paperService.find_by_title(name).isEmpty()) return Result.error("no such files");
        Integer id=paperService.find_by_title(name).get(0).getId();
        List<paper_refe> plist=Paper_refeService.searchlist(id);
        List<String> result=new ArrayList<>();
        for(paper_refe a:plist){
            result.add(paperService.find_by_id(a.getReferenceId()).get(0).getTitle());
        }
        return Result.success(result);

    }

}
