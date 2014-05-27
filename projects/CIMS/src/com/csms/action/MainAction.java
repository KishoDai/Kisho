package com.csms.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.User;
import com.csms.utils.Constants;
import com.csms.utils.MD5Util;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("rawtypes")
@Component("mainAction")
public class MainAction extends CommonAction {

	private static final long serialVersionUID = 4592358731186487954L;

	private String password;
	private String newpassword;
	private String newpassword2;

	public String main() {
		return "toMainPage";
	}

	@Transactional
	public void modifyPassword() {
		if (StringUtils.isBlank(password) || StringUtils.isBlank(newpassword)
				|| StringUtils.isBlank(newpassword2)) {
			writeToJson(new JSON("1", "新旧密码必须输入"));
			return;
		}
		final User loginUser = (User) ActionContext.getContext().getSession()
				.get(Constants.LOGIN_USER);
		if (!MD5Util.getMD5(password).equals(loginUser.getUr_password())) {
			writeToJson(new JSON("2", "原始密码输入错误"));
			return;
		}
		if (!newpassword.equals(newpassword2)) {
			writeToJson(new JSON("3", "两次密码输入不一致"));
			return;
		}
		if (password.equals(newpassword)) {
			writeToJson(new JSON("4", "新密码和旧密码不能相同"));
			return;
		}
		Map<String, Object> updatePasswordMap = new HashMap<String, Object>() {
			
			private static final long serialVersionUID = 7705450774681209065L;

			{
				put("ur_id", loginUser.getUr_id());
				put("ur_password", MD5Util.getMD5(newpassword));
			}
		};
		sqlMap.update("MainAction.updatePassword", updatePasswordMap);
		writeToJson(new JSON("0", "密码修改成功"));
		loginUser.setUr_password(MD5Util.getMD5(newpassword));
	}

	public void exit() {
		ServletActionContext.getRequest().getSession()
				.setMaxInactiveInterval(0);
		ServletActionContext.getRequest().getSession().invalidate();
		writeToJson(new JSON("1", ""));
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
