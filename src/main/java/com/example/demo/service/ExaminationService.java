package com.example.demo.service;

import com.example.demo.entity.Examination;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.UserAndExam;

import java.util.List;

public interface ExaminationService {

    public List findBestStudent(ReceiveEntity receiveEntity);

    public List findRatioById(ReceiveEntity receiveEntity);

    public List findExamination(ReceiveEntity receiveEntity);

    public void finishExam(ReceiveEntity receiveEntity);

    public Integer findExaminationNumber(ReceiveEntity receiveEntity);

    public Examination addExamination(Examination examination);

    public List findExamUser(ReceiveEntity receiveEntity);

    public List addExaminee(ReceiveEntity receiveEntity);

    public com.example.demo.entity.Examination findEndTest(ReceiveEntity receiveEntity);

    public void deleteExam(ReceiveEntity receiveEntity);

    public List findExamTalent(ReceiveEntity receiveEntity);

    public void addExamProblem(ReceiveEntity receiveEntity);

    public List findProblemData(ReceiveEntity receiveEntity);

    public List findUserData(ReceiveEntity receiveEntity);

    public void updateUserAndExam(UserAndExam userAndExam);
}
