package com.example.demo.service.serviceImp;

import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.EnterpriseTalentDao;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;
import com.example.demo.service.EnterpriseTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EnterpriseTalentServiceImp implements EnterpriseTalentService {

    @Autowired
    private EnterpriseTalentDao enterpriseTalentDao;

    @Autowired
    private EnterpriseDao enterpriseDao;

    public List findTalentList(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.page = (receiveEntity.page-1)*10;
        receiveEntity.remark = "%"+receiveEntity.remark+"%";
        List list = this.enterpriseTalentDao.findTalentList(receiveEntity);
        return list;
    }

    public void addTalent(Talent talent){
        talent.setSelectKey(this.getKey(talent));
        talent.setUnit(this.getUnitByName(talent.getUserName()));
        talent.setTime(new Date());
        this.enterpriseTalentDao.addTalent(talent);
    }

    public void updateTalent(Talent talent){
        talent.setSelectKey(this.getKey(talent));
        talent.setUnit(this.getUnitByName(talent.getUserName()));
        talent.setTime(new Date());
        this.enterpriseTalentDao.updateTalent(talent);
    }

    public void deleteTalent(ReceiveEntity receiveEntity){
        this.enterpriseTalentDao.deleteTalent(receiveEntity);
    }

    public Integer findTalentNumber(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.remark = "%"+receiveEntity.remark+"%";
        return this.enterpriseTalentDao.findTalentNumber(receiveEntity);
    }

    public Talent findTalent(ReceiveEntity receiveEntity){
        return this.enterpriseTalentDao.findTalent(receiveEntity);
    }

    public String getKey(Talent talent){
        String selectKey = "";
        selectKey += talent.getName();
        selectKey += talent.getSchool();
        selectKey += talent.getEducation();
        selectKey += talent.getGrade();
        selectKey += talent.getSex();
        selectKey += talent.getRemark();
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
