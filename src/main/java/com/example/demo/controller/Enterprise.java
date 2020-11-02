package com.example.demo.controller;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Enterprise")
@CrossOrigin
public class Enterprise {
    @Autowired
    private EnterpriseService enterpriseService;

    @RequestMapping(value = "/findEnterpriseRecommend")
    @ResponseBody
    public List findEnterpriseRecommend(@RequestBody ReceiveEntity receiveEntity){
        return this.enterpriseService.findEnterpriseRecommend(receiveEntity);
    }

    @RequestMapping(value = "/changeRecommend")
    @ResponseBody
    public void changeRecommend(@RequestBody List<com.example.demo.entity.Enterprise> enterprise){
        for (int i = 0; i < enterprise.size(); i++) {
            this.enterpriseService.changeRecommend(enterprise.get(i));
        }
    }
}
