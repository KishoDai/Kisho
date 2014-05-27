<%@page import="com.csms.common.utils.SysCode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {

	var adminPrefix = "${pageContext.request.contextPath}/AdminAction/";
	
	$('#adminDataListTable').datagrid({
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function(){
				$.post(adminPrefix + 'toAddPage.action',
						null,
						function(data){
					       $('#operAdminDiv').html(data).panel("setTitle",'添加管理员').panel('open');
				        },'html');
			}
		},'-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler :  function(){
						var row = $('#adminDataListTable').datagrid('getSelected');
						if(row){
							 $.messager.confirm('确认框', '您确认删除该管理员吗?', function(r){
								if(r){
									 $.post(adminPrefix + "delAdmin.action",
											 "operEntity.ur_id=" + row.ur_id,
								    		 function(data){
											   if(data.flag == "0"){ 
												    $.messager.alert('提示框', data.message);
									                $("#adminDataListTable").datagrid("load");
									                $('#operAdminDiv').panel("setTitle",'管理员维护').panel('close');
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
	   }],
	   onClickRow:function(rowIndex, rowData){
		   $.post(adminPrefix + 'toUpdateAdminPage.action',
				  'updateEntity.ur_id=' + rowData.ur_id,
				  function(data){
			          $('#operAdminDiv').html(data).panel("setTitle",'修改管理员信息').panel('open');
		          },'html');
	   },
		nowrap : false,
		striped : true,
		singleSelect:true, 
		fit : true,
		url : adminPrefix + 'queryPageList.action',
		sortName : 'ur_id',
		sortOrder : 'desc',
		idField : '',
		frozenColumns : [ [ {
			title : '管理员编号',
			field : 'ur_id',
			width:100,
			hidden:false,
			sortable : true
		} ] ],
		columns : [ [ {
			title : '管理员名称',
			field : 'ur_name',
			width:140,
			sortable : true
		},{
			title : '管理员状态',
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
		loadMsg:'<%=SysCode.getValue("loadMsg")%>'
	});
	
	$('#operAdminDiv').panel({  
		title:'管理员信息维护',
		height:'100%',
		width:'100%',
		collapsible:true,
		loadingMessage:'<%=SysCode.getValue("loadMsg")%>'
	}).panel('close'); 
	
	
});

</script>  
<table style="width: 100%; height: 100%">
	<tr>
		<td valign="top" style="width: 40%">
			<table id="adminDataListTable" title="应用管理员"></table>
		</td>
		<td valign="top" style="width: 60%">
			<div id="operAdminDiv"></div>
			<div style="width: 100%; height: 100%" id="setRoleDiv"></div>
		</td>
	</tr>
</table>
