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
         var holidayname= document.getElementById("holidayname");
         var holidate= document.getElementById("holidate");
         var len= document.getElementById('holiyear');
         var year=len.options[len.options.selectedIndex].value
        
         if(Trim(holidayname.value)==""){
            alert('假期名称不能为空');
            return false;
         }
        if(Trim(holidate.value)==""){
            alert('日期不能为空');
            return false;
         }
         if(Trim(year)==""){
            alert('年份不能为空');
            return false;
         }
         deptform.action='<%= basePath%>holiday/add.action';
         deptform.submit();
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
        <td width="86" align="left" class="STYLE2">假期添加</td>
      </tr>
    </table></td>
    <td align="right" valign="top" background="images/right_hou.jpg"><table width="200" border="0" cellspacing="0" cellpadding="0">
      <tr>

        <td><input type="image" src="<%=basePath%>images/baocuo.jpg" onclick="checkSubmit()"/>
        <input type="image" src="<%=basePath%>images/fanhui.jpg" onclick="javascript:window.history.back(-1);"/><td>
        <td width="16">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF">
      <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">假期名称</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='holidayname' name="holiday.holidayname" ><span><font color='red'>*</font></span>
        </td>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE2 STYLE3">年份</td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE2 STYLE3">
        
        <select id="holiyear" name='holiday.holiyear'>
           <option value="">请选择年份</option>
           <c:forEach items="${yearlist}" var="year">
           <option value="${year }">${year }</option>
          </c:forEach>
        </select>
        
        <span><font color='red'>*</font></span>
        </td>
      </tr>
      
       <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">日期</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE2 STYLE3">
        <input type="text" id='holidate' name="holiday.holidate" onfocus="new WdatePicker(this,null,false,'whyGreen')" >
        <span><font color='red'>*</font></span>
        </td>
        
         <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE2 STYLE3"></td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE2 STYLE3">
      </tr>
      
         <tr>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE2 STYLE3"></td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE2 STYLE3">
        </td>
      </tr>
      <tr>
    </table>
</table>
</form>  
  </body>
</html>
