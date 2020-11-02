package com.example.demo.service;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.User;

import java.util.List;

public interface UserService {
    public User Login(User user);

    public List finUserList(ReceiveEntity receiveEntity);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(ReceiveEntity receiveEntity);

    public Integer findUserNumber(ReceiveEntity receiveEntity);

    public User findUser(ReceiveEntity receiveEntity);
}
