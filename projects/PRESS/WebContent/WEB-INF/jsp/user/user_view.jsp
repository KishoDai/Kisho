<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../comm/comm.jsp"%>
<title>查看用户</title>
<script type="text/javascript">
 
</script>
</head>
<body class="easyui-layout">
  <div region="center" title="查看用户" style="overflow: hidden;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%" />
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr >
					<th >用户ID</th>
					<td><input type="text" name="viewCondition.userid" value="${viewCondition.userid }" disabled="disabled" style="width:300px;"/></td>
				</tr>
				<tr>
				    <th >昵称</th>
					<td><input type="text" name="viewCondition.nicknam" value="${viewCondition.nicknam }" disabled="disabled" style="width:300px;"/>
					</td>
				</tr>
				<tr>
				  <th >email</th>
					<td><input type="text" name="viewCondition.email" value="${viewCondition.email }" disabled="disabled" style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >手机号</th>
					<td><input type="text" name="viewCondition.cellphone" value="${viewCondition.cellphone}" disabled="disabled" style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >固话</th>
					<td><input type="text" name="viewCondition.telephone" value="${viewCondition.telephone}" disabled="disabled" style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >QQ</th>
					<td><input type="text" name="viewCondition.qq" value="${viewCondition.qq }" disabled="disabled" style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >Email</th>
					<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.email }"/>
					</td>
				</tr>
				<tr>
				    <th >MSN</th>
					<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.msn }"/>
					</td>
				</tr>
				<tr>
				    <th >会员级别</th>
					<td>
					   <select id="lvl" name="viewCondition.lvl" style="width:305px;" disabled="disabled">
					      ${lvlOptions }
					   </select>
					</td>
				</tr>
				<tr>
				    <th >注册时间</th>
					<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.regtime }"/>
					</td>
				</tr>
				<tr >
					<th >是否登录</th>
					<td>
					     <select id="islogin" name="viewCondition.islogin" style="width:305px;" disabled="disabled">
					      ${isloginOptions }
					   </select>
					</td>
				</tr>
				<tr >
					<th >上次登录时间</th>
					<td>
					<input type="text" value="${viewCondition.lastlogintime }" disabled="disabled" style="width:300px;"/>
					</td>
				</tr>
				<tr >
					<th >上次登录IP</th>
					<td>
					 <input type="text" value="${viewCondition.lastloginip }" disabled="disabled" style="width:300px;"/>
					</td>
				</tr>
				<tr >
					<th >是否被注销</th>
					<td>
					   <select id="islogout" name="viewCondition.islogout" style="width:305px;" disabled="disabled">
					      ${islogoutOptions }
					   </select>
					</td>
				</tr>
				<tr >
					<th >注销原因</th>
					<td><textarea readonly="disabled" cols="36" rows="3">${viewCondition.logoutreason }</textarea>
					</td>
				</tr>
			<tr >
				<td colspan="2" align="center">
				   <a href="javascript:void(0);" class="easyui-linkbutton" id="_back_btn_" iconCls="icon-back">返回</a>
				</td>
			</tr>
		</tbody>
	</table>
  </div>
</body>
</html>