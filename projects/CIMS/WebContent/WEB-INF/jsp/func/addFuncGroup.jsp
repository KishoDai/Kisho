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
			  title: '添加功能组信息'
		});  
		
		$("#resetAddFuncGroupBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
			$("#addFuncGroupForm").form("reset");
		});
		
		$("#addFuncGroupBtn").linkbutton({
			 iconCls: 'icon-save'
		}).click(function(){
			if(!$("#addFuncGroupForm").form("validate")){
				 return;
			 }
			 $.post(funcGroupPrefix + "addEntity.action",
		    		 $("#addFuncGroupForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			 	        	$('#funcTreeUl').tree("reload");
			 	        	$("#resetAddFuncGroupBtn").click();
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'error');
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
		<form id="addFuncGroupForm" method="post">
		    <input type="hidden" name="addEntity.fn_isleaf" value="0"/>
		    <input type="hidden" name="addEntity.fn_groupid" value="${operEntity.fn_id}"/>
			<table>
			    <tr>
					<td>上级功能组编号(<span><font color="red">*必输</font></span>):</td>
					<td><input id="fn_groupid"disabled value="${operEntity.fn_id}"/></td>
				</tr>
			    <tr>
					<td>功能组编号(<span><font color="red">*必输</font></span>):</td>
					<td><input id="fn_id" name="addEntity.fn_id"/></td>
				</tr>
				<tr>
					<td>功能组名称(<span><font color="red">*必输</font></span>):</td>
					<td><input id="fn_name" name="addEntity.fn_name"/>
					</td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a  id="addFuncGroupBtn">保存</a>
			<a  id="resetAddFuncGroupBtn">重置</a>
		</div>
	</div>

</body>
</html>