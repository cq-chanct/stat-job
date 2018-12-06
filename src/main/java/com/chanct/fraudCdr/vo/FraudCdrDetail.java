package com.chanct.fraudCdr.vo;

public class FraudCdrDetail {
	public String frontid;//局点编号
	public String direction;//局向
	public String domain;//运营商
	public String callingnumber;//主叫号码
	public String oricallednumber;//原被叫号码
	public String callednumber;//被叫号码
	public String starttime;//信令开始时间
	public String answertime;//通话开始时间
	public String keypresstime;//被叫按键时间
	public String answerendtime;//通话结束时间
	public String endtime;//信令结束时间
	public String calllength;//呼叫时长（空值填0），即信令时长
	public String callduration;//通话时长，单位：秒
	public String calltype;//呼叫类型
	public String barringtype;//拦截类型
	public String trunkid;//中继号 
	public String localcode;//源信令点编码
	public String destcode;//目的信令点编码
	public String listtype;//名单类型
	public String auditresult;//录音结果
	public String recordpath;//文件名
	public String recordstarttime;
	public String recordendtime;
	public String voiceName;
	public String voiceUrl;
	
	
	public String getRecordstarttime() {
		return recordstarttime;
	}
	public void setRecordstarttime(String recordstarttime) {
		this.recordstarttime = recordstarttime;
	}
	public String getRecordendtime() {
		return recordendtime;
	}
	public void setRecordendtime(String recordendtime) {
		this.recordendtime = recordendtime;
	}
	public String getTrunkid() {
		return trunkid;
	}
	public void setTrunkid(String trunkid) {
		this.trunkid = trunkid;
	}
	public String getLocalcode() {
		return localcode;
	}
	public void setLocalcode(String localcode) {
		this.localcode = localcode;
	}
	public String getDestcode() {
		return destcode;
	}
	public void setDestcode(String destcode) {
		this.destcode = destcode;
	}
	public String getFrontid() {
		return frontid;
	}
	public void setFrontid(String frontid) {
		this.frontid = frontid;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getCallingnumber() {
		return callingnumber;
	}
	public void setCallingnumber(String callingnumber) {
		this.callingnumber = callingnumber;
	}
	public String getOricallednumber() {
		return oricallednumber;
	}
	public void setOricallednumber(String oricallednumber) {
		this.oricallednumber = oricallednumber;
	}
	public String getCallednumber() {
		return callednumber;
	}
	public void setCallednumber(String callednumber) {
		this.callednumber = callednumber;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getAnswertime() {
		return answertime;
	}
	public void setAnswertime(String answertime) {
		this.answertime = answertime;
	}
	public String getKeypresstime() {
		return keypresstime;
	}
	public void setKeypresstime(String keypresstime) {
		this.keypresstime = keypresstime;
	}
	public String getAnswerendtime() {
		return answerendtime;
	}
	public void setAnswerendtime(String answerendtime) {
		this.answerendtime = answerendtime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getCalllength() {
		return calllength;
	}
	public void setCalllength(String calllength) {
		this.calllength = calllength;
	}
	public String getCallduration() {
		return callduration;
	}
	public void setCallduration(String callduration) {
		this.callduration = callduration;
	}
	public String getCalltype() {
		return calltype;
	}
	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}
	public String getBarringtype() {
		return barringtype;
	}
	public void setBarringtype(String barringtype) {
		this.barringtype = barringtype;
	}

	public String getListtype() {
		return listtype;
	}
	public void setListtype(String listtype) {
		this.listtype = listtype;
	}
	public String getAuditresult() {
		return auditresult;
	}
	public void setAuditresult(String auditresult) {
		this.auditresult = auditresult;
	}
	public String getRecordpath() {
		return recordpath;
	}
	public void setRecordpath(String recordpath) {
		this.recordpath = recordpath;
	}
	public String getVoiceName() {
		return voiceName;
	}
	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}
	public String getVoiceUrl() {
		return voiceUrl;
	}
	public void setVoiceUrl(String voiceUrl) {
		this.voiceUrl = voiceUrl;
	}
	
	
}
