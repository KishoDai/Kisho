package com.csms.service;

public interface AdminFuncTreeService {

	String getJsonByFuncGroupId(String funcGroupId, boolean isSynchronized);

	String getJsonByFuncGroupIdWithoutLeafs(String funcGroupId,
			boolean isSynchronized);
}
