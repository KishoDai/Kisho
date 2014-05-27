<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
   var funcPrefix = "${pageContext.request.contextPath }/FuncAction/";
   
   window.deleteFunc = function(node,item){
		    $.post(funcPrefix + $(item.target).attr("name") + ".action",
		    	   "operEntity.fn_id=" + node.id + "&operEntity.fn_isleaf=" + node.attributes.is_leaf,
		    	   function(data){
					   if(data.flag == "0"){ 
			 	        	$('#funcTreeUl').tree("reload");
			 	        } else{
			 	        	$.messager.alert('提示框',data.message,'warning');
			 	        }
		           },
		           'json');
	};
	
      $('#funcTreeUl').tree({  
          url: funcPrefix + "loadJsonByFuncId.action",
          lines:true,
          dnd:true,
          onBeforeDrop:function(target, source, point){
      		   var sourceNode = $('#funcTreeUl').tree('getData',source).target;
      		   var targetNode = $('#funcTreeUl').tree('getNode',target);
      		   
              if(targetNode.attributes.is_leaf == "1"){
           	   $.messager.alert('提示框','不能移到功能上','error');
           	   return false;
              }
              $.post(funcPrefix + 'move.action', 
           		   'target_fn_id=' +  targetNode.id + "&source_fn_id=" + sourceNode.id,
           		   function(data){
           	          if(data.flag != '0'){
           	        	  $.messager.alert('提示框',data.message);
           	          } else{
           	        	  $('#modifyFuncDiv').html("").panel('close');
           	          }
                      },'json');
          },
          animate:true,
          onContextMenu: function(e,node){  
       	     e.preventDefault();  
       	     $(this).tree('select',node.target);  
       	     var obj = "";
       	     if(node.id == "0"){
       	    	 obj = "funcMenuDiv";
       	     } else if(node.attributes.is_leaf == "0"){
       	    	 obj = "funcMenuDiv1";
       	     } else{
       	    	 obj = "funcMenuDiv2";
       	     }
       	     
       	     $('#' + obj).menu({  
      	    	    onClick:function(item){ 
      	    	    	var name = $(item.target).attr("name");
					    if("removeFuncGroup" == name){
							$.messager.confirm('Confirm','您确认删除该功能组及功能组下所有的信息么?',function(r){  
							    if (r){  
							    	deleteFunc(node,item);
							    }  
							}); 
      	    	    	} else if("removeFunc" == name){
      	    	    		$.messager.confirm('Confirm','您确认删除该功能么?',function(r){  
							    if (r){  
							    	deleteFunc(node,item);
							    }  
						    }); 
      	    	        } else {
      	    	       		$.post(funcPrefix + $(item.target).attr("name") + ".action",
      	    	       			   "operEntity.fn_id=" + node.id + "&operEntity.fn_isleaf=" + node.attributes.is_leaf,
      	    	       			   function(data){
      	    	       			      $('#modifyFuncDiv').html(data).panel('open');
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
       		$.post(funcPrefix + (node.attributes.is_leaf == "1" ? "toModifyFuncPage.action" : "toModifyFuncGroupPage.action"),
       			  'operEntity.fn_id=' + node.id,
       			   function(data){
       			      $('#modifyFuncDiv').html(data).panel('open');
       		       },
       		       'html');
       	}
      });  

	$('#modifyFuncDiv').panel({  
		  title: '功能维护',
		  width:"100%",
		  height:"100%"
	});  

});
</script>
<table style="width:100%;height:100%">
	<tr>
		<td style="width:200px"  valign="top" class="easyui-bg">
			<div border="true" 
				style="width: 200px; height: 100%; padding1: 1px; overflow: hidden;">
                       <ul id="funcTreeUl"></ul>
					<div id="funcMenuDiv" class="easyui-menu" style="width:120px;">  
					     <div name="toAddFuncGroupPage">添加功能组</div>  
					     <div name="toAddFuncPage">添加功能</div> 
					</div>  
                       <div id="funcMenuDiv1" class="easyui-menu" style="width:120px;">  
					     <div name="toAddFuncGroupPage">添加功能组</div>  
					     <div name="removeFuncGroup">删除功能组</div>
					     <div name="toAddFuncPage">添加功能</div>  
					</div>  
					<div id="funcMenuDiv2" class="easyui-menu" style="width:120px;">  
					     <div name="removeFunc">删除功能</div>
					</div> 
			</div>
		</td>
		<td valign="top" style="width:100%;">
		    <div id="modifyFuncDiv" style="padding1: 1px;"></div>
		</td>
	</tr>
</table>
