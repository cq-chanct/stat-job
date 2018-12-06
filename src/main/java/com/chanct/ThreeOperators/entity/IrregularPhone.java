package com.chanct.ThreeOperators.entity;

/**
 * 不规则号码统计量
 * Created by Administrator on 2017/12/20.
 */
public class IrregularPhone {

    private String startTime;
    private String endTime;
    private int territory;   //境内
    private String territoryTop1;   //境内top1
    private String territoryTop2;   //境内top2
    private String territoryTop3;   //境内top3
    private int abroad;   //境外
    private int number;   //境内外总和
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
        return "IrregularPhone{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", territory=" + territory +
                ", territoryTop1='" + territoryTop1 + '\'' +
                ", territoryTop2='" + territoryTop2 + '\'' +
                ", territoryTop3='" + territoryTop3 + '\'' +
                ", abroad=" + abroad +
                ", number=" + number +
                ", type=" + type +
                '}';
    }
}
