package com.chanct.maliceUrl.entity;

public class maliceUrlInterface {

    String ticketId;
    String name;
    String phone;
    int belongPlaceCode;
    String belongPlace;
    String url;
    String ip;
    String image;
    int depth;
    int operator;
    int visisPV;
    String firstTime;
    String lastTime;
    int isStop;
    String stopTime;
    int typeThree;
    int isPolice;
    String md5;

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBelongPlaceCode() {
        return belongPlaceCode;
    }

    public void setBelongPlaceCode(int belongPlaceCode) {
        this.belongPlaceCode = belongPlaceCode;
    }

    public String getBelongPlace() {
        return belongPlace;
    }

    public void setBelongPlace(String belongPlace) {
        this.belongPlace = belongPlace;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public int getVisisPV() {
        return visisPV;
    }

    public void setVisisPV(int visisPV) {
        this.visisPV = visisPV;
    }

    public String getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(String firstTime) {
        this.firstTime = firstTime;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public int getIsStop() {
        return isStop;
    }

    public void setIsStop(int isStop) {
        this.isStop = isStop;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public int getTypeThree() {
        return typeThree;
    }

    public void setTypeThree(int typeThree) {
        this.typeThree = typeThree;
    }

    public int getIsPolice() {
        return isPolice;
    }

    public void setIsPolice(int isPolice) {
        this.isPolice = isPolice;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "maliceUrlInterface{" +
                "ticketId='" + ticketId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", belongPlaceCode=" + belongPlaceCode +
                ", belongPlace='" + belongPlace + '\'' +
                ", url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", image='" + image + '\'' +
                ", depth=" + depth +
                ", operator=" + operator +
                ", visisPV=" + visisPV +
                ", firstTime='" + firstTime + '\'' +
                ", lastTime='" + lastTime + '\'' +
                ", isStop=" + isStop +
                ", stopTime='" + stopTime + '\'' +
                ", typeThree=" + typeThree +
                ", isPolice=" + isPolice +
                ", md5='" + md5 + '\'' +
                '}';
    }
}

