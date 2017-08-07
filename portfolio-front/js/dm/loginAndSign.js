function login() {
    var user = $("#user").val();
    var pass = $("#pwd").val();
    if (user.length == 0 || pass.length == 0) {
        alert("username or password cannot be none!"); 
        $("#pwd").value() = ""; 
        return false; 
    } 
    else {
        self.location = "index.html";

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

function cancel() {
    // $("#user").value() = ""; 
    // $("#pwd").value() = ""; 
    // return false; 
} 

