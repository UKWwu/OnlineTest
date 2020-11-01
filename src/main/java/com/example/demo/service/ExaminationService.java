package com.example.demo.service;

import com.example.demo.entity.ReceiveEntity;

import java.util.List;

public interface ExaminationService {

    public List findFiveByTestId(ReceiveEntity receiveEntity);

    public List findRatioById(ReceiveEntity receiveEntity);

    public List findExamination(ReceiveEntity receiveEntity);

    public void finishExam(ReceiveEntity receiveEntity);

    public Integer findExaminationNumber(ReceiveEntity receiveEntity);
}
