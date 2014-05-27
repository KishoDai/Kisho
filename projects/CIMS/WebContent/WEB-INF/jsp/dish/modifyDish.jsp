<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		//改变标题
		$('#modifyDishDiv').panel({  
			  title: '修改菜品信息'
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
		    url: "${pageContext.request.contextPath }/DishAction/getJsonByShopIdWithoutLeafs.action?shopId=${updateEntity.sp_id}",  
		    required: true  
		}); 
		
		$("#resetUpdateDishButton").linkbutton({iconCls: 'icon-reset'}).click(function(){
			$("#modifyDishForm").form("reset");
		});
		
		$("#updateDishButton").linkbutton({
			 iconCls: 'icon-save'
		}).click(function(){
			if(!$("#modifyDishForm").form("validate")){
				 return;
			 }
			 $.post("${pageContext.request.contextPath }/DishAction/updateDish.action",
		    		 $("#modifyDishForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			 	        	$.messager.alert('提示框',data.message);
			 	        	$('#dishTreeUl').tree("reload");
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
<div style="overflow: hidden;" class="easyui-bg">
	<form id="modifyDishForm" method="post">
	    <input type="hidden" name="updateEntity.dh_isleaf" value="1"/>
	    <input type="hidden" name="updateEntity.dh_id" value="${updateEntity.dh_id }"/>
	    <input type="hidden" name="updateEntity.sp_id" value="${updateEntity.sp_id }"/>
	    <input type="hidden" name="updateEntity.dh_parentid" value="${updateEntity.dh_parentid}"/>
		<table>
		    <tr>
				<td>店铺编号(<span><font color="red">*必输</font></span>):</td>
				<td><input id="sp_id" disabled value="${updateEntity.sp_id}"/></td>
			</tr>
			<tr>
				<td>菜品分类编号(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_parentid" disabled  value="${updateEntity.dh_parentid }" ></td>
			</tr>
			<tr>
				<td>菜品编号(<span><font color="red">*必输</font></span>):</td>
				<td><input disabled type="text" value="${updateEntity.dh_id }" /></td>
			</tr>
			<tr>
				<td>菜品名称(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_name" name="updateEntity.dh_name" value="${updateEntity.dh_name }" /></td>
			</tr>
			<tr>
				<td>菜品价格(元)(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_price" name="updateEntity.dh_price"  value="${updateEntity.dh_price }" /></td>
			</tr>
			<tr>
				<td>菜品折扣:</td>
				<td><input id="dh_discount" name="updateEntity.dh_discount" value="${updateEntity.dh_discount }" /></td>
			</tr>
			<tr>
				<td>菜品描述:</td>
				<td><textarea name="updateEntity.dh_describe" rows="5" cols="40">${updateEntity.dh_describe }</textarea></td>
			</tr>
		</table>
	</form>
	<div style="text-align: center; padding: 5px">
		<a id="updateDishButton">保存</a>
		<a id="resetUpdateDishButton">重置</a>
	</div>
</div>

