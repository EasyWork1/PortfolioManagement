<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin</title>

    <script src="Scripts/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    
    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
     <link href="css/bootstrap.min.css" rel="stylesheet">
     <link href="css/sb-admin.css" rel="stylesheet">

    <link href="css/height.css" rel="stylesheet">
    <link href="css/fund.css" rel="stylesheet">
    <script type="text/javascript" src="js/dm/fundSub.js"></script>
    <script type="text/javascript" src="js/dm/highcharts.js"></script>
    <script type="text/javascript" src="js/dm/exporting.js"></script>
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header" style="margin-left: 5px">

                <img src="img/logo.png" width="60" height="39" style="float:left"/>
                <a class="navbar-brand"><font size="5">WePortfolio</font></a >

            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">    
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i><span id="user-box">John Smith</span><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="login.html"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li class="active">
                        <a href="fund.html"><i class="fa fa-fw fa-table"></i> Portfolio</a>
                    </li>
                    <li>
                        <a href="history.html"><i class="fa fa-fw fa-desktop"></i> History</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid white-place">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header" id="PortfolioNameSub">
                            My symbols
                        </h1>
                        
                    </div>
                </div>
                <!-- /.row -->
				   <!-- show pie chart -->
                <div class="row">
                    <div class="col-lg-12">
                       <div id="pieContainer" style="min-width:300px;height:300px"></div>
                        
                    </div>
                </div>
				

                <div class="col-lg-12">
                        <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
                            <i class="fa fa-plus fa-fw"></i><span class="network-name">Add symbol</span>
                         </button>
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Choose a symbol</h4>
                                    </div>
                                    <div class="modal-body">
                                        <select class="form-control select-fund" id="chooseType">
                                            <option>Type</option>
                                            <option>Bond</option>
                                            <option>Future</option>
                                            <option>Stock</option>
                                        </select>
                                        <div class="form-group input-group search-group">
                                             <input type="text" class="form-control" id="searchInput">
                                             <span class="input-group-btn"><button class="btn btn-default" type="button" onclick="searchSymbol()"><i class="fa fa-search"></i></button></span>
                                        </div>
                                        <label style="margin-left: 50px;">quantity:</label>
                                        <input type="text" id="quantityNum" class="form-control">
                                        <table id= "tb_Bond" class="table table-bordered table-hover table-striped" style="display: none">
                                            <thead>
                                                <tr>
                                                    <th>isin</th>
                                                    <th>issuer</th> 
                                                </tr>
                                            </thead>
                                            <tbody>                   
                                            </tbody>
                                        </table>
                                        <table id= "tb_Future" class="table table-bordered table-hover table-striped" style="display: none">
                                            <thead>
                                                <tr>
                                                    <th>ClrAlias</th>
                                                    <th>Exch</th>
                                                </tr>
                                            </thead>
                                            <tbody>                   
                                            </tbody>
                                        </table>
                                        <table id= "tb_Stock" class="table table-bordered table-hover table-striped" style="display: none">
                                            <thead>
                                                <tr>
                                                    <th>symbol</th> 
                                                    <th>name</th>
                                                </tr>
                                            </thead>
                                            <tbody>                   
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary" onclick="addStock()">Add</button>
                                    </div>
                                    
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div><!-- /modalend -->
                        <div class="modal fade" id="lineModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Show Detail</h4>
                                    </div>
                                    <div class="modal-body">
                                        <div id="container" style="min-width:400px;height:400px"></div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    </div>
                                    
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div><!-- /modalend -->
                        <div>
                         
                        <div>

                            <table id= "tb_Symbol" class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>symbol</th> 
                                        <th>lastprice</th>
                                        <th>benefit</th>   
                                        <th>currency</th>
                                        <th>quantity</th>
                                        <th>asset</th>
                                        <th>dateTime</th>
                                        <th>Edit</th>
                                    </tr>
                                </thead>
                                <tbody>                   
                                </tbody>
                            </table>
                        </div>
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->
            <footer  style="float: right;" >
                <p><h4><font color="white">&copy; Copyright 2017.EASYWORK TEAM All rights reserved.</font></h4></p>
            </footer>
        </div>
    <!-- /#wrapper -->

</body>
</html>