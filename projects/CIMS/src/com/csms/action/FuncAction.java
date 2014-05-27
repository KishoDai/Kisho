package com.csms.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.Func;
import com.csms.service.FuncTreeService;

@Component("funcAction")
public class FuncAction extends CommonAction<Func> {

	private static final long serialVersionUID = -5370581958713397265L;

	@Resource
	private FuncTreeService funcTreeService;

	public String toAddFuncGroupPage() {
		return "toAddFuncGroupPage";
	}

	public String toAddFuncPage() {
		return "toAddFuncPage";
	}

	public String toModifyFuncPage() {
		updateEntity = getFuncByFuncId();
		return "toModifyFuncPage";
	}

	@Transactional
	public void move() {
		Map<String, Object> moveMap = new HashMap<String, Object>();
		moveMap.put("target_fn_id", getParameter("target_fn_id"));
		moveMap.put("source_fn_id", getParameter("source_fn_id"));
		sqlMap.update("FuncAction.updateMove", moveMap);
		writeToJson(new JSON("0", getText("FuncAction_move_success")));
	}

	public void loadJsonByFuncId() {
		writeToJson(funcTreeService.getJsonByFuncGroupId(getParameter("id"),
				true));
	}

	private Func getFuncByFuncId() {
		return (Func) sqlMap.selectOne("FuncAction.queryFuncByFuncId",
				operEntity.getFn_id());
	}

	public String toModifyFuncGroupPage() {
		updateEntity = getFuncByFuncId();
		return "toModifyFuncGroupPage";
	}

	public void queryFuncGroupWithoutLeafs() {
		writeToJson(funcTreeService.getJsonByFuncGroupIdWithoutLeafs(
				getParameter("id"), true));
	}

	@Transactional
	public void addEntity() {
		Func func = (Func) sqlMap.selectOne("FuncAction.queryFuncByFuncId",
				addEntity.getFn_id());
		if (func != null) {
			writeToJson(new JSON("1", getText("FuncAction_addEntity_failure")));
		} else {
			sqlMap.insert("FuncAction.insertFunc", addEntity);
		}
		writeToJson(new JSON("0", getText("FuncAction_addEntity_success")));
	}

	@Transactional
	public void updateEntity() {
		sqlMap.update("FuncAction.updateFunc", updateEntity);
		writeToJson(new JSON("0", getText("FuncAction_updateEntity_success")));
	}

	@Transactional
	public void removeFunc() {
		sqlMap.delete("FuncAction.deleteFuncByFuncId", operEntity.getFn_id());
		writeToJson(new JSON("0", getText("FuncAction_removeFunc_success")));
	}

	@Transactional
	public void removeFuncGroup() {
		List<String> needRemoveFuncIdList = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> funcList = sqlMap
				.selectList("FuncAction.queryFunc");
		Map<String, List<String>> parentIdAnFuncIdRel = getParentIdAndFuncIdRel(funcList);
		getNeedRemoveFuncId(needRemoveFuncIdList, operEntity.getFn_id(),
				parentIdAnFuncIdRel);
		Map<String, Object> needRemoveFuncMap = new HashMap<String, Object>();
		needRemoveFuncMap.put("needDeleteFuncIdList", needRemoveFuncIdList);
		sqlMap.delete("FuncAction.deleteFuncByFuncIds", needRemoveFuncMap);
		writeToJson(new JSON("0", getText("FuncAction_removeFuncGroup_success")));
	}

	private void getNeedRemoveFuncId(List<String> needRemoveDishIdList,
			String dhId, Map<String, List<String>> parentIdAnDishIdRel) {
		needRemoveDishIdList.add(dhId);
		List<String> dishIds = parentIdAnDishIdRel.get(dhId);
		if (!CollectionUtils.isEmpty(dishIds)) {
			for (String dishId : parentIdAnDishIdRel.get(dhId)) {
				if (parentIdAnDishIdRel.containsKey(dishId)) {
					getNeedRemoveFuncId(needRemoveDishIdList, dishId,
							parentIdAnDishIdRel);
				} else {
					needRemoveDishIdList.add(dishId);
				}
			}
		}
	}

	private Map<String, List<String>> getParentIdAndFuncIdRel(
			List<Map<String, Object>> dishList) {
		Map<String, List<String>> parentIdAndDishIdRel = new HashMap<String, List<String>>();
		for (Map<String, Object> dishMap : dishList) {
			final String dishId = (String) dishMap.get("FN_ID");
			String parentId = (String) dishMap.get("FN_GROUPID");
			if (parentIdAndDishIdRel.containsKey(parentId)) {
				parentIdAndDishIdRel.get(parentId).add(dishId);
			} else {
				parentIdAndDishIdRel.put(parentId, new ArrayList<String>() {
					
					private static final long serialVersionUID = 2473142660636247430L;

					{
						add(dishId);
					}
				});
			}
		}
		return parentIdAndDishIdRel;
	}

}
