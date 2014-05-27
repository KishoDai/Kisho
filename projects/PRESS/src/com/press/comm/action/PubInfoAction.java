package com.press.comm.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.press.comm.JSON;
import com.press.comm.SysCode;
import com.press.comm.pojo.PicInfo;
import com.press.comm.pojo.PubInfo;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;
import com.press.util.DateUtil;
import com.press.util.UUIDUtil;
import com.press.util.Utils;

public class PubInfoAction extends CommAction<PubInfo> {
	private static Logger logger = LoggerFactory.getLogger(PubInfoAction.class);

	@Autowired
	private SqlMap sqlMap;

	private String catalogOptions;
	private String isshowOptions;

	private List<File> imageList;
	private List<String> imageListFileName;
	private List<String> imageListContentType;
	private List<String> picremarkList;
    
	private String infotyp;
	private String infotypnam;
	
	public String to_editinfocontent(){
		return "to_editinfocontent";
	}
	

	/**
	 * 转向首页
	 * 
	 * @return
	 */
	public String to_index() {
		infotyp = infotyp.trim();
		infotypnam = Utils.getCattypname(infotyp, sqlMap);
		if(queryCondition == null){
			queryCondition = new PubInfo();
		}
		queryCondition.setInfotyp(infotyp);
		return Constants.TO_INDEX;
	}
	
	public void getListJSON(){
		Integer totalRecords = (Integer) sqlMap.selectOne("pubinfo.queryCount",queryCondition);
		if(totalRecords == null){
			totalRecords = 0;
		}
		doPage(totalRecords,queryCondition);
		tList = sqlMap.selectList("pubinfo.queryPageList",queryCondition);
		
		if(tList!=null){
			for(PubInfo pubInfo : tList){
				try {
					dealTime4PubInfo(pubInfo);
				} catch (ParseException e) {
					logger.error("转换时间异常:"+e.getMessage(),e);
				}
			}
		}
		sendJSON(totalRecords, tList);
	}
	

	/**
	 * 转向添加页面
	 * 
	 * @return
	 */
	public String to_add() {
		addCondition = new PubInfo();
//		addCondition.setInfotyp(infotyp);
		infotypnam = Utils.getCattypname(infotyp, sqlMap);
		catalogOptions = Utils.getCatalogOptions(sqlMap, null,true,infotyp);
		isshowOptions = Utils.getParamOptions("common.select",null,true, sqlMap);
		logger.debug("infotyp="+infotyp);
		logger.debug("infotypnam="+infotypnam);
		logger.debug("catalogOptions="+catalogOptions);
		return Constants.TO_ADD;
	}

	/**
	 * 添加数据
	 * 
	 * @return
	 */
	public String do_add() {
		// 2.添加产品信息
		// 3.将产品关联的图片存储到数据库中
		addCondition.setInfoid(UUIDUtil.getUUIDStr());
//		pubinfo.setLastmodifytime(DateUtil.getCurrDate());
		if("1".equals(addCondition.getIsshow())){
			addCondition.setPubtime(DateUtil.getCurrDate());
		} else {
			addCondition.setPubtime("");
		}
		dealPubinfoShowtime(addCondition);
		addCondition.setCreatetime(DateUtil.getCurrDate());
		addCondition.setCreateid((String) ActionContext.getContext().getSession().get(Constants.ADMINID));
		sqlMap.insert("pubinfo.insertPubinfo", addCondition);
		Utils.uploadFile(imageList, imageListFileName,picremarkList,sqlMap,addCondition.getInfoid());
		return Constants.REDIRECT_INDEX;
	}

	private void dealPubinfoShowtime(PubInfo pubinfo) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		if(StringUtils.isNotBlank(pubinfo.getShowstarttime())){
			try {
				pubinfo.setShowstarttime(DateUtil.formateDate(sdf.parse(pubinfo.getShowstarttime()+" 00:00:00"), "yyyyMMddHHmmss"));
				
			} catch (ParseException e) {
				logger.error("时间转换异常:"+e.getMessage(),e);
			}
		}
		if(StringUtils.isNotBlank(pubinfo.getShowendtime())){
			try {
				pubinfo.setShowendtime(DateUtil.formateDate(sdf.parse(pubinfo.getShowendtime()+" 23:59:59"), "yyyyMMddHHmmss"));
			} catch (ParseException e) {
				logger.error("时间转换异常:"+e.getMessage(),e);
			}
		}
		
	}



	/**
	 * 转向修改页面
	 * 
	 * @return
	 */
	public String to_update() {
		updateCondition = (PubInfo) sqlMap.selectOne("pubinfo.queryPubinfo", ids[0]);
		queryPubinfo(updateCondition);
		logger.debug("showstarttime="+updateCondition.getShowstarttime());
		logger.debug("showendtime="+updateCondition.getShowendtime());
		if(StringUtils.isNotBlank(updateCondition.getShowstarttime())){
			try {
				updateCondition.setShowstarttime(DateUtil.formateDate(updateCondition.getShowstarttime(), "MM/dd/yyyy"));
			} catch (ParseException e) {
				logger.error("时间转换异常:"+e.getMessage(),e);
			}
		}
		if(StringUtils.isNotBlank(updateCondition.getShowendtime())){
			try {
				updateCondition.setShowendtime(DateUtil.formateDate(updateCondition.getShowendtime(), "MM/dd/yyyy"));
			} catch (ParseException e) {
				logger.error("时间转换异常:"+e.getMessage(),e);
			}
		}
		logger.debug("showstarttime="+updateCondition.getShowstarttime());
		logger.debug("showendtime="+updateCondition.getShowendtime());
		return Constants.TO_UPDATE;
	}

	/**
	 * 修改数据
	 * 
	 * @return
	 */
	public String do_update() {
		//1.上传图片
		Utils.uploadFile(imageList, imageListFileName, picremarkList,sqlMap, updateCondition.getInfoid());
		//2.更新信息
		if("1".equals(updateCondition.getIsshow())){
			updateCondition.setPubtime(DateUtil.getCurrDate());
		} else {
			updateCondition.setPubtime("");
		}
		dealPubinfoShowtime(updateCondition);
		updateCondition.setLastmodifytime(DateUtil.getCurrDate());
		updateCondition.setModifyid((String) ActionContext.getContext().getSession().get(Constants.ADMINID));
		sqlMap.update("pubinfo.updatePubinfo",updateCondition);
		return Constants.REDIRECT_INDEX;
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 */
	public String do_delete() {
        
		for (String id : ids) {
			sqlMap.delete("pubinfo.deletePubinfo", id);
			PicInfo picInfo = new PicInfo();
			picInfo.setPicid(id);
			sqlMap.delete("picinfo.deletePicinfo", picInfo);
			// 删除发布信息相关信息
			Utils.deleteRelInfo(id,sqlMap);

		}
		return Constants.REDIRECT_INDEX;
	}
	
	/**
	 * 转向查看页面
	 * 
	 * @return
	 */
	public String to_view() {
		viewCondition = (PubInfo) sqlMap.selectOne("pubinfo.queryPubinfo", ids[0]);
		queryPubinfo(viewCondition);
		return Constants.TO_VIEW;
	}

	private void queryPubinfo(PubInfo pubInfo) {
		catalogOptions = Utils.getCatalogOptions(sqlMap,pubInfo.getCatid(),false, pubInfo.getInfotyp());
		isshowOptions = Utils.getParamOptions("common.select",pubInfo.getIsshow(),false, sqlMap);
		try {
			dealTime4PubInfo(pubInfo);
		} catch (ParseException e) {
			logger.error("转换时间异常:"+e.getMessage(),e);
		}
	}

	private void dealTime4PubInfo(PubInfo pubInfo) throws ParseException {
		if(StringUtils.isNotBlank(pubInfo.getPubtime())){
		   pubInfo.setPubtime(DateUtil.formateDate(DateUtil.parseDate(pubInfo.getPubtime()), "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringUtils.isNotBlank(pubInfo.getCreatetime())){
		   pubInfo.setCreatetime(DateUtil.formateDate(DateUtil.parseDate(pubInfo.getCreatetime()), "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringUtils.isNotBlank(pubInfo.getLastmodifytime())){
		   pubInfo.setLastmodifytime(DateUtil.formateDate(DateUtil.parseDate(pubInfo.getLastmodifytime()), "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringUtils.isNotBlank(pubInfo.getShowstarttime())){
		   pubInfo.setShowstarttime(DateUtil.formateDate(DateUtil.parseDate(pubInfo.getShowstarttime()), "yyyy-MM-dd HH:mm:ss"));
		}
		if(StringUtils.isNotBlank(pubInfo.getShowendtime())){
		  pubInfo.setShowendtime(DateUtil.formateDate(DateUtil.parseDate(pubInfo.getShowendtime()), "yyyy-MM-dd HH:mm:ss"));
		}
	}
	



	public String getCatalogOptions() {
		return catalogOptions;
	}

	public void setCatalogOptions(String catalogOptions) {
		this.catalogOptions = catalogOptions;
	}

	public List<File> getImageList() {
		return imageList;
	}

	public void setImageList(List<File> imageList) {
		this.imageList = imageList;
	}

	public List<String> getImageListFileName() {
		return imageListFileName;
	}

	public void setImageListFileName(List<String> imageListFileName) {
		this.imageListFileName = imageListFileName;
	}

	public List<String> getImageListContentType() {
		return imageListContentType;
	}

	public void setImageListContentType(List<String> imageListContentType) {
		this.imageListContentType = imageListContentType;
	}

	public List<String> getPicremarkList() {
		return picremarkList;
	}

	public void setPicremarkList(List<String> picremarkList) {
		this.picremarkList = picremarkList;
	}

	public String getInfotypnam() {
		return infotypnam;
	}

	public void setInfotypnam(String infotypnam) {
		this.infotypnam = infotypnam;
	}


	public String getInfotyp() {
		return infotyp;
	}

	public void setInfotyp(String infotyp) {
		this.infotyp = infotyp;
	}

	public String getIsshowOptions() {
		return isshowOptions;
	}

	public void setIsshowOptions(String isshowOptions) {
		this.isshowOptions = isshowOptions;
	}

    
}
