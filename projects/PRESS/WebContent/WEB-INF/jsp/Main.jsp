<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.press.comm.Menu.SubMenu"%>
<%@page import="com.press.comm.Menu"%>
<%@page import="java.util.List"%>
<%@page import="com.press.comm.SysCode"%>
<%
	List<Menu> menuList = SysCode.getMenuList();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<%@ include file="comm/comm.jsp"%>
<script type="text/javascript">
	$(function() {
		/*在center添加tab */
		$("a[id$='a_class']").click(function() {
			var title = $(this).attr("title") ;
			var url = $(this).attr("url") ;
			var exists = $("#easyui-tabs_id").tabs("exists", title);
			if (!exists) {
				$('#easyui-tabs_id').tabs('add', {
					title : title,
					href : url,
					//iconCls : 'icon-reload',
					closable : true,
					cache : true
				});
			} else {
				$("#easyui-tabs_id").tabs("select", title);
			}
	  });
 	 
	//当关闭修改密码窗口时清空密码输入框中的内容
     $("#_modify_password_window_").window({onClose:function(){
    	 //$("input[type='password']").val("");
    	 $("#_update_password_form_").form("clear");
       }}
     ); 
	 //为头部菜单添加事件
	 $("#easyui-tabs_head").tabs({
		 onSelect:function(){
			 var title = $("#easyui-tabs_head").tabs("getSelected").panel("options").title;
			 if(title == "修改密码"){
				 $("#_modify_password_window_").window("open");
			 } else if (title == "退出"){
				 $.messager.confirm('确认框', '您确认退出吗?', function(r){
					if (r){
						$.getJSON("${pageContext.request.contextPath}/exit.action",
								  function(data){
									if(data.flag=="1"){
										window.location="${pageContext.request.contextPath}/index.jsp";
									 }
							      }
						); 
					}
				});
			 } else if (title == "首页"){
			 }
		 }
	 });
	//修改密码
	 $("#_update_password_btn_").click(function(){
		 if(!$("#_update_password_form_").form("validate")){
			 return;
		 }
		 if($.trim($("#newpassword").val())!=$.trim($("#newpassword2").val())){
			 $.messager.alert('提示框','两次密码输入不一致!','error');
			 $("#newpassword").focus();
			 return false;
		 }
		 $.post("${pageContext.request.contextPath}/admin/admin_do_update_password.action",
				$("#_update_password_form_").serialize(),
				function(data){
			       if(data.flag == "1" || data.flag =="2"){
			          $.messager.alert('提示框',data.message,'error');
			       }
			       if(data.flag == "0"){
			          $.messager.alert('提示框',data.message,'info');
			    	   $("#_modify_password_window_").window("close");
			       }
		       },
		        'json'
		 ); 
	 });
	 //取消修改密码窗口
	 $("#_cancel_password_btn_").click(function(){
		 $("#_modify_password_window_").window("close");
	 });
	 
   });
</script>
</head>
<body class="easyui-layout">
    <!-- 头部 -->
	<div region="north" collapsible="true" split="true" style="height: 50px; padding: 10px;">
		<div class="easyui-tabs" fit="true" border="false" id="easyui-tabs_head">
			<div title="首页" style="padding: 20px; overflow: hidden;"></div>
			<div title="修改密码" style="padding: 20px;"></div>
			<div title="退出" style="overflow: hidden; padding: 5px;"></div>
		</div>
	</div>
	<!-- 左侧功能菜单栏 -->
	<div region="west" split="true" title="功能菜单栏" style="width: 200px; padding1: 1px; overflow: hidden;">
		<div class="easyui-accordion" fit="true" border="false">
			<%
				Menu menu = null;
				List<SubMenu> subMenuList = null;
				SubMenu subMenu = null;
				for (int i = 0; i < menuList.size(); i++) {
					menu = menuList.get(i);
					subMenuList = menu.getSubMenuList();
			%>
			<div title="<%=menu.getTitle()%>" style="overflow: auto;">
				<ul>
					<%
						for (int j = 0; j < subMenuList.size(); j++) {
								subMenu = subMenuList.get(j);
					%>
					<li><a href="javascript:void(0);" 
					       class='easyui-linkbutton'
					       plain="true"
					       url="<%=request.getContextPath() + subMenu.getUrl()%>" 
					       title="<%=subMenu.getTitle()%>" 
					       id="<%=subMenu.getId() %>a_class"><%=subMenu.getTitle()%></a>
					</li>
					<%
						}
					%>
				</ul>
			</div>
			<%
				}
			%>
		</div>
	</div>
	<!-- 中部tab -->
	<div region="center" style="overflow: hidden;">
		<div class="easyui-tabs" fit="true" border="false" id="easyui-tabs_id" style="overflow: hidden;">
			<div title="首页" style="padding: 5px; overflow: hidden;">
				<div class="easyui-layout" fit="true">
					<div region="center" border="false" shadow="true">
						<div style=" width: 100%; height: 100%"
							class="easyui-bg">
							<h3 align="center">欢迎访问网站后台管理系统</h3>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 修改密码window -->
		<div id="_modify_password_window_" closed="true" class="easyui-dialog" title="修改密码" modal="true" iconCls="icon-edit" style="width:300px;height:180px;padding:5px;background: #fafafa;">
		    <div class="easyui-layout" fit="true" >
				<div region="center" border="false" shadow="true">
		            <form id="_update_password_form_" method="post">
					 <table cellpadding="0" cellspacing="0" border="0"   align="center" style="overflow:hidden;">
						<tbody>
							<tr >
								<th  align="right">原密码:</th>
								<td><input type="password" 
								           name="password" 
								           id="password" 
								           class="easyui-validatebox" 
								           required="true"
								           missingMessage="请输入原密码"
								           /></td>
								</td>
							</tr>
							<tr >
								<th  align="right">新密码:</th>
								<td><input type="password" 
								           name="newpassword" 
								           id="newpassword"  
								           class="easyui-validatebox" 
								           required="true"
								           missingMessage="请输入新密码"/></td>
								</td>
							</tr>
							<tr >
								<th align="right">确认密码:</th>
								<td><input type="password" 
								           name="newpassword2" 
								           id="newpassword2"
								           class="easyui-validatebox" 
								           required="true"
								           missingMessage="请输入确认新密码"/></td>
								</td>
							</tr>
						</tbody>
					</table>
		           </form>
				</div>
				<div region="south" border="false" style="text-align:center;height:30px;line-height:30px;">
					<a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" id="_update_password_btn_">修改</a>
					<a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" id="_cancel_password_btn_">取消</a>
			    </div>
		  </div>
	  </div>
	</div>
</body>
</html>