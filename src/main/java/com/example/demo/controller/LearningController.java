package com.example.demo.controller;

import com.example.demo.pojo.OutputReponse;
import com.example.demo.pojo.Question;
import com.example.demo.service.OutputService;
import com.example.demo.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

@CrossOrigin
@RestController
public class LearningController {
    @GetMapping("/api/generate")
    public List<Question> generateQuestion(
            @RequestParam("datarange") Integer datarange,
            @RequestParam("num") Integer num
    ) throws InterruptedException {
        //生成数据
        List<Question> questionList=new ArrayList<>();
        for (int i=1;i<=num;i++){
            Question temp=new Question();
            Random rand = new Random();
            int num1=rand.nextInt(datarange);
            int num2=rand.nextInt(datarange);
            temp.setQuestionstr(num1+"+"+num2+" = ");
            temp.setResult(num1+num2);
            questionList.add(temp);
        }
        //写入数据到word
        return questionList;
    }

    @GetMapping("/api/generateandoutput")
    public OutputReponse outputQuestion(
            @RequestParam("datarange") Integer datarange,
            @RequestParam("num") Integer num,
            @RequestParam("operator") String operator
    ) throws Exception {
        //生成数据
        List<Question> questionList=new ArrayList<>();
        String exportString=new String();
        String opa=operator;
        System.out.println(opa);
        for (int i=1;i<=num;i++){
            Question temp=new Question();
            Random rand = new Random();
            int num1=rand.nextInt(datarange);
            int num2=rand.nextInt(datarange-1)+1;
            //生成操作符
            int number = rand.nextInt(opa.length());
            String op=String.valueOf(opa.charAt(number));
            //生成导出字符串
            switch (op){
                case "a":
                    temp.setQuestionstr(num1 + "+" + num2 + " = ");
                    exportString=exportString+num1+"+"+num2+" = "+(char)9;
                    break;
                case "b":
                    temp.setQuestionstr(num1 + "-" + num2 + " = ");
                    exportString=exportString+num1+"-"+num2+" = "+(char)9;
                    break;
                case "c":
                    temp.setQuestionstr(num1 + "x" + num2 + " = ");
                    exportString=exportString+num1+"x"+num2+" = "+(char)9;
                    break;
                case "d":
                    temp.setQuestionstr(num1 + "÷" + num2 + " = ");
                    exportString=exportString+num1+"÷"+num2+" = "+(char)9;
                    break;
            }


            switch (op){
                case "a":
                    temp.setResult(num1+num2);
                    break;
                case "b":
                    temp.setResult(num1-num2);
                    break;
                case "c":
                    temp.setResult(num1*num2);
                    break;
                case "d":
                    temp.setResult(num1/num2);
                    break;
            }
            questionList.add(temp);
        }
        //写入数据到word
        OutputService outputService= new OutputService();
        String tmpFile = "D:/data/export/template.doc";
        String expFile = "D:/data/export/result/result.doc";
        Map<String, String> datas = new HashMap<String, String>();
        datas.put("title", "加法练习题");
        datas.put("content",exportString);
        outputService.build(new File(tmpFile), datas, expFile);
        //声明返回体
        OutputReponse outputReponse=new OutputReponse();
        //生成下载链接
        File file= new File("D:/data/export/result/result.doc");
        String folder = "D:/workspace/word";
        outputService.copyFile(file,folder);
        File wordFolder = new File("D:/workspace/word/result.doc");
        File f = new File("D:/workspace/word", StringUtils.getRandomString(6) + file.getName()
                .substring(file.getName().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        wordFolder.renameTo(f);
        String fileurl = "http://localhost:8443/api/wordfile/" + f.getName();
        outputReponse.setUrl(fileurl);
        outputReponse.setMessage("获取成功");
        //数据写入返回实例
        outputReponse.setQuestiondata(questionList);
        return outputReponse;
    }

}




