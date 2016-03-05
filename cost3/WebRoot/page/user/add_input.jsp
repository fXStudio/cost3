<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="<%=basePath %>js/public.js"></script>
<title>添加机构信息</title>
</head>
<script language="javascript" type="text/javascript">

</script>
<body>
<center>
<form  name=form1 action="" method="post">
<TABLE class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
	<TBODY>
		<TR>
			<!-- 这里是添加、编辑界面的标题 -->
			<td align="center" class="tdEditTitle">添加机构信息</TD>
		</TR>
		<TR>
			<td>
			<!-- 主输入域开始 -->

<input type="hidden" name="method" value="add">
<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td class="tdEditLabel" >用户名称</td>			
		<td class="tdEditContent"><input type="text" id='username' name="user.username"></td>
		<td><div><font color='red'>*</font></div></td>
	</tr>
	<tr>
	<td class="tdEditLabel" >住址</td>			
	<td class="tdEditContent"><input type="text" id='address' name="user.address"></td>
	<td><div><font color='red'>*</font></div></td>
	</tr>
	
		<tr>
		<td class="tdEditLabel" >密码</td>			
		<td class="tdEditContent"><input type="text" id='password' name="user.password"></td>
		<td><div><font color='red'>*</font></div></td>
	</tr>
	<tr>
	<td class="tdEditLabel" >性别</td>			
	<td class="tdEditContent"><input type="text" id='sex' name="user.sex"></td>
	<td><div><font color='red'>*</font></div></td>
	</tr>
	
		<tr>
		<td class="tdEditLabel" ></td>			
		<td class="tdEditContent"><input type="text" id='worklevel' name="worklevel"></td>
		<td><div><font color='red'>*</font></div></td>
	</tr>
	<tr>
	<td class="tdEditLabel" >机构描述</td>			
	<td class="tdEditContent"><input type="text" id='census' name="census"></td>
	<td><div><font color='red'>*</font></div></td>
	</tr>
	
		<tr>
		<td class="tdEditLabel" >机构名称</td>			
		<td class="tdEditContent"><input type="text" id='departmentname' name="dept.departmentname"></td>
		<td><div><font color='red'>*</font></div></td>
	</tr>
	<tr>
	<td class="tdEditLabel" >机构描述</td>			
	<td class="tdEditContent"><input type="text" id='departmentcode' name="dept.departmentcode"></td>
	<td><div><font color='red'>*</font></div></td>
	</tr>
	
		<tr>
		<td class="tdEditLabel" >机构名称</td>			
		<td class="tdEditContent"><input type="text" id='departmentname' name="dept.departmentname"></td>
		<td><div><font color='red'>*</font></div></td>
	</tr>
	<tr>
	<td class="tdEditLabel" >机构描述</td>			
	<td class="tdEditContent"><input type="text" id='departmentcode' name="dept.departmentcode"></td>
	<td><div><font color='red'>*</font></div></td>
	</tr>
</table>

			<!-- 主输入域结束 -->
			</td>
		</TR>
	</TBODY>
</TABLE>

<TABLE>
		<TR align="center">
			<TD colspan="3" bgcolor="#EFF3F7">
			<input type=button name="saveButton"
				 value="保存机构信息" onclick="checkSubmit()"> 
			<input type="button" class="MyButton"
				value="关闭窗口" onclick="window.close()">
			</TD>
		</TR>
</TABLE>
</form>
</center>
</body>
</html>