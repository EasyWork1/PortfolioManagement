$(function(){
    var username = localStorage['username'];
    $("#user-box").html(username);
    getAllSymbolInfo();
    var name =  localStorage['portfolioName'] ;
    console.log("get name from cookies:"+name);
    $("#PortfolioName").html(name);
});
$(function () {
    $('#container').highcharts({
        chart: {
            type: 'spline'
        },
        title: {
            text: 'Symbols Spline'
        },

        xAxis: {
            type: 'datetime',
            title: {
                text: null
            }
        },
        yAxis: {
            title: {
                text: 'offer price'
            },
            min: 0
        },
        tooltip: {
            headerFormat: '<b>{series.name}</b><br>',
            pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'
        },
        plotOptions: {
            spline: {
                marker: {
                    enabled: true
                }
            }
        },
        series: [{

            name: '2007-2008 冬',
            // Define the data points. All series have a dummy year
            // of 1970/71 in order to be compared on the same x axis. Note
            // that in JavaScript, months start at 0 for January, 1 for February etc.
            data: [
                [Date.UTC(1970,  9, 27), 0   ],
                [Date.UTC(1970, 10, 10), 0.6 ],
                [Date.UTC(1970, 10, 18), 0.7 ],
                [Date.UTC(1970, 11,  2), 0.8 ],
                [Date.UTC(1970, 11,  9), 0.6 ],
                [Date.UTC(1970, 11, 16), 0.6 ],
                [Date.UTC(1970, 11, 28), 0.67],
                [Date.UTC(1971,  0,  1), 0.81],
                [Date.UTC(1971,  0,  8), 0.78],
                [Date.UTC(1971,  0, 12), 0.98],
                [Date.UTC(1971,  0, 27), 1.84],
                [Date.UTC(1971,  1, 10), 1.80],
                [Date.UTC(1971,  1, 18), 1.80],
                [Date.UTC(1971,  1, 24), 1.92],
                [Date.UTC(1971,  2,  4), 2.49],
                [Date.UTC(1971,  2, 11), 2.79],
                [Date.UTC(1971,  2, 15), 2.73],
                [Date.UTC(1971,  2, 25), 2.61],
                [Date.UTC(1971,  3,  2), 2.76],
                [Date.UTC(1971,  3,  6), 2.82],
                [Date.UTC(1971,  3, 13), 2.8 ],
                [Date.UTC(1971,  4,  3), 2.1 ],
                [Date.UTC(1971,  4, 26), 1.1 ],
                [Date.UTC(1971,  5,  9), 0.25],
                [Date.UTC(1971,  5, 12), 0   ]
            ]
        }, {
            name: '2008-2009 冬',
            data: [
                [Date.UTC(1970,  9, 18), 0   ],
                [Date.UTC(1970,  9, 26), 0.2 ],
                [Date.UTC(1970, 11,  1), 0.47],
                [Date.UTC(1970, 11, 11), 0.55],
                [Date.UTC(1970, 11, 25), 1.38],
                [Date.UTC(1971,  0,  8), 1.38],
                [Date.UTC(1971,  0, 15), 1.38],
                [Date.UTC(1971,  1,  1), 1.38],
                [Date.UTC(1971,  1,  8), 1.48],
                [Date.UTC(1971,  1, 21), 1.5 ],
                [Date.UTC(1971,  2, 12), 1.89],
                [Date.UTC(1971,  2, 25), 2.0 ],
                [Date.UTC(1971,  3,  4), 1.94],
                [Date.UTC(1971,  3,  9), 1.91],
                [Date.UTC(1971,  3, 13), 1.75],
                [Date.UTC(1971,  3, 19), 1.6 ],
                [Date.UTC(1971,  4, 25), 0.6 ],
                [Date.UTC(1971,  4, 31), 0.35],
                [Date.UTC(1971,  5,  7), 0   ]
            ]
        }, {
            name: '2009-2010 冬',
            data: [
                [Date.UTC(1970,  9,  9), 0   ],
                [Date.UTC(1970,  9, 14), 0.15],
                [Date.UTC(1970, 10, 28), 0.35],
                [Date.UTC(1970, 11, 12), 0.46],
                [Date.UTC(1971,  0,  1), 0.59],
                [Date.UTC(1971,  0, 24), 0.58],
                [Date.UTC(1971,  1,  1), 0.62],
                [Date.UTC(1971,  1,  7), 0.65],
                [Date.UTC(1971,  1, 23), 0.77],
                [Date.UTC(1971,  2,  8), 0.77],
                [Date.UTC(1971,  2, 14), 0.79],
                [Date.UTC(1971,  2, 24), 0.86],
                [Date.UTC(1971,  3,  4), 0.8 ],
                [Date.UTC(1971,  3, 18), 0.94],
                [Date.UTC(1971,  3, 24), 0.9 ],
                [Date.UTC(1971,  4, 16), 0.39],
                [Date.UTC(1971,  4, 21), 0   ]
            ]
        }]
    });
});

var chooseSymbol = "";
var series = "series: [";
function getSymbolData() {
    var http = 'http://localhost:8080/';
    var Id =  localStorage['portfolioId'];
    $.ajax({
        type: "POST",
        data:{portfolioid:Id},
        dataType: "json",
        url: http+"selectPositionData",
        success: function(json) {
            //打印信息
            console.log("PositionsData查询返回的数据:"+json);
            var data = eval(json);
            var symbol= "";
            var offerprice="";
            var date="";
            for(var i=0;i<data.length;i++){
                console.log("select symbol data查询返回的数据:"+json);
                symbol=data[i].symbol;
                price =data[i].price;
                series += "{" + "name: " + symbol +"data:[";
                for(var n=0;n<price.length;n++){
                    offerprice=price[n].offerprice;
                    date=price[n].date;
                    series += dataFormatChange(offerprice,date) + ",";
                }
                series-=",";
                series +="]}";

            }
        },
        error: function(json) {
            alert("load fail");
        }
    });
}
function getAllSymbolInfo() {
    var http = 'http://localhost:8080/';
    var Id =  localStorage['portfolioId'];
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
        var Id =  localStorage['portfolioId'];
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
                    chooseSymbol="";
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
function dataFormatChange(offerprice,date){
    var dataFormat = "[Date.UTC("+date.getYear() +" ," +date.getMonth() + " ," +date.getDay() +" ," + offerprice +"]";
    return dataFormat;
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



