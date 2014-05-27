/**
 * @(#)JdomUtil.java 2010-9-12 下午09:54:08
 * Copyright 2010 Michael. All rights reserved.
 */
package com.csms.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.XSLTransformException;
import org.jdom.transform.XSLTransformer;
import org.jdom.xpath.XPath;

public class JdomUtil {
	private static SAXBuilder builder;
	private static XMLOutputter output;
	static {
		builder = new SAXBuilder();
		output = new XMLOutputter();
	}

	public static Element getElement(Document doc, String path)
			throws JDOMException {
		if (doc == null || path == null) {
			return null;
		}
		return (Element) XPath.selectSingleNode(doc, path);
	}

	public static Element getElement(Element ele, String path)
			throws JDOMException {
		if (ele == null || path == null) {
			return null;
		}
		return (Element) XPath.selectSingleNode(ele, path);
	}

	@SuppressWarnings("unchecked")
	public static List<Element> getElements(Document doc, String path)
			throws JDOMException {
		if (doc == null || path == null) {
			return null;
		}
		return XPath.selectNodes(doc, path);
	}

	@SuppressWarnings("unchecked")
	public static List<Element> getElements(Element ele, String path)
			throws JDOMException {
		if (ele == null || path == null) {
			return null;
		}
		return XPath.selectNodes(ele, path);
	}

	public static String getElementText(Document doc, String path)
			throws JDOMException {
		if (doc == null) {
			return null;
		}
		if (path != null) {
			getElement(doc, path).getText();
		}
		return doc.toString();
	}

	public static String getElementText(Element ele, String path)
			throws JDOMException {
		if (ele == null) {
			return null;
		}
		if (path != null) {
			getElement(ele, path).getText();
		}
		return ele.getText();
	}

	public static String getAttributeText(Element ele, String attributeName) {
		if (ele == null || attributeName == null) {
			return null;
		}
		return ele.getAttributeValue(attributeName);
	}

	public static Document createDocument() {
		return new Document();
	}

	public static Document createDocument(Element root) {
		if (root == null) {
			createDocument();
		}
		return createDocument().setRootElement(root);
	}

	public static Element createElement(String name) {
		if (name == null) {
			return null;
		}
		return new Element(name);
	}

	public static Element createElement(String name, String text) {
		if (name == null) {
			return null;
		}
		if (text == null) {
			return createElement(name);
		}
		return createElement(name).setText(text);
	}

	public static Attribute createAttribute(String name, String text) {
		if (name == null) {
			return null;
		}
		return new Attribute(name, text);
	}

	public static Element setAttribute(Element ele, Attribute attribute) {
		if (ele == null) {
			return null;
		}
		return ele.setAttribute(attribute);
	}

	public static Element setAttribute(Element ele, String name, String text) {
		if (ele == null) {
			return null;
		}
		return ele.setAttribute(createAttribute(name, text));
	}

	public static Element appendChild(Element parent, Element child) {
		if (parent == null) {
			return null;
		}
		return parent.addContent(child);
	}

	public static Element appendChildren(Element parent, List<Element> children) {
		if (parent == null) {
			return null;
		}
		if (children != null) {
			for (Element child : children) {
				appendChild(parent, child);
			}
		}
		return parent;
	}

	public static Document parse(String xmlStr) throws JDOMException,
			IOException {
		return builder.build(new StringReader(xmlStr));
	}

	public static Document parse(Reader reader) throws JDOMException,
			IOException {
		return builder.build(reader);
	}

	public static Document parse(File file) throws JDOMException, IOException {
		return builder.build(file);
	}

	public static Document parse(InputStream in) throws JDOMException,
			IOException {
		return builder.build(in);
	}

	public static String transform(String xmlStr, String xslStr)
			throws XSLTransformException, JDOMException, IOException {
		return toXML(new XSLTransformer(parse(xslStr)).transform(parse(xmlStr)));
	}

	public static String transform(Document xmlDoc, Document xslDoc)
			throws XSLTransformException {
		return toXML(new XSLTransformer(xslDoc).transform(xmlDoc));
	}

	public static String transform(String xmlStr, Document xslDoc)
			throws JDOMException, IOException {
		return toXML(new XSLTransformer(xslDoc).transform(parse(xmlStr)));
	}

	public static String transform(Document xmlDoc, String xslStr)
			throws JDOMException, IOException {
		return toXML(new XSLTransformer(parse(xslStr)).transform(xmlDoc));
	}

	public static String toXML(Document doc) {
		return output.outputString(doc);
	}

	public static String toXML(Element ele) {
		return output.outputString(ele);
	}

	public static void write(Document doc, OutputStream out) throws IOException {
		output.output(doc, out);
	}

	public static void write(Document doc, Writer out) throws IOException {
		output.output(doc, out);
	}

	public static void write(Element ele, OutputStream out) throws IOException {
		output.output(ele, out);
	}

	public static void write(Element ele, Writer out) throws IOException {
		output.output(ele, out);
	}

}
