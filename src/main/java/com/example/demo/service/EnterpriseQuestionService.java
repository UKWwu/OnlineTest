package com.example.demo.service;

import com.example.demo.entity.Problem;
import com.example.demo.entity.ReceiveEntity;

import java.util.List;

public interface EnterpriseQuestionService {
    public List findByPage(ReceiveEntity receiveEntity);

    public void deleteQuestion(Integer id);

    public void addQuestion(Problem problem);

    public Problem findQuestionById(Integer id);
}
