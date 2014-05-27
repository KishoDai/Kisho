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
	
	$('#table').datagrid({
		toolbar : [ {
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
			            	ids += "&ids="+rows[i].comid;
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
		sortName : 'comid',
		sortOrder : 'desc',
		idField : 'comid',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			title : 'comid',
			field : 'comid',
			width : 80,
			hidden:true,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'userid',
			title : '用户ID',  
			width : 150,
			sortable : true
		}, {
			field : 'nicknam',
			title : '昵称',
			width : 150,
			sortable : true,
			editor : 'text'
		}, {
			field : 'reltyp',
			title : '评论类型',
			width : 100,
			sortable : true,
			formatter:function(value,rec){
				var ret ;
				if(value == '0'){
					ret = "产品";
				} else if(value == '1'){
					ret = '资讯';
				} else if(value = '2'){
					ret = '活动';
				} else if(value = '3'){
					ret = '健康指南';
				}
				return ret; 
			}
		},{
			field : 'comtime',
			title : '评论时间',
			width : 150,
			sortable : true
		},{
			field:'comcontent',
			title:'评论内容',
			width:300,
			sortable:false
		}] ],
		pagination : true,
		rownumbers : true,
		loadMsg:'正在加载中...'
	});
	

});

</script>
</head>
<body class="easyui-layout" border="true">
	<div region="center" title="查询列表"  border="false">
		<table id="table" singleSelect="false"></table>
    </div>
 </body>
 </html>
