<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		var funcPrefix = "${pageContext.request.contextPath }/FuncAction/";
		//改变标题
		$('#modifyFuncDiv').panel({  
			  title: '修改功能信息'
		});  
		
		$('#fn_groupid').combotree({  
			url: funcPrefix + 'queryFuncGroupWithoutLeafs.action', 
		    required: true  
		});

		$("#resetUpdateFuncBtn").linkbutton({iconCls: 'icon-reset'}).click(function(){
			$("#modifyFuncForm").form("reset");
		});
		
		$("#updateFuncBtn").linkbutton({
			 iconCls: 'icon-save'
		}).click(function(){
			if(!$("#modifyFuncForm").form("validate")){
				 return;
			 }
			 $.post(funcPrefix + "updateEntity.action",
		    		 $("#modifyFuncForm").serialize(),
		    		 function(data){
					   if(data.flag == "0"){ 
			 	        	$('#funcTreeUl').tree("reload");
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'warning');
			 	        }
			       },'json');
		});
		
		$('#fn_groupid').combotree({  
			url: funcPrefix + 'queryFuncGroupWithoutLeafs.action', 
		    required: true  
		}).validatebox({
			required : true,
			missingMessage : "功能组编号必选",
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

<form id="modifyFuncForm" method="post">
	<div style="overflow: hidden;" class="easyui-bg">
		    <input type="hidden" name="updateEntity.fn_isleaf" value="${updateEntity.fn_isleaf }"/>
		    <input type="hidden" name="updateEntity.fn_id" value="${updateEntity.fn_id }"/>
		    <input type="hidden" name="updateEntity.fn_groupid" value="${updateEntity.fn_groupid}"/>
			<table>
			    <tr>
					<td>功能组编号(<span><font color="red">*必输</font></span>):</td>
					<td><input id="fn_groupid" disabled value="${updateEntity.fn_groupid}"/></td>
				</tr>
			    <tr>
					<td>功能编号(<span><font color="red">*必输</font></span>):</td>
					<td><input  disabled value="${updateEntity.fn_id}" /></td>
				</tr>
				<tr>
					<td>功能名称(<span><font color="red">*必输</font></span>):</td>
					<td><input id="fn_name" name="updateEntity.fn_name" value="${updateEntity.fn_name}"/>
					</td>
				</tr>
				<tr>
					<td>功能路径(<span><font color="red">*必输</font></span>):</td>
					<td><input id="fn_url" name="updateEntity.fn_url" value="${updateEntity.fn_url}" style="width:400px"/>
					</td>
				</tr>
			</table>
		<div style="text-align: center; padding: 5px">
			<a id="updateFuncBtn">保存</a>
			<a id="resetUpdateFuncBtn">重置</a>
		</div>
	</div>
</form>
