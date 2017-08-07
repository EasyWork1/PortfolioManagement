function login() {
    var user = $("#user").val();
    var pass = $("#pwd").val();
    if (user.length == 0 || pass.length == 0) {
        alert("username or password cannot be none!"); 
        $("#pwd").value() = ""; 
        return false; 
    } 
    else {
        
        var http = 'http://localhost:8080/';  
                $.ajax({  
                    type: "POST",  
                    url: http+"login",  
                    data: { username: user,password:pass},  
                    dataType: "json",  
                    timeout: 15000,  
                    success: function (data) {  
                        self.location = "index.html";  
                    },
                    error: function (xhr, message) {
                        displayError(xhr, message);
                    }
                });
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
                        self.location = "login.html";  
                    },
                    error: function (xhr, message) {
                        displayError(xhr, message);
                    }
                });
    }
}

function cancel() {
    // $("#user").value() = ""; 
    // $("#pwd").value() = ""; 
    // return false; 
} 



