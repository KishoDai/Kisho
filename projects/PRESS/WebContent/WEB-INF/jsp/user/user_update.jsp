<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../comm/comm.jsp"%>
<script type="text/javascript">
$(function(){
	$("#_update_btn_").click(function(){
		 //TODO...
		 if(!$("#_update_form_").form("validate")){
			 return;
		 }
		 //用户ID校验
		 //TODO...
	     $("#_update_form_").attr("action","${pageContext.request.contextPath}/${module}/${submodule}_do_update.action").submit();
	});
});
</script>
</head>
<form id="_update_form_" method="post" >
<input type="hidden" name="updateCondition.userid" value="${updateCondition.userid}"/>
<body class="easyui-layout">
	<div region="center" title="修改用户" style="overflow: hidden;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" >
			<colgroup>
				<col width="20%"/>
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr >
					<th >用户ID</th>
					<td><input type="text" name="updateCondition.userid" value="${updateCondition.userid }" style="width:300px;" disabled="disabled"/></td>
				</tr>
				<tr>
				    <th >昵称</th>
					<td><input type="text" name="updateCondition.nicknam"  value="${updateCondition.nicknam }" style="width:300px;"/>
					</td>
				</tr>
				<tr>
				  <th >email</th>
					<td><input type="text" name="updateCondition.email" value="${updateCondition.email}" style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >手机号</th>
					<td><input type="text" name="updateCondition.cellphone" value="${updateCondition.cellphone}" style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >固话</th>
					<td><input type="text" name="updateCondition.telephone"  value="${updateCondition.telephone}" style="width:300px;"/>
					</td>
				</tr>
				<tr>
				    <th >QQ</th>
					<td><input type="text" name="updateCondition.qq"   value="${updateCondition.qq}" style="width:300px;"/>
					</td>
				</tr>
		
				<tr>
				    <th >MSN</th>
					<td><input type="text"  style="width:300px;"   value="${updateCondition.msn}"/>
					</td>
				</tr>
				<tr>
				    <th >会员级别</th>
					<td>
					   <select id="lvl" name="updateCondition.lvl" style="width:305px;" >
					      ${lvlOptions }
					   </select>
					</td>
				</tr>
				
				<tr >
					<th >是否被注销</th>
					<td>
					   <select id="islogout" name="updateCondition.islogout" style="width:305px;" >
					      ${islogoutOptions }
					   </select>
					</td>
				</tr>
				<tr >
					<th >注销原因</th>
					<td><textarea  cols="36" rows="3">${updateCondition.logoutreason }</textarea>
					</td>
				</tr>
				<tr >
					<td colspan="2" align="center">
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_update_btn_" iconCls="icon-edit" >修改</a>
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_back_btn_"  iconCls="icon-back">返回</a>
					</td>
				</tr>
			<tbody>
		</table>
    </div>
</body>
</form>
</html>