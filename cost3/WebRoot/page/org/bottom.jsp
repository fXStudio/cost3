<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bottom.jsp' starting page</title>
    
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
   	<BODY bgcolor="#abd7ff">
	 <table background="<%=basePath %>img/tt.jpg" width="100%" height="70">
	 	<tr align="right">
	 		<td >
	 		<br>
	 		<br>
	 		<script type="text/javascript">
				var enabled = 0; today = new Date();
				var day; var date;
				if(today.getDay()==0) day = "������"
				if(today.getDay()==1) day = "����һ"
				if(today.getDay()==2) day = "���ڶ�"
				if(today.getDay()==3) day = "������"
				if(today.getDay()==4) day = "������"
				if(today.getDay()==5) day = "������"
				if(today.getDay()==6) day = "������"
				date = "��ǰ���ڣ�" + (today.getYear()) + "��" + (today.getMonth() + 1 ) + "��" + today.getDate() + "��  " + day +"  "+today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
				document.write("" + date + "");
		
		</SCRIPT>
	 		</td>
	 	</tr>
	 </table>
	</BODY>
</html>
