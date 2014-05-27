$.extend({
	checkEmpty:function(obj,message){
		var flag = false;
		$(obj).next().text("");
		var val = $(obj).val();
		if(val==null||$.trim(val)==""){
			$(obj).next().text(message);
			flag = true;
		}
		return flag;
	},
	checkMaxlength : function(obj,maxlength,message){
		var flag = false;
		$(obj).next().text("");
		var val = $(obj).val();
		if(val.length>maxlength){
			$(obj).next().text(message);
			flag = true;
		}
		return flag;
		
	}
	
});