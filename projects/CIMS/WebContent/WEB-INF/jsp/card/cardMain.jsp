<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		var cardPrefix = "${pageContext.request.contextPath}/CardAction/";

		//美食卡列表
		$('#cardDataListTable').datagrid(
		{
			nowrap : false,
			striped : true,
			singleSelect : true,
			fit : true,
			singleSelect : true,
			checkOnSelect : true,
			selectOnCheck : true,
			url : cardPrefix + 'queryPageList.action',
			sortName : 'cd_send_datetime',
			sortOrder : 'desc',
			idField : 'cd_id',
			columns : [ [ {
				title : '卡编号',
				field : 'cd_id',
				width : 100,
				sortable : true
			}, {
				title : '美食卡种类',
				field : 'cd_type',
				width : 70,
				hidden : false,
				sortable : true,
				formatter : function(val, row) {
					return $.getCdType(val);
				}
			},{
				title : '卡余额(元)',
				field : 'cd_balance',
				width : 70,
				sortable : true
			},{
				title : '开卡日期',
				field : 'cd_send_datetime',
				width : 150,
				sortable : true
			}] ],
			pagination : true,
			rownumbers : true,
			queryParams : {},
			loadMsg : 'loading...'
		});

		//新增加美食卡
		$("#addCardBtn").linkbutton({iconCls: 'icon-save'}).click(function(){
			if(!$("#addCardForm").form("validate")){
				 return;
			 }
			 //写美食卡信息
			 if(!$.writeCard($("#addCardForm").serialize())){
				 return;
			 }
			 //系统中增加美食卡信息
			 $.post(cardPrefix + "addCard.action",
		    		 $("#addCardForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
						   $.messager.alert("提示框","开卡成功！","warning"); 
						   $('#cardDataListTable').datagrid('load');
						   $("#resetAddCardBtn").click();
					       return;
			 	        } 
			 	       $.messager.alert('提示框',data.message,'error');
			       },'json');
		});
		
		//重置添加美食卡信息
        $("#resetAddCardBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
        	$("#addCardForm").form("reset");
		});
        
		//校验美食卡ID
        $('#cd_id').validatebox({
			required : true,
			validType : 'length[1,32]',
			missingMessage : "卡编号必输",
			invalidMessage : "卡编号最长为32个字符"
	    });
        
		//美食卡类型
        $('#cd_type').combotree({  
			url: $.getParameterUrl('cardTypeJson'), 
			valueField : 'id',
			textField : 'text',
			panelHeight:'auto' ,
			required : true,
			missingMessage : "卡种类必选"
		});
        
		//读取美食卡信息并且赋值卡号
        $("#readCard4Add").linkbutton({iconCls: 'icon-search'}).click(function(){
        	var card = $.readCard();
        	if(!card){
        		return;
        	}
        	$("#cd_id").val(card.cd_id);
        });
        
		//美食卡查询条件
        $('#cardQueryCondition').searchbox({  
    	    searcher:function(value,cd_type){ 
    	    	cd_type = (cd_type == 'all' ? '' : cd_type);
    	    	$('#cardDataListTable').datagrid('load', {  
    	    		'queryEntity.cd_type': cd_type,
    	    		'queryEntity.cd_id': value
    	    	});  
    	    },  
    	    menu:'#cardQueryMenu',  
    	    prompt:'请输入卡编号',
    	    width:250
    	});  
    
    //读取美食卡信息并且赋值查询美食卡的ID
	$("#readCard4Query").linkbutton({iconCls: 'icon-search'}).click(function() {
		var card = $.readCard();
    	if(!card){
    		return;
    	}
    	$("#cardQueryCondition").val(card.cd_id);
	});

    //读取美食卡信息用于充值
	$("#readCard4AddValue").linkbutton({iconCls: 'icon-search'}).click(function() {
		var card = $.readCard();
    	if(!card){
    		return;
    	}
		$("#addValueCard_cd_id").val(card.cd_id);
		var cd_id = $("#addValueCard_cd_id").val();
		$.post(cardPrefix + "getJsonCardByCardId.action?cd_id=" + cd_id,null,
				function(data){
			       $('#addValueCard_cd_type').combobox({
			    	    url: $.getParameterUrl('cardTypeJson'), 
			   			valueField : 'id',
						textField : 'text',
						panelHeight:'auto',
						onLoadSuccess:function(){
							 $('#addValueCard_cd_type').combobox('setValue',data.cd_type);
						}});
			       $('#addValueCard_cd_balance').val(data.cd_balance);
		},'json');
		
	});
	
	$("#addValueCard_cd_id").validatebox({
		required:true,
		missingMessage : "美食卡编号必输!"
	});
	
	$("#addValueCard_balance").numberbox({
		min:0,
		precision:2
	}).validatebox({
		required:true,
		missingMessage : "充值金额必输且不能为负数"
	});
	
	$("#addValueCardBtn").linkbutton({iconCls: 'icon-money'}).click(function(){
		if(!$("#addValue4CardForm").form("validate")){
			 return;
		 }
		$.post(cardPrefix + "addValue.action",
				 $("#addValue4CardForm").serialize(),
				 function(data){
			       if(data.flag == '0'){
			    	   $.showMessage("充值成功!");
			    	   $('#cardDataListTable').datagrid('load');
			    	   $("#resetAddValueCardBtn").click();
			       } else{
			    	   $.messager.alert('提示框', data.message,'error');
			       }
		         },
				 'json');
	});
	
	$("#resetAddValueCardBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
		$("#addValue4CardForm").form("reset");
	});
	
	$("#returnCardBtn").linkbutton({iconCls: 'icon-remove'}).click(function(){
		if($("#returnCard_cd_type").val() == "0"){//会员卡不允许退卡
			$.messager.alert("提示框","会员卡不允许退卡!",'warning');
			return;
		}
		
		if(!$("#returnCardForm").form("validate")){
			 return;
		}
		$.post(cardPrefix + "returnCard.action",
				 $("#returnCardForm").serialize(),
				 function(data){
			       if(data.flag == '0'){
			    	   $.showMessage("开卡成功");
			    	   $("#resetReturnCardBtn").click();
			       } else{
			    	   $.messager.alert('提示框', data.message,'error');
			       }
		         },
				 'json');
	});
	
	$("#returnCard_cd_id").validatebox({
		required:true,
		missingMessage : "美食卡编号必输!"
	});
	
	$("#readCard4ReturnCard").linkbutton({iconCls: 'icon-search'}).click(function() {
		var card = $.readCard();
    	if(!card){
    		return;
    	}
		$("#returnCard_cd_id").val(card.cd_id);
		var cd_id = $("#returnCard_cd_id").val();
		$.post(cardPrefix + "getJsonCardByCardId.action?cd_id=" + cd_id,null,
				function(data){
			       $('#returnCard_cd_type').combobox({
			    	    url: $.getParameterUrl('cardTypeJson'), 
			   			valueField : 'id',
						textField : 'text',
						panelHeight:'auto',
						onLoadSuccess:function(){
							 $('#returnCard_cd_type').combobox('setValue',data.cd_type);
						}});
			       $('#returnCard_cd_balance').val(data.cd_balance);
		},'json');
	});
	
	$("#resetReturnCardBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
		$("#returnCardForm").form("reset");
	});
	
 });
</script>
<table style="width: 100%; height: 95%">
	<tr>
		<td valign="top" style="width: 50%">
		    <input id="cardQueryCondition"/><a id="readCard4Query">读卡</a>
			<div id="cardQueryMenu" style="width:120px" title="美食卡类型">  
			    <div data-options="name:'all',iconCls:'icon-ok'">所有</div> 
			    <div data-options="name:'0'">会员卡</div>  
			    <div data-options="name:'1'">临时卡</div>  
			</div>
			<table id="cardDataListTable" title="美食卡列表"></table>
		</td>
		<td valign="top">
			<div class="easyui-accordion" >  
	        <div title="新增加美食卡" data-options="iconCls:'icon-add'" style="overflow:auto;padding:10px;">  
	            <form id="addCardForm" method="post">
				<table class="easyui-bg">
				    <tr>
						<td colspan="2"><span><font color="red">请将美食卡放于刷卡区点击读卡按钮获取卡信息</font></td>
					</tr>
					<tr>
						<td>卡编号(<span><font color="red">*必输</font></span>):</td>
						<td><input id="cd_id" readonly name="addEntity.cd_id" />
						    <a id="readCard4Add">读卡</a>
						</td>
					</tr>
				    <tr>
						<td>美食卡种类(<span><font color="red">*必输</font></span>):</td>
						<td><input id="cd_type" name="addEntity.cd_type"/></td>
					</tr>
				</table>
			    <div style="text-align: center; padding: 5px">
					<a id="addCardBtn">保存</a>
					<a id="resetAddCardBtn">重置</a>
				</div>
			</form>
	        </div> 
	        <div title="美食卡充值" data-options="iconCls:'icon-money'" style="overflow:auto;padding:10px;">  
	            <form id="addValue4CardForm" method="post">
				<table class="easyui-bg">
				    <tr>
						<td colspan="2"><span><font color="red">请将美食卡放于刷卡区点击读卡按钮获取卡信息</font></td>
					</tr>
					<tr>
						<td>卡编号(<span><font color="red">*必输</font></span>):</td>
						<td><input id="addValueCard_cd_id"  readonly name="addValueCard_cd_id"/>
						    <a id="readCard4AddValue">读卡</a>
						</td>
					</tr>
					<tr>
						<td>卡种类:</td>
						<td><input id="addValueCard_cd_type"  disabled name="addValueCard_cd_type"/>
						</td>
					</tr>
					<tr>
						<td>卡余额(元):</td>
						<td><input id="addValueCard_cd_balance"  disabled name="addValueCard_cd_balance"/>
						</td>
					</tr>
				    <tr>
						<td>充值金额(元)</td>
						<td><input id="addValueCard_balance" name="addValueCard_balance"/></td>
					</tr>
				</table>
			    <div style="text-align: center; padding: 5px">
					<a id="addValueCardBtn">充值</a>
					<a id="resetAddValueCardBtn">重置</a>
				</div>
				</form>
	        </div> 
	        <div title="退还美食卡" data-options="iconCls:'icon-remove'" style="overflow:auto;padding:10px;">  
	             <form id="returnCardForm" method="post">
				<table class="easyui-bg">
				    <tr>
						<td colspan="2"><span><font color="red">请将美食卡放于刷卡区点击读卡按钮获取卡信息</font></td>
					</tr>
					<tr>
						<td>卡编号(<span><font color="red">*必输</font></span>):</td>
						<td><input id="returnCard_cd_id"  readonly name="returnCard_cd_id"/>
						    <a id="readCard4ReturnCard">读卡</a>
						</td>
					</tr>
					<tr>
						<td>卡种类:</td>
						<td><input id="returnCard_cd_type"  disabled name="returnCard_cd_type"/>
						</td>
					</tr>
					<tr>
						<td>卡余额(元):</td>
						<td><input id="returnCard_cd_balance"  disabled name="returnCard_cd_balance"/>
						</td>
					</tr>
				</table>
			    <div style="text-align: center; padding: 5px">
					<a id="returnCardBtn">退卡</a>
					<a id="resetReturnCardBtn">重置</a>
				</div>
				</form>
	        </div> 
	    </div>  
	    </td>
	</tr>
</table>

