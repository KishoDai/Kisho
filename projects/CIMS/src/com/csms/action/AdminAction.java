package com.csms.action;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.User;

/**
 * This class is used for managing admin information, such as add,
 * delete,update,query operations.
 * 
 * @author HERO
 * 
 */
@Component("adminAction")
public class AdminAction extends CommonAction<User> {

	@Transactional
	public void addAdmin() {
		User user = (User) sqlMap.selectOne("AdminAction.queryAdminByUserId",
				addEntity.getUr_id());
		if (user != null) {
			writeToJson(new JSON("1", getText("AdminAction_addAdmin_failure")));
			return;
		}
		addEntity.setUr_password(sysInfo.get("initpassword"));
		sqlMap.insert("AdminAction.addAdmin", addEntity);
		writeToJson(new JSON("0", getText("AdminAction_addAdmin_success")));
	}

	@Transactional
	public void delAdmin() {
		sqlMap.delete("AdminAction.deleteAdminByUserId", operEntity.getUr_id());
		writeToJson(new JSON("0", getText("AdminAction_delAdmin_success")));
	}

	public String toUpdateAdminPage() {
		updateEntity = (User) sqlMap.selectOne(
				"AdminAction.queryAdminByUserId", updateEntity.getUr_id());
		return "toUpdateAdminPage";
	}

	@Transactional
	public void updateAdmin() {
		sqlMap.update("AdminAction.updateAdmin", updateEntity);
		writeToJson(new JSON("0", getText("AdminAction_updateAdmin_sucess")));
	}

	public void queryPageList() {
		queryEntity = queryEntity == null ? new User() : queryEntity;
		doPageList("AdminAction.queryAdminCount", "AdminAction.queryPageList",
				queryEntity);
	}

}
