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
            //alert(v);
                deptform.action='<%=basePath%>total/delete.action?id='+v;
                deptform.submit();
                return true;
            }else{
              return false;
            } 
}

    function addInput(){
       document.deptform.action='<%=basePath%>total/toadd.action';
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
            }else if(v.length>1){
              alert("请选择一条数据进行修改");
              return false;
            }
            deptform.action='<%=basePath%>total/toupdate.action?id='+v;
            deptform.submit();
         } 
         
         function onQuery(){
           deptform.action="<%=basePath%>total/browe.action";
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
             deptform.action='<%=basePath%>total/find.action?id='+v;
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
         function  toexcept(){
        	 deptform.action='<%=basePath%>total/toexcept.action';
             deptform.submit();
             }
             function yushezhi(){
              deptform.action='<%=basePath%>total/toexcept.action';
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
        <td width="86" align="left" class="STYLE2">项目统计</td>
      </tr>
    </table></td>
    <td align="right" valign="top" background="images/right_hou.jpg"><table width="200" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="STYLE3">项</td>
        <td class="STYLE3">目</td>
        <td class="STYLE3">名</td>
        <td class="STYLE3">称</td>
        <td><input type="text" id='usernameid' name="total.itemname" value="${total.itemname}" size=6></td>
        <td><input type="image" src="<%=basePath%>images/chankan.jpg" onclick="onQuery()" class="STYLE3"/></td>
        <td width="16">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <c:if test="${login.type eq 'system'}">
        <td><input type="image" src="<%=basePath%>images/tianjia.jpg" onclick="addInput()" class="STYLE3"/><td>
        </c:if>
        <c:if test="${login.type eq 'system'}">
        <td><input type="image" src="<%=basePath%>images/shanchu.jpg" onclick="ischeck()" class="STYLE3"/><td>
         </c:if>
          <c:if test="${login.type eq 'system'}">
        <td><input type="image" src="<%=basePath%>images/edit.jpg" onclick="updateInput()" class="STYLE3"/><td>
        </c:if>
        <td><input type="image" src="<%=basePath%>images/xinxin.jpg" onclick="look()" class="STYLE3"/></td>
         <td><input type="image" src="<%=basePath%>images/dc.gif" onclick="toexcept()" class="STYLE3"/></td>
        <td width="16">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#c6c6c6" style="FONT-SIZE: 12pt">
      <tr>
        <td width="6%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">选择
        <input type="checkbox" name="deptradios" onclick="selectAll()">
        </td>
        <td width="4%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">序号</td>
        <td width="15%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">项目名称</td>
        <td width="7%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">合同总额</td>
        <td width="8%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">人工成本</td>
        <td width="8%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">支出费用</td>
        <td width="8%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">外部分包费用</td>
          <td width="7%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">税&nbsp;&nbsp;金</td>
        <td width="5%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">M值</td>
        <td width="11%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">Contract Cost</td>
         <td width="11%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">REVENUE</td>
        <td width="10%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2">创建日期</td>
      </tr>
      <tr>
      <!-- 列表数据栏 -->
          
          <c:if test="${!empty pm.datas}">
          <c:forEach items="${pm.datas }" var="subsidy" varStatus="status">
	      <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	          <td align="center" vAlign="center"><input type="checkbox" name="deptradio" value="${subsidy.id} "></td>
		      <td align="center" vAlign="center" class="STYLE3">${status.index+1}</td>
	          <td align="left" vAlign="center" class="STYLE3">&nbsp;${subsidy.itemname}</td>
	          <td align="left" vAlign="center" class="STYLE3">&nbsp;${subsidy.htvalue}</td>
	          <td align="left" vAlign="center" class="STYLE3">&nbsp;${subsidy.peoplevalue }</td>
	          <td align="left" vAlign="center" class="STYLE3">&nbsp;${subsidy.palyvalue}</td>
	          <td align="left" vAlign="center" class="STYLE3">&nbsp;${subsidy.extl}</td>
	          <td align="left" vAlign="center" class="STYLE3">&nbsp;${subsidy.sj}</td>
	          <td align="center" vAlign="center" class="STYLE3">${subsidy.mv}</td>
	          <td align="left" vAlign="center" class="STYLE3">&nbsp;${subsidy.chengben}</td>
	            <td align="left" vAlign="center" class="STYLE3">&nbsp;${subsidy.revenue}</td>
	          <td align="center" vAlign="center" class="STYLE3">${subsidy.createdate}</td>
	          
        </tr>
        </c:forEach>
		</c:if>
        <!-- 在列表数据为空的时候，要显示的提示信息 -->
	    <c:if test="${empty pm.datas}">
	    <tr>
	    	<td colspan="12" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </c:if>
      </tr>

    </table>
      <tr>
      <td></td>
      <td width="%20%" align="right" class="STYLE2" style="FONT-SIZE: 12pt">
      	                  <!-- 可以在这里插入分页导航条 -->
	<pg:pager url="total/browe.action" items="${pm.total}" maxPageItems="${ps}" export="currentPageNumber=pageNumber">
		<pg:param name="total.itemname" value="${total.itemname}"/>
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
	</td>
      </tr>
</table>
</form>  
  </body>
</html>
