package com.press.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.press.comm.SysCode;
import com.press.comm.pojo.Catalog;
import com.press.comm.pojo.Param;
import com.press.comm.pojo.PicInfo;
import com.press.product.pojo.Product;
import com.press.product.pojo.Producttyp;
import com.press.sqlmap.SqlMap;

public class Utils {

	public static String rootPath;

	public static String substring(String arg0, int arg1) {
		if (StringUtils.isEmpty(arg0) || arg1 <= 0) {
			return "";
		}
		int l = arg0.getBytes().length;
		if (l <= arg1 || l == 1) {
			return arg0;
		}

		int totalCount = 0;
		int interceptLength = 0;
		int size = 0;
		char[] tempChars = arg0.toCharArray();
		for (int i = 0; i < tempChars.length; i++) {
			size = String.valueOf(tempChars[i]).getBytes().length;
			totalCount += size;
			if (totalCount == arg1) {
				interceptLength = i + 1;
				break;
			} else if (totalCount > arg1) {
				if (i == 0) {
					return "";
				} else {
					interceptLength = i;
					break;
				}
			}
		}
		return arg0.substring(0, interceptLength);
	}

	public static void moveFileList(List<File> srcfileList,
			List<File> destFileList) {
		File srcFile = null;
		File destFile = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		byte[] bytes = new byte[1024];
		try {
			for (int i = 0; i < srcfileList.size(); i++) {
				srcFile = srcfileList.get(i);
				destFile = destFileList.get(i);
				if (!destFile.exists()) {
					destFile.createNewFile();
				}
				fis = new FileInputStream(srcFile);
				fos = new FileOutputStream(destFile);
				int length;
				while ((length = fis.read(bytes)) != -1) {
					fos.write(bytes, 0, length);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// return flag;
	}

	public static List<File> getDestFileList(String path,
			List<String> fileNameList) {
		String dateName = DateUtil.getCurrDate("yyyyMMddHHmmss");
		List<File> fileList = new ArrayList<File>();
		File file = null;
		String fileName = null;
		for (int i = 1; i <= fileNameList.size(); i++) {
			fileName = fileNameList.get(i - 1);
			file = new File(path + dateName + i
					+ (fileName.substring(fileName.lastIndexOf("."))));
			fileList.add(file);
		}
		return fileList;
	}

	public static void uploadFile(List<File> imageList,
			List<String> imageListFileName, List<String> picremarkList,
			SqlMap sqlMap, String relid) {
		if (imageList != null) {
			if (StringUtils.isEmpty(rootPath)) {
				rootPath = getParamvalue("fileupload.rootpath", sqlMap);
			}
			List<File> destFileList = Utils.getDestFileList(rootPath,
					imageListFileName);
			Utils.moveFileList(imageList, destFileList);
			PicInfo picInfo = new PicInfo();
			for (int i = 0; i < destFileList.size(); i++) {
				File file = destFileList.get(i);
				picInfo.setPicid(UUIDUtil.getUUIDStr());
				picInfo.setRelid(relid);
				picInfo.setPicnam(file.getName());
				picInfo.setPicremark(picremarkList.get(i));
				sqlMap.insert("picinfo.insertPicinfo", picInfo);
			}
		}
	}

	public static String getCatalogOptions(SqlMap sqlMap,String catid, boolean isHaveDefault,String cattyp) {
		List<Catalog> catalogList = sqlMap.selectList("catalog.queryCatalogList2", cattyp);
		StringBuffer options = new StringBuffer();
		if(isHaveDefault){
		   options.append("<option value=\"\"></option>");
		}
		for (Catalog catalog : catalogList) {
			options.append("<option value=\"");
			options.append(catalog.getCatid());
			options.append("\"");
			if(catalog.getCatid().equals(catid)){
				options.append(" selected=\"selected\"");
			}
			options.append(">");
			options.append(catalog.getCatnam());
			options.append("</option>");
		}
		return options.toString();
	}
	
	public static String getProtypOptions(SqlMap sqlMap, String protypid,boolean isHaveDefault,String defaultStr) {
		List<Producttyp> producttypList = sqlMap.selectList("producttyp.queryProducttypList");
		StringBuffer options = new StringBuffer();
		if(isHaveDefault){
		   options.append("<option value=\"\">");
		   options.append(defaultStr);
		   options.append("</option>");
		}
		for (Producttyp producttyp : producttypList) {
			options.append("<option value=\"");
			options.append(producttyp.getProtypid());
			options.append("\"");
			if(producttyp.getProtypid().equals(protypid)){
				options.append(" selected=\"selected\"");
			}
			options.append(">");
			options.append(producttyp.getProtypnam());
			options.append("</option>");
		}
		return options.toString();
	}

	public static String getParamvalue(String paramnam, SqlMap sqlMap) {
		Param param = (Param) sqlMap.selectOne("comm.queryParam", paramnam);
		return param.getParamvalue();
	}

	public static Map<String, String> getParamMap(String paramnam, SqlMap sqlMap) {
		List<Param> paramList = sqlMap.selectList("comm.queryParam", paramnam);
		Map<String, String> map = new TreeMap<String, String>();
		if (paramList != null) {
			for (Param param : paramList) {
				map.put(param.getParamkey().trim(), param.getParamvalue());
			}
		}
		return map;
	}


	/**
	 * @param paramnam
	 * @param key   默认选择的值
	 * @param isHaveDefault 是否有空值
	 * @param sqlMap
	 * @return
	 */
	public static String getParamOptions(String paramnam,String key,boolean isHaveDefault, SqlMap sqlMap) {
		StringBuffer sb = new StringBuffer();
		if(isHaveDefault){
		   sb.append("<option value=\"\"></option>");
		}
		Map<String, String> map = getParamMap(paramnam, sqlMap);
		for (Entry<String, String> entry : map.entrySet()) {
			sb.append("<option value=\"");
			sb.append(entry.getKey());
			sb.append("\"");
			if(entry.getKey().equals(key)){
				sb.append(" selected=\"selected\"");
			}
			sb.append(">");
			sb.append(entry.getValue());
			sb.append("</option>");
		}
		return sb.toString();
	}

	
	public static String getCattypname(String cattyp, SqlMap sqlMap) {
		return getParamMap("pubinfo.cattypnam", sqlMap).get(cattyp);
	}
    
	public static void dealTime4Product(Product product) throws ParseException {
		if (product != null) {
			if (StringUtils.isNotBlank(product.getPubtime())) {
				product.setPubtime(DateUtil.formateDate(
						DateUtil.parseDate(product.getPubtime()),
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.isNotBlank(product.getCreatetime())) {
				product.setCreatetime(DateUtil.formateDate(
						DateUtil.parseDate(product.getCreatetime()),
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.isNotBlank(product.getLastmodifytime())) {
				product.setLastmodifytime(DateUtil.formateDate(
						DateUtil.parseDate(product.getLastmodifytime()),
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.isNotBlank(product.getShowstarttime())) {
				product.setShowstarttime(DateUtil.formateDate(
						DateUtil.parseDate(product.getShowstarttime()),
						"yyyy-MM-dd HH:mm:ss"));
			}
			if (StringUtils.isNotBlank(product.getShowendtime())) {
				product.setShowendtime(DateUtil.formateDate(
						DateUtil.parseDate(product.getShowendtime()),
						"yyyy-MM-dd HH:mm:ss"));
			}
		}
	}
	public static void deleteFile(File file){
		if(file.exists()){
		   file.delete();
		}
	}
	
	public static void deleteFile(String path,String fileName){
		deleteFile(path+fileName);
	}
	
	public static void deleteFile(String pathname){
		deleteFile(new File(pathname));
	}
	
	public static String getRootUploadpath(SqlMap sqlMap){
		return getParamvalue("fileupload.rootpath", sqlMap);
	}
	
	public static void deleteRelInfo(String id,SqlMap sqlMap) {
		PicInfo picInfo = new PicInfo();
		picInfo.setRelid(id);
		List<PicInfo> picInfos = sqlMap.selectList("picinfo.queryPicInfoList", picInfo);
		if(picInfos!=null&&picInfos.size()>0){
			for(PicInfo tempPicInfo : picInfos){
				//删除图片地址
				Utils.deleteFile(Utils.getRootUploadpath(sqlMap), tempPicInfo.getPicnam());
			}
			sqlMap.delete("picinfo.deleteRelPicinfo", picInfo);
		}
		//4.删除该产品的所有评论
		sqlMap.delete("comment.deleteRelComment", id);
		//5.删除与该产品与类型的关系
		sqlMap.delete("producttyp.deleteRelprotyp3",id);
	}
}
