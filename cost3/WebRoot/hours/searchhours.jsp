<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>报表导出功能</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<style type="text/css">
			<!--
				.STYLE8 {
					font-size: 14px;
					font-weight: bold;
				}
			-->
		</style>
	</head>
	<body>
		<form action="" name="form1" method="post">
			<table width="100%" height="87" border="1" cellpadding="0" cellspacing="1">
				<tr>
					<td height="19" colspan="9" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE7">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="832" height="32" valign="top" background="images/right_bg.jpg">
									<table width="121" height="30" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td width="35">&nbsp;</td>
											<td width="86" class="STYLE8">
												报表管理
											</td>
										</tr>
									</table>
								</td>
								<td align="right" valign="top" background="images/right_hou.jpg">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="101" height="19" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE7">
						工时统计报表
					</td>
					<td colspan="7" bgcolor="#FFFFFF" class="STYLE2 STYLE3">
						<input type="text" id='exportYear'
						       readonly="readonly"
						       name="worktime.date"
						       value='<%=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime())%>'
						       onfocus="new WdatePicker(this, '%Y-%M', false, 'whyGreen')" />
						<input type="button" id="worktimeBtn" value="导出报表"/>
					</td>
				</tr>
				<tr>
					<td height="19"></td>
				</tr>
				<tr>
					<td width="101" height="19" align="center"
						background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE7">
						成本统计报表
					</td>
					<td colspan="7" bgcolor="#FFFFFF" class="STYLE2 STYLE3">
						<input type="text" id='exportYear'
						       readonly="readonly"
						       name="chengben.date"
						       value='<%=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime())%>'
						       onfocus="new WdatePicker(this, '%Y-%M', false, 'whyGreen')" />
						<input type="button" id="statisticsBtn" value="导出报表"/>
					</td>
				</tr>
			</table>
		</form>

		<!-- 这样可以避免脚本加载的速度阻塞页面 -->
		<script language="javascript" src="<%=basePath%>js/public.js"></script>
		<script language="javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" src="<%=basePath%>js/jquery-1.11.0.min.js"></script>
		<script>
			$(document).ready(function(){
				$('#worktimeBtn').click(function(){
					window.location.href = "<%=basePath%>hours/excelWorkTime.action?searchDate=" + $(':input[name=worktime\\.date]').val();
				});
				
				$('#statisticsBtn').click(function(){
					window.location.href = "<%=basePath%>hours/excelStatistics.action?searchDate=" + $(':input[name=chengben\\.date]').val();
				});
			});
		</script>
	</body>
</html>
