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
            <a class="navbar-brand" href="index.html"> <img alt="Charisma Logo" src="/SL/statics/bootstrap/img/logo20.png" class="hidden-xs"/>
                <span>SL 会员商城</span></a>

            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> admin</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li><a href="#">Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="login.html">Logout</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->

            <!-- theme selector starts -->
            <div class="btn-group pull-right theme-container animated tada">
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-tint"></i><span
                        class="hidden-sm hidden-xs"> Change Theme / Skin</span>
                    <span class="caret"></span>
                </button>
              
            </div>
            <!-- theme selector ends -->

            <ul class="collapse navbar-collapse nav navbar-nav top-menu">
                <li><a href="#"><i class="glyphicon glyphicon-globe"></i> Visit Site</a></li>
                <li class="dropdown">
                    <a href="#" data-toggle="dropdown"><i class="glyphicon glyphicon-star"></i> Dropdown <span
                            class="caret"></span></a>
                </li>
                <li>
                    <form class="navbar-search pull-left">
                        <input placeholder="Search" class="search-query form-control col-md-10" name="query"
                               type="text">
                    </form>
                </li>
            </ul>

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