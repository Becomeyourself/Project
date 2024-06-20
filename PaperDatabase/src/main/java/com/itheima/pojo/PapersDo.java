package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PapersDo {
        private int id;
        private String author1;
        private String author2;
        private String title;
        private String abstractText;
        private String journal;
        private String category;
        private String file_path;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private Date publicationDate;

}

