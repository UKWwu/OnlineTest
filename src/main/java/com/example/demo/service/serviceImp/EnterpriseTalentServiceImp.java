package com.example.demo.service.serviceImp;

import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.EnterpriseTalentDao;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;
import com.example.demo.entity.TestAnswer;
import com.example.demo.entity.UserAndExam;
import com.example.demo.service.EnterpriseTalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EnterpriseTalentServiceImp implements EnterpriseTalentService {

    @Autowired
    private EnterpriseTalentDao enterpriseTalentDao;

    @Autowired
    private EnterpriseDao enterpriseDao;

    @Autowired
    private JavaMailSender javaMailSender;

    public List findTalentList(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.page = (receiveEntity.page-1)*10;
        receiveEntity.remark = "%"+receiveEntity.remark+"%";
        List list = this.enterpriseTalentDao.findTalentList(receiveEntity);
        return list;
    }

    public List findTalentListByTestId(ReceiveEntity receiveEntity){
        List<UserAndExam> idList = this.enterpriseTalentDao.findExamByTalentId(receiveEntity);
        List<Talent> talentList = new ArrayList();
        for (int i = 0; i < idList.size(); i++) {
            ReceiveEntity receiveEntity1 = new ReceiveEntity();
            receiveEntity1.setTargetID((Integer) idList.get(i).getUserId());
            Talent talent = this.enterpriseTalentDao.findTalent(receiveEntity1);
            talentList.add(talent);
        }
        for (int i = 0; i < talentList.size(); i++) {
            for (int j = 0; j < talentList.size(); j++) {
                if(Integer.valueOf(talentList.get(i).getGrade()) > Integer.valueOf(talentList.get(j).getGrade())){
                    String temp = talentList.get(i).getGrade();
                    talentList.get(i).setGrade(talentList.get(j).getGrade());
                    talentList.get(j).setGrade(temp);
                }
            }
        }
        return talentList;
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

    //根据考生ID找截图路径
    public List findPictureSrc(ReceiveEntity receiveEntity){
        return this.enterpriseDao.findPictureSrc(receiveEntity);
    }

    public List findTalentSimpleQuestion(ReceiveEntity receiveEntity){
        return this.enterpriseTalentDao.findTalentSimpleQuestion(receiveEntity);
    }

    public void updateTestAnswer(TestAnswer testAnswer){
        this.enterpriseTalentDao.updateTestAnswer(testAnswer);
        this.updateTalentGrade(testAnswer.getPersonId());
    }

    private void updateTalentGrade(String personId) {
        List<Integer> list = this.enterpriseTalentDao.getTalentGrade(Integer.valueOf(personId));
        Integer grade = 0;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) != null){
                grade += list.get(i);
            }
        }
        Talent talent = new Talent();
        talent.setId(Integer.valueOf(personId));
        talent.setGrade(String.valueOf(grade));
        this.enterpriseTalentDao.updateUserGrade(talent);
    }

}
