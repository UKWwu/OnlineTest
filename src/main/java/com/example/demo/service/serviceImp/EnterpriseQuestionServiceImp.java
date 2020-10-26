package com.example.demo.service.serviceImp;

import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.EnterpriseQuestionDao;
import com.example.demo.entity.Problem;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.service.EnterpriseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EnterpriseQuestionServiceImp implements EnterpriseQuestionService {
    @Autowired
    private EnterpriseQuestionDao enterpriseQuestionDao;

    @Autowired
    private EnterpriseDao enterpriseDao;

    public List findByPage(ReceiveEntity receiveEntity){
        receiveEntity.page = (receiveEntity.page-1)*10;
        return this.enterpriseQuestionDao.findByPage(receiveEntity);
    }

    public void deleteQuestion(Integer id){
        this.enterpriseQuestionDao.deleteQuestion(id);
    }
    public void addQuestion(Problem problem){
        problem.setUnit(this.getUnitByName(problem.getUnitName()));
        problem.setTime(new Date());
        this.enterpriseQuestionDao.addQuestion(problem);
    }

    public String getUnitByName(String name){
        return this.enterpriseDao.getUnitByName(name);
    }

    public Problem findQuestionById(Integer id){
        return this.enterpriseQuestionDao.findQuestionById(id);
    }
}
