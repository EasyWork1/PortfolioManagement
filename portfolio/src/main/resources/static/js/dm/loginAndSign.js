function login() {
    var user = $("#user").val();
    var pass = $("#pwd").val();
    if (user.length == 0 || pass.length == 0) {
        alert("username or password cannot be none!");
        return false; 
    } 
    else {
        var http = 'http://localhost:8080';
        $.ajax({
            type:"POST",
            url:http+"/login",
            data:{username:user,password:pass},
            dataType:"json",
            success:function (data) {
                self.location = "index.html";
            },
            error:function (xhr,message) {
                console.log("error");
            }
        })


    // var theUrl = "/api/productsuggestions/" + id;
    // $.ajax({
    //     type: "GET",
    //     url: theUrl,
    //     cache: false,

    //     accepts: {
    //         json: 'application/json'
    //     },

    //     success: function (data) {
    //         if (data === null) {
    //             alert("Could not get item");
    //         }
    //         else {
    //             displayProductSuggestion(data);
    //         }
    //     },

    //     error: function (xhr, message) {
    //         displayError(xhr, message, $("#id").val());
    //     }
    // });
    }
}

function sign() {
    var user = $("#sign-user").val();
    var pass = $("#sign-pwd").val();
    var address = $("#sign-mail").val();
    if (user.length == 0 || pass.length == 0 || address.length ==0) {
        alert("information imperfect !"); 
        return false; 
    } 
    else {
        self.location = "login.html";
    }
}

function cancel() {
    // $("#user").value() = ""; 
    // $("#pwd").value() = ""; 
    // return false; 
} 



