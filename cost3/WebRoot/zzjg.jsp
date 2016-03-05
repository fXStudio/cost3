<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>柏坚联创工程顾部工时核算系统</title>
		<link href="<%=basePath%>css/index.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="address">
			<div class="bt">
				组织架构
			</div>
		</div>
		<!--地址栏结束-->
		<div class="table">
			<table width="100%" border="0" align="center" cellpadding="10"
				cellspacing="0">
				<tr>
					<c:forEach items="${comps}" var="comp" varStatus="status">
						<c:choose>
							<c:when test="${status.index%2 == 0}">
								<td valign="top" bgcolor="#e7feeb">
							</c:when>
							<c:otherwise>
								<td valign="top" bgcolor="#e1f0ff">
							</c:otherwise>
						</c:choose>
						<div class="zzjg">
							<div class="zbt1">
								${comp.companyName}
							</div>

							<c:forEach items="${comp.depts}" var="dept">
								<div class="zbt2">
									${dept.deptName}
								</div>
								<div class="zbt3">
									<c:forEach items="${dept.emps}" var="emp">
										<span class="zspan1">${emp.empName}</span>
										<span class="zspan2">${emp.empPost}</span>
									</c:forEach>
								</div>
							</c:forEach>
						</div>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</body>
</html>
