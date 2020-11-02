package com.example.demo.service;

import com.example.demo.entity.Enterprise;
import com.example.demo.entity.ReceiveEntity;

import java.util.List;

public interface EnterpriseService {
    public List findEnterpriseRecommend(ReceiveEntity receiveEntity);

    public void changeRecommend(Enterprise enterprise);
}
