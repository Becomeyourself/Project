package com.itheima.controller;


import com.itheima.pojo.*;
import com.itheima.service.Paper_filesService;
import com.itheima.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import jakarta.servlet.http.HttpServletResponse;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Paper")
public class PaperController {
    @Autowired
    PaperServiceImpl paperService;

    @Autowired
    Paper_keyServiceImpl Paper_keyService;

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
    @Autowired
    private com.itheima.mapper.journalMapper journalMapper;

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
        if(!Objects.equals(paperDo.getAbstractText(), "")) {
            p.setAbstract(paperDo.getAbstractText());
        }
        p.setCategoryId(c.getId());
        List<Journal> j=JournalService.findByName(paperDo.getJournal());

        p.setJournalId(j.get(0).getId());
        if(!Objects.equals(paperDo.getFile_path(), "")) {
            p.setFilePath(paperDo.getFile_path());
        }
        p.setPublicationDate(paperDo.getPublicationDate());
        List<Papers> papers=paperService.find_by_title(p.getTitle());
        author author1 = new author(),author2 = new author();
        System.out.println(paperDo.getAuthor1());
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
        if(p.isEmpty()) {
            return Result.error("no such paper");
        } else{
            System.out.println(1);
            paper_authorService.delete(id);
            System.out.println(11);
            paperService.dele_paper(id);
            System.out.println(112);
            Paper_keyService.deletep(id);

            System.out.println(113);
            return Result.success();

        }

    }
    //更新
    @PutMapping
    public Result<String> update_paper(@RequestBody PapersDo papersdo){
        List<Journal> j=JournalService.findByName(papersdo.getJournal());
        Category i = CategoryService.findByname(papersdo.getCategory());
        //这里主要是因为传进来的是papersdo，先只支持修改papers本身
        Papers paper = new Papers();
        paper.setId(papersdo.getId());
        paper.setTitle(papersdo.getTitle());
        paper.setAbstract(papersdo.getAbstractText());
        paper.setCategoryId(i.getId());
        paper.setJournalId(j.get(0).getId());
        paper.setFilePath(papersdo.getFile_path());
        paper.setPublicationDate(papersdo.getPublicationDate());

        paperService.update(paper);
        return Result.success();
    }

    //关系网络要调用的接口，大概是根据名字找到相联系的name列表

    @GetMapping("/search")
    public Result<Map<String, List<String>>> social_search(Integer id) {
        // 查到了作者所有的论文关系
        List<Paper_author> papers = paper_authorService.findByaid(id);
        // 创建了一个hashmap表格，第一个String是论文名，第二个List是作者名，这样可以方便画图
        Map<String, List<String>> paperAuthorMap = new HashMap<>();

        for (Paper_author paperAuthor : papers) {
            // 每次处理新的论文前，创建一个新的authorNames列表
            List<String> authorNames = new ArrayList<>();

            // 查到了作者们
            List<Paper_author> authors = paper_authorService.findBypId(paperAuthor.getPaperId());
            // 获取论文标题
            List<Papers> paper = paperService.find_by_id(paperAuthor.getPaperId());
            String paperTitle = paper.get(0).getTitle();
            // 把论文、作者们插入到map中
            for (Paper_author authorRel : authors) {
                String authorEntity = authorService.findById(authorRel.getAuthorId());
                authorNames.add(authorEntity);
            }

            paperAuthorMap.put(paperTitle, authorNames);
        }
        return Result.success(paperAuthorMap);
    }
    //上传文件
    @PostMapping("/upload")
    public  Result<String> upload(MultipartFile file, String papername)throws IOException {
        //打包成jar包前记得修改URL！！！！！！！！
        String name=file.getOriginalFilename();
        String UID= UUID.randomUUID().toString()+name.substring(name.lastIndexOf("."));
        file.transferTo(new File("C:\\Users\\42208\\Desktop\\file\\"+UID));
        //根据filename找到paperid
        List<Papers> temp=paperService.find_by_title(papername);
        Integer paperid=temp.get(0).getId();
        paper_files pf=new paper_files();
        pf.setPaperId(paperid);
        pf.setFilePath("C:\\Users\\42208\\Desktop\\file\\"+UID);
        pf.setUploadDate(LocalDateTime.now().toString());
        Paper_filesService.add(pf);

        return Result.success();

    }

    //返回文件本地地址
    @GetMapping("/download")
    public Result<Map<String, String>> download(@RequestParam String name) {
        try {
            // 获取论文ID
            Integer id = paperService.find_by_title(name).get(0).getId();

            // 查找文件路径
            String filepath = Paper_filesService.search(id).isEmpty() ? "" : Paper_filesService.search(id).get(0).getFilePath();
            if (!filepath.isEmpty()) {
                // 获取文件格式
                String fileType = Files.probeContentType(Paths.get(filepath));
                // 读取文件数据并进行Base64编码
                byte[] fileData = Files.readAllBytes(Paths.get(filepath));
                String encodedFileData = Base64.getEncoder().encodeToString(fileData);

                Map<String, String> response = new HashMap<>();
                response.put("fileType", fileType);
                response.put("fileData", encodedFileData);
                return Result.success(response);
            } else {
                return Result.error("File not uploaded yet!");
            }
        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/addrefe")
    public  Result<String> addrefe(String name1,String name2){
        List<Papers> p=paperService.find_by_title(name1);
        if(p.isEmpty()) {
            return Result.error("no such files!");
        }
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

        return Result.success("");

    }

    @PostMapping("/delerefe")
    public  Result<String> delerefe(String name1,String name2){
        paper_refe pp=new paper_refe();
        List<Papers> p=paperService.find_by_title(name1);
        if(p.isEmpty()) {
            return Result.error("no such files!");
        }
        pp.setPaperId(p.get(0).getId());

        p=paperService.find_by_title(name2);
        if(p.isEmpty()) {
            return Result.error("no such files!");
        }
        pp.setReferenceId(p.get(0).getId());


        Paper_refeService.delete(pp);

        return Result.success();

    }

    @GetMapping("/searchrefe")
    Result<List<String>> searchrefe(String name){
        if(paperService.find_by_title(name).isEmpty()) {
            return Result.error("no such files");
        }
        Integer id=paperService.find_by_title(name).get(0).getId();
        List<paper_refe> plist=Paper_refeService.searchlist(id);
        List<String> result=new ArrayList<>();
        for(paper_refe a:plist){
            result.add(paperService.find_by_id(a.getReferenceId()).get(0).getTitle());
        }
        return Result.success(result);

    }
    //查找谁参考了name
    @GetMapping("/searchpa")
    Result<List<String>> searchpa(String name){
        if(paperService.find_by_title(name).isEmpty()) {
            return Result.error("no such files");
        }
        Integer id=paperService.find_by_title(name).get(0).getId();
        List<paper_refe> plist=Paper_refeService.searchrlist(id);
        List<String> result=new ArrayList<>();
        for(paper_refe a:plist){
            result.add(paperService.find_by_id(a.getPaperId()).get(0).getTitle());
        }
        return Result.success(result);

    }

    @GetMapping("/cal")
    Result<Integer>  cal(String name) throws IOException {
        Integer id=paperService.find_by_title(name).get(0).getId();
        String filepath="";
        Integer score=0;
        if(!Paper_filesService.search(id).isEmpty()) {
            filepath = Paper_filesService.search(id).get(0).getFilePath();
            long fileSize =0;
            System.out.println(filepath);
            //fileSize = Files.size(Paths.get(filepath)); // 计算文件大小
            try (BufferedReader reader = new BufferedReader(new FileReader(filepath, StandardCharsets.UTF_8))) {
                int c;
                while ((c = reader.read()) != -1) {
                    if (!Character.isWhitespace(c)) { // 如果你不想计算空白字符，可以添加这个条件
                        fileSize++;
                    }
                }
                // 在这里，charCount包含了文件中非空白字符的数量（如果你添加了上面的条件）
                // 或者包含了所有字符的数量（如果没有添加条件）
                // 你可以将charCount封装到你的Result对象中并返回
            } catch (IOException e) {
                // 处理文件读取错误
                e.printStackTrace();
                return Result.error("Error reading the file: " + e.getMessage());
            }
            System.out.println(fileSize);
            if (fileSize< 10) {
                score += 1;
            } else if (fileSize <= 20) {
                score += 2;
            } else {
                score += 3;
            }
            System.out.println(score);
            //计算参考文献个数
            List<paper_refe> p = Paper_refeService.searchrlist(id);
            score+=p.size();
            System.out.println(score);
            return Result.success(score);
        }
        else {
            return Result.error("no such files!");
        }
    }


}
