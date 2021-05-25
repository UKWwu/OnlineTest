package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.entity.Examination;
import com.example.demo.service.IndividualTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public void setUserGrade(@RequestBody TestAnswerList testAnswerList){
        this.individualTestService.setUserGrade(testAnswerList);
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

    //该场笔试所有参考人员
    @RequestMapping("/findTalentList")
    public List findTalentList(@RequestBody ReceiveEntity receiveEntity){
        return this.individualTestService.findTalentList(receiveEntity);
    }

    //考生修改个人信息(在线笔试确认信息阶段使用)
    @RequestMapping("/updateTalent")
    public void updateTalent(@RequestBody Talent talent){
         this.individualTestService.updateTalent(talent);
    }

//    //保存图片（在线笔试系统确认阶段使用）
//    @RequestMapping("/saveImg")
//    public void saveImg(@RequestBody String imgBase){
//        this.individualTestService.saveImg(imgBase);
//    }


    //保存图片（在线笔试系统确认阶段使用）
    @RequestMapping("/saveImg")
    public String saveImg(@RequestParam("file") MultipartFile file){
        return this.individualTestService.saveImg(file);
    }

    //保存图片（在线笔试系统确认阶段使用）
    @RequestMapping("/savePicture")
    public void savePicture(@RequestBody Picture picture){
         this.individualTestService.savePicture(picture);
    }
}
