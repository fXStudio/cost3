<%@ page language="java" import="java.util.*"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"  uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg"  uri="http://jsptags.com/tags/navigation/pager" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    	  if(!confirm('您确定保存吗？')){
    		  return;
    	  }
<%--         var itemname= document.getElementById("itemname");--%>
<%--         var itemcode= document.getElementById("itemcode");--%>
         var riqi= document.getElementById("riqi");
         var rengong_chengben= document.getElementById("rengong_chengben");
         var feiyong_chengben= document.getElementById("feiyong_chengben");
         var shuijin= document.getElementById("shuijin");
         var fenbaofei= document.getElementById("fenbaofei");
        
<%--         if(Trim(itemname.value)==""){--%>
<%--            alert('项目名称不能为空');--%>
<%--            return false;--%>
<%--         }--%>
<%--        if(Trim(itemcode.value)==""){--%>
<%--            alert('项目编码不能为空');--%>
<%--            return false;--%>
<%--         }--%>
         if(Trim(riqi.value)==""){
            alert('日期不能为空');
            return false;
         }
         if(Trim(rengong_chengben.value)==""){
            alert('人工成本不能为空');
            return false;
         }
         if(Trim(feiyong_chengben.value)==""){
            alert('费用不能为空');
            return false;
         }
         if(Trim(shuijin.value)==""){
            alert('税金不能为空');
            return false;
         }
         if(Trim(fenbaofei.value)==""){
            alert('分包费不能为空');
            return false;
         }
         check();
         deptform.submit();
      }
      function check(){
    	 var voDate='<%=request.getAttribute("voDate") %>';
    	 var contract_money_double=0;
    	 var voMoney_double=0;
    	 var rengong_chengben_double=0;
    	 var feiyong_chengben_double=0;
    	 var shuijin_double=0;
    	 var fenbaofei_double=0;
    	 
    	 var feiyong_chengben_sum_double=0;
    	 var shuijin_sum_double=0;
    	 var fenbaofei_sum_double=0;
    	 var rengong_chengben_sum_double=0;
    	 
    	 var riqi=document.getElementById("riqi").value;
    	 if(riqi==''){
    		 alert('请先输入日期!');
    		 return false;
    	 }
    	 
    	 var contract_money= document.getElementById("contract_money");
    	 var voMoney= document.getElementById("voMoney");
    	 var rengong_chengben= document.getElementById("rengong_chengben");
         var feiyong_chengben= document.getElementById("feiyong_chengben");
         var shuijin= document.getElementById("shuijin");
         var fenbaofei= document.getElementById("fenbaofei");
         var m= document.getElementById("m");
         var contract_cost= document.getElementById("contract_cost");
         
         var feiyong_chengben_sum= document.getElementById("feiyong_chengben_sum");
         var shuijin_sum= document.getElementById("shuijin_sum");
         var fenbaofei_sum= document.getElementById("fenbaofei_sum");
         var rengong_chengben_sum= document.getElementById("rengong_chengben_sum");
         
         contract_money_double=parseFloat(contract_money.value);
         if(duibi(voDate,riqi)<=0){
         	voMoney_double=parseFloat(voMoney.value);
         }else{
        	 voMoney_double=0;
         }
         rengong_chengben_double=parseFloat(rengong_chengben.value);
         feiyong_chengben_double=parseFloat(feiyong_chengben.value);
         shuijin_double=parseFloat(shuijin.value);
         fenbaofei_double=parseFloat(fenbaofei.value);
         
         feiyong_chengben_sum_double=parseFloat(feiyong_chengben_sum.value);
         shuijin_sum_double=parseFloat(shuijin_sum.value);
         fenbaofei_sum_double=parseFloat(fenbaofei_sum.value);
         rengong_chengben_sum_double=parseFloat(rengong_chengben_sum.value);
         //alert(feiyong_chengben_sum_double+"-"+shuijin_sum_double+"-"+rengong_chengben_sum_double+"-"+fenbaofei_sum_double);
         
         if(rengong_chengben_double==0){
        	 alert('人工成本不能为0！');
        	 return;
         }
         
         var mTemp=(contract_money_double+voMoney_double-feiyong_chengben_double-feiyong_chengben_sum_double-shuijin_double-shuijin_sum_double-fenbaofei_double-fenbaofei_sum_double)/(rengong_chengben_double+rengong_chengben_sum_double);
         m.value=mTemp.toFixed(2);
         
         var contractCostTemp=rengong_chengben_double+feiyong_chengben_double+shuijin_double+fenbaofei_double;
         contract_cost.value=contractCostTemp;
      }
      //如果a<b返回负数，a=b返回0，a>b返回正数
      function duibi(a, b) {
		    var arr = a.split("-");
		    var starttime = new Date(arr[0], arr[1], arr[2]);
		    var starttimes = starttime.getTime();
		
		    var arrs = b.split("-");
		    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
		    var lktimes = lktime.getTime();
		
		    if (starttimes < lktimes) {
		        return -1;
		    }else if(starttimes == lktimes){
		    	return 0;
		    }else{
		        return 1;
		    }
		
		}
</script>
  </head>
<body bgcolor="#f1f1f1">
<div class="address">
	<div class="bt"></div>	
</div>
<div class="table" style="margin-top:15px;">
<form name='deptform' action="<%= basePath%>item/itemReport2_update.action" method='post' onsubmit="return false">
	<table width="100%" border="0" cellpadding="0" cellspacing="1">
<%--      <tr>--%>
<%--        <td width="8%" align="center"   >项目编号</td>--%>
<%--        <td width="43%" align="left"   >--%>
<%--        <input type="text" id='itemcode' name="itemReport.itemcode" value="<s:property value='itemReport.itemcode'/>"><span><font color='red'>*</font></span>--%>
<%--        </td>--%>
<%--        <td width="8%" height="23" align="center"   >项目名称</td>--%>
<%--        <td width="27%" align="left"   >--%>
<%--        <input type="text" id='itemname' name="itemReport.itemname" value="<s:property value='itemReport.itemname'/>" ><span><font color='red'>*</font></span>--%>
<%--        </td>--%>
<%--      </tr>--%>
      <tr>
      	<td width="8%" height="23" align="center"   >合同总金额</td>
        <td width="27%" align="left"   >
        <input type="text" id='contract_money' value="<%=request.getParameter("contractMoney") %>" readonly>
        </td>
        <td width="8%" align="center"   >&nbsp;</td>
        <td width="43%" align="left"   >&nbsp;</td>
      </tr>
      <tr>
      	<td width="8%" height="23" align="center"   >VO金额</td>
        <td width="27%" align="left"   >
        <input type="text" id='voMoney' value="<%=request.getParameter("voMoney") %>" readonly>
        </td>
        <td width="8%" align="center"   >VO日期</td>
        <td width="43%" align="left"   >
        <input type="text" id='voDate' value="<%=request.getAttribute("voDate") %>" readonly>
        </td>
      </tr>
      <tr>
        <td width="8%" align="center"   >日期</td>
        <td width="43%" align="left"   >
        <input type="text" id='riqi' name="riqi" onFocus="new WdatePicker(this,null,false,'whyGreen')" value='${itemReport2.riqi}'><span><font color='red'>*（修改日期后，请点击[计算]按钮）</font></span>
        </td>
        <td width="8%" height="23" align="center"   >人工成本</td>
        <td width="27%" align="left"   >
        <input type="text" id='rengong_chengben' name="rengongChengben" onChange="check()"  value='${itemReport2.rengongChengben}' ><span><font color='red'>*</font></span>
        </td>
      </tr>
      
      <tr>
        <td width="8%" height="23" align="center"   >费用成本</td>
        <td width="27%" align="left"   >
        <input type="text" id='feiyong_chengben' name="feiyongChengben" onChange="check()"  value='${itemReport2.feiyongChengben}' ><span><font color='red'>*</font></span>
        </td>
        <td width="8%" align="center"   >税金</td>
        <td width="43%" align="left"   >
        <input type="text" id='shuijin' name="shuijin" onChange="check()"  value='${itemReport2.shuijin}'><span><font color='red'>*</font></span>
        </td>
      </tr>
      <tr>
        <td width="8%" height="23" align="center"   >分包费</td>
        <td width="27%" align="left"   >
        <input type="text" id='fenbaofei' name="fenbaofei" onChange="check()"  value='${itemReport2.fenbaofei}' ><span><font color='red'>*</font></span>
        </td>
        <td width="8%" align="center"   >M</td>
        <td width="43%" align="left"   >
        <input type="text" id='m' name="m" onChange="check()"  value='${itemReport2.m}'><span>
        </td>
      </tr>
      <tr>
        <td width="8%" height="23" align="center"   >Contract Cost</td>
        <td width="27%" align="left"   >
        <input type="text" id='contract_cost' name="contractCost" onChange="check()"  value='${itemReport2.contractCost}' >
        </td>
        <td width="8%" align="center"   >
<%--        Revenue--%>
        </td>
        <td width="43%" align="left"   >
<%--        <input type="text" id='revenue' name="revenue" onChange="check()"  value='${itemReport2.revenue}' ><span>--%>
        </td>
      </tr>
<%--      <tr>--%>
<%--        <td width="8%" height="23" align="center"   >Revenue调整</td>--%>
<%--        <td width="27%" align="left"   >--%>
<%--        <input type="text" id='revenue_tiaozheng' name="revenueTiaozheng" onChange="check()"  value='${itemReport2.revenueTiaozheng}' >--%>
<%--        </td>--%>
<%--        <td width="8%" align="center"   >费用百分比</td>--%>
<%--        <td width="43%" align="left"   >--%>
<%--        <input type="text" id='feiyong_baifenbi' name="feiyongBaifenbi" onChange="check()"  value='${itemReport2.feiyongBaifenbi}' >--%>
<%--        </td>--%>
<%--      </tr>--%>
<%--      <tr>--%>
<%--        <td width="8%" height="23" align="center"   >Revenue 百分比</td>--%>
<%--        <td width="27%" align="left"   >--%>
<%--        <input type="text" id='revenue_baifenbi' name="revenueBaifenbi" onChange="check()"  value='${itemReport2.revenueBaifenbi}' >--%>
<%--        </td>--%>
<%--        <td width="8%" align="center"   ></td>--%>
<%--        <td width="43%" align="left"   >--%>
<%--        </td>--%>
<%--      </tr>--%>
      <tr>
          <td align="center" colspan="4">
          	<input type="button" name= "button"  value="计算" onClick="check()"/>
          	<input type="button" name= "button"  value="保存" onClick="checkSubmit()"/>
        	<input type="reset" name="Submit3" value="重置" />
          	<input type="hidden" id='id' name="id" value='${itemReport2.id}' >
          	<input type="hidden" id='itemId' name="itemId" value='${itemReport2.itemId}' >
          	<input type="hidden" id='reportId' name="reportId" value='${itemReport2.reportId}' >
          	<input type="hidden" id='feiyong_chengben_sum' name="feiyongChengbenSum" value='${feiyongChengbenSum}' >
          	<input type="hidden" id='shuijin_sum' name="shuijinSum" value='${shuijinSum}' >
          	<input type="hidden" id='fenbaofei_sum' name="fenbaofeiSum" value='${fenbaofeiSum}' >
          	<input type="hidden" id='rengong_chengben_sum' name="rengongChengbenSum" value='${rengongChengbenSum}' >
          </td>
        </tr>
    </table>
</form>
</div>
  </body>
</html>
