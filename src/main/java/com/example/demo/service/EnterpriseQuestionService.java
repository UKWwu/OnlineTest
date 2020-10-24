package com.example.demo.service;

import com.example.demo.entity.ReceiveEntity;

import java.util.List;

public interface EnterpriseQuestionService {
    public List findByPage(ReceiveEntity receiveEntity);

    public void deleteQuestion(Integer id);
}
