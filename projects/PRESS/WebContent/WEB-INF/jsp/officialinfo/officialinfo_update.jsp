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
<input type="hidden" name="officialInfo.officialid" value="${officialInfo.officialid}"/>
<body class="easyui-layout">
	<div region="center" title="修改官方信息" style="overflow: hidden;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%"/>
				<col width="50%" />
			</colgroup>
			<tbody>
			    <tr >
					<th >姓名</th>
					<td><input type="text" name="officialInfo.name" value="${officialInfo.name}" style="width:300px;"
					class="easyui-validatebox"
						        missingMessage="请输入您的姓名!" 
						        required="true"/></td>
				</tr>
				<tr >
					<th >手机</th>
					<td><input type="text" 
					class="easyui-numberbox"
						        missingMessage="请输入您的手机!" 
						        required="true"
					name="officialInfo.cellphone" value="${officialInfo.cellphone}" style="width:300px;"/></td>
				</tr>
				<tr>
					<th >固话</th>
					<td><input type="text"
					 name="officialInfo.telephone" value="${officialInfo.telephone }" style="width:300px;"/>
					</td>
				</tr>
				<tr >
					<th >Email</th>
					<td><input type="text" 
					class="easyui-validatebox"
					 validType="email"
						        missingMessage="请输入合法的Email!" 
						        required="true"
					name="officialInfo.email" value="${officialInfo.email}" style="width:300px;"/></td>
				</tr>
				<tr>
					<th >QQ</th>
					<td><input type="text"
					class="easyui-numberbox"
						        missingMessage="请输入QQ号!" 
						        required="true" 
					name="officialInfo.qq" value="${officialInfo.qq }" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<th >地址</th>
					<td><textarea
					class="easyui-validatebox"
						        missingMessage="请输入您的Email!" 
						        required="true" 
					name="officialInfo.address" cols="36" rows="3">${officialInfo.address}</textarea>
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