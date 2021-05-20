package com.example.demo.service.serviceImp;

import com.example.demo.entity.*;
import com.example.demo.dao.IndividualTestDao;
import com.example.demo.service.IndividualTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<Problem> findQuestionByExam(ReceiveEntity receiveEntity){
        List list = new ArrayList();
        //查询目标考试ID
        Integer examinationId = this.individualTestDao.findExamByUserId(receiveEntity.getTargetID());
        //查询该次考试的题目
        List<Integer> temp = this.individualTestDao.findExamsById(examinationId);
        for (int i = 0; i < temp.size(); i++) {
            //该题目的内容
            Problem problem = this.individualTestDao.findExam(temp.get(i));
            String[] lists = problem.getContent().split("&|&");
            problem.setContentA(lists[0]);
            problem.setContentB(lists[2]);
            problem.setContentC(lists[4]);
            problem.setContentD(lists[6]);
            list.add(problem);
        }
        return list;
    }

    public void setUserGrade(TestAnswerList testAnswerList){
//        this.individualTestDao.setUserGrade(testAnswer);
        String personId = testAnswerList.getPersonId();
        String testId = testAnswerList.getTestId();
        Integer grade = 0;
        for(int i=0;i<testAnswerList.getTestAnswers().length;i++){
            TestAnswer testAnswer = new TestAnswer();
            testAnswer.setPersonId(personId);
            testAnswer.setTestId(testId);
            testAnswer.setAnswer(testAnswerList.getTestAnswers()[i].getAnswer());
            testAnswer.setTrueAnswer(testAnswerList.getTestAnswers()[i].getTrueAnswer());
            testAnswer.setProblemId(testAnswerList.getTestAnswers()[i].getId());
            this.individualTestDao.saveUserAnswer(testAnswer);
            if(testAnswer.getAnswer().equals(testAnswer.getTrueAnswer())){
                grade += testAnswerList.getTestAnswers()[i].getScore();
            }
        }
        User user = new User();
        user.setUserAccountId(Integer.valueOf(personId));
        user.setGrade(grade.toString());
        this.individualTestDao.setUserGrade(user);

        //将用户账号设置为过期
        this.setUserTested(personId);
    }

    //用户已经考试过
    private void setUserTested(String personId) {
        this.individualTestDao.setUserTested(personId);
    }

    public Examination findTestTime(ReceiveEntity receiveEntity){
        //查询目标考试ID
        Integer examinationId = this.individualTestDao.findExamByUserId(receiveEntity.getTargetID());
        Examination examination = this.individualTestDao.findExamTimeByUserId(examinationId);
        return examination;
    }

    public Talent findTalent(ReceiveEntity receiveEntity){
        return this.individualTestDao.findTalent(receiveEntity.targetID);
    }

    public void updateTalent(Talent talent){
         this.individualTestDao.updateTalent(talent);
    }

    public void saveImg(String imgBase){
        //先解码
        String newImg = "";
        try {
            newImg= URLDecoder.decode(imgBase, "UTF-8");//解码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        GenerateImage(newImg,"G:\\homework\\localImg\\ces.png");
    }

    public String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(String.valueOf(imgStr));
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
