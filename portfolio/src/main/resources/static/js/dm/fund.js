$(function(){

    getAllPortfolioInfo();

});

function getAllPortfolioInfo() {
      var http = 'http://localhost:8080/'; 
      $.ajax({
        type: "POST",
        data:{fundManagerId:1},
        dataType: "json",
        url: http+"myportfolio",
        success: function(json) {
              //打印信息
            console.log("myportfolio查询返回的数据:"+json);
            jsonInfo(json);
        },
        error: function(json) {
          alert("加载失败");
        }
      });
 }

 function jsonInfo(json)
 {
        var data = json;
        for(var i=0;i<data.length;i++){ 
             addPortfolioRow(data[i].id,data[i].name,data[i].symbols,data[i].lotvalue,data[i].benefit);
        } 
}

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
                    addPortfolioRow(json.id,json.name,json.symbols,json.lotvalue,json.benefit); 
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
    event.stopPropagation(); 
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

function addPortfolioRow(id,name,symbols,Lotvalue,benefit)
{
    console.log("add a new row to tb_Portfolio");
   
    var tbBody = "<tr onclick=\"trClick(this)\"><td>" + id + "</td>"+"<td>" + name + "</td>"+"<td>" + symbols + "</td>"+"<td>" +Lotvalue+ "</td>"+"<td>"+ benefit +"</td>";

    var buttontd = "<td>"+'<button class=\"btn btn-primary btn-sm\" onclick=\"deletePortfolio(this)\">delete</button>'+"</td></tr>";
    tbBody += buttontd;
    $("#tb_Portfolio").append(tbBody);

}



