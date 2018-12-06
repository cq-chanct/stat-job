package com.chanct.ThreeOperators.entity;

public class EnterpriseManagement {

    private String startTime;
    private String endTime;
    private int training;   //防范打击通讯信息诈骗培训次数
    private int examination; //防范打击通讯信息诈骗检查次数
    private int violation;  //追究企业内部违规人员数量
    private int type; // 类型

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getTraining() {
        return training;
    }

    public void setTraining(int training) {
        this.training = training;
    }

    public int getExamination() {
        return examination;
    }

    public void setExamination(int examination) {
        this.examination = examination;
    }

    public int getViolation() {
        return violation;
    }

    public void setViolation(int violation) {
        this.violation = violation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
