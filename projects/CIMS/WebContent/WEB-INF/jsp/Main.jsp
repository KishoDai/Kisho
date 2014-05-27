<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.csms.utils.Constants"%>
<%@page import="com.csms.pojo.User"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>餐饮综合服务管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="comm/comm.jsp"%>
	
<script type="text/javascript">
	$(function() {
		//当关闭修改密码窗口时清空密码输入框中的内容
		$("#_modify_password_window_").window({
			onClose : function() {
				$("#_update_password_form_").form("clear");
			}
		});
		//为头部菜单添加事件
		$("#easyui-tabs_head").tabs({onSelect : function() {
			var title = $("#easyui-tabs_head").tabs("getSelected").panel("options").title;
			if (title == "修改密码") {
				$("#_modify_password_window_").window("open");
			} else if (title == "退出") {
				$.messager.confirm('确认框','您确认退出吗?',
								function(r) {
									if (r) {
										$.post("${pageContext.request.contextPath}/exit.action",null,
											function(data) {
												if (data.flag == "1") {
													window.location = "${pageContext.request.contextPath}/login.jsp";
												}
											},'json');
									}
								});
			} 
		  }
		});
		//修改密码
		$("#_update_password_btn_").click(
			function() {
				if (!$("#_update_password_form_").form("validate")) {
					return;
				}
				if ($.trim($("#newpassword").val()) != $.trim($("#newpassword2").val())) {
					$.messager.alert('提示框', '两次密码输入不一致!', 'error');
					$("#newpassword").focus();
					return false;
				}
				$.post("${pageContext.request.contextPath}/modifyPassword.action",
						$("#_update_password_form_").serialize(),
						function(data) {
							if (data.flag != '0') {
								$.messager.alert('提示框', data.message, 'error');
							} else {
								$.messager.alert('提示框', data.message);
								$("#_modify_password_window_").window("close");
							}
						}, 'json');
			});
		
		//取消修改密码窗口
		$("#_cancel_password_btn_").click(function() {
			$("#_modify_password_window_").window("close");
		});

		$("#_cancel_password_btn_").click();
	});
</script>
</head>
<body class="easyui-layout">
	<!-- 头部 -->
	<div region="north" collapsible="true" split="true"
		style="height: 50px; padding: 10px;">
		<div class="easyui-tabs" fit="true" border="false" id="easyui-tabs_head" align="right">
			<div title="修改密码" style="padding: 20px;"></div>
			<div title="退出" style="overflow: hidden; padding: 5px;"></div>
		</div>
	</div>
	<!-- 左侧功能菜单栏 -->
	<div region="west"  title="系统功能栏" class="easyui-bg"
		style="width: 200px; height:100%; padding1: 1px; overflow: hidden;">
		<div style="overflow: auto;" class="easyui-bg">
			<table>
			     <tr>
					<td valign="top">
					     <ul id="systemTree"></ul>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<%
	   User user = (User)session.getAttribute(Constants.LOGIN_USER);
	   if(StringUtils.isNotBlank(user.getSp_id())){
	%>
	<div region="east"  title="菜品" class="easyui-bg"
		style="width: 200px; height:100%; ">
		<div style="overflow:auto;height:80%" class="easyui-bg">
			<table>
			     <tr>
					<td valign="top">
					     <ul id="mainDishTreeUl"></ul>
					</td>
				</tr>
			</table>
		</div>
		<div style="overflow: auto;" class="easyui-bg">
			<table>
			     <tr>
					<td valign="bottom">
					   <div id="dishInfoDiv"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<%} %>
	<!-- 中部tab -->
	<div region="center" style="overflow: hidden;">
		<div class="easyui-tabs" fit="true" border="true" id="easyui-tabs_id"
			style="overflow: hidden;">
			<div title="首页" style="padding: 5px; overflow: hidden;" class="easyui-bg">
				<h1 align="center">欢迎访问${infoConfig.systemName }</h1>
			</div>
		</div>
		<!-- 修改密码window -->
		<div id="_modify_password_window_" closed="true" class="easyui-dialog"
			title="修改密码" modal="true" iconCls="icon-edit"
			style="width: 300px; height: 180px; padding: 5px; background: #fafafa;">
			<div class="easyui-layout" fit="true">
				<div region="center" border="false" shadow="true">
					<form id="_update_password_form_" method="post">
						<table cellpadding="0" cellspacing="0" border="0" align="center"
							style="overflow: hidden;">
							<tbody>
								<tr>
									<th align="right">原密码:</th>
									<td><input type="password" name="password" id="password"
										class="easyui-validatebox" required="true"
										missingMessage="请输入原密码" /></td>
									</td>
								</tr>
								<tr>
									<th align="right">新密码:</th>
									<td><input type="password" name="newpassword"
										id="newpassword" class="easyui-validatebox" required="true"
										missingMessage="请输入新密码" /></td>
									</td>
								</tr>
								<tr>
									<th align="right">确认密码:</th>
									<td><input type="password" name="newpassword2"
										id="newpassword2" class="easyui-validatebox" required="true"
										missingMessage="请输入确认新密码" /></td>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div region="south" border="false"
					style="text-align: center; height: 30px; line-height: 30px;">
					<a class="easyui-linkbutton" iconCls="icon-ok"
						href="javascript:void(0)" id="_update_password_btn_">修改</a> <a
						class="easyui-linkbutton" iconCls="icon-cancel"
						href="javascript:void(0)" id="_cancel_password_btn_">取消</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
    $(function(){
    	 $('#systemTree').tree({  
             url: "${pageContext.request.contextPath }/SystemAction/loadSystemTree4Json.action",
             lines:true,
             animate:true,
          	 onClick:function(node){
          		if(node.id == "0" || node.attributes.url == null || node.attributes.url == ""){
          			return;
          		}
          		var title = node.text;
       			var exists = $("#easyui-tabs_id").tabs("exists", title);
       			if (!exists) {
       				$('#easyui-tabs_id').tabs('add', {
       					title : title,
       					href :  "${pageContext.request.contextPath }" + node.attributes.url,
       					closable : true
       				}).panel("open");
       			} else {
       				$("#easyui-tabs_id").tabs("select", title).tabs('getSelected').panel('refresh');
       			}
          	}
         });  
    	<% if(StringUtils.isNotBlank(user.getSp_id())){%>
   			 $('#mainDishTreeUl').tree({  
   	             url: "${pageContext.request.contextPath}/DishAction/loadJsonByShopId.action?sp_id=<%=user.getSp_id()%>",
   	             lines:true,
   	             animate:true,
   	             onClick:function(node){
   	            	if(node.attributes.is_leaf == "1"){
	   	            	 $.post('${pageContext.request.contextPath}/DishAction/toViewDish.action','operEntity.dh_id='+node.id,function(data){
	   	            		 $("#dishInfoDiv").html(data);
	   	            	 },'html');
   	            	 }
   	             }});
    	
    	 <%}%>
   		
    });
	
</script>
</html>