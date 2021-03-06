<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
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

		<title>工时管理</title>
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
				.header{font-size:9pt;font-weight:bold;color:maroon;}
				.title{font-size:16pt; height:40px;color:blue;}
				.odd{background-color:lightBlue;}
				.nonzero{font-weight:bolder;}
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
				.toolbar{float:left;width:600px;height:20px;padding-top:3px;}
				.toolbar>*{float:right;}
				.toolbar>#reset{margin-right:5px;}
				.toolbar>span{line-height:30px;margin:-5px 5px;display:inline-block;}
				.modified{color: yellow;font-size:15pt;font-weight:bold;background-color:#6699FF;}
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
		        .CEditor{
					border:solid 1px lightGray;
					width:100px;
					font-size:12pt;
					text-align:center;
					color: blue;
				}
			-->
		</style>
	</head>
	<body>
		<div class="address" style="width: 1200px;">
			<div class="bt">
				工时管理
			</div>
			<div class="gsgl_cx">
				登录人员：
				<span class="f12b_009f43">${session.login.username}</span>
			</div>
			<div class="toolbar">
				<input id="reset" type="button" value="取消修改"/>
				<span >|</span>
				<input id="submit" type="button" value="提交"/>
			</div>
		</div>
		<div class="table" style="margin:0px 0px;">
			<form name='form1' action="<%=basePath%>hours/saveUpdate.action" method='post'>
				<input type="hidden" id="start" name="hour.clientparam"/>
				<table width="1200px" border="0" cellpadding="0" cellspacing="1">
					<tr class="tr">
						<td width="8%" rowspan='2' align="center" style="font-size:13pt;">项目</td>
						<td width="200px" rowspan='2' align="center" style="font-size:13pt;">剩余工时</td>
						<td align="center" colspan="${request.maxdate+2}" class="title">
							<%=new SimpleDateFormat("yyyy-MM").format(new Date())%>
						</td>
					</tr>
					<tr>
						<c:forEach begin="1" end="${request.maxdate}" var="s">
							<td width="10px;" align="center" class="header">${s}</td>
						</c:forEach>
					</tr>
					<c:forEach items="${request.items}" var="item" varStatus="status">
						<tr class="${status.index%2==0?'odd':''}">
							<td align="center" class="header">
								${item.itemname}
							</td>
							<td align="center" class="header" style="color:green;font-size:14pt;" id="${item.id}_0">
								${empty item.timelong ? '0' : item.timelong gt 9999 ? '----' : item.timelong}
							</td>
							<c:forEach begin="1" end="${request.maxdate}" var="s">
								<!-- id: Row_Col -->
								<td id="${item.id}_${s}" abbr="0" class="CEditor" align="center" style="font-size:11pt;">0</td>
							</c:forEach>
						</tr>
					</c:forEach>
					<tr>
						<td align="center" class="header" colspan="2" style="color:black;">每日最大可用工时</td>
						<c:forEach begin="1" end="${request.maxdate}" var="s">
							<td width="10px;" align="center" class="header" style="color:green;font-size:14pt;" id="static_${s}">8</td>
						</c:forEach>
					</tr>
				</table>
			</form>
			<input id="editor" style="width:20px;" maxlength="1"/>
		</div>
		<div id="mask" class="mask">
			<div class="loading"></div>
			<p style="font-size:14pt;font-weight:bold;color:#0033FF;">数据加载中，请稍候........</p>
		</div> 
				
		<!-- 这样可以避免脚本加载的速度阻塞页面 -->
		<script language="javascript" src="<%=basePath%>js/public.js"></script>
		<script language="javascript" src="<%=basePath%>js/jquery-1.11.0.min.js"></script>
		<script language="javascript" src="<%=basePath%>js/hours.js"></script>
	</body>
</html>
