<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"  uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
	<link rel="<%=basePath%>css/text.css" type="text/css" href="styles.css">
	<link href="<%=basePath%>css/index.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/table.css" rel="stylesheet" type="text/css" />
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
	<script language="javascript" type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
      function checkSubmit(){
    	  if(!confirm('您确定要更新吗？')){
    		  return;
    	  }
         var itemname= document.getElementById("itemname");
         var itemcode= document.getElementById("itemcode");
         var contract_money= document.getElementById("contract_money");
         var start_date= document.getElementById("start_date");
         var itemstate= document.getElementById("itemstate");
         var vo_money= document.getElementById("vo_money");
         var vo_date= document.getElementById("vo_date");
        
         if(Trim(itemname.value)==""){
            alert('项目名称不能为空');
            return false;
         }
        if(Trim(itemcode.value)==""){
            alert('项目编码不能为空');
            return false;
         }
         if(Trim(contract_money.value)==""){
            alert('合同总金额不能为空');
            return false;
         }
         if(Trim(start_date.value)==""){
            alert('启动日期不能为空');
            return false;
         }
<%--         if(Trim(vo_money.value)==""){--%>
<%--            alert('VO金额不能为空');--%>
<%--            return false;--%>
<%--         }--%>
<%--         if(Trim(vo_date.value)==""){--%>
<%--            alert('VO日期不能为空');--%>
<%--            return false;--%>
<%--         }--%>
         
         deptform.submit();
      }
      function deleteItemReport(){
    	  if(confirm('您确定删除本记录吗？')){
    		  location.href='itemReport_delete.action?id=<s:property value='itemReport.id'/>';
    	  }
      }
      function deleteItemReport2(id){
    	  if(confirm('您确定删除本记录吗？')){
    		  location.href='itemReport2_delete.action?id='+id+"&itemId=<s:property value='itemReport.itemId'/>";
    	  }
      }
      function deleteItemReport3(id){
    	  if(confirm('您确定删除本记录吗？')){
    		  location.href='itemReport3_delete.action?id='+id+"&itemId=<s:property value='itemReport.itemId'/>";
    	  }
      }
</script>
  </head>
<body bgcolor="#f1f1f1">
<div class="address">
	<div class="bt"></div>	
</div>
<div class="table" style="margin-top:15px;">
<form name='deptform' action="<%= basePath%>item/itemReport_update.action" method='post' onsubmit="return false">
	<table width="100%" border="0" cellpadding="0" cellspacing="1">
      <tr>
        <td width="8%" align="center"   >项目编号</td>
        <td width="43%" align="left"   >
        <input type="text" id='itemcode' name="itemcode" value="<s:property value='itemReport.itemcode'/>" readonly><span><font color='red'>*</font></span>
        </td>
        <td width="8%" height="23" align="center"   >项目名称</td>
        <td width="27%" align="left"   >
        <input type="text" id='itemname' name="itemname" value="<s:property value='itemReport.itemname'/>" readonly><span><font color='red'>*</font></span>
        </td>
      </tr>
      
       <tr>
        <td width="8%" height="23" align="center"   >合同总金额</td>
        <td width="27%" align="left"   >
        <input type="text" id='contract_money' name="contractMoney" onkeyup="value=value.replace(/[^\d\.]/g,'')" value="<s:property value='itemReport.contractMoney'/>"><span><font color='red'>*</font></span>
        </td>
        <td width="8%" align="center"   >启动日期</td>
        <td width="43%" align="left"   >
        <input type="text" id='start_date' name="startDate" onfocus="new WdatePicker(this,null,false,'whyGreen')" value="<s:property value='itemReport.startDate'/>"><span><font color='red'>*</font></span>
        </td>
      </tr>
      
         <tr>
        <td width="8%" height="23" align="center"   >VO金额</td>
        <td width="27%" align="left"   >
        <input type="text" id='vo_money' name="voMoney" onkeyup="value=value.replace(/[^\d\.]/g,'')" value="<s:property value='itemReport.voMoney'/>">
        </td>
        <td width="8%" align="center"   >VO日期</td>
        <td width="43%" align="left"   >
        <input type="text" id='vo_date' name="voDate" onfocus="new WdatePicker(this,null,false,'whyGreen')" value="<s:property value='itemReport.voDate'/>">
        </td>
      </tr>
      
<%--      <tr>--%>
<%--        <td width="8%" height="23" align="center"   >项目工程师配置</td>--%>
<%--        <td width="27%" align="left"   >--%>
<%--        </td>--%>
<%--        <td width="8%" align="center"   ></td>--%>
<%--        <td width="43%" align="left"   >--%>
<%--        </td>--%>
<%--      </tr>--%>
      <tr>
          <td align="center" colspan="4">
          	<input type="button" name= "button"  value="更新" onClick="checkSubmit()"/>
          	<input type="button" name= "button"  value="删除" onClick="deleteItemReport()"/>
        	<input type="reset" name="Submit3" value="重置" />
          	<input type="button" name= "button"  value="返回" onClick="javascript:window.history.back(-1);"/>
          	<input type="hidden" id='itemId' name="itemId" value="<s:property value='itemReport.itemId'/>" >
          	<input type="hidden" id='itemId' name="id" value="<s:property value='itemReport.id'/>" >
          </td>
        </tr>
    </table>
</form>
</div>
<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name= "button"  value="添加" onClick="javascript:location.href='itemReport2_forwardAdd.action?itemId=<s:property value='itemReport.itemId'/>&reportId=<s:property value='itemReport.id'/>&contractMoney=<s:property value='itemReport.contractMoney'/>&voMoney=<s:property value='itemReport.voMoney'/>&voDate=<s:property value='itemReport.voDate'/>'"/>
<div class="table">
<table width="100%" border="0" cellpadding="3" cellspacing="0" >
      <tr class="tr">
<%--        <td width="4%" align="center" >序号</td>--%>
        <td width="4%" align="center" >预算</td>
        <td width="4%" align="center" >日期</td>
        <td width="4%" align="center" >人工成本</td>
        <td width="4%" align="center" >费用成本</td>
        <td width="4%" align="center" >税金</td>
        <td width="4%" align="center" >分包费</td>
        <td width="4%" align="center" >M</td>
        <td width="6%" align="center" >Contract Cost</td>
<%--        <td width="8%" align="center" >Revenue</td>--%>
<%--        <td width="8%" align="center" >Revenue调整</td>--%>
<%--        <td width="8%" align="center" >费用百分比</td>--%>
<%--        <td width="8%" align="center" >Revenue 百分比</td>--%>
        <td width="4%" align="center" >操作</td>
      </tr>
      <c:forEach items="${itemReport2List }" var="itemReport2" varStatus="status">
      <tr <c:choose >
			   <c:when test="${status.index%2==0}"></c:when>
			   <c:otherwise >class="tr2"</c:otherwise>
			 </c:choose>>
<%--        <td align="center" >${status.index+1 }</td>--%>
        <td align="center" class="STYLE3">
        <c:choose >
			   <c:when test="${empty  itemReport2.mingcheng ||itemReport2.mingcheng eq ''}">${status.index+1 }</c:when>
			   <c:otherwise >${itemReport2.mingcheng }</c:otherwise>
			 </c:choose>
        </td>
        <td align="center" class="STYLE3">${itemReport2.riqi }</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${empty  itemReport2.mingcheng ||itemReport2.mingcheng eq ''}">${itemReport2.rengongChengben }</c:when>
			   <c:otherwise >${itemReport2.rengongChengben }<input id="cgcbItem1" type="hidden" value="${itemReport2.rengongChengben }"/></c:otherwise>
			 </c:choose>
        </td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${empty  itemReport2.mingcheng ||itemReport2.mingcheng eq ''}">${itemReport2.feiyongChengben }</c:when>
			   <c:otherwise >${itemReport2.feiyongChengben }<input id="fycbItem1" type="hidden" value="${itemReport2.feiyongChengben }"/></c:otherwise>
			 </c:choose>
        </td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${empty  itemReport2.mingcheng ||itemReport2.mingcheng eq ''}">${itemReport2.shuijin }</c:when>
			   <c:otherwise >${itemReport2.shuijin }<input id="sjItem1" type="hidden" value="${itemReport2.shuijin }"/></c:otherwise>
			 </c:choose>
        </td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${empty  itemReport2.mingcheng ||itemReport2.mingcheng eq ''}">${itemReport2.fenbaofei }</c:when>
			   <c:otherwise >${itemReport2.fenbaofei }<input id="fbfItem1" type="hidden" value="${itemReport2.fenbaofei }"/></c:otherwise>
			 </c:choose>
        </td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${status.last}"></c:when>
			   <c:otherwise >${itemReport2.m }</c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">${itemReport2.contractCost}</td>
<%--        <td width="8%" align="center" class="STYLE3">${itemReport2.revenue }</td>--%>
<%--        <td width="8%" align="center" class="STYLE3">${itemReport2.revenueTiaozheng }</td>--%>
<%--        <td width="8%" align="center" class="STYLE3">${itemReport2.feiyongBaifenbi }</td>--%>
<%--        <td width="8%" align="center" class="STYLE3">${itemReport2.revenueBaifenbi }</td>--%>
        <td align="center" class="STYLE3">
        <c:if test="${status.index== fn:length(itemReport2List)-2}">
<%--        	<a href='item/itemReport2_show.action?id=${itemReport2.id }&contractMoney=<s:property value='itemReport.contractMoney'/>&voMoney=<s:property value='itemReport.voMoney'/>&voDate=<s:property value='itemReport.voDate'/>'>修改</a>--%>
        	<a href='javascript:deleteItemReport2("${itemReport2.id }")'>删除</a>
	    </c:if>
        </td>
      </tr>
      </c:forEach>
</table>
</div>
<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" name= "button"  value="添加" onClick="javascript:location.href='itemReport3_forwardAdd.action?itemId=<s:property value='itemReport.itemId'/>&reportId=<s:property value='itemReport.id'/>&itemReportId=<s:property value='itemReport.id'/>&contractMoney=<s:property value='itemReport.contractMoney'/>&voMoney=<s:property value='itemReport.voMoney'/>&voDate=<s:property value='itemReport.voDate'/>'"/>
<div class="table">
<table width="100%" border="0" cellpadding="3" cellspacing="0" >
      <tr class="tr">
<%--        <td width="4%" align="center" >序号</td>--%>
        <td width="4%" align="center" >项目</td>
        <td width="4%" align="center" >日期</td>
        <td width="4%" align="center" >人工<br/>成本</td>
        <td width="4%" align="center" >费用<br/>成本</td>
        <td width="4%" align="center" >税金</td>
        <td width="4%" align="center" >分包费</td>
        <td width="4%" align="center" >M</td>
        <td width="4%" align="center" >Contract<br/>Cost</td>
        <td width="4%" align="center" >Revenue</td>
        <td width="4%" align="center" >Revenue<br/>调整</td>
        <td width="4%" align="center" >费用<br/>百分比</td>
        <td width="4%" align="center" >Revenue<br/>百分比</td>
        <td width="4%" align="center" >Billed</td>
        <td width="4%" align="center" >Cash In</td>
        <td width="4%" align="center" >EUB</td>
        <td width="4%" align="center" >AR</td>
        <td width="4%" align="center" >项目<br/>进度<br/>百分比</td>
        <td width="4%" align="center" >操作</td>
      </tr>
      <c:forEach items="${itemReport3List }" var="itemReport3" varStatus="status">
      <tr <c:choose >
			   <c:when test="${status.index%2==0}"></c:when>
			   <c:otherwise >class="tr2"</c:otherwise>
			 </c:choose>>
<%--        <td width="4%" align="center" >序号</td>--%>
        <td align="center" class="STYLE3">${status.index+1 }</td>
        <td align="center" class="STYLE3">${itemReport3.riqi }</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${status.index==(showModifyIndex+1)}">
				<font id="rgcbColor">${itemReport3.rengongChengben }</font><input id="cgcbItem2" type="hidden" value="${itemReport3.rengongChengben }"/>
			   </c:when>
			   <c:otherwise >${itemReport3.rengongChengben }</c:otherwise>
			 </c:choose>
        
        </td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${status.index==(showModifyIndex+1)}">
				<font id="fycbColor">${itemReport3.feiyongChengben }</font><input id="fycbItem2" type="hidden" value="${itemReport3.feiyongChengben }"/>
			   </c:when>
			   <c:otherwise >${itemReport3.feiyongChengben }</c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">
        
        	<c:choose >
			   <c:when test="${status.index==(showModifyIndex+1)}">
				<font id="sjColor">${itemReport3.shuijin }</font><input id="sjItem2" type="hidden" value="${itemReport3.shuijin }"/>
			   </c:when>
			   <c:otherwise >${itemReport3.shuijin }</c:otherwise>
			 </c:choose>
        </td>
        <td align="center" class="STYLE3">
        
        	<c:choose >
			   <c:when test="${status.index==(showModifyIndex+1)}">
				<font id="fbfColor">${itemReport3.fenbaofei }</font><input id="fbfItem2" type="hidden" value="${itemReport3.fenbaofei }"/>
			   </c:when>
			   <c:otherwise >${itemReport3.fenbaofei }</c:otherwise>
			 </c:choose>
        </td>
        <td align="center" class="STYLE3">
       		 <c:choose >
			   <c:when test="${itemReport3.type==1}">${itemReport3.m }</c:when>
			   <c:otherwise ></c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">${itemReport3.contractCost}</td>
        <td align="center" class="STYLE3">${itemReport3.revenue }</td>
        <td align="center" class="STYLE3">${itemReport3.revenueTiaozheng }</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${itemReport3.type==1}">${itemReport3.feiyongBaifenbi }</c:when>
			   <c:otherwise ></c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${itemReport3.type==1}">${itemReport3.revenueBaifenbi }</c:when>
			   <c:otherwise ></c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${itemReport3.type==1 || itemReport3.type==2}">${itemReport3.billed}</c:when>
			   <c:otherwise ></c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${itemReport3.type==1 || itemReport3.type==2}">${itemReport3.cashIn}</c:when>
			   <c:otherwise ></c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${itemReport3.type==1}">
			   	<c:choose >
					<c:when test="${itemReport3.eub > 300000 }"><font color="red">${itemReport3.eub}</font>
			   		</c:when>
			   		<c:otherwise >${itemReport3.eub}</c:otherwise>
		   		</c:choose>
			   </c:when>
			   <c:otherwise ></c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${itemReport3.type==1 || itemReport3.type==2}">${itemReport3.ar}</c:when>
			   <c:otherwise ></c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${itemReport3.type==1}">
				${itemReport3.xiangmuJinduBaifenbi}
			   </c:when>
			   <c:otherwise ></c:otherwise>
			 </c:choose>
		</td>
        <td align="center" class="STYLE3">
        	<c:choose >
			   <c:when test="${status.index==showModifyIndex}">
				<a href='item/itemReport3_show.action?id=${itemReport3.id }&contractMoney=<s:property value='itemReport.contractMoney'/>&voMoney=<s:property value='itemReport.voMoney'/>&voDate=<s:property value='itemReport.voDate'/>'>修改</a>
        	    <a href='javascript:deleteItemReport3("${itemReport3.id }")'>删除</a>
			   </c:when>
			   <c:otherwise ></c:otherwise>
			 </c:choose>
        	
        </td>
      </tr>
      </c:forEach>
      <tr>
      	<td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td id="item1" width="4%" align="center" >&nbsp;</td>
        <td id="item2" width="4%" align="center" >&nbsp;</td>
        <td id="item3" width="4%" align="center" >&nbsp;</td>
        <td id="item4" width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
        <td width="4%" align="center" >&nbsp;</td>
      </tr>
</table>
</div>
  </body>
</html>
<script type="text/javascript">
function toDecimal(x) {  
    var f = parseFloat(x);  
    if (isNaN(f)) {  
        return;  
    }  
    f = Math.round(x*100)/100;  
    return f;  
} 
function changeItemValueColor(){
	var cgcbItem1 = document.getElementById("cgcbItem1").value;
	var cgcbItem2 = document.getElementById("cgcbItem2").value;
	document.getElementById("item1").innerHTML=toDecimal(cgcbItem2/cgcbItem1*100)+'%';
	if(cgcbItem1*0.8<=cgcbItem2){
		document.getElementById("rgcbColor").style.color="red";
	}else{
		document.getElementById("rgcbColor").style.color="#000000";
	}
	var fycbItem1 = document.getElementById("fycbItem1").value;
	var fycbItem2 = document.getElementById("fycbItem2").value;
	document.getElementById("item2").innerHTML=toDecimal(fycbItem2/fycbItem1*100)+'%';
	if(fycbItem1*0.8<=fycbItem2){
		document.getElementById("fycbColor").style.color="red";
	}else{
		document.getElementById("fycbColor").style.color="#000000";
	}
	var sjItem1 = document.getElementById("sjItem1").value;
	var sjItem2 = document.getElementById("sjItem2").value;
	document.getElementById("item3").innerHTML=toDecimal(sjItem2/sjItem1*100)+'%';
	if(sjItem1*0.8<=sjItem2){
		document.getElementById("sjColor").style.color="red";
	}else{
		document.getElementById("sjColor").style.color="#000000";
	}
	var fbfItem1 = document.getElementById("fbfItem1").value;
	var fbfItem2 = document.getElementById("fbfItem2").value;
	document.getElementById("item4").innerHTML=toDecimal(fbfItem2/fbfItem1*100)+'%';
	if(fbfItem1*0.8<=fbfItem2){
		document.getElementById("fbfColor").style.color="red";
	}else{
		document.getElementById("fbfColor").style.color="#000000";
	}
}
changeItemValueColor();
</script>