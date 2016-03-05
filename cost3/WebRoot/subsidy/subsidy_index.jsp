<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
		<link rel="stylesheet" type="text/css" href="<%=basePath%>css/text.css"/>
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
				
				.STYLE3 {
					font-size: 12px;
				}
			-->
		</style>
		<script language="javascript" src="<%=basePath%>js/public.js"></script>
		<script language="javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script language="javascript" src="<%=basePath%>js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
			/**
			 * 页面内的脚本处理
			 *
			 * @Author FXStudio.com.cn(Ajaxfan)
			 */
      		$(document).ready(function(){// exportYear
      			/** 按年导出工资变化报表 */
      			$('input[name="Submit22"]').click(function(){
      				exportFunc('<%=basePath%>subsidy/excelAllChdange.action');
      			});
      			
      			/** 按年导出工资报表 */
      			$('input[name="Submit23"]').click(function(){
      				exportFunc('<%=basePath%>subsidy/excelCompanySalary.action');
      			});
      			
      			/** 导出方法  */
      			var exportFunc = function(url){
      				window.location.href = url + '?subsidy.exportyear=' + $('#exportYear').val();
      			}
      		});
      		
			function deleteInfo(v){
				if (confirm("确认删除？")){
			        location.href='<%=basePath%>subsidy/delete.action?subsidy.deleteid='+v;
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
	                deptform.action='<%=basePath%>subsidy/delete.action?subsidy.deleteid='+v;
	                deptform.submit();
	                return true;
	            }else{
	              return false;
	            } 
			}

	    function addInput(){
	       document.deptform.action='<%=basePath%>subsidy/addInput.action';
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
	            deptform.action='<%=basePath%>subsidy/updateInput.action?subsidy.id='+v;
	            deptform.submit();
	         } 
	         
	         function onQuery(){
	           deptform.action="<%=basePath%>subsidy/browe.action";
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
				工资管理
			</div>
		</div>
		<!--地址栏结束-->
		<div class="ss">
			<form name='deptform' action="<%=basePath%>subsidy/browe.action" method='post'>
				<table border="0" align="center" cellpadding="5" cellspacing="0"
					class="table">
					<tr>
						<td width="15%" align="right">
							<strong>按人员姓名搜索：</strong>
						</td>
						<td align="left" colspan="2">
							<input type="text" id='usernameid' name="subsidy.username" value="${subsidy.username}"/>
							<input type="submit" name="Submit" value="搜索" class="ssan" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<strong>报表导出：</strong>
						</td>
						<td>
							<input type="text" id='exportYear'
								readonly="readonly"
								style="width:75px;font-size:12pt;"
								value='<%=new java.text.SimpleDateFormat("yyyy").format(java.util.Calendar.getInstance().getTime())%>'
								onfocus="new WdatePicker(this, '%Y', false, 'whyGreen')" />
							<b style="color:gray;font-size:8pt;">* 选择要导出的年</b>
						</td>
						<td align="center">
							<input type="button" name="Submit22" value="按年导出薪资变动情况" />
							<input type="button" name="Submit23" value="按年导出所有的薪资信息" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="table">
			<table width="100%" border="0" cellpadding="3" cellspacing="0">
				<tr>
					<td colspan="11" align="left" align="center">
						<input type="submit" name="Submit252" value=" "
							onclick="javascript:location.href='<%=basePath%>subsidy/addInput.action'"
							class="tan1" />
					</td>
				</tr>

				<tr class="tr">
					<td width="50px" align="center">
						序号
					</td>
					<td width="10%" align="center">
						人员姓名
					</td>
					<td align="center">
						基本工资
					</td>
					<td align="center">
						补贴1
					</td>
					<td align="center">
						补贴2
					</td>
					<td align="center">
						补贴3
					</td>
					<td width="200px" align="center">
						操作
					</td>
				</tr>
				<tr>
					<!-- 列表数据栏 -->

					<c:if test="${!empty pm.datas}">
						<c:forEach items="${pm.datas }" var="subsidy" varStatus="status">
							<tr
						<c:choose >
						   	<c:when test="${status.index%2==0}"></c:when>
						   	<c:otherwise >class="tr2"</c:otherwise>
					   	</c:choose>>
								<td align="center" align="center">
									${status.index+1 }
								</td>
								<td align="center" align="center">
									${subsidy.user.username }
								</td>
								<td align="center" align="center">
									${empty subsidy.subsidyhistory.basepay ? '0' : subsidy.subsidyhistory.basepay}
								</td>
								<td align="center" align="center">
									${empty subsidy.subsidyhistory.subsidy1 ? '0' : subsidy.subsidyhistory.subsidy1}
								</td>
								<td align="center" align="center">
									${empty subsidy.subsidyhistory.subsidy2 ? '0' : subsidy.subsidyhistory.subsidy2}
								</td>
								<td align="center" align="center">
									${empty subsidy.subsidyhistory.subsidy3 ? '0' : subsidy.subsidyhistory.subsidy3}
								</td>
								<td align="center" align="center">
									<input type="submit" name="Submit222" value=" " onclick="deleteInfo('${subsidy.id}')" class="tan2" />
									<input type="submit" name="Submit232" value=" "
										onclick="javascript:location.href='<%=basePath%>subsidy/updateInput.action?subsidy.id=${subsidy.id}'"
										class="tan3" />
									<span>|</span>
									<input type="button" 
										   name="Submit2" 
										   onclick="javascript:location.href='<%=basePath%>subsidy/excelPerson.action?subsidy.id=${subsidy.id}'" 
										   value="薪资单" />
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<!-- 在列表数据为空的时候，要显示的提示信息 -->
					<c:if test="${empty pm.datas}">
						<tr>
							<td colspan="9" align="center"
								onmouseover="this.bgColor = '#DEE7FF';"
								onmouseout="this.bgColor='#EFF3F7';">
								没有找到相应的记录
							</td>
						</tr>
					</c:if>
				</tr>

			</table>
			<!-- 可以在这里插入分页导航条 -->
			<div class="sabrosus">
				<pg:pager url="subsidy/browe.action" items="${pm.total}"
					maxPageItems="${ps}" export="currentPageNumber=pageNumber">
					<pg:param name="subsidy.username" value="${subsidy.username}" />
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
								<span class="current">${pageNumber }</span>
							</c:when>
							<c:otherwise>
								<a href="${pageUrl }">${pageNumber }</a>
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
