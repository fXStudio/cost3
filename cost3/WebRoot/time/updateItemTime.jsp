<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<link href="<%=basePath%>css/index.css?v=1" rel="stylesheet"
			type="text/css" />
		<link href="<%=basePath%>css/table.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>css/text.css" />
		<style>
.update_content,.update_content input {
	font-size: 16pt;
	font-weight: bold;
	padding-left: 15px;
	color: #8d222d;
}

.update_content input {
	padding-left: 0px;
	width:60px;
	color:blue;
}
</style>
		<script language="javascript"
			src="<%=basePath%>js/jquery-1.11.0.min.js"></script>
	</head>
	<body>
		<div class="address">
			<div class="bt">
				项目人员月工时安排
			</div>
		</div>
		<!--地址栏结束-->
		<div class="ss">
			<form name='deptform1' action="time/browe.action" method='post'>
				<table border="0" align="center" cellpadding="5" cellspacing="0"
					class="table">
					<tr>
						<td colspan="4">
							<input type="button" value="返回"
								onclick="javascript:location.href='<%=basePath%>time/timeItems.action?user.id=${user.id}'" />
							<input id="submitBtn" type="button" value="提交" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="table">
			<table width="100%" border="0" cellpadding="3" cellspacing="0">
				<tr>
					<td width="110px" align="right">
						项目人员:
					</td>
					<td class="update_content">
						${user.username}
					</td>
				</tr>
				<tr>
					<td align="right">
						项目:
					</td>
					<td class="update_content">
						${item.itemname}
					</td>
				</tr>
				<tr>
					<td align="right">
						日期:
					</td>
					<td class="update_content"><%=new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date())%></td>
				</tr>
				<tr>
					<td align="right">
						项目总工时:
					</td>
					<td class="update_content">
						<c:choose>
							<c:when test="${item.itemcode gt 16}">
								<input name="user.cworktime.timelong" id="editor" maxlength="4"
									value="${empty user.cworktime.timelong? '0' : user.cworktime.timelong}" />
								<span><font color='red'>* 分配的工时不能小于已使用工时</font> </span>
							</c:when>
							<c:otherwise>
								<span style="color:green">无限制</span>
								<input name="user.cworktime.timelong" id="editor" type="hidden" value="99999"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td align="right">
						已使用工时:
					</td>
					<td class="update_content" id="usedtime">
						${empty user.cworktime.usedTime ? '0' : user.cworktime.usedTime}
					</td>
				</tr>
				<c:if test="${item.itemcode gt 16}">
					<tr>
						<td align="right">
							剩余工时:
						</td>
						<td class="update_content">
							${empty user.cworktime.residTime ? '0' : user.cworktime.residTime}
						</td>
					</tr>
				</c:if>
			</table>
			<form action="<%=basePath%>time/saveUpdate.action" >
				<input type="hidden" name="user.id" value="${user.id}"/>
				<input type="hidden" name="item.id" value="${item.id}"/>
				<input type="hidden" name="user.cworktime.timelong"/>
			</form>
		</div>
	</body>
	<script>
		$(document).ready(function(){
			/**
			 * 控制组件的录入
			 */
			$('#editor').keydown(function(event){
				// 键盘案件的ASCII码值
			    var code = parseInt(event.keyCode);
			    
			    // 设置可接受的输入内容格式
			    return (code >= 96 && code <= 105 || 
			        code >= 48 && code <= 57 || 
			        code >= 37 && code <= 40 ||
			        code === 8 || code === 9);
			});
			/**
			 * 设置控件内容选中
			 */
			$('#editor').click(function(){$(this).select();})
			/**
			 * 提交表单
			 */
			$('#submitBtn').click(function(){
				var inputval = $('#editor').val();
				var usedTime = $('#usedtime').html();
				
				if(parseInt(inputval) < parseInt(usedTime)){
					alert("总工时不能少于已使用工时");
				
					return;
				}
				$(':hidden[name=user\\.cworktime\\.timelong]').val(inputval);
				
				$('form').submit();
			});
		});
	</script>
</html>
