package com.example.demo.service.serviceImp;

import com.example.demo.dao.LoginDao;
import com.example.demo.entity.User;
import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImp implements LoginService {

    @Autowired
    private LoginDao loginDao;

    public User Login(){
        return this.loginDao.Login();
    }
}
