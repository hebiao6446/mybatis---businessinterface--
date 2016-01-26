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


import com.business.entity.Company;
import com.json.BaseBean;
import com.business.service.ColumnManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/column")
public class ColumnAction extends ActionSupport {

	private static Logger logger = Logger.getLogger(ColumnAction.class);
	private static final long serialVersionUID = 3754746194693099418L;
	@Resource
	private ColumnManager columnManager;
	private int rows, page;
	private Long muserId;
	private Long columnId;
	private String columnName;
	private String columnDescription;
	private Integer columnAuthority;
	private Integer columnLevel;
	private Long parentId;
	private Long editorId;
	private String editorTime;
	private Long examineId;
	private String examineTime;
	private Integer deleteFlag;
	private Integer publishStatus;
	private String ids;
	private Long id;
	private String table;
	private String reason;
	private Integer isDefault;
	private Integer isNeed;
	private String editorName;
	private String examineName;

	@Action(value="loadColumnList")
	public void loadColumnList() throws Exception {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		BaseBean bean = new BaseBean();
		try {
			List<Company> newsColumnList = columnManager.loadColumnList(rows, page,null,2);

			bean.setStatus(200);
			bean.setRows(newsColumnList);

		} catch (Exception e) {
			bean.setMsg(e.getLocalizedMessage());
			logger.info("/column/loadColumnList:"+e.getLocalizedMessage());
		}
		String json = JSONObject.fromObject(bean).toString();
		
		response.getOutputStream().write(json.getBytes("UTF-8"));
	}
	
	public ColumnManager getColumnManager() {
		return columnManager;
	}

	public void setColumnManager(ColumnManager columnManager) {
		this.columnManager = columnManager;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Long getMuserId() {
		return muserId;
	}

	public void setMuserId(Long muserId) {
		this.muserId = muserId;
	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDescription() {
		return columnDescription;
	}

	public void setColumnDescription(String columnDescription) {
		this.columnDescription = columnDescription;
	}

	public Integer getColumnAuthority() {
		return columnAuthority;
	}

	public void setColumnAuthority(Integer columnAuthority) {
		this.columnAuthority = columnAuthority;
	}

	public Integer getColumnLevel() {
		return columnLevel;
	}

	public void setColumnLevel(Integer columnLevel) {
		this.columnLevel = columnLevel;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getEditorId() {
		return editorId;
	}

	public void setEditorId(Long editorId) {
		this.editorId = editorId;
	}

	public String getEditorTime() {
		return editorTime;
	}

	public void setEditorTime(String editorTime) {
		this.editorTime = editorTime;
	}

	public Long getExamineId() {
		return examineId;
	}

	public void setExamineId(Long examineId) {
		this.examineId = examineId;
	}

	public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getIsNeed() {
		return isNeed;
	}

	public void setIsNeed(Integer isNeed) {
		this.isNeed = isNeed;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public String getExamineName() {
		return examineName;
	}

	public void setExamineName(String examineName) {
		this.examineName = examineName;
	}
}
