package com.example.demo.service.serviceImp;

import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.ExaminationDao;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.ResultEntity;
import com.example.demo.service.ExaminationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ExaminationServiceImp implements ExaminationService {

    @Autowired
    private ExaminationDao examinationDao;

    @Autowired
    private EnterpriseDao enterpriseDao;

    public List findFiveByTestId(ReceiveEntity receiveEntity) {
        List list = this.examinationDao.findFiveByTestId(receiveEntity);
        return list;
    }

    public List findRatioById(ReceiveEntity receiveEntity) {
        List<ResultEntity> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ResultEntity resultEntity = new ResultEntity();
            receiveEntity.setMax(50 + i * 10);
            if (i != 0){
                resultEntity.setName("分数在"+(40 + i * 10)+"，"+(50 + i * 10)+"区间");
                receiveEntity.setMin(40 + i * 10);
            }
            else{
                resultEntity.setName("不及格");
                receiveEntity.setMin(0);
            }

            resultEntity.setValue(this.examinationDao.findRatioById(receiveEntity));
            list.add(resultEntity);
        }
        return list;
    }


    public List findExamination(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.page = (receiveEntity.page-1)*10;
        return this.examinationDao.findExamination(receiveEntity);
    }

    //获取当前角色的单位
    public String getUnitByName(String name){
        return this.enterpriseDao.getUnitByName(name);
    }

    public void finishExam(ReceiveEntity receiveEntity){
        this.examinationDao.finishExam(receiveEntity);
    }

    public Integer findExaminationNumber(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        return this.examinationDao.findExaminationNumber(receiveEntity);
    }

}
