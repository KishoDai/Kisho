<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../comm/comm.jsp"%>
<script type="text/javascript">

window.closeWindow = function(){
	$("#_add_pubinfo_content_dialog_").window("close");
};

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
	$("#_add_infocontent_").click(function(){
		$("#_add_pubinfo_content_dialog_").window("open");
	});
});
</script>
</head>
 <form id="_add_form_" method="post" enctype="multipart/form-data">
 <input type="text" name="addCondition.infotyp" value="${infotyp }"/>
 <input type="text" name="addCondition.infocontent" id="infocontent" value="${addCondition.infocontent }"/>
<body class="easyui-layout">
	<div region="center" title="添加${infotypnam }" style="overflow: auto;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%"/>
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr >
					<th >${infotypnam }标题</th>
					<td><input type="text" name="addCondition.infotitle" style="width:300px;"/></td>
				</tr>
				<tr>
				  <th >${infotypnam }分类</th>
					<td><select name="addCondition.catid" style="width:305px;">
					      ${catalogOptions }
					    </select>
					</td>
				</tr>
				<tr>
				  <th >是否前端显示</th>
					<td><select name="addCondition.isshow" style="width:305px;">
					     ${isshowOptions }
					    </select>
					</td>
				</tr>
				<tr>
				  <th >显示起始时间</th>
					<td><input type="text" 
					           name="addCondition.showstarttime"
					           style="width:300px;" 
					           readonly="readonly"
					           class="easyui-datetimebox"/>
					</td>
				</tr>
				<tr>
				  <th >显示结束时间</th>
					<td><input type="text" 
					           name="addCondition.showendtime" 
                                style="width:300px;" 
                                readonly="readonly"
					            class="easyui-datetimebox" />
					</td>
				</tr>
				<tr >
					<th >${infotypnam }图片</th>
					<td >
						<div id="_img_div_" style="overflow:auto;widht:100%;height:75px">
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/><br/>
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/><br/>
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/>
						</div>
						<a href="javascript:void(0);" class="easyui-linkbutton" id="_add_img_" iconCls="icon-add">新增图片信息</a></td>
					</td>
				</tr>
				<tr >
					<th >${infotypnam }信息内容</th>
					<td  >
						<a href="javascript:void(0);" class="easyui-linkbutton" id="_add_infocontent_" iconCls="icon-add">新增${infotypnam }信息</a></td>
					</td>
				</tr>
				<tr >
					<td colspan="2" align="center">
					    <a href="javascript:void(0);" class="easyui-linkbutton" id="_add_btn_" iconCls="icon-save">保存</a> 
					    <a href="javascript:void(0);" class="easyui-linkbutton" id="_back_btn_" iconCls="icon-back">返回</a>
					</td>
				</tr>
			</tbody>
		</table>
		<div id="_add_pubinfo_content_dialog_" closed="true" class="easyui-window" title="添加信息内容" modal="true" iconCls="icon-add" style="width:1000px;height:400px;padding:5px;">
		    <div class="easyui-layout" fit="true" >
				<div region="center" border="false" shadow="true">
				     <iframe src="${pageContext.request.contextPath}/${module}/${submodule}_to_editinfocontent.action" width="100%"style="width:100%;height:100%" scrolling="no" frameborder="no"></iframe>
	               </table>
				</div>
			</div>
	    </div>
	</div>
</body>
</form>
</html>