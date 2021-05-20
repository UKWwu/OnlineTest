package com.example.demo.service.serviceImp;

import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.LoginDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Examination;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        User returnUser = this.userDao.Login(user);
        //获取考试场次id
        Integer testId = this.userDao.getTestId(returnUser.getId());
        //查看是否过期
        Examination examination = this.userDao.getExamination(testId);
        Date date = new Date();
        if(!examination.getStatus().equals("正在进行")){
            returnUser.setUserType("4");
        }
        //compareTo()方法的返回值，date小于endTime返回-1，date1大于date2返回1，相等返回0
        if(examination.getEndTime() != null){
            if((1 == date.compareTo(examination.getEndTime())) || (0 == date.compareTo(examination.getEndTime()))){
                returnUser.setUserType("4");
            }
        }else{
            returnUser.setUserType("4");
        }

        if(examination.getBeginTime() != null){
            if(-1 == date.compareTo(examination.getBeginTime())){
                returnUser.setUserType("4");
            }
        }else{
            returnUser.setUserType("4");
        }

        return returnUser;
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
