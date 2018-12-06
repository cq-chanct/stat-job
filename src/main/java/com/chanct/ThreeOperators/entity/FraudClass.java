package com.chanct.ThreeOperators.entity;

/**
 * Created by Administrator on 2017/12/20.
 */
public class FraudClass {


    private String startTime;// NULL开始时间
    private String endTime; //NULL结束时间
    private int gwelfareMessage;  //公益短信发送量
    private int garbageMessage;  //垃圾短信发送量
    private int fraudMessage;  //中低危疑似诈骗提醒短信发送量
    private int highlySuspectedNumber;  //推送公安机关的高危疑似诈骗电话量
    private  int dissuadeSuccess; //劝阻成功次数
    private  int dissuadeFailure;   //劝阻失败次数
    private int publicFraudNumber;  //公安机关确认的诈骗电话量
    private double stopMoney; //止损金额
    private int fraudUrlNumber;  //推送公安机关的恶意URL统计量
    private int enterpriseUrl;  //企业监测处置的恶意URL统计量
    private int wjzNumber;  //伪基站统计量
    private int type; //1:电信2：移动3：联通


    public int getDissuadeSuccess() {
        return dissuadeSuccess;
    }

    public void setDissuadeSuccess(int dissuadeSuccess) {
        this.dissuadeSuccess = dissuadeSuccess;
    }

    public int getDissuadeFailure() {
        return dissuadeFailure;
    }

    public void setDissuadeFailure(int dissuadeFailure) {
        this.dissuadeFailure = dissuadeFailure;
    }

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

    public int getGwelfareMessage() {
        return gwelfareMessage;
    }

    public void setGwelfareMessage(int gwelfareMessage) {
        this.gwelfareMessage = gwelfareMessage;
    }

    public int getGarbageMessage() {
        return garbageMessage;
    }

    public void setGarbageMessage(int garbageMessage) {
        this.garbageMessage = garbageMessage;
    }

    public int getFraudMessage() {
        return fraudMessage;
    }

    public void setFraudMessage(int fraudMessage) {
        this.fraudMessage = fraudMessage;
    }

    public int getHighlySuspectedNumber() {
        return highlySuspectedNumber;
    }

    public void setHighlySuspectedNumber(int highlySuspectedNumber) {
        this.highlySuspectedNumber = highlySuspectedNumber;
    }

    public int getPublicFraudNumber() {
        return publicFraudNumber;
    }

    public void setPublicFraudNumber(int publicFraudNumber) {
        this.publicFraudNumber = publicFraudNumber;
    }

    public double getStopMoney() {
        return stopMoney;
    }

    public void setStopMoney(double stopMoney) {
        this.stopMoney = stopMoney;
    }

    public int getFraudUrlNumber() {
        return fraudUrlNumber;
    }

    public void setFraudUrlNumber(int fraudUrlNumber) {
        this.fraudUrlNumber = fraudUrlNumber;
    }

    public int getEnterpriseUrl() {
        return enterpriseUrl;
    }

    public void setEnterpriseUrl(int enterpriseUrl) {
        this.enterpriseUrl = enterpriseUrl;
    }

    public int getWjzNumber() {
        return wjzNumber;
    }

    public void setWjzNumber(int wjzNumber) {
        this.wjzNumber = wjzNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
