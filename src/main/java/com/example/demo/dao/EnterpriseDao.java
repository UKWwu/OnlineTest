package com.example.demo.dao;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.Picture;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "EnterpriseDao")
public interface EnterpriseDao {
    //根据名字获取单位ID
    public String getUnitByName(String name);

    public String getUnitID(String id);

    public List<Enterprise> findAllEnterprise();

    public void changeRecommend(Enterprise enterprise);

    public List<Picture> findPictureSrc(ReceiveEntity receiveEntity);

    public User getAccountNameById(Integer userAccountId);
}
