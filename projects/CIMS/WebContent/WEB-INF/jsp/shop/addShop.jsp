<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#addShopButton").linkbutton({
		 iconCls: 'icon-save'
	}).click(function(){
		if(!$("#addShopForm").form("validate")){
			 return;
		 }
		 $.post("${pageContext.request.contextPath }/ShopAction/addShop.action",
	    		 $("#addShopForm").serialize(),
	    		 function(data){
				   if(data.flag == "0"){ 
					   $.messager.alert('提示框',data.message);
		 	        	$("#resetAddShopButton").click();
		                $("#shopDataListTable").datagrid("load");
		 	        } else{
		 	        	$.messager.alert('提示框',data.message,'error');
		 	        }
		       },'json');
	});
	
	$("#resetAddShopButton").linkbutton({iconCls: 'icon-reset'}).click(function(){
		$("#addShopForm").form("reset");
	});
	
	$("#add_sp_status").combobox({
		url : "${pageContext.request.contextPath }/SystemAction/loadJson4Options.action?key=shopStatusJson",
		valueField : 'id',
		textField : 'text',
		panelHeight:'auto' 
	});
	
	$("#add_sp_id").validatebox({
			required : true,
			validType : 'length[1,10]',
			missingMessage : "店铺编号必须输入",
			invalidMessage : "店铺编号最长为10个字符"
	});
	
	$("#add_sp_name").validatebox({
		required : true,
		missingMessage : "店铺名称必须输入"
	});
	
	$("#add_sp_status").validatebox({
		required : true,
		missingMessage : "店铺状态必选"
	});
	
	$("#add_sp_shopman_phone").validatebox({
		required : false,
		validType : 'length[11,11]',
		invalidMessage : "请输入有效的11位手机号码"
	});
	
	$("#add_sp_shopman_email").validatebox({
		required : false,
		validType : 'email',
		invalidMessage : "请输入有效的Email地址"
	});

});
</script>
<form id="addShopForm" method="post">
	<table class="easyui-bg" style="width:100%;height:100%" >
		<tr>
			<td>店铺编号:</td>
			<td><input type="text" id="add_sp_id" name="addEntity.sp_id"/></td>
		</tr>
		<tr>
			<td>店铺名称:</td>
			<td><input type="text" id="add_sp_name" name="addEntity.sp_name"/></td>
		</tr>
		<tr>
			<td>店铺状态:</td>
			<td><input id="add_sp_status" name="addEntity.sp_status" style="width:155px" value="0"/>
			</td>
		</tr>
		<tr>
			<td>店主:</td>
			<td><input type="text" name="addEntity.sp_shopman"/></td>
		</tr>
		<tr>
			<td>店主手机:</td>
			<td><input type="text" id="add_sp_shopman_phone" name="addEntity.sp_shopman_phone"/></td>
		</tr>
		<tr>
			<td>店主Email:</td>
			<td><input type="text" id="add_sp_shopman_email" name="addEntity.sp_shopman_email"/></td>
		</tr>
		<tr>
			<td>备注:</td>
			<td>
			<textarea name="addEntity.sp_remark" cols=50 rows=5></textarea></td>
		</tr>
		<tr>
		    <td colspan="2" align="center">
		       <a id="addShopButton">保存</a>
			   <a id="resetAddShopButton">重置</a>
		    </td>
		</tr>
	</table>
</form>