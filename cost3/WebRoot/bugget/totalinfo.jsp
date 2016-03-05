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
         var userid= document.getElementById("itemid");
         var htvalue= document.getElementById("htvalue");
         var peoplevalue= document.getElementById("peoplevalue");
         var palyvalue= document.getElementById("palyvalue");
         var extl= document.getElementById("extl");
         var Mvalue= document.getElementById("Mvalue");
        // var subsidy5= document.getElementById("subsidy5");
         if(Trim(userid.value)==""){
            alert('项目不能为空');
            return false;
         }
        if(Trim(htvalue.value)==""){
            alert('合同金额不能为空');
            return false;
         }
           if(isNaN(htvalue.value)){
             alert("请输入数字");
             document.getElementById("htvalue").focus();
             return false;
           }else{
             document.getElementById("htvalue").value=(Math.round(htvalue.value * 100) / 100);
           }

         if(Trim(palyvalue.value)!=""){
            if(isNaN(palyvalue.value)){
             alert("请输入数字");
             document.getElementById("palyvalue").focus();
             return false;
           }else{
             document.getElementById("palyvalue").value=(Math.round(palyvalue.value * 100) / 100);
           }
         }

         if(Trim(extl.value)!=""){
            if(isNaN(extl.value)){
             alert("请输入数字");
             document.getElementById("extl").focus();
             return false;
           }else{
             document.getElementById("extl").value=(Math.round(extl.value * 100) / 100);
              
           }
         }
         deptform.action='<%= basePath%>total/update.action';
         alert(deptform.action);
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
        <td width="86" align="left" class="STYLE2">项目预算查看</td>
      </tr>
    </table></td>
    <td align="right" valign="top" background="images/right_hou.jpg"><table width="200" border="0" cellspacing="0" cellpadding="0">
      <tr>

        <td>
        <input type="image" src="<%=basePath%>images/fanhui.jpg" onclick="javascript:window.history.back(-1);"/>
        <td width="16">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF" >
      <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">项目名称</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE2 STYLE3">
        <input type="text" id='htvalue' name="total.itemname" value="${total.itemname}" disabled="disabled">
        
        
        <span><font color='red'>*</font></span>
        </td>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE3">合同金额</td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='htvalue' name="total.htvalue" value="${total.htvalue}" disabled="disabled"><span><font color='red'>*</font></span>
        </td>
      </tr>
      
       <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">人工成本</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='peoplevalue' name="total.peoplevalue" value="${total.peoplevalue}" disabled="disabled">
        </td>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE3">支出费用</td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='palyvalue' name="total.palyvalue" value="${total.palyvalue}" disabled="disabled">
        </td>
      </tr>
      
         <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">外部分包费用</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='extl' name="total.extl" value="${total.extl}" disabled="disabled">
        </td>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE3">税金</td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='sj' name="total.sj" value="${total.sj}" disabled="disabled">
        </td>
      </tr>
       <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">Contract Cost</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
		<input type="text" id='chengben' name="total.chengben" value="${total.chengben}" disabled="disabled">
        </td>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">REVENUE</td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='revenue' name="total.revenue" value="${total.revenue}" disabled="disabled">
        </td>
      </tr>
      <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">创建日期</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
		<input type="text" id='createdate' name="total.createdate" value="${total.createdate}" disabled="disabled">
        </td>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE3">m值 </td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="hidden" name="total.userid" value="${total.createdate}">
         <input type="hidden" name="total.id" value="${total.id}">
           <input type="text" id='mv' name="total.mv" value="${total.mv}" disabled="disabled">
        </td>
         
      
        \
      </tr>
      <tr>
    </table>
</table>
</form>  
  </body>
</html>