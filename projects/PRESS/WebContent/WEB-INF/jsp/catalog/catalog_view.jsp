<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../comm/comm.jsp"%>
<title>查看分类</title>
<script type="text/javascript">
 
</script>
</head>
<body class="easyui-layout">
  <div region="center" title="查看${cattypnam }分类" style="overflow: hidden;">
		<table cellpadding="0" cellspacing="0" border="0"  width="100%" class="easyui-bg">
			<colgroup>
				<col width="20%" align="right"/>
				<col width="40%" align="left"/>
			</colgroup>
			<tbody>
				<tr >
					<th > ${cattypnam }分类名称:&nbsp;</th>
					<td><input type="text" value="${viewCondition.catnam }"  disabled="disabled" size="50"/></td>
				</tr>
				<tr >
					<th class="ui-widget-header ">${cattypnam }分类排序码:&nbsp;</th>
					<td><input type="text" value="${viewCondition.sortnum }"  disabled="disabled" size="50"/></td>
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