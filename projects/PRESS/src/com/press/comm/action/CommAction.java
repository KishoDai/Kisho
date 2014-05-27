package com.press.comm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;
import com.press.comm.JSON;
import com.press.comm.pojo.Page;
import com.press.product.pojo.Product;
import com.press.util.Constants;
import com.press.util.JSONUtil;

public class CommAction<T extends Page> extends ActionSupport {
	private static final long serialVersionUID = 1234567890L;
	private static Logger logger = LoggerFactory.getLogger(CommAction.class);
	private String module;
	private String submodule;
	protected Map<String, Object> paramMap = new HashMap<String, Object>();
	protected String id;
	protected String[] ids;
	protected String submenuid;
	
	protected String order;//升降序
	protected String page;//第几页
	protected String rows ;//每页显示记录数
	protected String sort;//排序字段
	
	protected T queryCondition;//查询条件
	protected T viewCondition;
	protected T updateCondition;
	protected T addCondition;
	protected List<T> tList;
    /**
     * 设置查询条件
     */
	public void setQueryCondition(){
		sendJSON(new JSON("1", ""));
	}
	
	/**
	 * 转向通用iframe
	 */
	public String to_iframe(){
		return Constants.TO_IFRAME;
	}
	
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getSubmodule() {
		return submodule;
	}

	public void setSubmodule(String submodule) {
		this.submodule = submodule;
	}

	protected void sendJSON(JSON json) {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String jsonStr = "{" + json + "}";
			logger.debug("jsonStr="+jsonStr);
			writer.write(jsonStr);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	protected void sendJSON(int totalRecords,List<T> list){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = null;
		try {
			StringBuffer jsonSb = new StringBuffer();
			jsonSb.append("{\"total\":");
			jsonSb.append(totalRecords);
			jsonSb.append(",\"rows\":");
			jsonSb.append(JSONUtil.getJsonStr4List(list));
			jsonSb.append("}");
			writer = response.getWriter();
			logger.debug("jsonStr="+jsonSb.toString());
			writer.write(jsonSb.toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	protected void sendJSON(List list,int totalRecords){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = null;
		try {
			StringBuffer jsonSb = new StringBuffer();
			jsonSb.append("{\"total\":");
			jsonSb.append(totalRecords);
			jsonSb.append(",\"rows\":");
			jsonSb.append(JSONUtil.getJsonStr4List(list));
			jsonSb.append("}");
			writer = response.getWriter();
			logger.debug("jsonStr="+jsonSb.toString());
			writer.write(jsonSb.toString());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	protected void sendJSON(String jsonStr){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			logger.debug("jsonStr="+jsonStr);
			writer.write(jsonStr);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	protected void sendJSON(List<T> list){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			String jsonStr = JSONUtil.getJsonStr4List(list);
			logger.debug("jsonStr="+jsonStr);
			writer.write(jsonStr);
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
  protected void sendScript(String script){
	  HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write("<script type=\"text/javascript\">"+script+"</script>");
		} catch (IOException e) {
			logger.error(e.getMessage());
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
  }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getSubmenuid() {
		return submenuid;
	}

	public void setSubmenuid(String submenuid) {
		this.submenuid = submenuid;
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
	
	/**
	 * 处理分页
	 * @param totalRecords
	 * @param t
	 */
	public void doPage(Integer totalRecords ,T t){
		t.setOrder(order);
		t.setSort(sort);
		t.setLimitcount(10);
		t.setStartnum(0);
		if(totalRecords != null && totalRecords >0 ){
			if(StringUtils.isNotBlank(rows)){
				t.setLimitcount(Integer.valueOf(rows));
			}
			if(StringUtils.isNotBlank(page)){
				Integer _page = Integer.valueOf(page);
				if(_page!=null && _page>0){
					int temp = totalRecords%t.getLimitcount();
				    temp = (temp == 0 ? (totalRecords/t.getLimitcount()): ((totalRecords/t.getLimitcount())+1));
				    if(_page>temp){
				    	_page = temp;
				    }
				    temp = (_page-1)*t.getLimitcount();
				    t.setStartnum(temp);
				}
			}
		}
	}

	public T getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(T queryCondition) {
		this.queryCondition = queryCondition;
	}

	public T getUpdateCondition() {
		return updateCondition;
	}

	public void setUpdateCondition(T updateCondition) {
		this.updateCondition = updateCondition;
	}

	public T getAddCondition() {
		return addCondition;
	}

	public void setAddCondition(T addCondition) {
		this.addCondition = addCondition;
	}

	public T getViewCondition() {
		return viewCondition;
	}

	public void setViewCondition(T viewCondition) {
		this.viewCondition = viewCondition;
	}

	public List<T> gettList() {
		return tList;
	}

	public void settList(List<T> tList) {
		this.tList = tList;
	}

}
