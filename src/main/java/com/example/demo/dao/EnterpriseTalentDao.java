package com.example.demo.dao;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;
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
}
