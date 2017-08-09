$(function(){

    //getAllStockInfo();
    jsonInfo();

});

function getAllStockInfo() {
      var http = 'http://localhost:8080/'; 
      $.ajax({
        type: "POST",
        dataType: "json",
        url: http+"selectAllStocks",
        success: function(json) {
          //打印信息
        console.log("stock查询返回的数据:"+json);
        jsonInfo(json);
          /*var typeData = json.Module;
          $.each(typeData, function(i, n) {
            var tbBody = ""
            var trColor;
            if (i % 2 == 0) {
              trColor = "even";
            }
            else {
              trColor = "odd";
            }
            tbBody += "<tr class='" + trColor + "'><td>" + n.ModuleNum + "</td>" + "<td>" + n.ModuleName + "</td>" + "<td>" + n.ModuleDes + "</td></tr>";
            $("#myTb").append(tbBody);
          });*/
        },
        error: function(json) {
          alert("Fail To Load!");
        }
      });
 }

  function jsonInfo( )
 {
        //var data = json;
        var data = [{"sector":"1","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"1","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"2","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"3","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"4","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"5","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"6","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"7","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"8","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"9","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"10","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"11","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"12","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"13","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"14","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"15","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"16","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"17","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"},
        {"sector":"18","lastsale":1.0,"offerprice":1.0,"marketcap":1,"symbol":"1","name":"1","bidprice":1.0,"industry":"1","date":1502121600000,"ipoyear":1,"currency":"1"}
        ];
        
        for(var i=0;i<data.length;i++){ 
             addRow(data[i].symbol, data[i].name,data[i].marketcap,data[i].ipoyear,data[i].sector,data[i].industry,data[i].bidprice,data[i].offerprice,data[i].date);
        } 
}

function addRow(Symbol,Name,MarketCap,IPOyear,Sector,Industry,bidPrice,offerPrice,date)
{

   
    var tbBody = "<tr><td>"+ Symbol + "</td>"+"<td>" + Name + "</td>"+"<td>" + MarketCap + "</td>"+"<td>" + IPOyear + "</td>"+"<td>" + Sector + "</td>"+"<td>" + Industry + "</td>"+"<td>" + bidPrice + "</td>"+"<td>"+offerPrice+"</td>"+"<td>"+date+"</td></tr>" ;

    $("#stockTb").append(tbBody);

}