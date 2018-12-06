package com.chanct.ThreeOperators.entity;

import java.security.PrivateKey;

/**
 * 有害呼叫拦截统计量
 * Created by Administrator on 2017/12/20.
 */
public class BadcallNumber {

    private String startTime;
    private String endTime;
    private int territory;   //境内
    private String territoryTop1;   //境内top1
    private String territoryTop2;   //境内top2
    private String territoryTop3;   //境内top3
    private int abroad;   //境外
    private int number1;   //境内外总和
    private int anomaly;  //超频异常
    private int fraud;  //诈骗
    private int advertisement;  //广告
    private int other;    //其他
    private int number2;   //垃圾信息总和
    private int type;   //类型

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

    public int getTerritory() {
        return territory;
    }

    public void setTerritory(int territory) {
        this.territory = territory;
    }

    public String getTerritoryTop1() {
        return territoryTop1;
    }

    public void setTerritoryTop1(String territoryTop1) {
        this.territoryTop1 = territoryTop1;
    }

    public String getTerritoryTop2() {
        return territoryTop2;
    }

    public void setTerritoryTop2(String territoryTop2) {
        this.territoryTop2 = territoryTop2;
    }

    public String getTerritoryTop3() {
        return territoryTop3;
    }

    public void setTerritoryTop3(String territoryTop3) {
        this.territoryTop3 = territoryTop3;
    }

    public int getAbroad() {
        return abroad;
    }

    public void setAbroad(int abroad) {
        this.abroad = abroad;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getAnomaly() {
        return anomaly;
    }

    public void setAnomaly(int anomaly) {
        this.anomaly = anomaly;
    }

    public int getFraud() {
        return fraud;
    }

    public void setFraud(int fraud) {
        this.fraud = fraud;
    }

    public int getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(int advertisement) {
        this.advertisement = advertisement;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BadcallNumber{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", territory=" + territory +
                ", territoryTop1='" + territoryTop1 + '\'' +
                ", territoryTop2='" + territoryTop2 + '\'' +
                ", territoryTop3='" + territoryTop3 + '\'' +
                ", abroad=" + abroad +
                ", number1=" + number1 +
                ", anomaly=" + anomaly +
                ", fraud=" + fraud +
                ", advertisement=" + advertisement +
                ", other=" + other +
                ", number2=" + number2 +
                ", type=" + type +
                '}';
    }
}
