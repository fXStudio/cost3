<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'error.jsp' starting page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

		<LINK href="<%=basePath%>css/style.css" type=text/css rel=stylesheet>
		<META content="MSHTML 6.00.3790.1830" name=GENERATOR>
	</HEAD>
	<BODY bgcolor="#abd7ff">
		<TABLE border=0 width="928" height="500">
			<TBODY>
				<TR>
					<TD align=middle>
						<FONT color=red><B>你请求的资源不存在，请与系统管理员联系！</B> </FONT>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</BODY>
</HTML>
