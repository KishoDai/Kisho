package com.press.admin.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.press.admin.pojo.Admin;
import com.press.comm.JSON;
import com.press.comm.action.CommAction;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;
import com.press.util.MD5Util;

public class AdminAction extends CommAction {
	private static Logger logger = LoggerFactory.getLogger(AdminAction.class);
	@Autowired
	private SqlMap sqlMap;
	private Admin admin;
	private String password;
	private String newpassword;
	private String newpassword2;

	/**
	 * 转向首页
	 * 
	 * @return
	 */
	public String to_index() {
		String adminid = (String) ActionContext.getContext().getSession().get(Constants.ADMINID);
		admin = (Admin) sqlMap.selectOne("admin.queryAdmin", adminid);
		return Constants.TO_INDEX;
	}

	
	/**
	 * 修改数据
	 * 
	 * @return
	 */
	public void do_update() {
		sqlMap.update("admin.updateAdmin", admin);
		sendJSON(new JSON("1",""));
	}

	public void do_update_password() {
		String adminid = (String) ActionContext.getContext().getSession()
				.get(Constants.ADMINID);
		admin = (Admin) sqlMap.selectOne("admin.queryAdmin", adminid);
		logger.debug("原密码=" + admin.getPassword());
		logger.debug("password=" + password);
		logger.debug("newpassword=" + newpassword);
		logger.debug("newpassword2=" + newpassword2);
		if (!admin.getPassword().equals(MD5Util.getMD5(password))) {
			sendJSON(new JSON("1", "原密码输入不正确!"));
			return;
		}
		if (!newpassword.equals(newpassword2)) {
			sendJSON(new JSON("2", "两次新密码输入不一致!"));
			return;
		}
		Admin paramAdmin = new Admin();
		paramAdmin.setAdminid(adminid);
		paramAdmin.setPassword(MD5Util.getMD5(newpassword));
		sqlMap.update("admin.updateAdminPassword", paramAdmin);
		sendJSON(new JSON("0", "修改密码成功!"));
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getNewpassword2() {
		return newpassword2;
	}

	public void setNewpassword2(String newpassword2) {
		this.newpassword2 = newpassword2;
	}

}
