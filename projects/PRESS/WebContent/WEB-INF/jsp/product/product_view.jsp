<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../comm/comm.jsp"%>
<title>查看产品</title>
<script type="text/javascript">
 
</script>
</head>
<body class="easyui-layout">
  <div region="center" title="查看产品" style="overflow: hidden;">
	<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
		<colgroup>
			<col width="20%"/>
			<col width="50%" />
		</colgroup>
		<tbody>
			<tr >
				<th >产品名称</th>
				<td><input type="text" name="viewCondition.pronam" value="${viewCondition.pronam }" disabled="disabled" style="width:300px;"/></td>
			</tr>
			<tr>
			    <th >产品分类</th>
				<td><select id="catid" name="viewCondition.catid" disabled="disabled" style="width:305px;">
				      ${catalogOptions }
				    </select>
				</td>
			</tr>
			<tr>
			    <th >是否上架</th>
				<td><select id="isshow" name="viewCondition.isshow" disabled="disabled"  style="width:305px;">
				      ${isshowOptions }
				    </select>
				</td>
			</tr>
			<tr>
			    <th >上架时间</th>
				<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.pubtime }"/>
				</td>
			</tr>
			<tr>
			    <th >创建时间</th>
				<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.createtime }"/>
				</td>
			</tr>
			<tr>
			    <th >最后修改时间</th>
				<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.lastmodifytime }"/>
				</td>
			</tr>
			<tr>
			    <th >创建者ID</th>
				<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.createid }"/>
				</td>
			</tr>
			<tr>
			    <th >修改者ID</th>
				<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.modifyid }"/>
				</td>
			</tr>
			<tr >
				<th >价格范围</th>
				<td><input type="text" name="viewCondition.startprice" value="${viewCondition.startprice }" disabled="disabled" style="width:135px;"/>~
				    <input type="text" name="viewCondition.endprice" value="${viewCondition.endprice }" disabled="disabled" style="width:135px;"/>元</td>
				</td>
			</tr>
			<tr >
				<th >产品简介</th>
				<td ><textarea name="viewCondition.proprofile" cols="60%" disabled="disabled" cols="75" rows="4">${viewCondition.proprofile }</textarea></td>
			</tr>
			<tr >
				<th>产品功能</th>
				<td ><textarea name="viewCondition.profun" cols="60%" disabled="disabled" cols="75" rows="4">${viewCondition.profun }</textarea></td>
			</tr>
			<tr >
				<th >产品图片</th>
				<td colspan="2">
					 <a href="javascript:void(0);" class="easyui-linkbutton" id="_view_img_btn_" iconCls="icon-search" relid="${viewCondition.proid }">查看图片</a>
				</td>
			</tr>
			<tr >
				<td colspan="2" align="center">
				   <a href="javascript:void(0);" class="easyui-linkbutton" id="_back_btn_" iconCls="icon-back">返回</a>
				</td>
			</tr>
		</tbody>
	</table>
  </div>
</body>
</html>