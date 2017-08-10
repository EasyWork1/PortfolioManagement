<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin-Portfolio Management System</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/plugins/morris.css" rel="stylesheet">
    <link href="css/height.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>Admin <b class="caret"></b></a>
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
                        <a href="FundManager.html"><i class="fa fa-fw fa-user""></i> Fund Manager</a>
                    </li>
                     <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-bar-chart-o"></i> Instrument <i class="fa fa-fw "></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="stockPage.html">Stock</a>
                            </li>
                            <li>
                                <a href="bondPage.html">Bond</a>
                            </li>
                            <li>
                                <a href="FuturePage.html">Future</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid white-place">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-9">
                        <h1 class="page-header">
                            FundManager
                        </h1>
                        
                    </div>
                </div>
                <!-- /.row -->

                <row class="col-lg-9">
                     <div class="btn-group" role="group" aria-label="..." >
                            <button type="button" class="btn btn-primary btn-sm" id="add_btn" onclick="changeVisibility()">
                            <span class="glyphicon glyphicon-plus"></span>Add a New Manager
                            </button>
                          
                     </div>
                </row>    

                <br/>                

                <row>
                     <div id="add_edit_div" style="display: none" class="col-lg-10">

                     <table  >
                     <tr>
                         <th style="width:150px; height:70px">
                                <label>First Name</label>
                                <input class="form-control" style="width:140px;" id="i1">                           
                        </th> 
                         <th style="width:150px;">
                                <label>Last Name</label>
                                <input class="form-control" style="width:140px;" id="i2">                           
                         </th>
                         <th style="width:150px;">
                                <label>Telephone</label>
                                <input class="form-control" style="width:140px;" id="i3">                           
                        </th>
                     
                         <th style="width:150px; height:70px">
                                <label>Email</label>
                                <input class="form-control" style="width:140px;" id="i4">
                         </th>
                         <th style="width:150px; height:70px">
                                <label>User Name</label>
                                <input class="form-control" style="width:140px;" id="i5">
                         </th>
                         <th style="width:150px; height:70px">
                                <label>Password</label>
                                <input class="form-control" style="width:140px;" id="i6">
                          </th>
                     </tr>
                     <tr>
                        
                         <td style=" height:70px">
                                <button id = "btn_cancel" type="button" class="btn btn-primary btn-sm" onclick="cancelManager()" value="Reset" style="float:left;">CANCEL</button>
                                 <button id = "btn_save" type="button" class="btn btn-primary btn-sm" onclick="addManager()" style="float:right;">SAVE</button>                      
                                
                         </td>
                     </tr>
                    
                      
                    </table>
                    
                    </div>  
                </row>
                <br/>

                <row >
                     <div class="col-lg-12"> 

                        <div>
                            <table class="table table-bordered table-hover table-striped" id="tb_manager">
                                <thead>
                                    <tr>
                                        <th>ID</th> 
                                        <th>FirstName</th> 
                                        <th>LastName</th> 
                                        <th>Telephone</th> 
                                        <th>Email</th>   
                                        <th>UserName</th> 
                                        <th>Profit</th> 
                                        <th>Edit</th>     
                                    </tr>
                                </thead >
                                <tbody id= "myTb" nowrap="nowrap">
                                  
                                                                     
                                </tbody>
                            </table>
                        </div>
                     </div>

                </row>
                <!-- /.row -->
            </div>
           

        </div>
        <!-- /#page-wrapper -->
        <footer  style="float: right;" >
                <p><h4><font color="white">&copy; Copyright 2017.EASYWORK TEAM All rights reserved.</font></h4></p>
        </footer> 

       </div>

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>
     <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/lx/manager.js"></script>  
    <script src="js/lx/ajaxGetData.js"></script> 

</body>

</html>