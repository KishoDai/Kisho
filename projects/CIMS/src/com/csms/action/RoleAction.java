package com.csms.action;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.sf.sojo.interchange.json.JsonSerializer;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.Role;
import com.csms.pojo.RoleFuncRel;

@Component("roleAction")
public class RoleAction extends CommonAction<Role> {

	private static final long serialVersionUID = -4105834716830178706L;

	private RoleFuncRel addRoleFuncRel;
	private RoleFuncRel operRoleFuncRel;

	public String toRoleFuncPage() {
		return "toRoleFuncPage";
	}

	@Transactional
	public void addRoleFuncRel() {
		RoleFuncRel roleFuncRel = (RoleFuncRel) sqlMap.selectOne(
				"RoleAction.queryRoleFuncRelByRoleIdAndFuncId", addRoleFuncRel);
		if (roleFuncRel == null) {
			sqlMap.insert("RoleAction.addRoleFuncRel", addRoleFuncRel);
		}
	}

	@Transactional
	public void delRoleFuncRel() {
		sqlMap.delete("RoleAction.delRoleFuncRel", operRoleFuncRel);
	}

	public void queryJson4RoleFuncRelByRoleId() {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> roleFuncRelList = sqlMap.selectList(
				"RoleAction.queryRoleFuncRelByRoleId", operRoleFuncRel);
		writeToJson((String) new JsonSerializer().serialize(roleFuncRelList));
	}

	public String toUpdateRolePage() {
		updateEntity = (Role) sqlMap.selectOne("RoleAction.queryRoleByRoleId",
				updateEntity.getRe_id());
		return "toUpdateRolePage";
	}

	@Transactional
	public void deleteRole() {
		sqlMap.delete("RoleAction.deleteRoleByRoleId", operEntity.getRe_id());
		writeToJson(new JSON("0", "删除成功!"));
	}

	@Transactional
	public void addRole() {
		Role role = (Role) sqlMap.selectOne("RoleAction.queryRoleByRoleId",
				addEntity.getRe_id());
		if (role != null) {
			writeToJson(new JSON("0", "角色编号已存在!"));
		} else {
			sqlMap.insert("RoleAction.addRole", addEntity);
			writeToJson(new JSON("0", "添加角色成功！"));
		}
	}

	@Transactional
	public void updateRole() {
		sqlMap.update("RoleAction.updateRole", updateEntity);
		writeToJson(new JSON("0", "修改角色成功!"));
	}

	public void queryPageList() {
		queryEntity = queryEntity == null ? new Role() : queryEntity;
		doPageList("RoleAction.queryRoleCount", "RoleAction.queryPageList",
				queryEntity);
	}

	public RoleFuncRel getAddRoleFuncRel() {
		return addRoleFuncRel;
	}

	public void setAddRoleFuncRel(RoleFuncRel addRoleFuncRel) {
		this.addRoleFuncRel = addRoleFuncRel;
	}

	public RoleFuncRel getOperRoleFuncRel() {
		return operRoleFuncRel;
	}

	public void setOperRoleFuncRel(RoleFuncRel operRoleFuncRel) {
		this.operRoleFuncRel = operRoleFuncRel;
	}

}
