package com.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import javax.persistence.Transient;

public class ProjectVisit implements Serializable {

	private Long projectVisitId;
	private Long projectId;
	private String content;
	private String createTime;
	private Integer deleteFlag;

	public ProjectVisit() {
	}

	public Long getProjectVisitId() {
		return projectVisitId;
	}

	public void setProjectVisitId(Long projectVisitId) {
		this.projectVisitId = projectVisitId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}