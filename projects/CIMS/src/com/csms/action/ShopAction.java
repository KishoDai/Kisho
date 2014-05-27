package com.csms.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.csms.common.CommonAction;
import com.csms.common.JSON;
import com.csms.pojo.Shop;
import com.csms.pojo.User;
import com.csms.utils.DateTimeUtils;

@Component("shopAction")
public class ShopAction extends CommonAction<Shop> {

	private static final long serialVersionUID = -4105834716830178706L;

	public String toUpdateShopPage() {
		updateEntity = (Shop) sqlMap.selectOne("ShopAction.queryShopByShopId",
				updateEntity.getSp_id());
		return "toUpdateShopPage";
	}

	@Transactional
	public void delShops() {
		String[] sp_ids = getParameterValues("sp_id");
		Map<String, Object> delShopMap = new HashMap<String, Object>();
		delShopMap.put("sp_ids", sp_ids);
		sqlMap.delete("ShopAction.deleteShops", delShopMap);
		writeToJson(new JSON("0", "删除成功!"));
	}

	@Transactional
	public void doAddShop() {
		String existShopId = (String) sqlMap.selectOne(
				"ShopAction.checkShopId", addEntity);
		if (StringUtils.isNotBlank(existShopId)) {
			writeToJson(new JSON("1", "店铺编号已存在"));
		} else {
			addEntity.setSp_update_time(DateTimeUtils
					.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
			sqlMap.insert("ShopAction.addShop", addEntity);
			writeToJson(new JSON("0", "添加成功！"));
		}
	}

	@Transactional
	public void doUpdateShop() {
		updateEntity.setSp_update_time(DateTimeUtils
				.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
		User user = getUser();
		updateEntity.setUr_id(user.getUr_id());
		sqlMap.update("ShopAction.updateShop", updateEntity);
		writeToJson(new JSON("0", "修改成功！"));
	}

	public void queryPageList() {
		queryEntity = queryEntity == null ? new Shop() : queryEntity;
		doPageList("ShopAction.queryShopCount", "ShopAction.queryPageList",
				queryEntity);
	}

	public void loadJson4Shop() {
		@SuppressWarnings("unchecked")
		List<Map<String, String>> shopInfo = sqlMap
				.selectList("ShopAction.queryShop4Json");
		for (Map<String, String> shop : shopInfo) {
			shop.put("text", shop.get("id") + "-" + shop.get("text"));
		}
		writeToJson((String) new JsonSerializer().serialize(shopInfo));
	}

}
