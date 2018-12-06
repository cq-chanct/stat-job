
package com.chanct.interfaceProcess.entity;


public class InterfaceProcess {
	public String id;
	public String callingnumber;
	public String callednumber;
	public String result;
	public String remark;
	public String dissuade;
	public String amount;
	public String createtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCallingnumber() {
		return callingnumber;
	}

	public void setCallingnumber(String callingnumber) {
		this.callingnumber = callingnumber;
	}

	public String getCallednumber() {
		return callednumber;
	}

	public void setCallednumber(String callednumber) {
		this.callednumber = callednumber;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDissuade() {
		return dissuade;
	}

	public void setDissuade(String dissuade) {
		this.dissuade = dissuade;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "InterfaceProcess{" +
				"id='" + id + '\'' +
				", callingnumber='" + callingnumber + '\'' +
				", callednumber='" + callednumber + '\'' +
				", result='" + result + '\'' +
				", remark='" + remark + '\'' +
				", createtime='" + createtime + '\'' +
				'}';
	}
}