package com.example.demo.controller;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/User")
@CrossOrigin
public class User {
    @Autowired
    private UserService userService;

    @RequestMapping("/finUserList")
    public List finUserList(@RequestBody ReceiveEntity receiveEntity){
        return this.userService.finUserList(receiveEntity);
    }
    @RequestMapping("/addUser")
    public void addUser(@RequestBody com.example.demo.entity.User user){
        this.userService.addUser(user);
    }
    @RequestMapping("/updateUser")
    public void updateUser(@RequestBody com.example.demo.entity.User user){
        this.userService.updateUser(user);
    }


    @RequestMapping("/deleteUser")
    public void deleteUser(@RequestBody ReceiveEntity receiveEntity){
        this.userService.deleteUser(receiveEntity);
    }
    @RequestMapping("/findUserNumber")
    public Integer findUserNumber(@RequestBody ReceiveEntity receiveEntity){
        return this.userService.findUserNumber(receiveEntity);
    }
    @RequestMapping("/findUser")
    public com.example.demo.entity.User findUser(@RequestBody ReceiveEntity receiveEntity){
        return this.userService.findUser(receiveEntity);
    }
}
