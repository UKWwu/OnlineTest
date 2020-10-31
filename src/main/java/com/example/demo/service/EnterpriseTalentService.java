package com.example.demo.service;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.talent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EnterpriseTalentService {
    public List findTalentList(ReceiveEntity receiveEntity);

    public void addTalent(talent talent);

    public void updateTalent(talent talent);

    public void deleteTalent(ReceiveEntity receiveEntity);

    public Integer findTalentNumber(ReceiveEntity receiveEntity);

    public talent findTalent(ReceiveEntity receiveEntity);
}
