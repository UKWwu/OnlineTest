package com.example.demo.controller;


import com.example.demo.entity.Problem;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.service.EnterpriseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@ResponseBody
public class EnterpriseQuestion {
    @Autowired
    private EnterpriseQuestionService enterpriseQuestionService;

    //根据页数来查询
    @RequestMapping(value = "/EnterpriseQuestion/findByPage",method = RequestMethod.POST)
    @ResponseBody
    public List findByPage(@RequestBody ReceiveEntity receiveEntity){
        return this.enterpriseQuestionService.findByPage(receiveEntity);
    }

    //根据id删除
    @RequestMapping(value = "/EnterpriseQuestion/deleteQuestion",method = RequestMethod.POST)
    @ResponseBody
    public void deleteQuestion(@RequestBody Problem problem){
         this.enterpriseQuestionService.deleteQuestion(problem.getId());
    }

    //新增问题
    @RequestMapping(value = "/EnterpriseQuestion/addQuestion",method = RequestMethod.POST)
    @ResponseBody
    public void addQuestion(@RequestBody Problem problem){
        this.enterpriseQuestionService.addQuestion(problem);
    }

    //根据Id来查询
    @RequestMapping(value = "/EnterpriseQuestion/findQuestionById",method = RequestMethod.POST)
    @ResponseBody
    public Problem findQuestionById(@RequestBody Problem Problem){
        return this.enterpriseQuestionService.findQuestionById(Problem.getId());
    }

    //更新问题
    @RequestMapping(value = "/EnterpriseQuestion/updateQuestion",method = RequestMethod.POST)
    @ResponseBody
    public void updateQuestion(@RequestBody Problem Problem){
        this.enterpriseQuestionService.updateQuestion(Problem);
    }

    //查询条数
    @RequestMapping(value = "/EnterpriseQuestion/findQuestionNumber",method = RequestMethod.POST)
    @ResponseBody
    public int findQuestionNumber(@RequestBody ReceiveEntity unit){
        return this.enterpriseQuestionService.findQuestionNumber(unit);
    }

    //查询条数--企业专用
    @RequestMapping(value = "/EnterpriseQuestion/findQuestionNumberToA",method = RequestMethod.POST)
    @ResponseBody
    public int findQuestionNumberToA(@RequestBody ReceiveEntity unit){
        return this.enterpriseQuestionService.findQuestionNumberToA(unit);
    }

    //根据关键字查询
    @RequestMapping(value = "/EnterpriseQuestion/findQuestionByKey",method = RequestMethod.POST)
    @ResponseBody
    public List findQuestionByKey(@RequestBody ReceiveEntity body){
        return this.enterpriseQuestionService.findQuestionByKey(body);
    }

    //根据页数来查询
    @RequestMapping(value = "/EnterpriseQuestion/findAQ",method = RequestMethod.POST)
    @ResponseBody
    public List findAQ(@RequestBody ReceiveEntity receiveEntity){
        return this.enterpriseQuestionService.findAQ(receiveEntity);
    }

    //根据页数来查询
    @RequestMapping(value = "/EnterpriseQuestion/findEQ",method = RequestMethod.POST)
    @ResponseBody
    public List findEQ(@RequestBody ReceiveEntity receiveEntity){
        return this.enterpriseQuestionService.findEQ(receiveEntity);
    }
}
