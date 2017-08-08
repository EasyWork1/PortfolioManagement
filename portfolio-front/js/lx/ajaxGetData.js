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
      //getModuleInfo();
      //addRow("15","Eric","sam","1238","@gmail.com","eric","123");
    });


function createTable()
{
  //定义行元素标签 
  var tr=document.createElement('tr'); 
           

}


/*function addRow(id,firstName,lastName,telephone,email,username,password)
{

        var rs = $("#tb_manager").rows;  //table取到所有的行
        var insertR = $("#stb_manager").insertRow(1); //给表格添加一行(不包单元格)
        //insertR.innerHTML = rs[1].innerHTML;    
        var c0 = insertR.insertCell(0);       
        c0.innerHTML = id;

        var c1 = insertR.insertCell(1);
        c1.innerHTML = firstName;

        var c2 = insertR.insertCell(2);
        c2.innerHTML = lastName;

        var c3 = insertR.insertCell(3);
        c.innerHTML = telephone;

        var c4 = insertR.insertCell(4);
        c.innerHTML = email;

        var c5 = insertR.insertCell(5);
        c.innerHTML = username;

        var c6 = insertR.insertCell(6);
        c.innerHTML = password;

        var c7 = insertR.insertCell(7);
        c7.innerHTML ='<input type="button" value="删除" class="del"/><input type="button" value="修改" class="update"/>';


}*/








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