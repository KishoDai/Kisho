<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		//改变标题
		$('#modifyDishDiv').panel({  
			  title: '添加菜品分类信息'
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
		    url: "${pageContext.request.contextPath }/DishAction/getJsonByShopIdWithoutLeafs.action?shopId=${operDish.sp_id}",  
		    required: true  
		}); 
		
		
		$("#resetAddDishSortButton").linkbutton({iconCls: 'icon-reset'}).click(function(){
			$("#addDishSortForm").form("reset");
		});
		
		$("#addDishSortButton").linkbutton({
			 iconCls: 'icon-save'
		}).click(function(){
			if(!$("#addDishSortForm").form("validate")){
				 return;
			 }
			 $.post("${pageContext.request.contextPath }/DishAction/addDish.action",
		    		 $("#addDishSortForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			 	        	$.messager.alert('提示框',data.message);
			 	        	$('#dishTreeUl').tree("reload");
			 	        	$("#resetAddDishSortButton").click();
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'error');
			 	        }
			       },'json');
		});
		
		$("#dh_name").validatebox({
			required : true,
			missingMessage : "菜品分类名称必输"
		});
		
		$("#dh_id").validatebox({
			required : true,
			validType : 'length[1,10]',
			missingMessage : "菜品分类编号必须输入",
			invalidMessage : "菜品分类编号最长为10个字符"
	    });
	});
</script>
<div style="overflow: hidden;" class="easyui-bg">
	<form id="addDishSortForm" method="post">
	    <input type="hidden" name="addEntity.dh_isleaf" value="0"/>
	    <input type="hidden" name="addEntity.sp_id" value="${operEntity.sp_id}"/>
	    <input type="hidden" name="addEntity.dh_parentid" value="${operEntity.dh_id }"/>
		<table>
		    <tr>
				<td>店铺编号(<span><font color="red">*必输</font></span>):</td>
				<td><input id="sp_id" disabled value="${operEntity.sp_id}" /></td>
			</tr>
			<tr>
				<td>上级菜品分类编号(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_parentid" disabled value="${operEntity.dh_id }"></td>
			</tr>
			<tr>
				<td>菜品分类编号(<span><font color="red">*必输</font></span>):</td>
				<td><input id="dh_id"  name="addEntity.dh_id"></td>
			</tr>
			<tr>
				<td>菜品分类名称(<span><font color="red">*必输</font></span>):</td>
				<td><input name="addEntity.dh_name" id="dh_name"/></td>
			</tr>
		</table>
	</form>
	<div style="text-align: center; padding: 5px">
		<a id="addDishSortButton">保存</a>
		<a id="resetAddDishSortButton">重置</a>
	</div>
</div>
