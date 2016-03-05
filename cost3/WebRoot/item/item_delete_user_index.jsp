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
    
    <title>项目的人员信息</title>
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
         
     function  ischeck(){
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
                deptform.action='<%=basePath%>item/addItemUser!deleteItemUser.action?item.deleteid='+v;
                deptform.submit();
                 window.close();
            }else{
              return false;
            } 
}
	</script>
  </head>
  <body>
  <form name='deptform' action="" method='post' onsubmit='return false'>
  <input type='hidden' name="itemuser.itemid" value='${itemuser.itemid}'>
  <table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
  <tr>
    <td width="832" height="32" valign="top" background="images/right_bg.jpg"><table width="121" height="30" border="0" cellpadding="0" cellspacing="0">
      <tr>
        
        <td width="35"><input type="image" src="<%=basePath%>images/shanchu.jpg" onclick="ischeck()" class="STYLE3"/></td>
        <td width="100" align="left" class="STYLE2"></td>
      </tr>
    </table></td>
    <td align="right" valign="top" background="images/right_hou.jpg"><table width="200" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><td>
        <td width="16">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#c6c6c6">
      <tr>
         <td width="5%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">选择
         <input type="checkbox" name="deptradios" onclick="selectAll()">
        </td>
        <td width="5%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">序号</td>
        <td width="19%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">人员姓名</td>
        <td width="19%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">人员编码</td>
        <td width="19%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">岗位级别</td>
        <td width="19%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">籍贯</td>
        <td width="19%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">所属机构</td>
      </tr>
      <tr>
      <!-- 列表数据栏 -->
          
          <c:if test="${!empty pm.datas}">
          <c:forEach items="${pm.datas }" var="user" varStatus="status">
	      <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	      <td><input type="checkbox" name="deptradio" value="${user[0].id} "></td>
		      <td align="center" vAlign="center" class="STYLE3">${status.index+1 }</td>
	          <td align="center" vAlign="center" class="STYLE3">${user[0].username }</td>
	          <td align="center" vAlign="center" class="STYLE3">${user[0].usercode }</td>
	          <td align="center" vAlign="center" class="STYLE3">${user[0].worklevel }</td>
	          <td align="center" vAlign="center" class="STYLE3">${user[0].census }</td>
	          <td align="center" vAlign="center" class="STYLE3">${user[0].dept.departmentname}</td>
	          <td align="center" vAlign="center" class="STYLE3"></td>
        </tr>
        </c:forEach>
		</c:if>
        <!-- 在列表数据为空的时候，要显示的提示信息 -->
	    <c:if test="${empty pm.datas}">
	    <tr>
	    	<td colspan="6" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </c:if>
	       <tr>
	<pg:pager url="user/broweItemUser!broweDeleteItemUser.action" items="${pm.total}" maxPageItems="${ps}" export="currentPageNumber=pageNumber">
		<pg:param name="itemuser.itemid" value="${itemuser.itemid}"/>
		<pg:first>
			<a href="${pageUrl}" id="firstpageurl" class="n2"><font class="STYLE2">首页</font></a>
		</pg:first>
		<pg:prev>
		<a href="${pageUrl }" class="n2"><font class="STYLE2">前页</font></a>
		</pg:prev>
		<pg:pages>
			<c:choose>
				<c:when test="${currentPageNumber eq pageNumber}">
					<font color="red">${pageNumber }</font>
				</c:when>
				<c:otherwise>
					<a href="${pageUrl }">${pageNumber }</a>
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
      </tr>   
    </table></td>
  </tr>
</table>
</form>  
  </body>
</html>
