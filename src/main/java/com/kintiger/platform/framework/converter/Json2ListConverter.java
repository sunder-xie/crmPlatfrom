package com.kintiger.platform.framework.converter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ognl.OgnlOps;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * �ͻ��˽��ӱ�������JSON����ķ�ʽ�ύ������� �������ʵ��JSON��List<E>����֮��Ļ���
 * 
 * @author tingjia.chentj
 * 
 */
public class Json2ListConverter extends StrutsTypeConverter {

	private static final Log log = LogFactory.getLog(Json2ListConverter.class);
	private static final String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	/**
	 * �����е�ʵ������
	 */
	private Class genericType = null;

	@Override
	public Object convertValue(Map context, Object target, Member member,
			String propertyName, Object value, Class toType) {
		// ��ȡ�����е�ʵ������
		if (member instanceof Method) {
			ParameterizedType type = (ParameterizedType) ((Method) member)
					.getGenericParameterTypes()[0];
			Type actualType = type.getActualTypeArguments()[0];
			genericType = (Class) actualType;
		}
		return super.convertValue(context, target, member, propertyName, value,
				toType);
	}

	/**
	 * ��JSON��ʽת����List<E>
	 * 
	 * @param context
	 * @param values
	 *            �ַ������鳤��Ϊ1��values[0]ΪJSON��ʽ��[{...},{...},...{...}]
	 * @param toClass
	 * 
	 * @see com.opensymphony.webwork.util.WebWorkTypeConverter#convertFromString(java.util.Map,
	 *      java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (genericType == null) {
			return null;
		}
		// DefaultTypeConverter.setInstance(new XWorkConverter() {
		// });
		ArrayList<Object> list = new ArrayList<Object>();
		try {
			JSONArray array = new JSONArray(values[0]);
			for (int i = 0; i < array.length(); i++) {
				Object obj = genericType.newInstance();
				Map map = toMap(array.getJSONObject(i));
				PropertyDescriptor[] props = PropertyUtils
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
								log.error(e);
							}
						} else if (java.sql.Date.class.isAssignableFrom(desc
								.getPropertyType())) {
							try {
								value = new java.sql.Date(new SimpleDateFormat(
										DATE_PATTERN).parse(
										(String) map.get(desc.getName()))
										.getTime());
							} catch (ParseException e) {
								log.error(e);
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
				list.add(obj);
			}
		} catch (Exception e) {
			log.error("JSON ����ת������.", e);
		}
		return list;
	}

	/**
	 * ��List<E>ת����JSON��ʽ
	 * 
	 * @param context
	 * @param o
	 * 
	 * @see com.opensymphony.webwork.util.WebWorkTypeConverter#convertToString(java.util.Map,
	 *      java.lang.Object)
	 */
	public String convertToString(Map context, Object o) {
		String json = null;
		if (o != null && o instanceof List) {
			json = new JSONArray((List) o).toString();
		}
		return json;
	}

	// ��JSONObjectת����Map
	private Map toMap(JSONObject object) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Iterator iter = object.keys();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Object val = object.get(key);
			map.put(key, val == null || val.toString().equals("") ? null : val);
		}
		return map;
	}

}
