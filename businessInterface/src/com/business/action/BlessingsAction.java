package com.business.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import com.business.entity.Blessings;
import com.business.entity.BlessingsType;
import com.json.BaseBean;
import com.business.service.BlessingsManager;
import com.common.Commonparam;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/blessings")
public class BlessingsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8491620621758148693L;
	private static Logger logger = Logger.getLogger(BlessingsAction.class);
	@Resource
	private BlessingsManager blessingsManager;
	private String key,data;
	
	
	
	
	/**
	 * 节日祝福分类 ————————————接口
	 * 
	 * @throws Exception
	 */
	@Action(value = "findBlessingsList")
	public void findBlessingsList() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);

			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				List<BlessingsType> list = blessingsManager.findBlessingsTypeList(param);

				for (BlessingsType blessingsType : list) {

					Integer count = blessingsManager.findBlessingsListCount(blessingsType);
					Object blessingslist = blessingsManager.findBlessingsList(blessingsType);
		
					blessingsType.setBlessingsCount(count);
					blessingsType.setBlessingsList(blessingslist);
				}

				bean.setStatus(200);
				bean.setRows(list);

			} else {

				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("blessings/findBlessingsList:param=" + data + ",error="
					+ e.getLocalizedMessage());
		}
		
		String json = JSONObject.fromObject(bean,
				Commonparam.getJsonConfig("yyyy-MM-dd HH:mm:ss")).toString();
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}
	
	
	
	
	/**
	 * 节日祝福首页 ————————————接口
	 * 
	 * @throws Exception
	 */
	@Action(value = "findBlessingIndexsList")
	public void findBlessingIndexsList() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			String keyparam = Commonparam.Md5String(data);

			JSONObject param = JSONObject.fromObject(data);
			if (keyparam.equals(key)) {
				List<Blessings> list = blessingsManager.findBlessingIndexsList(param);

				bean.setStatus(200);
				bean.setRows(list);
			
			}else{

				bean.setStatus(400);
				bean.setMsg(Commonparam.error_param_msg);
			}
		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("blessings/findBlessingIndexsList:param=" + data + ",error="
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

	public void setBlessingsManager(BlessingsManager blessingsManager) {
		this.blessingsManager = blessingsManager;
	}

	public BlessingsManager getBlessingsManager() {
		return blessingsManager;
	}
	
	
}
