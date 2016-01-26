package com.business.action;

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
import com.business.entity.Things;
import com.business.entity.ThingsThingsFinish;
import com.business.service.ThingsManager;
import com.common.Commonparam;
import com.json.BaseBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/things")
public class ThingsAction extends ActionSupport {
	private static final long serialVersionUID = 593773289291341653L;

	private static Logger logger = Logger.getLogger(ThingsAction.class);

	private String key, data;
	@Resource
	ThingsManager thingsManager;

	@Action(value = "findAllThings")
	public void findAllThings() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				List<ThingsThingsFinish> list = thingsManager
						.findAllThings(param);
				bean.setRows(list);
				bean.setStatus(200);
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("sign/findTodaySign:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean, Commonparam.getJsonConfig())
				.toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	@Action(value = "updateNewThings")
	public void updateNewThings() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				thingsManager.updateNewThings(param);
				bean.setStatus(200);
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

	private void findThingsInfo() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				Things things = thingsManager.findThingsInfo(param);
				bean.setRows(things);
				bean.setStatus(200);
			} else {
				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("sign/findTodaySign:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean, Commonparam.getJsonConfig())
				.toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}

	@Action(value = "finishThings")
	public void finishThings() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				Things t = thingsManager.findThingsminInfo(param);
				if (t.getEndTime().before(new Date())) {
					bean.setMsg("已过期！");
					bean.setStatus(400);
				} else {
					param.put("updateTime",Commonparam.Date2Str());					
					thingsManager.finishThings(param);
					bean.setStatus(200);
				}
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

	@Action(value = "deleteThings")
	public void deleteThings() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);
			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				thingsManager.deleteThings(param);
				bean.setStatus(200);
				bean.setMsg("删除成功！");
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
