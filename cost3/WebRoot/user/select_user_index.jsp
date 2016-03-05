<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>柏坚工时核算</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/text.css">
		<style type="text/css">
			<!--
				body {
					margin-left: 0px;
					margin-top: 0px;
					margin-right: 0px;
					margin-bottom: 0px;
				}
				.STYLE2 {
					font-size: 12px;
					font-weight: bold;
					background-image: URL('images/dh_bg.jpg');
					background-color: #fff;
					text-align: center;
				}
				.STYLE3 {
					font-size: 12px;
					text-align: center;
				}
			-->
		</style>
		<script language="javascript" src="<%=basePath%>js/public.js"></script>
		<script type="text/javascript">
		   function selectOrg(oid, oname, census, formula){
				if(window.opener){
					window.opener.document.all.userid.value = oid;
					window.opener.document.all.username.value = oname;
					window.opener.document.all.census.value = census;
					window.opener.document.all.formula.value = formula;
					
					window.close();
				}
			}   
		</script>
	</head>
	<body>
		<form name='deptform' action="" method='post'>
			<table width="100%" border="0" align="left" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="832" height="32" valign="top"
						background="images/right_bg.jpg">
						<table width="121" height="30" border="0" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="35">
									&nbsp;
								</td>
								<td width="90" align="left" class="STYLE2">
									人员信息
								</td>
							</tr>
						</table>
					</td>
					<td align="right" valign="top" background="images/right_hou.jpg">
						<table width="200" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="16">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="1"
							bordercolor="#FFFFFF" bgcolor="#c6c6c6" style="FONT-SIZE: 10pt">
							<tr>
								<td width="5%" class="STYLE2">选择</td>
								<td width="5%" class="STYLE2">序号</td>
								<td width="19%" class="STYLE2">人员姓名</td>
								<td width="19%" class="STYLE2">人员编码</td>
								<td width="19%" class="STYLE2">岗位级别</td>
								<td width="19%" class="STYLE2">籍贯</td>
								<td width="19%" class="STYLE2">所属机构</td>
							</tr>
							<tr>
								<!-- 列表数据栏 -->
								<c:if test="${!empty pm.datas}">
									<c:forEach items="${pm.datas }" var="user" varStatus="status">
										<tr bgcolor="#EFF3F7" class="TableBody1"
											onmouseover="this.bgColor = '#DEE7FF';"
											onmouseout="this.bgColor='#EFF3F7';">
											<td align="center" align="center">
												<input type="radio" 
												       name="deptradio" 
												       value="${user.id}" 
												       onclick="selectOrg('${user.id}', '${user.username}', '${user.census}', '${user.nat.welfare.wformula}')">
											</td>
											<td class="STYLE3">${status.index+1}</td>
											<td class="STYLE3">${user.username}</td>
											<td class="STYLE3">${user.usercode}</td>
											<td class="STYLE3">${user.worklevel}</td>
											<td class="STYLE3">${user.nat.nativename}</td>
											<td class="STYLE3">${user.dept.departmentname}</td>
										</tr>
									</c:forEach>
								</c:if>
								<!-- 在列表数据为空的时候，要显示的提示信息 -->
								<c:if test="${empty pm.datas}">
									<tr>
										<td colspan="7" align="center" bgcolor="#EFF3F7"
											class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';"
											onmouseout="this.bgColor='#EFF3F7';" style="FONT-SIZE: 10pt">
											没有找到相应的记录
										</td>
									</tr>
								</c:if>
								<!-- 可以在这里插入分页导航条 -->
								<pg:pager url="user/selectBrowe.action" items="${pm.total}"
									maxPageItems="${ps}" export="currentPageNumber=pageNumber">
									<pg:param name="parentId" />
									<pg:first>
										<a href="${pageUrl}" id="firstpageurl" class="n2">
											<font class="STYLE2">首页</font>
										</a>
									</pg:first>
									<pg:prev>
										<a href="${pageUrl }" class="n2">
											<font class="STYLE2">前页</font>
										</a>
									</pg:prev>
									<pg:pages>
										<c:choose>
											<c:when test="${currentPageNumber eq pageNumber}">
												<font color="red">${pageNumber }</font>
											</c:when>
											<c:otherwise>
												<a href="${pageUrl }">${pageNumber }</a>
											</c:otherwise>
										</c:choose>
									</pg:pages>
									<pg:next>
										<a href="${pageUrl }" class="n2"><font class="STYLE2">后页</font>
										</a>
									</pg:next>
									<pg:last>
										<a href="${pageUrl }" class="n2"><font class="STYLE2">尾页</font>
										</a>
									</pg:last>
								</pg:pager>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
