<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
	%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script language="javascript" src="<%=basePath%>js/public.js"></script>
		<title>柏坚工时成本核算系统</title>

		<script type="text/javascript">
			function keyLogin(){  
				if (event.keyCode==13)   //回车键的键值为13   
					document.getElementById("loginbutton").click(); //调用登录按钮的登录事件   
			}  
		</script>
		<link href="<%=basePath%>css/login.css" rel="stylesheet" type="text/css" />
	</head>
	<body onkeydown="keyLogin();">
		<form name=userform action="<%=basePath%>login.action" method="post" onsubmit="return false">
			<div class="loginmian">
				<div class="log_text">
					<table width="100%" border="0" cellspacing="0" cellpadding="5">
						<tr>
							<td width="38%" align="right">
								用户名：
							</td>
							<td width="62%">
								<input name="user.username" value="" type="text" id="username"
									size="20" class="input" />
							</td>
						</tr>
						<tr>
							<td align="right">
								密 码：
							</td>
							<td>
								<input name="user.password" type="password" id="password"
									size="20" class="input" />
							</td>
						</tr>
						<tr>
							<td height="45" colspan="2" align="center">
								<input onclick="document.userform.submit()" type="button"
									name="loginbutton" id="loginbutton" value="登录" class="lan" />
								<input type="button" name="button"
									onclick="document.userform.reset()" value="重置" class="lan" />
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</body>
</html>
