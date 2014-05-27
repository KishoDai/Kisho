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
		 if(!$("#_update_form_").form("validate")){
			 return;
		 }
	     $.post("${pageContext.request.contextPath}/${module}/${submodule}_do_update.action",
	    		 $("#_update_form_").serialize(),
	    		 function(data){
			    if(data.flag == "1"){ 
	    	        $.messager.alert('提示框',"修改信息成功!",'info');
	    	        window.location="${pageContext.request.contextPath}/${module}/${submodule}_to_index.action";
 	             }
		 },'json');
	});
});
</script>
</head>
<form id="_update_form_" method="post" >
<input type="hidden" name="admin.adminid" value="${admin.adminid}"/>
<body class="easyui-layout">
	<div region="center" title="修改管理员信息" style="overflow: hidden;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%" />
				<col width="50%" /> 
			</colgroup>  
			<tbody>
				<tr >
					<th >ID</th>
					<td><input type="text" style="width:300px;"  value="${admin.adminid}" disabled="disabled"/></td>
				</tr>
				<tr>
				    <th >QQ</th>
					<td><input type="text" style="width:300px;"  value="${admin.qq }"
					name="admin.qq"
					class="easyui-numberbox"
						        missingMessage="请输入QQ号!" 
						        required="true"/>
					</td>
				</tr>
				<tr >
					<th >Email</th>
					<td><input type="text" style="width:300px;"  
					    name="admin.email"
					   value="${admin.email }"
					   class="easyui-validatebox" validType="email" required="true"
					   missingMessage="请输入合法的Email!"
					   /></td>
				</tr>
				<tr>
				  <th >Msn</th>
					<td><input type="text" style="width:300px;"  value="${admin.msn }"
					name="admin.msn"
					class="easyui-validatebox" validType="email"
					missingMessage="请输入合法的Msn!" 
				/>
					</td>
				</tr>
				<tr >
					<th >地址</th>
					<td >
					<textarea name="admin.address" cols="36" rows="3" 
					class="easyui-validatebox" required="true" missingMessage="请输入您的地址!">${admin.address }</textarea>
					</td>
				</tr>
				<tr >
					<td colspan="2" align="center">
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_update_btn_" iconCls="icon-edit" >修改</a>
					</td>
				</tr>
			<tbody>
		</table>
    </div>
</body>
</form>
</html>