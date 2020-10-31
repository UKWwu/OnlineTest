package com.example.demo.service.serviceImp;

import com.example.demo.dao.EnterpriseTalentDao;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.talent;
import com.example.demo.service.EnterpriseTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EnterpriseTalentServiceImp implements EnterpriseTalentService {

    @Autowired
    private EnterpriseTalentDao enterpriseTalentDao;

    public List findTalentList(ReceiveEntity receiveEntity){
        return null;
    }

    public void addTalent(talent talent){
    }

    public void updateTalent(talent talent){
    }

    public void deleteTalent(ReceiveEntity receiveEntity){
        this.enterpriseTalentDao.deleteTalent(receiveEntity);
    }

    public Integer findTalentNumber(ReceiveEntity receiveEntity){
        return this.enterpriseTalentDao.findTalentNumber(receiveEntity);
    }

    public talent findTalent(ReceiveEntity receiveEntity){
        return this.enterpriseTalentDao.findTalent(receiveEntity);
    }
}
