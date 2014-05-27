package com.csms.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.springframework.stereotype.Component;

import com.csms.common.CommonAction;
import com.csms.pojo.Func;
import com.csms.pojo.User;
import com.csms.service.AdminFuncTreeService;
import com.csms.utils.Constants;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("rawtypes")
@Component("systemAction")
public class SystemAction extends CommonAction {

	private static final long serialVersionUID = -1828018471305876093L;

	private String key;

	@Resource
	AdminFuncTreeService adminFuncTreeService;

	public String loadSystemTree() {
		// ThreadPoolTaskExecutor tpte = null;
		// tpte.
		return "systemTree";
	}

	@SuppressWarnings("unchecked")
	public void loadSystemTree4Json() {
		User user = (User) ActionContext.getContext().getSession()
				.get(Constants.LOGIN_USER);
		if ("0".equals(user.getUr_level())) {// 普通用户
			// 根据用户 获取角色，根据角色获取功能
			List<Func> funcs = sqlMap.selectList(
					"SystemAction.queryFuncsByUserId", user.getUr_id());
			final List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
			for (final Func func : funcs) {
				children.add(new HashMap<String, Object>() {
					private static final long serialVersionUID = 7263113758780632423L;

					{
						put("id", func.getFn_id());
						put("text", func.getFn_name());
						put("state", "open");
						put("attributes", new HashMap<String, String>() {
							private static final long serialVersionUID = -1191625907818944682L;

							{
								put("url", func.getFn_url());
							}
						});
					}
				});
			}
			List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>() {

				private static final long serialVersionUID = 1L;
				{
					add(new HashMap<String, Object>() {
						private static final long serialVersionUID = 1L;

						{
							put("id", "0");
							put("text", "餐饮综合服务管理系统树");
							put("state", "open");
							put("children", children);
						}
					});
				}
			};
			writeToJson((String) new JsonSerializer().serialize(treeList));
		} else if ("1".equals(user.getUr_level())) {// 管理员
			writeToJson(adminFuncTreeService.getJsonByFuncGroupId(null, true));
		}
	}

	public void loadJson4Options() {
		String statusJson = (String) sqlMap.selectOne(
				"SystemAction.queryValueByKey", key);
		writeToJson(statusJson);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
