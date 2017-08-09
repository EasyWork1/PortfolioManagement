function login() {
    console.log("begin to login");
    var user = $("#user").val();
    var pass = $("#pwd").val();
    if (user.length == 0 || pass.length == 0) {
        alert("username or password cannot be null!");
        cancel();
        return false; 
    } 
    else {
        
        if(user=='Admin'&&pass=='123456')
        {
            self.location = "FundManager.html";
        }
        else
        {
        var http = 'http://localhost:8080/';  
                $.ajax({  
                    type: "POST",  
                    url: http+"login",  
                    data: { username: user,password:pass},  
                    dataType: "json",  
                    timeout: 15000,  
                    success: function (data) {  
                        var json = eval(data);
                        if (json.resultCode == 1) {
                            self.location = "index.html?username="+json.username+"&id="+json.id;
                        } else {
                            alert(json.errorMessage);
                        }
                    },
                    error: function (xhr, message) {
                        alert("Access Failure!");
                    }
                });
        }
    }
}

function sign() {
    var firstName = $("#firstName").val();
    var telephone = $("#telephone").val();
    var lastName = $("#lastName").val();
    var email = $("#email").val();
    var userName = $("#sign-user").val();
    var passWord = $("#sign-pwd").val();
    if (firstName.length == 0 || telephone.length == 0 || lastName.length ==0||email.length == 0 || userName.length == 0 || passWord.length ==0) {
        alert("information imperfect !"); 
        return false; 
    } 
    else {
        var http = 'http://localhost:8080/';  
                $.ajax({  
                    type: "POST",  
                    url: http+"register",  
                    data: { firstName: firstName,lastName:lastName,telephone:telephone,email:email,username:userName,password:passWord},  
                    dataType: "json",  
                    timeout: 15000,  
                    success: function (data) {  
                        var json = eval(data);
                        if (json.resultCode == 1) {
                            self.location = "fund.html?username="+json.username+"&id="+json.id;
                        } else {
                            alert(json.errorMessage);
                        }
                    },
                    error: function (xhr, message) {
                        alert(message);
                    }
                });
    }
}

function cancel() {
    document.getElementById("user").value="";
    document.getElementById("pwd").value="";
} 

function signCancel() {
    self.location = "login.html";
} 



