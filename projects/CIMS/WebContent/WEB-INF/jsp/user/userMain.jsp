<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {

	var userPrefix = "${pageContext.request.contextPath}/UserAction/";
	
	$('#userDataListTable').datagrid({
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function(){
				$("#userInfoTabsDiv").tabs("select", "添加用户信息");
			}
		},{
			text : '修改',
			iconCls : 'icon-edit',
			handler : function(){
				var row = $('#userDataListTable').datagrid('getSelected');
				if(row == null){
					$.messager.alert('提示框','请选择一条 记录后再修改!','warning');
					return;
				}
				$("#userInfoTabsDiv").tabs("select", "修改用户信息");
			}
		},'-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler :  function(){
				var row = $('#userDataListTable').datagrid('getSelected');
				if(row){
					 $.messager.confirm('确认框', '您确认删除该用户吗?', function(r){
						if(r){
							 $.post(userPrefix + "delUser.action",
									 'operEntity.ur_id=' + row.ur_id,
						    		 function(data){
									   if(data.flag == "0"){ 
							 	        	$.messager.alert('提示框',data.message);
							                $("#userDataListTable").datagrid("load");
							                $('#setUserRoleDiv').html("请选择左侧一条店铺记录");
							                $('#updateUserDiv').html("请选择左侧一条店铺记录");
							 	        } else{
							 	        	$.messager.alert('提示框',data.message,'error');
							 	        }
							       },'json');
						}
					 });
				} else {
					$.messager.alert('提示框','请选择一点记录后再删除!','warning');
				}
			}
	   },'-', {
			text : '恢复初始密码',
			handler :  function(){
				var row = $('#userDataListTable').datagrid('getSelected');
				if(row){
					$.post(userPrefix + 'recoverInitPwd.action',
							  'operEntity.ur_id=' + row.ur_id,
							  function(data){
						          if(data.flag != '0'){
						        	  $.messager.alert('提示框',data.message,'error');
						          } else {
						        	  $.messager.alert('提示框',data.message);
						          }
					          },'json');
				} else {
					$.messager.alert('提示框','请选择一点记录后再恢复密码!','warning');
				}
			}}],
	   onClickRow:function(rowIndex, rowData){
		   var tab = $("#userInfoTabsDiv").tabs('getSelected');
		   $("#userInfoTabsDiv").tabs('select',$('#userInfoTabsDiv').tabs('getTabIndex',tab));
	   },
		nowrap : false,
		striped : true,
		singleSelect:true, 
		fit : true,
		singleSelect:true,
		checkOnSelect:true,
		selectOnCheck:true,
		checkbox:true,
		url : userPrefix + 'queryPageList.action',
		sortName : 'ur_id',
		sortOrder : 'desc',
		idField : 'ur_id',
		frozenColumns : [ [{
			field : 'ck',
			checkbox:true
		}, {
			title : '用户编号',
			field : 'ur_id',
			width:100,
			hidden:false,
			sortable : true
		} ] ],
		columns : [ [ {
			title : '用户名称',
			field : 'ur_name',
			width:140,
			sortable : true
		},{
			title : '用户状态',
			field : 'ur_status',
			width:100,
			sortable : true,
			formatter:function(val,row){
				 if (val == 0){  
				    return '正常';  
				  } else if(val == 1){  
					return '<span style="color:red;">停用</span>';  
				  } else {
					  return val;
				  }
			}
		}]],
		pagination : true,
		rownumbers : true,
		queryParams:{},
		loadMsg:'loading...'
	});
	
	$('#userInfoTabsDiv').tabs({  
		fit:true,
		border:true,
	    onSelect:function(title,index){  
	    	var row = $('#userDataListTable').datagrid('getSelected');
            if(title == '修改用户信息'){
            	if(row == null){
            		$('#updateUserDiv').html('请选择左侧一条店铺记录');
    				return;
    			}
            	$.post(userPrefix + 'toUpdateUserPage.action',
       				  'updateEntity.ur_id=' + row.ur_id,
       				   function(data){
       			          $('#updateUserDiv').html(data);
       		           },
       				   'html');
            } else if(title == "设置用户角色"){
            	if(row == null){
            		$('#setUserRoleDiv').html('请选择左侧一条店铺记录');
    				return;
    			}
            	$.post(userPrefix + 'toUserRolePage.action',
         				  'ur_id=' + row.ur_id,
         				   function(data){
         			          $('#setUserRoleDiv').html(data);
         		           },
         				   'html');
            } else if(title == '添加用户信息'){
            	$.post(userPrefix + 'toAddPage.action',
						null,
						function(data){
					       $('#addUserDiv').html(data);
				        },'html');
            }
	    }  
	}); 
	
	$('#userQueryCondition').searchbox({  
	    searcher:function(value,name){ 
	    	name = (name == 'all' ? '' : name);
	    	$('#userDataListTable').datagrid('load', {  
	    		'queryEntity.ur_status': name,
    			'queryEntity.ur_id': value,
    			'queryEntity.ur_name':value
	    	});  
	    },  
	    menu:'#userQueryMenu',  
	    prompt:'请输入用户编号或者用户名称',
	    width:400
	});  
	
});

</script>  
<table style="width: 100%; height: 95%">
	<tr>
		<td valign="top" style="width: 35%">
		    <input id="userQueryCondition"/> 
			<div id="userQueryMenu" style="width:120px" title="用户状态">  
			    <div data-options="name:'all',iconCls:'icon-ok'">所有</div> 
			    <div data-options="name:'0'">正常</div>  
			    <div data-options="name:'1'">停用</div>  
			</div>  
			<table id="userDataListTable" title="应用用户列表"></table>
		</td>
		<td valign="top">
		    <div style="width:500px" id="userInfoTabsDiv">
				<div title="修改用户信息" id="updateUserDiv" class="easyui-bg">请选择左侧一条店铺记录</div>
				<div title="设置用户角色" id="setUserRoleDiv" class="easyui-bg">请选择左侧一条店铺记录</div>
				<div title="添加用户信息" id="addUserDiv" class="easyui-bg"></div>
		    </div>
		</td>
	</tr>
</table>
