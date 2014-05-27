<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../comm/comm.jsp"%>
<script type="text/javascript">
$(function(){
	$("#_update_btn_").click(function(){
		 //TODO...
		 if(!$("#_update_form_").form("validate")){
			 return;
		 }
		 //1.产品名称校验
		 //TODO...
		 //2.起始价和结束价校验
		 //TODO...
	     $("#_update_form_").attr("action","${pageContext.request.contextPath}/${module}/${submodule}_do_update.action").submit();
	});
});
</script>
</head>
<form id="_update_form_" method="post" enctype="multipart/form-data">
<body class="easyui-layout">
	<div region="center" title="修改产品" style="overflow: hidden;">
      <input type="hidden" name="updateCondition.proid" value="${updateCondition.proid }"/>
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%"/>
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr >
					<th >产品名称</th>
					<td><input type="text" 
					           name="updateCondition.pronam" 
					           value="${updateCondition.pronam }" 
					           style="width:300px;"
					           class="easyui-validatebox"
						       missingMessage="请输入产品名称" 
						       required="true"/></td>
				</tr>
				<tr>
				    <th class="ui-widget-header ">产品分类</th>
					<td>
					     <select id="catid" 
					             name="updateCondition.catid" 
					             style="width:305px;"
					             class="easyui-validatebox"
						         missingMessage="请选择产品分类" 
						         required="true" >
					      ${catalogOptions }
					    </select>
					</td>
				</tr>
				<tr>
				    <th >是否上架</th>
					<td><select id="isshow" 
					            name="updateCondition.isshow" 
					            style="width:305px;"
					            class="easyui-validatebox"
						        missingMessage="请选择产品是否上架" 
						        required="true" >
					      ${isshowOptions }
					    </select>
					</td>
				</tr>
				<tr >
					<th class="ui-widget-header ">价格范围</th>
					<td>
					    <input type="text" 
					           name="updateCondition.startprice" 
					           value="${updateCondition.startprice }" 
					           style="width:135px;"
					           class="easyui-numberbox"
						       missingMessage="请输入产品起始价"
						       required="true"/>~
					    <input type="text" 
					           name="updateCondition.endprice" 
					           value="${updateCondition.endprice }" 
					           style="width:135px;"
					           class="easyui-numberbox"
						       missingMessage="请输入产品结束价"
						       required="true"/>元</td>
					</td>
				</tr>
				<tr >
					<th >产品简介</th>
					<td ><textarea name="updateCondition.proprofile" 
					               cols="75" 
					               rows="4"
					               class="easyui-validatebox"
						           missingMessage="请输入产品简介" 
						           required="true">${updateCondition.proprofile }</textarea></td>
				</tr>
				<tr >
					<th >产品功能</th>
					<td ><textarea name="updateCondition.profun" 
					               cols="75" 
					               rows="4"
					               class="easyui-validatebox"
						           missingMessage="请输入产品功能" 
						           required="true" >${updateCondition.profun }</textarea></td>
				</tr>
				<tr >
					<th >产品图片</th>
					<td colspan="2">
						<div id="_img_div_" style="overflow:auto;widht:100%;height:75px">
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/><br/>
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/><br/>
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/>
						</div>
						<a href="javascript:void(0);" class="easyui-linkbutton"  id="_add_img_" iconCls="icon-add">新增图片信息</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" id="_modify_img_btn_" iconCls="icon-edit" relid="${updateCondition.proid }">修改已有图片</a>
					</td>
				</tr>
				<tr >
					<td colspan="2" align="center">
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_update_btn_" iconCls="icon-edit" >修改</a>
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_back_btn_"  iconCls="icon-back">返回</a>
					</td>
				</tr>
			<tbody>
		</table>
    </div>
</body>
</form>
</html>

   