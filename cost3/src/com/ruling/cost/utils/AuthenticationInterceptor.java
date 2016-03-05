package com.ruling.cost.utils;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 系统权限拦截器
 * 
 * @author ajaxfan (FXStudio.com.cn)
 */
@SuppressWarnings("serial")
public class AuthenticationInterceptor extends AbstractInterceptor {

	/**
	 * 拦截启动请求，检查操作者是否为登录用户
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 获得Session对象
		Map session = ActionContext.getContext().getSession();
		
		// 检查Session信息，看用户是否已经登录
		Object user = session.get("login");
		
		// 如果用户没有登录到系统，则跳转到登录页
		if (user == null) {
			ActionSupport action = (ActionSupport) invocation.getAction();

			action.addActionError("你还没有登录，不能访问资源!请先登录");

//			return Action.LOGIN;
			return "login";
		} else {
			return invocation.invoke();
		}
	}
}
