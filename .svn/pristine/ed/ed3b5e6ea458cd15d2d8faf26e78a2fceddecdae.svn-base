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
 * 权限验证拦截器
 * 
 * 若用户尚未登录，则跳转到登录页面
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
		// 验证request if null then 重新
		if (request == null) {
			return LOGIN_TIMEOUT;
		}

		Map session = invocation.getInvocationContext().getSession();
		String loginId = (String) session.get("ACEGI_SECURITY_LAST_LOGINID");

		String ps = null;
		String pw = null;

		// loginId if not empty then 已经登陆过子系统，但是存在用户不同的情况
		if (StringUtils.isNotEmpty(loginId)) {
			String[] s = getCookie(request.getCookies());
			ps = s[0];
			pw = s[1];

			// 验证ps和当前子系统session一致，免登通过
			if (loginId.equals(ps)) {
				return invocation.invoke();
			} else {
				// 不一致 删除原有session 再次cookie免登验证
				Enumeration enumeration = request.getSession().getAttributeNames();
				while (enumeration.hasMoreElements()) {
					request.getSession().removeAttribute((String) enumeration.nextElement());
				}
			}
		}

		// 通过cookie免登验证
		// if ps is null then 从cookie重新获取ps pw
		if (StringUtils.isEmpty(ps) || StringUtils.isEmpty(pw)) {
			String[] s = getCookie(request.getCookies());
			ps = s[0];
			pw = s[1];
		}

		// if ps is null then cookie中没有信息 需要登陆
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
	 * 解析当前请求actionName
	 * 
	 * @return
	 */
	private String getActionName() {
		String actionName = null;
		// 获取当前applicationContex
		ActionContext ctx = ActionContext.getContext();
		Map map = ctx.getSession();
		// 设置当前请求的URL
		HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
		StringBuffer url = request.getRequestURL();
		int index = url.lastIndexOf(request.getContextPath()) + request.getContextPath().length();
		actionName = url.substring(index, url.length());
		return actionName;
	}

	private String getRequetSessionId() {
		// 获取当前applicationContex
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
