package com.example.demo.service;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;
import com.example.demo.entity.TestAnswer;

import java.util.List;

public interface EnterpriseTalentService {
    public List findTalentList(ReceiveEntity receiveEntity);

    public List findTalentListByTestId(ReceiveEntity receiveEntity);

    public void addTalent(Talent talent);

    public void updateTalent(Talent talent);

    public void deleteTalent(ReceiveEntity receiveEntity);

    public Integer findTalentNumber(ReceiveEntity receiveEntity);

    public Talent findTalent(ReceiveEntity receiveEntity);

    public List findPictureSrc(ReceiveEntity receiveEntity);

    public List findTalentSimpleQuestion(ReceiveEntity receiveEntity);

    public void updateTestAnswer(TestAnswer testAnswer);
}
