$(function(){
    var username = localStorage['username'];
    $("#user-box").html(username);
    getAllSymbolInfo();
    var name =  localStorage['portfolioName'] ;
    console.log("get name from cookies:"+name);
    $("#PortfolioNameSub").html(name);
    
});

var chooseSymbol = "";

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
			var totalAmount = localStorage['lotvalue'];
			
			showPieChart(json,totalAmount);
			
            var data = eval(json);
            var symbol= "";
            for(var i=0;i<data.length;i++){
                console.log("data:"+i+"("+data[i].securityid+data[i].asset+")");
                addSymbolRow(data[i].id,data[i].securityid,data[i].lastprice,data[i].benifit,data[i].currency,data[i].quantity,data[i].asset,data[i].datetime);
            } 
            setColor();
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
                    addSymbolRow(json.id,json.securityid,json.lastprice,json.benifit,json.currency,json.quantity,json.asset,json.datetime);
                    chooseSymbol="";
                    setColor();
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

function addSymbolRow(id,securityid,lastprice,benefit,currency,quantity,asset,dateTime)
{
    console.log("add a new row to tb_Symbol");
   
    var tbBody = "<tr onclick=\"PositionClick(this)\"><td class=\"active\">" +id+ "</td>"+"<td class=\"success\">"+ securityid + "</td>"+"<td class=\"warning\">" + lastprice +"</td>"+"<td class=\"danger\">"+ benefit+"</td>"+"<td class=\"active\">" + currency + "</td>"+"<td class=\"success\">" +quantity+"</td>"+"<td class=\"warning\">"+ asset + "</td>"+"<td class=\"danger\">"+dateTime+ "</td>";

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

function PositionClick(e) {
    console.log("dianji");
    var symbol = e.children[1].innerHTML;
    var http = 'http://localhost:8080/';  
    $.ajax({  
        type: "POST",  
        url: http+"selectSymbolData",  
        data: {symbol:symbol},  
        dataType: "json",  
        timeout: 15000,  
        success: function (data) {  
            var bidpriceArray = new Array();
            var offerpriceArray = new Array();
            $.each(data, function(index, item){
                    var date= new Date(item.date);
                    var dateutc=Date.UTC(date.getFullYear(), date.getMonth(), date.getDate());
                    var myDate=new Array();
                    var offerDate = new Array();
                    myDate.push(dateutc);
                    myDate.push(item.bidprice);
                    bidpriceArray.push(myDate);
                    offerDate.push(dateutc);
                    offerDate.push(item.offerprice);
                    offerpriceArray.push(offerDate);
                }); 
            showChart(symbol,bidpriceArray,offerpriceArray);
        },
        error: function (xhr, message) {
            alert(message);
        }
    });
}

function showChart(positionName,bidpriceArray,offerpriceArray) {
    $("#lineModal").modal('show');
    $('#container').highcharts({
        chart: {
            type: 'spline'
        },title: {
            text: positionName
        },
        xAxis: {
            type: 'datetime',
            title: {
                text: null
            }
        },
        yAxis: {
            title: {
                text: 'price ($)'
            },
            min: 0
        },
        tooltip: {
            headerFormat: '<b>{series.name}</b><br>',
            pointFormat: '{point.x:%e. %b}: ${point.y:.2f} '
        },
        plotOptions: {
            spline: {
                marker: {
                    enabled: true
                }
            }
        },
        series: [{
            name: "bidprice",
            data: bidpriceArray
        },{
            name: "offerprice",
            data: offerpriceArray
        }
        ]
    });
}


function setColor() {
    $("#tb_Symbol tr td:nth-child(4)").each(function() {
        if(parseFloat($(this).text()) < 0) {
            $(this).css("color", "#f00");
        } else{
            $(this).css("color", "#228B22");
        }
    });
}

//前面要调用这个方法
function showPieChart(data,totalAmount)
{    
    var arr = new Array();  
    var obj;
    
    for(var i=0;i<data.length;i++){ 
        
        obj = new Array();
        obj[0] = data[i].securityid;
        obj[1] = (data[i].quantity * data[i].lastprice + data[i].benifit)/totalAmount;
        console.log("饼图值:"+obj[0]+":"+obj[1] );
        
        arr.push(obj);
    } 


    $('#pieContainer').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title:{
          text:'Percentage'
        },
        tooltip: {
            headerFormat: '{series.name}<br>',
            pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'occupation',
            data: arr
        }]
    });

}




