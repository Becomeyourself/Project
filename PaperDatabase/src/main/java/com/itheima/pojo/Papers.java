package com.itheima.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class Papers {
    private int id;
    private String title;
    private String Abstract;
    private int journalId;
    private int categoryId;
    private String filePath;
    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date publicationDate;
}
