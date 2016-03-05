<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工时核算系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<LINK 
href="<%=basePath%>css/style1.css" type=text/css rel=stylesheet>
<META content="MSHTML 6.00.3790.1830" name=GENERATOR>
<script language="JavaScript">
  function show_div(menu){
    if(document.all.item(menu).style.display=="none"){
   
   
      document.all.item(menu).style.display="block";
      }else{
        document.all.item(menu).style.display="none";
        }
    }
	function quit1(){
		var flag = confirm("您确定要退出吗");
		if(flag==true){
			window.location.href="<%=basePath%>login!quit.action";
		}else{
			return;
		}
	} 
</script>
</HEAD>
<BODY>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="10%" valign="top"><table width="172" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="23" align="center" background="images/top_bg.jpg"><span class="STYLE2">閰嶇疆绠＄悊</span></td>
      </tr>
      <tr>
        <td><table width="172" border="0" cellpadding="0" cellspacing="0" bgcolor="#e4e4e4">
          <tr>
            <td height="419" valign="top"><table width="172" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="25" align="center" background="images/xl_bg.jpg"><span class="STYLE3">缁勭粐绠＄悊</span></td>
              </tr>
              <tr>
                <td height="23"><table width="100" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="50" align="right"><img src="images/pic.jpg" width="13" height="16" /></td>
                    <td width="72" align="right" ><a href="#" class="n2">缁勭粐绠＄悊</a></td>
                  </tr>
                </table></td>
              </tr>
              <tr>
                <td><table width="100" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="50" align="right"><img src="images/pic.jpg" width="13" height="16" /></td>
                    <td width="72" align="right" ><a href="#" class="n2">缁勭粐绠＄悊</a></td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="24" align="center" background="images/xl_bg2.jpg"><span class="STYLE3">缁勭粐绠＄悊</span></td>
          </tr>
          <tr>
            <td height="24" align="center" background="images/xl_bg2.jpg"><span class="STYLE3">缁勭粐绠＄悊</span></td>
          </tr>
          <tr>
            <td height="24" align="center" background="images/xl_bg2.jpg"><span class="STYLE3">缁勭粐绠＄悊</span></td>
          </tr>
          <tr>
            <td height="24" align="center" background="images/xl_bg2.jpg"><span class="STYLE3">缁勭粐绠＄悊</span></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
    <td align="left" valign="top"><img src="images/sc.jpg" width="8" height="539" /></td>
    <td width="89%" align="left" valign="top"><table width="100%" border="0" align="left" cellpadding="0" cellspacing="0">
      <tr>
        <td width="832" height="32" valign="top" background="images/right_bg.jpg"><table width="121" height="30" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td width="35">&nbsp;</td>
            <td width="86" align="left" class="STYLE2">缁勭粐绠＄悊</td>
          </tr>
        </table></td>
        <td align="right" valign="top" background="images/right_hou.jpg"><table width="150" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="66"><img src="images/tianjia.jpg" width="57" height="22" /></td>
            <td width="72"><img src="images/shanchu.jpg" width="56" height="22" /></td>
            <td width="12">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td colspan="2" align="left" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#c6c6c6">
          <tr>
            <td width="13%" height="23" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE5">閫夋嫨</td>
            <td width="27%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">鐩綍鍚嶇О </td>
            <td width="17%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">鍒涘缓鏃堕棿 </td>
            <td width="43%" align="center" background="images/dh_bg.jpg" bgcolor="#FFFFFF" class="STYLE2 STYLE3">鎻忚堪</td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">&nbsp;</td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">&nbsp;</td>
          </tr>
          <tr>
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">&nbsp;</td>
            <td bgcolor="#FFFFFF">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</BODY></HTML>
