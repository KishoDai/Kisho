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
			text : '产品类型名称维护',
			iconCls : 'icon-edit',
			handler : function() {
				window.location = "${pageContext.request.contextPath}/product/producttyp_to_index.action";
			}
	   },'-', {
			text : '取消产品类型',
			iconCls : 'icon-cancel',
			handler : function() {
				var rows = $('#table').datagrid('getSelections');
				if(rows.length==0){
					 $.messager.alert("提示框","请选择记录后再取消","warning");
					return;
				 } 
				 $.messager.confirm('确认框', '您确认取消吗?', function(r){
					 if(r){
						var ids = "1=1";
			            for(var i = 0;i<rows.length;i++){ 
			            	ids += "&ids="+rows[i].relid;
			            }
			            window.location = prefix+"_do_delete.action?"+ids;
		             }
	           });
			}
	   }],
		nowrap : false,
		striped : true,
		fit : true,
		url : prefix+'_getListJSON.action',
		sortName : 'protypnam',
		sortOrder : 'desc',
		idField : 'relid',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : '关系ID',
			field : 'relid',
			width : 80,
			hidden:true,
			sortable : false
		} ] ],
		columns : [ [ {
			field : 'protypnam',
			title : '产品类型',
			width : 150,
			sortable : true,
			editor : 'text'
		},{
			field : 'pronam',
			title : '产品名称',
			width : 150,
			sortable : true,
			editor : 'text'
		}, {
			field : 'catnam',
			title : '产品分类',
			width : 150,
			sortable : false,
			editor : 'text'
		}, {
			field : 'views',
			title : '浏览次数',
			width : 100,
			sortable : false,
			editor : 'text'
		},{
			field : 'pricerange',
			title : '价格范围',
			width : 150,
			sortable : false,
			formatter:function(value,rec){
				return rec.pricerange+"元";
			}
		},{
			field : 'isshow',
			title : '是否上架',
			width : 100,
			sortable : false,
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
			sortable : false,
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
				<td>产品名称:</td>
				<td>
				    <input type="text" 
				           name="queryCondition.pronam"
				           value="${queryCondition.pronam }" 
				           />
				</td>
				<td>产品类型:</td>
				<td>
				   <select name="queryCondition.protypid" >
				     ${protypOptions }
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
	</div>
 </body>
</form>
 </html>