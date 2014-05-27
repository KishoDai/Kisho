<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#updateShopButton").linkbutton({
		 iconCls: 'icon-save'
	}).click(function(){
		if(!$("#updateShopForm").form("validate")){
			 return;
		 }
		 $.post("${pageContext.request.contextPath }/ShopAction/updateShop.action",
	   		 $("#updateShopForm").serialize(),
	   		 function(data){
				   if(data.flag == "0"){ 
					   $.messager.alert('提示框',data.message);
		                $("#shopDataListTable").datagrid("load");
		 	        } else {
		 	        	$.messager.alert('提示框',data.message,'error');
		 	        }
		       },'json');
	});
	
	$("#resetUpdateShopButton").linkbutton({iconCls: 'icon-reset'}).click(function(){
		$("#updateShopForm").form("reset");
	});
	
	$("#sp_status").combobox({
		url : "${pageContext.request.contextPath }/SystemAction/loadJson4Options.action?key=shopStatusJson",
		valueField : 'id',
		textField : 'text',
		panelHeight:'auto' 
	});
	
	$("#sp_name").validatebox({
		required : true,
		missingMessage : "店铺名称必须输入"
	});
	
	$("#sp_status").validatebox({
		required : true,
		missingMessage : "店铺状态必选"
	});
	
	$("#sp_shopman_phone").validatebox({
		required : false,
		validType : 'length[11,11]',
		invalidMessage : "请输入有效的11位手机号码"
	});
	
	$("#sp_shopman_email").validatebox({
		required : false,
		validType : 'email',
		invalidMessage : "请输入有效的Email地址"
	});

});
</script>
<form id="updateShopForm" method="post">
	<table class="easyui-bg" style="width:100%;height:100%" >
		<tr>
			<td>店铺编号:</td>
			<td><input type="text" name="updateEntity.sp_id" disabled value="${updateEntity.sp_id }"/></td>
		</tr>
		<tr>
			<td>店铺名称:</td>
			<td><input type="text" id="sp_name" name="updateEntity.sp_name"value="${updateEntity.sp_name }"/></td>
		</tr>
		<tr>
			<td>店铺状态:</td>
			<td><input id="sp_status" name="updateEntity.sp_status" style="width:155px" value="${updateEntity.sp_status }"/>
			</td>
		</tr>
		<tr>
			<td>店主:</td>
			<td><input type="text" name="updateEntity.sp_shopman" value="${updateEntity.sp_shopman }"/></td>
		</tr>
		<tr>
			<td>店主手机:</td>
			<td><input type="text" id="sp_shopman_phone" name="updateEntity.sp_shopman_phone" value="${updateEntity.sp_shopman_phone }"/>
			</td>
		</tr>
		<tr>
			<td>店主Email:</td>
			<td><input type="text" id="sp_shopman_email" name="updateEntity.sp_shopman_email" value="${updateEntity.sp_shopman_email }"/></td>
		</tr>
		<tr>
			<td>修改人:</td>
			<td><input disabled name="updateEntity.ur_id"  value="${updateEntity.ur_id }"/></td>
		</tr>
		<tr>
			<td>修改时间:</td>
			<td><input disabled name="updateEntity.sp_update_time"  value="${updateEntity.sp_update_time }"/></td>
		</tr>
		<tr>
			<td>备注:</td>
			<td>
			<textarea name="updateEntity.sp_remark" cols=50 rows=5>${updateEntity.sp_remark }</textarea></td>
		</tr>
		<tr>
		    <td colspan="2" align="center">
		       <a id="updateShopButton">保存</a>
		       <a id="resetUpdateShopButton">重置</a>
		    </td>
		</tr>
	</table>
</form>