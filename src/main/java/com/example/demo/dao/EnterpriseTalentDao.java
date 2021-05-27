package com.example.demo.dao;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;
import com.example.demo.entity.TestAnswer;
import com.example.demo.entity.UserAndExam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "EnterpriseTalentDao")
public interface EnterpriseTalentDao {
    public List<Talent> findTalentList(ReceiveEntity receiveEntity);

    public void addTalent(Talent talent);

    public void updateTalent(Talent talent);

    public void deleteTalent(ReceiveEntity receiveEntity);

    public Integer findTalentNumber(ReceiveEntity receiveEntity);

    public Talent findTalent(ReceiveEntity receiveEntity);

    public List<UserAndExam> findExamByTalentId(ReceiveEntity receiveEntity);

    public List<TestAnswer> findTalentSimpleQuestion(ReceiveEntity receiveEntity);

    public void updateTestAnswer(TestAnswer testAnswer);

    public List<Integer> getTalentGrade(Integer personId);

    public void updateUserGrade(Talent talent);
}
