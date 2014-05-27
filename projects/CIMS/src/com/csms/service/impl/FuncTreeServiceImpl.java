package com.csms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.csms.service.FuncTreeService;
import com.csms.sqlmap.SqlMap;

@Component("funcTreeService")
public class FuncTreeServiceImpl implements FuncTreeService {

	@Autowired
	private SqlMap sqlMap;

	public String getJsonByFuncGroupId(String funcGroupId,
			boolean isSynchronized) {
		boolean withLeafs = true;
		if (isSynchronized) {
			return getJsonByFuncGroupId4Sync(funcGroupId, withLeafs);
		}
		return getJsonByFuncGroupId4Async(funcGroupId, withLeafs);
	}

	public String getJsonByFuncGroupIdWithoutLeafs(String funcGroupId,
			boolean isSynchronized) {
		boolean withLeafs = false;
		if (isSynchronized) {
			return getJsonByFuncGroupId4Sync(funcGroupId, withLeafs);
		}
		return getJsonByFuncGroupId4Async(funcGroupId, withLeafs);
	}

	@SuppressWarnings("unchecked")
	private String getJsonByFuncGroupId4Sync(String funcGroupId,
			boolean withLeafs) {
		List<Map<String, Object>> children0 = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		Map<String, Object> child0 = null;
		List<Map<String, Object>> dishList = sqlMap
				.selectList("FuncAction.queryFunc");
		if (StringUtils.isBlank(funcGroupId)) {
			funcGroupId = "0";
			child0 = getChild("0", "功能菜单树", "", "0");
		}
		if (CollectionUtils.isEmpty(dishList)) {
			children0.add(child0);
			return serialize(children0);
		}
		Map<String, List<String>> parentIdAnDishIdRel = getParentIdAndDishIdRel(dishList);
		Map<String, Map<String, Object>> dishIdInfoMap = getDishIdInfoMap(dishList);
		if (child0 == null) {
			Map<String, Object> dishMap = dishIdInfoMap.get(funcGroupId);
			String text = funcGroupId + "-" + dishMap.get("FN_NAME");
			String shopId = (String) dishMap.get("SP_ID");
			String isLeaf = (String) dishMap.get("FN_ISLEAF");
			child0 = getChild(funcGroupId, text, shopId, isLeaf);
		}
		child0.put("state", "open");
		getChildren(funcGroupId, children, parentIdAnDishIdRel, dishIdInfoMap,
				withLeafs);
		child0.put("children", children);
		children0.add(child0);
		return serialize(children0);
	}

	private List<Map<String, Object>> getChildren(String parentId,
			List<Map<String, Object>> children,
			Map<String, List<String>> parentIdAnDishIdRel,
			Map<String, Map<String, Object>> dishIdInfoMap, boolean withLeafs) {
		List<String> dishIdList = parentIdAnDishIdRel.get(parentId);
		if (CollectionUtils.isEmpty(dishIdList)) {
			return children;
		}
		for (String dishId : dishIdList) {
			Map<String, Object> dish = dishIdInfoMap.get(dishId);
			String isLeaf = (String) dish.get("FN_ISLEAF");
			if (isLeaf(isLeaf) && !withLeafs) {
				continue;
			}
			String text = dishId + "-" + dish.get("FN_NAME");
			String shopId = (String) dish.get("SP_ID");
			Map<String, Object> child = getChild(dishId, text, shopId, isLeaf);
			child.put("state", "open");
			if (!isLeaf(isLeaf)) {
				if (CollectionUtils.isEmpty(parentIdAnDishIdRel.get(dishId))) {
					// child.put("state", "closed");
				} else {
					if (CollectionUtils
							.isEmpty(parentIdAnDishIdRel.get(dishId))) {
						// child.put("state", "closed");
					} else {
						child.put(
								"children",
								getChildren(dishId,
										new ArrayList<Map<String, Object>>(),
										parentIdAnDishIdRel, dishIdInfoMap,
										withLeafs));
					}
				}
			}
			children.add(child);
		}
		return children;
	}

	private Map<String, List<String>> getParentIdAndDishIdRel(
			List<Map<String, Object>> dishList) {
		Map<String, List<String>> parentIdAndDishIdRel = new HashMap<String, List<String>>();
		for (Map<String, Object> dishMap : dishList) {
			final String dishId = (String) dishMap.get("FN_ID");
			String parentId = (String) dishMap.get("FN_GROUPID");
			if (parentIdAndDishIdRel.containsKey(parentId)) {
				parentIdAndDishIdRel.get(parentId).add(dishId);
			} else {
				parentIdAndDishIdRel.put(parentId, new ArrayList<String>() {
					private static final long serialVersionUID = 1L;

					{
						add(dishId);
					}
				});
			}
		}
		return parentIdAndDishIdRel;
	}

	private Map<String, Map<String, Object>> getDishIdInfoMap(
			List<Map<String, Object>> dishList) {
		Map<String, Map<String, Object>> dishIdInfoMap = new HashMap<String, Map<String, Object>>();
		for (Map<String, Object> dishMap : dishList) {
			dishIdInfoMap.put((String) dishMap.get("FN_ID"), dishMap);
		}
		return dishIdInfoMap;
	}

	@SuppressWarnings("unchecked")
	private String getJsonByFuncGroupId4Async(String parentId, boolean withLeafs) {
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		if (StringUtils.isBlank(parentId)) {
			children.add(getChild("0", "功能菜单树", "", "0"));
		} else {
			List<Map<String, Object>> dishList = sqlMap.selectList(
					"FuncAction.queryFunc", parentId);
			for (Map<String, Object> dishMap : dishList) {
				String isLeaf = (String) dishMap.get("FN_ISLEAF");
				if (!withLeafs && isLeaf(isLeaf)) {
					continue;
				}
				String id = (String) dishMap.get("FN_ID");
				String text = id + "-" + dishMap.get("FN_NAME");
				String shopId = (String) dishMap.get("SP_ID");
				children.add(getChild(id, text, shopId, isLeaf));
			}
		}
		return serialize(children);
	}

	private HashMap<String, Object> getChild(final String id,
			final String text, final String shopId, final String isLeaf) {
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;

			{
				put("id", id);
				put("text", text);
				put("attributes", new HashMap<String, Object>() {
					private static final long serialVersionUID = 1L;

					{
						put("sp_id", shopId);
						put("is_leaf", isLeaf);
					}
				});
				put("state", "open");
			}
		};
	}

	private String serialize(Object obj) {
		return (String) new JsonSerializer().serialize(obj);
	}

	private boolean isLeaf(String isLeaf) {
		return "1".equals(isLeaf);
	}

}
