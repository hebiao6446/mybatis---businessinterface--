package com.business.service;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import com.business.entity.User;
import com.business.entity.UserRoleCompany;

public interface UserManager {

	User userLogin(JSONObject param);

	User findUserInfo(JSONObject param);

	Object findUserCompanyId(Long roleId);

	Integer findUserCompanyType(Long roleId);

	Long findUserRoleChidren(Long roleId);

	@SuppressWarnings("rawtypes")
	List<HashMap> findUserMainMenu(JSONObject param);

	@SuppressWarnings("rawtypes")
	List<HashMap> findUserAllMenu(JSONObject param);

	Object findUserAddModule(Object object);

	String findUserChidrenRole(Long roleId);

	void addUserSuggest(JSONObject param);

	UserRoleCompany finduserRoleAndCompany(JSONObject param);

	List<HashMap> findAllUserList(Long roleId);

}
