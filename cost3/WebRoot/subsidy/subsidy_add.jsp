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
      			/**
      			 * 对于可编辑文本框的处理
      			 */
			  	$(':text:not([readonly="readonly"])').each(function(i){
			  		/**
			  		 * 对于文本框的录入操作，这里限制了输入的内容只能是数字和小数点
			  		 */
				   	$(this).keydown(function(){
				   		// 未选择用户前不可编辑工资信息
				   		if($('#username').val().length === 0){
				   			return false;
				   		}
				   	
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
				   	
				   	/**
				   	 * 选择文本框操作
				   	 */
				   	$(this).click(function(){
				   		if($(this).val() === '0'){
				   			$(this).val('');
				   		}
				   		$(this).select(); 
			   		});
			   		
			   		/**
					 * 焦点离开文本框
					 */
			   		$(this).blur(function(){
			   			if($(this).val() === ''){ $(this).val('0'); return; }
			   			
			   			// 加载动态计算公式
		   				var formula = $('#formula').val();
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
				$('#newRecord').click(function(){
					// 判断人员信息是否完整
					if($('#username').val().length === 0){
						alert('人员信息不能为空');
					}
					// 判断基本工资信息是否有效
					else if($('#basepay').val() === '0') {
						alert('基本工资应为大于0的有效数字');			
					}
					// 如果没有问题则可以提交表单
					else{
						// 提交表单
						$('form[name="deptform"]').submit();
					}
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
				工资添加
			</div>
		</div>
		<div class="table" style="margin-top: 15px;">
			<form name='deptform' action="<%=basePath%>subsidy/add.action" method='post'>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td width="8%" height="23" align="center">
							人员名称
						</td>
						<td width="27%" align="left" class="STYLE2 STYLE3">
							<input type="hidden" id='userid' name="subsidy.user.id"/>
							<input type="text" id='username' disabled="disabled" name="subsidy.user.username"/>
							<input type="hidden" id='census' name="subsidy.user.census"/>
							<span><font color='red'>*</font> </span>
							<input type="button" value="选择人员"
								onclick="openWin('<%=basePath%>user/selectBrowe.action','selectOrgs',800,500,1);"/>
						</td>
						<td width="8%" align="center">
							基本工资
						</td>
						<td width="43%" align="left">
							<input type="text" id='basepay' name="subsidy.subsidyhistory.basepay" value="0"/>
							<span><font color='red'>*</font> </span>
						</td>
					</tr>
					<tr>
						<td width="8%" height="23" align="center">
							补贴一
						</td>
						<td width="27%" align="left">
							<input type="text" id='subsidy1' name="subsidy.subsidyhistory.subsidy1"
								value="0"/>
						</td>
						<td width="8%" align="center">
							补贴二
						</td>
						<td width="43%" align="left">
							<input type="text" id='subsidy2' name="subsidy.subsidyhistory.subsidy2"
								value="0"/>
						</td>
					</tr>
					<tr>
						<td width="8%" height="23" align="center">
							补贴三
						</td>
						<td width="27%" align="left">
							<input type="text" id='subsidy3' name="subsidy.subsidyhistory.subsidy3" value="0"/>
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
								<input type="text" id='overtimepay' name="subsidy.subsidyhistory.overtimepay" readonly="readonly"/>
							</span>
							<span style="background-color:lightGray;display:inline-block;width:1px;">&nbsp;</span>
							<span>
								时薪
								<input type="text" id='hourspaly' name="subsidy.subsidyhistory.hourspaly" readonly="readonly"/>
							</span>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="hidden" id="formula"/>
							<input id="newRecord" type="button" name="button" value="添加"/>
							<input type="reset" name="Submit3" value="重置" />
							<input type="button" name="button" value="返回" onclick="javascript:window.history.back(-1);" />
						</td>
					</tr>
					<tr>
						<td colspan="4" style="color:red;">
							*&nbsp;1. 工资标准，将从自次月1日起生效, &nbsp;&nbsp;2. 没有选择人员前工资信息不可被编辑
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
