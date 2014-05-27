<%@page language="java"   pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<link href="${pageContext.request.contextPath }/resources/jqueryeasyui/themes/cupertino/easyui.css" type="text/css" rel="stylesheet">
<%-- <link href="${pageContext.request.contextPath }/resources/jqueryeasyui/themes/pepper-grinder/easyui.css" type="text/css" rel="stylesheet"> --%>
<link href="${pageContext.request.contextPath }/resources/jqueryeasyui/themes/icon.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.json-2.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/csms-card.js"></script>
<script type="text/javascript">
	//获取卡类型
	$.getCdType = function(cd_type){
		if(cd_type == "0"){
			cd_type = "会员卡";
		} else if(cd_type == "1"){
			cd_type = "临时卡";
		}
		return cd_type;
	};
	
	//获取参数url
	$.getParameterUrl = function(parameter){
		return "${pageContext.request.contextPath }/SystemAction/loadJson4Options.action?key=" + parameter;
	};
	
	$.showMessage = function(msg){
		$.messager.show({  
		      title:"提示框",  
		      msg:msg,  
		      showType:'show',
		      timeout:1000,
		      style:{  
		          right:'',  
		          top:document.body.scrollTop+document.documentElement.scrollTop,  
		          bottom:''  
		      }  
		  }); 
	};
	
</script>
