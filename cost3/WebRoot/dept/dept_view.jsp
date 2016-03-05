<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>"/>

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
		<meta http-equiv="description" content="This is my page"/>
		<link href="<%=basePath%>css/index.css?v=1" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/text.css"/>

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
			-->
		</style>
		<script language="javascript" src="<%=basePath%>js/public.js"></script>
		<script type="text/javascript">
			function deleteInfo(v, c){
				if (confirm("您确认删除吗？")){
					location.href='<%=basePath%>dept/deleteDept.action?dept.deleteid='+v + '&dept.compid=' + c;
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
	                deptform.action='<%=basePath%>dept/delete.action?dept.deleteid='+v;
	                deptform.submit();
	                return true;
	            }else{
	              return false;
	            } 
			}

    function addInput(){
       document.deptform.action='<%=basePath%>dept/deptAdd.action';
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
              alert('请选择一条数据进行修改');
              return false;
            }
            
            deptform.action='<%=basePath%>dept/updateInput.action?dept.id='+v;
            deptform.submit();
         }
         
         function onQuery(){
              deptform.action="<%=basePath%>dept/browe.action";
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
				组织管理(${dept.compname})
			</div>
		</div>
		<!--地址栏结束-->
		<div class="ss">
			<form name='deptform' action="<%=basePath%>dept/browe.action"
				method='post'>
				<table border="0" align="center" cellpadding="5" cellspacing="0"
					class="table">
					<tr>
						<td width="29%" align="right">
							组织名称：
						</td>
						<td width="21%">
							<input type='text' name='dept.departmentname'
								value="${dept.departmentname }" />
						</td>
						<td width="12%" align="right">
							组织编码：
						</td>
						<td width="38%">
							<input type='text' name='dept.departmentcode'
								value="${dept.departmentcode}" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="submit" name="Submit3" value="查看" class="ssan" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="table">
			<table width="100%" border="0" align="center" cellpadding="3"
				cellspacing="0" style="text-align: center">
				<tr>
					<td colspan="5" align="left">
						<input type="submit" name="Submit252" value=" "
							onclick="javascript:location.href='<%=basePath%>dept/deptAdd.action?dept.id=' + ${dept.id}"
							class="tan1" />
							<span>|</span>
							<input type="button"  value=" " 
							onclick="javascript:location.href='<%=basePath%>dept/browe.action'"
							class="tan6"/> 
					</td>
				</tr>
				<tr class="tr">
					<td width="50px" align="center">
						序号
					</td>
					<td align="center">
						组织名称
					</td>
					<td width="43%" align="center">
						组织编号
					</td>
					<td width="200px" align="center">
						操作
					</td>
				</tr>
				<tr>
					<!-- 列表数据栏 -->

					<c:if test="${!empty pm.datas}">
						<c:forEach items="${pm.datas }" var="org" varStatus="stauts">
							<tr
								<c:choose >
			   <c:when test="${stauts.index%2==0}"></c:when>
			   <c:otherwise >class="tr2"</c:otherwise>
			 </c:choose>>
								<td align="center" vAlign="center">
									${stauts.index+1}
								</td>
								<td align="center" vAlign="center">
									${org.departmentname }
								</td>
								<td align="center" vAlign="center">
									${org.departmentcode }
								</td>
								<td align="center" vAlign="center">
									<input type="button" onclick="deleteInfo('${org.id}', '${dept.id}')"
										name="Submit222" value=" " class="tan2" />
									<input type="button"
										onclick="javascript:location.href='<%=basePath%>dept/deptupdateInput.action?dept.id=${org.id}&dept.compid=${dept.id}'"
										name="Submit232" value=" " class="tan3" />
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<!-- 在列表数据为空的时候，要显示的提示信息 -->
					<c:if test="${empty pm.datas}">
						<tr>
							<td colspan="7" align="center" bgcolor="#EFF3F7"
								class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';"
								onmouseout="this.bgColor='#EFF3F7';">
								没有找到相应的记录
							</td>
						</tr>
					</c:if>
			</table>
			<div class="sabrosus">
				<pg:pager url="dept/browe.action" maxPageItems="${ps}"
					items="${pm.total}" export="currentPageNumber=pageNumber">
					<pg:param name="dept.departmentname" value="${dept.departmentname}" />
					<pg:param name="dept.departmentcode" value="${dept.departmentcode}" />
					<pg:first>
						<a href="${pageUrl}" id="firstpageurl" class="n2"><font
							class="STYLE2">首页</font>
						</a>
					</pg:first>
					<pg:prev>
						<a href="${pageUrl }" class="n2"><font class="STYLE2">前页</font>
						</a>
					</pg:prev>
					<pg:pages>
						<c:choose>
							<c:when test="${currentPageNumber eq pageNumber}">
								<span class="current">${pageNumber }</span>
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
		</div>
	</body>
</html>
