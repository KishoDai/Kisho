<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../../comm/comm.jsp"%>
<script type="text/javascript">
$(function(){
	$("#_add_btn_").click(function(){
		 if(!$("#_add_form_").form("validate")){
			 return;
		 }
		 //产品类型名称校验
		 //TODO...
	     $("#_add_form_").attr("action","${pageContext.request.contextPath}/${module}/${submodule}_do_add.action").submit();
	});
});
</script>
</head>
 <form id="_add_form_" method="post" >
<body class="easyui-layout">
	<div region="center" title="添加产品类型" style="overflow: hidden;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%" align="right"/>
				<col width="40%" align="left"/>
			</colgroup>
			<tbody>
				<tr >
					<th >产品类型名称:&nbsp;</th>
					<td><input type="text" 
					           name="addCondition.protypnam"   
					           size="50"
					           class="easyui-validatebox"
					           missingMessage="请输入产品类型名称"
					           required="true"
					           /></td>
				</tr>
				<tr >
					<th >产品类型排序码:&nbsp;</th>
					<td><input type="text" 
					           name="addCondition.sortnum"   
					           size="50"
					           class="easyui-numberbox"
					           missingMessage="请输入排序码"
					           required="true"/></td>
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
