package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "EnterpriseDao")
public interface EnterpriseDao {
    //根据名字获取单位ID
    public String getUnitByName(String name);
}
