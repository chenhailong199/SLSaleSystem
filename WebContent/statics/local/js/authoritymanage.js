//获取功能列表
$('.roleNameAuthority').click(function(){
	var authority = $(this);
	var roleId = authority.attr("roleid");
	$("#selectrole").html("当前配置角色为：" + authority.attr("rolename"));
	$("#roleidhide").val(roleId);
	//functions
	$.ajax({
		url: '/SL/backend/functions.html',
		type: 'POST',
		data:{"roleId":roleId},
		dataType: 'JSON',
		timeout: 1000,
		error: function(){
			alert("对不起，功能列表获取失败，请重试。");
		},
		success: function(result){
			if(result == "nodata"){
				alert("对不起，功能列表未获取到数据,请重试.");
			}else{
				var listr = "";	
				for(var i=0;i<result.length;i++){
					listr += "<li>";
					listr += "<ul id=\"subfuncul"+result[i].mainFunction.id+"\" class=\"subfuncul\">";
					listr += "<li  class=\"functiontitle\" ><input id='functiontitle"+result[i].mainFunction.id+"' onchange='mainFunctionSelectChange(this,"+result[i].mainFunction.id+");' funcid=\""+result[i].mainFunction.id+"\" type='checkbox' />"+result[i].mainFunction.functionName+"</li>";
					for(j=0;j<result[i].subFunctions.length;j++){					
						listr += "<li><input onchange='subFunctionSelectChange(this,"+result[i].mainFunction.id+");' funcid=\""+result[i].subFunctions[j].id+"\" type='checkbox' /> "+result[i].subFunctions[j].functionName+"</li>";
					}
					listr += "</ul></li>";
				}
				console.log(listr);
				$("#functionList").html(listr);
				//通过roleId 回显已有的权限功能
				$("#functionList :checkbox").each(function () {  
					var checkbox = $(this);
					$.ajax({
						url: '/SL/backend/getAuthorityDefault.html',
						type: 'POST',
						data:{"rid":$("#roleidhide").val(),"fid":$(this).attr("funcid")},
						dataType: 'text',
						timeout: 1000,
						error: function(){
							console.log("回显勾选失败");
						},
						success: function(result){
							if(result == "success"){
								checkbox.attr("checked", true); 
							}else{
								checkbox.attr("checked", false);
							}
						}
						});
				});
			}
		}
		});
});
//子菜单选中
function subFunctionSelectChange(obj,id){
	if(obj.checked){
		$("#functiontitle"+id).attr("checked", true);  
	}
}
//主菜单选中
function mainFunctionSelectChange(obj,id){
	if(obj.checked){
		$("#subfuncul"+id+" :checkbox").attr("checked", true);  
	}else{
		$("#subfuncul"+id+" :checkbox").attr("checked", false);  
	}
	
	//alert($(this) +　id);
}


$("#selectAll").click(function () {//全选  
    $("#functionList :checkbox").attr("checked", true);  
});  

$("#unSelect").click(function () {//全不选  
    $("#functionList :checkbox").attr("checked", false);  
});  

$("#reverse").click(function () {//反选  
    $("#functionList :checkbox").each(function () {  
        $(this).attr("checked", !$(this).attr("checked"));  
    });  
});  


  

$("#confirmsave").click(function(){
	if(confirm("您确定要修改当前角色的权限吗？")){
		//角色ID roleID
		var ids = $("#roleidhide").val()+"-";
		//功能ID functionID
		$("#functionList :checkbox").each(function () {
			if($(this).attr("checked") == 'checked'){
				ids += $(this).attr("funcid") + "-" ;
			}
		console.log(ids);	
	    }); 
		//提交角色权限
		$.ajax({
			url: '/SL/backend/modifyAuthority.html',
			type: 'POST',
			data:{ids:ids},
			dataType: 'text',
			timeout: 1000,
			error: function(){
				alert("修改权限失败");
			},
			success: function(result){
				if(result == "nodata"){
					alert("对不起，功能列表获取失败，请重试。");
				}else{
					alert("恭喜您，权限修改成功。");
				}
			}
		});
	}
});
