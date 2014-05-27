package com.csms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.csms.service.AdminFuncTreeService;
import com.csms.sqlmap.SqlMap;

@Component("adminFuncTreeService")
public class AdminFuncTreeServiceImpl implements AdminFuncTreeService {

	@Resource
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
				.selectList("AdminFuncTreeServiceImpl.queryFunc");
		if (StringUtils.isBlank(funcGroupId)) {
			funcGroupId = "0";
			child0 = getChild("0", "餐饮综合服务管理系统树", "", "0");
		}
		if (CollectionUtils.isEmpty(dishList)) {
			children0.add(child0);
			return serialize(children0);
		}
		Map<String, List<String>> parentIdAnDishIdRel = getParentIdAndDishIdRel(dishList);
		Map<String, Map<String, Object>> dishIdInfoMap = getDishIdInfoMap(dishList);
		if (child0 == null) {
			Map<String, Object> dishMap = dishIdInfoMap.get(funcGroupId);
			String text = (String) dishMap.get("FN_NAME");
			String url = (String) dishMap.get("FN_URL");
			String isLeaf = (String) dishMap.get("FN_ISLEAF");
			child0 = getChild(funcGroupId, text, url, isLeaf);
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
			Map<String, Map<String, Object>> funcIdInfoMap, boolean withLeafs) {
		List<String> funcIdList = parentIdAnDishIdRel.get(parentId);
		if (CollectionUtils.isEmpty(funcIdList)) {
			return children;
		}
		for (String funcId : funcIdList) {
			Map<String, Object> funcMap = funcIdInfoMap.get(funcId);
			String isLeaf = (String) funcMap.get("FN_ISLEAF");
			if (isLeaf(isLeaf) && !withLeafs) {
				continue;
			}
			String text = (String) funcMap.get("FN_NAME");
			String url = (String) funcMap.get("FN_URL");
			Map<String, Object> child = getChild(funcId, text, url, isLeaf);
			child.put("state", "open");
			if (!isLeaf(isLeaf)) {
				if (CollectionUtils.isEmpty(parentIdAnDishIdRel.get(funcId))) {
					// child.put("state", "closed");
				} else {
					if (CollectionUtils
							.isEmpty(parentIdAnDishIdRel.get(funcId))) {
						// child.put("state", "closed");
					} else {
						child.put(
								"children",
								getChildren(funcId,
										new ArrayList<Map<String, Object>>(),
										parentIdAnDishIdRel, funcIdInfoMap,
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
			children.add(getChild("0", "餐饮综合服务管理系统树", "", "0"));
		} else {
			List<Map<String, Object>> funcList = sqlMap.selectList(
					"AdminFuncTreeServiceImpl.queryFunc", parentId);
			for (Map<String, Object> funcMap : funcList) {
				String isLeaf = (String) funcMap.get("FN_ISLEAF");
				if (!withLeafs && isLeaf(isLeaf)) {
					continue;
				}
				String id = (String) funcMap.get("FN_ID");
				String text = (String) funcMap.get("FN_NAME");
				String url = (String) funcMap.get("FN_URL");
				children.add(getChild(id, text, url, isLeaf));
			}
		}
		return serialize(children);
	}

	private HashMap<String, Object> getChild(final String id,
			final String text, final String url, final String isLeaf) {
		return new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;

			{
				put("id", id);
				put("text", text);
				put("attributes", new HashMap<String, Object>() {
					private static final long serialVersionUID = 1L;

					{
						put("url", url);
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
