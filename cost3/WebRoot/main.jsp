<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
	  <frameset rows="80,*,23" frameborder="NO" border="0" framespacing="0" id="frame1">
        <FRAME name=title src="<%=basePath %>user/loginTitle.action"  scrolling=no marginwidth="%100%" marginheight="0">
        <frameset cols="169,*" frameborder="NO" border="0" framespacing="0" id="frame2"> 
        <FRAME name=tree marginWidth=0 marginHeight=0 scrolling=no src="<%=basePath %>user/loginLeft.action"  style=" border-right:3px solid #ccc;background:#f0f0f0">
		<FRAME name=main marginWidth=0 marginHeight=0 scrolling=yes noresize="noresize" style="overflow-x:hidden;" 
		 src="<%=basePath %>user/loginLeft!loginCenter.action">	
        </frameset>
       <FRAME name=bottom src="<%=basePath %>user/loginBottom.action"  noResize scrolling=no marginheight="0">
    </frameset> 
</html>
