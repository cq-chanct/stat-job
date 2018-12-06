package com.chanct.sjhmdinterfaceForBack.entity;

/**
 * Created by Administrator on 2017/9/16.
 */
public class FeedBack {

    public String sjhmdId;
    public String hm;        //手机号码
    public String ballyxm;   //办案联系人姓名
    public String ballydh;   //办案联系人电话
    public String ssgajg;    //所属公安机关
    public String qzje;      //劝阻金额
    public String yssje;     //已损失金额
    public String hmlyid;    //号码来源
    public String ajlbdm;    //案件类别代码
    public String zxzt;      //状态
    public int gp;           //拦截时间
    public String lhyy;      //拉黑原因

    public String getLhyy() {
        return lhyy;
    }

    public void setLhyy(String lhyy) {
        this.lhyy = lhyy;
    }

    public int getGp() {
        return gp;
    }

    public void setGp(int gp) {
        this.gp = gp;
    }

    public String getSjhmdId() {
        return sjhmdId;
    }

    public void setSjhmdId(String sjhmdId) {
        this.sjhmdId = sjhmdId;
    }

    public String getHm() {
        return hm;
    }

    public void setHm(String hm) {
        this.hm = hm;
    }

    public String getBallyxm() {
        return ballyxm;
    }

    public void setBallyxm(String ballyxm) {
        this.ballyxm = ballyxm;
    }

    public String getBallydh() {
        return ballydh;
    }

    public void setBallydh(String ballydh) {
        this.ballydh = ballydh;
    }

    public String getSsgajg() {
        return ssgajg;
    }

    public void setSsgajg(String ssgajg) {
        this.ssgajg = ssgajg;
    }

    public String getQzje() {
        return qzje;
    }

    public void setQzje(String qzje) {
        this.qzje = qzje;
    }

    public String getYssje() {
        return yssje;
    }

    public void setYssje(String yssje) {
        this.yssje = yssje;
    }

    public String getHmlyid() {
        return hmlyid;
    }

    public void setHmlyid(String hmlyid) {
        this.hmlyid = hmlyid;
    }

    public String getAjlbdm() {
        return ajlbdm;
    }

    public void setAjlbdm(String ajlbdm) {
        this.ajlbdm = ajlbdm;
    }

    public String getZxzt() {
        return zxzt;
    }

    public void setZxzt(String zxzt) {
        this.zxzt = zxzt;
    }

    @Override
    public String
    toString() {
        return "feedBack{" +
                "sjhmdId='" + sjhmdId + '\'' +
                ", hm='" + hm + '\'' +
                ", ballyxm='" + ballyxm + '\'' +
                ", ballydh='" + ballydh + '\'' +
                ", ssgajg='" + ssgajg + '\'' +
                ", qzje='" + qzje + '\'' +
                ", yssje='" + yssje + '\'' +
                ", hmlyid='" + hmlyid + '\'' +
                ", ajlbdm='" + ajlbdm + '\'' +
                ", zxzt='" + zxzt + '\'' +
                '}';
    }
}


