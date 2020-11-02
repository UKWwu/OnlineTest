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
        receiveEntity.userUnit = this.getUnitByName(receiveEntity.userName);
        return this.enterpriseQuestionDao.findByPage(receiveEntity);
    }

    public void deleteQuestion(Integer id){
        this.enterpriseQuestionDao.deleteQuestion(id);
    }
    public void addQuestion(Problem problem){
        if(problem.getUnit()!="" || problem.getUnit()!=null){
        }else {
            problem.setUnit(this.getUnitByName(problem.getUnitName()));
        }
        problem.setUnitName(this.getUnitNameById(problem.getUnit()));
        problem.setTime(new Date());
        problem.setSelectKey(this.getALLkey(problem));
        this.enterpriseQuestionDao.addQuestion(problem);
    }

    public void updateQuestion(Problem problem){
        if(problem.getUnit()!="" || problem.getUnit()!=null){
        }else {
            problem.setUnit(this.getUnitByName(problem.getUnitName()));
        }
        problem.setUnitName(this.getUnitNameById(problem.getUnit()));
        problem.setTime(new Date());
        problem.setSelectKey(this.getALLkey(problem));
        this.enterpriseQuestionDao.updateQuestion(problem);
    }

    //获取当前角色的单位
    public String getUnitByName(String name){
        return this.enterpriseDao.getUnitByName(name);
    }

    //获取当前角色的单位名称
    public String getUnitNameById(String id){
        return this.enterpriseDao.getUnitID(id);
    }

    public Problem findQuestionById(Integer id){
        Problem problem = this.enterpriseQuestionDao.findQuestionById(id);
        String[] lists = problem.getContent().split("&|&");
        problem.setContentA(lists[0]);
        problem.setContentB(lists[2]);
        problem.setContentC(lists[4]);
        problem.setContentD(lists[6]);
        return problem;
    }



    public int findQuestionNumber(ReceiveEntity unit){
        String unitName = unit.getUserName();
        String id = this.getUnitByName(unitName);
        return this.enterpriseQuestionDao.findQuestionNumber(id);
    }

    public List findQuestionByKey(ReceiveEntity body){
        //根据remark进行查询
        String[] keys = body.remark.split("\\+");
        body.remark = "%"+body.remark+"%";
        body.page = (body.page-1)*10;
        return this.enterpriseQuestionDao.findQuestionByKey(body);
    }

    public String getALLkey(Problem problem){
        String key = "";
        key += problem.getName();
        key += problem.getUnitName();
        key += problem.getTitle();
        key += problem.getContent();
        key += problem.getProblemType();
        key += problem.getType();
        return key;
    }
    public int findQuestionNumberToA(ReceiveEntity unit){
        unit.remark = "%"+unit.remark+"%";
        if(unit.getType().equals("公共"))
            return this.enterpriseQuestionDao.findAN(unit);
        else{
            return this.enterpriseQuestionDao.findEN(unit);
        }
    }

    public List findAQ(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.page = (receiveEntity.page-1)*10;
        receiveEntity.remark = "%"+receiveEntity.remark+"%";
        return this.enterpriseQuestionDao.findAQ(receiveEntity);
    }

    public List findEQ(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.page = (receiveEntity.page-1)*10;
        receiveEntity.remark = "%"+receiveEntity.remark+"%";
        return this.enterpriseQuestionDao.findEQ(receiveEntity);
    }

}
