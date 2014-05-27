<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%@ include file="../comm/comm.jsp"%>
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
			            	ids += "&ids="+rows[i].userid;
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
				 window.location = prefix+"_to_update.action?ids="+row.userid;
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
				window.location=prefix+"_to_view.action?ids="+row.userid;
			}
		}, '-', {
			text : '查看用户评论',
			iconCls : 'icon-search',
			handler : function() {
				var rows = $('#table').datagrid('getSelections');
				if(rows.length !=1){
					 $.messager.alert("提示框","请选择一条记录后再进行操作","warning");
					return;
				} 
				var row = $('#table').datagrid('getSelected'); 
				window.location="${pageContext.request.contextPath}/comment/comment_to_index.action?userid="+row.userid;
			}
		}],
		nowrap : false,
		striped : true,
		fit : true,
		url : prefix+'_getListJSON.action',
		sortName : 'userid',
		sortOrder : 'desc',
		idField : 'userid',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : '用户ID',
			field : 'userid',
			width : 100,
			hidden:true,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'userid',
			title : '用户ID',
			width : 100,
			sortable : true,
			editor : 'text'
		}, {
			field : 'nicknam',
			title : '昵称',
			width : 100,
			sortable : true,
			editor : 'text'
		},{
			field : 'cellphone',
			title : '手机',
			width : 100,
			sortable : true,
			editor : 'text'
		},{
			field : 'telephone',
			title : '固话',
			width : 100,
			sortable : true,
			editor : 'text'
		},{
			field : 'eamil',
			title : 'Email',
			width : 100,
			sortable : true,
			editor : 'text'
		},{
			field : 'qq',
			title : 'QQ',
			width : 100,
			sortable : true,
			editor : 'text'
		},{
			field : 'lvl',
			title : '级别',
			width : 100,
			sortable : true,
			editor : 'text'
		},{
			field : 'islogout',
			title : '是否被注销',
			width : 100,
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
<input type="hidden" name="queryCondition.cattyp" value="${cattyp }"/>
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