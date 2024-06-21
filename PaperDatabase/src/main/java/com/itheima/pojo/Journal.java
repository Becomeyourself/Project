package com.itheima.pojo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Journal {
    @NotNull
    private int id;
    private String name;
}
