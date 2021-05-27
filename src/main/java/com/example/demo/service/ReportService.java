package com.example.demo.service;

import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;

import java.util.List;

public interface ReportService {
    public List<Talent> findTalentListByTestId(ReceiveEntity receiveEntity);
}
