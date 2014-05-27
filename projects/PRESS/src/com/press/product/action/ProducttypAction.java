package com.press.product.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.press.comm.action.CommAction;
import com.press.product.pojo.Producttyp;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;
import com.press.util.UUIDUtil;

public class ProducttypAction extends CommAction<Producttyp> {
	private static Logger logger = LoggerFactory.getLogger(ProducttypAction.class);
	@Autowired
	private SqlMap sqlMap;

    
	public String to_index() {
		if (queryCondition == null) {
			queryCondition = new Producttyp();
		}
		return Constants.TO_INDEX;
	}

	public void getListJSON() {
		Integer totalRecords = (Integer) sqlMap.selectOne(
				"producttyp.queryCount", queryCondition);
		if (totalRecords == null) {
			totalRecords = 0;
		}
		doPage(totalRecords, queryCondition);
		tList = sqlMap.selectList("producttyp.queryPageList", queryCondition);
		sendJSON(totalRecords, tList);
	}

	/**
	 * 转向添加页面
	 * 
	 * @return
	 */
	public String to_add() {
		addCondition = new Producttyp();
		return Constants.TO_ADD;
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 */
	public String do_add() {
		addCondition.setProtypid(UUIDUtil.getUUIDStr());
		sqlMap.insert("producttyp.insertProducttyp", addCondition);
		return Constants.REDIRECT_INDEX;
	}

	/**
	 * 转向修改页面
	 * 
	 * @return
	 */
	public String to_update() {
		updateCondition = (Producttyp) sqlMap.selectOne(
				"producttyp.queryProducttyp", ids[0]);
		return Constants.TO_UPDATE;
	}

	/**
	 * 修改数据
	 * 
	 * @return
	 */
	public String do_update() {
		sqlMap.update("producttyp.updateProducttyp", updateCondition);
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
				//删除产品类型
				sqlMap.delete("producttyp.deleteProducttyp", ids[i]);
				//删除产品类型与产品的关系
				sqlMap.delete("producttyp.deleteRelprotyp4", ids[i]);
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
		viewCondition = (Producttyp) sqlMap.selectOne(
				"producttyp.queryProducttyp", ids[0]);
		return Constants.TO_VIEW;
	}

}
