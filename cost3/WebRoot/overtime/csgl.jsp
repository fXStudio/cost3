<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"  uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>柏坚联创工程顾部工时核算系统</title>
<link href="<%=basePath%>css/index.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="address">
	<div class="bt">超时管理</div>
	<div class="gsgl_cx">登录人员：<span class="f12b_009f43">${username}</span> 	&nbsp;&nbsp;&nbsp;&nbsp;	
	日期：
		<select name="select">
		  <s:iterator id="date" value="dateList" status="i">    
		    <option value="<s:property value="date"/>" <s:if test="#i.last">selected="selected" </s:if>><s:property value="date"/></option>
		  </s:iterator>    
	    </select>　　　
<%--	时间：<span class="f12b_9f1300">2013-11-10至16日　　　</span>--%>
<%--      <input type="submit" name="Submit" value="统计" class="ssan" />--%>
	</div>	
</div>
<!--地址栏结束-->
<div class="table">
  <table width="100%" border="0" align="center" cellpadding="3" cellspacing="0" style="text-align:center">
  <tr><td colspan="8" align="left"><input type="submit" name="Submit252" value=" " onclick="javascript:location.href('showAddPage.action')"  class="tan1"/></td>
  </tr>
    <tr class="tr">
      <td align="center">项目</td>
      <td align="center">操作
		</td>
    </tr>
    <s:iterator id="data" value="dataList" status="i">   
    <tr <s:if test="i.even">class="tr2"</s:if>>
      <td> <s:property value="data.itemname"/> </td>
      <td><input type="button" onclick="deleteInfo('5')" name="Submit222" value=" "  class="tan2"/>
		  <input type="button" onclick="javascript:location.href='http://localhost:8081/cost3/dept/updateInput.action?dept.id=5'" name="Submit232" value=" "  class="tan3" />
	  </td>
    </tr>
    </s:iterator>
<%--    <tr class="tr2">--%>
<%--      <td colspan="8" align="center">--%>
<%--        <input type="submit" name="Submit2" value="保存" class="ssan" /></td>--%>
<%--    </tr>--%>
  </table>
</div>
<div class="sabrosus"><span class="disabled"> < </span><span class="current">1</span><a href="#?page=2">2</a><a href="#?page=3">3</a><a href="#?page=4">4</a><a href="#?page=5">5</a><a href="#?page=6">6</a><a href="#?page=7">7</a>...<a href="#?page=199">199</a><a href="#?page=200">200</a><a href="#?page=2"> > </a></div>
</body>
</html>
