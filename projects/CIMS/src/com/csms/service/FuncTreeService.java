package com.csms.service;

public interface FuncTreeService {

	String getJsonByFuncGroupId(String funcGroupId, boolean isSynchronized);

	String getJsonByFuncGroupIdWithoutLeafs(String funcGroupId,
			boolean isSynchronized);
}
