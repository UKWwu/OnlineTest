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

    //寻找成绩最好的学生
    @RequestMapping("/findBestStudent")
    public List findBestStudent(@RequestBody ReceiveEntity receiveEntity){
        return this.examinationService.findBestStudent(receiveEntity);
    }

    //寻找最近的一次考试
    @RequestMapping("/findEndTest")
    public com.example.demo.entity.Examination findEndTest(@RequestBody ReceiveEntity receiveEntity){
        return this.examinationService.findEndTest(receiveEntity);
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

    //删除笔试
    @RequestMapping("/deleteExam")
    public void deleteExam(@RequestBody ReceiveEntity receiveEntity){
        this.examinationService.deleteExam(receiveEntity);
    }

    //查询条数
    @RequestMapping("/findExaminationNumber")
    public Integer findExaminationNumber(@RequestBody ReceiveEntity receiveEntity){
        return this.examinationService.findExaminationNumber(receiveEntity);
    }

    //查询条数
    @RequestMapping("/addExamination")
    public com.example.demo.entity.Examination addExamination(@RequestBody com.example.demo.entity.Examination examination){
        return this.examinationService.addExamination(examination);
    }

    //查询符合条件的候选人
    @RequestMapping("/findExamUser")
    public List findExamUser(@RequestBody ReceiveEntity receiveEntity){
        return this.examinationService.findExamUser(receiveEntity);
    }

    //新建考试时查询人才库
    @RequestMapping("/findExamTalent")
    public List findExamTalent(@RequestBody ReceiveEntity receiveEntity){
        return this.examinationService.findExamTalent(receiveEntity);
    }

    //新增考试人员
    @RequestMapping("/addExaminee")
    public void addExaminee(@RequestBody ReceiveEntity receiveEntity){
         this.examinationService.addExaminee(receiveEntity);
    }

    //新增考试问题
    @RequestMapping("/addExamProblem")
    public void addExamProblem(@RequestBody ReceiveEntity receiveEntity){
         this.examinationService.addExamProblem(receiveEntity);
    }
}
