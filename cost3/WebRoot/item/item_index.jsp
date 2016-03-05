<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"  uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/text.css">
	<link href="css/index.css" rel="stylesheet" type="text/css" />
	<link href="css/table.css" rel="stylesheet" type="text/css" />
	
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
function deleteInfo(v){
	if (confirm("确认删除？")){
        location.href='<%=basePath%>item/delete.action?item.deleteid='+v;
        return true;
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
                deptform.action='<%=basePath%>item/delete.action?item.deleteid='+v;
                deptform.submit();
                return true;
            }else{
              return false;
            } 
}

    function addItemUser(){
            var v=[];
            var dept = document.getElementsByName('deptradio');
            for(var i=0;i<dept.length;i++){
                if(dept[i].checked){
                  v.push(dept[i].value);
                }
            }
            if(v.length==0){
              alert('请先选择项目在添加人员');
              return false;
            }else if(v.length>1){
              alert('请先选择一条项目信息');
              return false;
            }
            
            openWin('<%= basePath%>user/itemUserSelectBrowe.action?itemuser.itemid='+v,'selectOrgs',800,600,0);
    }
    
    function addInput(){
            deptform.action='<%=basePath%>item/addInput.action';
            deptform.submit();
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
              alert('请先选择一条项目信息修改');
              return false;
            }else if(v.length>1){
              alert("请先选择一条项目信息修改");
              return false;
            }
            deptform.action='<%=basePath%>item/updateInput.action?item.id='+v;
            deptform.submit();
         }
         
         function queryItemUser(){
            var v=[];
            var dept = document.getElementsByName('deptradio');
            for(var i=0;i<dept.length;i++){
                if(dept[i].checked){
                  v.push(dept[i].value);
                }
            }
            if(v.length==0){
              alert('请先选择一条项目信息进行查看');
              return false;
            }else if(v.length>1){
              alert("请先选择一条项目信息进行查看");
              return false;
            }
             openWin('<%=basePath%>user/broweItemUser.action?itemuser.itemid='+v,'selectOrgs',1000,600,0);
         } 
         
         function onQuery(){
           
           deptform.action="<%=basePath%>item/browe.action";
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
         
         function deleteItemUser(){
         
           var v=[];
            var dept = document.getElementsByName('deptradio');
            for(var i=0;i<dept.length;i++){
                if(dept[i].checked){
                  v.push(dept[i].value);
                }
            }
            if(v.length==0){
              alert('请先选择一条项目信息进行删除人员');
              return false;
            }else if(v.length>1){
              alert("请先选择一条项目信息进行删除人员");
              return false;
            }
             openWin('<%=basePath%>user/broweItemUser!broweDeleteItemUser.action?itemuser.itemid='+v,'selectOrgs',800,500,0);
         }
</script>
  </head>
  <body>
<div class="address">
	<div class="bt">项目管理</div>
</div>
<!--地址栏结束-->
<div class="ss">
<form name='deptform'action="<%=basePath%>item/browe.action" method='post'>
	  <table border="0" align="center" cellpadding="5" cellspacing="0" class="table" >
        <tr>
          <td width="29%" align="right">项目名称：</td>
          <td width="21%"><input type='text' name='item.itemname' value="${item.itemname }"/></td>
          <td width="12%" align="right">项目编码：</td>
          <td width="38%"><input type='text' name='item.itemcode' value="${item.itemcode}"/></td>
        </tr>
        <tr>
          <td colspan="4" align="center"><input type="submit" name="Submit" value="查看" class="ssan" />　            </td>
        </tr>
      </table>
</form>
</div>
<div class="table">
<table width="100%" border="0" align="center" cellpadding="3" cellspacing="0" style="text-align:center">
	<tr>
      <td colspan="8" align="left">
      <input type="submit" name="Submit252" value=" " onclick="javascript:location.href('<%=basePath%>item/addInput.action')"  class="tan1"/>
      
      <input type="image" src="<%=basePath%>images/tjry.jpg" onclick="addItemUser()" class="STYLE3"/>
      <input type="image" src="<%=basePath%>images/ckry.jpg" onclick="queryItemUser()" class="STYLE3"/>
      <input type="image" src="<%=basePath%>images/scry.jpg" onclick="deleteItemUser()" class="STYLE3"/>
      </td>
    </tr>
    <tr class="tr">
        <td width="13%" align="center"  >选择
        <input type="checkbox" name="deptradios" onclick="selectAll()"></td>
        <td width="50px" align="center"  >序号</td>
        <td align="center"  >项目名称</td>
        <td width="13%" align="center"  >项目编号</td>
        <td width="13%" align="center"  >开始时间</td>
        <td width="13%" align="center"  >结束时间</td>
        <td width="13%" align="center"  >活动状态</td>
        <td width="200px" align="center"  >操作</td>
      </tr>
      <tr>
      <!-- 列表数据栏 -->
          
          <c:if test="${!empty pm.datas}">
          <c:forEach items="${pm.datas }" var="item" varStatus="status">
	      <tr <c:choose >
			   <c:when test="${status.index%2==0}"></c:when>
			   <c:otherwise >class="tr2"</c:otherwise>
			 </c:choose>>
	          <td align="center" vAlign="center">
	          <input type="checkbox" name="deptradio" value="${item.id} "></td>
		      <td align="center" vAlign="center" class="STYLE3">${status.index+1 }</td>
	          <td align="center" vAlign="center" class="STYLE3">${item.itemname }</td>
	          <td align="center" vAlign="center" class="STYLE3">${item.itemcode }</td>
	          <td align="center" vAlign="center" class="STYLE3">${item.starttime }</td>
	          <td align="center" vAlign="center" class="STYLE3">${item.endtime }</td>
	          <td align="center" vAlign="center" class="STYLE3">
	          <c:if test="${item.itemstate eq 'activity'}">
	                进行中
	          </c:if>
	         <c:if test="${item.itemstate eq 'end'}">
	                结束
	          </c:if>
	          <c:if test="${item.itemstate eq 'stop'}">
	                暂停
	          </c:if>
	          </td>
	          <td><input type="submit" name="Submit22212" value=" " onclick="deleteInfo('${item.id}')"  class="tan2"/>
		        <input type="submit" name="Submit23212" value=" "  onclick="javascript:location.href('<%=basePath%>item/updateInput.action?item.id=${item.id}')"class="tan3" />
		        <a href='<%=basePath%>item/itemReport_showReport.action?itemId=${item.id}'>报表</a>
		      </td>
	          
        </tr>
        </c:forEach>
		</c:if>
        <!-- 在列表数据为空的时候，要显示的提示信息 -->
	    <c:if test="${empty pm.datas}">
	    <tr>
	    	<td colspan="7" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </c:if>
      </tr>
    </table>
</div>
    <div class="sabrosus">
    <pg:pager url="item/browe.action" items="${pm.total}" maxPageItems="${ps}" export="currentPageNumber=pageNumber">
		<pg:param name="item.itemname" value="${item.itemname}"/>
		<pg:param name="item.itemcode" value="${item.itemcode}"/>
		<pg:first>
			<a href="${pageUrl}" id="firstpageurl" class="n2"><font class="STYLE2">首页</font></a>
		</pg:first>
		<pg:prev>
		<a href="${pageUrl }" class="n2"><font class="STYLE2">前页</font></a>
		</pg:prev>
		<pg:pages>
			<c:choose>
				<c:when test="${currentPageNumber eq pageNumber}">
					<span class="current">${pageNumber }</span>
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
	</div>
  </body>
</html>
