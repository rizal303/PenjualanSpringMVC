<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Dashboard Inventory</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="resource/bootstrap/css/bootstrap.min.css"/> ">
        <link type="text/css" rel="stylesheet" href="<c:url value="resource/bootstrap/css/bootstrap-responsive.min.css"/> ">
        <link type="text/css" rel="stylesheet" href="<c:url value="resource/css/theme.css"/> ">
        <link type="text/css" rel="stylesheet" href="<c:url value="resource/images/icons/css/font-awesome.css"/> ">
        <link type="text/css" rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,600italic,400,600'>
    </head>
    <body>
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".navbar-inverse-collapse">
                        <i class="icon-reorder shaded"></i></a><a class="brand" href="dashboardadmin.htm">Inventory Application </a>
                    <!-- /.nav-collapse -->
                </div>
            </div>
            <!-- /navbar-inner -->
        </div>
        <!-- /navbar -->
        <div class="wrapper">
            <div class="container">
                <div class="row">
                    <div class="span3">
                        <div class="sidebar">
                            <ul class="widget widget-menu unstyled">
                                <li class="active"><a href="dashboardadmin.htm"><i class="menu-icon icon-reorder"></i>Shop</a></li>
                                <li><a href="chart.htm"><i class="menu-icon icon-shopping-cart"></i>Chart
                                        <b class="label green pull-right">${chart} </b></a>
                            </ul>
                            <!--/.widget-nav-->

                            <!--/.widget-nav-->
                            <ul class="widget widget-menu unstyled">
                                <li><a href="logout.htm"><i class="menu-icon icon-signout"></i>Logout </a></li>
                            </ul>
                        </div>
                        <!--/.sidebar-->
                    </div>
                    <div class="span9">
                        <div class="content">

                            <div class="module">
                                <div class="module-head">
                                    <h3>Chart</h3>
                                </div>
                                <div class="module-body">
                                    <table class="table table-striped">

                                        <div class="pull-left">
                                            <h4>TOTAL : ${sub}</h4>

                                            <form class="form-horizontal row-fluid" method="post" action="checkout.htm">
                                                <input type="number" name="bayar" id="basicinput" class="span8" required>
                                                <input type="hidden" name="total" value="${sub}"/>
                                                <button type="submit" class="btn btn-success">Checkout</button>                                      
                                            </form>

                                        </div>
                                        <br/>
                                        <thead>
                                            <tr>
                                                <th>Barang</th>
                                                <th>Harga</th>
                                                <th>Quantity</th>
                                                <th>Amount</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${list}" var="c">
                                                <tr>
                                                    <td>${c.idBarang.barang}</td>
                                                    <td>${c.idBarang.harga}</td>
                                                    <td>${c.qty}</td>
                                                    <td>${c.idBarang.harga*c.qty}</td>
                                                    <td>
                                                        <a href="plusqty.htm?id=${c.idBarang.idBarang}" class="btn btn-warning">Tambah</a>
                                                        <a href="minqty.htm?id=${c.idBarang.idBarang}" class="btn btn-info">Kurangi</a>
                                                        <a href="deletebrg.htm?id=${c.idBarang.idBarang}" class="btn btn-danger">Hapus</a>
                                                    </td>
                                                </tr>

                                            </c:forEach>
                                        <br />
                                        </tbody>
                                    </table>
                                </div>
                            </div><!--/.module-->

                            <br />

                        </div><!--/.content-->
                    </div><!--/.span9-->

                </div>
            </div>
            <!--/.container-->
        </div>
        <!--/.wrapper-->
        <div class="footer">
            <div class="container">
                <b class="copyright">&copy; 2017 Inventory Application</b>. All rights reserved.
            </div>
        </div>
        <script src="resource/scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
        <script src="resource/scripts/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
        <script src="resource/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resource/scripts/flot/jquery.flot.js" type="text/javascript"></script>
        <script src="resource/scripts/flot/jquery.flot.resize.js" type="text/javascript"></script>
        <script src="resource/scripts/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="resource/scripts/common.js" type="text/javascript"></script>

    </body>
