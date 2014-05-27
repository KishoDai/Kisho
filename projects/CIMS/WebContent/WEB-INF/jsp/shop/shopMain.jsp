<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function() {
	var shopMainPrefix = "${pageContext.request.contextPath}/ShopAction/";
	$('#shopDataListTable').datagrid({
		toolbar : [ {
			text : '添加',
			iconCls : 'icon-add',
			handler : function(){
				$("#shopInfoTabsDiv").tabs("select", "添加店铺信息");
			}
		},{
			text : '修改',
			iconCls : 'icon-edit',
			handler : function(){
				var row = $('#shopDataListTable').datagrid('getSelected');
				if(row == null){
					$.messager.alert('提示框','请选择一条 记录后再修改!','warning');
					return;
				}
				$("#shopInfoTabsDiv").tabs("select", "修改店铺信息");
			}
		},'-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function(){
				var rows = $('#shopDataListTable').datagrid('getSelections');
				if(rows.length == 0){
					$.messager.alert('提示框','请选择记录后再删除!','warning');
					return;
				}
				$.messager.confirm('确认框', '您确认删除店铺吗?', function(r){
					if(r){
						 var sp_ids = "1=1";
						 for(var i = 0; i < rows.length; i++){
							 sp_ids += "&sp_id=" + rows[i].sp_id;
						 }
						 $.post(shopMainPrefix + "delShops.action",
								 sp_ids,
					    		 function(data){
								   if(data.flag == "0"){ 
									    $.messager.alert('提示框',data.message);
						                $("#shopDataListTable").datagrid("load");
						 	        } else{
						 	        	$.messager.alert('提示框',data.message,'error');
						 	        }
						  },'json');
					}
				});
			}
	   }],
	   onClickRow:function(rowIndex, rowData){
		   var tab = $("#shopInfoTabsDiv").tabs('getSelected');
		   $("#shopInfoTabsDiv").tabs('select',$('#shopInfoTabsDiv').tabs('getTabIndex',tab));
	   },
		fitColumns:true,
		nowrap : false,
		striped : true,
		fit : true,
		singleSelect:true,
		checkOnSelect:true,
		selectOnCheck:true,
		checkbox:true,
		url : shopMainPrefix + 'queryPageList.action',
		sortName : 'sp_id',
		sortOrder : 'desc',
		idField : 'sp_id',
		frozenColumns : [ [ {
			field : 'ck',
			checkbox:true
		}, {
			title : '店铺编号',
			field : 'sp_id',
			width:80,
			hidden:false,
			sortable : true
		} ] ],
		columns : [ [ {
			title : '店铺名称',
			field : 'sp_name',
			width:100,
			sortable : true
		}, {
			title : '店铺状态',
			field : 'sp_status',
			width:60,
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
		}] ],
		pagination : true,
		rownumbers : true,
		queryParams:{},
		loadMsg:'loading...'
	});
	
	$('#queryCondition').searchbox({  
	    searcher:function(value,name){ 
	    	name = (name == 'all' ? '' : name);
	    	$('#shopDataListTable').datagrid('load', {  
	    		'queryEntity.sp_status': name,
    			'queryEntity.sp_id': value,
    			'queryEntity.sp_name':value
	    	});  
	    },  
	    menu:'#queryMenu',  
	    prompt:'请输入店铺编号或者店铺名称',
	    width:400
	});  
	
	$('#shopInfoTabsDiv').tabs({  
		fit:true,
		border:true,
	    onSelect:function(title,index){  
	    	var row = $('#shopDataListTable').datagrid('getSelected');
            if(title == '修改店铺信息'){
            	if(row == null){
            		$('#updateShopDiv').html('请选择左侧一条店铺记录');
    				return;
    			}
            	$.post(shopMainPrefix + 'toUpdateShopPage.action',
       				  'updateEntity.sp_id=' + row.sp_id,
       				   function(data){
       			          $('#updateShopDiv').html(data);
       		           },
       				   'html');
            } else if(title == "设置店铺菜品"){
            	if(row == null){
            		$('#setShopDishDiv').html('请选择左侧一条店铺记录');
    				return;
    			}
            	$.post('${pageContext.request.contextPath}/DishAction/toMainPage.action',
         				  'sp_id=' + row.sp_id,
         				   function(data){
         			          $('#setShopDishDiv').html(data);
         		           },
         				   'html');
            } else if(title == '添加店铺信息'){
            	$.post(shopMainPrefix + 'toAddPage.action',
 					   null,
 					   function(data){
 				          $('#addShopDiv').html(data);
 			           },
 					   'html');
            }
	    }  
	}); 

	
});
</script>

<table style="width: 100%; height: 95%">
	<tr>
		<td valign="top" style="width: 35%">
		    <input id="queryCondition"/> 
			<div id="queryMenu" style="width:120px" title="店铺状态">  
			    <div data-options="name:'all',iconCls:'icon-ok'">所有</div> 
			    <div data-options="name:'0'">正常</div>  
			    <div data-options="name:'1'">停用</div>  
			</div>  
		    <table id="shopDataListTable" title="店铺信息列表"></table>
		</td>
		<td valign="top">
		    <div style="width:500px" id="shopInfoTabsDiv">
				<div title="修改店铺信息" id="updateShopDiv" class="easyui-bg">请选择左侧一条店铺记录</div>
				<div title="设置店铺菜品" id="setShopDishDiv" class="easyui-bg">请选择左侧一条店铺记录</div>
				<div title="添加店铺信息" id="addShopDiv" class="easyui-bg"></div>
		    </div>
		</td>
	</tr>
</table>
