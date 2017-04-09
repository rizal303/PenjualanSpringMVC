<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inventory</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="resource/bootstrap/css/bootstrap.min.css"/> ">
        <link type="text/css" rel="stylesheet" href="<c:url value="resource/bootstrap/css/bootstrap-responsive.min.css"/> ">
        <link type="text/css" rel="stylesheet" href="<c:url value="resource/css/theme.css"/> ">
        <link type="text/css" rel="stylesheet" href="<c:url value="resource/images/icons/css/font-awesome.css"/> ">
        <link type="text/css" rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600'>

        <style>
            .error {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #a94442;
                background-color: #f2dede;
                border-color: #ebccd1;
            }

            .msg {
                padding: 15px;
                margin-bottom: 20px;
                border: 1px solid transparent;
                border-radius: 4px;
                color: #31708f;
                background-color: #d9edf7;
                border-color: #bce8f1;
            }

            #login-box {
                width: 300px;
                padding: 20px;
                margin: 100px auto;
                background: #fff;
                -webkit-border-radius: 2px;
                -moz-border-radius: 2px;
                border: 1px solid #000;
            }
        </style>

    </head>
    <body>

        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                        <i class="icon-reorder shaded"></i>
                    </a>
                    <a class="brand" href="index.htm">
                        Inventory Application
                    </a>
                    <div class="nav-collapse collapse navbar-inverse-collapse">

                        <ul class="nav pull-right">					
                            <li><a href="forgotpassword.htm">
                                    Forgot your password?
                                </a></li>
                        </ul>
                    </div><!-- /.nav-collapse -->
                </div>
            </div><!-- /navbar-inner -->
        </div><!-- /navbar -->

        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="module module-login span4 offset4">

                        <c:if test="${not empty error}">
                            <div class="error">${error}</div>
                        </c:if>
                        <c:if test="${not empty msg}">
                            <div class="msg">${msg}</div>
                        </c:if>

                        <form name="form-login" class="form-vertical" method="POST" action="proccesLogin.htm">
                            <div class="module-head">
                                <h3>Sign In</h3>
                            </div>
                            <div class="module-body">
                                <div class="control-group">
                                    <div class="controls row-fluid">
                                        <input class="span12" name="username" type="text" id="inputEmail" placeholder="Username" required="">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <div class="controls row-fluid">
                                        <input class="span12" name="password" type="password" id="inputPassword" placeholder="Password" required="">
                                    </div>
                                </div>
                            </div>
                            <div class="module-foot">
                                <div class="control-group">
                                    <div class="controls clearfix">
                                        <button type="submit" class="btn btn-primary pull-right">Login</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div><!--/.wrapper-->

        <div class="footer">
            <div class="container">
                <b class="copyright">&copy; 2017 Inventory Application - inventory.com </b> All rights reserved.
            </div>
        </div>
        <script src="<c:url value="resource/scripts/jquery-1.9.1.min.js"/>"></script>
        <script src="<c:url value="resource/scripts/jquery-ui-1.10.1.custom.min.js"/>"></script>
        <script src="<c:url value="resource/bootstrap/js/bootstrap.min.js"/>"></script>
    </body>