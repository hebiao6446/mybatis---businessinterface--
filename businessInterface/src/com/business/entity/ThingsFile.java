package com.business.entity;

import java.io.Serializable;

public class ThingsFile implements Serializable {

	private Long fileId;
	private Long thingsId;
	private String filePath;
	private Integer fileType;
	
	public ThingsFile() {
		super();
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public Long getThingsId() {
		return thingsId;
	}

	public void setThingsId(Long thingsId) {
		this.thingsId = thingsId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileType() {
		return fileType;
	}

	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
}
