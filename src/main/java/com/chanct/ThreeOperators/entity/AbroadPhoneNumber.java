package com.chanct.ThreeOperators.entity;

/**
 * 境外来电提醒发送量
 * Created by Administrator on 2017/12/20.
 */
public class AbroadPhoneNumber {


    private String startTime;
    private String endTime;
    private int flashSignal;
    private int message;
    private int number;
    private int type;

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

    public int getFlashSignal() {
        return flashSignal;
    }

    public void setFlashSignal(int flashSignal) {
        this.flashSignal = flashSignal;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AbroadPhoneNumber{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", flashSignal=" + flashSignal +
                ", message=" + message +
                ", number=" + number +
                ", type=" + type +
                '}';
    }
}
