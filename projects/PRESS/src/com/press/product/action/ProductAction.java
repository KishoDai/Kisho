package com.press.product.action;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.press.comm.JSON;
import com.press.comm.action.CommAction;
import com.press.product.pojo.Product;
import com.press.product.pojo.Product2;
import com.press.product.pojo.Producttyp;
import com.press.sqlmap.SqlMap;
import com.press.util.Constants;
import com.press.util.DateUtil;
import com.press.util.UUIDUtil;
import com.press.util.Utils;

public class ProductAction extends CommAction<Product> {
	private static Logger logger = LoggerFactory.getLogger(ProductAction.class);

	@Autowired
	private SqlMap sqlMap;


	private String catalogOptions;

	private List<File> imageList;
	private List<String> imageListFileName;
	private List<String> imageListContentType;
	private List<String> picremarkList;
	private String isshowOptions;
    private List<Producttyp> protypList;
    private List<String> protypids;
    private Product existProduct;
    
	/**
	 * 转向首页
	 * 
	 * @return
	 */
	public String to_index() {
		if(queryCondition==null){
			queryCondition = new Product();
		}
		catalogOptions = Utils.getCatalogOptions(sqlMap,queryCondition.getCatid(),true, "0");
		isshowOptions = Utils.getParamOptions("common.select",queryCondition.getIsshow(),true, sqlMap);
		return Constants.TO_INDEX;
	}
	
	public void getListJSON2(){
		protypList = sqlMap.selectList("producttyp.queryProducttypList");
	    sendJSON(protypList,protypList.size());
	}
	
	public void getListJSON(){
		Integer totalRecords = (Integer) sqlMap.selectOne("product.queryCount",queryCondition);
		if(totalRecords == null){
			totalRecords = 0;
		}
		doPage(totalRecords,queryCondition);
		tList = sqlMap.selectList("product.queryPageList",queryCondition);
		if (tList != null) {
			for (Product product : tList) {
				try {
					Utils.dealTime4Product(product);
				} catch (ParseException e) {
					logger.error("转换时间异常:" + e.getMessage(), e);
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
		catalogOptions = Utils.getCatalogOptions(sqlMap,null,true, "0");
		isshowOptions = Utils.getParamOptions("common.select",null,true, sqlMap);
		addCondition = new Product();
		return Constants.TO_ADD;
	}
   

	/**
	 * 添加数据
	 * 
	 * @return
	 */
	public String do_add() {
		logger.debug("*********添加产品***********");

		// 2.添加产品信息
		// 3.将产品关联的图片存储到数据库中
		addCondition.setProid(UUIDUtil.getUUIDStr());
		addCondition.setPricerange(addCondition.getStartprice() + "~"
				+ addCondition.getEndprice());
		if ("1".equals(addCondition.getIsshow())) {
			addCondition.setPubtime(DateUtil.getCurrDate());
		} else {
			addCondition.setPubtime("");
		}
		addCondition.setCreatetime(DateUtil.getCurrDate());
		addCondition.setCreateid((String) ActionContext.getContext().getSession()
				.get(Constants.ADMINID));
		// product.setLastmodifytime(DateUtil.getCurrDate());
		sqlMap.insert("product.insertProduct", addCondition);
		Utils.uploadFile(imageList, imageListFileName, picremarkList, sqlMap,addCondition.getProid());
//		sendScript("window.parent.returnProductIndex(\"1\");");
		return Constants.REDIRECT_INDEX;
	}

	/**
	 * 转向修改页面
	 * 
	 * @return
	 */
	public String to_update() {
		updateCondition = (Product) sqlMap.selectOne("product.queryProduct", ids[0]);
		setProductContent(updateCondition);
		return Constants.TO_UPDATE;
	}

	/**
	 * 修改数据
	 * 
	 * @return
	 */
	public String do_update() {
		try {
			Utils.uploadFile(imageList, imageListFileName, picremarkList,sqlMap, updateCondition.getProid());
			// 2.更新图片
			updateCondition.setPricerange(updateCondition.getStartprice() + "~"
					+ updateCondition.getEndprice());
			if ("1".equals(updateCondition.getIsshow())) {
				updateCondition.setPubtime(DateUtil.getCurrDate());
			} else {
				updateCondition.setPubtime("");
			}
			updateCondition.setLastmodifytime(DateUtil.getCurrDate());
			updateCondition.setModifyid((String) ActionContext.getContext()
					.getSession().get(Constants.ADMINID));
			sqlMap.update("product.updateProduct", updateCondition);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		sendScript("window.parent.returnProductIndex(\"1\");");
		return Constants.REDIRECT_INDEX;
	}

	/**
	 * 删除数据
	 * 
	 * @return
	 */
	public String do_delete() {
		for (String id : ids) {
			//1.删除产品
			sqlMap.delete("product.deleteProduct", id);
			//2.产品相关信息
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
		viewCondition = (Product) sqlMap.selectOne("product.queryProduct", ids[0]);
		setProductContent(viewCondition);
		return Constants.TO_VIEW;
	}

	
	
	private void setProductContent(Product product) {
		catalogOptions = Utils.getCatalogOptions(sqlMap,product.getCatid(),false, "0");
		isshowOptions = Utils.getParamOptions("common.select", product.getIsshow(),false,sqlMap);
		try {
			String[] strs = product.getPricerange().split("[~]");
			product.setStartprice(strs[0]);
			product.setEndprice(strs[1]);
		} catch (Exception e) {
			logger.error("拆分字符串异常:" + e.getMessage(), e);
		}
		try {
			Utils.dealTime4Product(product);
		} catch (ParseException e) {
			logger.error("转换时间异常:" + e.getMessage(), e);
		}
		
	}

	public void checkProductNameExists(){
		
	}
	
	public String do_add_protyp(){
		if(protypids!=null){
			for(String protypid : protypids){
				for(String proid : ids){
					Product2 product2 = new Product2();
					product2.setRelid(UUIDUtil.getUUIDStr());
					product2.setProid(proid);
					product2.setProtypid(protypid);
					sqlMap.delete("producttyp.deleteRelprotyp2",product2 );
					sqlMap.insert("producttyp.insertRelprotyp", product2);
				}
			}
		}
//		sendJSON(new JSON("1", ""));
		return Constants.REDIRECT_INDEX;
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


	public String getIsshowOptions() {
		return isshowOptions;
	}

	public void setIsshowOptions(String isshowOptions) {
		this.isshowOptions = isshowOptions;
	}



	public List<String> getProtypids() {
		return protypids;
	}

	public void setProtypids(List<String> protypids) {
		this.protypids = protypids;
	}


	public List<Producttyp> getProtypList() {
		return protypList;
	}

	public void setProtypList(List<Producttyp> protypList) {
		this.protypList = protypList;
	}

	public Product getExistProduct() {
		return existProduct;
	}

	public void setExistProduct(Product existProduct) {
		this.existProduct = existProduct;
	}
	


}
