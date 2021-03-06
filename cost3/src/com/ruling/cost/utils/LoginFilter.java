package com.ruling.cost.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	protected FilterConfig config;

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		boolean isValid = true;
		String uriStr = request.getRequestURI();
		if (uriStr.indexOf(".jsp") == -1 && uriStr.indexOf(".html") == -1
				&& uriStr.indexOf(".action") == -1) {
			isValid = true;
		} else if (uriStr.indexOf("login.jsp") == -1
				&& uriStr.indexOf("login.html") == -1
				&& uriStr.indexOf("login.action") == -1
				&& request.getSession().getAttribute("login") == null) {
			isValid = false;
		}

		if (isValid) {
			filterChain.doFilter(request, servletResponse);
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>window.top.location.href ='login.jsp'</script>");
			// if (uriStr.indexOf("login.jsp") == -1) {
			// out.write("<script>window.top.location.href
			// ='/cost/login.jsp'</script>");
			// }
		}

		// HttpServletRequest req = (HttpServletRequest)servletRequest;
		// HttpServletResponse resp = (HttpServletResponse)servletResponse;
		// HttpSession session = req.getSession();
		// String requesturi = req.getRequestURI();
		// if(requesturi.endsWith("css")
		// || requesturi.endsWith("js")
		// || requesturi.endsWith("login.jsp")
		// || requesturi.endsWith("login.action")
		// || requesturi.endsWith("Logout.action")
		// || req.getSession().getAttribute("login") != null){
		//		       
		// filterChain.doFilter(req, resp);
		//		       
		// }else if(req.getSession().getAttribute("login") == null
		// ){
		//		       
		// resp.sendRedirect("/login.jsp");
		// }
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

}
