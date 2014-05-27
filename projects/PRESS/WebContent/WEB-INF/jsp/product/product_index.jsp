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
				var rows = $('#table').datagrid('getSelections');
				if(rows.length <=0){
					 $.messager.alert("提示框","请选择记录后再删除","warning");
					return;
				 } 
				 $.messager.confirm('确认框', '您确认删除吗?', function(r){
					 if(r){
						var ids = "1=1";
			            for(var i = 0;i<rows.length;i++){
			            	ids += "&ids="+rows[i].proid;
			            }
			            window.location = prefix+"_do_delete.action?"+ids;
		             }
	           });
			}
	   },'-',{
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				var rows = $('#table').datagrid('getSelections');
				 if(rows.length!=1){
					 $.messager.alert("提示框","请选择一条记录后再进行修改","warning");
					return;
				 } 
				 var row = $('#table').datagrid('getSelected');
				 window.location = prefix+"_to_update.action?ids="+row.proid;
			}
		}, '-', {
			text : '查看产品',
			iconCls : 'icon-search',
			handler : function() {
				var rows = $('#table').datagrid('getSelections');
				if(rows.length!=1){
					 $.messager.alert("提示框","请选择一条记录后再进行查看","warning");
					return;
				} 
				var row = $('#table').datagrid('getSelected');
				window.location=prefix+"_to_view.action?ids="+row.proid;
			}
		}, '-', {
			text : '查看产品相关评论',
			iconCls : 'icon-search',
			handler : function() {
				var rows = $('#table').datagrid('getSelections');
				if(rows.length!=1){
					 $.messager.alert("提示框","请选择一条记录后再进行操作","warning");
					return;
				} 
				var row = $('#table').datagrid('getSelected');
				window.location="${pageContext.request.contextPath}/comment/comment_to_index.action?relid="+row.proid+"&reltyp=0";
			}
		}, '-', {
			text : '加为产品类型',
			iconCls : 'icon-add',
			handler : function() {
				var rows = $('#table').datagrid('getSelections');
				if(rows.length <=0){
					 $.messager.alert("提示框","请选择一条记录后再进行操作","warning");
					return;
				} 
				$("#_add_pro_typ_dialog_").window("open");
			}
		}, '-', {
			text : '刷新',
			iconCls : 'icon-reload',
			handler : function() {
				$("#table").datagrid({
					url:prefix+"_getListJSON.action"
				});
			}
		}],
		nowrap : false,
		striped : true,
		fit : true,
		url : prefix+'_getListJSON.action',
		sortName : 'proid',
		sortOrder : 'desc',
		idField : 'proid',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : '产品ID',
			field : 'proid',
			width : 80,
			hidden:true,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'pronam',
			title : '产品名称',
			width : 150,
			sortable : true,
			editor : 'text'
		}, {
			field : 'catnam',
			title : '产品分类',
			width : 150,
			sortable : true,
			editor : 'text'
		}, {
			field : 'views',
			title : '浏览次数',
			width : 100,
			sortable : true,
			editor : 'text'
		},{
			field : 'pricerange',
			title : '价格范围',
			width : 150,
			sortable : true,
			formatter:function(value,rec){
				return rec.pricerange+"元";
			}
		},{
			field : 'isshow',
			title : '是否上架',
			width : 100,
			sortable : true,
			formatter:function(value,rec){
				var ret = '未上架' ;
				if(rec.isshow==1){
					ret = "已上架";
				} 
				return ret;
			}
		},{
			field : 'pubtime',
			title : '上架时间',
			width : 150,
			sortable : true,
			editor : 'text'
		}] ],
		pagination : true,
		rownumbers : true,
		loadMsg:'正在加载中...'
	});
	
	$("#protyp_table").datagrid({
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				var rows = $('#protyp_table').datagrid('getSelections');
				if(rows.length <=0){
					 $.messager.alert("提示框","请选择一条记录后再进行操作","warning");
					return;
				} 
				
				var rows1 = $('#table').datagrid('getSelections');
				if(rows1.length <=0){
					 $.messager.alert("提示框","请选择记录后再删除","warning");
					return;
				 } 
				var ids = "";
	            for(var i = 0;i<rows1.length;i++){
	            	ids += "&ids="+rows1[i].proid;
	            }
				
				var protypids = "1=1";
	            for(var i = 0;i<rows.length;i++){
	            	protypids += "&protypids="+rows[i].protypid;
	            }
	            window.location = prefix+"_do_add_protyp.action?"+protypids+ids;
			}
		}],
		nowrap : false,
		striped : true,
		fit : true,
		url : prefix+'_getListJSON2.action',
		idField : 'protypid',
		frozenColumns : [ [ {
			field : 'ck2',
			checkbox : true
		}, {
			title : 'protypid',
			field : 'protypid',
			width : 80,
			hidden:true,
			sortable : false
		} ] ],
		columns : [ [ {
			field : 'protypnam',
			title : '产品类型名称',
			width : 300,
			sortable : false
		}] ],
		pagination : false,
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
				<td>产品名称:</td>
				<td>
				    <input type="text" 
				           name="queryCondition.pronam"
				           value="${queryCondition.pronam }" 
				           />
				</td>
				<td>产品类别:</td>
				<td>
				   <select name="queryCondition.catid" >
				     ${catalogOptions }
				   </select>
				</td>
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
		<div id="_add_pro_typ_dialog_" closed="true" class="easyui-dialog" title="产品类型" modal="true" iconCls="icon-add" style="width:500px;height:300px;padding:5px;">
		    <div class="easyui-layout" fit="true" >
				<div region="center" border="false" shadow="true">
				   <table id="protyp_table">
	               </table>
				</div>
			</div>
	      </div>
	</div>
 </body>
</form>
 </html>