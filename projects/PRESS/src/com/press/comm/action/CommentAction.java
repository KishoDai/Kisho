package com.press.comm.action;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.press.comm.pojo.Comment;
import com.press.product.pojo.Product;
import com.press.sqlmap.SqlMap;
import com.press.user.pojo.User;
import com.press.util.Constants;
import com.press.util.DateUtil;
import com.press.util.Utils;

public class CommentAction extends CommAction<Comment> {
	private static Logger logger = LoggerFactory.getLogger(CommentAction.class);
	@Autowired
	SqlMap sqlMap;
	private String relid;
	private String userid;
	private String reltyp;
	
	public String to_index(){
		if(queryCondition==null){
			queryCondition = new Comment();
		}
		queryCondition.setRelid(relid);
		queryCondition.setReltyp(reltyp);
		queryCondition.setUserid(userid);
		return Constants.TO_INDEX;
	}
   
	public void getListJSON(){
		Integer totalRecords = (Integer) sqlMap.selectOne("comment.queryCount",queryCondition);
		if(totalRecords == null){
			totalRecords = 0;
		}
		doPage(totalRecords,queryCondition);
		tList = sqlMap.selectList("comment.queryPageList",queryCondition);
		if (tList != null) {
			for(Comment com : tList){
				if(StringUtils.isNotBlank(com.getComtime())){
					try {
						com.setComtime(DateUtil.formateDate(com.getComtime(), "yyyy-MM-dd HH:mm:ss"));
					} catch (ParseException e) {
						logger.error("时间格式转换异常:"+e.getMessage(),e);
					}
				}
			}
		}
	  sendJSON(totalRecords, tList);
	}
	
	public String do_delete() {
		for (String id : ids) {
			sqlMap.delete("comment.deleteComment", id);
		}
		return Constants.REDIRECT_INDEX;
	}
	
	public String to_view(){
		queryCondition = (Comment) sqlMap.selectOne("comment.queryComment",queryCondition.getComid());
		return Constants.TO_VIEW;
	}

	public String getRelid() {
		return relid;
	}

	public void setRelid(String relid) {
		this.relid = relid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getReltyp() {
		return reltyp;
	}

	public void setReltyp(String reltyp) {
		this.reltyp = reltyp;
	}
	
}
