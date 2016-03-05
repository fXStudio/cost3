<%@ page language="java" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
		<link rel="<%=basePath%>css/text.css" type="text/css" href="styles.css"/>
		<link href="<%=basePath%>css/index.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
<!--
.table tr:hover{background: white;}
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
.showOnly {
	height: 25px;
	background-color: #fddeac;
}
.table tr.showOnly:hover{
 background-color: #fddeac;
}
.showOnly Input{
 	background-color: #ecf6dd;
}
.showOnly span{
	margin: 8px;
}
.showOnly b{color: red;}
.pdes b{color: blue;padding:2px;font-size:12pt;}
-->
</style>
		<script language="javascript" src="<%=basePath%>js/public.js"></script>
		<script language="javascript" src="<%=basePath%>js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript">
      		$(document).ready(function(){
			  	$(':text:not([readonly="readonly""]):not([disabled="disabled"])').each(function(i){
			  		$(this).data($(this).attr("name"), $(this).val());
			  	
			  		// 对于文本框的录入操作，这里限制了输入的内容只能是数字和小数点
				   	$(this).keydown(function(){
				   		var e = $(this).event || window.event;
					    var code = parseInt(e.keyCode);
					    if (code >= 96 && code <= 105 || 
					        code >= 48 && code <= 57 || 
					        code >= 37 && code <= 40 ||
					        code === 8 || code === 9) {  
					        return true;  
					    } else {  
					        return false;  
					    }
				   	});
				   	
				   	// 选择文本框操作
				   	$(this).click(function(){
				   		if($(this).val() === '0'){
				   			$(this).val('');
				   		}
				   		$(this).select(); 
			   		});
			   		
			   		// 焦点离开文本框
			   		$(this).blur(function(){
			   			if($(this).val() === ''){ $(this).val('0'); return; }
			   			
			   			// 加载动态计算公式
		   				var formula = '${subsidy.user.nat.welfare.wformula}';
		   				// 基本工资
		   				var basepay = $('#basepay').val();
		   				
		   				// 计算补贴信息
		   				var subsidies = 0;
		      			$("input[id^='subsidy']").each(function(){
		      				subsidies += parseInt($(this).val());
		      			});
		      			// 替换公式里面的占位符,这样可以保证公式和业务数据解耦
		      			formula = formula.replace(/@{basepay}/ig, basepay);
		      			formula = formula.replace(/@{Subsidies}/ig, subsidies);
		      			
		      			// TODO 加班时薪，目前公式写死在了这里，以后需要将公式移入数据库
		      			$('#overtimepay').val(toDecimal(basepay / 21.75 / 8 * 1.5));
		      			// 时薪计算，根据员工的不同籍贯有不同的计算方法，这部分由数据库映射而来
		      			$('#hourspaly').val(toDecimal(eval(formula)));
			   		});
				});
				
				/**
				 * 添加新记录
				 */
				$('#updateRecord').click(function(){
					// 获得组件集合
					var inputs = $(':text:not([readonly="readonly"]):not([disabled="disabled"])');
					
					// 遍历组件集合
					for(var i = 0; i < inputs.length; i++){
						// 获取集合中的一个对象
						var inp = $('#' + inputs.get(i).id);
						
						// 判断数据是否有发生变更
						if(inp.data(inp.attr("name")) != inp.val()){
							// 判断基本工资信息是否有效
							if($('#basepay').val() === '0') {
								alert('基本工资应为大于0的有效数字');			
							}
							// 如果没有问题则可以提交表单
							$('form[name="deptform"]').submit(); return;
						}
					}
					window.location.href = "<%=basePath%>subsidy/browe.action";
				});
				
				/**
				 * 只保留小数点后两位
				 */
				function toDecimal(x) {    
		            var f = parseFloat(x);    
		            if (isNaN(f)) {    
		                return;    
		            }    
		            return Math.round(x*100)/100;
		        }
			});
		</script>
	</head>
	<body bgcolor="#f1f1f1">
		<div class="address">
			<div class="bt">
				工资修改
			</div>
		</div>
		<div class="table" style="margin-top: 15px;">
			<form name='deptform' action="<%=basePath%>subsidy/update.action" method='post'>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td width="8%" height="23" align="center">
							人员名称
						</td>
						<td width="27%" align="left">
							<input type="hidden" id='census' name="subsidy.user.census" value="${subsidy.user.census }"/>
							<input type="hidden" id='userid' name="subsidy.user.id" value="${subsidy.user.id }"/>
							<input type="hidden" id='id' name="subsidy.id" value="${subsidy.id }"/>
							<input type="text" id='username' disabled="disabled" name="subsidy.user.username" value="${subsidy.user.username}"/>
						</td>
						<td width="8%" align="center">
							基本工资
						</td>
						<td width="43%" align="left">
							<input type="text" id='basepay' name="subsidy.subsidyhistory.basepay"
								value="${empty subsidy.subsidyhistory.basepay ? '0' : subsidy.subsidyhistory.basepay}"/>
							<span><font color='red'>*</font> </span>
						</td>
					</tr>
					<tr>
						<td width="8%" height="23" align="center">
							补贴一
						</td>
						<td width="27%" align="left">
							<input type="text" id='subsidy1' name="subsidy.subsidyhistory.subsidy1"
								value="${empty subsidy.subsidyhistory.subsidy1 ? '0' : subsidy.subsidyhistory.subsidy1}"/>
						</td>
						<td width="8%" align="center">
							补贴二
						</td>
						<td width="43%" align="left">
							<input type="text" id='subsidy2' name="subsidy.subsidyhistory.subsidy2"
								value="${empty subsidy.subsidyhistory.subsidy2 ? '0' : subsidy.subsidyhistory.subsidy2}"/>
						</td>
					</tr>
					<tr>
						<td width="8%" height="23" align="center">
							补贴三
						</td>
						<td width="27%" align="left">
							<input type="text" id='subsidy3' name="subsidy.subsidyhistory.subsidy3"
								value="${empty subsidy.subsidyhistory.subsidy3 ? '0' : subsidy.subsidyhistory.subsidy3}"/>
						</td>
						<td width="8%" align="center"></td>
						<td width="43%" align="left">
						</td>
					</tr>
					<tr class="showOnly">
						<td colspan="4">
							<span style="color:Blue;">* 以下数据通过[<b>基本薪资</b>]和[<b>补贴</b>]计算而得</span>
							<span style="background-color:lightGray;display:inline-block;width:1px;">&nbsp;</span>
							<span>
								加班时薪
								<input type="text" id='overtimepay' name="subsidy.subsidyhistory.overtimepay"
								value="${empty subsidy.subsidyhistory.overtimepay ? '0' : subsidy.subsidyhistory.overtimepay}"
								readonly="readonly"/>
							</span>
							<span style="background-color:lightGray;display:inline-block;width:1px;">&nbsp;</span>
							<span>
								时薪
								<input type="text" id='hourspaly' name="subsidy.subsidyhistory.hourspaly"
								value="${empty subsidy.subsidyhistory.hourspaly ? '0' : subsidy.subsidyhistory.hourspaly}"
								readonly="readonly"/>
							</span>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="button" id="updateRecord" value="保存"/>
							<input type="reset" name="Submit3" value="重置" />
							<input type="button" name="button" value="返回" onclick="javascript:window.history.back(-1);" />
						</td>
					</tr>
					<tr>
						<td colspan="4" style="color:red;">
							* 新的工资标准，将自次月1日起生效
						</td>
					</tr>
				</table>
				<hr style="border:dashed 1px;"/>
				<div style="text-indent:1em" class="pdes">
					<p><h1>@&nbsp;员工情况概述</h1></p>
					<p style="padding-left: 40px;">
						<b>${subsidy.user.username}</b>于<b>${subsidy.user.jobdate}</b>
						入职公司，现就职于我公司<b>${subsidy.user.dept.departmentname}</b>。
						现职务为<b>${subsidy.user.post.postname}</b>。岗位级别为<b>${subsidy.user.worklevel}</b>。
						现处于<b>${subsidy.user.employtype}</b>状态。籍贯为<b>${subsidy.user.nat.nativename}</b>。
					</p>
				</div>
			</form>
		</div>
	</body>
</html>
