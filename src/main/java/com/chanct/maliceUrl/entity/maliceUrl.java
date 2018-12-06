package com.chanct.maliceUrl.entity;

public class maliceUrl {

    int id;
    String url;
    String findTime;
    int coding;
    int operator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFindTime() {
        return findTime;
    }

    public void setFindTime(String findTime) {
        this.findTime = findTime;
    }

    public int getCoding() {
        return coding;
    }

    public void setCoding(int coding) {
        this.coding = coding;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "maliceUrl{" +
                "url='" + url + '\'' +
                ", findTime='" + findTime + '\'' +
                ", coding=" + coding +
                ", operator=" + operator +
                '}';
    }
}
