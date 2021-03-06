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
</head>
    
<body>
    <div id="wrapper"> 
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="navbar-header">
               
                <a class="navbar-brand" href="login.html">Portfolio Managerment</a>
            </div>
        </nav>
    <div class="intro-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1>Sign Page</h1>
                        <div class="list-inline intro-social-buttons "> 
                            <label class="frontLabel">FirstName</label>
                            <input type="text" id="firstName" placeholder="Creat a FirstName">
                        </div>
                        <div class="list-inline intro-social-buttons "> 
                            <label class="frontLabel">LastName</label>
                            <input type="text" id="lastName" placeholder="Creat a LastName">
                        </div>
                        <div class="list-inline intro-social-buttons "> 
                            <label class="frontLabel">Telephone</label>
                            <input type="text" id="telephone" placeholder="Creat a Telephone">
                        </div>
                        <div class="list-inline intro-social-buttons "> 
                            <label class="frontLabel">MailAddress</label>
                            <input type="text" id="email" placeholder="you@example.com">
                        </div>
                        <div class="list-inline intro-social-buttons "> 
                            <label class="frontLabel">UserName</label>
                            <input type="text" id="sign-user" placeholder="Creat a username">
                        </div>
                        <div class="list-inline intro-social-buttons"> 
                            <label class="frontLabel">PassWord</label>
                            <input type="password" id="sign-pwd" placeholder="Create a password">
                        </div>
                        <ul class="list-inline intro-social-buttons login-btn">
                            <li>
                                <button class="btn btn-default btn-lg" onclick="sign()">Sign</button>
                            </li>
                            <li>
                                <button class="btn btn-default btn-lg" onclick="signCancel()">Cancel</button>
                            </li>
                        </ul>
                        
                    </div>
                </div>
            </div>


        </div>
        <!-- /.container -->


    </div>


    </div>

</body>
</html>
