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
        $("#myModal").modal('hide');
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
                    addPortfolioRow(json.id,json.name,json.fundManagerid,json.benefit); 
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

function addPortfolioRow(id,name,fundManagerid,benefit)
{
    console.log("add a new row to tb_Portfolio");
   
    var tbBody = "<tr onclick=\"trClick(this)\"><td>" + id + "</td>"+"<td>" + name + "</td>"+"<td>" + fundManagerid + "</td>"+"<td>" + benefit ;

    var buttontd = "<td>"+'<button class=\"btn btn-primary btn-sm\" onclick=\"deletePortfolio(this)\">delete</button>'+"</td></tr>";
    tbBody += buttontd;
    $("#tb_Portfolio").append(tbBody);

}



