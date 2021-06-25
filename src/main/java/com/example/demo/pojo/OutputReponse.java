package com.example.demo.pojo;

import lombok.Data;

import java.util.List;

@Data
public class OutputReponse {

    String message;
    List<Question> questiondata;
    String url;
}
