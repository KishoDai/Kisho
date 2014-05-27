<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {

	var withdrawPrefix = "${pageContext.request.contextPath}/WithdrawAction/";
	
	$('#withdrawDataListTable').datagrid({
		nowrap : false,
		striped : true,
		singleSelect:true, 
		fit : true,
		singleSelect:true,
		checkOnSelect:true,
		selectOnCheck:true,
// 		checkbox:true,
		url : withdrawPrefix + 'queryPageList.action',
		sortName : 'wp_status',
		sortOrder : 'asc',
		idField : 'wp_id',
// 		frozenColumns : [ [{
// 			field : 'ck',
// 			checkbox:true
// 		} ] ],
		columns : [ [  {
			title : '卡编号',
			field : 'cd_id',
			width:80,
			hidden:false,
			sortable : true
		},{
			title : '订单编号',
			field : 'or_id',
			width:80,
			sortable : true
		},{
			title : '审批人编号',
			field : 'wp_approve_ur_id',
			width:80,
			sortable : true
		},{
			title : '流程状态',
			field : 'wp_status',
			width:80,
			sortable : true,
			formatter:function(val,row){
				 if (val == 1){  
				    return '等待审核';  
				  } else if(val == 2){  
					return '审核通过';  
				  } else if(val == 4){
					  return "审核拒绝";
				  }
			}
		},{
			title : '流程发起时间',
			field : 'wp_startdatetime',
			width:120,
			sortable : true
		},{
			title : '流程完成时间',
			field : 'wp_donedatetime',
			width:120,
			sortable : true
		}]],
		pagination : true,
		rownumbers : true,
		queryParams:{},
		loadMsg:'loading...',
		view: detailview,  
		detailFormatter:function(index,row){  
			return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';  
		},  
		onExpandRow: function(index,row){  
			$('#ddv-'+index).datagrid({  
				url: withdrawPrefix + 'loadWithdrawOrderDishJsonByOrderId.action?or_id=' + row.or_id,  
				fitColumns:true,  
				singleSelect:true,  
				rownumbers:true,  
				loadMsg:'',  
				height:'auto',  
				columns:[[  
					{field:'dh_name',title:'退菜菜品',width:40},  
					{field:'wod_amount',title:'菜品数量',width:40},
					{field:'wod_balance',title:'单价(元)',width:40},
					{field:'wod_status',title:'菜品状态',width:40,
						formatter:function(val,row){
							  if(val == 1){
								  return '拒绝退菜'; 
							  } else if (val == 2) {  
							      return '退菜中';  
							  } else if(val == 4){
								  return "已退菜";
							  }
						}}  
				]],  
				onResize:function(){  
					$('#withdrawDataListTable').datagrid('fixDetailRowHeight',index);  
				},  
				onLoadSuccess:function(){  
					setTimeout(function(){  
						$('#withdrawDataListTable').datagrid('fixDetailRowHeight',index);  
					},0);  
				}  
			});  
			$('#withdrawDataListTable').datagrid('fixDetailRowHeight',index);  
		}  
	});
	
	$("#addWithdrawDiv").panel({
		title:"发起退菜审核流程"
	});
	
	$("#cd_id").validatebox({
		required : true,
		missingMessage : "卡编号必输",
     });
	
	$("#or_id").validatebox({
		required : true,
		missingMessage : "订单编号必输",
     }).blur(function(){
    	 var or_id = $("#or_id").val();
    	 if(or_id != ''){
    		 $("#addWithdrawDataListTable").datagrid({
    				checkOnSelect:true,
    				selectOnCheck:true,
    		 		url:withdrawPrefix + 'loadOrderDishByOrderId.action',
    				sortName : 'dh_id',
    				idField : 'dh_id',
    				sortOrder : 'asc',
    		 		frozenColumns : [ [{
    		 			field : 'ck',
    		 			checkbox:true
    		 		},{
    		 			field : 'dh_id',
    		 			hidden:true
    		 		}]],
	                columns : [ [ {
						field : 'dh_name',
						title : '菜品',
						width : 80
					},{
						field : 'od_amount',
						title : '菜品数量',
						width : 80,
						formatter:function(val,row){
// 							var id = 'od_amount_' + row;
// 							var idAndName = "id='" + id + "' name='" + id + "'";
// 							var returnVal = "<input " + idAndName + " value='" + val + "'/>";
							
							return val;
						},
						editor:{type:'text'}
					},{
						field : 'od_balance',
						title : '菜品单价',
						width : 80
					}]],
					queryParams : {
						or_id : or_id
					},
					rownumbers : true,
					loadMsg : 'loading...'
					,
					onClickCell : onClickCell
				});
			}
		});

		$("#resetAddWithdrawBtn").linkbutton().click(function() {
			$("#addWithdrawForm").form("reset");
		});

		$("#addWithdrawBtn").linkbutton({
			iconCls : 'icon-save'
		}).click(function() {
			if (!$("#addWithdrawForm").form("validate")) {
				return;
			}
		});
		
		function odAmount(id,oper){
			alert(id + "  " + oper);
		}

		
// 		  $.extend($.fn.datagrid.methods, {  
// 	          editCell: function(jq,param){  
// 	              return jq.each(function(){  
// 	                  var opts = $(this).datagrid('options');  
// 	                  var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));  
// 	                  for(var i=0; i<fields.length; i++){  
// 	                      var col = $(this).datagrid('getColumnOption', fields[i]);  
// 	                      col.editor1 = col.editor;  
// 	                      if (fields[i] != param.field){  
// 	                          col.editor = null;  
// 	                      }  
// 	                  }  
// 	                  $(this).datagrid('beginEdit', param.index);  
// 	                  for(var i=0; i<fields.length; i++){  
// 	                      var col = $(this).datagrid('getColumnOption', fields[i]);  
// 	                      col.editor = col.editor1;  
// 	                  }  
// 	              });  
// 	          }  
// 	      }); 
   $.extend($.fn.datagrid.defaults.editors, {  
	   text: {  
	       init: function(container, options){  
	           var input = $('<input type="checkbox" class="easyui-numberspinner">').appendTo(container);  
	           return input;  
	       },  
	       getValue: function(target){  
	           return $(target).val();  
	       },  
	        setValue: function(target, value){  
	            $(target).val(value);  
	        },  
	        resize: function(target, width){  
	            var input = $(target);  
	            if ($.boxModel == true){  
	                input.width(width - (input.outerWidth() - input.width()));  
	            } else {  
	                input.width(width);  
	            }  
	        }  
	    }  
	});  


		
		var editIndex = undefined;
		function endEditing() {
			if (editIndex == undefined) {
				return true;
			}
			if ($('#addWithdrawDataListTable').datagrid('validateRow', editIndex)) {
				$('#addWithdrawDataListTable').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		
		function onClickCell(index, field) {
			if (endEditing()) {
				$('#addWithdrawDataListTable').datagrid('editCell', {
					index : index,
					field : field
				});
				editIndex = index;
			}
		}

	});
</script>  
<table style="width: 100%; height: 100%">
<form id="addWithdrawForm">
	<tr>
		<td valign="top" style="width: 65%">
			<table id="withdrawDataListTable" title="退菜流程列表"></table>
		</td>
		<td valign="top" >
		    <div id="addWithdrawDiv">
		        <table class="easyui-bg" title="申请退菜">
		           <tr>
		              <td>卡编号(*):</td>
		              <td><input id="cd_id" name="addWithdraw.cd_id"/></td>
		           </tr>
		           <tr>
		              <td>订单编号(*):</td>
		              <td><input id="or_id" name="addWithdraw.od_id"/></td>
		           </tr>
		           <tr>
		             <td colspan="2" style="width:60%">
					 	<table id="addWithdrawDataListTable" class="easyui-datagrid" title="菜品列表" style="height:auto">  
					    </table>  
		             </td>
		           </tr>
		           <tr>
					    <td colspan="2" align="center">
					       <a  id="addWithdrawBtn">提交</a>
						   <a  id="resetAddWithdrawBtn">重置</a>
					    </td>
					</tr>
		        </table>
		    </div>
		</td>
	</tr>
</form>
</table>

