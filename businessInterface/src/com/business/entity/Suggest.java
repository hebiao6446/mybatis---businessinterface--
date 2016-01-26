package com.business.entity;

import java.io.Serializable;
import java.util.Date;

public class Suggest implements Serializable {

	private Long suggestId;
	private Long userId;
	private String content;
	private Date updateTime;
	private Integer deleteFlag;
	
	public Suggest() {
		super();
	}
	public Long getSuggestId() {
		return suggestId;
	}
	public void setSuggestId(Long suggestId) {
		this.suggestId = suggestId;
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
