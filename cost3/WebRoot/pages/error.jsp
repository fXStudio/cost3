<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>错误提示页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
			<link rel="stylesheet" type="text/css" href="styles.css">
		-->
	</head>
	<body>
		<table align='center'>
			<tr>
				<td>
					错误提示:
				</td>
				<td>
					<s:property value="exception.message" />
				</td>
			</tr>
			<tr></tr>
			<tr>
				<td>
					<input type=button name="saveButton" value="返回"
						onclick="javascrip:window.history.back(-1);">
				</td>
			</tr>
		</table>
	</body>
</html>
