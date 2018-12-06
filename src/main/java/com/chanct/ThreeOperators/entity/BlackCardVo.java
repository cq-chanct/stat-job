package com.chanct.ThreeOperators.entity;


import java.io.Serializable;

public class BlackCardVo  implements Serializable{

	
	private static final long serialVersionUID = 1L;

	  private String filename;
	  private String phoneNum;
	  private String source;
	  private String sourcePhone;
	  private String disposeTime;
	  private String result;
	  private String closeTime;
	  private int type;   //运营商类型

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BlackCardVo() {
		super();
	}
	
	
	public BlackCardVo(String filename, String phoneNum, String source,
                       String sourcePhone, String disposeTime, String result,
                       String closeTime) {
		super();
		this.filename = filename;
		this.phoneNum = phoneNum;
		this.source = source;
		this.sourcePhone = sourcePhone;
		this.disposeTime = disposeTime;
		this.result = result;
		this.closeTime = closeTime;
	}


	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourcePhone() {
		return sourcePhone;
	}
	public void setSourcePhone(String sourcePhone) {
		this.sourcePhone = sourcePhone;
	}
	public String getDisposeTime() {
		return disposeTime;
	}
	public void setDisposeTime(String disposeTime) {
		this.disposeTime = disposeTime;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	  

}
