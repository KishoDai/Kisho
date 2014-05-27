<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../comm/comm.jsp"%>
<script type="text/javascript">
$(function(){
	$("#_add_btn_").click(function(){
		 if(!$("#_add_form_").form("validate")){
			 return;
		 }
		 //1.产品名称校验
		 //TODO...
		 //2.起始价和结束价校验
		 //TODO...
	     $("#_add_form_").attr("action","${pageContext.request.contextPath}/${module}/${submodule}_do_add.action").submit();
	});
});
</script>
</head>
 <form id="_add_form_" method="post" enctype="multipart/form-data">
 <input type="hidden" name="addCondition.infotyp" value="${infotyp }"/>
<body class="easyui-layout">
	<div region="center" title="添加产品" style="overflow: hidden;">
		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="easyui-bg">
			<colgroup> 
				<col width="20%" />
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr>
					<th >产品名称</th>
					<td><input type="text"
					           name="addCondition.pronam"
						       style="width: 300px;" 
						       class="easyui-validatebox"
						       missingMessage="请输入产品名称" 
						       required="true" />
					</td>
				</tr>
				<tr>
					<th >产品分类</th>
					<td>
					    <select name="addCondition.catid"
						        style="width: 305px;"
						        class="easyui-validatebox"
						        missingMessage="请选择产品分类" 
						        required="true" > 
						      ${catalogOptions }
						</select>
				    </td>
				</tr>
				<tr>
					<th >是否上架</th>
					<td>
					    <select name="addCondition.isshow"
						        style="width: 305px;"
						        class="easyui-validatebox"
						        missingMessage="请选择产品是否上架" 
						        required="true"> 
						     ${isshowOptions }
					    </select>
					</td>
				</tr>
				<tr>
					<th >价格范围</th>
					<td>
					    <input type="text" 
					           name="addCondition.startprice"
						       style="width: 135px;" 
						       class="easyui-numberbox"
						       missingMessage="请输入产品起始价"
						       required="true"/>~ 
						<input type="text"
						       name="addCondition.endprice" 
						       style="width: 135px;"
						       class="easyui-numberbox"
						       missingMessage="请输入产品结束价"
						       required="true"
						        />元
				    </td>
					</td>
				</tr>
				<tr>
					<th >产品简介</th>
					<td><textarea name="addCondition.proprofile" 
					              cols="75" 
					              rows="4"
					              class="easyui-validatebox"
						          missingMessage="请输入产品简介" 
						          required="true"></textarea>
					</td>
				</tr>
				<tr>
					<th >产品功能</th>
					<td><textarea name="addCondition.profun" 
					              cols="75" 
					              rows="4"
					              class="easyui-validatebox"
						          missingMessage="请输入产品功能" 
						          required="true"></textarea>
					</td>
				</tr>
				<tr>
					<th >产品图片</th>
					<td>
						<div id="_img_div_" style="overflow:auto;widht:100%;height:75px">
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/><br/>
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/><br/>
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/>
						</div> 
						<a href="javascript:void(0);" class="easyui-linkbutton" id="_add_img_" iconCls="icon-add">新增图片信息</a></td>
				</tr>
				<tr>
					<td colspan="2" align="center" >
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_add_btn_" iconCls="icon-save">保存</a> 
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_back_btn_" iconCls="icon-back">返回</a>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</form>
</html>
