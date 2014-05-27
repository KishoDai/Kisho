<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function submitForm() {
		$('#ff').form('submit');
	}
	function clearForm() {
		$('#ff').form('reset');
	}
</script>
</head>
<body>
	<div style="overflow: hidden;" class="easyui-bg">
		<form id="ff" method="post">
			<table>
				<tr>
					<td>菜单编号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="menu.mu_id" required="true" disabled value="${menu.mu_id }" /></td>
				</tr>
				<tr>
					<td>菜单名称:</td>
					<td><input class="easyui-validatebox" type="text"
						name="menu.mu_name" required="true" value="${menu.mu_name }" /></td>
				</tr>
				<tr>
					<td>功能编号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="menu.fn_id" required="false" disabled value="${menu.fn_id }" /></td>
				</tr>
				<tr>
					<td>功能路径:</td>
					<td><input class="easyui-validatebox" type="text"
						name="menu.fn_id" required="false" disabled value="${menu.fn_id }" /></td>
				</tr>
				<tr>
					<td>是否为叶子结点:</td>
					<td><select class="easyui-combobox" name="menu.mu_isleaf">
							<option value="1">是</option>
							<option value="0">否</option>
					</select></td>
				</tr>
				<tr>
					<td>功能图片地址:</td>
					<td><input class="easyui-validatebox" type="text"
						name="menu.mu_img_path" /></td>
				</tr>
				<tr>
					<td>序号:</td>
					<td><input class="easyui-validatebox" type="text"
						name="menu.mu_sn" /></td>
				</tr>
			</table>
		</form>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitForm()">保存</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="clearForm()">重置</a>
		</div>
	</div>

</body>
</html>