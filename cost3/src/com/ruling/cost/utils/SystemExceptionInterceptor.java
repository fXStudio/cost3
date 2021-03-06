package com.ruling.cost.utils;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

@SuppressWarnings("serial")
public class SystemExceptionInterceptor extends AbstractInterceptor {

	private String error;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (SystemException systemException) {
			ValueStack valueStack = invocation.getInvocationContext()
					.getValueStack();
			ActionSupport actionSupport = (ActionSupport) invocation
					.getAction();

			exception(systemException, actionSupport, valueStack);

			return Action.ERROR;
		}
	}

	private void exception(SystemException systemException,
			ActionSupport action, ValueStack valueStack) {
		// ȡ��keyֵ
		String messageKey = systemException.getMessageKey();
		String[] args = systemException.getMessageArgs();

		if (messageKey == null) {
			error = systemException.getMessage();
		} else if (args != null && args.length > 0) {
			error = action.getText(messageKey, args);
		} else {
			error = action.getText(messageKey);
		}
		valueStack.set("error", error);

	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
