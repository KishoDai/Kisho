package com.csms.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csms.common.CommonAction;
import com.csms.pojo.User;
import com.csms.pojo.Withdraw;
import com.csms.sqlmap.SqlMap;
import com.csms.utils.Constants;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("rawtypes")
@Component("withdrawAction")
public class WithdrawAction extends CommonAction {

	private static final long serialVersionUID = 4043574863455109827L;

	@Autowired
	SqlMap sqlMap;

	private Withdraw addWithdraw;
	private Withdraw queryWithdraw;
	private String or_id;

	public String toWithdrawMainPage() {
		return "toWithdrawMainPage";
	}

	@SuppressWarnings("unchecked")
	public void queryPageList() {
		queryWithdraw = queryWithdraw == null ? new Withdraw() : queryWithdraw;
		User user = (User) ActionContext.getContext().getSession()
				.get(Constants.LOGIN_USER);
		queryWithdraw.setWp_created_ur_id(user.getUr_id());
		queryWithdraw.setSp_id(user.getSp_id());
		Integer totalRecords = (Integer) sqlMap.selectOne(
				"WithdrawAction.queryWithdrawCount", queryWithdraw);
		if (totalRecords == null) {
			totalRecords = 0;
		}
		doPage(totalRecords, queryWithdraw);
		List<Object> list = sqlMap.selectList("WithdrawAction.queryPageList",
				queryWithdraw);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalRecords);
		map.put("rows", list);
		writeToJson((String) new JsonSerializer().serialize(map));
	}

	@SuppressWarnings("unchecked")
	public void loadWithdrawOrderDishJsonByOrderId() {
		List<Map<String, Object>> withdrawOrderDishList = sqlMap.selectList(
				"WithdrawAction.queryWithdrawOrderDishList", or_id);
		writeToJson((String) new JsonSerializer()
				.serialize(withdrawOrderDishList));
	}

	@SuppressWarnings("unchecked")
	public void loadOrderDishByOrderId() {
		List<Map<String, Object>> orderDishList = sqlMap.selectList(
				"WithdrawAction.queryOrderDishListByOrderId", or_id);
		writeToJson((String) new JsonSerializer().serialize(orderDishList));
	}

	public Withdraw getAddWithdraw() {
		return addWithdraw;
	}

	public void setAddWithdraw(Withdraw addWithdraw) {
		this.addWithdraw = addWithdraw;
	}

	public Withdraw getQueryWithdraw() {
		return queryWithdraw;
	}

	public void setQueryWithdraw(Withdraw queryWithdraw) {
		this.queryWithdraw = queryWithdraw;
	}

	public String getOr_id() {
		return or_id;
	}

	public void setOr_id(String or_id) {
		this.or_id = or_id;
	}

}
