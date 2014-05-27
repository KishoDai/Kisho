package com.csms.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestClass {
	public static void main(String[] args) {
//		XSSFWorkbook book = new XSSFWorkbook();
//		XSSFSheet sheet = book.createSheet("xxxxx");
//
//		int rownum = 0;
//		XSSFRow row = sheet.createRow(rownum++);
//		row.setRowStyle(getHeadCellStyle(book));
//		int colnum = 0;
//		XSSFCellStyle cellStyle = getHeadCellStyle(book);
//		createCell(row, cellStyle, colnum++, "时间");
//		createCell(row, cellStyle, colnum++, "店铺编号");
//		createCell(row, cellStyle, colnum++, "店铺名称");
//		createCell(row, cellStyle, colnum++, "菜品分类编号");
//		createCell(row, cellStyle, colnum++, "菜品分类编号");
//		createCell(row, cellStyle, colnum++, "菜品编号");
//		createCell(row, cellStyle, colnum++, "菜品名称");
//		createCell(row, cellStyle, colnum++, "营业额(元)");
//		
//		row = sheet.createRow(rownum++);
//		cellStyle = getBodyCellStyle(book);
//		createCell(row, cellStyle, colnum++, "时间");
//		createCell(row, cellStyle, colnum++, "店铺编号");
//		createCell(row, cellStyle, colnum++, "店铺名称");
//		createCell(row, cellStyle, colnum++, "菜品分类编号");
//		createCell(row, cellStyle, colnum++, "菜品分类编号");
//		createCell(row, cellStyle, colnum++, "菜品编号");
//		createCell(row, cellStyle, colnum++, "菜品名称");
//		createCell(row, cellStyle, colnum++, "营业额(元)");
//		try {
//			book.write(new FileOutputStream(new File("C:\\Users\\HERO\\Desktop\\test.xls")));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("OKDd");
		
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        MonitorRunnabl
//        ExecutorService es = new Executor
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1", "QQ");
		for(Entry<String, Object> entry : map.entrySet()){
			System.out.println(entry.getKey() + "  " + entry.getValue());
		}
//		NullHandler
//		ClassPool
	}
	
	public static void createCell(XSSFRow row, XSSFCellStyle cellStyle,int colnum, String value){
		XSSFCell cell = row.createCell(colnum);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(value);
	}

	private static XSSFCellStyle getHeadCellStyle(XSSFWorkbook book) {
		XSSFCellStyle style = book.createCellStyle();// 创建样式对象
		XSSFFont font = book.createFont();// 创建字体对象
		font.setFontHeightInPoints((short) 14);// 设置字体大小
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
		font.setFontName("宋体");// 设置为黑体字
		style.setFont(font);// 将字体加入到样式对象
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左对齐
//		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);// 设置边框
		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);// 底部边框双线
//		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框
		style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);// 右边边框
		
//		DataSourceUtils
//		SqlSessionUtils.
		return style;
	}

	private static XSSFCellStyle getBodyCellStyle(XSSFWorkbook book) {
		XSSFCellStyle style = book.createCellStyle();// 创建样式对象
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);// 左对齐

		style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);// 设置边框
		style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);// 底部边框双线
		style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);// 左边边框
		style.setBorderRight(HSSFCellStyle.BORDER_THICK);// 右边边框

		return style;
	}

	private static XSSFCellStyle getDateCellStyle(XSSFWorkbook book) {
		XSSFCellStyle cellStyle = getBodyCellStyle(book);
		cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("yyyy-m-d"));
		return cellStyle;
	}
}
