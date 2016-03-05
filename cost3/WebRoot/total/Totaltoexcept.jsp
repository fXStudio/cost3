<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"  uri="/WEB-INF/tag/c.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'searchhours.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="javascript" src="<%=basePath%>js/public.js"></script>
<script language="javascript" type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>

  <style type="text/css">
<!--
.STYLE8 {	font-size: 14px;
	font-weight: bold;
}
-->
  </style>
</head>
 <script type="text/javascript">
 function exceportexcelwork(){//根据月份导出
var item= document.getElementById('itme1');
 var item1=item.options[item.options.selectedIndex].value
 var date=document.getElementById('riqi1');
 var date1=date.options[date.options.selectedIndex].value;
document.form1.action="<%=basePath%>total/excelworkhours.action?&itemid="+item1+"&date="+date1;
	document.form1.submit();
 
 }
 
 function exceportHourByYear(){//根据年导出
  var item= document.getElementById('itme2');
  var item1=item.options[item.options.selectedIndex].value
  var date=document.getElementById('year');
  var date1=date.options[date.options.selectedIndex].value;
  document.form1.action="<%=basePath%>total/excelhoursByYear.action?&itemid="+item1+"&date="+date1;
  document.form1.submit();
 }
 </script>
  
  <body>
  <form action="" name="form1" method="post">
  <table width="100%" height="87" border="0" cellpadding="0" cellspacing="1">
  
  <tr>
    <td height="19" colspan="9" align="left" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE7"><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td width="832" height="32" valign="top" background="images/right_bg.jpg"><table width="121" height="30" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="35">&nbsp;</td>
              <td width="86" align="left" class="STYLE8">报表管理</td>
            </tr>
        </table></td>
        <td align="right" valign="top" background="images/right_hou.jpg">&nbsp;</td>
      </tr>
    </table></td>
    </tr>
  
  <tr>
  <td height="19"></td>
  </tr>
    <tr>
    <td width="101" height="19" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE7">项目统计报表</td>     
    <td width="63" align="left" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3"><span class="STYLE7">项目</span></td>
    <td width="122" align="left" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">
      <select id="itme2">
        <option value="">请选择</option> 
        <c:if test="${!empty listitem}">
          <c:forEach items="${listitem}" var="item" varStatus="status">
          <option value="${item.id}">${item.itemname}</option>
          </c:forEach>
          </c:if>
        </select>    </td>
    <td width="50" align="left" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3"><span class="STYLE7">年份</span></td>
     <td width="109" align="left" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">
     <select id="year">
       <option value="">请选择</option>
       <option value="2010">2010</option>
       <option value="2011">2011</option>
       <option value="2012">2012</option>
       <option value="2013">2013</option>
       <option value="2014">2014</option>
       <option value="2015">2015</option>
       <option value="2016">2016</option>
       <option value="2017">2017</option>
       <option value="2018">2018</option>
       <option value="2019">2019</option>
       <option value="2020">2020</option>
       <option value="2021">2021</option>
       <option value="2022">2022</option>
       <option value="2023">2023</option>
       <option value="2024">2024</option>
       <option value="2025">2025</option>
       <option value="2026">2026</option>
       <option value="2027">2027</option>
       <option value="2028">2028</option>
       <option value="2029">2029</option>
       <option value="2030">2030</option>
     </select>
     </td>
   
    <td width="95" colspan="2" align="left" bgcolor="#FFFFFF" class="STYLE2 STYLE3"><img src="images/daochu.jpg" width="79" height="21" onClick="exceportHourByYear()" /></td>
  </tr>
  
  <tr>
    <td height="19" colspan="9" align="center"  bgcolor="#FFFFFF" class="STYLE7">&nbsp;</td>
    </tr>
   <tr>
    <td height="19" width="101" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE7">项目统计报表</td> 
    <td width="63" align="left" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3"><span class="STYLE7">项目</span></td>
    <td width="122" align="left" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">
      <select id="itme1">
        <option value="">请选择</option> 
        <c:if test="${!empty listitem}">
          <c:forEach items="${listitem}" var="item" varStatus="status">
          <option value="${item.id}">${item.itemname}</option>
          </c:forEach>
          </c:if>
        </select>    </td>
    <td width="79" align="left" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3"><span class="STYLE7">月份</span></td>
     <td width="109" align="left" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">
 <select id="riqi1">
        <option value="">请选择</option> 
        <c:if test="${!empty riqi}">
          <c:forEach items="${riqi}" var="date" varStatus="status">
          <option value="${date.value}">${date.key}</option>
          </c:forEach>
           </c:if>
          </select>    </td>
    <td width="95" colspan="2" align="left" bgcolor="#FFFFFF" class="STYLE2 STYLE3"><img src="images/daochu.jpg" width="79" height="21" onClick="exceportexcelwork()" /></td>
  </tr>
</table>
<p>&nbsp;</p>  
  </form>
  </body>
</html>
