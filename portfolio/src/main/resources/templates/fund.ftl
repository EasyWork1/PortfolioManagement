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


</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">Portfolio Management</a>
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
                        <h1 class="page-header">
                            Portfolio
                        </h1>
                        
                    </div>
                </div>
                <!-- /.row -->

                <div class="col-lg-10">
                            <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
                            <i class="fa fa-plus fa-fw"></i><span class="network-name">Add a new portfolio</span>
                            </button>
                            <button class="btn btn-primary btn-sm" style="margin-left: 20px;" onclick="updateBenefit()">update benefit</button>
                        <!-- /#wrapper -->
                        <!-- 模态框（Modal） -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                        <h4 class="modal-title" id="myModalLabel">Create A New Portfolio</h4>
                                    </div>
                                    <div class="modal-body">
                                        <input type="text" id="PortfolioName" placeholder="Input PortfolioName"/>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <button type="button" class="btn btn-primary" onclick="createPortfolio()">Add</button>
                                    </div>
                                </div><!-- /.modal-content -->
                            </div><!-- /.modal -->
                        </div>
                        
                        <div>
                            <table id= "tb_Portfolio" class="table table-bordered table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th>Id</th> 
                                        <th>Name</th> 
                                        <th>symbols</th> 
                                        <th>Lotvalue</th>
                                        <th>Benefit</th>
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
            <div id="container" style="min-width: 400px;hight=400px"></div>
            <div class="message"></div>

        </div>
        <!-- /#page-wrapper -->

    </div>
    

</body>

</html>