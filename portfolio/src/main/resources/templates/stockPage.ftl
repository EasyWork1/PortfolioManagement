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

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body ng-app="app" ng-controller="ctrl">

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
                        <a href="FundManager.html"><i class="fa fa-fw fa-user"></i>Fund Manager</a>
                    </li>
                     <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-bar-chart-o"></i> Instrument <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="stockPage.html">Stock</a>
                            </li>
                            <li>
                                <a href="bondPage.html">Bond</a>
                            </li>
                            <li>
                                <a href="futurePage.html">Future</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

       <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Stocks</h1>
                        
                    </div>
                </div>
            
            
            <div class="row req ">
		    <div class="col-md-8 col-md-offset-4 solid_top form-group">
			<div class="pull-right">
				<label>展示条数: 
					<select  class="form-control" ng-change="change(selectedLimit)" ng-model="selectedLimit" ng-options="value.limit for value in values"></select>
				</label>
			</div>
		    </div>
	        </div> 
	        
	        <table class="table table-bordered table-hover table-striped">
		       <thead>
			     <tr>
                 <th>Symbol</th>
                 <th>Name</th>
                 <th>MarketCap</th>
                 <th>IPOYear</th>
                 <th>Sector</th>
                 <th>Industry</th>
                 <th>BidPrice</th>
                 <th>OfferPrice</th>
                 <th>Date</th>
			     </tr>
		      </thead>
		    <tbody>
			     <tr  ng-repeat="data in datas">
				 <td class="active">{{data.symbol}}</td>
                 <td class="success">{{data.name}}</td>
                 <td class="warning">{{data.marketcap}}</td>
                 <td class="danger">{{data.ipoyear}}</td>
                 <td class="active">{{data.sector}}</td>
                 <td class="success">{{data.industry}}</td>
                 <td class="warning">{{data.bidprice}}</td>
                 <td class="active">{{data.offerprice}}</td>
                 <td class="success">{{data.date}}</td>
			     </tr>
		    </tbody>
	       </table>
    
       <div class="row form-inline">
	        <div class="col-md-8">
		    <uib-pagination 
			total-items="page.totalCount" 
			ng-model="page.pageNo" 
			max-size="5" 
			class="samplePage pagination" 
			boundary-links="true" 
			force-ellipses="false"
			first-text="首页" 
			last-text="末页" 
			previous-text="上一页"
			next-text="下一页" 
			items-per-page="page.limit"
			ng-change="pageChanged()" 
			boundary-link-numbers="true"
			>
	        </uib-pagination>
	  </div> 
	 
	 <div class="pull-right" style="margin-right: 20px">
		<input type="text" class="form-control" style="width:100px;margin:25px 0" name="" ng-model="go"/>
		<a class="btn btn-primary btn-sm" ng-click="setPage(go)">跳转</a>
	 </div>  
	
     </div> 
               
      </div><!-- /.container-fluid -->

     </div><!-- /#page-wrapper -->

    </div><!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <script type="text/javascript" src="js/lx/angular.min.js"></script>
    <script type="text/javascript" src="js/lx/ui-bootstrap-tpls.min.js"></script>
    <script type="text/javascript" src="js/lx/stockPage.js"></script>

</body>

</html>
