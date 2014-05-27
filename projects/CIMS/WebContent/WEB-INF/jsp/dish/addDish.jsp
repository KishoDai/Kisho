<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		//改变标题
		$('#modifyDishDiv').panel({  
			  title: '添加菜品信息'
		});  
		
		$('#sp_id').combobox({  
		    url:'${pageContext.request.contextPath }/DishAction/queryShop.action',  
		    valueField:'id',  
		    textField:'text',
		    onSelect: function(record){
		    	$('#dh_parentid').combotree("reload","${pageContext.request.contextPath }/DishAction/loadJsonByShopId.action?shopId=" + record.id);
		    	$('#dh_parentid').combotree('setValue', "");

		    }
		});  
		
		$('#dh_parentid').combotree({  
		    url: "${pageContext.request.contextPath }/DishAction/getJsonByShopIdWithoutLeafs.action?shopId=${operEntity.sp_id}",  
		    required: true  
		}); 
		
		$("#resetAddDishButton").linkbutton({iconCls: 'icon-reset'}).click(function(){
			$("#addDishForm").form("reset");
		});
		
		$("#addDishButton").linkbutton({
			 iconCls: 'icon-save'
		}).click(function(){
			if(!$("#addDishForm").form("validate")){
				 return;
			 }
			 $.post("${pageContext.request.contextPath }/DishAction/addDish.action",
		    		 $("#addDishForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			 	        	$.messager.alert('提示框',data.message);
			 	        	$('#dishTreeUl').tree("reload");
			 	        	$("#resetAddDishButton").click();
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'error');
			 	        }
			       },'json');
		});
		
		$("#dh_id").validatebox({
			required : true,
			validType : 'length[1,10]',
			missingMessage : "菜品编号必须输入",
			invalidMessage : "菜品编号最长为10个字符"
	    });
		
		$("#dh_name").validatebox({
			required : true,
			missingMessage : "菜品名称必须输入"
	    });
		
		$("#dh_price").numberbox({  
		    min:0,  
		    precision:2  
		}).validatebox({
			required : true,
			missingMessage : "菜品价格必须输入"
	    });
		
		$("#dh_discount").numberbox({  
		    min:0,
		    max:1,
		    precision:1  
		}).validatebox({
			required : true,
			missingMessage : "菜品价格必须输入"
	    });
		
	});
</script>
<div style="overflow:visible;" class="easyui-bg">
	<form id="addDishForm" method="post">
	    <input type="hidden" name="addEntity.dh_isleaf" value="1"/>
	    <input type="hidden" name="addEntity.sp_id" value="${operEntity.sp_id}"/>
	    <input type="hidden" name="addEntity.dh_parentid" value="${operEntity.dh_id}"/>
		<table>
		    <tr>
				<td>店铺编号(<span><font color="red">*必输</font></span>):</td>
				<td><input id="sp_id" value="${operEntity.sp_id}" disabled/></td>
			</tr>
			<tr>
				<td>菜品分类编号(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_parentid" disabled  value="${operEntity.dh_id }"></td>
			</tr>
			<tr>
				<td>菜品编号(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_id" name="addEntity.dh_id"/></td>
			</tr>
			<tr>
				<td>菜品名称(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_name" name="addEntity.dh_name"/></td>
			</tr>
			<tr>
				<td>菜品价格(元)(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_price" name="addEntity.dh_price"/></td>
			</tr>
			<tr>
				<td>菜品折扣:</td>
				<td><input name="addEntity.dh_discount" id="dh_discount"/></td>
			</tr>
			<tr>
				<td>菜品描述:</td>
				<td><textarea name="addEntity.dh_describe" rows="5" cols="40"></textarea></td>
			</tr>
		</table>
	</form>
	<div style="text-align: center; padding: 5px">
		<a id="addDishButton">保存</a>
		<a id="resetAddDishButton">重置</a>
	</div>
</div>
