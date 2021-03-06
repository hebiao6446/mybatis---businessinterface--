package com.business.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.sf.json.JSONObject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.business.entity.Group;
import com.business.service.ContactManager;

@Service
@Transactional
public class ContactManagerImpl implements ContactManager {
	@Autowired
	private SqlSessionTemplate sqlSession;

	/**
	 * 此句话针对查询 // 一个只读事务，可以提高效率。
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List findUserGroupList(JSONObject param) {
		List<HashMap> list = (List<HashMap>) sqlSession.selectList(
				"Group.findGroupListByOrder", param);
		List<HashMap> list1 = (List<HashMap>) sqlSession.selectList(
				"Group.findGroupListNoOrder", param);
		list.addAll(list1);
		return list;
	}

	@Override
	public Integer findcontactsCount(Group group) {
		return (Integer) sqlSession.selectOne("Contacts.findcontactsCount",
				group);

	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	// 一个只读事务，可以提高效率。
	@Override
	public Object findcontactsList(Group group) {
		Object obj = sqlSession.selectList("Contacts.findcontactsList", group);
		return obj;
	}

	@Override
	public Group updateUserGroupInfo(JSONObject param) {
		if (param.get("groupId") != null
				&& param.get("groupId").toString().trim().length() > 0) {
			sqlSession.update("Group.updateUserGroupInfo", param);

			return (Group) sqlSession.selectOne("Group.findUserGroup", param);

		} else {
			sqlSession.insert("Group.insertUserGroupInfo", param);

			return (Group) sqlSession.selectOne("Group.findUserGroupInfo",
					param);

		}
	}

	@Override
	public void updateContactsInfo(JSONObject param) {
		if (param.get("contactsId") != null
				&& param.get("contactsId").toString().trim().length() > 0) {
			sqlSession.update("Contacts.updateContactsInfo", param);
		} else {
			sqlSession.insert("Contacts.insertContactsInfo", param);

		}
	}

	@Override
	public void deleteGroupData(JSONObject param) {
		if (param.get("groupId") != null
				&& param.get("groupId").toString().trim().length() > 0) {
			sqlSession.update("Contacts.updateGroupContactData", param);
			sqlSession.delete("Group.deleteGroupData", param);
		}

	}

	@Override
	public Group findGroupisDefaultList(JSONObject param) {
		return (Group) sqlSession.selectOne("Group.findGroupisDefaultList",
				param);

	}

	@Override
	public Object findMyContactsNum(JSONObject param) {
		Object count = sqlSession
				.selectOne("Contacts.findMyContactsNum", param);
		return count;
	}

	@Override
	public Object findSubContactsNum(JSONObject param) {
		String roleids = (String) sqlSession.selectOne(
				"Contacts.findSubRoleIdList", param);
		if (roleids == null || roleids.trim().length() == 0) {
			return 0;
		}
		Object count = sqlSession.selectOne("Contacts.findSubContactsNum",
				roleids);
		return count;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	@Override
	public List findMySubContactsList(JSONObject param) {
		String roleids = (String) sqlSession.selectOne(
				"Contacts.findSubRoleIdList", param);
		if (roleids == null || roleids.trim().length() == 0) {
			return Collections.EMPTY_LIST;
		}
		List<HashMap> list = (List<HashMap>) sqlSession.selectList(
				"User.findMySubContactsList", roleids);
		return list;
	}

	@Override
	public Object findUserGroup(JSONObject param) {
		return (Group) sqlSession.selectOne("Group.findUserGroup", param);
	}

	@Override
	public void updateGroupOrder(JSONObject param) {
		// TODO Auto-generated method stub
		Group group1 = (Group) sqlSession.selectOne("Group.findUserGroupById",param.getLong("groupId1"));
		Group group2 = (Group) sqlSession.selectOne("Group.findUserGroupById",param.getLong("groupId2"));
		if(group1!=null && group2!=null){
			Long groupId = group2.getGroupId();
			group2.setGroupId(group1.getGroupId());
			group1.setGroupId(groupId);
			sqlSession.update("Group.updateUserGroupOrderFieldByGroup",group1);
			sqlSession.update("Group.updateUserGroupOrderFieldByGroup",group2);
		}
		
	}

	@Override
	public List findComapnyUserAllList(JSONObject param) {
		List<HashMap> list = (List<HashMap>) sqlSession.selectList("User.findComapnyUserAllList", param);
		return list;
	}

}
