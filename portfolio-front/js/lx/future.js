$(function(){

    getAllFutureInfo();
    //jsonInfo();

});

function getAllFutureInfo() {
      var http = 'http://localhost:8080/'; 
      $.ajax({
        type: "POST",
        dataType: "json",
        url: http+"selectAllFutures",
        success: function(json) {
          //打印信息
        console.log("futures查询返回的数据:"+json);
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

  function jsonInfo(json)
 {
        var data = json;
       // var data = [{"offerprice":1.0,"uomqty":1.0,"desc":"1","clralias":"1","exchid":"1","bidprice":1.0,"sym":"1","sectyp":"1","date":1502121600000,"matdt":1504022400000,"exch":"1"}];

        for(var i=0;i<data.length;i++){ 
             addRow(data[i].exchid, data[i].exch, data[i].desc,data[i].sectyp,data[i].matdt,data[i].uomqty,data[i].bidprice,data[i].offerprice,data[i].date);
        } 
}

function addRow(exchid,exchName,desc,secTyp,maturity,uomQty,bidPrice,offerPrice,date)
{

    var tbBody = "<tr><td>"+ exchid+ "</td>"+"<td>" +exchName + "</td>"+"<td>" + desc + "</td>"+"<td>" + secTyp + "</td>"+"<td>" + maturity + "</td>"+"<td>" + uomQty + "</td>"+"<td>" + bidPrice + "</td>"+"<td>"+offerPrice+"</td>"+"<td>"+date+"</td></tr>" ;

    $("#futureTb").append(tbBody);

}