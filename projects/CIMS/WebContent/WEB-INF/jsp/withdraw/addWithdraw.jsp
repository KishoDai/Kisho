<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">

$(function(){
	
	$("#resetAddUserBtn").linkbutton().click(function(){
		$("#addUserForm").form("reset");
	});
	
	$("#addUserSaveBtn").linkbutton({
		 iconCls: 'icon-save'
	}).click(function(){
		if(!$("#addUserForm").form("validate")){
			 return;
		 }
		 $.post("${pageContext.request.contextPath }/UserAction/addUser.action",
	    		 $("#addUserForm").serialize(),
	    		 function(data){
				   if(data.flag == "1"){ 
		 	        	$("#resetAddUserBtn").click();
		                $("#userDataListTable").datagrid("load");
		 	        } else {
		 	        	$.messager.alert('提示框',data.message,'warning');
		 	        }
		       },'json');
	});
	
	
	$("#add_ur_status").combobox({
		url : "${pageContext.request.contextPath }/SystemAction/loadJson4Options.action?key=userStatusJson",
		valueField : 'id',
		textField : 'text',
		panelHeight:'auto' ,
		onLoadSuccess:function(){
			var o1 = $('#add_ur_status').combobox('getData')[0];
			if(o1){
				$('#add_ur_status').combobox('select',o1.id);
			}}
	});
	
	$('#add_sp_id').combobox({  
	    url:'${pageContext.request.contextPath }/ShopAction/loadJson4Shop.action',  
	    valueField:'id',  
	    textField:'text',
	    panelHeight:'auto',
	    onLoadSuccess:function(){
			var o1 = $('#add_sp_id').combobox('getData')[0];
			if(o1){
				$('#add_sp_id').combobox('select',o1.id);
			}}
	});
	
	$("#add_ur_id").validatebox({
		required : true,
		validType : 'length[1,10]',
		missingMessage : "用户编号必须输入",
		invalidMessage : "用户编号最长为10个字符"
     });
	
	$("#add_ur_name").validatebox({
		required : true,
		missingMessage : "用户名称必须输入",
     });
	
});
</script>
<form id="addUserForm" method="post">
	 <table class="easyui-bg" style="width:100%;height:100%">
	    <colgroup>
	       <col width="30%"/>
	       <col width="70%"/>
	    </colgroup>
		<tr>
			<td>用户编号(<span><font color="red">*必输</font></span>):</td>
			<td><input id="add_ur_id" name="addUser.ur_id"/></td>
		</tr>
		<tr>
		  <td>用户名称(<span><font color="red">*必输</font></span>):</td>
			<td><input id="add_ur_name" name="addUser.ur_name"/></td>
		</tr>
		<tr>
			<td>用户状态(<span><font color="red">*必输</font></span>):</td>
			<td><input id="add_ur_status" name="addUser.ur_status" style="width:155px" value="0"/></td>
		</tr>
		<tr>
			<td>店铺(<span><font color="red">*必输</font></span>):</td>
			<td><select id="add_sp_id" name="addUser.sp_id" style="width:155px"></select>
			</td>
		</tr>
		<tr>
		    <td colspan="2" align="center">
		       <a  id="addUserSaveBtn">保存</a>
			   <a  id="resetAddUserBtn">重置</a>
		    </td>
		</tr>
	</table>
</form>