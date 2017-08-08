function trClick(e) {
    var Id = e.children[0].innerHTML;
    console.log(Id);
    self.location = "fundSub.html"; 
}

function createPortfolio() {
	var portfolioName = $("#PortfolioName").val();
	if (portfolioName.length == 0) {
        alert("portfolioName cannot be null!"); 
        return false; 
    } 
    else {
        var http = 'http://localhost:8080/';  
        $.ajax({  
            type: "POST",  
            url: http+"insertPortfolio",  
            data: {name:portfolioName,fundManagerId:1},  
            dataType: "json",  
            timeout: 15000,  
            success: function (data) {  
                var json = eval(data);
                if (json.resultCode == 1) {
                    $("#myModal").modal('hide');
                    con = "<tr onclick=\"trClick(this)\">";
                    con += "<td>"+ json.id + "</td>";
                    con += "<td>"+ json.name + "</td>";
                    con += "<td>"+ json.fundManagerid + "</td>";
                    con += "<td>"+ json.benefit + "</td>";
                    con += "<td><button class=\"btn btn-primary btn-sm\" onclick=\"deletePortfolio(this)\">delete</button></td></tr>";
                     $("#tb_Portfolio").append(con);     
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

function addStock() {
	var stock = $("#stockshow").val();
    console.log(stock);
    $("#myModal").modal('hide');
	// var http = 'http://localhost:8080/';  
 //        $.ajax({  
 //            type: "POST",  
 //            url: http+"register",  
 //            data: { },  
 //            dataType: "json",  
 //            timeout: 15000,  
 //            success: function (data) {  
 //                var json = eval(data);
 //                if (json.resultCode == 1) {
                    
 //                } else {
 //                    alert(json.errorMessage);
 //                }
 //            },
 //            error: function (xhr, message) {
 //                alert(message);
 //            }
 //        });
}

function deletePortfolio(e){
    var portfolioId = e.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
    var http = 'http://localhost:8080/';  
        $.ajax({  
            type: "POST",  
            url: http+"deletePortfolio",  
            data: {id:portfolioId},  
            dataType: "json",  
            timeout: 15000,  
            success: function (data) {  
                var json = eval(data);
                if (json.resultCode == 1) {
                    document.getElementById('tb_Portfolio').deleteRow(getRow(e));  
                } else {
                    alert(json.errorMessage);
                }
            },
            error: function (xhr, message) {
                alert(message);
            }
        });
}

function deleteStock(e) {
    var portfolioId = e.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
    var http = 'http://localhost:8080/';  
        $.ajax({  
            type: "POST",  
            url: http+"deletePortfolio",  
            data: {id:portfolioId},  
            dataType: "json",  
            timeout: 15000,  
            success: function (data) {  
                var json = eval(data);
                if (json.resultCode == 1) {
                    document.getElementById('tb_fundSub').deleteRow(getRow(e));   
                } else {
                    alert(json.errorMessage);
                }
            },
            error: function (xhr, message) {
                alert(message);
            }
        });
}

function getRow(r){
     var i=r.parentNode.parentNode.rowIndex; 
     return i ;
}