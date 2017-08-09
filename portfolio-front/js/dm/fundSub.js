$(function(){

    getAllSymbolInfo();
});

var chooseSymbol = "";

function getAllSymbolInfo() {
    var http = 'http://localhost:8080/'; 
      $.ajax({
        type: "POST",
        data:{portfolioid:2},
        dataType: "json",
        url: http+"selectAllPositions",
        success: function(json) {
              //打印信息
            console.log("myPositions查询返回的数据:"+json);
            var data = eval(json);
            var symbol= "";
            for(var i=0;i<data.length;i++){
                console.log("data:"+i+"("+data[i].securityid+data[i].asset+")");
                // if (data[i].asset == "Bond") {
                //         symbol=data[i].isin;
                //     } else if (data[i].asset == "Future") {
                //         symbol=data[i].clralias;
                //     } else if (data[i].asset == "Stock") {
                //         symbol=data[i].symbol;
                //     }
                addSymbolRow(data[i].id,data[i].securityid,data[i].lastprice,data[i].currency,data[i].quantity,data[i].asset,data[i].datetime);
        } 
        },
        error: function(json) {
          alert("load fail");
        }
      });
}

function addStock() {
    $("#myModal").modal('hide');
    cleanModel();
	var asset = $("#chooseType").val();
    var quantity = $("#quantityNum").val();
    console.log("symbol:"+chooseSymbol+"asset:"+asset+"quantity"+quantity);
	if (chooseSymbol.length == 0 || asset == "Type" || quantity.length ==0) {
        alert("symbol information bad!"); 
        return false; 
    } 
    else {
        var http = 'http://localhost:8080/';  
        $.ajax({  
            type: "POST",  
            url: http+"insertPosition",  
            data: {securityid:chooseSymbol,asset:asset,portfolioid:2,quantity:quantity},  
            dataType: "json",  
            timeout: 15000,  
            success: function (data) {  
                var json = eval(data);
                if (json.resultCode == 1) {
                    addSymbolRow(json.id,json.securityid,json.lastprice,json.currency,json.quantity,json.asset,json.datetime);
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

function searchSymbol() {
    $("#tb_Result tr:not(:first)").html("");
    var type = $("#chooseType").val();
    var symbol = $("#searchInput").val();
    document.getElementById("tb_Result").style="display:";
    if (type == "Type") {
        alert("please choose a type!");
    	return false;
    } else {
    	var http = 'http://localhost:8080/';  
        $.ajax({  
            type: "POST",  
            url: http+"searchSecurity",  
            data: {asset:type,querysymbol:symbol},  
            dataType: "json",  
            timeout: 15000,  
            success: function (data) {  
                var json = eval(data);
            	$.each(json, function(index, item){
                    if (type == "Bond") {
                        addResultRow(item.isin);
                    } else if (type == "Future") {
                        addResultRow(item.clralias);
                    } else if (type == "Stock") {
                        addResultRow(item.symbol);
                    }
            		
            	}); 
            },
            error: function (xhr, message) {
                alert(message);
            }
        });
    }
}

function deleteStock(e) {
	event.stopPropagation();
    var positionId = e.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
    var http = 'http://localhost:8080/';  
        $.ajax({  
            type: "POST",  
            url: http+"deletePosition",  
            data: {id:positionId},  
            dataType: "json",  
            timeout: 15000,  
            success: function (data) {  
                var json = eval(data);
                if (json.resultCode == 1) {
                    document.getElementById('tb_Symbol').deleteRow(getRow(e));
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

function cleanModel() {
	$("#tb_Result tr:not(:first)").html("");
	document.getElementById("tb_Result").style="display:none";
	document.getElementById("searchInput").value="";
}

function addResultRow(symbol) {
	console.log("add a new row to tb_Result");
    var tbBody = "<tr onclick=\"trClick(this)\"><td>" + symbol + "</td></tr>";
    $("#tb_Result").append(tbBody);
}

function addSymbolRow(id,securityid,lastprice,currency,quantity,asset,dateTime)
{
    console.log("add a new row to tb_Symbol");
   
    var tbBody = "<tr><td>" +id+ "</td>"+"<td>"+ securityid + "</td>"+"<td>" + lastprice + "</td>"+"<td>" + currency + "</td>"+"<td>" +quantity+"</td>"+"<td>"+ asset + "</td>"+"<td>"+dateTime+ "</td>";

    var buttontd = "<td>"+'<button class=\"btn btn-primary btn-sm\" onclick=\"deleteStock(this)\">delete</button>'+"</td></tr>";
    tbBody += buttontd;
    $("#tb_Symbol").append(tbBody);

}

function trClick(e) {
    chooseSymbol = e.children[0].innerHTML;
    console.log("choosesymbol:"+chooseSymbol);
    var trs = document.getElementById('tb_Result').getElementsByTagName('tr'); 
    for( var o=0; o<trs.length; o++ ){  
     if( trs[o] == e ){  
        trs[o].style.backgroundColor = '#337ab7';  
     }  
     else{  
        trs[o].style.backgroundColor = '';  
     }  
    }  

}

