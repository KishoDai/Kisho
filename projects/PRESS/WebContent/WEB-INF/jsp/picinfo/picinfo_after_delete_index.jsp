<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="picInfoList" begin="0" status="s">
	<div class="slide">
		<a href="#" title="">
		<img src="${pageContext.request.contextPath }/resources/images/fileupload/${picnam}"
			width="570" height="270" alt="${picremark }"> </a>
		<div class="caption" style="bottom: 0">
			<p>
				<a href="javascript:void(0);" id="${picid }" relid="${relid }">删除</a>
			</p>
		</div>
	</div>
</s:iterator>
