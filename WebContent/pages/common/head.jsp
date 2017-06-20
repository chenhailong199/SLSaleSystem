<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>    
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SL会员商城首页</title>
    <style type="text/css">
    	li {
    		list-style:none;
    	}
    </style>

     <!-- The styles -->
    <link href="/SL/statics/bootstrap/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href='/SL/statics/bootstrap/css/charisma-app.css' rel="stylesheet">
    <link href='/SL/statics/bootstrap/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='/SL/statics/bootstrap/bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/css/jquery.noty.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/css/noty_theme_default.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/css/elfinder.min.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/css/elfinder.theme.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/css/uploadify.css' rel='stylesheet'>
    <link href='/SL/statics/bootstrap/css/animate.min.css' rel='stylesheet'>
	
    <!-- jQuery -->
    <script src="/SL/statics/bootstrap/bower_components/jquery/jquery.min.js"></script>
	
    <!-- The fav icon -->
    <link rel="shortcut icon" href="/SL/statics/bootstrap/img/favicon.ico">
	<script type="text/javascript">
		var tt = '${mList}';
	</script>
</head>

<body>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
            <button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/main.html"> <img alt="Charisma Logo" src="/SL/statics/bootstrap/img/logo20.png" class="hidden-xs"/>
                <span>SL 会员商城</span></a>
			<div class="pull-right">
				<ul class="collapse navbar-collapse nav navbar-nav top-menu">
					<li><a href="#"><i class=" glyphicon glyphicon-user"></i> 你好,${currentUser.userName}</a></li>
					<li><a href="#"> 角色:${currentUser.role.roleName}</a></li>
					<li><a href="<%=request.getContextPath() %>/main.html"> 首页</a></li>
					<li><a href="#"> 购物车</a></li>
					<li><a href="#"> 留言板</a></li>
					<li><a href="javascript:void();" class="btn-setting modifypwd"> 修改密码</a></li>
					<li><a href="<%=request.getContextPath() %>/logOut.html"> 注销</a></li>
				</ul>
			</div>
			<!-- 弹框修改密码 开始 -->
			<!-- <a href="#" class="btn btn-info clickbtn">Click for dialog</a> -->
			<div class="modal hide fade" id="myModal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">x</button>
					<h3>修改密码</h3>
				</div>
				<div class="modal-body">
					<p>
						<label>请输入原密码:</label>
							<input id="oldpassword" type="password">
							<span style="color:red;font-weight:bold;">*</span>
						<label>请输入新密码:</label>
							<input id="newpassword" type="password">
							<span style="color:red;font-weight:bold;">*新密码必须6位以上</span>
						<label>再次输入新密码:</label>
							<input id="aginpassword" type="password">
							<span style="color:red;font-weight:bold;">*</span>		 
					</p>
					<p id="modifypwdtip"></p>
				</div>
				<div class="modal-footer">
					<a href="#" class="btn" data-dismiss="modal">取消</a>
					<a href="#" id="modifysavepwd" class="btn btn-primary" >修改</a>
				</div>
			</div>
			<!-- 弹框修改密码 结束 -->

        </div>
    </div>
    <!-- topbar ends -->
<div class="ch-container">
    <div class="row">    
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">
                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu" id="menus">
							<!-- 功能菜单栏 -->          
                    </ul>
                 
                </div>
            </div>
        </div>

        <!-- left menu ends -->

       <div id="content" class="col-lg-10 col-sm-10">
       <div> 
</div>

</body>
</html>