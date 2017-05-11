package com.kintiger.platform.framework.content.interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.kintiger.platform.allUser.pojo.AllUsers;
import com.kintiger.platform.allUser.service.IAllUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * Ȩ����֤������
 * 
 * ���û���δ��¼������ת����¼ҳ��
 * 
 * 
 */
public class AuthencationInterceptor implements Interceptor {
	private static final long serialVersionUID = -7498838714747075663L;
	private static final String LOGIN_TIMEOUT = "logintimeout";
	private IAllUserService allUserServiceHessian;

	public void init() {
	}

	public void destroy() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// ��֤request if null then ����
		if (request == null) {
			return LOGIN_TIMEOUT;
		}

		Map session = invocation.getInvocationContext().getSession();
		String loginId = (String) session.get("ACEGI_SECURITY_LAST_LOGINID");

		String ps = null;
		String pw = null;

		// loginId if not empty then �Ѿ���½����ϵͳ�����Ǵ����û���ͬ�����
		if (StringUtils.isNotEmpty(loginId)) {
			String[] s = getCookie(request.getCookies());
			ps = s[0];
			pw = s[1];

			// ��֤ps�͵�ǰ��ϵͳsessionһ�£����ͨ��
			if (loginId.equals(ps)) {
				return invocation.invoke();
			} else {
				// ��һ�� ɾ��ԭ��session �ٴ�cookie�����֤
				Enumeration enumeration = request.getSession().getAttributeNames();
				while (enumeration.hasMoreElements()) {
					request.getSession().removeAttribute((String) enumeration.nextElement());
				}
			}
		}

		// ͨ��cookie�����֤
		// if ps is null then ��cookie���»�ȡps pw
		if (StringUtils.isEmpty(ps) || StringUtils.isEmpty(pw)) {
			String[] s = getCookie(request.getCookies());
			ps = s[0];
			pw = s[1];
		}

		// if ps is null then cookie��û����Ϣ ��Ҫ��½
		if (StringUtils.isEmpty(ps) || StringUtils.isEmpty(pw)) {
			return LOGIN_TIMEOUT;
		} else {
			AllUsers loginUser = allUserServiceHessian.getAllUserByPassport(ps);
			request.getSession().setAttribute("ACEGI_SECURITY_LAST_LOGINID", ps);
			request.getSession().setAttribute("ACEGI_SECURITY_LAST_LOGINUSER", loginUser);
			return invocation.invoke();
		}
	}

	private String[] getCookie(Cookie[] cookies) {
		String ps = null;
		String pw = null;
		if (cookies != null && cookies.length != 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if ("PS".equals(cookie.getName())) {
					ps = cookie.getValue();
				}
				if ("PW".equals(cookie.getName())) {
					pw = cookie.getValue();
				}

				if (StringUtils.isNotEmpty(ps) && StringUtils.isNotEmpty(pw)) {
					break;
				}
			}
		}

		return new String[] { ps, pw };
	}

	/**
	 * ������ǰ����actionName
	 * 
	 * @return
	 */
	private String getActionName() {
		String actionName = null;
		// ��ȡ��ǰapplicationContex
		ActionContext ctx = ActionContext.getContext();
		Map map = ctx.getSession();
		// ���õ�ǰ�����URL
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		StringBuffer url = request.getRequestURL();
		int index = url.lastIndexOf(request.getContextPath()) + request.getContextPath().length();
		actionName = url.substring(index, url.length());
		return actionName;
	}

	private String getRequetSessionId() {
		// ��ȡ��ǰapplicationContex
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		return request.getRequestedSessionId();
	}

	private Map getRequestParam() {
		ActionContext ctx = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		return request.getParameterMap();
	}

	public IAllUserService getAllUserServiceHessian() {
		return allUserServiceHessian;
	}

	public void setAllUserServiceHessian(IAllUserService allUserServiceHessian) {
		this.allUserServiceHessian = allUserServiceHessian;
	}

}
