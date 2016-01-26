package com.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Transient;

public class Sell implements Serializable {

	private Long sellId;
	private Long contactsId;
	private String sellMethod;
	private Date updateTime;
	private Integer deleteFlag;
	private Integer status;
	private BigDecimal totalPrice;
	private Long userId;
	
	private Object SellVisitlist;
	private Object SellProductlist;
	private String name;
	private String company;
	public Sell() {
	}

	public Long getSellId() {
		return sellId;
	}

	public void setSellId(Long sellId) {
		this.sellId = sellId;
	}

	public Long getContactsId() {
		return contactsId;
	}

	public void setContactsId(Long contactsId) {
		this.contactsId = contactsId;
	}

	public String getSellMethod() {
		return sellMethod;
	}

	public void setSellMethod(String sellMethod) {
		this.sellMethod = sellMethod;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setSellVisitlist(Object sellVisitlist) {
		SellVisitlist = sellVisitlist;
	}

	public Object getSellVisitlist() {
		return SellVisitlist;
	}

	public void setSellProductlist(Object sellProductlist) {
		SellProductlist = sellProductlist;
	}

	public Object getSellProductlist() {
		return SellProductlist;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	
	
	
	
	
}