<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../comm/comm.jsp"%>
<title>查看资讯</title>
<script type="text/javascript">
 
</script>
</head>
<body class="easyui-layout">
  <div region="center" title="查看${infotypnam }" style="overflow: hidden;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%"/>
				<col width="50%" />
			</colgroup>
			<tbody>
				<tr >
					<th >${infotypnam }标题</th>
					<td><input type="text" name="viewCondition.infotitle" value="${viewCondition.infotitle }" disabled="disabled" style="width:300px;"/></td>
				</tr>
				<tr>
				    <th >${infotypnam }分类</th>
					<td><select name="viewCondition.catid" 
					            disabled="disabled" 
					            style="width:305px;">
					      ${catalogOptions }
					    </select>
					</td>
				</tr>
				<tr>
				  <th>是否前端显示</th>
					<td><select  name="viewCondition.isshow" style="width:305px;" disabled="disabled">
					     <option value="0">不显示</option>
					     <option value="1">显示</option>
					    </select>
					</td>
				</tr>
				<tr>
				    <th >发布时间</th>
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
				    <th >显示起始时间</th>
					<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.showstarttime }"/>
					</td>
				</tr>
				<tr>
				    <th >显示结束时间</th>
					<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.showendtime }"/>
					</td>
				</tr>
				<tr>
				    <th >创建者ID</th>
					<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.createid }"/>
					</td>
				</tr>
				<tr>
				    <th>修改者ID</th>
					<td><input type="text" disabled="disabled" style="width:300px;" value="${viewCondition.modifyid }"/>
					</td>
				</tr>
				<tr >
					<th >${infotypnam }图片</th>
					<td>
						 <a href="javascript:void(0);" class="easyui-linkbutton" id="_view_img_btn_" iconCls="icon-search" relid="${viewCondition.infoid }">查看图片</a>
					</td>
				</tr>
				<tr >
					<th >${infotypnam }信息内容</th>
				</tr>
				<tr>
				 <td colspan='2'> 
				        ${viewCondition.infocontent }
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