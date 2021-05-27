package com.example.demo.service.serviceImp;

import com.example.demo.entity.*;
import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.ExaminationDao;
import com.example.demo.service.ExaminationService;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.EnterpriseQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ExaminationServiceImp implements ExaminationService {

    @Autowired
    private ExaminationDao examinationDao;

    @Autowired
    private EnterpriseDao enterpriseDao;


    @Autowired
    private UserDao userDao;

    @Autowired
    private EnterpriseQuestionDao enterpriseQuestionDao;


    @Autowired
    private JavaMailSender javaMailSender;

    public List findBestStudent(ReceiveEntity receiveEntity) {
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        List list = this.examinationDao.findFiveByTestId(receiveEntity);
        return list;
    }

    public List findRatioById(ReceiveEntity receiveEntity) {
        List<ResultEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ResultEntity resultEntity = new ResultEntity();
            receiveEntity.setMax(10 + i * 10);
            resultEntity.setName("分数在" + (i * 10) + "，" + (10 + i * 10) + "区间");
            receiveEntity.setMin(i * 10);

            resultEntity.setValue(this.examinationDao.findRatioById(receiveEntity));
            list.add(resultEntity);
        }
        return list;
    }


    public List findExamination(ReceiveEntity receiveEntity) {
        //查询关键字为status
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.page = (receiveEntity.page - 1) * 10;
        if (receiveEntity.getKey() != null) {
            return this.examinationDao.findExaminationWithKey(receiveEntity);
        } else {
            return this.examinationDao.findExamination(receiveEntity);
        }

    }

    //获取当前角色的单位
    public String getUnitByName(String name) {
        return this.enterpriseDao.getUnitByName(name);
    }

    public void finishExam(ReceiveEntity receiveEntity) {
        this.examinationDao.finishExam(receiveEntity);
    }

    public Integer findExaminationNumber(ReceiveEntity receiveEntity) {
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        if (receiveEntity.getKey() != null) {
            return this.examinationDao.findExaminationNumberWithKey(receiveEntity);
        } else {
            return this.examinationDao.findExaminationNumber(receiveEntity);
        }
    }

    public Examination addExamination(Examination examination) {
        examination.setUnit(this.getUnitByName(examination.getUserName()));
        this.examinationDao.addExamination(examination);
        return examination;
    }

    public List findExamUser(ReceiveEntity receiveEntity) {
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        return this.examinationDao.findExamUser(receiveEntity);
    }

    public List addExaminee(ReceiveEntity receiveEntity) {
        List temp = (List) receiveEntity.getObject();
        List ans = new ArrayList();
        for (int i = 0; i < temp.size(); i++) {
            Talent talentTemp = new Talent();
            talentTemp.setId((Integer) temp.get(i));
            talentTemp.setTestId(receiveEntity.targetID);
            this.examinationDao.updateTalentTest(talentTemp);
            UserAndExam userAndExam = new UserAndExam();
            userAndExam.setUserId((Integer) temp.get(i));
            userAndExam.setExaminationId(receiveEntity.getTargetID());
            this.examinationDao.addExaminee(userAndExam);
            Talent talent = this.getUserNameById(userAndExam.getUserId());
            userAndExam.setUserName(talent.getName());
            userAndExam.setUtil(Integer.valueOf(talent.getUnit()));
            //创建考试账号
            userAndExam.setUserAccountId(this.createUserAccount(userAndExam));
            ans.add(userAndExam);
            //发送邮件,设置相关信息
            EmaiEntity emaiEntity = new EmaiEntity();
            emaiEntity.setName(talent.getName());
            //账号信息
            User user = this.enterpriseDao.getAccountNameById(userAndExam.getUserAccountId());
            emaiEntity.setAccountName(user.getUserAccount());
            emaiEntity.setPassword(user.getPassword());
            emaiEntity.setEmail(talent.getEmail());
            emaiEntity.setRoute("http://localhost:8080");
            //查看考试信息
            Examination examination = this.examinationDao.findExaminationById(receiveEntity.targetID);
            emaiEntity.setBeginTime(examination.getBeginTime());
            emaiEntity.setEndTime(examination.getEndTime());
            emaiEntity.setContinueTime(examination.getContinueTime());
            emaiEntity.setTestName(examination.getName());
            this.sendSimpleMail(emaiEntity);
        }
        return ans;
    }

    //更新考试人员信息
    public void updateUserAndExam(UserAndExam userAndExam) {
        this.examinationDao.updateExaminee(userAndExam);
    }


    //根据ID获取姓名
    private Talent getUserNameById(Integer userId) {
        return this.userDao.getUserNameById(userId);
    }

    //创建考试账号
    private Integer createUserAccount(UserAndExam userAndExam) {
        User user = new User();
        user.setUserType("3");
        user.setUserTypeName("个人用户");
        user.setPassword("123456");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String accout = formatter.format(date).replace("-", "") + userAndExam.getUserId();
        user.setUserAccount(accout);
        user.setUserName(userAndExam.getUserName());
        user.setUserUnit(userAndExam.getUtil());
        user.setSelectKey(this.getKey(user));
        this.userDao.addUser(user);
        return user.getId();
    }

    public String getKey(User user) {
        String selectKey = "";
        selectKey += user.getUserName();
        selectKey += user.getUserAccount();
        selectKey += user.getUserType();
        selectKey += user.getUserTypeName();
        selectKey += user.getRemark();
        return selectKey;
    }

    //查找最近的一次考试
    public com.example.demo.entity.Examination findEndTest(ReceiveEntity receiveEntity) {
        String unit = this.getUnitByName(receiveEntity.getUserName());
        List lists = this.examinationDao.findEndTest(unit);
        if (lists.size() == 0) {
            return null;
        }
        return (Examination) lists.get(0);
    }

    //删除笔试
    public void deleteExam(ReceiveEntity receiveEntity) {
        this.examinationDao.deleteExam(receiveEntity);
    }

    //新建时查询人才库
    public List findExamTalent(ReceiveEntity receiveEntity) {
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        return this.examinationDao.findAllTalent(receiveEntity);
    }

    //新增考试人员
    public void addExamProblem(ReceiveEntity receiveEntity) {
        List temp = (List) receiveEntity.getObject();
        for (int i = 0; i < temp.size(); i++) {
            TestProblem testProblem = new TestProblem();
            testProblem.setProblemId(String.valueOf(temp.get(i)));
            testProblem.setTestId(String.valueOf(receiveEntity.targetID));
            this.examinationDao.addExamProblem(testProblem);
        }
    }

    public List findProblemData(ReceiveEntity receiveEntity) {
        List problemList = this.examinationDao.findProblemData(receiveEntity);
        List ans = new ArrayList();
        for (int i = 0; i < problemList.size(); i++) {
            TestProblem problem = (TestProblem) problemList.get(i);
            ans.add(this.enterpriseQuestionDao.findQuestionById(Integer.valueOf(problem.getProblemId())));
        }
        return ans;
    }

    public List findUserData(ReceiveEntity receiveEntity) {
        List userList = this.examinationDao.findUserData(receiveEntity);
        List ans = new ArrayList();
        for (int i = 0; i < userList.size(); i++) {
            UserAndExam userAndExam = (UserAndExam) userList.get(i);
            ans.add(this.examinationDao.findUserById(userAndExam.getUserAccountId()));
        }
        return ans;
    }


    //发送邮件测试
    public void sendSimpleMail(EmaiEntity email) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        // 设置邮件主题
        message.setSubject("欢迎参加"+ email.getTestName());
        // 设置邮件发送者，这个跟application.yml中设置的要一致
        message.setFrom("1632846188@qq.com");
        // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似
        // message.setTo("10*****16@qq.com","12****32*qq.com");
        message.setTo(email.getEmail());
        // 设置邮件抄送人，可以WW有多个抄送人
//        message.setCc("12****32*qq.com");
        // 设置隐秘抄送人，可以有多个
//        message.setBcc("7******9@qq.com");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        // 设置邮件的正文
        String content = "";
        content += "亲爱的"+email.getName()+"\n";
        content += "恭喜你进入笔试阶段\n";
        String beginTime=simpleDateFormat.format(email.getBeginTime());
        content += "笔试开始时间为"+beginTime+"\n";
        String endTime = simpleDateFormat.format(email.getEndTime());
        content += "结束时间为"+endTime+"\n";
        content += "账号名:"+email.getAccountName()+"\n";
        content += "密码:"+email.getPassword()+"\n";
        content += "考试地址为:"+email.getRoute()+"\n";
        message.setText(content);
        // 发送邮件
        javaMailSender.send(message);
    }
}
