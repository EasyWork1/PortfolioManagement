$(function(){

    getAllPortfolioInfo();
    var http = 'http://localhost:8080/';
});



function getAllPortfolioInfo() {
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
          alert("load fail");
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
    self.location = http+"fundSub.html"; 
}

function createPortfolio() {
	var portfolioName = $("#PortfolioName").val();
	if (portfolioName.length == 0) {
        alert("portfolioName cannot be null!"); 
        return false; 
    } 
    else {
        $("#myModal").modal('hide');
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


function deletePortfolio(e){
    event.stopPropagation();
    var like=window.confirm("Are you sure delete this?");
　　if(like==true) {
        var portfolioId = e.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
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
    else
        return false;
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



