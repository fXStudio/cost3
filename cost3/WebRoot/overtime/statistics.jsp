<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>

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
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/statistics.css"/>
	</head>
	<body>
		<form name='form1' action="<%=basePath%>/overtime/${login.type eq 'system' ? 'showItems.action' : 'showItemOvers.action'}" method='post'>
			<input type="hidden" name="overtime.itemname"/>
			<div class="container">
				<h1 class="title">项目超时统计</h1>
				<div>
					<label>项目名称</label>
					<select name="overtime.itemid">
						<c:forEach items="${request.items}" var="item" varStatus="status">
							<option value="${item.id}">
								${item.itemname}
							</option>
						</c:forEach>
					</select>
				</div>
				<div>
					<label>汇总日期</label>
					<input type="text" id='exportYear'
						readonly="readonly"
						name="overtime.date"
						value='<%=new SimpleDateFormat("yyyy-MM").format(Calendar.getInstance().getTime())%>'
						onfocus="new WdatePicker(this, '%Y-%M', false, 'whyGreen')" />
				</div>
				<div class="toolbar">
					<input type="submit" value="统计超时时间"/>
					<input type="reset" value="重置"/>
				</div>
			</div>
		</form>
		
		<!-- 这样可以避免脚本加载的速度阻塞页面 -->
		<script language="javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" src="<%=basePath%>js/jquery-1.11.0.min.js"></script>
		<script>
			/**
			 * 下拉列表变更事件处理
			 */
			$(document).ready(function(){
				$('select[name="overtime.itemid"]').change(function(){
					setItemname();
				});
			});
			
			/**
			 * 设置隐藏属性--项目名称
			 */
			var setItemname = function(){
				var sel = $('select[name="overtime.itemid"]');
				var v = sel.find("option:selected").text();
				
				// 设置隐藏属性
				$(':hidden[name="overtime.itemname"]').val(v);
			};
			setItemname();
		</script>
	</body>
</html>
