package org.coco.test;

import java.util.HashMap;
import java.util.Map;


public class Util {
	
	private static Map<String, Object> branchInfoMap = null;
	public Util(){
		System.out.println("创建Util实例　：　" + this);
	}
	
	static {
		if(branchInfoMap == null){
			branchInfoMap = new HashMap<String, Object>();
			branchInfoMap.put("110999", "1");
		}
	}
	

}
