<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>登录首页</title>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath }/resources/css/login/User_Login.css" type="text/css" rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
  $(function(){
	 
// 	  $("#imagecode").click(function(){
// 		  $(this).attr("src","${pageContext.request.contextPath }/LoginAction/validateCode.action");
// 	  });
	  
	 $("#ur_id").focus(); 
	 
	 $("#_login_btn_").click(function(){
		 if($.trim($("#ur_id").val())==""){
			 alert("请输入用户名");
			 $("#ur_id").focus();
			 return false;
		 }
		 
		 if($.trim($("#ur_password").val())==""){
			 alert("请输入密码");
			 $("#ur_password").focus();
			 return false;
		 }
		 
// 		 if($.trim($("#validatecode").val())==""){
// 			 alert("请输入验证码");
// 			 $("#validatecode").focus();
// 			 return false;
// 		 } 
		 
		 $.post('${pageContext.request.contextPath}/LoginAction/check.action',
				 $("#_form_").serialize(),
				 function(data){
					  if(data.flag == "0"){
						  window.location="${pageContext.request.contextPath}/main.action";
					  } else {
						  alert(data.message);
						  window.location="${pageContext.request.contextPath}/login.jsp";
					  } 
					  return false;
		         },'json');
	 });

	 if("${user.ur_id}" != null && "${user.ur_id}"!=""){
		  $("#ur_password").focus();
	  }
	 
  });
</script>
</head>
<body id="userlogin_body">
	<form name="_form_" id="_form_" method="post" >
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
									         id="ur_id" 
										     name="user.ur_id"
										     maxlength="20"
										     value="${user.ur_id}"
										      />
								    </li>
								</ul>
								<ul>
									<li class="user_main_text">密&nbsp;&nbsp; 码：</li>
									<li class="user_main_input">
									   <input class="TxtPasswordCssClass" 
									          id="ur_password" 
									          type="password"
										      name="user.ur_password"
										      maxlength="20" />
								    </li>
								</ul>
<!-- 								<ul> -->
<!-- 									<li class="user_main_text">验证码：</li> -->
<!-- 									<li class="user_main_input"> -->
<!-- 									<input  class="TxtValidateCodeCssClass" -->
<!-- 									          id="validatecode"  -->
<!-- 									          type="text" -->
<!-- 										      name="validatecode" -->
<!-- 										      maxlength="20" /> -->
<!-- 								    </li> -->
<%-- 									 <li ><img src="${pageContext.request.contextPath }/LoginAction/validateCode.action"  --%>
<!-- 									            id="imagecode" -->
<!-- 									            alt="请输入此验证码，如看不清请点击刷新"  -->
<!-- 									            style="cursor: pointer"  -->
<!-- 									            /> -->
<!-- 									 </li> -->
<!-- 								</ul> -->
							</div>
						</li>
						<li class="user_main_r">
						   <input class="IbtnEnterCssClass"
							      id="_login_btn_"
							      style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px; border-right-width: 0px"
							      type="image" 
							      src="resources/images/user_botton.gif"
							      name="_login_btn_" onclick="return false;">
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
