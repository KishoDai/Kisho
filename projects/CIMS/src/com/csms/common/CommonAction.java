package com.csms.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.csms.pojo.User;
import com.csms.sqlmap.SqlMap;
import com.csms.utils.Constants;
import com.opensymphony.xwork2.ActionContext;

@Scope("prototype")
public class CommonAction<Entity extends Page> {

	private static final String APPLICATION_JSON_CHARSET = "application/json;charset=UTF-8";

	private static final String APPLICATION_XML_CHARSET = "application/xml;charset=UTF-8";

	protected static final String TO_MAIN_PAGE = "toMainPage";

	protected static final String TO_ADD_PAGE = "toAddPage";

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	protected Entity queryEntity;
	protected Entity updateEntity;
	protected Entity addEntity;
	protected Entity operEntity;

	protected String order;// order : desc or asc
	protected String page;// which page
	protected String rows;// row count per page
	protected String sort;// column used for sort

	protected String[] ids;
	protected String requestJsonStr;

	@Resource
	protected SqlMap sqlMap;

	@Resource
	protected Map<String, String> infoConfig;

	@Resource
	protected Map<String, String> sysInfo;

	public String toMainPage() {
		return TO_MAIN_PAGE;
	}

	public String toAddPage() {
		return TO_ADD_PAGE;
	}

	protected String getParameter(String key) {
		return ServletActionContext.getRequest().getParameter(key);
	}

	protected String[] getParameterValues(String key) {
		return ServletActionContext.getRequest().getParameterValues(key);
	}

	protected User getUser() {
		return (User) ActionContext.getContext().getSession()
				.get(Constants.LOGIN_USER);
	}

	protected void writeToXml(String xml) {
		ServletActionContext.getResponse().setContentType(
				APPLICATION_XML_CHARSET);
		write(xml);
	}

	protected void writeToJson(Object obj) {
		ServletActionContext.getResponse().setContentType(
				APPLICATION_JSON_CHARSET);
		write(new JsonSerializer().serialize(obj).toString());
	}

	protected void write(String str) {
		HttpServletResponse response = ServletActionContext.getResponse();
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(str);
			log.debug("write string : " + str);
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	protected void doPage(Integer totalRecords, Page t) {
		t.setOrder(order);
		t.setSort(sort);
		t.setLimitcount(10);
		t.setStartnum(0);
		if (totalRecords != null && totalRecords > 0) {
			if (StringUtils.isNotBlank(rows)) {
				t.setLimitcount(Integer.valueOf(rows));
			}
			if (StringUtils.isNotBlank(page)) {
				Integer _page = Integer.valueOf(page);
				if (_page != null && _page > 0) {
					int temp = totalRecords % t.getLimitcount();
					temp = (temp == 0 ? (totalRecords / t.getLimitcount())
							: ((totalRecords / t.getLimitcount()) + 1));
					if (_page > temp) {
						_page = temp;
					}
					temp = (_page - 1) * t.getLimitcount();
					t.setStartnum(temp);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void doPageList(String queryCountSql, String queryPageListSql,
			Entity queryEntity) {
		Integer totalRecords = (Integer) sqlMap.selectOne(queryCountSql,
				queryEntity);
		if (totalRecords == null) {
			totalRecords = 0;
		}
		doPage(totalRecords, queryEntity);
		List<Object> list = sqlMap.selectList(queryPageListSql, queryEntity);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalRecords);
		map.put("rows", list);
		writeToJson((String) new JsonSerializer().serialize(map));
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getRequestJsonStr() {
		return requestJsonStr;
	}

	public void setRequestJsonStr(String requestJsonStr) {
		this.requestJsonStr = requestJsonStr;
	}

	public SqlMap getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(SqlMap sqlMap) {
		this.sqlMap = sqlMap;
	}

	public Entity getQueryEntity() {
		return queryEntity;
	}

	public void setQueryEntity(Entity queryEntity) {
		this.queryEntity = queryEntity;
	}

	public Entity getUpdateEntity() {
		return updateEntity;
	}

	public void setUpdateEntity(Entity updateEntity) {
		this.updateEntity = updateEntity;
	}

	public Entity getAddEntity() {
		return addEntity;
	}

	public void setAddEntity(Entity addEntity) {
		this.addEntity = addEntity;
	}

	public Entity getOperEntity() {
		return operEntity;
	}

	public void setOperEntity(Entity operEntity) {
		this.operEntity = operEntity;
	}

	public String getText(String name) {
		return infoConfig.get(name);
	}

}
