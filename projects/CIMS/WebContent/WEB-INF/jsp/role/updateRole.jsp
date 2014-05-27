<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
   $(function(){
		$("#resetUpdateRoleButton").linkbutton({iconCls: 'icon-reset'}).click(function(){
			$("#updateRoleForm").form("reset");
		});
	   
	   $("#updateRoleButton").linkbutton({
			 iconCls: 'icon-save'
		}).click(function(){
			if(!$("#updateRoleForm").form("validate")){
				return;
			}
			 $.post("${pageContext.request.contextPath }/RoleAction/updateRole.action",
		    		 $("#updateRoleForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			                $("#roleDataListTable").datagrid("load");
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'error');
			 	        }
			       },'json');
		});
		
		$("#re_status").combobox({
			url : "${pageContext.request.contextPath }/SystemAction/loadJson4Options.action?key=roleStatusJson",
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto' 
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
<form id="updateRoleForm" method="post">
    <input type="hidden" name="updateEntity.re_id" value="${updateEntity.re_id }"/>
	<table class="easyui-bg" style="width:100%;height:100%">
	    <colgroup>
	       <col width="30%"/>
	       <col width="70%"/>
	    </colgroup>
		<tr>
			<td>角色编号(<span><font color="red">*必输</font></span>):</td>
			<td><input  disabled value="${updateEntity.re_id }"/></td>
		</tr>
		<tr>
			<td>角色名称(<span><font color="red">*必输</font></span>):</td>
			<td><input id="re_name" name="updateEntity.re_name"  value="${updateEntity.re_name }"/></td>
		</tr>
		<tr>
			<td>角色状态(<span><font color="red">*必输</font></span>):</td>
			<td><input id="re_status" name="updateEntity.re_status" value="${updateEntity.re_status }"/>
			</td>
		</tr>
		<tr>
		    <td colspan="2" align="center">
		       <a  id="updateRoleButton">保存</a>
		       <a  id="resetUpdateRoleButton">重置</a>
		       
		    </td>
		</tr>
	</table>
</form>