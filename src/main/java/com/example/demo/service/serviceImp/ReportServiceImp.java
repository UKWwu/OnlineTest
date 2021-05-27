package com.example.demo.service.serviceImp;


import com.example.demo.dao.ReportDao;
import com.example.demo.entity.ReceiveEntity;
import com.example.demo.entity.Talent;
import com.example.demo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReportServiceImp implements ReportService {

    @Autowired
    private ReportDao reportDao;

    public List<Talent> findTalentListByTestId(ReceiveEntity receiveEntity){
        List talentId = this.reportDao.findTalentId(receiveEntity.targetID);
        List talentList = new ArrayList();
        for (int i = 0; i < talentId.size(); i++) {
            Talent talent = this.reportDao.findTalent((Integer) talentId.get(i));
            talentList.add(talent);
        }
        return talentList;
    }
}
