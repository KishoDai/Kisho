package com.csms.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.CustOrder;
import com.csms.pojo.User;
import com.csms.service.StepSequence;
import com.csms.utils.Constants;
import com.csms.utils.DateTimeUtils;
import com.csms.utils.UUIDUtils;
import com.opensymphony.xwork2.ActionContext;

@Component("orderAction")
public class OrderAction extends CommonAction<CustOrder> {

	private static final long serialVersionUID = 576829555336908991L;
	
	@Resource
	StepSequence orderStepSequence;
	@Resource
	StepSequence orderDishStepSequence;

	private CustOrder custOrder;
	private String or_id;
	private String dh_id;

	private List<Map<String, Object>> orderDishList;
	private String cd_id;

	/**
	 * 分页查询订单信息
	 */
	public void queryPageList() {
		custOrder = custOrder == null ? new CustOrder() : custOrder;
		custOrder.setSp_id(getUser().getSp_id());
		doPageList("OrderAction.queryCustOrderCount",
				"OrderAction.queryPageList", custOrder);
	}

	/**
	 * 根据订单编号查询订单菜品信息
	 */
	public void loadOrderDishJsonByOrderId() {
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> orderDishList = sqlMap.selectList(
				"WithdrawAction.queryOrderDishListByOrderId", or_id);
		writeToJson((String) new JsonSerializer().serialize(orderDishList));
	}

	/**
	 * 根据店铺编号查询菜品
	 */
	public void loadDishByShopId() {
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> dishList = sqlMap.selectList(
				"OrderAction.queryDishsByShopId", getUser().getSp_id());
		writeToJson((String) new JsonSerializer().serialize(dishList));
	}

	/**
	 * 根据菜品ID查询菜品信息
	 */
	public void loadDishByDishId() {
		@SuppressWarnings("unchecked")
		Map<String, Object> dishMap = (Map<String, Object>) sqlMap.selectOne(
				"OrderAction.queryDishByDishId", dh_id);
		writeToJson((String) new JsonSerializer().serialize(dishMap));
	}

	/**
	 * 添加订单
	 */
	@Transactional
	public void addOrder() {
		if (StringUtils.isNotBlank(requestJsonStr)) {
			Map<String, Object> orderMap = new HashMap<String, Object>();
			orderMap.put("or_id", orderStepSequence.create());
			orderMap.put("or_balance", "");
			orderMap.put("or_status", "0");// 待付款
			orderMap.put("or_people_number", "");
			orderMap.put("cd_id", "");
			orderMap.put("or_datetime",
					DateTimeUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
			User user = (User) ActionContext.getContext().getSession()
					.get(Constants.LOGIN_USER);
			orderMap.put("ur_id", user.getUr_id());
			orderMap.put("sp_id", user.getSp_id());
			BigDecimal or_balance = BigDecimal.ZERO;
			BigDecimal or_balance_discount = BigDecimal.ZERO;
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> orderDishList = (List<Map<String, Object>>) new JsonSerializer()
					.deserialize(requestJsonStr);
			for (Map<String, Object> orderDishMap : orderDishList) {
				String od_amount = (String) orderDishMap.get("od_amount");
				String dh_discount = (String) orderDishMap.get("dh_discount");
				String dh_price = (String) orderDishMap.get("dh_price");
				orderDishMap.put("ID", orderDishStepSequence.create());
				orderDishMap.put("or_id", orderMap.get("or_id"));
				orderDishMap
						.put("od_datetime", DateTimeUtils
								.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
				BigDecimal bd = new BigDecimal(od_amount)
						.multiply(new BigDecimal(dh_price));
				or_balance = or_balance.add(bd);
				or_balance_discount = or_balance_discount.add(bd
						.multiply(new BigDecimal(dh_discount)));
			}
			orderMap.put("or_balance", or_balance_discount);
			sqlMap.insert("OrderAction.addOrder", orderMap);
			sqlMap.insert("OrderAction.addOrderDishList", orderDishList);

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("flag", "0");

			resultMap.put("total", orderDishList.size());
			resultMap.put("rows", orderDishList);
			resultMap.put("od_datetime",
					DateTimeUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
			resultMap.put("or_id", orderMap.get("or_id"));

			final Map<String, Object> footerMap = new HashMap<String, Object>();
			footerMap.put("dh_name", "总计：");
			footerMap.put("od_amount", or_balance.doubleValue());
			final Map<String, Object> discountFooterMap = new HashMap<String, Object>();
			discountFooterMap.put("dh_name", "总折扣：");
			discountFooterMap.put("od_amount",
					or_balance_discount.subtract(or_balance).doubleValue());
			final Map<String, Object> discountBalanceFooterMap = new HashMap<String, Object>();
			discountBalanceFooterMap.put("dh_name", "折后金额：");
			discountBalanceFooterMap.put("od_amount",
					or_balance_discount.doubleValue());
			resultMap.put("footer", new ArrayList<Map<String, Object>>() {
				
				private static final long serialVersionUID = 8713022874470475241L;

				{
					add(footerMap);
					add(discountFooterMap);
					add(discountBalanceFooterMap);
				}
			});
			writeToJson((String) new JsonSerializer().serialize(resultMap));
		}
	}

	/**
	 * 根据订单号查询订单信息
	 */
	@SuppressWarnings({ "serial", "unchecked" })
	public void getOrderDataByOrderId() {
		String or_id = getParameter("or_id");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("flag", "0");
		Map<String, Object> orderMap = (Map<String, Object>) sqlMap.selectOne(
				"OrderAction.queryOrderByOrderId", or_id);
		List<Map<String, Object>> orderDishList = sqlMap.selectList(
				"OrderAction.queryOrderDishList2", or_id);
		resultMap.put("total", orderDishList.size());
		resultMap.put("rows", orderDishList);
		resultMap.put("od_datetime", orderMap.get("OR_DATETIME"));
		resultMap.put("or_id", or_id);
		BigDecimal total_or_balance = BigDecimal.ZERO;
		for (Map<String, Object> orderDishMap : orderDishList) {
			String od_amount = (String) orderDishMap.get("od_amount");
			String dh_price = (String) orderDishMap.get("dh_price");
			BigDecimal bd = new BigDecimal(od_amount).multiply(new BigDecimal(
					dh_price));
			total_or_balance = total_or_balance.add(bd);
		}
		BigDecimal discount_or_balance = new BigDecimal(
				(String) orderMap.get("or_balance"));
		final Map<String, Object> footerMap = new HashMap<String, Object>();
		footerMap.put("dh_name", "总计：");
		footerMap.put("od_amount", total_or_balance.doubleValue());
		final Map<String, Object> discountFooterMap = new HashMap<String, Object>();
		discountFooterMap.put("dh_name", "总折扣：");
		discountFooterMap.put("od_amount",
				total_or_balance.subtract(discount_or_balance).doubleValue());
		final Map<String, Object> discountBalanceFooterMap = new HashMap<String, Object>();
		discountBalanceFooterMap.put("dh_name", "折后金额：");
		discountBalanceFooterMap.put("od_amount", orderMap.get("or_balance"));
		resultMap.put("footer", new ArrayList<Map<String, Object>>() {
			{
				add(footerMap);
				add(discountFooterMap);
				add(discountBalanceFooterMap);
			}
		});
		writeToJson((String) new JsonSerializer().serialize(resultMap));
	}

	/**
	 * 根据卡编号查询美食卡信息
	 */
	public void loadCardJsonByCardId() {
		String cardId = ServletActionContext.getRequest().getParameter("cd_id");
		@SuppressWarnings("unchecked")
		Map<String, Object> cardMap = (Map<String, Object>) sqlMap.selectOne(
				"OrderAction.queryCardByCardId", cardId);

		if (CollectionUtils.isEmpty(cardMap)) {
			writeToJson(new JSON("1",
					getText("OrderAction_loadCardJsonByCardId_failure")));
		} else {
			cardMap.put("flag", "0");
			writeToJson((String) new JsonSerializer().serialize(cardMap));
		}
	}

	/**
	 * 扣费
	 */
	@Transactional
	public void consume() {
		String cd_id = ServletActionContext.getRequest().getParameter("cd_id");
		String od_balance = ServletActionContext.getRequest().getParameter(
				"od_balance");
		String or_id = ServletActionContext.getRequest().getParameter("or_id");
		// 更新订单表
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("or_id", or_id);
		parameterMap.put("cd_id", cd_id);
		parameterMap.put("or_status", "1");

		sqlMap.update("OrderAction.updateOrderCardId", parameterMap);
		// 更新卡余额
		parameterMap.put("od_balance", od_balance);
		sqlMap.update("OrderAction.updateCardBalance", parameterMap);

		// 更新消费记录表
		parameterMap.put("ID", UUIDUtils.getUUIDStr());
		parameterMap.put("cer_operate_balance", od_balance);
		parameterMap.put("cer_operate_type", "2");
		parameterMap.put("cer_datetime",
				DateTimeUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
		User user = (User) ActionContext.getContext().getSession()
				.get(Constants.LOGIN_USER);
		parameterMap.put("ur_id", user.getUr_id());
		sqlMap.update("OrderAction.addCardExpenseRecord", parameterMap);
		writeToJson(new JSON("0", ""));
	}

	/**
	 * 取消订单
	 */
	@Transactional
	public void cancelOrder() {
		String or_id = ServletActionContext.getRequest().getParameter("or_id");
		sqlMap.delete("OrderAction.deleteOrder", or_id);
		sqlMap.delete("OrderAction.deleteOrderDish", or_id);
		writeToJson(new JSON("0", ""));
	}

	public CustOrder getCustOrder() {
		return custOrder;
	}

	public void setCustOrder(CustOrder custOrder) {
		this.custOrder = custOrder;
	}

	public String getOr_id() {
		return or_id;
	}

	public void setOr_id(String or_id) {
		this.or_id = or_id;
	}

	public String getDh_id() {
		return dh_id;
	}

	public void setDh_id(String dh_id) {
		this.dh_id = dh_id;
	}

	public List<Map<String, Object>> getOrderDishList() {
		return orderDishList;
	}

	public void setOrderDishList(List<Map<String, Object>> orderDishList) {
		this.orderDishList = orderDishList;
	}

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

}
