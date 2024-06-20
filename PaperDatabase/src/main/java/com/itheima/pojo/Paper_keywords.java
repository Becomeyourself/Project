package com.itheima.pojo;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Paper_keywords {
    @NotNull
    private int paper_id;
    private int keyword_id;
}
