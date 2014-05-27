<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		var orderPrefix = "${pageContext.request.contextPath}/OrderAction/";
		
		//打印票据
	    $.printPiaoJu = function(cardData,orderData){
	    	try {
	    		var seperator = "------------------------------------";
		    	var intOrient = 1;//纵向打印
		        var intTopLength = 15;
		        var intTop = 0; 
		        
		        var intLeft = 0;
		        var intWidth = 580;
		        var intHeight = 700 + orderData.total*120;
		        var eachHeight = 20;
				LODOP.SET_PRINT_PAGESIZE(intOrient,intWidth,intHeight,"");
		        
				LODOP.SET_PRINT_STYLE("FontColor",16711680);
				LODOP.ADD_PRINT_RECT(0,0,intWidth,intHeight,0,0);
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,intLeft,intWidth,eachHeight,"      消费清单         ");
				LODOP.SET_PRINT_STYLEA(2,"FontName","隶书");
				LODOP.SET_PRINT_STYLEA(2,"FontSize",11);
				LODOP.SET_PRINT_STYLEA(2,"FontColor",0);
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,0,70,eachHeight,"店铺名称:");
				LODOP.ADD_PRINT_TEXT(intTop,70,130,eachHeight,"${session.loginUser.sp_name}");
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,0,70,eachHeight,"店铺编号：" );
				LODOP.ADD_PRINT_TEXT(intTop,70,130,eachHeight,"${session.loginUser.sp_id}");
				
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,intLeft,intWidth,eachHeight,seperator);
				
				LODOP.SET_PRINT_STYLEA(4,"FontColor",0);
				
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,0,60,eachHeight,"菜品");
				LODOP.ADD_PRINT_TEXT(intTop,60,60,eachHeight,"单价(元)");
				LODOP.ADD_PRINT_TEXT(intTop,120,40,eachHeight,"折扣 ");
				LODOP.ADD_PRINT_TEXT(intTop,160,50,eachHeight,"数量");
                var rows = orderData.rows;
				for(var i = 0 ; i < rows.length; i++){
			        intTop += intTopLength;
			        LODOP.ADD_PRINT_TEXT(intTop,0,70,eachHeight,rows[i].dh_name);
					LODOP.ADD_PRINT_TEXT(intTop,70,60,eachHeight, rows[i].dh_price);
					LODOP.ADD_PRINT_TEXT(intTop,130,40,eachHeight,rows[i].dh_discount);
					LODOP.ADD_PRINT_TEXT(intTop,170,50,eachHeight,rows[i].od_amount);
			        intTop += intTopLength;
			        LODOP.ADD_PRINT_TEXT(intTop,0,intWidth,eachHeight,"(" + rows[i].dh_id + ")");
				}
				intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,intLeft,intWidth,eachHeight,seperator);
				var footers = orderData.footer;
				for(var i = 0 ; i < footers.length; i++){
			        intTop += intTopLength;
			        LODOP.ADD_PRINT_TEXT(intTop,0,70,eachHeight,footers[i].dh_name);
					LODOP.ADD_PRINT_TEXT(intTop,70,130,eachHeight, footers[i].od_amount + "元");
				}
				intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,intLeft,intWidth,eachHeight,seperator);
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,0,70,eachHeight,"订单编号:");
				LODOP.ADD_PRINT_TEXT(intTop,70,130,eachHeight, orderData.or_id);
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,0,70,eachHeight,"操作柜员：");
				LODOP.ADD_PRINT_TEXT(intTop,70,130,eachHeight,"${session.loginUser.ur_id}");
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,0,70,eachHeight,"卡类型:");
				LODOP.ADD_PRINT_TEXT(intTop,70,130,eachHeight, $.getCdType(cardData.cd_type));
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,0,70,eachHeight,"卡编号:");
				LODOP.ADD_PRINT_TEXT(intTop,70,130,eachHeight,cardData.cd_id);
		        intTop += intTopLength;
				LODOP.ADD_PRINT_TEXT(intTop,0,70,eachHeight,"出票时间:");
				LODOP.ADD_PRINT_TEXT(intTop,70,130,eachHeight,orderData.od_datetime);
		        LODOP.PRINT();
	    	} catch(e) {
	    		alert(e);
	    	}
	    };
		
	    //刷卡扣费
	    $.shuaCreditCard = function(card,orderData){
	    	$.post(orderPrefix + "loadCardJsonByCardId.action", "cd_id=" + card.cd_id,
 				 function(cardData){
 				       if(cardData.flag != '0'){
 				    	   $.messager.alert("提示框", cardData.message,'error');
 				    	   return;
 				        }
 				       
		 			
		//  		    if(card.cd_balance != cardData.cd_balance){
		//  		 	   $.messager.alert("提示框","该卡余额与系统记录余额不一致，请联系系统管理员处理",'error');
		//  		 	   return;
		//  		     }
		//TODO............
					if(card.cd_balance < orderData.footer[2].od_amount){
						$.messager.alert("提示框","卡余额为" + card.cd_balance + "元不足以支付本次消费");
						return;
					} 
					
					//从数据库中扣除本次消费 
					$.post(orderPrefix + "consume.action",
						"cd_id=" + card.cd_id + "&od_balance=" + orderData.footer[2].od_amount + "&or_id=" + orderData.or_id,
						function(consumeData){
							if(consumeData.flag != '0'){
								 $.messager.alert('提示框',consumeData.message,'error');
								 return;
							} 
// 							consume(card);
//                          从卡中扣费
//                          TODO......
							
		                    $.printPiaoJu(cardData,orderData);//打印票据给顾客
		                    alert("打印小票副本!");
		                    $.printPiaoJu(cardData,orderData);//打印小票副本
		                       
				    	    $('#orderDishEdatagrid').datagrid('loadData',{total:0,rows:[],footer:[]});//清空订单菜品列表
						    $('#orderDataListTable').datagrid('load');//刷新订单列表
					},'json');
 				},'json');
	    	
	    };
	    
	    $("#addOrderDishBtn").linkbutton({
			iconCls : 'icon-save'
		}).click(function() {
			var rows = $('#orderDishEdatagrid').datagrid('getRows');
			if (rows.length == 0) {
				return;
			}
			var card = $.readCard();
			if(!card){
				alert('读取卡信息失败，请检查卡是否放于刷卡区!');
				return;
			}
			$.post(orderPrefix + "addOrder.action", 
				   'requestJsonStr=' + $.toJSON(rows), 
					function(orderData) {
				       if(orderData.flag != '0'){
				    	   $.messager.alert('提示框', data.message,'error');
				    	   return;
				       }
                       $.shuaCreditCard(card,orderData); //刷卡扣费            
			}, 'json');
		});
	    
		$('#orderDataListTable').datagrid(
		{
			nowrap : false,
			striped : true,
			singleSelect : true,
			fit : true,
			singleSelect : true,
			checkOnSelect : true,
			selectOnCheck : true,
			url : orderPrefix + 'queryPageList.action',
			sortName : 'or_datetime',
			sortOrder : 'desc',
			idField : 'or_id',
			toolbar:[ {
				text : '撤销订单',
				iconCls : 'icon-undo',
				plain : true,
				handler : function() {
					var row = $('#orderDataListTable').datagrid('getSelected');
					if(row == null){
						$.messager.alert('提示框','请选择一条 记录后再操作!','warning');
						return;
					} 
				    if(row.or_status == '0'){
				    	$.post(orderPrefix + "cancelOrder.action",
				    			"or_id=" + row.or_id,
				    			function(data){
				    		if(data.flag != '0'){
				    			$.messager.alert('提示框','订单取消失败!','warning');
				    		} else {
				    			$('#orderDataListTable').datagrid('load');
				    		}
				    	},'json');
				    	return;
				    }
				    $.messager.alert('提示框','已付款的订单不能取消!','error');
				}
			},{
				text : '刷卡扣费',
				iconCls : 'icon-money',
				plain : true,
				handler : function() {
					var row = $('#orderDataListTable').datagrid('getSelected');
					if(row == null){
						$.messager.alert('提示框','请选择一条 记录后再操作!','warning');
						return;
					} 
					if(row.or_status == '1'){
						$.messager.alert('提示框','该订单已付款不能再次扣费！','error');
						return;
					}
					var card = $.readCard();
					if(!card){
						alert('读取卡信息失败，请检查卡是否放于刷卡区!');
						return;
					}
					$.post(orderPrefix + "getOrderDataByOrderId.action",
						  "or_id=" + row.or_id,
						  function(orderData){
						    $.shuaCreditCard(card,orderData); //刷卡扣费
					},'json');
			}},{
				text : '打印票据',
 				iconCls : 'icon-print',
				plain : true,
				handler : function() {
				var row = $('#orderDataListTable').datagrid('getSelected');
				if(row == null){
					$.messager.alert('提示框','请选择一条 记录后再操作!','warning');
					return;
				} 	
				 if(row.or_status == '0'){
					 $.messager.alert('提示框','该订单尚未付款不能打印票据!','error');
					 return;
				 }
				 $.post(orderPrefix + "getOrderDataByOrderId.action",
						  "or_id=" + row.or_id,
						  function(orderData){
					          $.post(orderPrefix + "loadCardJsonByCardId.action", "cd_id=" + row.cd_id,
				 				    function(cardData){
				 				        if(cardData.flag != '0'){
				 				    	   $.messager.alert("提示框", cardData.message,'error');
				 				    	   return;
				 				         }
				 				         $.printPiaoJu(cardData,orderData); //刷卡扣费
				 				      },'json');
					},'json');
			}},{
				text : '刷新',
 				iconCls : 'icon-reload',
				plain : true,
				handler : function() {
				 $('#orderDataListTable').datagrid('load');
			}}],
			columns : [ [ {
				title : '卡编号',
				field : 'cd_id',
				width : 60,
				sortable : true
			}, {
				title : '订单编号',
				field : 'or_id',
				width : 70,
				hidden : false,
				sortable : true
			}, {
				title : '订单状态',
				field : 'or_status',
				width : 55,
				hidden : false,
				sortable : true,
				formatter : function(val, row) {
					if (val == 0) {
						return '待付款';
					} else if (val == 1) {
						return '已付款';
					} else {
						return val;
					}
				}
			}, {
				title : '订单金额(元)',
				field : 'or_balance',
				width : 80,
				sortable : true
			}, {
				title : '订单时间',
				field : 'or_datetime',
				width : 80,
				sortable : true
			}, {
				title : '用户编号',
				field : 'ur_id',
				width : 60,
				sortable : true
			}] ],
			pagination : true,
			rownumbers : true,
			queryParams : {},
			loadMsg : 'loading...',
			view : detailview,
			detailFormatter : function(index, row) {
				return '<div style="padding:2px"><table id="orderDish-' + index + '"></table></div>';
			},
			onExpandRow : function(index, row) {
				$('#orderDish-' + index).datagrid(
								{
									url : orderPrefix + 'loadOrderDishJsonByOrderId.action?or_id=' + row.or_id,
									fitColumns : true,
									singleSelect : true,
									rownumbers : true,
									loadMsg : '',
									height : 'auto',
									columns : [ [ {
										field : 'dh_name',
										title : '菜品',
										width : 40
									}, {
										field : 'od_amount',
										title : '菜品数量',
										width : 40
									}, {
										field : 'dh_price',
										title : '单价(元)',
										width : 40
									}, {
										field : 'dh_discount',
										title : '菜品折扣',
										width : 40
									}, ] ],
									onResize : function() {
										$('#orderDataListTable').datagrid('fixDetailRowHeight',index);
									},
									onLoadSuccess : function() {
										setTimeout(
												function() {
													$('#orderDataListTable').datagrid('fixDetailRowHeight',index);
												}, 0);
									}
								});
				$('#orderDataListTable').datagrid('fixDetailRowHeight', index);
			}
		});

		//选择菜品 
		$("#order_dh_id").combobox(
				{
					url : orderPrefix + 'loadDishByShopId.action',
					valueField : 'id',
					textField : 'text',
					panelHeight : 'auto',
					onHidePanel : function() {
						var value = $("#order_dh_id").combobox('getValue');
						if (!value) {
							return;
						}
						$.post(orderPrefix + 'loadDishByDishId.action',
								'dh_id=' + value, function(data) {
									if (data) {
										var dg = $('#orderDishEdatagrid').edatagrid('addRow');
										var rows = dg.datagrid('getRows');
										dg.datagrid('updateRow', {
											index : rows.length - 1,
											row : data
										}).edatagrid('editRow', rows.length);
									}
									$("#order_dh_id").combobox('reset');
								}, 'json');
					}
				});

		//订单菜品列表
		$('#orderDishEdatagrid').edatagrid({
			singleSelect : true,
			rownumbers : true,
			fitColumns : true,
			title : '订单菜品列表',
			showFooter:true,
			toolbar : [ {
				text : '删除',
				iconCls : 'icon-remove',
				plain : true,
				handler : function() {
					$('#orderDishEdatagrid').edatagrid('destroyRow');
				}
			},{
				text : '保存',
				iconCls : 'icon-save',
				plain : true,
				handler : function() {
					$('#orderDishEdatagrid').edatagrid('saveRow');
				}
			}],
			columns : [ [ {
				title : '菜品编号',
				field : 'dh_id',
				width : 60
			}, {
				title : '菜品名称',
				field : 'dh_name',
				width : 60
			}, {
				title : '菜品数量',
				field : 'od_amount',
				width : 60,
				editor : {
					type : 'numberspinner',
					options : {
						min : 1,
						editable : true
					}
				}
			}, {
				title : '菜品价格(元)',
				field : 'dh_price',
				width : 60,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2
					}
				}
			}, {
				title : '菜品折扣',
				field : 'dh_discount',
				width : 60,
				editor : {
					type : 'numberbox',
					options : {
						precision : 2
					}
				}
			} ] ]
		});
		
		$.extend($.fn.datagrid.defaults.editors, {
			numberspinner : {
				init : function(container, options) {
					var input = $('<input type="text">').appendTo(container);
					return input.numberspinner(options);
				},
				destroy : function(target) {
					$(target).numberspinner('destroy');
				},
				getValue : function(target) {
					return $(target).numberspinner('getValue');
				},
				setValue : function(target, value) {
					$(target).numberspinner('setValue', value);
				},
				resize : function(target, width) {
					$(target).numberspinner('resize', width);
				}
			}
		});
		
	});
</script>
<table style="width: 100%; height: 99%">
	<tr>
		<td valign="top" style="width: 55%">
			<table id="orderDataListTable" title="订单列表"></table>
		</td>
		<td valign="top">
	         <span><font color='red'>请选择菜品：</font></span> 
	         <input id="order_dh_id" name="order_dh_id" style="width: 300px;" />
			 <table id="orderDishEdatagrid" style="height: 300px"></table>
			<div align="center">
				<a id="addOrderDishBtn">生成订单</a>
			</div></td>
	</tr>
</table>

<OBJECT ID="LODOP" CLASSID="clsid:2105C259-1E0C-4534-8141-A753534CB4CA"WIDTH=0 HEIGHT=0> </OBJECT>
