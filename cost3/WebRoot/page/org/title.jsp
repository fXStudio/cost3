<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
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

		<title>My JSP 'title.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</HEAD>
	<BODY bgcolor="#abd7ff">
	 <table background="<%=basePath %>img/tt.jpg" width="100%" height="70">
	 	<tr align="right">
	 		<td >
	 		<br>
	 		<br>
	 		<script type="text/javascript">
				var enabled = 0; today = new Date();
				var day; var date;
				if(today.getDay()==0) day = "星期日"
				if(today.getDay()==1) day = "星期一"
				if(today.getDay()==2) day = "星期二"
				if(today.getDay()==3) day = "星期三"
				if(today.getDay()==4) day = "星期四"
				if(today.getDay()==5) day = "星期五"
				if(today.getDay()==6) day = "星期六"
				//date = "当前用户:${user.name}"+" 当前日期：" + (today.getYear()) + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日  " + day +"  "+today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
				date = "当前用户:${login.name}"+" 当前日期：" + (today.getYear()) + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日  " + day;
				document.write("<font color='bule'>" + date + "</font>");
		
		</SCRIPT>
	 		</td>
	 	</tr>
	 </table>
	</BODY>
</HTML>