<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		//改变标题
		$('#modifyDishDiv').panel({  
			  title: '修改菜品分类信息'
		});  
		
		$('#sp_id').combobox({  
		    url:'${pageContext.request.contextPath }/DishAction/queryShop.action',  
		    valueField:'id',  
		    textField:'text',
		    onSelect: function(record){
		    	$('#dh_parentid').combotree("reload","${pageContext.request.contextPath }/DishAction/loadJsonByShopId.action?shopId=" + record.id);
		    	$('#dh_parentid').combotree('setValue', "0");

		    }
		});  
		
		$('#dh_parentid').combotree({  
		    url: "${pageContext.request.contextPath }/DishAction/getJsonByShopIdWithoutLeafs.action?shopId=${updateEntity.sp_id}",  
		    required: true  
		}); 
		
		$("#resetUpdateDishDishButton").linkbutton({iconCls: 'icon-reset'}).click(function(){
			$("#updateDishSortForm").form("reset");
		});
		
		$("#updateDishSortButton").linkbutton({ iconCls: 'icon-save'}).click(function(){
			if(!$("#updateDishSortForm").form("validate")){
				 return;
			 }
			 $.post("${pageContext.request.contextPath }/DishAction/updateDish.action",
		    		 $("#updateDishSortForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			 	        	$.messager.alert('提示框',data.message);
			 	        	$('#dishTreeUl').tree("reload");
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'error');
			 	        }
			 },'json');
		});
		
		$("#dh_name").validatebox({
			required : true,
			missingMessage : "菜品分类名称必输"
		});
		
	});
</script>
<div style="overflow: hidden;" class="easyui-bg">
	<form id="updateDishSortForm" method="post">
	    <input type="hidden" name="updateEntity.dh_id" value="${updateEntity.dh_id }"/>
	    <input type="hidden" name="updateEntity.dh_isleaf" value="0"/>
	    <input type="hidden" name="updateEntity.sp_id" value="${updateEntity.sp_id}"/>
	    <input type="hidden" name="updateEntity.dh_parentid" value="${updateEntity.dh_parentid}"/>
		<table>
		    <tr>
				<td>店铺编号(<span><font color="red">*必输</font></span>):</td>
				<td><input disabled id="sp_id" value="${updateEntity.sp_id}"/></td>
			</tr>
			<tr>
				<td>上级菜品分类编号(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_parentid" disabled value="${updateEntity.dh_parentid }"></td>
			</tr>
			<tr>
				<td>菜品分类编号(<span><font color="red">*必输</font></span>):</td>
				<td><input disabled value="${updateEntity.dh_id }"></td>
			</tr>
			<tr>
				<td>菜品分类名称(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_name" name="updateEntity.dh_name" value="${updateEntity.dh_name }"/></td>
			</tr>
		</table>
	</form>
	<div style="text-align: center; padding: 5px">
		<a id="updateDishSortButton">保存</a>
		<a id="resetUpdateDishDishButton">重置</a>
	</div>
</div>
