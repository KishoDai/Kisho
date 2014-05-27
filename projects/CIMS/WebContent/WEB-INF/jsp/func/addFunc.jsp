<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		var funcPrefix = "${pageContext.request.contextPath }/FuncAction/";
		
		$("#addFuncBtn").linkbutton({iconCls: 'icon-save'});
		$("#addFuncBtn").click(function(){
			if(!$("#addFuncForm").form("validate")){
				 return;
			 }
			 $.post(funcPrefix + "addEntity.action",
		    		 $("#addFuncForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			 	        	$('#funcTreeUl').tree("reload");
			 	        	$("#resetAddFuncBtn").click();
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'warning');
			 	        }
			       },'json');
		});
		
		$("#resetAddFuncBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
			$("#addFuncForm").form("reset");
		});
		
		$('#modifyFuncDiv').panel({  
			  title: '添加功能信息'
		});  
		
		$('#fn_groupid').combotree({  
			url: funcPrefix + 'queryFuncGroupWithoutLeafs.action', 
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
			missingMessage : "功能名称必输",
	    });
		
		$('#fn_url').validatebox({
			required : true,
			missingMessage : "功能路径必输",
	    });
		
});
</script>

<form id="addFuncForm" method="post">
<div class="easyui-bg">
    <input type="hidden" name="addEntity.fn_isleaf" value="1"/>
    <input type="hidden" name="addEntity.fn_groupid" value="${operEntity.fn_id }"/>
	<table>
		<tr>
			<td>功能组编号(<span><font color="red">*必输</font></span>):</td>
			<td><input id="fn_groupid" disabled value="${operEntity.fn_id }" /></td>
		</tr>
	    <tr>
			<td>功能编号(<span><font color="red">*必输</font></span>):</td>
			<td><input id="fn_id" name="addEntity.fn_id"/></td>
		</tr>
		<tr>
			<td>功能名称(<span><font color="red">*必输</font></span>):</td>
			<td><input id="fn_name" name="addEntity.fn_name"/>
			</td>
		</tr>
		<tr>
			<td>功能路径(<span><font color="red">*必输</font></span>):</td>
			<td><input id="fn_url" name="addEntity.fn_url" style="width:400px"/>
			</td>
		</tr>
	</table>
    <div style="text-align: center; padding: 5px">
			<a id="addFuncBtn">保存</a>
			<a id="resetAddFuncBtn">重置</a>
		</div>
	</div>
</form>

