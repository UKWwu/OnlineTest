package com.example.demo.dao;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "UserDao")
public interface UserDao {
    public User Login(User user);

    public List<User> finUserList(ReceiveEntity receiveEntity);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUser(ReceiveEntity receiveEntity);

    public Integer findUserNumber(ReceiveEntity receiveEntity);

    public User findUser(ReceiveEntity receiveEntity);
}
