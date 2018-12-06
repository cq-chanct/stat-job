package com.chanct.ThreeOperators.entity;

public class TelecomRectify {

    private String startTime;
    private String endTime;
    private int   voice;          //语音专线用户总数
    private int  rectifyVoice;   //清理整顿的语音专线业务用户数量
    private int shutDownVoice;  //拆除关停的语音专线中继线条数
    private int rectify_business;  //清理整顿400业务用户数
    private int shutDown_business; //关停回收400号码数量
    private int one_connect;       //关停回收“一号通”号码数量
    private int business;          //关停回收的商务总机号码数量
    private int type;        //类型

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

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getRectifyVoice() {
        return rectifyVoice;
    }

    public void setRectifyVoice(int rectifyVoice) {
        this.rectifyVoice = rectifyVoice;
    }

    public int getShutDownVoice() {
        return shutDownVoice;
    }

    public void setShutDownVoice(int shutDownVoice) {
        this.shutDownVoice = shutDownVoice;
    }

    public int getRectify_business() {
        return rectify_business;
    }

    public void setRectify_business(int rectify_business) {
        this.rectify_business = rectify_business;
    }

    public int getShutDown_business() {
        return shutDown_business;
    }

    public void setShutDown_business(int shutDown_business) {
        this.shutDown_business = shutDown_business;
    }

    public int getOne_connect() {
        return one_connect;
    }

    public void setOne_connect(int one_connect) {
        this.one_connect = one_connect;
    }

    public int getBusiness() {
        return business;
    }

    public void setBusiness(int business) {
        this.business = business;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
