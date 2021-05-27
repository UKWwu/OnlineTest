package com.example.demo.dao;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ReportDao")
public interface ReportDao {
    public List findTalentId(Integer id);

    public Talent findTalent(Integer id);
}
