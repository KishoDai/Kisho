<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {

	var userPrefix = "${pageContext.request.contextPath}/UserAction/";
	
	$('#userDataListTable').datagrid({
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function(){
				$.post(userPrefix + 'toAddUserPage.action',
						null,
						function(data){
					       $('#operUserDiv').html(data).panel("setTitle",'添加用户').panel('open');
				        },'html');
				$('#setRoleDiv').panel('close');
			}
		},'-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler :  function(){
						var row = $('#userDataListTable').datagrid('getSelected');
						if(row){
							 $.messager.confirm('确认框', '您确认删除该用户吗?', function(r){
								if(r){
										 var userObj = new Object();
										 userObj['operUser.ur_id'] = row.ur_id;
										 $.post(userPrefix + "delUser.action",
												 userObj,
									    		 function(data){
												   if(data.flag == "1"){ 
										                $("#userDataListTable").datagrid("load");
										                $('#operUserDiv').panel("setTitle",'用户维护').panel('close');
										                $('#setRoleDiv').panel("setTitle",'设置用户').panel('close');
										 	        } else{
										 	        	$.messager.alert('提示框',data.message,'warning');
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
							  'operUser.ur_id=' + row.ur_id,
							  function(data){
						          if(data.flag != '1'){
						        	  $.messager.alert('提示框',data.message,'warning');
						          } else {
						        	  $.messager.show({  
						        		      title:'提示',  
						        		      msg:'密码恢复成功',  
						        		      showType:'slide',
						        		      timeout:1000,
						        		      style:{  
						        		          right:'',  
						        		          top:document.body.scrollTop+document.documentElement.scrollTop,  
						        		          bottom:''  
						        		      }  
						        		  }); 

						          }
					          },'json');
				}else {
					$.messager.alert('提示框','请选择一点记录后再恢复密码!','warning');
				}
			}}],
	   onClickRow:function(rowIndex, rowData){
		   $.post(userPrefix + 'toUpdateUserPage.action',
				  'updateUser.ur_id=' + rowData.ur_id,
				  function(data){
			          $('#operUserDiv').html(data).panel("setTitle",'修改用户信息').panel('open');
		          },'html');
		   $.post(userPrefix + 'toUserRolePage.action',
					  'updateUser.ur_id=' + rowData.ur_id,
					  function(data){
				          $('#setRoleDiv').html(data).panel("setTitle",'设置用户角色').panel('open');
			          },'html');
	   },
		nowrap : false,
		striped : true,
		singleSelect:true, 
		fit : true,
		url : userPrefix + 'queryPageList.action',
		sortName : 'ur_id',
		sortOrder : 'desc',
		idField : '',
		frozenColumns : [ [ {
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
	
	$('#operUserDiv').panel({  
		title:'用户信息维护',
		height:'100%',
		width:'100%',
		collapsible:true,
		loadingMessage:'loading...'
	}).panel('close'); 
	
	$('#setRoleDiv').panel({  
		title:'设置用户角色',
		height:'100%',
		width:'100%',
		collapsible:true,
		loadingMessage:'loading...'
	}).panel('close'); 
	
});

</script>  
<table style="width: 100%; height: 100%">
	<tr>
		<td valign="top" style="width: 40%">
			<table id="userDataListTable" title="应用用户"></table>
		</td>
		<td valign="top" style="width: 60%">
			<div id="operUserDiv"></div>
			<div style="width: 100%; height: 100%" id="setRoleDiv"></div>
		</td>
	</tr>
</table>
