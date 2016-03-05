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
    
    <title>柏坚工时核算系统</title>
     <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="<%=basePath%>css/text.css" type="text/css" href="styles.css">
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
	<script language="javascript" type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
      function checkSubmit(){

      }
      
  function checkNumber(TempS) {
	for(Count=0;Count<TempS.length;Count++) {
		TempChar=TempS.substring(Count,Count+1);
		RefString="0123456789";
		if (RefString.indexOf(TempChar,0)==-1) {
			return false;
		}
	}
	return true;
}
</script>
  </head>
  <body>
  <form name='deptform'action="" method='post' onsubmit="return false">
  <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td width="832" height="32" valign="top" background="images/right_bg.jpg"><table width="121" height="30" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="35">&nbsp;</td>
        <td width="86" align="left" class="STYLE2">工时补录</td>
      </tr>
    </table></td>
    <td align="right" valign="top" background="images/right_hou.jpg"><table width="200" border="0" cellspacing="0" cellpadding="0">
      <tr>

        <td>
        <input type="image" src="<%=basePath%>images/baocuo.jpg" onclick="checkSubmit()"/>
        <input type="image" src="<%=basePath%>images/fanhui.jpg" onclick="javascript:window.history.back(-1);"/>
        </td>
        <td width="16">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="0" align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF" >
      <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">人员名称</td>
        <td width="27%" align="left" bgcolor="#EFF3F7" class="STYLE2 STYLE3">
        <input type="hidden" id='userid' name="hour.userid" >
        <input type="text" id='usernameid' disabled="disabled" name="hour.userid" ><span><font color='red'>*</font></span>
        <input type="button"  value="选择人员" onclick="openWin('<%= basePath%>user/selectBrowe.action','selectOrgs',800,400,1);">
        <span><font color='red'>*</font></span>
        </td>
        <td width="8%" align="center" bgcolor="#EFF3F7" class="STYLE3">项目</td>
        <td width="43%" align="left" bgcolor="#EFF3F7" class="STYLE3">
       <input type="text" id='usercode' name="user.usercode"><span><font color='red'>*</font></span>
        </td>
      </tr>
        <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">工时</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
		<input type="text" id='hour' name="hour.hour" maxlength='1'>
        </td>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE3">日期</td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
		<input type="text" id='todaydate' name="hour.todaydate" onfocus="new WdatePicker(this,null,false,'whyGreen')">
        </td>
      </tr>
    </table>
</table>
</form>  
  </body>
</html>
