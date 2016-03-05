<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"  uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="<%=basePath%>css/text.css" type="text/css" href="styles.css">
	
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
		.STYLE3 {font-size: 12px;}
		
		-->
	</style>
	<script language="javascript" src="<%=basePath%>js/public.js"></script>
<script type="text/javascript">


function lTrim(str)
{ 
	if (str.charAt(0) == ' ')
	{
		str = str.slice(1);
		str = lTrim(str);
	}
	return str;
} 
function rTrim(str)
{
	var iLength;
	iLength = str.length;
	if (str.charAt(iLength - 1) == " ")
	{
		str = str.slice(0, iLength - 1);
		str = rTrim(str); 
	}
	return str;
} 
function trim(str)
{
	return lTrim(rTrim(str));
}  
	function checkSubmit(){
		var oldpwd= document.getElementById("oldpwd").value;
		var newpwd= document.getElementById("newpwd").value; 
		if(Trim(oldpwd.value)==""){
			alert('旧密码不能为空');
            return false;
         }
         
        if(Trim(newpwd.value)==""){
            alert('新密码不能为空');
            return false;
         }  
		deptform.action='<%= basePath%>user/editpwd.action';
		deptform.submit();
}
</script>
  </head>
  <body>
  <form name='deptform'action="" method='post' onSubmit="return false">
  <div class="ss">
	  <table width="100%" border="0" align="center" cellpadding="5" cellspacing="0" class="table" >
        <tr>
          <td colspan="4" align="left"> 修改密码</td>
        </tr>
        <tr>
          <td width="39%" align="right">旧密码：</td>
          <td width="61%"><input  type="password" id='oldpwd' name="user.password" /></td> 
        </tr>
        <tr>
          <td align="right">新密码：</td>
          <td><input type="password" id='newpwd' name="user.newpwd" /></td>
        </tr> 
        <tr>
          <td colspan="4" align="center"><input type="button" name="submit1"  onclick="checkSubmit()" value="确定" class="ssan" />　
            <input type="reset" name="reset" value="重置" class="ssan" /></td>
        </tr>
      </table>
</div> 
</form>  
  </body>
</html>
