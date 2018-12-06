package com.chanct.tjStat.entity;

public class CallTotalCountVo {
	public String lightCount;
	public String deepCount;
	public String cheatCount;
	public String totalCount;
	public String createTime;
	public String dataSource; 
	
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getLightCount() {
		return lightCount;
	}
	public void setLightCount(String lightCount) {
		this.lightCount = lightCount;
	}
	public String getDeepCount() {
		return deepCount;
	}
	public void setDeepCount(String deepCount) {
		this.deepCount = deepCount;
	}
	public String getCheatCount() {
		return cheatCount;
	}
	public void setCheatCount(String cheatCount) {
		this.cheatCount = cheatCount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	} 


}
