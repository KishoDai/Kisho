<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
   $(function(){
	   var adminPrefix = "${pageContext.request.contextPath }/AdminAction/";
	   
	   $("#resetUpdateAdminSaveBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
		   $("#updateAdminForm").form("reset");
	   });
			   
	   $("#updateAdminSaveBtn").linkbutton({
			 iconCls: 'icon-save'
		}).click(function(){
			if(!$("#updateAdminForm").form("validate")){
				return;
			}
			 $.post(adminPrefix + "updateAdmin.action",
		    		 $("#updateAdminForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
						    $.messager.alert("提示框", data.message);
			                $("#adminDataListTable").datagrid("load");
			 	        } else {
			 	        	$.messager.alert('提示框',data.message);
			 	        }
			       },'json');
		});
		
		$("#update_ur_status").combobox({
			url : "${pageContext.request.contextPath }/SystemAction/loadJson4Options.action?key=userStatusJson",
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto' 
		});

		$("#update_ur_name").validatebox({
			required : true,
			missingMessage : "管理员名称必须输入",
	     });
		
   });
</script>
<form id="updateAdminForm" method="post">
    <input type="hidden" name="updateEntity.ur_id" value="${updateEntity.ur_id }"/>
	<table class="easyui-bg" style="width:100%;height:100%">
	    <colgroup>
	       <col width="30%"/>
	       <col width="70%"/>
	    </colgroup>
		<tr>
			<td>管理员编号(<span><font color="red">*必输</font></span>):</td>
			<td><input type="text" disabled value="${updateEntity.ur_id }"/></td>
		</tr>
		<tr>
			<td>管理员名称(<span><font color="red">*必输</font></span>):</td>
			<td><input id="update_ur_name" name="updateEntity.ur_name" value="${updateEntity.ur_name }"/></td>
		</tr>
		<tr>
			<td>状态(<span><font color="red">*必输</font></span>):</td>
			<td><input id="update_ur_status" name="updateEntity.ur_status"  value="${updateEntity.ur_status }"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center">
		       <a id="updateAdminSaveBtn">保存</a>
		       <a id="resetUpdateAdminSaveBtn">重置</a>
		    </td>
		</tr>
	</table>
</form>