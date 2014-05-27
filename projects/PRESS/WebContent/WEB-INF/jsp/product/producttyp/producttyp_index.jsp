<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%@ include file="/WEB-INF/jsp/comm/comm.jsp"%>
<script type="text/javascript">
$(function() {
	//产品管理URL前缀
	var prefix = "${pageContext.request.contextPath}/${module}/${submodule}";
	
	//显示查询列表
	$('#table').datagrid({
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				window.location=prefix+"_to_add.action";
			}
		},'-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				if($(":checkbox[checked]").size()==0){
					 $.messager.alert("提示框","请选择记录后再删除","warning");
					return;
				 } 
				 $.messager.confirm('确认框', '您确认删除吗?', function(r){
					 if(r){
						var rows = $('#table').datagrid('getSelections');
						var ids = "1=1";
			            for(var i = 0;i<rows.length;i++){
			            	ids += "&ids="+rows[i].protypid;
			            }
			            window.location = prefix+"_do_delete.action?"+ids;
		             }
	           });
			}
	   },'-',{
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				 if($(":checkbox[checked]").size()!=1){
					 $.messager.alert("提示框","请选择一条记录后再进行修改","warning");
					return;
				 } 
				 var row = $('#table').datagrid('getSelected');
				 window.location = prefix+"_to_update.action?ids="+row.protypid;
			}
		}, '-', {
			text : '查看',
			iconCls : 'icon-search',
			handler : function() {
				if($(":checkbox[checked]").size()!=1){
					 $.messager.alert("提示框","请选择一条记录后再进行查看","warning");
					return;
				} 
				var row = $('#table').datagrid('getSelected');
				window.location=prefix+"_to_view.action?ids="+row.protypid;
			}
		}, '-', {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				$("#table").datagrid({
					url:prefix + "_getListJSON.action"
				});
			}
		}],
		nowrap : false,
		striped : true,
		fit : true,
		url : prefix+'_getListJSON.action',
		sortName : 'protypnam',
		sortOrder : 'desc',
		idField : 'protypnam',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : '产品类型ID',
			field : 'protypid',
			width : 300,
			hidden:true,
			sortable : true
		} ] ],
		columns : [ [  {
			title : '产品类型ID',
			field : 'protypid',
			width : 300,
			hidden:true,
			sortable : true
		} , {
			field : 'protypnam',
			title : '产品类型名称',
			width : 300,
			sortable : true,
			editor : 'text'
		},{
			field : 'sortnum',
			title : '产品类型排序码',
			width : 200,
			sortable : true,
			editor : 'text'
		}] ],
		pagination : true,
		rownumbers : true,
		loadMsg:'正在加载中...'
	});
});
</script>
</head>
<form id="_query_form_" method="post">
<body class="easyui-layout" border="true">
	<div region="north" title="查询条件" style="height:100px;overflow: hidden" border="false">
		<table >
			<colgroup>
				<col width="200" align="right" />
				<col width="300" align="left" />
				<col width="200" align="right" />
				<col width="300" align="left" />
			</colgroup>
			<tr>
			  <td>产品类型名称</td>
			  <td><input type="text" name="queryCondition.protypnam" value="${queryCondition.protypnam }"/></td>
			</tr>
			<tr>
				<td colspan="4" align="center" >
					 <a class="easyui-linkbutton" iconCls="icon-search" id="_query_btn_" href="javascript:void(0)" >查询</a>
					 <a class="easyui-linkbutton" iconCls="icon-cancel" id="_clear_btn_" href="javascript:void(0)" >清空</a>
				</td>
			</tr>
		</table>
	</div>
	<div region="center" title="查询列表"  border="false">
		<table id="table" singleSelect="false"></table>
	</div>
 </body>
</form>
 </html>