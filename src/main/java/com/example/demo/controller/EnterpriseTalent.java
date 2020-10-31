package com.example.demo.controller;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.talent;
import com.example.demo.service.EnterpriseTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/EnterpriseTalent")
@CrossOrigin
public class EnterpriseTalent {

    @Autowired
    private EnterpriseTalentService enterpriseTalentService;

    @RequestMapping("/findTalentList")
    public List findTalentList(@RequestBody ReceiveEntity receiveEntity){
        return this.enterpriseTalentService.findTalentList(receiveEntity);
    }
    @RequestMapping("/addTalent")
    public void addTalent(@RequestBody talent talent){
        this.enterpriseTalentService.addTalent(talent);
    }
    @RequestMapping("/updateTalent")
    public void updateTalent(@RequestBody talent talent){
        this.enterpriseTalentService.updateTalent(talent);
    }
    @RequestMapping("/deleteTalent")
    public void deleteTalent(@RequestBody ReceiveEntity receiveEntity){
        deleteTalent(receiveEntity);
    }
    @RequestMapping("/findTalentNumber")
    public Integer findTalentNumber(@RequestBody ReceiveEntity receiveEntity){
        return findTalentNumber(receiveEntity);
    }
    @RequestMapping("/findTalent")
    public talent findTalent(@RequestBody ReceiveEntity receiveEntity){
        return this.findTalent(receiveEntity);
    }
}
