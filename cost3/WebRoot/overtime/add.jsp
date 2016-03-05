<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

.STYLE3 {
	font-size: 12px;
}
-->
</style>
<script language="javascript" src="<%=basePath%>js/public.js"></script>
<script language="javascript" type="text/javascript"
	src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
      function checkSubmit(){
         var userid= document.getElementById("userid");
         var basepay= document.getElementById("basepay");
         var deptIdId= document.getElementById("deptIdId");
         var post= document.getElementById("post");
         var subsidy1= document.getElementById("subsidy1");
         var subsidy2= document.getElementById("subsidy2");
         var subsidy3= document.getElementById("subsidy3");
         if(Trim(userid.value)==""){
            alert('人员不能为空');
            return false;
         }
        if(Trim(basepay.value)==""){
            alert('基本工资不能为空');
            return false;
         }
<%--          if(Trim(deptIdId.value)==""){--%>
<%--            alert('部门不能为空');--%>
<%--            return false;--%>
<%--         }--%>
<%--          if(Trim(post.value)==""){--%>
<%--            alert('职位不能为空');--%>
<%--            return false;--%>
<%--         }--%>
           if(isNaN(basepay.value)){
             alert("请输入数字");
             document.getElementById("basepay").focus();
             return false;
           }else{
             document.getElementById("basepay").value=(Math.round(basepay.value * 100) / 100);
           }

         if(Trim(subsidy1.value)!=""){
            if(isNaN(subsidy1.value)){
             alert("请输入数字");
             document.getElementById("subsidy1").focus();
             return false;
           }else{
             document.getElementById("subsidy1").value=(Math.round(subsidy1.value * 100) / 100);
           }
         }

         if(Trim(subsidy2.value)!=""){
            if(isNaN(subsidy2.value)){
             alert("请输入数字");
             document.getElementById("subsidy2").focus();
             return false;
           }else{
             document.getElementById("subsidy2").value=(Math.round(subsidy2.value * 100) / 100);
              
           }
         }
         if(Trim(subsidy3.value)!=""){
           if(isNaN(subsidy3.value)){
             alert("请输入数字");
             document.getElementById("subsidy3").focus();
             return false;
           }else{
             document.getElementById("subsidy3").value=(Math.round(subsidy3.value * 100) / 100);
              
           }
         }
         deptform.action='<%=basePath%>subsidy/add.action';
         deptform.submit();
      }
      function allchange(){
         var dwbili="43.3";
         var bjbili="44.1";
         var shbili="44.0";
         var census= document.getElementById("census").value;//籍贯
         var basepay= document.getElementById("basepay").value;
         var subsidy1= document.getElementById("subsidy1").value;
         var subsidy2= document.getElementById("subsidy2").value;
         var subsidy3= document.getElementById("subsidy3").value;
         var housp="0";
         var overtimepay="0";
         overtimepay=parseFloat((parseFloat(basepay/21.75/8))*1.5);
          document.getElementById("overtimepay").value=overtimepay.toFixed(2);
         //alert(census);
         if(census==''){
        	 alert('请先选择人员');
        	 return;
         }
         var subsidy=parseFloat(subsidy1)+parseFloat(subsidy2)+parseFloat(subsidy3);
         if(census=="dw"){
         housp=parseFloat((parseFloat(basepay)*13+parseFloat(subsidy)*12+parseFloat(basepay)*parseFloat(dwbili)*12/100+parseFloat(basepay)*13*2/100)/1920);
         }
         if(census=="hk"){
          housp=parseFloat((parseFloat(basepay)*13+parseFloat(subsidy)*13+parseFloat(basepay)*13*2/100)/1920);
         }
         if(census=="bj"){
           housp=parseFloat((parseFloat(basepay)*13+parseFloat(subsidy)*12+parseFloat(basepay)*parseFloat(bjbili)*12/100+parseFloat(basepay)*13*2/100)/1920);
         }
         if(census=="sh"){
          housp=parseFloat((parseFloat(basepay)*13+parseFloat(subsidy)*12+parseFloat(basepay)*parseFloat(shbili)*12/100+parseFloat(basepay)*13*2/100)/1920);
         
         }
          document.getElementById("hourspaly").value=housp.toFixed(2);
         var date=new Date();
         var year=date.getYear();
         var month=date.getMonth()+1;
       if(parseInt(month)<=9){
       month="0"+month;
       }
       
        var data= date.getDate();
        if(parseInt(data)<=9){
        data="0"+data;
        }
        var newdate=year+"-"+month+"-"+data;
        //alert(newdate);
      }
</script>
</head>
<body bgcolor="#f1f1f1">
<div class="address">
	<div class="bt">工资添加</div>	
</div>
<div class="table" style="margin-top:15px;">
	<form name='deptform' action="overtime/showAddPage.action" method='post'>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td width="8%" height="23" align="center"  >日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;期:</td>
				<td width="27%" align="left" class="STYLE2 STYLE3">
				<input type="text" name="overtime.date" id='date' readonly style="background: #CCCCCC" value="<s:date name="new java.util.Date()" format="yyyy-MM"/>">	
				</td>
			</tr>
			<tr>
				<td width="8%" height="23" align="center"  >项目名称:</td>
				<td width="27%" align="left" class="STYLE2 STYLE3">
				<input type="text" name="itemName" id='itemName' readonly style="background: #CCCCCC" value=""> 
          		<input type="" name="overtime.itemid" id="itemId" value="">
				
				<input type="button" value="选择项目"
					onclick="openWin('<%=basePath%>item/selectBrowe.action','selectOrgs',800,500,1);">
	          	<input type="button" value="选择人员"
					onclick="">
				</td>
			</tr>

			<tr>
	          <td align="center" colspan="4">
	          	<input type="button" name= "button"  value="保存" onClick="checkSubmit()"/>
	        	<input type="reset" name="Submit3" value="重置" />
	          	<input type="button" name= "button"  value="返回" onClick="javascript:window.history.back(-1);"/>
	          	<input type="hidden" id='census' name="subsidy.user.census">
	          </td>
	        </tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" >
			<tr>
				<td width="8%" height="23" align="center"  >姓名</td>
				<td width="8%" align="center"  >小时</td>
			</tr>
		</table>
		
	</form>
</div>
</body>
</html>
