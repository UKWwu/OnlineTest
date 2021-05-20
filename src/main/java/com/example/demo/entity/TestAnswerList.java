package com.example.demo.entity;

public class TestAnswerList {
    private Integer id;
    private String personId;
    private String testId;
    private TestAnswer[] testAnswers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public TestAnswer[] getTestAnswers() {
        return testAnswers;
    }

    public void setTestAnswers(TestAnswer[] testAnswers) {
        this.testAnswers = testAnswers;
    }
}
