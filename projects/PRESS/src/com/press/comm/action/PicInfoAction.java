package com.press.comm.action;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.press.comm.JSON;
import com.press.comm.action.CommAction;
import com.press.comm.pojo.Catalog;
import com.press.comm.pojo.PicInfo;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;
import com.press.util.UUIDUtil;

public class PicInfoAction extends CommAction {
	private static Logger logger = LoggerFactory.getLogger(PicInfoAction.class);

	@Autowired
	private SqlMap sqlMap;

	private List<PicInfo> picInfoList;

	private PicInfo picInfo;

	/**
	 * 转向首页
	 * 
	 * @return
	 */
	public String to_view() {
		logger.debug("*****************图片首页**************");
		picInfoList = sqlMap.selectList("picinfo.queryPicInfoList", picInfo);
		return Constants.TO_VIEW;
	}
	
	public String to_update(){
		logger.debug("*****************图片首页**************");
		picInfoList = sqlMap.selectList("picinfo.queryPicInfoList", picInfo);
		return Constants.TO_UPDATE;
	}
	
	public void do_delete(){
		sqlMap.delete("picinfo.deletePicinfo", picInfo);
		JSON json = new JSON("1", "删除成功");
		sendJSON(json);
	}

	public List<PicInfo> getPicInfoList() {
		return picInfoList;
	}

	public void setPicInfoList(List<PicInfo> picInfoList) {
		this.picInfoList = picInfoList;
	}

	public PicInfo getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(PicInfo picInfo) {
		this.picInfo = picInfo;
	}

}
