$(function(){
    var username = localStorage['username'];
    $("#user-box").html(username);
    console.log("history begin");
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
        	setColor();
        },
        error: function(json) {
          alert("load fail");
        }
      });
}


function addHistoryRow(securityid,asset,lastprice,quantity,buyorsell,currency,datetime)
{
    console.log("add a new row to tb_History");
   
    var tbBody = "<tr><td class=\"active\">" + securityid + "</td>"+"<td class=\"success\">" + asset + "</td>"+"<td class=\"warning\">" + lastprice + "</td>"+"<td class=\"danger\">" +quantity+ "</td>"+"<td class=\"active\">"+ buyorsell + "</td>"+"<td class=\"success\">" +currency+ "</td>"+"<td class=\"warning\">" +datetime+"</td></tr>";

    $("#tb_History").append(tbBody);

}

function setColor(){
	$("#tb_History tr td:nth-child(5)").each(function() {
        if($(this).text() == "SELL") {
            $(this).css("color", "#f00");
        } else{
            $(this).css("color", "#228B22");
        }
    });
}