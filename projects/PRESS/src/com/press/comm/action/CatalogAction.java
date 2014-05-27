package com.press.comm.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.press.comm.action.CommAction;
import com.press.comm.pojo.Catalog;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;
import com.press.util.UUIDUtil;
import com.press.util.Utils;

public class CatalogAction extends CommAction<Catalog> {
	private static Logger logger = LoggerFactory.getLogger(CatalogAction.class);

	@Autowired
	private SqlMap sqlMap;
    
	private String cattyp;
	
	private String cattypnam;

	/**
	 * 转向首页
	 * 
	 * @return
	 */
	public String to_index() {
		cattypnam = Utils.getCattypname(cattyp, sqlMap);
		if(queryCondition == null){
			queryCondition = new Catalog();
		}
		queryCondition.setCattyp(cattyp);
		return Constants.TO_INDEX;
	}

	public void getListJSON(){
		Integer totalRecords = (Integer) sqlMap.selectOne("catalog.queryCount",queryCondition);
		if(totalRecords == null){
			totalRecords = 0;
		}
		doPage(totalRecords,queryCondition);
		tList = sqlMap.selectList("catalog.queryPageList",queryCondition);
	   sendJSON(totalRecords, tList);
	}
	
	/**
	 * 转向添加页面
	 * 
	 * @return
	 */
	public String to_add() {
		cattypnam = Utils.getCattypname(cattyp, sqlMap);
		addCondition = new Catalog();
		return Constants.TO_ADD;
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 */
	public String do_add() {
		addCondition.setCatid(UUIDUtil.getUUIDStr());
		sqlMap.insert("catalog.insertCatalog", addCondition);
		return Constants.REDIRECT_INDEX;
	}

	/**
	 * 转向修改页面
	 * 
	 * @return
	 */
	public String to_update() {
		cattypnam = Utils.getCattypname(cattyp, sqlMap);
		updateCondition = (Catalog) sqlMap.selectOne("catalog.queryCatalog", ids[0]);
		return Constants.TO_UPDATE;
	}


	/**
	 * 修改数据
	 * 
	 * @return
	 */
	public String do_update() {
		sqlMap.update("catalog.updateCatalog", updateCondition);
		return Constants.REDIRECT_INDEX;
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 */
	public String do_delete() {
		try {
			for (int i = 0; i < ids.length; i++) {
				sqlMap.delete("catalog.deleteCatalog", ids[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.REDIRECT_INDEX;
	}

	/**
	 * 转向查看页面
	 * 
	 * @return
	 */
	public String to_view() {
		viewCondition = (Catalog) sqlMap.selectOne("catalog.queryCatalog", ids[0]);
		return Constants.TO_VIEW;
	}


	public String getCattypnam() {
		return cattypnam;
	}

	public void setCattypnam(String cattypnam) {
		this.cattypnam = cattypnam;
	}

	public String getCattyp() {
		return cattyp;
	}

	public void setCattyp(String cattyp) {
		this.cattyp = cattyp;
	}
	

}
