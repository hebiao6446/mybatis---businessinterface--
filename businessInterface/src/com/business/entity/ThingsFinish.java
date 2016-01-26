package com.business.entity;

import java.io.Serializable;
import java.util.Date;

public class ThingsFinish implements Serializable {
	private Long thingsId;
	private Long finishUserId;
	private String isFinish;
	private Date finishTime;
	private Integer deleteFlag;

	public ThingsFinish() {
		super();
	}

	public Long getThingsId() {
		return thingsId;
	}

	public void setThingsId(Long thingsId) {
		this.thingsId = thingsId;
	}

	public String getIsFinish() {
		return isFinish;
	}

	public void setIsFinish(String isFinish) {
		this.isFinish = isFinish;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Long getFinishUserId() {
		return finishUserId;
	}

	public void setFinishUserId(Long finishUserId) {
		this.finishUserId = finishUserId;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

}
