<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../comm/comm.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/ckfinder/ckfinder.js"></script>
<script type="text/javascript">
$(function(){
	 // 启用 CKEitor 的上传功能，使用了 CKFinder 插件
    var ckeditor = CKEDITOR.replace( 'infocontent', {
        filebrowserBrowseUrl        : '${pageContext.request.contextPath}/resources/ckfinder/ckfinder.html',
        filebrowserImageBrowseUrl   : '${pageContext.request.contextPath}/resources/ckfinder/ckfinder.html?Type=Images',
        filebrowserFlashBrowseUrl   : '${pageContext.request.contextPath}/resources/ckfinder/ckfinder.html?Type=Flash',
        filebrowserUploadUrl        : '${pageContext.request.contextPath}/resources/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files',
        filebrowserImageUploadUrl   : '${pageContext.request.contextPath}/resources/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images',
        filebrowserFlashUploadUrl   : '${pageContext.request.contextPath}/resources/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash'
    });
	var doc = window.parent.document;
	ckeditor.setData( doc.getElementById("infocontent").value );
	 $("#infocontent").val(doc.getElementById("infocontent").value);
	 
	$("#_save_btn_2_").click(function(){
		doc.getElementById("infocontent").value = ckeditor.getData();
		window.parent.closeWindow();
	});

	$("#_cancel_btn_2_").click(function(){
		ckeditor.setData( doc.getElementById("infocontent").value );
		$("#infocontent").val(doc.getElementById("infocontent").value);
		window.parent.closeWindow();
	}); 
	
});
</script>
</head>
 <body>
   <div style="overflow:auto;">
         <span style="align:center">
	         <a href="javascript:void(0);" class="easyui-linkbutton" id="_save_btn_2_" iconCls="icon-save">保存</a>
	         <a href="javascript:void(0);" class="easyui-linkbutton" id="_cancel_btn_2_" iconCls="icon-cancel">取消</a>
         </span>
        <textarea name="infocontent" id="infocontent"></textarea>
     </div>
</body>
</html>