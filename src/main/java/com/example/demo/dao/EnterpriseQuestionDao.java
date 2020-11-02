package com.example.demo.dao;


import com.example.demo.entity.Problem;
import com.example.demo.entity.ReceiveEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component(value = "EnterpriseQuestionDao")
public interface EnterpriseQuestionDao {
    public List<Problem> findByPage(ReceiveEntity receiveEntity);

    public void deleteQuestion(Integer id);

    public void addQuestion(Problem problem);

    public Problem findQuestionById(Integer id);

    public void updateQuestion(Problem problem);

    public int findQuestionNumber(String id);

    public List<Problem> findQuestionByKey(ReceiveEntity keys);

    public int findAN(ReceiveEntity receiveEntity);

    public int findEN(ReceiveEntity receiveEntity);

    public List<Problem> findAQ(ReceiveEntity receiveEntity);

    public List<Problem> findEQ(ReceiveEntity receiveEntity);
}
