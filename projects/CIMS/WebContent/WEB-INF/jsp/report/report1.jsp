<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {

	var reportPrefix = "${pageContext.request.contextPath}/ReportAction/";
	
	$('#report1DataListTable').datagrid({
		nowrap : false,
		striped : true,
		singleSelect:true, 
		fit : true,
		singleSelect:true,
		sortName : 'or_datetime',
		sortOrder : 'asc',
		pagination : true,
		rownumbers : true,
		queryParams:{},
		loadMsg:'loading...'
	});
	
	
	$("#queryReport1Div").panel({
			title : "查询条件",
			collapsible:true
		});
	
	var date = new Date();
	var currentYmdDate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
	var currentYmDate = date.getFullYear() + "-" + (date.getMonth() + 1) ;
	
	$('#startDate').datebox({  
		   required:true,
		   missingMessage:'起始日期必输',
		   formatter:function(date){
			   return date.getFullYear() +'-' + (date.getMonth() + 1) + '-' + date.getDate();
		   }
	}).datebox('setValue', currentYmdDate);
	
	$('#endDate').datebox({  
		   required:true,
		   missingMessage:'结束日期必输',
		   formatter:function(date){
			   return date.getFullYear() +'-' + (date.getMonth() + 1) + '-'+date.getDate();
		   }
	}).datebox('setValue', currentYmdDate);
	
	$('#dateType').combobox({
		panelHeight:'auto',
		onSelect:function(record){
			if(record.value == '0'){
				$('#startDate').datebox({formatter:function(date){
					   return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
				   }}).datebox('setValue',currentYmdDate);
				$('#endDate').datebox({formatter:function(date){
					   return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
				   }}).datebox('setValue',currentYmdDate);
			} else if(record.value = '1'){
				$('#startDate').datebox({formatter:function(date){
					   return date.getFullYear() + '-' + (date.getMonth() + 1);
				   }}).datebox('setValue',currentYmDate);
				$('#endDate').datebox({formatter:function(date){
					   return date.getFullYear() + '-' + (date.getMonth() + 1);
				   }}).datebox('setValue',currentYmDate);;
			}
		}
	});

	$('#sortType').combobox({
		panelHeight:'auto'
	});	
	$('#excelType').combobox({
		panelHeight:'auto'
	});	
	
	$('#queryReport1Btn').linkbutton({iconCls:'icon-search'}).click(function(){
		var columns = [ {
			title : '时间',
			field : 'or_datetime',
			width:100,
			sortable : true
		},{
			title : '店铺编号',
			field : 'sp_id',
			width:80,
			sortable : true
		},{
			title : '店铺名称',
			field : 'sp_name',
			width:120,
			sortable : true
		}];
		if($('#sortType').combobox('getValue') != "0"){
			columns = columns.concat([{
				title : '菜品分类编号',
				field : 'dh_parentid',
				width:80,
				sortable : true
			},{
				title : '菜品分类名称',
				field : 'dh_parentname',
				width:120,
				sortable : true
			}]);
		}
       if($('#sortType').combobox('getValue') == "2"){
			columns = columns.concat([{
				title : '菜品编号',
				field : 'dh_id',
				width:80,
				sortable : true
			},{
				title : '菜品名称',
				field : 'dh_name',
				width:120,
				sortable : true
			}]);
		}
		columns = columns.concat([{
			title : '营业额(元)',
			field : 'totalMoney',
			width:100,
			sortable : true
		}]);
        $('#report1DataListTable').datagrid({
    		columns : [columns],
			url : reportPrefix + 'queryReport1PageList.action?' + $('#queryReport1Form').serialize()
		});
	});

	$('#exportReport1ToExcelBtn').linkbutton({iconCls:'icon-excel'}).click(
		function() {
			$('#exportReport1ToExcelBtn').attr('href', reportPrefix + 'exportReport1ToExcel.action?' + $('#queryReport1Form').serialize());
	});

});
</script>  
<table style="width: 100%; height: 100%">
    <tr>
		<td valign="top">
		   <div id="queryReport1Div">
		   <form id="queryReport1Form">
			   <table class="easyui-bg">
			       <tr>
			          <td>
					            日期方式:<select id="dateType" name="rc.dateType">
					             <option value="0">0-按天</option>
					             <option value="1">1-按月</option>
					            </select>
					            起始日期：<input id="startDate" name="rc.startDate"/>
					            结束日期：<input id="endDate" name="rc.endDate"/>
			          </td>
			       </tr>
			       <tr>
			           <td>
					            种类方式:<select id="sortType" name="rc.sortType">
					             <option value="0">0-按店铺</option>
					             <option value="1">1-按菜品分类</option>
					             <option value="2">2-按菜品</option>
					            </select>
			           </td>
			       </tr>
			       <tr>
			          <td align="center">
			             <a id="queryReport1Btn">查询</a>
			             <a id="exportReport1ToExcelBtn">导出excel</a>(<select id="excelType" name="rc.excelType">
			                <option value="0">2007版及以上(.xlsx)</option>
			                <option value="1">2003版(.xls)</option>
			             </select>)
			          </td>
			       </tr>
			   </table>
		   </form>
		   </div>
		</td>
	</tr>
	<tr>
		<td valign="top" style="width: 100%; height: 75%">
			<table id="report1DataListTable" title="报表信息列表"></table>
		</td>
	</tr>
</table>
