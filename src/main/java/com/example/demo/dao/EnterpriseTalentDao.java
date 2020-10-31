package com.example.demo.dao;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.talent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "EnterpriseTalentDao")
public interface EnterpriseTalentDao {
    public List findTalentList(ReceiveEntity receiveEntity);

    public void addTalent(talent talent);

    public void updateTalent(talent talent);

    public void deleteTalent(ReceiveEntity receiveEntity);

    public Integer findTalentNumber(ReceiveEntity receiveEntity);

    public talent findTalent(ReceiveEntity receiveEntity);
}
