<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>SL会员商城</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">

	<!-- The styles -->
	<link  href="/SL/statics/bootstrap/css/bootstrap-cerulean.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	<link href="/SL/statics/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="/SL/statics/bootstrap/css/charisma-app.css" rel="stylesheet">
	<link href="/SL/statics/bootstrap/css/jquery-ui-1.8.21.custom.css" rel="stylesheet">
	<link href='/SL/statics/bootstrap/css/fullcalendar.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/fullcalendar.print.css' rel='stylesheet'  media='print'>
	<link href='/SL/statics/bootstrap/css/chosen.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/uniform.default.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/colorbox.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/jquery.cleditor.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/jquery.noty.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/noty_theme_default.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/elfinder.min.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/elfinder.theme.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/jquery.iphone.toggle.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/opa-icons.css' rel='stylesheet'>
	<link href='/SL/statics/bootstrap/css/uploadify.css' rel='stylesheet'>

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="/SL/statics/bootstrap/img/favicon.ico">
		
</head>

<body>
		<div class="container-fluid">
		<div class="row-fluid">
		
			<div class="row-fluid">
				<div class="span12 center login-header">
					<h2>SL会员商城</h2>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div class="alert alert-info">
						请输入登陆账号和密码...
				
					</div>
					<div class="form-horizontal" >
						<fieldset>
							<div class="input-prepend" title="登录账号" data-rel="tooltip">
								<span class="add-on"><i class="icon-user"></i></span><input autofocus class="input-large span10" name="loginCode" id="logincode" type="text" value="" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="登陆密码" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span><input class="input-large span10" name="password" id="password" type="password" value="" />
							</div>
							<div class="clearfix"></div>

							<!-- <div class="input-prepend">
							<label class="remember" for="remember"><input type="checkbox" id="remember" />Remember me</label>
							</div> -->
							<div class="clearfix"></div>
							<ul id="formtip"></ul>
							<p class="center span5">
							<button type="submit" class="btn btn-primary" id="loginBtn">登录</button>
							</p>
							
						</fieldset>
					</div>
				</div><!--/span-->
			</div><!--/row-->
				</div><!--/fluid-row-->
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script src="/SL/statics/bootstrap/js/jquery-1.7.2.min.js"></script>
	<!-- jQuery UI -->
	<script src="/SL/statics/bootstrap/js/jquery-ui-1.8.21.custom.min.js"></script>
	<!-- transition / effect library -->
	<script src="/SL/statics/bootstrap/js/bootstrap-transition.js"></script>
	<!-- alert enhancer library -->
	<script src="/SL/statics/bootstrap/js/bootstrap-alert.js"></script>
	<!-- modal / dialog library -->
	<script src="/SL/statics/bootstrap/js/bootstrap-modal.js"></script>
	<!-- custom dropdown library -->
	<script src="/SL/statics/bootstrap/js/bootstrap-dropdown.js"></script>
	<!-- scrolspy library -->
	<script src="/SL/statics/bootstrap/js/bootstrap-scrollspy.js"></script>
	<!-- library for creating tabs -->
	<script src="/SL/statics/bootstrap/js/bootstrap-tab.js"></script>
	<!-- library for advanced tooltip -->
	<script src="/SL/statics/bootstrap/js/bootstrap-tooltip.js"></script>
	<!-- popover effect library -->
	<script src="/SL/statics/bootstrap/js/bootstrap-popover.js"></script>
	<!-- button enhancer library -->
	<script src="/SL/statics/bootstrap/js/bootstrap-button.js"></script>
	<!-- accordion library (optional, not used in demo) -->
	<script src="/SL/statics/bootstrap/js/bootstrap-collapse.js"></script>
	<!-- carousel slideshow library (optional, not used in demo) -->
	<script src="/SL/statics/bootstrap/js/bootstrap-carousel.js"></script>
	<!-- autocomplete library -->
	<script src="/SL/statics/bootstrap/js/bootstrap-typeahead.js"></script>
	<!-- tour library -->
	<script src="/SL/statics/bootstrap/js/bootstrap-tour.js"></script>
	<!-- library for cookie management -->
	<script src="/SL/statics/bootstrap/js/jquery.cookie.js"></script>
	<!-- calander plugin -->
	<script src='/SL/statics/bootstrap/js/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='/SL/statics/bootstrap/js/jquery.dataTables.min.js'></script>

	<!-- chart libraries start -->
	<script src="/SL/statics/bootstrap/js/excanvas.js"></script>
	<script src="/SL/statics/bootstrap/js/jquery.flot.min.js"></script>
	<script src="/SL/statics/bootstrap/js/jquery.flot.pie.min.js"></script>
	<script src="/SL/statics/bootstrap/js/jquery.flot.stack.js"></script>
	<script src="/SL/statics/bootstrap/js/jquery.flot.resize.min.js"></script>
	<!-- chart libraries end -->

	<!-- select or dropdown enhancer -->
	<script src="/SL/statics/bootstrap/js/jquery.chosen.min.js"></script>
	<!-- checkbox, radio, and file input styler -->
	<script src="/SL/statics/bootstrap/js/jquery.uniform.min.js"></script>
	<!-- plugin for gallery image view -->
	<script src="/SL/statics/bootstrap/js/jquery.colorbox.min.js"></script>
	<!-- rich text editor library -->
	<script src="/SL/statics/bootstrap/js/jquery.cleditor.min.js"></script>
	<!-- notification plugin -->
	<script src="/SL/statics/bootstrap/js/jquery.noty.js"></script>
	<!-- file manager library -->
	<script src="/SL/statics/bootstrap/js/jquery.elfinder.min.js"></script>
	<!-- star rating plugin -->
	<script src="/SL/statics/bootstrap/js/jquery.raty.min.js"></script>
	<!-- for iOS style toggle switch -->
	<script src="/SL/statics/bootstrap/js/jquery.iphone.toggle.js"></script>
	<!-- autogrowing textarea plugin -->
	<script src="/SL/statics/bootstrap/js/jquery.autogrow-textarea.js"></script>
	<!-- multiple file upload plugin -->
	<script src="/SL/statics/bootstrap/js/jquery.uploadify-3.1.min.js"></script>
	<!-- history.js for cross-browser state change on ajax -->
	<script src="/SL/statics/bootstrap/js/jquery.history.js"></script>
	<!-- application script for Charisma demo -->
	<script src="/SL/statics/bootstrap/js/charisma.js"></script>
	<script src="/SL/statics/local/js/index.js"></script>
	
		
</body>
</html>


