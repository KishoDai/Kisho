<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	$(function(){
		var funcGroupPrefix = "${pageContext.request.contextPath }/FuncAction/";
		//改变标题
		$('#modifyFuncDiv').panel({  
			  title: '修改功能组信息'
		});  
		
		$("#resetUpdateFuncGroupBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
			$("#modifyFuncGroupForm").form("reset");
		});
		
		$("#updateFuncGroupBtn").linkbutton({
			 iconCls: 'icon-save'
		}).click(function(){
			if(!$("#modifyFuncGroupForm").form("validate")){
				 return;
			 }
			 $.post(funcGroupPrefix + "updateEntity.action",
		    		 $("#modifyFuncGroupForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			 	        	$('#funcTreeUl').tree("reload");
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'warning');
			 	        }
			       },'json');
		});
		
		$('#fn_groupid').combotree({  
			url: funcGroupPrefix + 'queryFuncGroupWithoutLeafs.action', 
		    required: true  
		}).validatebox({
			required : true,
			missingMessage : "功能组编号必选",
	    });
		
		$('#fn_id').validatebox({
			required : true,
			validType : 'length[1,10]',
			missingMessage : "功能编号必输",
			invalidMessage : "功能编号最长为10个字符"
	    });
		
		$('#fn_name').validatebox({
			required : true,
			missingMessage : "功能组名称必输",
	    });
		
	});
</script>
</head>
<body>
	<div style="overflow: hidden;" class="easyui-bg">
		<form id="modifyFuncGroupForm" method="post">
		    <input type="hidden" name="updateEntity.fn_isleaf" value="${updateEntity.fn_isleaf }"/>
		    <input type="hidden" name="updateEntity.fn_id" value="${updateEntity.fn_id }"/>
		    <input type="hidden" name="updateEntity.fn_groupid" value="${updateEntity.fn_groupid }"/>
			<table>
			    <tr>
					<td>上级功能组编号(<span><font color="red">*必输</font></span>):</td>
					<td><input id="fn_groupid" disabled value="${updateEntity.fn_groupid}"/></td>
				</tr>
			    <tr>
					<td>功能组编号(<span><font color="red">*必输</font></span>):</td>
					<td><input id="fn_id" name="updateEntity.fn_id" disabled value="${updateEntity.fn_id}"/></td>
				</tr>
				<tr>
					<td>功能组名称(<span><font color="red">*必输</font></span>):</td>
					<td><input id="fn_name" name="updateEntity.fn_name" value="${updateEntity.fn_name}"/></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a id="updateFuncGroupBtn">保存</a>
			<a id="resetUpdateFuncGroupBtn">重置</a>
		</div>
	</div>

</body>
</html>