package com.example.demo.service.serviceImp;

import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.LoginDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private EnterpriseDao enterpriseDao;

    public User Login(User user){
        User user1 = this.userDao.Login(user);
        return user1;
    }
    public List finUserList(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.page = (receiveEntity.page-1)*10;
        receiveEntity.remark = "%"+receiveEntity.remark+"%";
        return this.userDao.finUserList(receiveEntity);
    }

    public void addUser(User user){
        user.setSelectKey(this.getKey(user));
        user.setTime(new Date());
        this.userDao.addUser(user);
    }

    public void updateUser(User user){
        user.setSelectKey(this.getKey(user));
        user.setTime(new Date());
        this.userDao.updateUser(user);
    }

    public void deleteUser(ReceiveEntity receiveEntity){
        this.userDao.deleteUser(receiveEntity);
    }

    //管理员
    public Integer findUserNumber(ReceiveEntity receiveEntity){
        receiveEntity.remark = "%"+receiveEntity.remark+"%";
        return this.userDao.findUserNumber(receiveEntity);
    }

    public User findUser(ReceiveEntity receiveEntity){
        return this.userDao.findUser(receiveEntity);
    }

    public String getKey(User user){
        String selectKey = "";
        selectKey += user.getUserName();
        selectKey += user.getUserAccount();
        selectKey += user.getUserType();
        selectKey += user.getUserTypeName();
        selectKey += user.getRemark();
        return selectKey;
    }

    //获取当前角色的单位
    public String getUnitByName(String name){
        return this.enterpriseDao.getUnitByName(name);
    }

    //获取当前角色的单位名称
    public String getUnitNameById(String id){
        return this.enterpriseDao.getUnitID(id);
    }

}
