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
	
	<script language="javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
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
         deptform.action='<%= basePath%>total/add.action';
         //alert(deptform.action);
         deptform.submit();
      }
      function onchange11(){
        var c=  document.getElementById('itemid').value;
        var date= document.getElementById('createdate').value;
        //alert(date);
       // alert(c);
        if(c==''){
        alert("请选择项目");
        }
       if(date==''){
       document.getElementById('itemid').value=0;
        alert("请先选择创建日期");
        
        }
        if(c!=''&&date!=''){
          $.ajax({
               type: "POST",
               url: "<%=basePath%>/total/getitemhours.action",
               data: "itemid="+c+"&date="+date,
               success: function(msg){
               //alert(msg);
                 document.getElementById('peoplevalue').value=msg.split(",")[0];
                  document.getElementById('htvalue').value=msg.split(",")[1];
                   document.getElementById('mv').value=msg.split(",")[2];
                  }
              });
              }
          }
          function allvalue(){
         // alert(v.value);
          
        var  htvalue=document.getElementById('htvalue').value;//合同金额
       var   peoplevalue=document.getElementById('peoplevalue').value;//人工成本
       var palyvalue =document.getElementById('palyvalue').value;//花费
        var extl=document.getElementById('extl').value;//分保费
        var sj=document.getElementById('sj').value;//税金
        var mv=document.getElementById('mv').value;
           //var mv= (parseFloat(htvalue)-parseFloat(palyvalue)-parseFloat(extl)-parseFloat(sj))/parseFloat(peoplevalue)//m值
           //document.getElementById('mv').value=mv.toFixed(2);
           document.getElementById('chengben').value=parseFloat(peoplevalue)+parseFloat(palyvalue)+parseFloat(extl)+parseFloat(sj);
           //alert(extl+","+palyvalue+","+sj+","+peoplevalue+","+mv.toFixed(2));
       
           var total=""+(peoplevalue*parseFloat(mv)+Number(extl)+Number(palyvalue)+Number(sj));
              // alert("total:"+total);
           //document.getElementById('revenue').value='0';
           document.getElementById('revenue').value=total;
          
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
        <td width="86" align="left" class="STYLE2">项目统计添加</td>
      </tr>
    </table></td>
    <td align="right" valign="top" background="images/right_hou.jpg"><table width="200" border="0" cellspacing="0" cellpadding="0">
      <tr>

        <td><input type="image" src="<%=basePath%>images/baocuo.jpg" onclick="checkSubmit()"/>
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
        <select name="total.itemid" id='itemid' onchange="onchange11()">
        <option value="0">请选择项目</option>
        
        <c:forEach items="${listitem}" var="item">
        <option value="${item.id}">${item.itemname}</option>
        </c:forEach>
        </select>
        
        
        <span><font color='red'>*</font></span>
        </td>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE3">合同金额</td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='htvalue' name="total.htvalue" onchange="allvalue()" value="0" onfocus="if (value =='0'){value =''}" onblur="if (value ==''){value='0'}"><span><font color='red'>*</font></span>
        </td>
      </tr>
      
       <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">人工成本</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='peoplevalue' name="total.peoplevalue" readonly="true">
        </td>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE3">支出费用</td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='palyvalue' name="total.palyvalue" onchange="allvalue()" value="0" onfocus="if (value =='0'){value =''}" onblur="if (value ==''){value='0'}">
        </td>
      </tr>
      
         <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">外部分包费用</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='extl' name="total.extl" onchange="allvalue()" value="0" onfocus="if (value =='0'){value =''}" onblur="if (value ==''){value='0'}">
        </td>
        <td width="8%" align="center"  bgcolor="#EFF3F7" class="STYLE3">税金</td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='sj' name="total.sj" onchange="allvalue()" value="0" onfocus="if (value =='0'){value =''}" onblur="if (value ==''){value='0'}">
        </td>
      </tr>
       <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">Contract Cost</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
		<input type="text" id='chengben' name="total.chengben">
        </td>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">REVENUE </td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='revenue' name="total.revenue">
        </td>
      </tr>
      <tr>
        <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">创建日期</td>
        <td width="27%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
		<input type="text" id='createdate' onchange="onchange11()" value='' name="total.createdate" onClick="new WdatePicker(this,'%Y-%M',true,'whyGreen')" >
        </td>
         <td width="8%" height="23" align="center"  bgcolor="#EFF3F7" class="STYLE3">m值 </td>
        <td width="43%" align="left"  bgcolor="#EFF3F7" class="STYLE3">
        <input type="text" id='mv' name="total.mv">
        </td>
      </tr>
      <tr>
    </table>
</table>
</form>  
  </body>
</html>
