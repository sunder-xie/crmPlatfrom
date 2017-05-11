package com.kintiger.platform.framework.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import ognl.OgnlOps;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;
import com.kintiger.platform.base.pojo.OaXmlBean;
import com.opensymphony.xwork2.util.OgnlUtil;

public class XmlUtil {

	private static final Logger logger = Logger.getLogger(XmlUtil.class);

	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * ͨ��DOCUMENT�õ�һ��ELement����
	 * 
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getElementsFromDocument(Document document,
			String xpath) throws DocumentException {

		return document.selectNodes(xpath);
	}

	/**
	 * ��һ���ַ����õ�һ��DOCUMENT
	 * 
	 * @param inputStr
	 * @return
	 * @throws DocumentException
	 */
	public static Document getDocumentByStr(String inputStr)
			throws DocumentException {
		return DocumentHelper.parseText(inputStr);
	}

	public static Document getDocumentByFile(String filename)
			throws DocumentException, FileNotFoundException,
			UnsupportedEncodingException {
		SAXReader reader = new SAXReader();
		FileInputStream inputStream = new FileInputStream(new File(filename));
		InputStreamReader in = new InputStreamReader(inputStream, "gbk");//gbk//utf-8
		Document document = reader.read(in);
		return document;
	}

	/**
	 * ͨ��DOCUMENT�õ�����ELEMENT
	 * 
	 * @param document
	 * @param xpath
	 * @return
	 * @throws DocumentException
	 */
	public static Element getElementFromDocument(Document document, String xpath)
			throws DocumentException {
		if (getElementsFromDocument(document, xpath) == null)
			return null;
		return getElementsFromDocument(document, xpath).size() > 0 ? getElementsFromDocument(
				document, xpath).get(0)
				: null;
	}

	/**
	 * ����ELEMENT�õ�һ������
	 * 
	 * @param atrName
	 *            ������
	 * @param element
	 *            �����ELEMENT
	 * @return
	 */
	public static Attribute getAttributeByElement(String atrName,
			Element element) {
		if (element == null)
			return null;
		return element.attribute(atrName);
	}

	/**
	 * ����ELEMENT�õ���������ֵ
	 * 
	 * @param atrName
	 * @param element
	 * @return
	 */
	public static String getAttributeTextByElement(String atrName,
			Element element) {
		if (element == null)
			return null;
		return element.attribute(atrName).getText();
	}

	/**
	 * ��ȡxml
	 * 
	 * @param path
	 * @param oaXmlBean
	 * @param type
	 *            xml��root�ڵ�ĳһ������
	 * @return
	 */
	public static List<OaXmlBean> readXml(String path, OaXmlBean oaXmlBean,
			String type) {
		List<OaXmlBean> list = new ArrayList<OaXmlBean>();
		OaXmlBean oab;
		try {
			// xml�ļ�
			File f = new File(path);
			// ����dom
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// �����ļ�
			org.w3c.dom.Document doc = builder.parse(f);
			if (StringUtils.isNotEmpty(type)) {
				org.w3c.dom.Element element = doc.getDocumentElement();
				String str = element.getAttribute(type);
				oab = new OaXmlBean();
				oab.setParameter(str);
				list.add(oab);
			} else {
				// ֱ�Ӷ�ȡ����ڵ�ΪVALUE��
				NodeList nl = doc
						.getElementsByTagName(oaXmlBean.getParameter());
				for (int i = 0; i < nl.getLength(); i++) {
					oab = new OaXmlBean();
					String value = null;
					if ((value = getFirstChildValue(doc,
							oaXmlBean.getParameter1(), i)) != null) {
						oab.setParameter1(value);
					}
					if ((value = getFirstChildValue(doc,
							oaXmlBean.getParameter2(), i)) != null) {
						oab.setParameter2(value);
					}
					if ((value = getFirstChildValue(doc,
							oaXmlBean.getParameter3(), i)) != null) {
						oab.setParameter3(value);
					}
					list.add(oab);
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	private static String getFirstChildValue(org.w3c.dom.Document doc,
			String tagName, int index) {
		try {
			return doc.getElementsByTagName(tagName).item(index)
					.getFirstChild().getNodeValue();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * ��xml�ַ���ת��javabean
	 * 
	 * @param xmlValue
	 *            Ҫת����xml�ַ���
	 * @param toClass
	 *            Ҫת����������
	 * @param childMap
	 *            �������м����ֶε��ֶ����ͼ���Ԫ������map
	 * @return Object
	 */
	public static Object xml2Bean(Class toClass, String xmlValue,
			Map<String, Class> childMap) {

		Map<String, Object> map = toMap(xmlValue);
		try {
			Object obj = toClass.newInstance();
			PropertyDescriptor[] props = OgnlUtil.getPropertyDescriptors(obj);
			for (PropertyDescriptor desc : props) {
				if (map.containsKey(desc.getName())
						|| childMap.containsKey(desc.getName())) {

					Object value = null;
					if (java.util.Date.class.isAssignableFrom(desc
							.getPropertyType())) {
						try {
							String date = (String) map.get(desc.getName());
							if (date.contains(":")) {
								value = new SimpleDateFormat(DATE_PATTERN)
										.parse(date);
							} else {
								value = new SimpleDateFormat("yyyy-MM-dd")
										.parse(date);
							}
						} catch (ParseException e) {
							logger.error(e);
						}
					} else if (java.sql.Date.class.isAssignableFrom(desc
							.getPropertyType())) {
						try {
							String date = (String) map.get(desc.getName());
							if (date.contains(":")) {
								value = new java.sql.Date(new SimpleDateFormat(
										DATE_PATTERN).parse(date).getTime());
							} else {
								value = new java.sql.Date(new SimpleDateFormat(
										"yyyy-MM-dd").parse(date).getTime());
							}
						} catch (ParseException e) {
							logger.error(e);
						}
					} else if (Collection.class.isAssignableFrom(desc
							.getPropertyType())) {
						Class clz = childMap.get(desc.getName());
						if (clz.isPrimitive()
								|| CharSequence.class.isAssignableFrom(clz)
								|| Number.class.isAssignableFrom(clz)) {
							value = convertFromStringListSimple(
									map.get("items"), clz);
						} else {
							value = convertFromStringListComplex(
									map.get("items"), clz);
						}
					} else {
						if (desc.getPropertyType().isPrimitive()
								|| CharSequence.class.isAssignableFrom(desc
										.getPropertyType())
								|| Number.class.isAssignableFrom(desc
										.getPropertyType())) {
							value = OgnlOps.convertValue(
									map.get(desc.getName()),
									desc.getPropertyType());
						} else {
							value = xml2Bean(desc.getPropertyType(),
									map.get(desc.getName()).toString(), null);
						}
					}

					desc.getWriteMethod().invoke(obj, new Object[] { value });
				}
			}
			return obj;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			logger.error("xml xml2Bean()��������.", e);
			return null;
		}
	}

	private static Object convertFromStringListSimple(Object list, Class toClass) {
		if (toClass == null) {
			return null;
		}
		ArrayList<Map> param = (ArrayList<Map>) list;
		ArrayList<Object> result = new ArrayList<Object>();
		try {
			int i;
			Object value = null;
			for (i = 0; i < param.size(); i++) {
				Map property = param.get(i);
				if (java.util.Date.class.isAssignableFrom(toClass)) {
					try {
						value = new SimpleDateFormat(DATE_PATTERN)
								.parse((String) property.get("item"));
					} catch (ParseException e) {
						logger.error(e);
					}
				} else if (java.sql.Date.class.isAssignableFrom(toClass)) {
					try {
						value = new java.sql.Date(new SimpleDateFormat(
								DATE_PATTERN).parse(
								(String) property.get("item")).getTime());
					} catch (ParseException e) {
						logger.error(e);
					}
				} else {
					value = OgnlOps.convertValue(property.get("item"), toClass);
					result.add(value);
				}
			}
			return result;
		} catch (Exception e) {
			logger.error("xml �򵥶�������ת������", e);
		}
		return null;
	}

	private static Object convertFromStringListComplex(Object list,
			Class toClass) {
		if (toClass == null) {
			return null;
		}
		ArrayList<Map> param = (ArrayList<Map>) list;
		ArrayList<Object> result = new ArrayList<Object>();
		try {
			for (int i = 0; i < param.size(); i++) {
				Object obj = toClass.newInstance();
				Map map = param.get(i);
				PropertyDescriptor[] props = OgnlUtil
						.getPropertyDescriptors(obj);
				for (PropertyDescriptor desc : props) {
					if (map.containsKey(desc.getName())) {
						Object value = null;
						// ����������
						// "yyyy-MM-dd'T'HH:mm:ss'Z'"�˸�ʽ����json2.jsת�����Date��ʽ
						if (java.util.Date.class.isAssignableFrom(desc
								.getPropertyType())) {
							try {
								value = new SimpleDateFormat(DATE_PATTERN)
										.parse((String) map.get(desc.getName()));
							} catch (ParseException e) {
								logger.error(e);
							}
						} else if (java.sql.Date.class.isAssignableFrom(desc
								.getPropertyType())) {
							try {
								value = new java.sql.Date(new SimpleDateFormat(
										DATE_PATTERN).parse(
										(String) map.get(desc.getName()))
										.getTime());
							} catch (ParseException e) {
								logger.error(e);
							}
						} else {
							value = OgnlOps.convertValue(
									map.get(desc.getName()),
									desc.getPropertyType());

						}
						desc.getWriteMethod().invoke(obj,
								new Object[] { value });
					}
				}
				result.add(obj);
			}
		} catch (Exception e) {
			logger.error("xml ���Ӷ�������ת������.", e);
		}
		return result;
	}

	public static Map<String, Object> toMap(String xmlValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Document doc = DocumentHelper.parseText(xmlValue);
			Element root = doc.getRootElement();
			Iterator i = root.elementIterator();
			ArrayList<Map> items = new ArrayList<Map>();
			while (i.hasNext()) {
				Element e = (Element) i.next();
				if (e.nodeCount() != 0) {
					if (e.getName().equalsIgnoreCase("items")) {
						Iterator j = e.elementIterator();
						while (j.hasNext()) {
							Map<String, Object> item = new HashMap<String, Object>();
							Element ie = (Element) j.next();							
							Iterator k = ie.elementIterator();
							// ����������
							if (!k.hasNext()) {
								item.put(ie.getName(), ie.getTextTrim());
							}
							// ���Ӷ�����������
							while (k.hasNext()) {
								Element pro = (Element) k.next();
								if (!pro.getTextTrim().equals("")) {
									item.put(pro.getName(), pro.getTextTrim());
								}
							}
							if (!item.isEmpty()) {
								items.add(item);
							}
						}
						if (items.size() > 0) {
							map.put("items", items);
						}
					} else {
						map.put(e.getName(), e.getTextTrim());
					}
				}
			}
		} catch (DocumentException e) {
			logger.error("xml toMap��������.", e);
			return null;
		}
		return map;
	}
	
	
	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><expTravel>"
			+ "<property>SDF</property>"
			+ "<user_id>34567</user_id>"
			+ "<items>"
			+ "<��>"
			+ "<A>Hello</A><B>World</B>"
			+ "</��><˧>F</˧><��>X</��>"
			+ "</items>"
			+ "</expTravel>";
		Map<String, Object> m = XmlUtil.toMap(xml);
		Iterator it = m.keySet().iterator();
		
		System.out.println(m);
		
		while(it.hasNext()){
			String key = (String) it.next();
			System.out.println(key + " = " + m.get(key));
		}
	}
}

