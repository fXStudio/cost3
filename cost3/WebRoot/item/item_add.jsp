<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"  uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
	<script language="javascript" type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
      function checkSubmit(){
         var itemname= document.getElementById("itemname");
         var itemcode= document.getElementById("itemcode");
         var begindate= document.getElementById("starttime");
          var enddate= document.getElementById("endtime");
         var itemstate= document.getElementById("itemstate");
        
         if(Trim(itemname.value)==""){
            alert('项目名称不能为空');
            return false;
         }
        if(Trim(itemcode.value)==""){
            alert('项目编码不能为空');
            return false;
         }
         if(Trim(begindate.value)==""){
            alert('开始日期不能为空');
            return false;
         }
         if(Trim(itemstate.value)==""){
            alert('项目状态');
            return false;
         }
         
         deptform.action='<%= basePath%>item/add.action';
         deptform.submit();
      }
</script>
  </head>
<body bgcolor="#f1f1f1">
<div class="address">
	<div class="bt">项目添加</div>	
</div>
<div class="table" style="margin-top:15px;">
<form name='deptform'action="" method='post' onsubmit="return false">
	<table width="100%" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td width="8%" height="23" align="center"   >项目名称</td>
        <td width="27%" align="left"   >
        <input type="text" id='itemname' name="item.itemname" ><span><font color='red'>*</font></span>
        </td>
        <td width="8%" align="center"   >项目编号</td>
        <td width="43%" align="left"   >
        <input type="text" id='itemcode' name="item.itemcode"><span><font color='red'>*</font></span>
        </td>
      </tr>
      
       <tr>
        <td width="8%" height="23" align="center"   >开始日期</td>
        <td width="27%" align="left"   >
        <input type="text" id='starttime' name="item.starttime" onfocus="new WdatePicker(this,null,false,'whyGreen')" >
        <span><font color='red'>*</font></span>
        </td>
        <td width="8%" align="center"   >结束日期</td>
        <td width="43%" align="left"   >
        <input type="text" id='endtime' name="item.endtime" onfocus="new WdatePicker(this,null,false,'whyGreen')" >
        </td>
      </tr>
      
         <tr>
        <td width="8%" height="23" align="center"   >项目状态</td>
        <td width="27%" align="left"   >
       <select id='itemstate' name="item.itemstate">
		  <option value ="activity">进行中</option>
		  <option value ="end">结束</option>
		  <option value ="stop">暂停</option>
		</select>
        </td>
        <td width="8%" align="center"   ></td>
        <td width="43%" align="left"   >
        </td>
      </tr>
      <tr>
          <td align="center" colspan="4">
          	<input type="button" name= "button"  value="添加" onClick="checkSubmit()"/>
        	<input type="reset" name="Submit3" value="重置" />
          	<input type="button" name= "button"  value="返回" onClick="javascript:window.history.back(-1);"/>
          </td>
        </tr>
    </table>
</form>
</div>
  </body>
</html>
