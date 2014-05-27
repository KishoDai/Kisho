package com.csms.sqlmap.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.csms.sqlmap.SqlMap;

@Component("sqlMap")
public class SqlMapImpl implements SqlMap {

	@Resource
	SqlSessionTemplate sqlSession;

	public Object selectOne(String arg0) {
		return sqlSession.selectOne(arg0);
	}

	public Object selectOne(String arg0, Object arg1) {
		return sqlSession.selectOne(arg0, arg1);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String arg0) {
		return sqlSession.selectList(arg0);
	}

	@SuppressWarnings("rawtypes")
	public List selectList(String arg0, Object arg1) {
		return sqlSession.selectList(arg0, arg1);
	}

	public void insert(String arg0) {
		sqlSession.insert(arg0);
	}

	public void insert(String arg0, Object arg1) {
		sqlSession.insert(arg0, arg1);
	}

	public void update(String arg0) {
		sqlSession.update(arg0);
	}

	public void update(String arg0, Object arg1) {
		sqlSession.update(arg0, arg1);
	}

	public void delete(String arg0) {
		sqlSession.delete(arg0);
	}

	public void delete(String arg0, Object arg1) {
		sqlSession.delete(arg0, arg1);
	}

	public SqlSession getSession() {
		return sqlSession.getSqlSessionFactory().openSession();
	}

	public SqlSession getSession(boolean arg0) {
		return sqlSession.getSqlSessionFactory().openSession(arg0);
	}

}
