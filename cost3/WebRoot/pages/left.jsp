<%@ page language="java" pageEncoding="utf-8"%>

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
		<link href="text.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/index.css" rel="stylesheet"
			type="text/css" />
	</head>
	<body bgcolor="#f0f0f0">
		<form name=form1 action="" onsubmit="return false">
			<div class="left">
				<img src="<%=basePath%>images/png1.png" />
				<div class="leftmenu">
					<ul>
						<li>
							<img src="<%=basePath%>images/png4.png" />
							<a href='<%=basePath%>user/zzjg.action' target="main">组织架构</a>
						</li>
						<c:if test="${login.type eq 'system'}">
							<li>
								<img src="<%=basePath%>images/png5.png" />
								<a href='<%=basePath%>user/browe.action' target="main">人员管理</a>
							</li>
						</c:if>
						<c:if test="${login.type eq 'system'}">
							<li>
								<img src="<%=basePath%>images/png6.png" />
								<a href='<%=basePath%>subsidy/browe.action' target="main">工资管理</a>
							</li>
						</c:if>
						<li>
							<img src="<%=basePath%>images/png7.png" />
							<a href='<%=basePath%>hours/browe.action' target="main">工时管理</a>
						</li>
						<c:if test="${login.type eq 'system'}">
							<li>
								<img src="<%=basePath%>images/png9.png" />
								<a href="<%=basePath%>overtime/statisticsIndex.action"
									target="main">超时工作</a>
							</li>
						</c:if>
						<c:if test="${login.type eq 'system'}">
							<li>
								<img src="<%=basePath%>images/png8.png" />
								<a href='<%=basePath%>item/browe.action' target="main">项目管理</a>
							</li>
						</c:if>
						
						<c:if test="${login.type eq 'system'}">
							<li>
								<img src="<%=basePath%>images/png20.png" />
								<a href='<%=basePath%>time/browe.action' target="main">时间配置</a>
							</li>
						</c:if>
						<c:if test="${login.type eq 'system'}">
							<li>
								<img src="<%=basePath%>images/png11.png" />
								<a href='<%=basePath%>dept/browe.action' target="main">组织管理</a>
							</li>
						</c:if>
						<c:if test="${login.type eq 'system'}">
							<li>
								<img src="<%=basePath%>images/png12.png" />
								<a href='<%=basePath%>hours/toExceljsp.action' target="main">报表导出</a>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</form>
	</body>
</html>
