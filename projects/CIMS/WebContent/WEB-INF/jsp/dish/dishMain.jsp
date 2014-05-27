<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
	   var dishPrefix = "${pageContext.request.contextPath }/DishAction/";
	   
	   window.deleteDish = function(node,item){
		   var sp_id = node.attributes.sp_id;
  	    	if(!sp_id){
  	    		sp_id = "";
  	    	}
  	    	var is_leaf = node.attributes.is_leaf;
  	    	var dh_id = node.id;
		    $.post(dishPrefix + $(item.target).attr("name") + ".action",
		    	   "operEntity.sp_id=" + sp_id +"&operEntity.dh_id=" + dh_id + "&operEntity.dh_isleaf=" + is_leaf,
		    	   function(data){
					   if(data.flag == "0"){ 
			 	        	$.messager.alert('提示框',data.message);
			 	        	$('#dishTreeUl').tree("reload");
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'error');
			 	        }
		     },'json');
		};
		
       $('#dishTreeUl').tree({  
           url: dishPrefix + "loadJsonByShopId.action",
           lines:true,
           dnd:true,
           onBeforeDrop:function(target, source, point){
       		   var sourceNode = $('#dishTreeUl').tree('getData',source).target;
       		   var targetNode = $('#dishTreeUl').tree('getNode',target);
       		   
               if(targetNode.attributes.is_leaf == "1"){
            	   $.messager.alert('提示框','不能移到菜品上','error');
            	   return false;
               }
               $.post(dishPrefix + 'move.action', 
            		   'target_dh_id=' +  targetNode.id + "&source_dh_id=" + sourceNode.id + "&shop_id=" + sourceNode.attributes.sp_id,
            		   function(data){
            	          if(data.flag != '0'){
            	        	  $.messager.alert('提示框',data.message,'error');
            	          } else{
            	        	  $.messager.alert('提示框',data.message);
            	        	  $('#modifyDishDiv').html("").panel('close');
            	          }
                       },'json');
           },
           animate:true,
           onContextMenu: function(e,node){  
        	     e.preventDefault();  
        	     $(this).tree('select',node.target);  
        	     var obj = "";
        	     if(node.id == "0"){
        	    	 obj = "dishMenuDiv";
        	     } else if(node.attributes.is_leaf == "0"){
        	    	 obj = "dishMenuDiv1";
        	     } else{
        	    	 obj = "dishMenuDiv2";
        	     }
        	     
        	     $('#' + obj).menu({  
       	    	    onClick:function(item){ 
       	    	    	var name = $(item.target).attr("name");
						if("removeDishSort" == name){
							$.messager.confirm('Confirm','您确认删除该菜品分类及该菜品分类下所有的菜品信息么?',function(r){  
							    if (r){  
							    	deleteDish(node,item);
							    }  
							}); 

       	    	    	} else if("removeDish" == name){
       	    	    		$.messager.confirm('Confirm','您确认删除该菜品么?',function(r){  
							    if (r){  
							    	deleteDish(node,item);
							    }  
							}); 
       	    	        } else {
       	    	        	var sp_id = node.attributes.sp_id;
       	    	   	    	if(!sp_id){
       	    	   	    		sp_id = $('#shopDataListTable').datagrid('getSelected').sp_id;
       	    	   	    	}
       	    	   	         $.post(dishPrefix + $(item.target).attr("name") + ".action",
       	    	   	    	    "operEntity.sp_id=" + sp_id +"&operEntity.dh_id=" + node.id + "&operEntity.dh_isleaf=" + node.attributes.is_leaf,
		       			    	 function(data){
		       						$('#modifyDishDiv').html(data).panel('open');
		       			         },
       			           'html');
       	    	        }
       	    	    }  
        	     }).menu('show',{  
        	         left: e.pageX,  
        	         top: e.pageY  
        	     });
        	},
        	onClick:function(node){
        		if(node.id == "0"){
        			return;
        		}
        		var url = "";
        		if(node.attributes.is_leaf == "1"){
        		   url = dishPrefix + "toModifyDishPage.action";
        		} else {
        		   url = dishPrefix + "toModifyDishSortPage.action";
        		}
     		   $.post(url,
    				  "operEntity.dh_id=" + node.id + "&operEntity.sp_id=" + node.attributes.sp_id,
    				  function(data){
     			          $('#modifyDishDiv').html(data).panel('open');
    		          },
    		          'html');
        	}
       });  

		$('#modifyDishDiv').panel({  
			  title: '菜品维护',
			  width:"100%",
			  height:"100%"
		});  

	});
</script>
<table style="width:100%;height:90%">
	<tr>
		<td style="width:200px"  valign="top" class="easyui-bg">
			<div border="true" 
				style="width: 200px; height: 100%; padding1: 1px; overflow: hidden;">
                       <ul id="dishTreeUl"></ul>
					<div id="dishMenuDiv" class="easyui-menu" style="width:120px;display:none">  
					     <div name="toAddDishSortPage">添加菜品分类</div>  
					     <div name="toAddPage">添加菜品</div>  
					</div>  
                       <div id="dishMenuDiv1" class="easyui-menu" style="width:120px;display:none">  
					     <div name="toAddDishSortPage">添加菜品分类</div>  
					     <div name="removeDishSort">删除菜品分类</div>
					     <div name="toAddPage">添加菜品</div>  
					</div>  
					<div id="dishMenuDiv2" class="easyui-menu" style="width:120px;display:none">  
					     <div name="removeDish">删除菜品</div>
					</div> 
			</div>
		</td>
		<td valign="top" style="width:100%;">
		    <div id="modifyDishDiv" style="padding1: 1px;"></div>
		</td>
	</tr>
</table>
