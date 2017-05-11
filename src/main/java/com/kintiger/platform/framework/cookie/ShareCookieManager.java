package com.kintiger.platform.framework.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaodan.daixd ����cookie�����ں������û����
 * 
 */
public class ShareCookieManager extends CookieManagerBase {

	private static final String COOKIE_EUP_TMP = "FI_SHARE_TMP"; // cookie����

	public static final String ESHOP_K_LOGIN_FLAG = "ESHOP_K_LOGIN_FLAG";

	public ShareCookieManager(HttpServletRequest request,
			HttpServletResponse response, String domain, String path) {
		super(request, response, domain, path);
	}

	public ShareCookieManager(HttpServletRequest request) {
		this(request, null, null, null);
	}

	/**
	 * �����ʱcookie�е�һ����ֵ
	 */
	public String getTempCookie(String key) {
		return getTempCookie(key, null);
	}

	/**
	 * �����ʱcookie�е�һ����ֵ����Ϊ�գ��򷵻�defaultValue
	 */
	public String getTempCookie(String key, String defaultValue) {
		return getValue(COOKIE_EUP_TMP, key, defaultValue);
	}

	/**
	 * ������ʱcookie��һ����ֵ����valueΪ�գ����൱��removeTempCookie
	 */
	public void setTempCookie(String key, String value) {
		setValue(COOKIE_EUP_TMP, key, value);
	}

	/**
	 * ɾ��һ����ʱcookie��
	 */
	public void removeTempCookie(String key) {
		setValue(COOKIE_EUP_TMP, key, null);
	}

	/**
	 * ������ʱcookie�����е������д��response
	 */
	public void saveTempCookie() {
		save(COOKIE_EUP_TMP, TEMP_COOKIE_AGE);
	}

	public void removeCookie() {
		remove(COOKIE_EUP_TMP);
	}

}
