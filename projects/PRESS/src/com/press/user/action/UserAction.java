package com.press.user.action;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.press.comm.action.CommAction;
import com.press.product.pojo.Product;
import com.press.sqlmap.SqlMap;
import com.press.user.pojo.User;
import com.press.util.Constants;
import com.press.util.DateUtil;
import com.press.util.Utils;

public class UserAction extends CommAction<User> {
	private static Logger logger = LoggerFactory.getLogger(UserAction.class);
	@Autowired
	SqlMap sqlMap;

	private String lvlOptions;
	private String isloginOptions;
	private String islogoutOptions;

	public String to_index() {
		if(queryCondition==null){
			queryCondition = new User();
		}
		return Constants.TO_INDEX;
	}
	
	public void getListJSON(){
		Integer totalRecords = (Integer) sqlMap.selectOne("user.queryCount",queryCondition);
		if(totalRecords == null){
			totalRecords = 0;
		}
		doPage(totalRecords,queryCondition);
		tList = sqlMap.selectList("user.queryPageList",queryCondition);
		if (tList != null) {
			for (User user : tList) {
				dealUser(user);
			}
		}
	  sendJSON(totalRecords, tList);
	}

	public String to_add() {
		lvlOptions = Utils.getParamOptions("user.lvl","",true ,sqlMap);
		isloginOptions = Utils.getParamOptions("common.select","",true ,sqlMap);
		islogoutOptions = Utils.getParamOptions("common.select","",true, sqlMap);
		addCondition = new User();
		return Constants.TO_ADD;
	}

	public String do_add() {
		addCondition.setRegtime(DateUtil.getCurrDate());
		sqlMap.insert("user.insertUser", addCondition);
		return Constants.REDIRECT_INDEX;
	}

	public String to_view() {
		viewCondition = (User) sqlMap.selectOne("user.queryUser", ids[0]);
		setOptions(viewCondition);
		return Constants.TO_VIEW;
	}

	private void setOptions(User user) {
		lvlOptions = Utils.getParamOptions("user.lvl", user.getLvl(),false,sqlMap);
		isloginOptions = Utils.getParamOptions("common.select", user.getIslogin(),false,sqlMap);
		islogoutOptions = Utils.getParamOptions("common.select", user.getIslogout(),false,sqlMap);
	}

	public String to_update() {
		updateCondition = (User) sqlMap.selectOne("user.queryUser", ids[0]);
		setOptions(updateCondition);
		return Constants.TO_UPDATE;
	}

	public String do_update() {
		sqlMap.update("user.updateUser", updateCondition);
		return Constants.REDIRECT_INDEX;
	}

	public String do_delete() {
		for (String id : ids) {
			User user = new User();
			user.setUserid(id);
			sqlMap.delete("user.deleteUser", user);
		}
		return Constants.REDIRECT_INDEX;
	}

	
	private void dealUser(User user) {
		if (StringUtils.isNotBlank(user.getRegtime())) {
			try {
				user.setRegtime(DateUtil.formateDate(user.getRegtime(),
						"yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				logger.error("格式化时间异常:" + e.getMessage(), e);
			}
		}
		if (StringUtils.isNotBlank(user.getLastlogintime())) {
			try {
				user.setLastlogintime(DateUtil.formateDate(
						user.getLastlogintime(), "yyyy-MM-dd HH:mm:ss"));
			} catch (ParseException e) {
				logger.error("格式化时间异常:" + e.getMessage(), e);
			}
		}
	}

	public String getLvlOptions() {
		return lvlOptions;
	}

	public void setLvlOptions(String lvlOptions) {
		this.lvlOptions = lvlOptions;
	}

	public String getIsloginOptions() {
		return isloginOptions;
	}

	public void setIsloginOptions(String isloginOptions) {
		this.isloginOptions = isloginOptions;
	}

	public String getIslogoutOptions() {
		return islogoutOptions;
	}

	public void setIslogoutOptions(String islogoutOptions) {
		this.islogoutOptions = islogoutOptions;
	}

	

}
