package com.chanct.sjhmdinterfaceForBack.entity;

/**
 * Created by Administrator on 2017/9/16.
 */
public class BlackNum {


    public String phonenum;    //电话号码
    public String phonearea;    //电话归属地    移动
    public String issueTime;     //下发时间
    public String issueReason;   //下发原因
    public String issuePeople;    //下发人
    public String issueDepartment; //下发部门
    public String issuePhone;       //下发人电话号码
    public String auditReason;    //审核原因
    public String auditTime;      // 审核时间    入库时间
    public String auditPeople;    //审核人
    public String domain;          //运营商
    public String rulenumber;      //    规则号103
    public String frontid;         // 局点id   null
    public String calltype;    //主被叫  0
    public String operatetype;  //操作类型  0
    public String status;       //状态  1
    public String source;      //来源   3
    public int  interceptTime;  //拦截时间长度

    public String getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(String issueDepartment) {
        this.issueDepartment = issueDepartment;
    }

    public String getIssuePhone() {
        return issuePhone;
    }

    public void setIssuePhone(String issuePhone) {
        this.issuePhone = issuePhone;
    }

    public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getPhonearea() {
		return phonearea;
	}

	public void setPhonearea(String phonearea) {
		this.phonearea = phonearea;
	}

	public String getIssueReason() {
        return issueReason;
    }

    public void setIssueReason(String issueReason) {
        this.issueReason = issueReason;
    }

    public String getIssueTime() {
        return issueTime;
    }

    public void setIssueTime(String issueTime) {
        this.issueTime = issueTime;
    }

    public String getIssuePeople() {
        return issuePeople;
    }

    public void setIssuePeople(String issuePeople) {
        this.issuePeople = issuePeople;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getAuditPeople() {
        return auditPeople;
    }

    public void setAuditPeople(String auditPeople) {
        this.auditPeople = auditPeople;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getRulenumber() {
        return rulenumber;
    }

    public void setRulenumber(String rulenumber) {
        this.rulenumber = rulenumber;
    }

    public String getFrontid() {
        return frontid;
    }

    public void setFrontid(String frontid) {
        this.frontid = frontid;
    }

    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    public String getOperatetype() {
        return operatetype;
    }

    public void setOperatetype(String operatetype) {
        this.operatetype = operatetype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getInterceptTime() {
        return interceptTime;
    }

    public void setInterceptTime(int interceptTime) {
        this.interceptTime = interceptTime;
    }
}
