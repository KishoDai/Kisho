package com.csms.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.User;
import com.csms.pojo.UserRoleRel;
import com.csms.utils.MD5Util;

@Component("userAction")
public class UserAction extends CommonAction<User> {

	private static final long serialVersionUID = -8553804539113523015L;

	private UserRoleRel addUserRoleRel;
	private UserRoleRel operUserRoleRel;

	@Resource
	Map<String, String> sysInfo;

	@Transactional
	public void recoverInitPwd() {
		operEntity.setUr_password(MD5Util.getMD5(sysInfo.get("initpassword")));
		sqlMap.update("UserAction.recoverInitPwd", operEntity);
		writeToJson(new JSON("0", getText("UserAction_recoverInitPwd_success")));
	}

	public String toUserRolePage() {
		return "toUserRolePage";
	}

	@Transactional
	public void addUserRoleRel() {
		UserRoleRel userRoleRel = (UserRoleRel) sqlMap.selectOne(
				"UserAction.queryUserRoleRelByUserIdAndRoleId", addUserRoleRel);
		if (userRoleRel == null) {
			sqlMap.insert("UserAction.addUserRoleRel", addUserRoleRel);
		}
		writeToJson(new JSON("0", getText("UserAction_addUserRoleRel_success")));
	}

	@Transactional
	public void delUserRoleRel() {
		sqlMap.delete("UserAction.delUserRoleRel", operUserRoleRel);
		writeToJson(new JSON("0", getText("UserAction_delUserRoleRel_success")));
	}

	public void queryJson4UserRoleRelByUserId() {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> userRoleRelList = sqlMap.selectList(
				"UserAction.queryUserRoleRelByUserId", operUserRoleRel);
		writeToJson((String) new JsonSerializer().serialize(userRoleRelList));
	}

	@Transactional
	public String toUpdateUserPage() {
		updateEntity = (User) sqlMap.selectOne("UserAction.queryUserByUserId",
				updateEntity.getUr_id());
		return "toUpdateUserPage";
	}

	@Transactional
	public void delUser() {
		sqlMap.delete("UserAction.deleteUserByUserId", operEntity.getUr_id());
		writeToJson(new JSON("0", getText("UserAction_delUser_success")));
	}

	@Transactional
	public void addUser() {
		User user = (User) sqlMap.selectOne("UserAction.queryUserByUserId",
				addEntity.getUr_id());
		if (user != null) {
			writeToJson(new JSON("1", getText("UserAction_addUser_failure")));
		} else {
			addEntity
					.setUr_password(MD5Util.getMD5(sysInfo.get("initpassword")));
			sqlMap.insert("UserAction.addUser", addEntity);
			writeToJson(new JSON("0", getText("UserAction_addUser_success")));
		}
	}

	@Transactional
	public void updateUser() {
		sqlMap.update("UserAction.updateUser", updateEntity);
		writeToJson(new JSON("0", getText("UserAction_updateUser_success")));
	}

	public void queryPageList() {
		queryEntity = queryEntity == null ? new User() : queryEntity;
		doPageList("UserAction.queryUserCount", "UserAction.queryPageList",
				queryEntity);
	}

	@Transactional
	public void addUserRoleRels() {
		Map<String, Object> operMap = getBatchOperUserRoleRelMap();
		sqlMap.delete("UserAction.delUserRoleRels", operMap);
		sqlMap.insert("UserAction.addUserRoleRels", operMap);
		writeToJson(new JSON("0", getText("UserAction_addUserRoleRels_success")));
	}

	private Map<String, Object> getBatchOperUserRoleRelMap() {
		Map<String, Object> operMap = new HashMap<String, Object>();
		operMap.put("ur_id", getParameter("ur_id"));
		operMap.put("re_ids", getParameterValues("re_id"));
		return operMap;
	}

	@Transactional
	public void delUserRoleRels() {
		sqlMap.delete("UserAction.delUserRoleRels",
				getBatchOperUserRoleRelMap());
		writeToJson(new JSON("0", getText("UserAction_delUserRoleRels_success")));
	}

	public UserRoleRel getAddUserRoleRel() {
		return addUserRoleRel;
	}

	public void setAddUserRoleRel(UserRoleRel addUserRoleRel) {
		this.addUserRoleRel = addUserRoleRel;
	}

	public UserRoleRel getOperUserRoleRel() {
		return operUserRoleRel;
	}

	public void setOperUserRoleRel(UserRoleRel operUserRoleRel) {
		this.operUserRoleRel = operUserRoleRel;
	}

}
