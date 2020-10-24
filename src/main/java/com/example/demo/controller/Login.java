package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
public class Login {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/index")
    @ResponseBody
    public User Login(){
        return this.loginService.Login();
    }
}
