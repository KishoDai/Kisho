/**
 * @(#) Dom4jUtils.java 2010-9-12 下午03:02:30
 * Copyright 2010 Michael. All rights reserved.
 * 
 */
package com.csms.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * 使用Dom4jUtil解析XML的工具类
 * 
 * @author Michael
 * 
 */
public class Dom4jUtil {
	/** 用于读取XML */
	private static SAXReader reader;
	/** 用于写入XML */
	private static XMLWriter writer;
	/** 用于创建元素 */
	private static DocumentFactory factory;
	static {
		reader = new SAXReader();
		writer = new XMLWriter();
		factory = DocumentFactory.getInstance();
	}

	/**
	 * 从Document doc对象中获取String path路径的元素，
	 * <p>
	 * <code>String path="/root/elementName";</code>
	 * </p>
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-13 下午06:35:25
	 * @param Document
	 *            doc
	 * @param String
	 *            path
	 * @return Element
	 */
	public static Element getElement(Document doc, String path) {
		if (doc == null || path == null) {
			return null;
		}
		
		return (Element) doc.selectSingleNode(path);
	}

	/**
	 * 从Element ele对象中获取String path路径的元素，
	 * <p>
	 * <code>String path="elementName";</code>
	 * </p>
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-13 下午06:35:25
	 * @param Element
	 *            ele
	 * @param String
	 *            path
	 * @return Element
	 */
	public static Element getElement(Element ele, String path) {
		if (ele == null || path == null) {
			return null;
		}
		return (Element) ele.selectSingleNode(path);
	}

	/**
	 * 从Document doc对象中获取String path路径的元素列表，
	 * <p>
	 * <code>String path="/root/elementName";</code>
	 * </p>
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-13 下午06:35:25
	 * @param Document
	 *            doc
	 * @param String
	 *            path
	 * @return List<Element>
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getElements(Document doc, String path) {
		if (doc == null || path == null) {
			return null;
		}
		return doc.selectNodes(path);
	}

	/**
	 * 从Element ele对象中获取String path路径的元素列表，
	 * <p>
	 * <code>String path="elementName";</code>
	 * </p>
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-13 下午06:35:25
	 * @param Element
	 *            ele
	 * @param String
	 *            path
	 * @return List<Element>
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getElements(Element ele, String path) {
		if (ele == null || path == null) {
			return null;
		}
		return ele.selectNodes(path);
	}

	/**
	 * 从Document doc对象中获取String path路径的元素文本值,
	 * <p>
	 * 如果<code>doc==null</code>则返回的是doc的文本值
	 * </p>
	 * <p>
	 * <code>String path="/root/elementName";</code>
	 * </p>
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-13 下午06:35:25
	 * @param Document
	 *            doc
	 * @param String
	 *            path
	 * @return String
	 */
	public static String getElementText(Document doc, String path) {
		if (doc == null) {
			return null;
		}
		if (path != null) {
			Element ele = (Element) doc.selectSingleNode(path);
			return ele.getText();
		}
		return doc.getText();
	}

	/**
	 * 从Element ele对象中获取String path路径的元素文本值,
	 * <p>
	 * 如果<code>path==null</code>则返回的是ele的文本值
	 * </p>
	 * <p>
	 * <code>String path="elementName";</code>
	 * </p>
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-13 下午06:35:25
	 * @param Element
	 *            ele
	 * @param String
	 *            path
	 * @return String
	 */
	public static String getElementText(Element ele, String path) {
		if (ele == null) {
			return null;
		}
		if (path != null) {
			ele = (Element) ele.selectSingleNode(path);
		}
		return ele.getText();
	}

	/**
	 * 从Element ele对象中获取属性名为String attributeName的属性值,
	 * 
	 * @author Michael
	 * @since 1.0 2010-9-13 下午06:35:25
	 * @param Element
	 *            ele
	 * @param String
	 *            attributeName 属性名
	 * @return String
	 */
	public static String getAttributeText(Element ele, String attributeName) {
		if (ele == null || attributeName == null) {
			return null;
		}
		return ele.attributeValue(attributeName);
	}

	public static Document createDocument() {
		return factory.createDocument();
	}

	public static Document createDocument(Element root) {
		return factory.createDocument(root);
	}

	public static Element createElement(String name) {
		return factory.createElement(name);
	}

	public static Element createElement(String name, String text) {
		if (name == null) {
			return null;
		}
		Element ele = createElement(name);
		ele.setText(text);
		return ele;
	}

	public static Attribute createAttribute(String name, String value) {
		if (name == null) {
			return null;
		}
		return factory.createAttribute(null, name, value);
	}

	public static void setAttribute(Element ele, Attribute attribute) {
		if (ele == null) {
			throw new NullPointerException();
		}
		List<Attribute> list = new ArrayList<Attribute>();
		list.add(attribute);
		ele.setAttributes(list);
	}

	public static void setAttribute(Element ele, String name, String value) {
		if (ele == null) {
			throw new NullPointerException();
		}
		setAttribute(ele, createAttribute(name, value));
	}

	public static void appendChild(Element parent, Element child) {
		if (parent == null) {
			throw new NullPointerException("父元素为空！");
		}
		if (child == null) {
			throw new NullPointerException("子元素为空！");
		}
		parent.add(child);
	}

	public static void appendChildren(Element parent, List<Element> children) {
		if (children != null) {
			if (children == null) {
				throw new NullPointerException("子元素列表为空！");
			}
			for (Element child : children) {
				appendChild(parent, child);
			}
		}
	}

	public static Document parse(File file) throws DocumentException {
		return reader.read(file);
	}

	public static Document parse(InputStream in) throws DocumentException {
		return reader.read(in);
	}

	public static Document parse(String xmlStr) throws DocumentException {
		return reader.read(new StringReader(xmlStr));
	}

	public static Document parse(Reader read) throws DocumentException {
		return reader.read(read);
	}

	public static String toXML(Element ele) {
		return ele.asXML();
	}

	public static String toXML(Document doc) {
		return doc.asXML();
	}

	public static void write(OutputStream out, Document doc) throws IOException {
		writer.setOutputStream(out);
		writer.write(doc);
		writer.close();
	}

	public static void write(Writer out, Document doc) throws IOException {
		writer.setWriter(out);
		writer.write(doc);
		writer.close();
	}

	public static void write(OutputStream out, Element ele) throws IOException {
		writer.setOutputStream(out);
		writer.write(ele);
		writer.close();
	}

	public static void write(Writer out, Element ele) throws IOException {
		writer.setWriter(out);
		writer.write(ele);
		writer.close();
	}

}
