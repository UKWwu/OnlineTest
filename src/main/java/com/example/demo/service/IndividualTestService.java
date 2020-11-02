package com.example.demo.service;

import com.example.demo.entity.Examination;
import com.example.demo.entity.User;
import com.example.demo.entity.Problem;
import com.example.demo.entity.ReceiveEntity;

import java.util.List;

public interface IndividualTestService {
    public List<Problem> findQuestionByExam(ReceiveEntity receiveEntity);

    public void setUserGrade(User user);

    public Examination findTime(ReceiveEntity receiveEntity);
}
