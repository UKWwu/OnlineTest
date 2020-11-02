package com.example.demo.service;

import com.example.demo.entity.Problem;
import com.example.demo.entity.ReceiveEntity;

import java.util.List;

public interface EnterpriseQuestionService {
    public List findByPage(ReceiveEntity receiveEntity);

    public void deleteQuestion(Integer id);

    public void addQuestion(Problem problem);

    public Problem findQuestionById(Integer id);

    public void updateQuestion(Problem problem);

    public int findQuestionNumber(ReceiveEntity unit);

    public List findQuestionByKey(ReceiveEntity body);

    public int findQuestionNumberToA(ReceiveEntity unit);

    public List findAQ(ReceiveEntity receiveEntity);

    public List findEQ(ReceiveEntity receiveEntity);
}
