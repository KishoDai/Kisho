<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
   $(function(){
	   var re_id = $('#roleDataListTable').datagrid('getSelected').re_id;
	   
	   $('#roleFuncTreeUl').tree({  
           url:  "${pageContext.request.contextPath }/FuncAction/loadJsonByFuncId.action",
           lines:true,
           checkbox:true,
           cascadeCheck:true,
           onlyLeafCheck:true,
           onCheck:function(node,checked){
        	   var roleFuncRelPrefix = '${pageContext.request.contextPath }/RoleAction/';
       		   var aRfrObj = new Object();
        	   if(checked){
	       		   aRfrObj['addRoleFuncRel.re_id'] = re_id;
	       		   aRfrObj['addRoleFuncRel.fn_id'] = node.id;
        		   $.post(roleFuncRelPrefix + 'addRoleFuncRel.action',
        				   aRfrObj,
            			   null,'json');
        	   } else {
        		   aRfrObj['operRoleFuncRel.re_id'] = re_id;
        		   aRfrObj['operRoleFuncRel.fn_id'] = node.id;
        		   $.post(roleFuncRelPrefix + 'delRoleFuncRel.action',
        				   aRfrObj,
            			   null,'json'); 
        	   }
           }
       });
	   
	   var operRoleFuncRelObj = new Object();
	   operRoleFuncRelObj['operRoleFuncRel.re_id'] = re_id;
	   $.post('${pageContext.request.contextPath }/RoleAction/queryJson4RoleFuncRelByRoleId.action',
			   operRoleFuncRelObj,
			   function(data){
	              for(var i = 0; i < data.length; i++){
	            	  var node = $('#roleFuncTreeUl').tree('find', data[i].fn_id);
                         $('#roleFuncTreeUl').tree('check',node.target);
	              }
	           },'json');
	   
   });
</script>
<div class="easyui-bg" style="width:100%;height:100%">
    <ul id="roleFuncTreeUl"></ul>
</div>