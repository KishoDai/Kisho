//从IC卡中读取卡美食呵护信息
$.readCard = function(){
	//TODO....
	$.messager.alert("提示框","读取卡信息失败",'error');
	//如果读取卡信息失败，则返回为空
	var card = new Object();
	card.cd_id = "GG";//卡号
	card.cd_type = "0";//卡类型
	card.cd_balance = 100;//卡余额 
	return card;
};

//给美食卡充值
$.addCardValue = function(addBalance){
	//TODO....
	$.messager.alert("提示框","充值失败",'error');
	return true;
};

//将美食卡信息写入IC卡中
$.writeCard = function(card){
	//将card对象的信息写入卡中
	//TODO....
	//TODO....
	$.messager.alert("提示框","开卡失败",'error');
	return true;
};

