<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.ruling.cost.user.domain.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c"  uri="/WEB-INF/tag/c.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'post.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>
.tab1{ margin:20px 0 0 0; padding:0px; background-color:#c6c6c6}
.tab2{ margin:0px; padding:0px; font-size: 13px;}
</style>
  </head>
  
  <body>
  <center>
  <table  width="400" border="0" cellspacing="1" cellpadding="1" class="tab1">
    <% List<Map<String,List<User>>> listMap=(List<Map<String,List<User>>>)request.getAttribute("map"); 
    //Set<Map.Entry<String,List<User>>> set = map.entrySet();
    //Iterator<Map.Entry<String,List<User>>> it = set.iterator(); 
    for (int i=0;i<listMap.size();i++) 
    {
    	
    	Map entry =listMap.get(i);
    	%>
    	<tr bgcolor="#E3f3f7" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#E3f3f7';">
    	<td width="40%" align="center" >
    	<b><%=entry.get("key")%></b>
    	</td>
    	<td>
    		<table width="100%" class="tab2" border="0" cellspacing="1" cellpadding="3" bgcolor="#ffffff">
    	<% List<User>  list=(List<User>)entry.get("value");
    	 for(int j=0;j<list.size();j++){
    	%>
      <tr><td bgcolor="#E3f3f7" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#E3f3f7';">
      
    	
    
    	<%=list.get(j).getUsername()%>
    	</td></tr>
		<% }%>
			</table>
		</td>
    	</tr>
    	<% 
     }
       %>
    
    
    
    </table>
    </center>
  </body>
</html>
