package com.business.action;

import java.sql.Time;
import java.util.Date;
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

import com.business.entity.AttendDescript;
import com.business.entity.Attendance;
import com.business.entity.Company;
import com.business.entity.User;
import com.business.service.SignManager;
import com.business.service.UserManager;
import com.common.Commonparam;
import com.json.BaseBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/sign")
public class SignAction extends ActionSupport {
	private static final long serialVersionUID = -4937888148908083129L;

	private static Logger logger = Logger.getLogger(SignAction.class);

	private String key, data;
	@Resource
	private SignManager signManager;
	@Resource
	private UserManager userManager;

	/**
	 * 考勤ppt：第1页
	 * 
	 * @throws Exception
	 */
	@Action(value = "findTodaySign")
	public void findTodaySign() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				param.put("dayTime",
						new java.sql.Date(new Date().getTime()).toString());
				Attendance attendance = signManager.findTodaySign(param);

				HashMap<String, String> map = new HashMap<String, String>();
				map.put("in", "F");
				map.put("out", "F");
				if (attendance != null) {
					if (attendance.getInTime() != null) {
						map.put("in", "T");
					}
					if (attendance.getOutTime() != null) {
						map.put("out", "T");
					}
				}
				bean.setStatus(200);
				bean.setRows(map);
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("sign/findTodaySign:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 考勤ppt：第1页，考勤
	 * 
	 * @throws Exception
	 */
	@Action(value = "updateSign")
	public void updateSign() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			User user = userManager.findUserInfo(param);
			if (keyparam.equals(key)) {
				Time intime = new java.sql.Time(new Date().getTime());
				param.put("time", intime.toString());
				param.put("dayTime",
						new java.sql.Date(new Date().getTime()).toString());
				Company company = signManager.findCompanyInfo(user);
				Attendance attendance = signManager.findTodaySign(param);
				String type = param.getString("type");
				if (type != null && "D".equals(type)
						&& attendance.getInTime() == null) {
					if (intime.before(company.getReportWork())) {
						param.put("status", 1);
					} else {
						param.put("status", -1);
					}
					signManager.insertSign(param);
					bean.setStatus(200);
					bean.setMsg("签到成功！");
				} else if (type != null && "T".equals(type)
						&& attendance != null
						&& attendance.getAttendanceId() > 0) {
					if (intime.before(company.getOutWork())) {
						if (attendance.getStatus() == 1) {
							param.put("status", 3);
						} else if (attendance.getStatus() == -1) {
							param.put("status", 2);
						}
					} else {
						param.put("status", attendance.getStatus());
					}
					signManager.updateSign(param);
					bean.setStatus(200);
					bean.setMsg("签退成功！");
				} else {
					bean.setMsg("canshucuowu!");
					bean.setStatus(400);
				}
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("sign/updateSign:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	/**
	 * 考勤ppt：第2页
	 * 
	 * @throws Exception
	 */
	@Action(value = "insertAttendDescript")
	public void insertAttendDescript() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				param.put("dayTime",
						new java.sql.Date(new Date().getTime()).toString());
				param.put("updateTime", Commonparam.Date2Str());
				signManager.insertAttendDescript(param);
				bean.setStatus(200);
				bean.setMsg("添加成功！");
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("sign/signContent:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	@Action(value = "findHistoryAttendDescript")
	public void findHistoryAttendDescript() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);

			if (keyparam.equals(key)) {
				Integer page = param.getInt("page");
				Integer size = param.getInt("size");
				param.put("index", (page - 1) * size);
				List<AttendDescript> list = signManager
						.findHistoryAttendDescript(param);
				bean.setRows(list);
				bean.setStatus(200);
				bean.setTotal(signManager.findHistoryAttendDescriptCount(param));

			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("sign/signContent:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean, Commonparam.getJsonConfig())
				.toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	@Action(value = "findHistorySign")
	public void findHistorySign() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {

				List<Attendance> list = signManager.findHistorySign(param);
				
				bean.setStatus(200);
				bean.setRows(list);
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("sign/signContent:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean, Commonparam.getJsonConfig())
				.toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	@Action(value = "findManagedSign")
	public void findManagedSign() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				if (param.get("dateTime") == null
						|| "".equals(param.getString("dateTime"))) {
					long dateTime = new Date().getTime() - 24 * 60 * 60 * 1000L;
					param.put("dateTime",
							new java.sql.Date(dateTime).toString());
				}

				User user = userManager.findUserInfo(param);
				String roleIdList = userManager.findUserChidrenRole(user
						.getRoleId());
				if (roleIdList != null && !"".equals(roleIdList.trim())) {
					param.put("roleIdList", roleIdList);

					if (param.get("page") != null) {
						int page = param.getInt("page");
						int size = param.getInt("size");
						param.put("page", page);
						param.put("size", size);
						param.put("index", (page - 1) * size);
					}
					List<Attendance> list = signManager.findManagedSign(param);
					if (list != null && list.size() > 0) {
						for (Attendance a : list) {
							if (a.getInTime() == null) {
								a.setStatus(6);// 没有签到与签退
							} else {
								if (a.getOutTime() == null) {
									if (a.getStatus() == 1) {
										a.setStatus(5);// 迟到,没有签退
									} else if (a.getStatus() == -1) {
										a.setStatus(4);// 没有签退
									}
								}
							}
						}
					}
					bean.setRows(list);
					bean.setStatus(200);
					bean.setTotal(signManager.findManagedSignCount(param));
				} else {
					bean.setStatus(400);
					bean.setMsg("无下属人员！");
				}
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("sign/signContent:param=" + data + ",error="
					+ e.getLocalizedMessage());
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

}
