<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		var menu_treeboxbox = new dhtmlXTreeObject("menu_treeboxbox", "100%", "100%", 0);
		
		menu_treeboxbox.setSkin('dhx_skyblue');
		menu_treeboxbox.setImagePath("${pageContext.request.contextPath }/resources/dhtmlxtree/imgs/csh_bluebooks/");
		menu_treeboxbox.loadXML("${pageContext.request.contextPath }/MenuAction/loadMenuTree.action");
		menu_treeboxbox.setOnClickHandler(function(id){
			if(menu_treeboxbox.getParentId(id) == "0"){
				return;
			}
			var title = menu_treeboxbox.getItemText(id);
			$('#modifyMenuDiv').panel('open').panel('refresh',"${pageContext.request.contextPath }/MenuAction/toModifyMenuPage.action?menu.mu_id=" + id);
		});
		
		$('#modifyMenuDiv').panel({  
			  title: '应用菜单维护',
			  width:"100%",
			  height:"100%"
		});  

	});
</script>
<table style="width:100%;height:100%">
	<tr>
		<td style="width:150px"  valign="top" class="easyui-bg">
			<div id="menu_treeboxbox" border="true"  style="width: 150px;padding1: 1px; "class="easyui-bg"></div>
		</td>
		<td valign="top" style="width:100%;">
		    <div id="modifyMenuDiv" style="padding1: 1px;"></div>
		</td>
	</tr>
</table>
