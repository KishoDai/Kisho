<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {

	var rolePrefix = "${pageContext.request.contextPath}/RoleAction/";
	
	$('#roleDataListTable').datagrid({
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function(){
				$.post(rolePrefix + 'toAddPage.action',
						null,
						function(data){
					$('#operRoleDiv').html(data).panel("setTitle",'添加角色').panel('open')
				},'html');
				$('#setFuncDiv').panel('close');
			}
		},'-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler :  function(){
				 $.messager.confirm('确认框', '您确认删除该角色吗?', function(r){
					if(r){
						var row = $('#roleDataListTable').datagrid('getSelected');
						if(row){
							 $.post(rolePrefix + "deleteRole.action",
									 "operEntity.re_id=" + row.re_id,
						    		 function(data){
									   if(data.flag == "0"){ 
							                $("#roleDataListTable").datagrid("load");
							                $('#operRoleDiv').panel("setTitle",'角色维护').panel('close');
							                $('#setFuncDiv').panel("setTitle",'设置功能').panel('close');
							 	        } else{
							 	        	$.messager.alert('提示框',data.message,'warning');
							 	        }
							       },'json');
						} else {
							$.messager.alert('提示框','请选择一点记录后再删除!','warning');
						}
					}
				 });
			}
	   }],
	   onClickRow:function(rowIndex, rowData){
		   $.post(rolePrefix + 'toUpdateRolePage.action',
				  'updateEntity.re_id=' + rowData.re_id,
					function(data){
				$('#operRoleDiv').html(data).panel("setTitle",'修改角色信息').panel('open');
			},'html');
		   $.post(rolePrefix + 'toRoleFuncPage.action',
					  'updateEntity.re_id=' + rowData.re_id,
						function(data){
					$('#setFuncDiv').html(data).panel("setTitle",'设置角色功能').panel('open');
			},'html');
	   },
		nowrap : false,
		striped : true,
		singleSelect:true, 
		fit : true,
		url : rolePrefix + 'queryPageList.action',
		sortName : 're_id',
		sortOrder : 'desc',
		idField : '',
		frozenColumns : [ [ {
			title : '角色编号',
			field : 're_id',
			width:100,
			hidden:false,
			sortable : true
		} ] ],
		columns : [ [ {
			title : '角色名称',
			field : 're_name',
			width:140,
			sortable : true
		},{
			title : '角色状态',
			field : 're_status',
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
	
	$('#operRoleDiv').panel({  
		title:'角色信息维护',
		height:'100%',
		width:'100%',
		collapsible:true,
		loadingMessage:'loading...'
	}).panel('close'); 
	
	$('#setFuncDiv').panel({  
		title:'设置角色功能',
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
			<table id="roleDataListTable" title="应用角色列表"></table>
		</td>
		<td valign="top" style="width: 60%">
			<div id="operRoleDiv"></div>
			<div style="width: 100%; height: 100%" id="setFuncDiv"></div>
		</td>
	</tr>
</table>
