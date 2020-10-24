package com.example.demo.dao;


import com.example.demo.entity.Problem;
import com.example.demo.entity.ReceiveEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "EnterpriseQuestionDao")
public interface EnterpriseQuestionDao {
    public List<Problem> findByPage(ReceiveEntity receiveEntity);

    public void deleteQuestion(Integer id);
}
