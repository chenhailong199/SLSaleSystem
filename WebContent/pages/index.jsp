<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
 	  <!-- The styles -->
    <link id="bs-css" href="/SL/statics/bootstrap/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="/SL/statics/bootstrap/css/charisma-app.css" rel="stylesheet">
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

</head>

<body>
	<div class="ch-container">
	    <div class="row">
	        
	    <div class="row">
	        <div class="col-md-12 center login-header">
	            <h2>Welcome to SLSalesSystem</h2>
	        </div>
	        <!--/span-->
	    </div><!--/row-->
	
	    <div class="row">
	        <div class="well col-md-5 center login-box">
	            <div class="alert alert-info">
	              	请输入您登录的用户名和密码
	            </div>
	            <div class="form-horizontal">
	                    <div class="input-group input-group-lg">
	                        <span class="input-group-addon"><i class="glyphicon glyphicon-user red"></i></span>
	                        <input type="text" class="form-control" id="loginCode" placeholder="请输入账号">
	                    </div><span id="loginCode_prompt"></span>
	                    <div class="clearfix"></div><br>
	
	                    <div class="input-group input-group-lg">
	                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock red"></i></span>
	                        <input type="password" class="form-control" id="password" placeholder="请输入密码">
	                    </div><span id="password_prompt"></span>
	                    <div class="clearfix"></div>
	
	                    <div class="input-prepend">
	                        <label class="remember" for="remember"><input type="checkbox" id="remember"> Remember me</label>
	                    </div>
	                    <div class="clearfix"></div>
	
	                    <p class="center col-md-5">
	                        <button type="button" class="btn btn-primary" id="loginBtn">Login</button>
	                    </p>
	            </div>
	        </div>
	        <!--/span-->
	    </div><!--/row-->
	</div><!--/fluid-row-->
	
	</div><!--/.fluid-container-->
	
	<script src="/SL/statics/local/js/index.js"></script>
</body>
</html>
