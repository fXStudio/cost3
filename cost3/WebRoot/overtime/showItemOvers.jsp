<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>

<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>

		<title>超时统计</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<meta http-equiv="description" content="This is my page"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/text.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/index.css"/>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/table.css"/>
		<style type="text/css">
			<!--
				body {
					margin-left: 0px;
					margin-top: 0px;
					margin-right: 0px;
					margin-bottom: 0px;
				}
				.STYLE2 {font-size: 12px;font-weight: bold;}
				.STYLE3 {font-size: 12px;}
				.header{font-size:11pt;font-weight:bold;color:maroon;}
				.title{font-size:16pt; height:40px;color:blue;}
				.odd{background-color:lightBlue;}
				.nonzero{color: red;};
				.mask {    
		            position: absolute; 
		            top: 0px; 
		            filter: alpha(opacity=60); 
		            background-image: URL('<%=basePath%>/images/s.gif');
		            background-color: #fff;
		            z-index: 1002; 
		            left: 0px;  
		            opacity:0.55; 
		            -moz-opacity:0.55;
		            text-align: center;
		        }  
		        .loading{
		        	background: URL('<%=basePath%>/images/blue-loading.gif') no-repeat;
		        	width:32px;
		        	height:32px;
		        	margin-top:240px;
		        	margin-left:auto; 
		        	margin-right: auto;
		        }
			-->
		</style>
	</head>
	<body>
		<div class="address" style="width: 1600px;">
			<div class="bt">
				超时管理
			</div>
			<div class="gsgl_cx">
				登录人员：
				<span class="f12b_009f43">${session.login.username}</span>
			</div>
		</div>
		<input name="overtime.itemid" type="hidden" value="${request.itemid}"/>
		<input name="overtime.date" type="hidden" value="${request.itemdate}"/>
		<div class="table" style="margin:0px 0px;">
			<table width="1200px" border="0" cellpadding="0" cellspacing="1">
				<tr class="tr">
					<td width="10%" rowspan='2' colspan='2' align="center" style="font-size:13pt;">项目人员</td>
					<td align="center" colspan="${request.maxdate+2}" class="title">${request.itemname}(${request.itemdate})</td>
				</tr>
				<tr>
					<c:forEach begin="1" end="${request.maxdate}" var="s">
						<td width="30px;" align="center" class="header">${s}</td>
					</c:forEach>
					<td align="center" class="header" style="color:blue;">总时间</td>
					<td align="center" class="header" style="color:blue;">费用</td>
				</tr>
				<c:forEach items="${request.itemusers}" var="user" varStatus="status">
					<tr class="${status.index%2==0?'odd':''}">
						<td align="center" colspan='2' class="header">
							${user.username}
						</td>
						<c:forEach begin="1" end="${request.maxdate}" var="s">
							<!-- id: Row_Col -->
							<td id="${user.id}_${s}" align="center" style="font-size:12pt;">0</td>
						</c:forEach>
						<td>0</td>
						<td>0</td>
					</tr>
				</c:forEach>
				<tr>
					<td align="center" colspan='2' style="font-size:14pt;color:blue;">总计</td>
					<c:forEach begin="1" end="${request.maxdate}" var="s">
						<td abbr="${s}" id="@${s}" style="color:red;font-size:13pt;font-weight:bold;">0</td>
					</c:forEach>
				</tr>
			</table>
		</div>
		<div id="mask" class="mask">
			<div class="loading"></div>
			<p style="font-size:14pt;font-weight:bold;color:#0033FF;">数据加载中，请稍候........</p>
		</div> 
				
		<!-- 这样可以避免脚本加载的速度阻塞页面 -->
		<script language="javascript" src="<%=basePath%>js/jquery-1.11.0.min.js"></script>
		<script language="javascript" src="<%=basePath%>js/showItemOvers.js"></script>
		
	</body>
</html>
