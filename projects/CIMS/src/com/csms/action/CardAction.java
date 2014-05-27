package com.csms.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.Card;
import com.csms.utils.DateTimeUtils;
import com.csms.utils.UUIDUtils;

/**
 * This class is used for managing card information.
 * 
 * @author HERO
 * 
 */
@Component("cardAction")
public class CardAction extends CommonAction<Card> {

	private static final long serialVersionUID = -4467384158977842882L;

	public void getJsonCardByCardId() {
		Card card = (Card) sqlMap.selectOne("CardAction.queryCardByCardId",
				getParameter("cd_id"));
		writeToJson(card);
	}

	@Transactional
	public void returnCard() {
		Card card = (Card) sqlMap.selectOne("CardAction.queryCardByCardId",
				getParameter("returnCard_cd_id"));
		if (card == null) {
			writeToJson(new JSON("1",
					getText("CardAction_returnCard_failure_1")));
			return;
		}
		if ("0".equals(card.getCd_type())) {
			writeToJson(new JSON("2",
					getText("CardAction_returnCard_failure_2")));
			return;
		}
		sqlMap.update("CardAction.updateCardBalance2",
				getParameter("returnCard_cd_id"));
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		parameterMap.put("ID", UUIDUtils.getUUIDStr());
		parameterMap.put("cd_id", getParameter("returnCard_cd_id"));
		parameterMap.put("cer_operate_balance", card.getCd_balance());
		parameterMap.put("cer_operate_type", "8");
		parameterMap.put("cer_datetime",
				DateTimeUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
		parameterMap.put("ur_id", getUser().getUr_id());
		sqlMap.update("OrderAction.addCardExpenseRecord", parameterMap);
		writeToJson(new JSON("0", getText("CardAction_returnCard_success")));
	}

	@Transactional
	public void addValue() {
		Map<String, Object> addValueMap = new HashMap<String, Object>();
		addValueMap.put("addValueCard_cd_id",
				getParameter("addValueCard_cd_id"));
		addValueMap.put("addValueCard_balance",
				getParameter("addValueCard_balance"));
		sqlMap.update("CardAction.updateCardBalance", addValueMap);
		// 美食卡充值记录
		// 更新消费记录表
		addValueMap.put("ID", UUIDUtils.getUUIDStr());
		addValueMap.put("cd_id", getParameter("addValueCard_cd_id"));
		addValueMap.put("cer_operate_balance",
				getParameter("addValueCard_balance"));
		addValueMap.put("cer_operate_type", "1");
		addValueMap.put("cer_datetime",
				DateTimeUtils.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
		addValueMap.put("ur_id", getUser().getUr_id());
		sqlMap.update("OrderAction.addCardExpenseRecord", addValueMap);

		writeToJson(new JSON("0", getText("CardAction_addValue_sucess")));
	}

	/**
	 * 增加美食卡
	 */
	@Transactional
	public void addCard() {
		Card card = (Card) sqlMap.selectOne("CardAction.queryCardByCardId",
				addEntity.getCd_id());
		if (card != null) {
			writeToJson(new JSON("1", getText("CardAction_addCard_failure")));
			return;
		}
		addEntity.setCd_send_datetime(DateTimeUtils
				.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
		addEntity.setCd_balance("0");
		sqlMap.insert("CardAction.addCard", addEntity);
		writeToJson(new JSON("0", getText("CardAction_addCard_success")));
	}

	public void queryPageList() {
		queryEntity = queryEntity == null ? new Card() : queryEntity;
		doPageList("CardAction.queryCardCount", "CardAction.queryPageList",
				queryEntity);
	}

}
