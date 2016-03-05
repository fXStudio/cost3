<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>柏坚联创工程顾部工时核算系统</title>
<link href="<%=basePath%>css/index.css" rel="stylesheet" type="text/css" />
</head> 
<body>
<form name=form1 action="" onsubmit="return false">
<div class="top">

	<div  class="topan">
  		<a href='<%=basePath%>/pages/center.jsp' target="main" class="f12b_fff">
  		<img src="<%=basePath%>images/gif1.gif" border="0" /></a><br/>
    	<a href='<%=basePath%>/pages/center.jsp' target="main" class="f12b_fff"> 系统桌面</a>
	</div>
	
	<div class="topan" >
		<a href='<%=basePath%>/user/user_editpwd.jsp' target="main"  class="f12b_fff"><img src="<%=basePath%>images/gif2.gif" border="0" /></a><br/>
    	<a href='<%=basePath%>/user/user_editpwd.jsp' target="main"  class="f12b_fff"> 修改密码</a>
    </div>
	
	<div class="topan" ><a href='<%=basePath%>login!logout.action' target=_top class="f12b_fff"><img src="<%=basePath%>images/gif3.gif" border="0" /></a><br/>
		<a href='<%=basePath%>login!logout.action' target=_top class="f12b_fff"> 退出系统</a>
	</div>  
</div>  
</form>
</body>
</html> 

