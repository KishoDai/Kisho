package com.press.product.action;


import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.press.comm.action.CommAction;
import com.press.product.pojo.Product2;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;
import com.press.util.Utils;

public class Product2Action extends CommAction<Product2> {
	private static Logger logger = LoggerFactory.getLogger(Product2Action.class);
	@Autowired
	private SqlMap sqlMap;
	private String protypOptions ;
	
	private String[] ids;
   
	public String to_index(){
		if(queryCondition == null){
			queryCondition = new Product2();
		}
		protypOptions = Utils.getProtypOptions(sqlMap, queryCondition.getProtypid(), true, "全部");
		return Constants.TO_INDEX;
	}
	
	public void getListJSON(){
		Integer totalRecords = (Integer) sqlMap.selectOne("producttyp.queryCount2",queryCondition);
		if(totalRecords == null){
			totalRecords = 0;
		}
		doPage(totalRecords,queryCondition);
		tList = sqlMap.selectList("producttyp.queryPageList2",queryCondition);
		if (tList != null) {
			for (Product2 product2 : tList) {
				try {
					Utils.dealTime4Product(product2);
				} catch (ParseException e) {
					logger.error("转换时间异常:" + e.getMessage(), e);
				}
			}
		}
	  sendJSON(totalRecords, tList);
	}
	
	public String do_delete(){
		if(ids!=null){
		  for(String id : ids){
			  //删除产品与产品类型的关系表
			  sqlMap.delete("producttyp.deleteRelprotyp", id);
		  }
		}
		return Constants.REDIRECT_INDEX;
	}

	public String getProtypOptions() {
		return protypOptions;
	}

	public void setProtypOptions(String protypOptions) {
		this.protypOptions = protypOptions;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	
}
