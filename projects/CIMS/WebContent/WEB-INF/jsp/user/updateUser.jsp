<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
   $(function(){
	   
	   $("#resetUpdateUserSaveBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
		   $("#updateUserForm").form("reset");
	   });
			   
	   $("#updateUserSaveBtn").linkbutton({
			 iconCls: 'icon-save'
		}).click(function(){
			if(!$("#updateUserForm").form("validate")){
				return;
			}
			 $.post("${pageContext.request.contextPath }/UserAction/updateUser.action",
		    		 $("#updateUserForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			 	        	$.messager.alert('提示框',data.message);
			                $("#userDataListTable").datagrid("load");
			 	        } else {
			 	        	$.messager.alert('提示框',data.message,'error');
			 	        }
			       },'json');
		});
		
		$("#ur_status").combobox({
			url : "${pageContext.request.contextPath }/SystemAction/loadJson4Options.action?key=userStatusJson",
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto' 
		});

		$('#sp_id').combobox({  
			url:'${pageContext.request.contextPath }/ShopAction/loadJson4Shop.action',  
			valueField:'id',  
			textField:'text',
			panelHeight:'auto' 
		});
		
		$("#ur_name").validatebox({
			required : true,
			missingMessage : "用户名称必须输入",
	     });
		
   });
</script>
<form id="updateUserForm" method="post">
    <input type="hidden" name="updateEntity.ur_id" value="${updateEntity.ur_id }"/>
	<table class="easyui-bg" style="width:100%;height:100%">
	    <colgroup>
	       <col width="30%"/>
	       <col width="70%"/>
	    </colgroup>
		<tr>
			<td>用户编号(<span><font color="red">*必输</font></span>):</td>
			<td><input type="text" disabled value="${updateEntity.ur_id }"/></td>
		</tr>
		<tr>
			<td>用户名称(<span><font color="red">*必输</font></span>):</td>
			<td><input id="ur_name" name="updateEntity.ur_name" value="${updateEntity.ur_name }"/></td>
		</tr>
		<tr>
			<td>状态(<span><font color="red">*必输</font></span>):</td>
			<td><input id="ur_status" name="updateEntity.ur_status"  value="${updateEntity.ur_status }"/></td>
		</tr>
		<tr>
			<td>店铺(<span><font color="red">*必输</font></span>):</td>
			<td><input id="sp_id" name="updateEntity.sp_id" value="${updateEntity.sp_id }"/></td>
		</tr>
		<tr>
		    <td colspan="2" align="center">
		       <a id="updateUserSaveBtn">保存</a>
		       <a id="resetUpdateUserSaveBtn">重置</a>
		    </td>
		</tr>
	</table>
</form>