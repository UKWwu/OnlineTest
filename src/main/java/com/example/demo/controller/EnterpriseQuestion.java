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
}
