<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

$(function(){
	
	$("#resetAddRoleButton").linkbutton({iconCls: 'icon-reset'}).click(function(){
		$("#addRoleForm").form("reset");
	});
	
	$("#addRoleButton").linkbutton({
		 iconCls: 'icon-save'
	}).click(function(){
		if(!$("#addRoleForm").form("validate")){
			 return;
		 }
		 $.post("${pageContext.request.contextPath }/RoleAction/addRole.action",
	    		 $("#addRoleForm").serialize(),
	    		 function(data){
				   if(data.flag == "0"){ 
		 	        	$("#resetAddRoleButton").click();
		                $("#roleDataListTable").datagrid("load");
		 	        } else{
		 	        	$.messager.alert('提示框',data.message,'warning');
		 	        }
		       },'json');
	});
	
	
	$("#re_status").combobox({
							url : "${pageContext.request.contextPath }/SystemAction/loadJson4Options.action?key=roleStatusJson",
							valueField : 'id',
							textField : 'text',
							panelHeight:'auto' 
						});
	
	$("#re_id").validatebox({
		required : true,
		validType : 'length[1,10]',
		missingMessage : "角色编号必须输入",
		invalidMessage : "角色编号最长为10个字符"
	});
	
	$("#re_name").validatebox({
		required : true,
		missingMessage : "角色名称必须输入"
	});
	
	$("#re_status").validatebox({
		required : true,
		missingMessage : "角色状态必选"
	});
	
 });
</script>
<form id="addRoleForm" method="post">
	 <table class="easyui-bg" style="width:100%;height:100%">
	    <colgroup>
	       <col width="30%"/>
	       <col width="70%"/>
	    </colgroup>
		<tr>
			<td>角色编号(<span><font color="red">*必输</font></span>):</td>
			<td><input id="re_id" name="addEntity.re_id"/></td>
		</tr>
		<tr>
		  <td>角色名称(<span><font color="red">*必输</font></span>):</td>
			<td><input id="re_name" name="addEntity.re_name"/></td>
		</tr>
		<tr>
			<td>角色状态(<span><font color="red">*必输</font></span>):</td>
			<td><input id="re_status" name="addEntity.re_status" value="0"/>
			</td>
		</tr>
		<tr>
		    <td colspan="2" align="center">
		       <a href="javascript:void(0)" class="easyui-linkbutton" id="addRoleButton">保存</a>
			   <a href="javascript:void(0)" class="easyui-linkbutton" id="resetAddRoleButton">重置</a>
		    </td>
		</tr>
	</table>
</form>