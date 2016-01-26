package com.business.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Transient;

public class AttendDescript implements Serializable {

	private Long contentId;
	private Long userId;
	private String content;
	private java.sql.Date dayTime;
	private Date updateTime;
	private Integer deleteFlag;

	public AttendDescript() {
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public java.sql.Date getDayTime() {
		return dayTime;
	}

	public void setDayTime(java.sql.Date dayTime) {
		this.dayTime = dayTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	
	

}