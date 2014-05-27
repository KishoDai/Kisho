package org.coco.test.apache.lang;

import java.util.Arrays;
import java.util.Calendar;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.CharSetUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import com.ibm.db2.jcc.am.i;

public class TestLang {
	
	static {
		i = 100;
	}
	
	private static int i = 1;
	public static void main(String[] args) {
		
		
		System.out.println(i);
//		System.out.println(DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd"));
//		System.out.println(FastDateFormat.getInstance("yyyy-MM-dd").get);
//		System.out.println(RandomStringUtils.random(10));
//		System.out.println(RandomStringUtils.);
//		System.out.println(SystemUtils.getUserHome().getAbsolutePath());
//		System.out.println(SystemUtils.getJavaIoTmpDir().getAbsolutePath());
//		System.out.println(SystemUtils.IS_JAVA_1_7);
//		new JSONSerializer().
	}
}
