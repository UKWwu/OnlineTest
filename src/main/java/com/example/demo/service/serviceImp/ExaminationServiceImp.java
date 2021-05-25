package com.example.demo.service.serviceImp;

import com.example.demo.entity.*;
import com.example.demo.dao.EnterpriseDao;
import com.example.demo.dao.ExaminationDao;
import com.example.demo.service.ExaminationService;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.EnterpriseQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List findBestStudent(ReceiveEntity receiveEntity) {
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        List list = this.examinationDao.findFiveByTestId(receiveEntity);
        return list;
    }

    public List findRatioById(ReceiveEntity receiveEntity) {
        List<ResultEntity> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ResultEntity resultEntity = new ResultEntity();
            receiveEntity.setMax(50 + i * 10);
            if (i != 0){
                resultEntity.setName("分数在"+(40 + i * 10)+"，"+(50 + i * 10)+"区间");
                receiveEntity.setMin(40 + i * 10);
            }
            else{
                resultEntity.setName("不及格");
                receiveEntity.setMin(0);
            }

            resultEntity.setValue(this.examinationDao.findRatioById(receiveEntity));
            list.add(resultEntity);
        }
        return list;
    }


    public List findExamination(ReceiveEntity receiveEntity){
        //查询关键字为status
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        receiveEntity.page = (receiveEntity.page-1)*10;
        if(receiveEntity.getKey()!=null){
            return this.examinationDao.findExaminationWithKey(receiveEntity);
        }else{
            return this.examinationDao.findExamination(receiveEntity);
        }

    }

    //获取当前角色的单位
    public String getUnitByName(String name){
        return this.enterpriseDao.getUnitByName(name);
    }

    public void finishExam(ReceiveEntity receiveEntity){
        this.examinationDao.finishExam(receiveEntity);
    }

    public Integer findExaminationNumber(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        if(receiveEntity.getKey()!=null){
            return this.examinationDao.findExaminationNumberWithKey(receiveEntity);
        }else{
            return this.examinationDao.findExaminationNumber(receiveEntity);
        }
    }

    public Examination addExamination(Examination examination){
        examination.setUnit(this.getUnitByName(examination.getUserName()));
        this.examinationDao.addExamination(examination);
        return examination;
    }
    public List findExamUser(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        return this.examinationDao.findExamUser(receiveEntity);
    }

    public List addExaminee(ReceiveEntity receiveEntity){
        List temp = (List) receiveEntity.getObject();
        List ans = new ArrayList();
        for (int i = 0; i < temp.size(); i++) {
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
        }
        return ans;
    }

    //更新考试人员信息
    public void updateUserAndExam(UserAndExam userAndExam){
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
        String accout =  formatter.format(date).replace("-","") + userAndExam.getUserId();
        user.setUserAccount(accout);
        user.setUserName(userAndExam.getUserName());
        user.setUserUnit(userAndExam.getUtil());
        user.setSelectKey(this.getKey(user));
        this.userDao.addUser(user);
        return user.getId();
    }

    public String getKey(User user){
        String selectKey = "";
        selectKey += user.getUserName();
        selectKey += user.getUserAccount();
        selectKey += user.getUserType();
        selectKey += user.getUserTypeName();
        selectKey += user.getRemark();
        return selectKey;
    }

    //查找最近的一次考试
    public com.example.demo.entity.Examination findEndTest(ReceiveEntity receiveEntity){
        String unit =  this.getUnitByName(receiveEntity.getUserName());
        List lists = this.examinationDao.findEndTest(unit);
        if(lists.size() == 0){
            return null;
        }
        return (Examination) lists.get(0);
    }

    //删除笔试
    public void deleteExam(ReceiveEntity receiveEntity){
        this.examinationDao.deleteExam(receiveEntity);
    }

    //新建时查询人才库
    public List findExamTalent(ReceiveEntity receiveEntity){
        receiveEntity.setUserUnit(this.getUnitByName(receiveEntity.getUserName()));
        return this.examinationDao.findAllTalent(receiveEntity);
    }

    //新增考试人员
    public void addExamProblem(ReceiveEntity receiveEntity){
        List temp = (List) receiveEntity.getObject();
        for (int i = 0; i < temp.size(); i++) {
            TestProblem testProblem = new TestProblem();
            testProblem.setProblemId(String.valueOf(temp.get(i)));
            testProblem.setTestId(String.valueOf(receiveEntity.targetID));
            this.examinationDao.addExamProblem(testProblem);
        }
    }

    public List findProblemData(ReceiveEntity receiveEntity){
        List problemList = this.examinationDao.findProblemData(receiveEntity);
        List ans = new ArrayList();
        for (int i = 0; i < problemList.size(); i++) {
            TestProblem problem = (TestProblem)problemList.get(i);
            ans.add(this.enterpriseQuestionDao.findQuestionById(Integer.valueOf(problem.getProblemId())));
        }
        return ans;
    }

    public List findUserData(ReceiveEntity receiveEntity){
        List userList = this.examinationDao.findUserData(receiveEntity);
        List ans = new ArrayList();
        for (int i = 0; i < userList.size(); i++) {
            UserAndExam userAndExam = (UserAndExam)userList.get(i);
            ans.add(this.examinationDao.findUserById(userAndExam.getUserAccountId()));
        }
        return ans;
    }
}
