package com.csms.sqlmap;

import java.util.List;

public interface SqlMap {

	Object selectOne(String arg0);

	Object selectOne(String arg0, Object arg1);

	@SuppressWarnings("rawtypes")
	List selectList(String arg0);

	@SuppressWarnings("rawtypes")
	List selectList(String arg0, Object arg1);

	void insert(String arg0);

	void insert(String arg0, Object arg1);

	void update(String arg0);

	void update(String arg0, Object arg1);

	void delete(String arg0);

	void delete(String arg0, Object arg1);
}
