package com.chanct.netsecur.cronjob.vo;


import java.io.Serializable;


public class TCronjob implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -5179660205231392286L;

	private Long id;

	private String name;
	
    private String type;//‘1’：简单时间策略；‘2’：复杂时间策略；‘3’：单次任务；
    
    private String strategy;//时间策略:1）简单时间策略：时间间隔，单位分钟；2)复杂时间策略：quartz表达式；3）立刻执行的执行次数

    private String jobClass;

    private String jobData;

    private String jobStatus;
    
    private String serviceType;
    
    private String beginTime;

    private String createTime;
    
    private String updateTime;

	private String endTime;

    private String yl1;

    private String yl2;

    private String yl3;

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
    public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass == null ? null : jobClass.trim();
    }

    public String getJobData() {
        return jobData;
    }

    public void setJobData(String jobData) {
        this.jobData = jobData == null ? null : jobData.trim();
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1 == null ? null : yl1.trim();
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2 == null ? null : yl2.trim();
    }

    public String getYl3() {
        return yl3;
    }

    public void setYl3(String yl3) {
        this.yl3 = yl3 == null ? null : yl3.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

}