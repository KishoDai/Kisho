<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
   $(function(){
		$('#roleDataListTable2').datagrid({
			nowrap : false,
			striped : true,
			singleSelect:false, 
			checkOnSelect:true,
			selectOnCheck:true,
			checkbox:true,
			fit : true,
			url : '${pageContext.request.contextPath}/RoleAction/queryPageList.action',
			sortName : 're_id',
			sortOrder : 'desc',
			idField : 're_id',
			frozenColumns : [ [{
				field : 'ck',
				checkbox:true
			}, {
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
		}).datagrid({onLoadSuccess:function(){
			   var operUserRoleRelObj = new Object();
			   operUserRoleRelObj['operUserRoleRel.ur_id'] = $('#userDataListTable').datagrid('getSelected').ur_id;
			   $.post('${pageContext.request.contextPath }/UserAction/queryJson4UserRoleRelByUserId.action',
					   operUserRoleRelObj,
					   function(data){
			              for(var i = 0; i < data.length; i++){
			            	  $('#roleDataListTable2').datagrid("selectRecord",data[i].re_id);
			              };
			           },'json');
			
		},onCheck:function(rowIndex, rowData){
			var addUserRoleRelObj = new Object();
			addUserRoleRelObj['addUserRoleRel.ur_id'] = $('#userDataListTable').datagrid('getSelected').ur_id;
			addUserRoleRelObj['addUserRoleRel.re_id'] = rowData.re_id;
		    $.post('${pageContext.request.contextPath }/UserAction/addUserRoleRel.action',
				   addUserRoleRelObj,
				   function(data){
		              if(data.flag != 0){
		            	  $.messager.alert('提示框', data.message,'error');
		              }
		           },'json');
	    },
	    onUncheck:function(rowIndex, rowData){
	    	var operUserRoleRelObj = new Object();
	    	operUserRoleRelObj['operUserRoleRel.re_id'] = rowData.re_id;
	    	operUserRoleRelObj['operUserRoleRel.ur_id'] = $('#userDataListTable').datagrid('getSelected').ur_id;
		    $.post('${pageContext.request.contextPath }/UserAction/delUserRoleRel.action',
		    		operUserRoleRelObj,
				   function(data){
		              if(data.flag != 0){
		            	  $.messager.alert('提示框', data.message,'error');
		              }
		           },'json');
	    },
	    onCheckAll:function(rows){
	    	var operUserRoleRelsParams = "ur_id=" + $('#userDataListTable').datagrid('getSelected').ur_id;
	    	for(var i = 0; i < rows.length; i++){
	    		operUserRoleRelsParams += "&re_id=" + rows[i].re_id;
	    	}
	    	$.post('${pageContext.request.contextPath }/UserAction/addUserRoleRels.action',
	    			operUserRoleRelsParams,
				   function(data){
		              if(data.flag != 0){
		            	  $.messager.alert('提示框', data.message,'error');
		              }
		           },'json');
	    },
	    onUncheckAll:function(rows){
	    	var operUserRoleRelsParams = "ur_id=" + $('#userDataListTable').datagrid('getSelected').ur_id;
	    	for(var i = 0; i < rows.length; i++){
	    		operUserRoleRelsParams += "&re_id=" + rows[i].re_id;
	    	}
	    	$.post('${pageContext.request.contextPath }/UserAction/delUserRoleRels.action',
	    			operUserRoleRelsParams,
				   function(data){
		              if(data.flag != 0){
		            	  $.messager.alert('提示框', data.message,'error');
		              }
		           },'json');
	    }});
	   
   });
</script>
<div class="easyui-bg" style="width:100%;height:90%">
   <table id="roleDataListTable2"></table>
</div>
