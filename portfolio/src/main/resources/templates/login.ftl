<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Portfolio Managerment System</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/login.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <script src="Scripts/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="js/dm/loginAndSign.js"></script>
<!--     <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css"> -->

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
    
<body>
    <div id="wrapper"> 
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="navbar-header">
               
                <a class="navbar-brand" href="index.html">Portfolio Managerment</a>
            </div>
        </nav>
        <div class="intro-header">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="intro-message">
                            <h1>Landing Page</h1>
                            <div class="list-inline intro-social-buttons "> 
                                <label class="frontLabel">Username</label>
                                <input type="text" id="user" placeholder="Username">
                            </div>
                            <div class="list-inline intro-social-buttons"> 
                                <label class="frontLabel">Password</label>
                                <input type="password" id="pwd" placeholder="Password">
                            </div>
                            <ul class="list-inline intro-social-buttons login-btn">
                                <li>
                                    <button class="btn btn-default btn-lg" onclick="login()">Login</button>
                                </li>
                                <li>
                                    <button type="reset" class="btn btn-default btn-lg" onclick="cancel()">Cancel</button>
                                </li>
                            </ul>
                            <div class="sign-row">
                                <label class="nocount">No account?</label>
                                <a href="sign.html"><span class="sign">Sign</span></a> 
                            </div>
                            
                        </div>
                    </div>
                </div>

            </div>
        <!-- /.container -->
        </div>
    </div>
</body>
</html>
