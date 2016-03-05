<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<meta http-equiv="description" content="This is my page"/>
		<link rel="<%=basePath%>css/text.css" type="text/css" href="styles.css"/>
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
-->
</style>
		<script language="javascript" src="<%=basePath%>js/public.js"></script>
		<script type="text/javascript">
      function checkSubmit(){
         var deptname= document.getElementById("departmentname");
         var deptcode= document.getElementById("departmentcode");
        
         if(Trim(deptname.value)==""){
            alert('部门名称不能为空');
            return false;
         }
        if(Trim(deptcode.value)==""){
            alert('部门编码不能为空');
            return false;
         }
         
         deptform.action='<%=basePath%>dept/updateDept.action';
         deptform.submit();
      }
</script>
	</head>
	<body bgcolor="#f1f1f1">
		<div class="address">
			<div class="bt">
				组织修改
			</div>
		</div>
		<form name='deptform' action="" method='post' onsubmit="return false">
			<div class="table" style="margin-top: 15px;">
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td width="8%" height="23" align="center">
							组织名称
						</td>
						<td width="27%" align="left">
							<input type="text" id='departmentname' name="dept.departmentname"
								value='${dept.departmentname}'/>
							<span><font color='red'>*</font>
							</span>
						</td>
						<td width="8%" align="center">
							组织编号
						</td>
						<td width="43%" align="left">
							<input type="text" id='departmentcode' name="dept.departmentcode"
								value='${dept.departmentcode}'/>
							<span><font color='red'>*</font>
							</span>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="hidden" name='dept.id' value='${dept.id}'/>
							<input type="hidden" name='dept.compid' value='${dept.compid}'/>
							<input type="hidden" name='dept.pid' value='${dept.pid}'/>
							<input type="button" name="button" value="保存" onclick="checkSubmit()" />
							<input type="reset" name="Submit3" value="重置" />
							<input type="button" name="button" value="返回" onclick="javascript:window.history.back(-1);" />
						</td>
					</tr>
				</table>
			</div>
		</form>
	</body>
</html>
