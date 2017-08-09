$(function(){

    getAllBondInfo();
    //jsonInfo();

});

function getAllBondInfo() {
      var http = 'http://localhost:8080/'; 
      $.ajax({
        type: "POST",
        dataType: "json",
        url: http+"selectAllBonds",
        success: function(json) {
          //打印信息
        console.log("bonds查询返回的数据:"+json);
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
       // var data = [{"offerprice":1.0,"issuer":"1","maturityyear":1,"maturitymonth":"1","bidprice":1.0,"date":1502121600000,"coupon":1.0,"isin":"1"}];

        for(var i=0;i<data.length;i++){ 
             addRow(data[i].isin, data[i].issuer, data[i].coupon,data[i].maturitymonth,data[i].maturityyear,data[i].bidprice,data[i].offerprice,data[i].date);
        } 
}

function addRow(isin,issuer,coupon,maturitymonth,maturityyear,bidPrice,offerPrice,date)
{

   
    var tbBody = "<tr><td>"+ isin + "</td>"+"<td>" + issuer + "</td>"+"<td>" + coupon + "</td>"+"<td>" + maturitymonth + "</td>"+"<td>" + maturityyear + "</td>"+"<td>" + bidPrice + "</td>"+"<td>"+offerPrice+"</td>"+"<td>"+date+"</td></tr>" ;

    $("#bondTb").append(tbBody);

}