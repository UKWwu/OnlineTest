package com.example.demo.controller;


import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Report")
@CrossOrigin
public class Report {
    @Autowired
    private ReportService reportService;

    @RequestMapping("/findTalentListByTestId")
    @ResponseBody
    public List<Talent> findTalentListByTestId(@RequestBody ReceiveEntity receiveEntity){
        return this.reportService.findTalentListByTestId(receiveEntity);
    }
}
