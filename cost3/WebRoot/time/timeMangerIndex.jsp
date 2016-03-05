<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>时间分配</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath%>css/index.css?v=1" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/text.css" />
	<style>
		.time_content{font-size:15pt;font-weight:bolder;height:40px;}
		.time_content a {text-decoration: none;}
		.time_content a:visited {color: blue;}
		.time_content a:hover {color: red;}
	</style>
  </head>
  
  <body>
		<div class="address">
			<div class="bt">
				项目人员月度工时设定 <span style="color:blue;font-size:15pt;"></span>
			</div>
		</div>
		<!--地址栏结束-->
		<div class="ss">
			<form name='deptform1' action="time/browe.action" method='post'>
				<table border="0" align="center" cellpadding="5" cellspacing="0"
					class="table">
					<tr>
						<td colspan="4">
							<b style="color:red;font-size:10pt;">* 点击人员名称，进入项目工时编辑页面</b>
						</td>
					</tr>
					<tr>
						<td width="29%" align="right">
							人员姓名：
						</td>
						<td width="21%">
							<input type="text" name='user.username' value="${user.username}" />
						</td>
						<td width="12%" align="right">
							简码：
						</td>
						<td width="38%">
							<input type="text" name='user.usercode' value="${user.usercode}" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" name="Submit" value="搜索" class="ssan" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%int index = 0;boolean isodd = false;%>
		<div class="table">
			<table width="100%" border="0" cellpadding="3" cellspacing="0">
				<c:forEach items="${request.users}" var="user" varStatus="status">
					<%if(index++ %4 == 0){if(isodd){out.print("<tr>");}else{out.print("<tr class='tr2'>");}}%>
					<td align="center" class="time_content">
						<a href="<%=basePath%>time/timeItems.action?user.id=${user.id}">${user.username}</a>
					</td>
					<%if(index == 4){out.print("</tr>");index=0;isodd = !isodd;}%>
				</c:forEach>
			</table>
		</div>
  </body>
</html>
