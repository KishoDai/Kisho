<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

$(function(){
	var adminPrefix = "${pageContext.request.contextPath }/AdminAction/";
	
	$("#resetAddAdminBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
		$("#addAdminForm").form("reset");
	});
	
	$("#addAdminSaveBtn").linkbutton({
		 iconCls: 'icon-save'
	}).click(function(){
		if(!$("#addAdminForm").form("validate")){
			 return;
		 }
		 $.post(adminPrefix + "addAdmin.action",
	    		 $("#addAdminForm").serialize(),
	    		 function(data){
				   if(data.flag == "0"){ 
					    $.messager.alert('提示框','添加管理员成功!');
		 	        	$("#resetAddAdminBtn").click();
		                $("#adminDataListTable").datagrid("load");
		 	        } else {
		 	        	$.messager.alert('提示框',data.message,'warning');
		 	        }
		       },'json');
	});
	
	
	$("#add_ur_status").combobox({
		url : "${pageContext.request.contextPath }/SystemAction/loadJson4Options.action?key=userStatusJson",
		valueField : 'id',
		textField : 'text',
		panelHeight:'auto' 
	});
	
	$("#add_ur_id").validatebox({
		required : true,
		validType : 'length[1,10]',
		missingMessage : "管理员编号必须输入",
		invalidMessage : "管理员编号最长为10个字符"
     });
	
	$("#add_ur_name").validatebox({
		required : true,
		missingMessage : "管理员名称必须输入",
     });
	
});
</script>
<form id="addAdminForm" method="post">
	 <table class="easyui-bg" style="width:100%;height:100%">
	    <colgroup>
	       <col width="30%"/>
	       <col width="70%"/>
	    </colgroup>
		<tr>
			<td>管理员编号(<span><font color="red">*必输</font></span>):</td>
			<td><input id="add_ur_id" name="addEntity.ur_id"/></td>
		</tr>
		<tr>
		  <td>管理员名称(<span><font color="red">*必输</font></span>):</td>
			<td><input id="add_ur_name" name="addEntity.ur_name"/></td>
		</tr>
		<tr>
			<td>管理员状态(<span><font color="red">*必输</font></span>):</td>
			<td><input id="add_ur_status" name="addEntity.ur_status" style="width:155px" value="0"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center">
		       <a  id="addAdminSaveBtn">保存</a>
			   <a  id="resetAddAdminBtn">重置</a>
		    </td>
		</tr>
	</table>
</form>