package com.example.demo.service.serviceImp;

import com.example.demo.entity.*;
import com.example.demo.dao.IndividualTestDao;
import com.example.demo.service.IndividualTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.xml.soap.Text;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class IndividualTestServiceImp implements IndividualTestService {

    @Autowired
    private IndividualTestDao individualTestDao;

    public List<Problem> findQuestionByExam(ReceiveEntity receiveEntity) {
        List list = new ArrayList();
        //查询目标考试ID
        Integer examinationId = this.individualTestDao.findExamByUserId(receiveEntity.getTargetID());
        //查询该次考试的题目
        List<Integer> temp = this.individualTestDao.findExamsById(examinationId);
        for (int i = 0; i < temp.size(); i++) {
            //该题目的内容
            Problem problem = this.individualTestDao.findExam(temp.get(i));
            if(problem.getType().equals("选择题")){
                String[] lists = problem.getContent().split("&|&");
                problem.setContentA(lists[0]);
                problem.setContentB(lists[2]);
                problem.setContentC(lists[4]);
                problem.setContentD(lists[6]);
            }
            list.add(problem);
        }
        return list;
    }

    public void setUserGrade(TestAnswerList testAnswerList) {
//        this.individualTestDao.setUserGrade(testAnswer);
        //将用户账号设置为过期
        this.setUserTested(testAnswerList.getPersonId());
        Integer personId = this.individualTestDao.findTalentId(Integer.valueOf(testAnswerList.getPersonId()));
        String testId = testAnswerList.getTestId();
        Integer grade = 0;
        for (int i = 0; i < testAnswerList.getTestAnswers().length; i++) {
            TestAnswer testAnswer = new TestAnswer();
            testAnswer.setPersonId(String.valueOf(personId));
            testAnswer.setTestId(testId);
            testAnswer.setAnswer(testAnswerList.getTestAnswers()[i].getAnswer());
            testAnswer.setTrueAnswer(testAnswerList.getTestAnswers()[i].getTrueAnswer());
            testAnswer.setProblemId(testAnswerList.getTestAnswers()[i].getProblemId());
            testAnswer.setTitle(testAnswerList.getTestAnswers()[i].getTitle());
            testAnswer.setType(testAnswerList.getTestAnswers()[i].getType());
            testAnswer.setScore(testAnswerList.getTestAnswers()[i].getScore());
            if(testAnswer.getAnswer()!=null && testAnswer.getTrueAnswer()!=null){
                if (testAnswer.getAnswer().equals(testAnswer.getTrueAnswer())) {
                    grade += testAnswerList.getTestAnswers()[i].getScore();
                    testAnswer.setGrade(testAnswerList.getTestAnswers()[i].getScore());
                }
            }
            this.individualTestDao.saveUserAnswer(testAnswer);
        }
        Talent talent = new Talent();
        talent.setId(personId);
        talent.setGrade(grade.toString());
        this.individualTestDao.setUserGrade(talent);
    }

    //用户已经考试过
    private void setUserTested(String personId) {
        this.individualTestDao.setUserTested(personId);
    }

    public Examination findTestTime(ReceiveEntity receiveEntity) {
        //查询目标考试ID
        Integer examinationId = this.individualTestDao.findExamByUserId(receiveEntity.getTargetID());
            Examination examination = this.individualTestDao.findExamTimeByUserId(examinationId);
        return examination;
    }

    public Talent findTalent(ReceiveEntity receiveEntity) {
        Integer id = this.individualTestDao.findTalentId(receiveEntity.targetID);
        return this.individualTestDao.findTalent(id);
    }

    public void updateTalent(Talent talent) {
        this.individualTestDao.updateTalent(talent);
    }

    public String saveImg(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName.indexOf("\\") != -1) {
            fileName = fileName.substring(fileName.lastIndexOf("\\"));
        }
        String filePath = "G:/homework/localImg/";
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + fileName);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath+fileName;
    }

    //数据库保存图片
    public void savePicture(Picture picture){
        picture.setTalentId(this.individualTestDao.findTalentId(picture.getTalentId()));
        this.individualTestDao.savePicture(picture);
    }

    //查找参考人员信息
    public List findTalentList(ReceiveEntity receiveEntity){
        //查找参考人员有哪些
        List<UserAndExam>  list= this.individualTestDao.findUser(receiveEntity);
        List ans = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            ans.add(this.individualTestDao.findTalent(list.get(i).getUserId()));
        }
        return ans;
    }

    public Examination findExamByUserId(ReceiveEntity receiveEntity){
        Integer examinationId = this.individualTestDao.findExamByUserId(receiveEntity.getTargetID());
        return this.individualTestDao.findExamById(examinationId);
    }
}
