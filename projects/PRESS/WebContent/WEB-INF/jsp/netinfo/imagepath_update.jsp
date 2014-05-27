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
		 window.callback = function(data){
			 if(data.flag == "1"){ 
	    	        $.messager.alert('提示框',"修改信息成功!",'info');
	    	        window.location="${pageContext.request.contextPath}/${module}/${submodule}_to_index.action";
 	        }
		 };
	     $.post("${pageContext.request.contextPath}/${module}/${submodule}_do_update.action",
	    		 $("#_update_form_").serialize(),
	    		 callback,'json');
	});
});
</script>
</head>
<form id="_update_form_" method="post" >
 <input type="hidden" name="paramnam" value="${paramnam }"/>
<body class="easyui-layout">
	<div region="center" title="修改图片路径" style="overflow: hidden;" class="easyui-bg">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%"/>
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr >
					<th class="ui-widget-header ">图片路径</th>
					<td><input type="text" name="paramvalue" value="${paramvalue}" style="width:500px;"
					 class="easyui-validatebox"
						       missingMessage="请输入图片路径" 
						       required="true"/></td>
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