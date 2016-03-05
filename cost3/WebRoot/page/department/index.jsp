<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"  uri="/WEB-INF/tag/c.tld"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="<%=basePath %>js/public.js"></script>
</head>
<font color="bule">当前位置:</font><font color="bule">人员组织管理>></font><font color="bule">组织机构</font>
<BODY bgColor=#dee7ff leftMargin=0 background="" topMargin=0 marginheight="0" marginwidth="0">
<center>
      <TABLE width="778" border=0 cellPadding=0 cellSpacing=0 borderColor=#ffffff bgColor=#dee7ff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR height=35>
            <TD align=middle width=20 background=images/title_left.gif 
          bgColor=#dee7ff></TD>
            <TD align=middle width=120 background=images/title_left.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7><font color="#FFFFFF">&nbsp;</font></FONT> </TD>
            <TD align=middle width=11 background=images/title_middle.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
            <TD align=middle background=images/title_right.gif 
          bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT> </TD>
          </TR>
        </TBODY>
      </TABLE>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD width="82%" height=14 align=right vAlign=center noWrap>
            </TD>
            <TD width="18%" align=right vAlign=center noWrap>　</TD>
          </TR>
          <TR>
            <TD height=14 align=right vAlign=center noWrap><!-- 在这里插入查询表单 -->
            </TD>
            <TD height=14 align="left" vAlign=center noWrap>
            <a href="<%=basePath %>dept/addInput.action">添加机构信息</a>
            <a href="/ssh2/org/browe.action?parentId=${ppid }">返回</a>
            </TD>
          </TR>
          <TR>
            <TD height=28 colspan="2" align=right vAlign=center noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
            <!-- 可以在这里插入分页导航条 -->
            </TD>
          </TR>
        </TBODY>
      </TABLE>
      <table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6"style="FONT-SIZE: 10pt">
          <!-- 列表标题栏 -->
	      <tr bgcolor="#EFF3F7" class="TableBody1">
		      <td width="20%" height="37" align="center"><b>序号</b></td>
		      <td width="20%" height="37" align="center"><B>机构名称</B></td>
		      <td width="20%" height="37" align="center"><b>机构编号</b></td>
              <td width="20%" height="37" align="center"><b>操作</b></td>
          </tr>
          <!-- 列表数据栏 -->
          
          <c:if test="${!empty deptList.datas}">
          <c:forEach items="${deptList.datas }" var="dept">
	      <tr bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
		      <td align="center" vAlign="center">${dept.id }</td>
	          <td align="center" vAlign="center"><a href="/ssh2/org/browe.action?parentId=${dept.id }">${dept.departmentcode }</a></td>
	          <td align="center" vAlign="center">${dept.departmentname }</td>
	          <td align="center" vAlign="center">
	          <a href="/dept/updateInput.action?id=${dept.id}" >更新</a>
	          <a href="/dept/delete.action?id=${dept.id };" >删除</a>
	          </td>
        </tr>
        </c:forEach>
		</c:if>
        <!-- 在列表数据为空的时候，要显示的提示信息 -->
	    <c:if test="${empty deptList.datas}">
	    <tr>
	    	<td colspan="7" align="center" bgcolor="#EFF3F7" class="TableBody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	没有找到相应的记录
	    	</td>
	    </tr>
	    </c:if>
      </table>
      <TABLE width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
        <TBODY>
          <TR>
            <TD height=28 align=right vAlign=center noWrap background=images/list_middle.jpg>&nbsp;&nbsp;
    		</TD>
          </TR>

        </TBODY>
      </TABLE>
</center>

</body>

</html>
