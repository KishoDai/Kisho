package com.press.comm;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import com.press.comm.Menu.SubMenu;
import com.press.util.Dom4jUtil;

public class SysCode {

	private static List<Menu> menuList = new ArrayList<Menu>();
	private static StringBuffer skinsOptions;
	
	static {
		initMenuList();
		initSkinOptions();
	}

	private static void initMenuList() {
         try {
			Document doc = Dom4jUtil.parse(SysCode.class.getResourceAsStream("/config/function_menus.xml"));
			List<Element> elements = Dom4jUtil.getElements(doc, "/menus/menu");
			List<Element> elements2  = null;
			Element element = null;
			SubMenu subMenu = null;
			Menu menu = null;
			List<SubMenu> subMenuList = null;
			if(elements!=null){
				for(int i = 0;i<elements.size();i++){
					element = elements.get(i);
					menu = new Menu();
					menu.setTitle(Dom4jUtil.getAttributeText(element, "title"));
					elements2 = Dom4jUtil.getElements(element, "submenu");
					menu.setSubMenuList(new ArrayList<Menu.SubMenu>());
					if(elements2!=null){
						for(int j =0;j<elements2.size();j++){
							element = elements2.get(j);
							subMenu = menu.new SubMenu();
							subMenu.setTitle(Dom4jUtil.getAttributeText(element, "title"));
							subMenu.setUrl(Dom4jUtil.getAttributeText(element, "url"));
							subMenu.setId(Dom4jUtil.getAttributeText(element, "id"));
							subMenuList = menu.getSubMenuList();
							subMenuList.add(subMenu);
							menu.setSubMenuList(subMenuList);
						}
					}
					menuList.add(menu);
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private static void initSkinOptions() {
		try {
			Document doc = Dom4jUtil.parse(SysCode.class.getResourceAsStream("/config/skins-config.xml"));
			List<Element> elements = Dom4jUtil.getElements(doc, "/skins/skin");
			if(elements!=null){
				skinsOptions = new StringBuffer();
				for(int i = 0;i<elements.size();i++){
					Element element = elements.get(i);
					skinsOptions.append("<option value=\"");
					skinsOptions.append(Dom4jUtil.getAttributeText(element,"value"));
					skinsOptions.append("\">");
					skinsOptions.append(Dom4jUtil.getAttributeText(element, "name"));
					skinsOptions.append("</option>");
				}
		    }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Menu> getMenuList(){
		return menuList;
	}
	
	public static String getSkinOptions(){
		return skinsOptions.toString();
	}
	
	
}
