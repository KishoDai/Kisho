package com.csms.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.csms.common.CommonAction;
import com.csms.pojo.Menu;
import com.csms.service.MenuTreeService;

@SuppressWarnings("rawtypes")
@Component("menuAction")
public class MenuAction extends CommonAction {

	private static final long serialVersionUID = -4105834716830178706L;

	@Autowired
	private MenuTreeService menuTreeService;

	private Menu menu;

	public String toMenuMainPage() {
		return "toMenuMainPage";
	}

	public void loadMenuTree() {
		writeToXml(menuTreeService.getXmlTree4Menu());
	}

	public String toModifyMenuPage() {
		menu = (Menu) sqlMap.selectOne("MenuAction.queryMenuByMenuId",
				menu.getMu_id());
		return "toModifyMenuPage";
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Menu getMenu() {
		return menu;
	}

}
