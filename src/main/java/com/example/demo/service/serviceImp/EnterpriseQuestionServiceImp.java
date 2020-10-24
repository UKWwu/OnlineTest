package com.example.demo.service.serviceImp;

import com.example.demo.dao.EnterpriseQuestionDao;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.service.EnterpriseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EnterpriseQuestionServiceImp implements EnterpriseQuestionService {
    @Autowired
    private EnterpriseQuestionDao enterpriseQuestionDao;

    public List findByPage(ReceiveEntity receiveEntity){
        receiveEntity.page = (receiveEntity.page-1)*10;
        return this.enterpriseQuestionDao.findByPage(receiveEntity);
    }

    public void deleteQuestion(Integer id){
        this.enterpriseQuestionDao.deleteQuestion(id);
    }
}
