package com.example.demo.service.serviceImp;

import com.example.demo.entity.*;
import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.ExaminationDao;
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

    public List findBestStudent(ReceiveEntity receiveEntity) {
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
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
        //查询关键字为status
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.page = (receiveEntity.page-1)*10;
        if(receiveEntity.getKey()!=null){
            return this.examinationDao.findExaminationWithKey(receiveEntity);
        }else{
            return this.examinationDao.findExamination(receiveEntity);
        }

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
        if(receiveEntity.getKey()!=null){
            return this.examinationDao.findExaminationNumberWithKey(receiveEntity);
        }else{
            return this.examinationDao.findExaminationNumber(receiveEntity);
        }
    }

    public Examination addExamination(Examination examination){
        examination.setUnit(this.getUnitByName(examination.getUserName()));
        this.examinationDao.addExamination(examination);
        return examination;
    }
    public List findExamUser(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        return this.examinationDao.findExamUser(receiveEntity);
    }

    public void addExaminee(ReceiveEntity receiveEntity){
        List temp = (List) receiveEntity.getObject();
        for (int i = 0; i < temp.size(); i++) {
            UserAndExam userAndExam = new UserAndExam();
            userAndExam.setUserId((Integer) temp.get(i));
            userAndExam.setExaminationId(receiveEntity.getTargetID());
            this.examinationDao.addExaminee(userAndExam);
        }
    }

    //查找最近的一次考试
    public com.example.demo.entity.Examination findEndTest(ReceiveEntity receiveEntity){
        String unit =  this.getUnitByName(receiveEntity.getUserName());
        List lists = this.examinationDao.findEndTest(unit);
        return (Examination) lists.get(0);
    }

    //删除笔试
    public void deleteExam(ReceiveEntity receiveEntity){
        this.examinationDao.deleteExam(receiveEntity);
    }

    //新建时查询人才库
    public List findExamTalent(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        return this.examinationDao.findAllTalent(receiveEntity);
    }

    //新增考试人员
    public void addExamProblem(ReceiveEntity receiveEntity){
        List temp = (List) receiveEntity.getObject();
        for (int i = 0; i < temp.size(); i++) {
            TestProblem testProblem = new TestProblem();
            testProblem.setProblemId(String.valueOf(temp.get(i)));
            testProblem.setTestId(String.valueOf(receiveEntity.targetID));
            this.examinationDao.addExamProblem(testProblem);
        }
    }
}
