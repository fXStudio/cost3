<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工时核算系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<LINK 
href="<%=basePath%>css/style1.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.3790.1830" name=GENERATOR>
<script language="JavaScript">
  function show_div(menu){
    if(document.all.item(menu).style.display=="none"){
   
   
      document.all.item(menu).style.display="block";
      }else{
        document.all.item(menu).style.display="none";
        }
    }
	function quit1(){
		var flag = confirm("您确定要退出吗");
		if(flag==true){
			window.location.href="<%=basePath%>login!quit.action";
		}else{
			return;
		}
	} 
</script>
</HEAD>
<BODY>
<DIV class=menuDiv style="HEIGHT: 98%">
<a  onClick="show_div('menu1')" ><img src="<%=basePath %>img/left_top.GIF"></a>
<form name="form1" method="post" action="">
<TABLE width="100%" bgcolor="#abd7ff">
  <TBODY>
  
  <TR align="left">
    <TD><A   onClick="show_div('menu1')" 
     
     ><img src="<%=basePath %>img/tu1.jpg" > </A>
     	<Div id="menu1" style="display:none">
  <table width="165" border="0" cellpadding="0" cellspacing="0" class="tableBorder">
   <tr><td width="18%" height="24" align="center">&nbsp;<img src="img/subMenu_ico.GIF" width="4" height="7"></td>
       <td width="82%"><a href="/ssh2/person/browe.action" target="main">人员信息管理</a></td>
  </tr>
  <tr><td height="24" align="center">&nbsp;<img src="img/subMenu_ico.GIF" width="4" height="7"></td>
        <td><a href="/ssh2/org/browe.action?parentId=0" target="main" >组织机构管理</a></td>
  </tr>
 </table>
</Div>
     </TD>
  </TR>
  <TR align="left">
    <TD><A  onClick="show_div('menu2')"
      target=main><img src="<%=basePath %>img/tu6.jpg" ></A>
      <Div id="menu2" style="display:none">
  <table width="165" border="0" cellpadding="0" cellspacing="0" class="tableBorder">
   <tr><td width="18%" height="24" align="center">&nbsp;<img src="img/subMenu_ico.GIF" width="4" height="7"></td>
       <td width="82%"><a href="/ssh2/item/browe.action" target="main">项目管理</a></td>
  </tr>
 </table>
</Div>
      
   </TD>
  </TR>
  <TR align=left>
    <TD><A  onClick="show_div('menu3')" 
      > <img src="<%=basePath %>img/tu2.jpg" ></A>
      	<Div id="menu3" style="display:none">
  <table width="165" border="0" cellpadding="0" cellspacing="0" class="tableBorder">
   <tr><td width="18%" height="24" align="center">&nbsp;<img src="img/subMenu_ico.GIF" width="4" height="7"></td>
       <td width="82%"><a href="/ssh2/pay/browe.action" target="main">工时录入</a></td>
  </tr>
  <tr><td width="18%" height="24" align="center">&nbsp;<img src="img/subMenu_ico.GIF" width="4" height="7"></td>
       <td width="82%"><a href="/ssh2/person/updatePwdInput.action" target="main">修改密码</a></td>
  </tr>
 </table>
</Div>
     </TD>
  </TR>   
    <TR align="left">
    <TD><A  onClick="show_div('menu4')"
      target=main><img src="<%=basePath %>img/tu3.jpg" ></A>
      <Div id="menu4" style="display:none">
  <table width="165" border="0" cellpadding="0" cellspacing="0" class="tableBorder">
   <tr><td width="18%" height="24" align="center">&nbsp;<img src="img/subMenu_ico.GIF" width="4" height="7"></td>
       <td width="82%"><a href="/ssh2/pay/browe.action" target="main">员工工资补贴录入</a></td>
  </tr>

 </table>
</Div>     
   </TD>
  </TR>
  
     <TR align="left">
    <TD><A  onClick="show_div('menu5')"
      target=main><img src="<%=basePath %>img/tu5.jpg" ></A>
      <Div id="menu5" style="display:none">
  <table width="165" border="0" cellpadding="0" cellspacing="0" class="tableBorder">
   <tr><td width="18%" height="24" align="center">&nbsp;<img src="img/subMenu_ico.GIF" width="4" height="7"></td>
       <td width="82%"><a href="Charge!getfindpage.action?currpage=1" target="main">报表查询</a></td>
  </tr>

 </table>
</Div>   
   </TD>
  </TR>
 
 
    <TR align="left">
    <TD><A  href="#" onclick="quit1()"
      target="_parent"><img src="<%=basePath %>img/tu4.jpg" ></A>

   </TD>
  	</TR>
      </TBODY></TABLE>	</form></DIV></BODY></HTML>
