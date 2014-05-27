<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../comm/comm.jsp"%>
<script type="text/javascript">
$(function(){
	$("#_add_btn_").click(function(){
		 if(!$("#_add_form_").form("validate")){
			 return;
		 }
	     $("#_add_form_").attr("action","${pageContext.request.contextPath}/${module}/${submodule}_do_add.action").submit();
	});
});
</script>
</head>
 <form id="_add_form_" method="post" >
<body class="easyui-layout">
	<div region="center" title="添加用户" style="overflow: hidden;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%"/>
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr >
					<th >用户ID</th>
					<td><input type="text" name="addCondition.userid"  style="width:300px;"/></td>
				</tr>
				<tr>
				    <th >昵称</th>
					<td><input type="text" name="addCondition.nicknam"  style="width:300px;"/>
					</td>
				</tr>
				<tr>
				  <th >email</th>
					<td><input type="text" name="addCondition.email"  style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >手机号</th>
					<td><input type="text" name="addCondition.cellphone"  style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >固话</th>
					<td><input type="text" name="addCondition.telephone"  style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >QQ</th>
					<td><input type="text" name="addCondition.qq"  style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >MSN</th>
					<td><input type="text" name="addCondition.msn" style="width:300px;" />
					</td>
				</tr>
				<tr>
				    <th >会员级别</th>
					<td>
					   <select  name="addCondition.lvl" style="width:305px;" >
					      ${lvlOptions }
					   </select>
					</td>
				</tr>
				
				<tr >
					<th >是否被注销</th>
					<td>
					   <select  name="addCondition.islogout" style="width:305px;" >
					      ${islogoutOptions }
					   </select>
					</td>
				</tr>
				<tr >
					<th >注销原因</th>
					<td><textarea  cols="36" rows="3">${addCondition.logoutreason }</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" >
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_add_btn_" iconCls="icon-save">保存</a> 
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_back_btn_" iconCls="icon-back">返回</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</form>
</html>