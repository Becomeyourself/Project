package com.itheima.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Category {
    private int id;
    private int parentId ;
    private String name;
}
