package com.example.demo.service.serviceImp;

import com.example.demo.entity.User;
import com.example.demo.dao.IndividualTestDao;
import com.example.demo.entity.Examination;
import com.example.demo.entity.Problem;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.service.IndividualTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class IndividualTestServiceImp implements IndividualTestService {

    @Autowired
    private IndividualTestDao individualTestDao;

    public List<Problem> findQuestionByExam(ReceiveEntity receiveEntity){
        List list = new ArrayList();
        //查询目标考试ID
        Integer examinationId = this.individualTestDao.findExamByUserId(receiveEntity.getTargetID());
        //查询该次考试的题目
        List<Integer> temp = this.individualTestDao.findExamsById(examinationId);
        for (int i = 0; i < temp.size(); i++) {
            //该题目的内容
            Problem problem = this.individualTestDao.findExam(temp.get(i));
            String[] lists = problem.getContent().split("&|&");
            problem.setContentA(lists[0]);
            problem.setContentB(lists[2]);
            problem.setContentC(lists[4]);
            problem.setContentD(lists[6]);
            list.add(problem);
        }
        return list;
    }

    public void setUserGrade(User user){
        this.individualTestDao.setUserGrade(user);
    }

    public Examination findTime(ReceiveEntity receiveEntity){
        //查询目标考试ID
        Integer examinationId = this.individualTestDao.findExamByUserId(receiveEntity.getTargetID());
        Examination examination = this.individualTestDao.findExamTimeByUserId(examinationId);
        return examination;
    }
}
