$(function(){
    var username = localStorage['username'];
    $("#user-box").html(username);
    getAllHistory();

});

function getAllHistory() {
    var http = 'http://localhost:8080/';
    var fundManagerId = localStorage['fundManagerid'];
      $.ajax({
        type: "POST",
        data:{fundmanagerid:fundManagerId},
        dataType: "json",
        url: http+"selectAllHistory",
        success: function(json) {
            var data = eval(json);
            for(var i=0;i<data.length;i++){
                console.log("data:"+i+"("+data[i].securityid+data[i].asset+")");
                addHistoryRow(data[i].securityid,data[i].asset,data[i].lastprice,data[i].quantity,data[i].buyorsell,data[i].currency,data[i].datetime);
        } 
        },
        error: function(json) {
          alert("load fail");
        }
      });
}


function addHistoryRow(securityid,asset,lastprice,quantity,buyorsell,currency,datetime)
{
    console.log("add a new row to tb_History");
   
    var tbBody = "<tr><td>" + securityid + "</td>"+"<td>" + asset + "</td>"+"<td>" + lastprice + "</td>"+"<td>" +quantity+ "</td>"+"<td>"+ buyorsell + "</td>"+"<td>" +currency+ "</td>"+"<td>" +datetime+"</td></tr>";

    $("#tb_History").append(tbBody);

}