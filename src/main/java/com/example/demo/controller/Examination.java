package com.example.demo.controller;


import com.example.demo.entity.ReceiveEntity;
import com.example.demo.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Examination")
@CrossOrigin
public class Examination {

    @Autowired
    private ExaminationService examinationService;

    //寻找某次笔试的前五名
    @RequestMapping("/findFiveByTestId")
    public List findFiveByTestId(@RequestBody ReceiveEntity receiveEntity){
        return this.examinationService.findFiveByTestId(receiveEntity);
    }

    //查找某次笔试的成绩比例
    @RequestMapping("/findRatioById")
    public List findRatioById(@RequestBody ReceiveEntity receiveEntity){
        return this.examinationService.findRatioById(receiveEntity);
    }

    //查找所有的笔试情况
    @RequestMapping("/findExamination")
    public List findExamination(@RequestBody ReceiveEntity receiveEntity){
        return this.examinationService.findExamination(receiveEntity);
    }

    //结束笔试
    @RequestMapping("/finishExam")
    public void finishExam(@RequestBody ReceiveEntity receiveEntity){
        this.examinationService.finishExam(receiveEntity);
    }

    //查询条数
    @RequestMapping("/findExaminationNumber")
    public Integer findExaminationNumber(@RequestBody ReceiveEntity receiveEntity){
        return this.examinationService.findExaminationNumber(receiveEntity);
    }
}
