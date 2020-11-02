package com.example.demo.controller;


import com.example.demo.entity.Examination;
import com.example.demo.entity.Problem;
import com.example.demo.entity.ReceiveEntity;
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

    @RequestMapping("/findTime")
    public Examination findTime(@RequestBody ReceiveEntity receiveEntity){
        return  this.individualTestService.findTime(receiveEntity);
    }
}
