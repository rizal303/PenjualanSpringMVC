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
                        <i class="icon-reorder shaded"></i></a><a class="brand" href="index-administrator.html">Inventory Application </a>
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
                                <li class="active"><a href="dashboardadministrator.htm"><i class="menu-icon icon-dashboard"></i>Dashboard</a></li>
                                <li><a href="kategoribarang.htm"><i class="menu-icon icon-table"></i>Kategori Barang</a>
                                <li><a href="barang.htm"><i class="menu-icon icon-table"></i>Barang</a>
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
                                    <h3>Forms Edit Barang</h3>
                                </div>
                                <div class="module-body">
                                    <form class="form-horizontal row-fluid" method="post" action="proseseditbarang.htm">
                                        <input type="hidden" name="idBarang" value="${b.idBarang}">
                                        <input type="hidden" name="status" value="${b.status}">
                                        <div class="control-group">
                                            <label class="control-label" for="basicinput">Barang</label>
                                            <div class="controls">
                                                <input type="text" name="barang" id="basicinput" class="span8" value="${b.barang}" required>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label" for="basicinput">Old Kategori</label>
                                            <div class="controls">
                                                <input type="text" readonly="readonly" id="basicinput" class="span8" value="${b.idKategori.kategori}">
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label" for="basicinput">New Kategori</label>
                                            <div class="controls">
                                                <select tabindex="1" name="cbKategori" class="span8">
                                                    <c:forEach items="${list}" var="kb">
                                                        <option value="${kb.idKategori}">${kb.kategori}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label" for="basicinput">Stock</label>
                                            <div class="controls">
                                                <input type="number" name="stock" id="basicinput" value="${b.stock}" class="span8" required>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <label class="control-label" for="basicinput">Harga</label>
                                            <div class="controls">
                                                <input type="number" name="harga" id="basicinput" class="span8" value="${b.harga}" required>
                                            </div>
                                        </div>

                                        <div class="control-group">
                                            <div class="controls">
                                                <button type="submit" class="btn btn-success">Simpan</button>
                                            </div>
                                        </div>

                                        </br>
                                        </br>
                                        </br>

                                        <div class="pull-left">
                                            <a href="barang.htm" class="btn">Kembali ke list</a>
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

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
        <script src="scripts/jquery-1.9.1.min.js" type="text/javascript"></script>
        <script src="scripts/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="scripts/flot/jquery.flot.js" type="text/javascript"></script>
        <script src="scripts/flot/jquery.flot.resize.js" type="text/javascript"></script>
        <script src="scripts/datatables/jquery.dataTables.js" type="text/javascript"></script>
        <script src="scripts/common.js" type="text/javascript"></script>

    </body>
