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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/text.css">
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
<script type="text/javascript">
            function  ischeck(){
            var flag = false;
            var v=[];
            
            var dept = document.getElementsByName('deptradio');
            for(var i=0;i<dept.length;i++){
                if(dept[i].checked){
                  v.push(dept[i].value);
                }
            }
            if(v.length==0){
              alert('请先选择数据');
              return false;
            }
            if (confirm("确认删除？")){
                deptform.action='<%=basePath%>repairhoust/delete.action?hour.deleteid='+v;
                deptform.submit();
                return true;
            }else{
              return false;
            } 
}

    function addInput(){
       document.deptform.action='<%=basePath%>repairhoust/addInput.action';
       document.deptform.submit();
    }
    
    function updateInput(){
       
            var v=[];
            var dept = document.getElementsByName('deptradio');
            for(var i=0;i<dept.length;i++){
                if(dept[i].checked){
                  v.push(dept[i].value);
                }
            }
            
            if(v.length==0){
              alert('请先选择数据');
              return false;
            }
             if(v.length>1){
              alert('请选择一条数据进行修改');
              return false;
            }
            deptform.action='<%=basePath%>user/updateInput.action?user.id='+v;
            deptform.submit();
         }
         
         function onQuery(){
         
           deptform.action='<%=basePath%>user/browe.action';
           deptform.submit();
         }
         
         function look(){
            var v=[];
            var dept = document.getElementsByName('deptradio');
            for(var i=0;i<dept.length;i++){
                if(dept[i].checked){
                  v.push(dept[i].value);
                }
            }
            
            if(v.length==0){
              alert('请先选择数据');
              return false;
            }
             if(v.length>1){
              alert('请选择一条数据进行查看');
              return false;
            }
            deptform.action='<%=basePath%>user/updateInput!look.action?user.id='+v;
            deptform.submit();
         }
         
         function onQuery(){
         
           deptform.action='<%=basePath%>user/browe.action';
           deptform.submit();
         }
         
         function selectAll(){
           var deptAllCheck = document.getElementsByName('deptradios');
           
           var dept = document.getElementsByName('deptradio');
           
           if(deptAllCheck[0].checked){
              
              for(var i=0;i<dept.length;i++){
                 
                 dept[i].checked = true;
              }
           }else{
           
              for(var i=0;i<dept.length;i++){
                 
                 dept[i].checked = false;
              }
           }
         }
</script>
  </head>
  <body>
  <form name='deptform'action="" method='post' onsubmit="return false">
  <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0" >
  <tr>
    <td width="832" height="32" valign="top" background="images/right_bg.jpg"><table width="121" height="30" border="0" cellpadding="0" cellspacing="0" style="FONT-SIZE: 10pt">
      <tr>
        <td width="35">&nbsp;</td>
        <td width="90" align="left" class="STYLE2">人员信息</td>
      </tr>
    </table></td>
    <td align="right" valign="top" background="images/right_hou.jpg"><table width="300" border="0" cellspacing="0" cellpadding="0" >
      <tr>
        <td class="STYLE3">人</td>
        <td class="STYLE3">员</td>
        <td class="STYLE3"><input type='text' name='user.username' value="${user.username }" size='6'/></td>
        <td class="STYLE3">项</td>
        <td class="STYLE3">目</td>
        <td><input type='text' name='user.usercode' value="${user.usercode}" size='6'/></td>
        <td><input type="image" src="<%=basePath%>images/chankan.jpg" onclick="onQuery()" class="STYLE3"/></td>
        <td width="16">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <c:if test="${login.type eq 'system'}">
        <td>
        <input type="image" src="<%=basePath%>images/tianjia.jpg" onclick="addInput()" class="STYLE3"/>
        <td>
        <td>
        <input type="image" src="<%=basePath%>images/shanchu.jpg" onclick="ischeck()" class="STYLE3"/>
        <td>
        <td><input type="image" src="<%=basePath%>images/edit.jpg" onclick="updateInput()" class="STYLE3"/><td>
        </c:if>
        <td><input type="image" src="<%=basePath%>images/xinxin.jpg" onclick="look()" class="STYLE3"/><td>
        
        <td width="16">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  
  <tr>
    <td colspan="2" align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#c6c6c6">
      <tr>
        <td width="5%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">选择
        <input type="checkbox" name="deptradios" onclick="selectAll()" class="STYLE2">
        </td>
        <td width="3%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">序号</td>
        <td width="5%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">人员姓名</td>
        <td width="5%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">项目名称</td>
        <td width="5%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">工时</td>
        <td width="3%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">日期</td>
      </tr>
      <tr>
      <!-- 列表数据栏 -->
          
          <c:if test="${!empty pm.datas}">
          <c:forEach items="${pm.datas }" var="hour" varStatus="status">
	      <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	          <td align="center" vAlign="center" class="STYLE2">
	          <input type="checkbox" name="deptradio" value="${hour.id} " class="STYLE3"></td>
		      <td align="center" vAlign="center" class="STYLE3">${hour.index+1 }</td>
	          <td align="center" vAlign="center" class="STYLE3">${hour.userid }</td>
	           <td align="center" vAlign="center" class="STYLE3">${hour.itemid }</td>
	           <td align="center" vAlign="center" class="STYLE3">${hour.hour }</td>
	           <td align="center" vAlign="center" class="STYLE3">${hour.hour }</td>
	           <td align="center" vAlign="center" class="STYLE3">${hour.todaydate }</td>
        </tr>
        </c:forEach>
		</c:if>
        <!-- 在列表数据为空的时候，要显示的提示信息 -->
	    <c:if test="${empty pm.datas}">
	    <tr>
	    	<td colspan="11" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </c:if>
      </tr>

    </table> 
    
      <tr>
      <td></td>
      <td width="0" align="right" class="STYLE2" style="FONT-SIZE: 12pt">	
      	    	<pg:pager url="user/browe.action" items="${pm.total}" maxPageItems="${ps}" export="currentPageNumber=pageNumber">
		<pg:param name="user.username" value="${user.username}"/>
		<pg:param name="user.usercode" value="${user.usercode}"/>
		<pg:first>
			<a href="${pageUrl}" id="firstpageurl" class="n2"><font class="STYLE2">首页</font></a>
		</pg:first>
		<pg:prev>
		<a href="${pageUrl }"><font class="STYLE2" class="n2">前页</font></a>
		</pg:prev>
		<pg:pages>
			<c:choose>
				<c:when test="${currentPageNumber eq pageNumber}">
					<font color="red">${pageNumber }</font>
				</c:when>
				<c:otherwise>
					<a href="${pageUrl }" class="n2">${pageNumber }</a>
				</c:otherwise>
			</c:choose>
		</pg:pages>
		<pg:next>
		<a href="${pageUrl }" class="n2"><font class="STYLE2">后页</font></a>
		</pg:next>
		<pg:last>
		<a href="${pageUrl }" class="n2"><font class="STYLE2">尾页</font></a>
		</pg:last>
	</pg:pager>
    </td>
    </tr>  
</table>
</form>  
  </body>
</html>
