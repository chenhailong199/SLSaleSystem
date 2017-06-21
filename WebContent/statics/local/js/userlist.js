//点击增加按钮弹出model
$('.addUser').click(function (e) {
	$("#add_prompt").html("");
    e.preventDefault();
    $('#adduserdiv').modal('show');
});
//点击取消清空输入框
$(".addusercancel").click(function() {
	$("#a_logincode").val("");
	$("#a_username").val("");
});
//验证邮箱
function checkEmail(str){
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if (str == null || str == "" || reg.test(str)){
		return true;
	} else 
		return false;
}
//通过选择角色异步加载可选的用户类型
$("#selectrole").change(function() {
	//会员类型先置空
	$("#selectusertype").empty();
	$("#selectusertype").append('<option value="-1" selected="selected">--请选择--</option>');
	var s_roleId = $("#selectrole").val();
	alert(s_roleId);
	if (s_roleId == 2){
		alert("异步方法外-----");
		$.post("/loadUserTypeList.html",{"s_roleId":s_roleId},function(result){
			alert("异步方法内-----");
			if (result != ""){
				alert(result);
				for (var i=0; i<result.length; i++){
					$("#selectusertype").append('<option value="'+ result[i].valueId+'\" >'+result[i].valueName +'</option>');
				}
			} else {
				alert("用户类型加载失败");
			}
			
		},"JSON");
	}
	//为隐藏域赋值
	$("#selectrolename").val($("#selectrole").find("option:selected"));
});

//会员类型 赋值
$("#selectusertype").changed(function() {
	$("#selectusertypename").val($("#selectusertype").find("option:selected"));
});
//证件类型 赋值
$("#selectcardtype").changed(function() {
	$("#selectcardtypename").val($("#selectcardtype").find("option:selected"));
});

//判断是否重名(增加用户的时候)
$("#a_logincode").blur(function() {
	alert("移开事件");
	var alc = $("#a_logincode").val();
	if (alc != ""){
		alert(alc);
		//异步同名判断
		$.post("background/loginCodeIsExit.html",{"loginCode":alc,"id":"-1"},callBack,"text");
		function callBack(result){
			if (result == "repeat"){
				$("#add_prompt").css("color","red").html("<li>*该用户名已存在,请修改</li>");
				$("#add_prompt").attr("key",1); //作为重名的标记
			} else if (result == "failed"){
				alert("操作超时");
			} else if (result == "only"){
				$("#add_prompt").css("color","green").html("<li>*该用户名可以使用</li>");
				$("#add_prompt").attr("key",0); //作为不重名的标记
			} 
		}
	}
});
//验证邮箱
$("#a_email").blur(function() {
	$("#add_prompt").html("");
	var flag = checkEmail($("#a_email").val());
	if (flag == false){
		$("#add_prompt").css("color","red").html("<li>*email格式不正确,请修改</li>");
		$("#a_email").select();
	}
});

//隐藏域赋值


//增加用户验证
function addUser(){
	$("#add_prompt").html("");
	var result = true;
	if ($("#selectrole").val() == ""){//角色不能为空
		$("#add_prompt").css("color","red").html("<li>*角色不能为空</li>");
		result = false;
	} 
	if ($("#a_logincode").val() == "" || $("#a_logincode").val() == null){//用户名不能为空
		$("#add_prompt").css("color","red").html("<li>*用户名不能为空</li>");
		result = false;
	} else {//重名不能提交
		if ($("#add_prompt").attr("key") == "1"){
			$("#add_prompt").css("color","red").html("<li>*该用户名已存在,请修改</li>");
			result = false;
		}
	}
	//...
	if(result) alert("添加成功");
	return result;
}


