<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'about.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  <LINK 
href="<%=basePath%>css/style.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.3790.1830" name=GENERATOR></HEAD>
<BODY>
<DIV class=menuDiv style="WIDTH: 100%; HEIGHT: 98%">
<TABLE style="WIDTH: 100%; HEIGHT: 100%" border=0>
  <TBODY>
  <TR>
    <TD align=middle>
      <DIV style="OVERFLOW: auto; WIDTH: 300px; HEIGHT: 200px" align=left>
     <font color="#3f57e7">欢迎使用工时核算系统。</font> </DIV></TD></TR></TBODY></TABLE></DIV></BODY></HTML>
