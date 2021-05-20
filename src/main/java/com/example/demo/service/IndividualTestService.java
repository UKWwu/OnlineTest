package com.example.demo.service;

import com.example.demo.entity.*;

import java.util.List;

public interface IndividualTestService {
    public List<Problem> findQuestionByExam(ReceiveEntity receiveEntity);

    public void setUserGrade(TestAnswerList testAnswerList);

    public Examination findTestTime(ReceiveEntity receiveEntity);

    public Talent findTalent(ReceiveEntity receiveEntity);

    public void updateTalent(Talent talent);

    public void saveImg(String imgBase);
}
