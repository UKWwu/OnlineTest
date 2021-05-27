package com.example.demo.dao;


import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ExaminationDao")
public interface ExaminationDao {

    public List<Talent> findFiveByTestId(ReceiveEntity receiveEntity);

    public Integer findRatioById(ReceiveEntity receiveEntity);

    public List<Examination> findExamination(ReceiveEntity receiveEntity);

    public List<Examination> findExaminationWithKey(ReceiveEntity receiveEntity);

    public void finishExam(ReceiveEntity receiveEntity);

    public Integer findExaminationNumber(ReceiveEntity receiveEntity);

    public Integer findExaminationNumberWithKey(ReceiveEntity receiveEntity);

    public void addExamination(Examination examination);

    public List<User> findExamUser(ReceiveEntity receiveEntity);

    public void addExaminee(UserAndExam userAndExam);

    public List<Examination> findEndTest(String unit);

    public void deleteExam(ReceiveEntity receiveEntity);

    public List<Talent> findAllTalent(ReceiveEntity receiveEntity);

    public void addExamProblem(TestProblem testProblem);

    public void updateExaminee(UserAndExam userAndExam);

    public List<TestProblem> findProblemData(ReceiveEntity receiveEntity);

    public List<UserAndExam> findUserData(ReceiveEntity receiveEntity);

    public User findUserById(Integer userAccountId);

    public void updateTalentTest(Talent talent);

    public Examination findExaminationById(Integer targetID);
}
