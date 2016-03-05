<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>柏坚工时核算系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link rel="<%=basePath%>css/text.css" type="text/css" href="styles.css" />
		<link href="<%=basePath%>css/index.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
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
				}
				
				.STYLE3 {
					font-size: 12px;
				}
				
				.STYLE4 {
					font-size: 24px;
					font-weight: bold;
				}
				td {
					height: 25px;
					text-align: right;
				}
				td > p {
					color: #8d222d;
					padding-right:6px;
					font-size: 11pt;
				}
				td.toolbar{text-align: center;}
				td.toolbar>input[type='button']{
					width:120px;
					margin-top: 8px;
					padding: 2px 0px;
					font-size: 9pt;
				}
			-->
		</style>
	</head>
	<body bgcolor="#f1f1f1">
		<div class="address">
			<div class="bt">
				人员管理——人员详情
			</div>
		</div>
		<div class="table" style="margin-top: 15px;">
			<form name='deptform' action="" method='post' onsubmit="return false">
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td width="8%" align="center">
							人员名称
						</td>
						<td width="27%" align="left" class="STYLE2 STYLE3">
							<p>${empty user.username ? '-----' : user.username}</p>
						</td>
						<td width="8%" align="center">
							英文名字
						</td>
						<td width="43%" align="left">
							<p>${empty user.englishname ? '-----' : user.englishname}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							人员编码
						</td>
						<td width="27%" align="left">
							<p>${empty user.usercode ? '-----' : user.usercode}</p>
						</td>
						<td width="8%" align="center">
							人员简码
						</td>
						<td width="43%" align="left">
							<p>${empty user.easycode ? '-----' : user.easycode}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							手机号码
						</td>
						<td width="43%" align="left">
							<p>${empty user.tel ? '-----' : user.tel}</p>
						</td>
						<td width="8%" align="center">
							人员邮箱
						</td>
						<td width="43%" align="left">
							<p>${empty user.emial ? '-----' : user.emial}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							入职时间
						</td>
						<td width="27%" align="left">
							<p>${empty user.jobdate ? '-----' : user.jobdate}</p>
						</td>
						<td width="8%" align="center">
							聘用类型
						</td>
						<td width="43%" align="left">
							<p>${empty user.employtype ? '-----' : user.employtype}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							岗位级别
						</td>
						<td width="27%" align="left">
							<p>${empty user.worklevel ? '-----' : user.worklevel}</p>
						</td>
						<td width="8%" align="center">
							职位
						</td>
						<td width="8%" align="left">
							<p>${empty user.post.postname ? '-----' : user.post.postname}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							证件号码
						</td>
						<td width="27%" align="left">
							<p>${empty user.papersnum ? '-----' : user.papersnum}</p>
						</td>
						<td width="8%" align="center">
							人员籍贯
						</td>
						<td width="43%" align="left">
							<c:choose>
								<c:when test="${user.nat.nativecode eq 'hk'}">
									<p>港籍</p>
								</c:when>
								<c:when test="${user.nat.nativecode eq 'dw'}">
									<p>大陆外埠</p>
								</c:when>
								<c:when test="${user.nat.nativecode eq 'bj'}">
									<p>大陆北京</p>
								</c:when>
								<c:when test="${user.nat.nativecode eq 'sh'}">
									<p>上海</p>
								</c:when>
								<c:otherwise>
									<p>-----</p>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							人员性别
						</td>
						<td width="27%" align="left">
							<c:if test="${user.sex eq 'man'}">
								<p>男</p>
							</c:if>
							<c:if test="${user.sex eq 'gril'}">
								<p>女</p>
							</c:if>
						</td>
						<td width="8%" align="center">
							用户类别
						</td>
						<td width="43%" align="left">
							<c:if test="${user.type eq 'common'}">
								<p>普通用户</p>
							</c:if>
							<c:if test="${user.type eq 'system'}">
								<p>管理用户</p>
							</c:if>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							户口所在地
						</td>
						<td width="27%" align="left">
							<p>${empty user.account? '-----' : user.account}</p>
						</td>
						<td width="8%" align="center">
							国籍
						</td>
						<td width="43%" align="left">
							<p>${empty user.nationality ? '-----' : user.nationality}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							受教育程度
						</td>
						<td width="27%" align="left">
							<p>${empty user.education ? '-----' : user.education}</p>
						</td>
						<td width="8%" align="center">
							专业
						</td>
						<td width="43%" align="left">
							<p>${empty user.major ? '-----' : user.major}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							职称
						</td>
						<td width="27%" align="left">
							<p>${empty user.title ? '-----' : user.title}</p>
						</td>
						<td width="8%" align="center">
							专业资质
						</td>
						<td width="43%" align="left">
							<p>${empty user.majorquality ? '-----' : user.majorquality}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" height="23" align="center">
							现家庭住址
						</td>
						<td width="27%" align="left">
							<p>${empty user.address ? '-----' : user.address}</p>
						</td>
						<td width="8%" align="center">
							婚姻情况
						</td>
						<td width="43%" align="left">
							<p>${empty user.marry ? '-----' : user.marry}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							联系人
						</td>
						<td width="27%" align="left">
							<p>${empty user.linkman ? '-----' : user.linkman}</p>
						</td>
						<td width="8%" align="center">
							联系电话
						</td>
						<td width="43%" align="left">
							<p>${empty user.linkmantel ? '-----' : user.linkmantel}</p>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							密码
						</td>
						<td width="27%" align="left">
							<p>*****</p>
						</td>
						<td width="8%" align="center">
							所属部门
						</td>
						<td width="43%" align="left">
							<p>${empty user.dept.departmentname ? '-----' : user.dept.departmentname}</p>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4" class="toolbar">
							<input type="button" name="button" value="返回" onclick="javascript:window.history.back(-1);" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
