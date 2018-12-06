package com.chanct.maliceUrl.entity;

public class UrlInterface {

    public String ticketId;
    String url;
    String ip;
    String tp;
    int depth;
    int operator;
    String rdTime;
    int typeThree;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public String getRdTime() {
        return rdTime;
    }

    public void setRdTime(String rdTime) {
        this.rdTime = rdTime;
    }

    public int getTypeThree() {
        return typeThree;
    }

    public void setTypeThree(int typeThree) {
        this.typeThree = typeThree;
    }

    @Override
    public String toString() {
        return "UrlInterface{" +
                "ticketId='" + ticketId + '\'' +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", tp='" + tp + '\'' +
                ", depth=" + depth +
                ", operator=" + operator +
                ", rdTime='" + rdTime + '\'' +
                ", typeThree=" + typeThree +
                '}';
    }
}