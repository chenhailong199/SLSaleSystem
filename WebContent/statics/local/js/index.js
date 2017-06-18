//验证用户名
function checkLoginCode() {
	var loginCode = $("#loginCode").val();
	if (loginCode == ""){
		$("#loginCode").focus();
		$("#loginCode_prompt").css("color","red").html("登录账号不能为空");
		return false;
	} else {
		return true;
	}
}
$("#loginCode").blur(function() {
	var loginCode = $("#loginCode").val();
	if (loginCode == ""){
		$("#loginCode").focus();
		$("#loginCode_prompt").css("color","red").html("登录账号不能为空");
	} else {
		$("#loginCode_prompt").html("");
	}
});
//验证密码 
function checkPassword() {
	var password = $("#password").val();
	if (password == ""){
		$("#password").focus();
		$("#password_prompt").css("color","red").html("登录密码不能为空");
		return false;
	} else {
		$("#password_prompt").html("");
		return true;
	}	
}

$("#password").blur(function() {
	var password = $("#password").val();
	if (password == ""){
		$("#password").focus();
		$("#password_prompt").css("color","red").html("登录密码不能为空");
	} else {
		$("#password_prompt").html("");
	}	
});

$("#loginBtn").click(function() {
	if (checkPassword() == true && checkLoginCode() == true ){
		var loginCode = $("#loginCode").val();
		var password = $("#password").val();
		$("#loginCode_prompt").html("");
		$("#password_prompt").html("");
		$.post("userLogin.do",{loginCode:loginCode,password:password},callBack,"text");
		function callBack(result){
			//若登录成功,跳转到"/main.html"
			if (result != "" && result == "success"){
				window.location.href="/SL/main.html";
			} else if (result == "failed"){
				$("#loginCode_prompt").css("color","red").html("登录失败----进入方法内");
				$("#loginCode").val("");
				$("#password").val("");
			} else if (result == "nologinCode"){
				$("#loginCode_prompt").css("color","red").html("登录账号不存在");
				$("#loginCode").select();
				$("#password").val("");
			} else if (result == "pwdError"){
				$("#password_prompt").css("color","red").html("登录密码错误");
				$("#password").select();
			} else if (result == "nodata"){
				$("#loginCode_prompt").css("color","red").html("无数据");	
			} 	
		}
	} 
	
});