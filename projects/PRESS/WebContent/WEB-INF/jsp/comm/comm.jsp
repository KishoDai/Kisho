<%@page language="java"   pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link href="${pageContext.request.contextPath }/resources/jqueryeasyui/themes/default/easyui.css" type="text/css" rel="stylesheet">
<link href="${pageContext.request.contextPath }/resources/jqueryeasyui/themes/icon.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
window.showUpdateImageDialog = function(){
	$("#_modify_img_btn_").click();
};
$(function(){
	var prefix = "${pageContext.request.contextPath}/${module}/${submodule}";
	 //添加查询事件
	$("#_query_btn_").click(function(){
		 $.post(prefix+"_setQueryCondition.action",$("#_query_form_").serialize(),function(data){
			 if(data.flag == 1){
				 $("#table").datagrid({
					 url: prefix+"_getListJSON.action"
				 }); 
				// $("#_query_form_").attr("action",prefix+"_to_index.action").submit();
			 }
		 },'json');
	});
	//添加清空事件
	$("#_clear_btn_").click(function(){
		$("input[name^='query']").val("");
		$("select[name^='query']>option[value='']").attr("selected","selected");
	});
	//添加刷新事件
	$("#_reload_btn_").click(function(){
		$("#easyui-tabs_id").tabs("select", $("#${submenuid}a_class").attr("title"));
	});
	
	//添加图片事件
	$("#_add_img_").click(function(){
		$("#_img_div_").append("<br/><input type=\"file\"  name=\"imageList\"/>图片信息:<input type=\"text\" name=\"picremarkList\" size='50'/>");
	});
	
	//查看图片
	$("#_view_img_btn_").click(function(){
		window.showModalDialog("${pageContext.request.contextPath}/picinfo/picinfo_to_view.action?picInfo.relid="+$(this).attr("relid"),null,"dialogWidth=800px;dialogHeight=500px;scroll:no");
	});
	//修改图片
	$("#_modify_img_btn_").click(function(){
		window.showModalDialog("${pageContext.request.contextPath}/picinfo/picinfo_to_update.action?picInfo.relid="+$(this).attr("relid"),window,"dialogWidth=800px;dialogHeight=500px;scroll:no");
	});
	
	//返回事件
	$("#_back_btn_").click(function(){
		window.location = prefix+"_to_index.action";
	});
	
});
</script>