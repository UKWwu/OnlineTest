package com.example.demo.dao;


import com.example.demo.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component(value = "IndividualTestDao")
public interface IndividualTestDao {

    public Integer findExamByUserId(Integer targetID);

    public Examination findExamTimeByUserId(Integer targetID);

    public List<Integer> findExamsById(Integer id);

    public Problem findExam(Integer id);

    public void setUserGrade(User user);

    public Talent findTalent(Integer targetID);

    public void updateTalent(Talent talent);

    public void saveUserAnswer(TestAnswer testAnswer);

    public void setUserTested(String personId);
}
