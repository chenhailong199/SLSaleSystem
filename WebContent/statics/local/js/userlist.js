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
	if (s_roleId == 2){
		$.post("/SL/loadUserTypeList.html",{"s_roleId":s_roleId},function(result){
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
$("#selectusertype").change(function() {
	$("#selectusertypename").val($("#selectusertype").find("option:selected"));
});
//证件类型 赋值
$("#selectcardtype").change(function() {
	$("#selectcardtypename").val($("#selectcardtype").find("option:selected"));
});

//判断是否重名(增加用户的时候)
$("#a_logincode").blur(function() {
	var alc = $("#a_logincode").val();
	if (alc != ""){
		//异步同名判断
		$.post("/SL/background/loginCodeIsExit.html",{"loginCode":alc,"id":"-1"},callBack,"text");
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

//viewuser start
$('.viewuser').click(function (e) {	
	var v_id = $(this).attr('id');
	//ajax异步调用
	$.ajax({
		url:'/SL/background/getUser.html',
		type:'POST',
		data:{id:v_id},
		dataType:'json',
		timeout:1000,
		error:function() {alert("error");},
		success:function(result) {
			if ("failed" == result){
				alert("操作超时");
			} else if ("nodata" == result){
				alert("没有数据");
			} else {
				$("#v_id").val(result.id);
				$("#v_rolename").val(result.role.roleName);
				$("#v_usertypename").val(result.userTypeName);
				$("#v_logincode").val(result.loginCode);
				$("#v_username").val(result.userName);
				$("#v_gender").val(result.gender);
				$("#v_createdtime").val(result.createdTime);
				e.preventDefault();
				$('#viewuserdiv').modal('show');
			}	
		}
	});
});
//viewuser end

/*modifyuser start*/
$('.modifyuser').click(function(e) {
	var m_id = $(this).attr('id');
	$.ajax({
		url:'/SL/background/getUser.html',
		type:'POST',
		data:{id:m_id},
		dataType:'json',
		timeout:1000,
		error:function() {alert("error");},
		success:function(result) {
			if ("failed" == result){
				alert("操作超时");
			} else if ("nodata" == result){
				alert("没有数据");
			} else {
				$("#m_id").val(result.id);
				$("#m_rolename").val(result.role.roleName);
				$("#m_usertypename").val(result.userTypeName);
				$("#m_logincode").val(result.loginCode);
				$("#m_username").val(result.userName);
				$("#m_gender").val(result.gender);
				$("#m_createdtime").val(result.createdTime);
				
				e.preventDefault();
				$('#modifyuserdiv').modal('show');
			}	
		}
	});
});


/*modifyuser end*/



