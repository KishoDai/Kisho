package com.csms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Component;

import com.csms.service.MenuTreeService;
import com.csms.sqlmap.SqlMap;

@Component("menuTreeService")
public class MenuTreeServiceImpl implements MenuTreeService {

	@Resource
	private SqlMap sqlMap;

	@SuppressWarnings("unchecked")
	public String getXmlTree4Menu() {
		List<Map<String, Object>> menuList = sqlMap
				.selectList("MenuAction.queryMenu");
		Map<String, List<String>> parentIdAndMenuIdRel = getParentIdAndMenuIdRel(menuList);

		Map<String, Object> treeMap = new HashMap<String, Object>();
		treeMap = getTreeMap(parentIdAndMenuIdRel.get("0").get(0), treeMap,
				parentIdAndMenuIdRel);
		Document document = DocumentHelper.createDocument();
		Element rootElement = DocumentHelper.createElement("tree");
		rootElement.add(DocumentHelper.createAttribute(rootElement, "id", "0"));

		document.setRootElement(getElement(treeMap, rootElement,
				getMenuIdInfoMap(menuList)));

		return document.asXML();
	}

	private Map<String, Map<String, Object>> getMenuIdInfoMap(
			List<Map<String, Object>> menuList) {
		Map<String, Map<String, Object>> menuIdInfoMap = new HashMap<String, Map<String, Object>>();
		for (Map<String, Object> menuMap : menuList) {
			menuIdInfoMap.put((String) menuMap.get("MU_ID"), menuMap);
		}
		return menuIdInfoMap;
	}

	private Map<String, Object> getTreeMap(final String menuId,
			Map<String, Object> treeMap,
			Map<String, List<String>> parentIdAndMenuIdRel) {
		if (parentIdAndMenuIdRel.containsKey(menuId)) {
			List<Object> tempList = new ArrayList<Object>();
			for (String tempMenuId : parentIdAndMenuIdRel.get(menuId)) {
				Map<String, Object> newTreeMap = getTreeMap(tempMenuId,
						new HashMap<String, Object>(), parentIdAndMenuIdRel);
				tempList.add(parentIdAndMenuIdRel.containsKey(tempMenuId) ? newTreeMap
						: tempMenuId);
			}
			treeMap.put(menuId, tempList.size() == 1 ? tempList.get(0)
					: tempList);
		}
		return treeMap;
	}

	private Map<String, List<String>> getParentIdAndMenuIdRel(
			List<Map<String, Object>> menuList) {
		Map<String, List<String>> parentIdAndMenuIdRel = new HashMap<String, List<String>>();
		for (Map<String, Object> menuMap : menuList) {
			final String menuId = (String) menuMap.get("MU_ID");
			String parentId = (String) menuMap.get("MU_PARENTID");
			if (parentIdAndMenuIdRel.containsKey(parentId)) {
				parentIdAndMenuIdRel.get(parentId).add(menuId);
			} else {
				parentIdAndMenuIdRel.put(parentId, new ArrayList<String>() {
					private static final long serialVersionUID = 1L;

					{
						add(menuId);
					}
				});
			}
		}
		return parentIdAndMenuIdRel;
	}

	@SuppressWarnings("unchecked")
	private Element getElement(Object object, Element element,
			Map<String, Map<String, Object>> menuIdInfoMap) {
		if (object instanceof List) {
			for (Object tempObj : (List<Object>) object) {
				if (tempObj instanceof String) {
					element.add(createElement((String) tempObj, menuIdInfoMap));
				} else {
					getElement(tempObj, element, menuIdInfoMap);
				}
			}
		} else if (object instanceof Map) {
			for (Entry<String, Object> entry : ((Map<String, Object>) object)
					.entrySet()) {
				Element element2 = createElement(entry.getKey(), menuIdInfoMap);
				Object value = entry.getValue();
				element.add(getElement(value, element2, menuIdInfoMap));
			}
		} else {
			element.add(createElement((String) object, menuIdInfoMap));
		}
		return element;
	}

	private Element createElement(String id,
			Map<String, Map<String, Object>> menuIdInfoMap) {
		Element element = DocumentHelper.createElement("item");
		element.add(DocumentHelper.createAttribute(element, "text",
				(String) menuIdInfoMap.get(id).get("MU_NAME")));
		element.add(DocumentHelper.createAttribute(element, "id", id));
		return element;
	}

}
