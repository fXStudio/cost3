<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	    <base href="<%=basePath%>">
	    
	    <title>柏坚工时核算系统</title>
	    
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
				${user.username}现在参与的所有项目
			</div>
		</div>
		<!--地址栏结束-->
		<div class="ss">
			<form name='deptform1' action="time/timeItems.action" method='post'>
				<table border="0" align="center" cellpadding="5" cellspacing="0"
					class="table">
					<tr>
						<td colspan="4">
							<b style="color:red;font-size:10pt;">* 点击项目名称，进入人员-项目工时编辑页面</b>
						</td>
					</tr>
					<tr>
						<td width="29%" align="right">
							项目名称：
						</td>
						<td width="21%">
							<input type="text" name='item.itemname' value="${item.itemname}" />
						</td>
						<td width="12%" align="right">
							项目编码：
						</td>
						<td width="38%">
							<input type="text" name='item.itemcode' value="${item.itemcode}" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" name="Submit" value="搜索" class="ssan" />
    						<input type="button" value="返回" onclick="javascript:location.href='<%=basePath%>time/browe.action'"/>
						</td>
					</tr>
				</table>
				<input type="hidden" name="user.id" value="${user.id}"/>
			</form>
		</div>
		<%int index = 0;boolean isodd = false;%>
		<div class="table">
			<table width="100%" border="0" cellpadding="3" cellspacing="0">
				<c:forEach items="${user.items}" var="item" varStatus="status">
					<%if(index++ %4 == 0){if(isodd){out.print("<tr>");}else{out.print("<tr class='tr2'>");}}%>
					<td align="center" class="time_content">
						<a href="<%=basePath%>time/updateItemTime.action?user.id=${user.id}&item.id=${item.id}">${item.itemname}</a>
					</td>
					<%if(index == 4){out.print("</tr>");index=0;isodd = !isodd;}%>
				</c:forEach>
			</table>
		</div>
	</body>
</html>
