$(function(){

    getAllSymbolInfo();
});

var chooseSymbol = "";

function getAllSymbolInfo() {
    var http = 'http://localhost:8080/';
    var Id =  getCookie("portfolioid");
      $.ajax({
        type: "POST",
        data:{portfolioid:Id},
        dataType: "json",
        url: http+"selectAllPositions",
        success: function(json) {
              //打印信息
            console.log("myPositions查询返回的数据:"+json);
            var data = eval(json);
            var symbol= "";
            for(var i=0;i<data.length;i++){
                console.log("data:"+i+"("+data[i].securityid+data[i].asset+")");
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
    console.log("symbol:"+chooseSymbol+"asset:"+asset+"quantity:"+quantity);
	if (chooseSymbol.length == 0 || asset == "Type" || quantity.length ==0) {
        alert("symbol information bad!"); 
        return false; 
    } 
    else {
        var http = 'http://localhost:8080/';  
        var Id =  getCookie("portfolioid");
        $.ajax({  
            type: "POST",  
            url: http+"insertPosition",  
            data: {securityid:chooseSymbol,asset:asset,portfolioid:Id,quantity:quantity},  
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
    $("#tb_Bond tr:not(:first)").html("");
    $("#tb_Stock tr:not(:first)").html("");
    $("#tb_Future tr:not(:first)").html("");
    document.getElementById("tb_Future").style="display:none";
    document.getElementById("tb_Stock").style="display:none";
    document.getElementById("tb_Bond").style="display:none";
    var type = $("#chooseType").val();
    var symbol = $("#searchInput").val();
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
                        document.getElementById("tb_Bond").style="display:";
                        addResultRow(item.isin,item.issuer,type);
                    } else if (type == "Future") {
                        document.getElementById("tb_Future").style="display:";
                        addResultRow(item.clralias,item.exch,type);
                    } else if (type == "Stock") {
                        document.getElementById("tb_Stock").style="display:";
                        addResultRow(item.symbol,item.name,type);
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
    var like=window.confirm("Are you sure delete this?");
　　if(like==true) {
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
　　else
        return false;

    
}

function getRow(r){
     var i=r.parentNode.parentNode.rowIndex; 
     return i ;
}

function cleanModel() {
	$("#tb_Bond tr:not(:first)").html("");
    $("#tb_Stock tr:not(:first)").html("");
    $("#tb_Future tr:not(:first)").html("");
	document.getElementById("tb_Future").style="display:none";
    document.getElementById("tb_Stock").style="display:none";
    document.getElementById("tb_Bond").style="display:none";
	document.getElementById("searchInput").value="";
}

function addResultRow(symbol,descripe,asset) {
	console.log("add a new row to tb_Result");
    var tbBody = "<tr onclick=\"trClick(this)\"><td>" + symbol + "</td><td>" + descripe + "</td></tr>";
    if (asset == "Bond") {
        $("#tb_Bond").append(tbBody);
    } else if (asset == "Future") {
        $("#tb_Future").append(tbBody);
    } else if (asset == "Stock") {
        $("#tb_Stock").append(tbBody);
    }
    
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
    var type = $("#chooseType").val();
    var trs ;
    if (type == "Bond") {
        trs = document.getElementById('tb_Bond').getElementsByTagName('tr'); 
    } else if (type == "Future") {
        trs = document.getElementById('tb_Future').getElementsByTagName('tr');
    } else if (type == "Stock") {
        trs = document.getElementById('tb_Stock').getElementsByTagName('tr');
    }
    for( var o=0; o<trs.length; o++ ){  
     if( trs[o] == e ){  
        trs[o].style.backgroundColor = '#337ab7';  
     }  
     else{  
        trs[o].style.backgroundColor = '';  
     }  
    }  

}

function getCookie(c_name)
{
    if (document.cookie.length>0)
      {
      c_start=document.cookie.indexOf(c_name + "=")
      if (c_start!=-1)
        { 
        c_start=c_start + c_name.length+1 
        c_end=document.cookie.indexOf(";",c_start)
        if (c_end==-1) c_end=document.cookie.length
        return unescape(document.cookie.substring(c_start,c_end))
        } 
      }
    return ""
}

