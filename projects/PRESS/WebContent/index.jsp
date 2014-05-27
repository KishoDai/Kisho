<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>登录首页</title>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<link href="resources/css/login/User_Login.css" type="text/css" rel=stylesheet>
<script type="text/javascript" src="resources/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
  window.callback = function(data){
	  if(data.flag==1||data.flag==2){
		  alert(data.message);
		  window.location="${pageContext.request.contextPath}/index.jsp";
	  } else if(data.flag==0){
		  window.location="${pageContext.request.contextPath}/Main.action";
	  } 
  };
  $(function(){
	 
	  $("#imagecode").click(function(){
		  $(this).attr("src","${pageContext.request.contextPath }/login/validateCode.action?isAjax="+new Date());
	  });
	 $("#adminid").focus(); 
	 $("#_login_btn_").click(function(){
		 if($.trim($("#adminid").val())==""){
			 alert("请输入用户名");
			 $("#adminid").focus();
			 return false;
		 }
		 if($.trim($("#password").val())==""){
			 alert("请输入密码");
			 $("#password").focus();
			 return false;
		 }
		 if($.trim($("#validatecode").val())==""){
			 alert("请输入验证码");
			 $("#validatecode").focus();
			 return false;
		 } 
		 $.post('${pageContext.request.contextPath}/login/check.action',
				 $("#_form_").serialize(),
				 callback,
				 "json"
	     );
	 });
	var location = window.parent.location.href;
	if(location.lastIndexOf("index.jsp")<0){
		window.parent.parent.location="${pageContext.request.contextPath}/index.jsp";
	}
	 if("${adminid}" != null && "${adminid}"!=""){
		  $("#password").focus();
	  }
  });
</script>
</head>
<body id="userlogin_body">
	<form name="_form_" id="_form_" method="post" onsubmit="return false;">
		<div id="user_login">
			<dl>
				<dd id="user_top">
					<ul>
						<li class="user_top_l"></li>
						<li class="user_top_c"></li>
						<li class="user_top_r"></li>
					</ul>
				</dd>
				<dd id="user_main">
					<ul>
						<li class="user_main_l"></li>
						<li class="user_main_c">
							<div class="user_main_box">
								<ul>
									<li class="user_main_text">用户名：</li>
									<li class="user_main_input">
									  <input class="TxtUserNameCssClass" 
									         id="adminid" 
									         type="text"
										     name="adminid"
										     maxlength="20"
										     value="${adminid}"
										      />
								    </li>
								</ul>
								<ul>
									<li class="user_main_text">密&nbsp;&nbsp; 码：</li>
									<li class="user_main_input">
									   <input class="TxtPasswordCssClass" 
									          id="password" 
									          type="password"
										      name="password"
										      value="${password}"
										      maxlength="20" />
								    </li>
								</ul>
								<ul>
									<li class="user_main_text">验证码：</li>
									<li class="user_main_input">
									<input    class="TxtValidateCodeCssClass"
									          id="validatecode" 
									          type="text"
										      name="validatecode"
										      maxlength="20" />
								    </li>
									 <li ><img src="${pageContext.request.contextPath }/login/validateCode.action?isAjax"+new Date()
									            id="imagecode"
									            alt="请输入此验证码，如看不清请点击刷新" 
									            style="cursor: pointer" 
									            />
									 </li>
								</ul>
							</div>
						</li>
						<li class="user_main_r">
						   <input class="IbtnEnterCssClass"
							      id="_login_btn_"
							      style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px"
							      type="image" 
							      src="resources/images/user_botton.gif"
							      name="_login_btn_">
						   </li>
					</ul>
				<dd id="user_bottom">
					<ul>
						<li class="user_bottom_l"></li>
						<li class="user_bottom_c"></li>
						<li class="user_bottom_r"></li>
					</ul>
				</dd>
			</dl>
		</div>
	</form>
</body>
</html>
