package com.business.action;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.json.BaseBean;
import com.business.entity.Group;
import com.business.entity.User;
import com.business.service.ContactManager;
import com.business.service.UserManager;
import com.common.Commonparam;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/contact")
public class ContactAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(ContactAction.class);
	@Resource
	private ContactManager contactManager;
	@Resource
	private UserManager userManager;
	private String key, data;

	/**
	 * 联系人分组查询 联系人列表 groupId1,groupId2,userId
	 * 
	 * @throws Exception
	 */
	@Action(value = "updateGroupOrder")
	public void updateGroupOrder() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);

			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				contactManager.updateGroupOrder(param);
				bean.setStatus(200);

			} else {

				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("contact/findUserGroupList:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}

		String json = JSONObject.fromObject(bean,
				Commonparam.getJsonConfig("yyyy-MM-dd HH:mm:ss")).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 联系人分组查询 联系人列表
	 * 
	 * @throws Exception
	 */
	@Action(value = "findUserGroupList")
	public void findUserGroupList() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				List<Group> list = contactManager.findUserGroupList(param);
				for (Group group : list) {
					Integer count = contactManager.findcontactsCount(group);
					Object contactlist = contactManager.findcontactsList(group);
					group.setContactsCount(count);
					group.setContactsList(contactlist);
				}
				bean.setStatus(200);
				bean.setRows(list);
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("contact/findUserGroupList:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}

		String json = JSONObject.fromObject(bean,
				Commonparam.getJsonConfig("yyyy-MM-dd HH:mm:ss")).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 联系人分组查询列表
	 * 
	 * @throws Exception
	 */
	@Action(value = "findGroupList")
	public void findGroupList() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);

			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				List<Group> list = contactManager.findUserGroupList(param);

				bean.setStatus(200);
				bean.setRows(list);

			} else {

				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("contact/findGroupList:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 联系人 人数 数量 userId *
	 * 
	 * @throws Exception
	 */
	@Action(value = "findMyContactsNum")
	public void findMyContactsNum() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);

			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				Object myContactsNum = contactManager.findMyContactsNum(param);
				Object subContactsNum = contactManager
						.findSubContactsNum(param);

				HashMap result = new HashMap();

				result.put("myContactsNum", myContactsNum);
				result.put("subContactsNum", subContactsNum);

				bean.setStatus(200);
				bean.setRows(result);

			} else {

				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("contact/findMyContactsNum:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 我的下属 ———— 联系人 人数 列表 userId *
	 * 
	 * @throws Exception
	 */
	@Action(value = "findMySubContactsList")
	public void findMySubContactsList() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);

			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {

				List<HashMap> mySubContactslist = contactManager
						.findMySubContactsList(param);
				for (HashMap user : mySubContactslist) {
					JSONObject paramTemp = new JSONObject();
					paramTemp.put("userId", user.get("userId"));
					Object myContactsNum = contactManager
							.findMyContactsNum(paramTemp);
					user.put("subCount", myContactsNum);
				}

				bean.setStatus(200);
				bean.setRows(mySubContactslist);

			} else {

				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("contact/findMySubContactsList:param=" + data
					+ ",error=" + e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 联系人分组 编辑 添加，修改
	 * 
	 * @throws Exception
	 */
	@Action(value = "updateUserGroupInfo")
	public void updateUserGroupInfo() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();

		try {

			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				User user = userManager.userLogin(param);
				Long userId = param.getLong("userId");

				if (user != null && user.getUserId() == userId)

				{
					param.put("updateTime", Commonparam.Date2Str());

					Group g = contactManager.updateUserGroupInfo(param);

					bean.setStatus(200);
					bean.setRows(g);

				}
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}

		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("contact/updateUserGroupInfo:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}

		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 联系人————————编辑 添加，修改
	 * 
	 * @throws Exception
	 */
	@Action(value = "updateContactsInfo")
	public void updateContactsInfo() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		BaseBean bean = new BaseBean();

		try {

			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				User user = userManager.userLogin(param);
				Long userId = param.getLong("userId");

				if (user != null && user.getUserId() == userId)

				{
					param.put("updateTime", Commonparam.Date2Str());

					contactManager.updateContactsInfo(param);

					bean.setStatus(200);

				}
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}

		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("contact/updateContactsInfo:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}

		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 删除分组
	 * 
	 * @throws Exception
	 */
	@Action(value = "deleteGroupData")
	public void deleteGroupData() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);

			JSONObject param = JSONObject.fromObject(data);

			if (keyparam.equals(key)) {

				Group group = contactManager.findGroupisDefaultList(param);
				if (param.getLong("groupId") != 0
						&& param.getLong("groupId") != group.getGroupId()) {
					param.put("defaultGroupId", group.getGroupId());
					User user = userManager.userLogin(param);
					Long userId = param.getLong("userId");

					if (user != null && user.getUserId() == userId) {
						param.put("updateTime", Commonparam.Date2Str());

						contactManager.deleteGroupData(param);

						bean.setStatus(200);
					}

				}

			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}

		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("contact/deleteGroupData:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}

		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 查看公司所有人————用户列表 companyId,userId
	 * 
	 * @throws Exception
	 */
	@Action(value = "findComapnyUserAllList")
	public void findComapnyUserAllList() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);

			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {

				@SuppressWarnings("unchecked")
				List<User> list = contactManager.findComapnyUserAllList(param);
				bean.setStatus(200);
				bean.setRows(list);

			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("contact/findComapnyUserAllList:param=" + data
					+ ",error=" + e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean, Commonparam.getJsonConfig())
				.toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

}
