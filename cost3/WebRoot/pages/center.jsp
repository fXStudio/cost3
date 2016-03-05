<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE2 {
	font-size: 14px;
	font-weight: bold;
}

.STYLE3 {
	font-size: 12px
}
-->
</style>
		<link href="text.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
<!--
.STYLE6 {
	color: #FFFFFF;
	font-size: 14px;
	font-weight: bold;
}

.STYLE8 {
	font-size: 14px
}

.STYLE10 {
	color: #FFFFFF
}
-->
</style>
	</head>

	<body>
		<table width="339" height="300" border="0" align="center"
			cellpadding="0" cellspacing="0">
			<tr>
				<td width="141">
					<a href='<%=basePath%>user/browe.action' target="main"><img
							src='<%=basePath%>images/pic_12.jpg' border=0 width="138"
							height="73"></img>
					</a>
				</td>
				<c:if test="${login.type eq 'system'}">
					<td width="86" colspan="2">
						<a href='<%=basePath%>item/browe.action' target="main"><img
								src='<%=basePath%>images/pic_13.jpg' border=0 width="82"
								height="77"></img>
						</a>
					</td>
				</c:if>
			</tr>
			<tr>
				<td height="48">
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<c:if test="${login.type eq 'system'}">
					<td width="112">
						<a href='<%=basePath%>dept/browe.action' target="main"><img
								src='<%=basePath%>images/pic_11.jpg' border="0" width="108"
								height="73"></img>
						</a>
					</td>
				</c:if>
				<c:if test="${login.type eq 'system'}">
					<td>
						<a href='<%=basePath%>subsidy/browe.action' target="main"><img
								src='<%=basePath%>images/pic_14.jpg' border=0 width="108"
								height="63"></img>
						</a>
					</td>
				</c:if>
				<c:if test="${login.type eq 'system'}">
					<td>
						<a href='<%=basePath%>hours/toExceljsp.action' target="main"><img
								src='<%=basePath%>images/pic_16.jpg' border=0 width="82"
								height="63"></img>
						</a>
					</td>
				</c:if>
			</tr>
		</table>
	</body>
</html>
