package com.example.demo.service.serviceImp;

import com.example.demo.entity.Enterprise;
import com.example.demo.dao.EnterpriseDao;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EnterpriseServiceImp implements EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;


    public List findEnterpriseRecommend(ReceiveEntity receiveEntity){
        return this.enterpriseDao.findAllEnterprise();
    }

    public void changeRecommend(Enterprise enterprise){
        this.enterpriseDao.changeRecommend(enterprise);
    }

}
