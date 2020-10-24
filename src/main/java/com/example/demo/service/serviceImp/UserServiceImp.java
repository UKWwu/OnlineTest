package com.example.demo.service.serviceImp;

import com.example.demo.dao.LoginDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    public User Login(User user){
        User user1 = this.userDao.Login(user);
        return user1;
    }
}
