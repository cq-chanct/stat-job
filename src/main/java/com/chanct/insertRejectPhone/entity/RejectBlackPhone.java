package com.chanct.insertRejectPhone.entity;

/**
 * Created by Administrator on 2018/1/17.
 */
public class RejectBlackPhone {

    
    public String callingnumber;    // NOT NULL黑名单号码
    public String interceptTime;    // NULL拉黑时间长度
    public String issueReason;    // NULL拉黑原因
    public String issuePeople;    // NULL下发人
    public String issuePhone;    // NULL下发人联系方式
    public String issueDepartment;    // NULL下发人部门
    public String receiveTime;    // NULL接收时间
    public String rejectReason;    // NULL驳回原因
    public String id;    // NOT NULLid
    public String createTime;    // NULL创建时间


    public String getCallingnumber() {
        return callingnumber;
    }

    public void setCallingnumber(String callingnumber) {
        this.callingnumber = callingnumber;
    }

    public String getInterceptTime() {
        return interceptTime;
    }

    public void setInterceptTime(String interceptTime) {
        this.interceptTime = interceptTime;
    }

    public String getIssueReason() {
        return issueReason;
    }

    public void setIssueReason(String issueReason) {
        this.issueReason = issueReason;
    }

    public String getIssuePeople() {
        return issuePeople;
    }

    public void setIssuePeople(String issuePeople) {
        this.issuePeople = issuePeople;
    }

    public String getIssuePhone() {
        return issuePhone;
    }

    public void setIssuePhone(String issuePhone) {
        this.issuePhone = issuePhone;
    }

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
