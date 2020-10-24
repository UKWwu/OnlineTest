package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@ResponseBody
public class UserHome {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/User/Login",method = RequestMethod.POST)
    @ResponseBody
    public User Login(@RequestBody User user){
        return this.userService.Login(user);
    }
}
