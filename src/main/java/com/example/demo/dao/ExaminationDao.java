package com.example.demo.dao;


import com.example.demo.entity.Examination;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ExaminationDao")
public interface ExaminationDao {

    public List<Talent> findFiveByTestId(ReceiveEntity receiveEntity);

    public Integer findRatioById(ReceiveEntity receiveEntity);

    public List<Examination> findExamination(ReceiveEntity receiveEntity);

    public void finishExam(ReceiveEntity receiveEntity);

    public Integer findExaminationNumber(ReceiveEntity receiveEntity);
}
