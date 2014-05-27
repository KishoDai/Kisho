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
    $("#infocontent").val($("#hidden_div").html());
	
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
	
	$("#_add_infocontent_").click(function(){
		if($("#ff").attr("src")==''){
			$("#ff").attr("src","${pageContext.request.contextPath}/${module}/${submodule}_to_editinfocontent.action");
		}
		$("#_add_pubinfo_content_dialog_").window("open");
	});
	
	
});
</script>
</head>
<form id="_update_form_" method="post" enctype="multipart/form-data">
 <input type="hidden" name="updateCondition.infoid" value="${updateCondition.infoid }"/>
<input type="hidden" name="updateCondition.infocontent" id="infocontent" value=""/>
<body class="easyui-layout">
    <div id="hidden_div" style="display:none">${updateCondition.infocontent }</div>
	<div region="center" title="修改${infotypnam }" style="overflow: auto;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%"/>
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr >
					<th >${infotypnam }标题</th>
					<td><input type="text" name="updateCondition.infotitle" value="${updateCondition.infotitle }" style="width:300px;"/></td>
				</tr>
				<tr>
				    <th >${infotypnam }分类</th>
					<td><select name="updateCondition.catid" style="width:305px;">
					      ${catalogOptions }
					    </select>
					</td>
				</tr>
				</tr>
				<tr>
				  <th >是否前端显示</th>
					<td><select name="updateCondition.isshow" style="width:305px;">
					    ${isshowOptions }
					    </select>
					</td>
				</tr>
				<tr>
				  <th  >显示起始时间</th>
					<td><input type="text" 
					name="updateCondition.showstarttime" 
                    style="width:300px;"
                    value="${ updateCondition.showstarttime}"
                    readonly="readonly"
					class="easyui-datetimebox" />
					</td>
				</tr>
				<tr>
				  <th  >显示结束时间</th>
					<td><input type="text" 
					name="updateCondition.showendtime"  
					style="width:300px;" 
					value="${updateCondition.showendtime }"
					readonly="readonly"
					class="easyui-datetimebox" 
					/>
					</td>
				</tr>
				<tr >
					<th  >${infotypnam }图片</th>
					<td colspan="3">
						<div id="_img_div_" style="overflow:auto;widht:100%;height:75px">
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/><br/>
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/><br/>
							<input type="file"  name="imageList"/>图片信息:<input type="text" name="picremarkList" size="50"/>
						</div>
						<a href="javascript:void(0);" class="easyui-linkbutton"  id="_add_img_" iconCls="icon-add">新增图片信息</a>
						<a href="javascript:void(0);" class="easyui-linkbutton" id="_modify_img_btn_" iconCls="icon-edit" relid="${updateCondition.infoid}">修改已有图片</a>
					</td>
				</tr>
				<tr >
					<th  >${infotypnam }信息内容</th>
					<td  >
						<a href="javascript:void(0);" class="easyui-linkbutton" id="_add_infocontent_" iconCls="icon-edit">修改${infotypnam }信息</a></td>
					</td>
				</tr>
				<tr >
					<td colspan="2" align="center">
					    <a href="javascript:void(0);" class="easyui-linkbutton" id="_update_btn_" iconCls="icon-edit" >修改</a>
					   <a href="javascript:void(0);" class="easyui-linkbutton" id="_back_btn_"  iconCls="icon-back">返回</a>
					</td>
				</tr>
				
			</tbody>
		</table>
		<div id="_add_pubinfo_content_dialog_" closed="true" class="easyui-window" title="修改信息内容" modal="true" iconCls="icon-edit" style="width:1000px;height:400px;padding:5px;">
		    <div class="easyui-layout" fit="true" >
				<div region="center" border="false" shadow="true">
				     <iframe id="ff" src="" width="100%"style="width:100%;height:100%" scrolling="no" frameborder="no"></iframe>
	               </table>
				</div>
			</div>
	    </div>
    </div>
</body>
</form>
</html>