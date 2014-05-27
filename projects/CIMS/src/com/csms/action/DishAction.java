package com.csms.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.Dish;
import com.csms.service.DishTreeService;

@Component("dishAction")
public class DishAction extends CommonAction<Dish> {

	private static final long serialVersionUID = 9064196753394896649L;

	@Resource
	private DishTreeService dishTreeService;

	private String sp_id;

	@Transactional
	public void move() {
		Map<String, Object> moveMap = new HashMap<String, Object>();
		moveMap.put("target_dh_id", getParameter("target_dh_id"));
		moveMap.put("source_dh_id", getParameter("source_dh_id"));
		moveMap.put("shop_id", getParameter("shop_id"));
		sqlMap.update("DishAction.updateMove", moveMap);
		writeToJson(new JSON("0", getText("DishAction_move_success")));
	}

	public void loadJsonByDishId() {
		writeToJson(dishTreeService.getJsonByParentId(getParameter("id"), true));
	}

	public String toModifyDishPage() {
		updateEntity = (Dish) sqlMap.selectOne(
				"DishAction.queryDishByDishIdAndShopId", operEntity);
		return "toModifyDishPage";
	}

	public String toModifyDishSortPage() {
		updateEntity = (Dish) sqlMap.selectOne(
				"DishAction.queryDishByDishIdAndShopId", operEntity);
		return "toModifyDishSortPage";
	}

	@Transactional
	public String toAddDishSortPage() {
		return "toAddDishSortPage";
	}

	@Transactional
	public void removeDish() {
		sqlMap.delete("DishAction.deleteDishByDishId", operEntity.getDh_id());
		writeToJson(new JSON("0", getText("DishAction_removeDish_success")));
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void removeDishSort() {
		List<String> needRemoveDishIdList = new ArrayList<String>();
		List<Map<String, Object>> dishList = sqlMap.selectList(
				"DishAction.queryDishByShopId", operEntity.getSp_id());
		Map<String, List<String>> parentIdAnDishIdRel = getParentIdAndDishIdRel(dishList);
		getNeedRemoveDishId(needRemoveDishIdList, operEntity.getDh_id(),
				parentIdAnDishIdRel);
		Map<String, Object> needRemoveDishMap = new HashMap<String, Object>();
		needRemoveDishMap.put("needDeleteDishIdList", needRemoveDishIdList);
		sqlMap.delete("DishAction.deleteDishByDishIds", needRemoveDishMap);
		writeToJson(new JSON("0", getText("DishAction_removeDishSort_success")));
	}

	private void getNeedRemoveDishId(List<String> needRemoveDishIdList,
			String dhId, Map<String, List<String>> parentIdAnDishIdRel) {
		needRemoveDishIdList.add(dhId);
		List<String> dishIds = parentIdAnDishIdRel.get(dhId);
		if (!CollectionUtils.isEmpty(dishIds)) {
			for (String dishId : parentIdAnDishIdRel.get(dhId)) {
				if (parentIdAnDishIdRel.containsKey(dishId)) {
					getNeedRemoveDishId(needRemoveDishIdList, dishId,
							parentIdAnDishIdRel);
				} else {
					needRemoveDishIdList.add(dishId);
				}
			}
		}
	}

	@SuppressWarnings("serial")
	private Map<String, List<String>> getParentIdAndDishIdRel(
			List<Map<String, Object>> dishList) {
		Map<String, List<String>> parentIdAndDishIdRel = new HashMap<String, List<String>>();
		for (Map<String, Object> dishMap : dishList) {
			final String dishId = (String) dishMap.get("DH_ID");
			String parentId = (String) dishMap.get("DH_PARENTID");
			if (parentIdAndDishIdRel.containsKey(parentId)) {
				parentIdAndDishIdRel.get(parentId).add(dishId);
			} else {
				parentIdAndDishIdRel.put(parentId, new ArrayList<String>() {
					{
						add(dishId);
					}
				});
			}
		}
		return parentIdAndDishIdRel;
	}

	@Transactional
	public void addDish() {
		Dish dish = (Dish) sqlMap.selectOne(
				"DishAction.queryDishByDishIdAndShopId", addEntity);
		if (dish != null) {
			writeToJson(new JSON("1", getText("DishAction_addDish_failure")));
		} else {
			sqlMap.insert("DishAction.insertDish", addEntity);
		}
		writeToJson(new JSON("0", getText("DishAction_addDish_success")));
	}

	@SuppressWarnings("unchecked")
	public void queryShop() {
		List<Map<String, String>> shopInfo = sqlMap
				.selectList("DishAction.queryShop");
		for (Map<String, String> shop : shopInfo) {
			shop.put("text", shop.get("id") + "-" + shop.get("text"));
		}
		writeToJson(new JsonSerializer().serialize(shopInfo));
	}

	public void loadJsonByShopId() {
		writeToJson(dishTreeService.getJsonByShopId(sp_id, true));
	}

	public void getJsonByShopIdWithoutLeafs() {
		writeToJson(dishTreeService.getJsonByShopIdWithoutLeafs(sp_id, true));
	}

	@Transactional
	public void updateDish() {
		sqlMap.update("DishAction.updateDish", updateEntity);
		writeToJson(new JSON("0", getText("DishAction_updateDish_success")));
	}

	public String toViewDish() {
		operEntity.setSp_id(getUser().getSp_id());
		operEntity = (Dish) sqlMap.selectOne(
				"DishAction.queryDishByDishIdAndShopId", operEntity);
		return "toViewDish";
	}

	public String getSp_id() {
		return sp_id;
	}

	public void setSp_id(String sp_id) {
		this.sp_id = sp_id;
	}

}
