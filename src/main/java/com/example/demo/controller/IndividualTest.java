package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.entity.Examination;
import com.example.demo.entity.User;
import com.example.demo.service.IndividualTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/IndividualTest")
@CrossOrigin
public class IndividualTest {

    @Autowired
    private IndividualTestService individualTestService;

    @RequestMapping("/findQuestionByExam")
    public List<Problem> findQuestionByExam(@RequestBody ReceiveEntity receiveEntity){
        return this.individualTestService.findQuestionByExam(receiveEntity);
    }

    @RequestMapping("/setUserGrade")
    public void setUserGrade(@RequestBody User user){
        this.individualTestService.setUserGrade(user);
    }


    //确定考试时间
    @RequestMapping("/findTestTime")
    public Examination findTestTime(@RequestBody ReceiveEntity receiveEntity){
        return  this.individualTestService.findTestTime(receiveEntity);
    }

    //查询目标用户(在线笔试查询资料所用)
    @RequestMapping("/findTalent")
    public Talent findTalent(@RequestBody ReceiveEntity receiveEntity){
        return this.individualTestService.findTalent(receiveEntity);
    }

    //考生修改个人信息(在线笔试确认信息阶段使用)
    @RequestMapping("/updateTalent")
    public void updateTalent(@RequestBody Talent talent){
         this.individualTestService.updateTalent(talent);
    }

    //保存图片（在线笔试系统确认阶段使用）
    @RequestMapping("/saveImg")
    public void saveImg(@RequestBody String imgBase){
        this.individualTestService.saveImg(imgBase);
    }
}
