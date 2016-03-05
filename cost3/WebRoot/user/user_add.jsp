<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="/WEB-INF/tag/c.tld"%>
<%@ taglib prefix="pg" uri="http://jsptags.com/tags/navigation/pager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>"/>

		<title>柏坚工时核算系统</title>
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
		<script language="javascript" type="text/javascript"
			src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript">
      function checkSubmit(){
         var username= document.getElementById("username");
         var usercode= document.getElementById("usercode");
         var password= document.getElementById("password");
         var worklevel = document.getElementById("worklevel");
         var sex = document.getElementById("sex");
         var deptid= document.getElementById("deptIdId");
         var email= document.getElementById("email");
         var tel= document.getElementById("tel");
         var post= document.getElementById("post");
        
         if(Trim(username.value)==""){
            alert('人员名称不能为空');
            return false;
         }else if(Trim(usercode.value)==""){
            alert('人员编码不能为空');
            return false;
         }else if(Trim(tel.value)==""){
            alert('手机号码不能为空');
            return false;
         }else if(checkNumber(tel.value)){
            alert('手机号码格式不正确，请重新输入\n\n例如:13916752109');
            return false;
         }else if(Trim(email.value)==""){
            alert('邮箱不能为空');
            return false;
         }else if(checkemail(email.value)){
            alert('email格式不正确，请重新输入');
            return false;
         }else if(Trim(worklevel.value)==""){
            alert('岗位级别不能为空');
            return false;
         }else if(Trim(sex.value)==""){
            alert('人员性别不能为空');
            return false;
         }else if(Trim(password.value)==""){
            alert('人员初始密码不能为空');
            return false;
         }else if(Trim(deptid.value)==""){
            alert('部门不能为空');
            return false;
         }else if(Trim(post.value)==""){
            alert('职位不能为空');
            return false;
         }
         deptform.action='<%=basePath%>user/add.action?user.dept.id='+deptid.value;
         deptform.submit();
      }
      
  	function checkNumber(TempS) {
		var cellphone=/^(((1[0-9]{1}[0-9]{1}))+\d{8})$/;
		if(!cellphone.test(TempS)){
        return true;
		}
		return false;
	}
	function checkemail(v){
    	if(!new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/).test(v)){
        return true;
        }
        return false;
    }
</script>
	</head>
	<body bgcolor="#f1f1f1">
		<div class="address">
			<div class="bt">
				人员管理——人员添加
			</div>
		</div>
		<div class="table" style="margin-top: 15px;">
			<form name='deptform' action="" method='post' onSubmit="return false">
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td width="8%" align="center">
							人员名称
						</td>
						<td width="43%" align="left" class="STYLE2 STYLE3">
							<input type="text" id='username' name="user.username"/>
							<span><font color='red'>*</font>
							</span>
						</td>
						<td width="8%" align="center">
							英文名字
						</td>
						<td width="43%" align="left">
							<input type="text" id='englishname' name="user.englishname"//>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							人员编码
						</td>
						<td width="43%" align="left">
							<input type="text" id='usercode' name="user.usercode"/>
							<span><font color='red'>*</font></span>
						</td>
						<td width="8%" align="center">
							人员简码
						</td>
						<td width="43%" align="left">
							<input type="text" id='easycode' name="user.easycode"/>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							手机号码
						</td>
						<td width="43%" align="left">
							<input type="text" id='tel' name="user.tel"/>
							<span><font color='red'>*</font>
							</span>
						</td>
						<td width="8%" align="center">
							人员邮箱
						</td>
						<td width="43%" align="left">
							<input type="text" id='email' name="user.emial"/>
							<span><font color='red'>*</font>
							</span>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							入职时间
						</td>
						<td width="43%" align="left">
							<input type="text" id='jobdate' name="user.jobdate"
								onFocus="new WdatePicker(this,null,false,'whyGreen')"/>
						</td>
						<td width="8%" align="center">
							聘用类型
						</td>
						<td width="43%" align="left">
							<select name='user.employtype' id='employtype'>
								<option value='全职'>
									全职
								</option>
								<option value='兼职'>
									兼职
								</option>
								<option value='离职'>
									离职
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							岗位级别
						</td>
						<td width="27%" align="left">
							<input type="text" id='worklevel' name="user.worklevel"
								value="${user.worklevel}" />
							<span><font color='red'>*</font> </span>
						</td>
						<td width="8%" align="center">
							职位
						</td>
						<td width="43%" align="left">
							<select name='user.post.id' id='post'>
								<option>
									请选择
								</option>
								<s:iterator value="#request.post" status="i">
									<option value="${id}"
										<s:if test="#request.user.post.id==id">selected</s:if>>
										${postname}
									</option>
								</s:iterator>
							</select>
							<span><font color='red'>*</font></span>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							证件号码
						</td>
						<td width="43%" align="left">
							<input type="text" id='papersnum' name="user.papersnum" />
						</td>
						<td width="8%" align="center">
							人员籍贯
						</td>
						<td width="43%" align="left">
							<select name='user.nat.nativecode' id='census'>
								<option value='hk'
									<c:if test="${user.nat.nativecode eq 'hk'}">selected</c:if>>
									港籍
								</option>
								<option value='dw'
									<c:if test="${user.nat.nativecode eq 'dw'}">selected</c:if>>
									大陆外埠
								</option>
								<option value='bj'
									<c:if test="${user.nat.nativecode eq 'bj'}">selected</c:if>>
									大陆北京
								</option>
								<option value='sh'
									<c:if test="${user.nat.nativecode eq 'sh'}">selected</c:if>>
									上海
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							人员性别
						</td>
						<td width="43%" align="left">
							<select name='user.sex' id='sex'>
								<option value=''>
									请选择
								</option>
								<option value='man'>
									男
								</option>
								<option value='gril'>
									女
								</option>
							</select>
							<span><font color='red'>*</font></span>
						</td>
						<td width="8%" align="center">
							用户类别
						</td>
						<td width="43%" align="left">
							<select name='user.type' id='type'>
								<option value='common'>
									普通用户
								</option>
								<option value='system'>
									管理用户
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							户口所在地
						</td>
						<td width="43%" align="left">
							<input type="text" id='account' name="user.account"
								maxlength='11'/>
						</td>
						<td width="8%" align="center">
							国籍
						</td>
						<td width="43%" align="left">
							<input type="text" id='nationality' name="user.nationality"/>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							受教育程度
						</td>
						<td width="43%" align="left">
							<input type="text" id='education' name="user.education"
								maxlength='11'/>
						</td>
						<td width="8%" align="center">
							专业
						</td>
						<td width="43%" align="left">
							<input type="text" id='major' name="user.major"/>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							职称
						</td>
						<td width="43%" align="left">
							<input type="text" id='title' name="user.title" maxlength='11'/>
						</td>
						<td width="8%" align="center">
							专业资质
						</td>
						<td width="43%" align="left">
							<input type="text" id='majorquality' name="user.majorquality"/>
						</td>
					</tr>
					<tr>
						<td width="8%" height="23" align="center">
							现家庭住址
						</td>
						<td width="43%" align="left">
							<input type="text" id='address' name="user.address"/>
						</td>
						<td width="8%" align="center">
							婚姻情况
						</td>
						<td width="43%" align="left">
							<select name='user.marry' id='marry'>
								<option value='未婚'>
									未婚
								</option>
								<option value='已婚'>
									已婚
								</option>
								<option value='离异'>
									离异
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							联系人
						</td>
						<td width="43%" align="left">
							<input type="text" id='linkman' name="user.linkman"
								maxlength='11'/>
						</td>
						<td width="8%" align="center">
							联系电话
						</td>
						<td width="43%" align="left">
							<input type="text" id='linkmantel' name="user.linkmantel"/>
						</td>
					</tr>
					<tr>
						<td width="8%" align="center">
							密码
						</td>
						<td width="43%" align="left">
							<input type="password" id='password' name="user.password"
								maxlength='11'/>
							<span><font color='red'>*</font>
							</span>
						</td>
						<td width="8%" align="center">
							所属部门
						</td>
						<td width="43%" align="left">
							<input name="text" type="text" disabled="disabled"
								id='deptNameId'/>
							<input type="hidden" name="depteId" id="deptIdId"/>
							<span><font color='red'>*</font>
							</span>
							<input name="image" type="image"
								onclick="openWin('<%=basePath%>dept/selectBrowe.action','selectOrgs',500,400,1);"
								src="<%=basePath%>images/xuanzen.jpg" />
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="button" name="button" value="添加"
								onclick="checkSubmit()" />
							<input type="reset" name="Submit3" value="重置" />
							<input type="button" name="button" value="返回"
								onclick="javascript:window.history.back(-1);" />
						</td>
						<td width="16">
							&nbsp;
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
