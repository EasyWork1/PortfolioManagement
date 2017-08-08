 //获取发布模块类型
    function getModuleInfo() {
      var http = 'http://localhost:8080/'; 
      $.ajax({
        type: "POST",
        dataType: "json",
        url: http+"selectAllFundManagers",
        success: function(json) {
          //打印信息
        console.log("fundmanager查询返回的数据"+json);
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
          alert("加载失败");
        }
      });
    }
    $(function() {
      getModuleInfo();
    });



/*$(function(){
    $('#send').click(function(){
         $.ajax({
             type: "GET",
             url: "test.json",
             data: {username:$("#username").val(), content:$("#content").val()},
             dataType: "json",
             success: function(data){
                         $('#resText').empty();   //清空resText里面的所有内容
                         var html = ''; 
                         $.each(data, function(commentIndex, comment){
                               html += '<div class="comment"><h6>' + comment['username']
                                         + ':</h6><p class="para"' + comment['content']
                                         + '</p></div>';
                         });
                         $('#resText').html(html);
                      }
         });
    });
});*/