package com.csms.service;

public interface DishTreeService {

	String getJsonByParentId(String parentId, boolean isSynchronized);

	String getJsonByShopId(String shopId, boolean isSynchronized);

	String getJsonByShopIdWithoutLeafs(String shopId, boolean isSynchronized);

}
