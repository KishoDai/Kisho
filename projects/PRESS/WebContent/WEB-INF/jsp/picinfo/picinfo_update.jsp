<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>修改产品图片</title>
	
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/global.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/slides.min.jquery.js"></script>
	<script type="text/javascript">
	    window.init = function(){
	    	$('#slides').slides({
				preload: true,
				preloadImage: '${pageContext.request.contextPath }/resources/images/loading.gif',
				play: 5000,
				pause: 2500,
				hoverPause: true,
				animationStart: function(current){
					$('.caption').animate({
						bottom:-35
					},100);
					if (window.console && console.log) {
						// example return of current slide number
						console.log('animationStart on slide: ', current);
					};
				},
				animationComplete: function(current){
					$('.caption').animate({
						bottom:0
					},200);
					if (window.console && console.log) {
						// example return of current slide number
						console.log('animationComplete on slide: ', current);
					};
				},
				slidesLoaded: function() {
					$('.caption').animate({
						bottom:0
					},200);
				}
			});
	    };
	    window.callback = function(data){
	    	if(data.flag==1){
				   window.close();
				   window.dialogArguments.showUpdateImageDialog();
			}
	    };
		$(function(){
			init();
			$(".caption>p>a").click(function(){
				$.getJSON("${pageContext.request.contextPath }/picinfo/picinfo_do_delete.action?picInfo.picid="+$(this).attr("id")+"&picInfo.relid="+$(this).attr("relid"),
						callback);
			});
		    
		});
	</script>
</head>
<body>
<form id="_form_" name="_form_" method="post">
	<div id="container">
		<div id="example">
			<div id="slides">
				<div class="slides_container">
				<s:iterator value="picInfoList" begin="0" status="s" > 
				   <div class="slide">
						<a href="#" title="" ><img src="${pageContext.request.contextPath }/resources/images/fileupload/${picnam}" width="570" height="270" alt="${picremark }"></a>
						<div class="caption" style="bottom:0">
							<p><a href="javascript:void(0);" id="${picid }" relid="${relid }">删除</a></p>
						</div>
					</div>
		          </s:iterator>
				</div>
				<a href="#" class="prev"><img src="${pageContext.request.contextPath }/resources/images/arrow-prev.png" width="24" height="43" alt="前一张"></a>
				<a href="#" class="next"><img src="${pageContext.request.contextPath }/resources/images/arrow-next.png" width="24" height="43" alt="下一张"></a>
			</div>
			<img src="${pageContext.request.contextPath }/resources/images/example-frame.png" width="739" height="341" alt="产品图片" id="frame">
		</div>
	</div>
	</form>
</body>
</html>