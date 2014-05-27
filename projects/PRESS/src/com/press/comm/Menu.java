package com.press.comm;

import java.util.List;

public class Menu {
	private String title;
	private List<SubMenu> subMenuList;

	public class SubMenu {
		private String title;
		private String url;
		private String id;
        
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}


	
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SubMenu> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<SubMenu> subMenuList) {
		this.subMenuList = subMenuList;
	}

}
