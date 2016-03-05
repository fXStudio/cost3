<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />

		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link href="<%=basePath%>css/index.css?v=1" rel="stylesheet"
			type="text/css" />
		<link href="<%=basePath%>css/table.css" rel="stylesheet"
			type="text/css" />
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>css/text.css" />
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

.STYLE4 {
	font-size: 24px;
	font-weight: bold;
}
-->
</style>
		<script language="javascript" src="<%=basePath%>js/public.js"></script>
		<script type="text/javascript">
function deleteUser(id){
	if(!confirm('您确认删除信息吗？'))return false;
	location.href='<%=basePath%>user/delete.action?user.deleteid='+id;
}
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
                deptform.action='<%=basePath%>user/delete.action?user.deleteid='+v;
                deptform.submit();
                return true;
            }else{
              return false;
            } 
}

    function addInput(){
       document.deptform.action='<%=basePath%>user/addInput.action';
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
         
           deptform.action='<%=basePath%>user/outExcel.action';
           alert(deptform.action);
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
         function excelUser(){
           deptform.action='<%=basePath%>user/excelUser.action';
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
		<div class="address">
			<div class="bt">
				人员管理
			</div>
		</div>
		<!--地址栏结束-->
		<div class="ss">
			<form name='deptform1' action="user/browe.action" method='post'>
				<table border="0" align="center" cellpadding="5" cellspacing="0"
					class="table">
					<tr>
						<td colspan="4" align="left">
							<input type="button" name="Submit252" value=" "
								onclick="javascript:location.href='<%=basePath%>user/addInput.action'"
								class="tan1a" />
						</td>
					</tr>
					<tr>
						<td width="29%" align="right">
							人员姓名：
						</td>
						<td width="21%">
							<input type="text" name='user.username' value="${user.username }" />
						</td>
						<td width="12%" align="right">
							简码：
						</td>
						<td width="38%">
							<input type="text" name='user.usercode' value="${user.usercode}" />
						</td>
					</tr>
					<tr>
						<td align="right">
							手机号码：
						</td>
						<td>
							<input type="text" name="user.tel" value="${user.tel}" />
						</td>
						<td align="right">
							所属机构 ：
						</td>
						<td>
							<input type="text" name="user.dept.departmentname"
								id='deptNameId' readonly style="background: #CCCCCC"
								value="${user.dept.departmentname}" />
							<input type="hidden" name="user.dept.id" id="deptIdId"
								value="${user.dept.id}" />
							<input type="image"
								src="<%=basePath%>images/xuanzen.jpg"
								onclick="openWin('<%=basePath%>dept/selectBrowe.action','selectOrgs',500,400,1);" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" name="Submit" value="搜索" class="ssan" />
							<input type="reset" name="reset" value="重置" class="ssan" />
						</td>
					</tr>
				</table>
			</form>
		</div>

		<div class="table">
			<table width="100%" border="0" cellpadding="3" cellspacing="0">
				<tr class="tr">
					<td width="4%" align="center">
						序号
					</td>
					<td width="7%" align="center">
						人员姓名
					</td>
					<td width="8%" align="center">
						人员简码
					</td>
					<td width="7%" align="center">
						人员编码
					</td>
					<td width="4%" align="center">
						性别
					</td>
					<td width="12%" align="center">
						手机号码
					</td>
					<td width="14%" align="center">
						邮箱
					</td>
					<td width="10%" align="center">
						所属机构
					</td>
					<td width="6%" align="center">
						级别
					</td>
					<td width="8%" align="center">
						入职时间
					</td>
					<td width="20%" align="center">
						操作
					</td>
				</tr>
				<c:forEach items="${pm.datas }" var="user" varStatus="status">
					<tr
						<c:choose >
		   <c:when test="${status.index%2==0}"></c:when>
		   <c:otherwise >class="tr2"</c:otherwise>
		 </c:choose>>
						<td align="center" align="center">
							${status.index+1 }
						</td>
						<td align="center" align="center">
							${user.username }
						</td>
						<td align="center" align="center">
							${user.easycode }
						</td>
						<td align="center" align="center">
							${user.usercode }
						</td>
						<td align="center" align="center">
							<c:if test="${user.sex eq 'man'}"> 男
         </c:if>
							<c:if test="${user.sex eq 'gril'}"> 女 
         </c:if>
						</td>
						<td align="center" align="center">
							${user.tel}
						</td>
						<td align="center" align="center">
							${user.emial}
						</td>
						<td align="center" align="center">
							${user.departmentName}
						</td>
						<td align="center" align="center">
							${user.worklevel}
						</td>
						<td align="center" align="center">
							${user.jobdate}
						</td>
						<td align="center" align="center">
							<input type="button" name="Submit222" value=" "
								onclick="deleteUser('${user.id }')" class="tan2" />
							<input type="button" name="Submit232" value=" "
								onclick="javascript:location.href='<%=basePath%>user/updateInput.action?user.id=${user.id }'"
								class="tan3" />
							<input type="button" name="Submit242" value=" "
								onclick="javascript:location.href='<%=basePath%>user/look.action?user.id=${user.id }'"
								class="tan4" />
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="sabrosus">
			<pg:pager url="user/browe.action" items="${pm.total}"
				maxPageItems="${ps}" export="currentPageNumber=pageNumber">
				<pg:param name="user.username" value="${user.username}" />
				<pg:param name="user.usercode" value="${user.usercode}" />
				<pg:param name="user.tel" value="${user.tel}" />
				<pg:first>
					<a href="${pageUrl}" id="firstpageurl" class="n2"><font
						class="STYLE2">首页</font> </a>
				</pg:first>
				<pg:prev>
					<a href="${pageUrl }"><font class="STYLE2" class="n2">前页</font>
					</a>
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
					<a href="${pageUrl }" class="n2"><font class="STYLE2">后页</font>
					</a>
				</pg:next>
				<pg:last>
					<a href="${pageUrl }" class="n2"><font class="STYLE2">尾页</font>
					</a>
				</pg:last>
			</pg:pager>
		</div>
	</body>
</html>
