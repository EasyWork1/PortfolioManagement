


$(function(){

    console.log("add to table");
   
   var tbBody = "<tr><td>" + "Eric" + "</td>"+"<td>" + "Green" + "</td>"+"<td>" + "12324" + "</td>"+"<td>" + "12324@gmail.com" + "</td>"+"<td>" + "Citi" + "</td>"+"<td>" + "123455" + "</td>" +"</tr>";

   $("#myTb").append(tbBody);
});




 function changeVisibility()
{
            document.getElementById("add_edit_div").style.display="block";
            document.getElementById("btn_save").style.display="block";
            document.getElementById("btn_cancel").style.display="block";
}

 function changeVisibility2()
{
            document.getElementById("add_edit_div").style.display="none";
            document.getElementById("btn_save").style.display="none";
            document.getElementById("btn_cancel").style.display="none";
}

function cancelManager()
{

    //编辑框中的内容消失
    deleteInputText();
    
    //添加框消失
    changeVisibility2();
}

var row = 0 ; //定义全局行数用于修改
//----获取行号-----
function getRow(r){
 var i=r.parentNode.parentNode.rowIndex; 
 return i ;
}
function deleteManager(r)
{
    var arr = queryInfoByRow(r);
    console.log("ready to delete a manager:username"+arr[4]);

   //ajax,向后台发送请求
   var http = 'http://localhost:8080/';  
                $.ajax({  
                    type: "POST",  
                    url: http+"deleteManager",  
                    data: { id:15},  
                    dataType: "json",  
                    timeout: 15000,  
                    success: function (data) {  
                        console.log("删除后台返回的数据："+data);
                        //解析数据成功才上传
                        document.getElementById('tb_manager').deleteRow(getRow(r));
                    },
                    error: function (xhr, message) {

                        console.log("访问失败");
                        
                    }
                });
        alert("添加成功！！"); 
}

//弃用
function del(obj) {
    var trId = obj.parentNode.parentNode.id;
    console.log(trId);
}


//----根据行号查信息----
function queryInfoByRow(r){
  
 var arr = new Array();
 for(var m=0 ; m<6;m++){

  //这里row要加1，否则就是表头
  arr[m] = document.getElementById('tb_manager').rows[row+1].cells[m].innerText;
  console.log(arr[m]);
 }
 return arr ; //返回该行数据
  
}

function addManager()
{
    
    console.log("ready to add  a manager");
    //获取文本框数据
    var firstName = $('#i1').val();//firstName
    var lastName = $('#i2').val();
    var telephone = $('#i3').val();
    var email = $('#i4').val();
    var username = $('#i5').val();
    var password = $('#i6').val();
    
    if (firstName.length == 0 || lastName.length == 0||telephone.length==0||email.length==0) {
        alert("input cannot be none!"); 
    }
    else
    {
        console.log("input correct："+firstName+"/"+lastName+"/"+telephone+"/"+email+"/"+username+"/"+password);

        //ajax向后台发送请求，参数要改
        var http = 'http://localhost:8080/';  
                $.ajax({  
                    type: "POST",  
                    url: http+"register",  
                    data: { firstName:firstName ,lastName:lastName,telephone:telephone,email:email,username:username,password:password},  
                    dataType: "json",  
                    timeout: 15000,  
                    success: function (data) {  
                        console.log("增加后台返回的数据："+data);
                        var tbBody = "<tr><td>" + firstName + "</td>"+"<td>" + lastName + "</td>"+"<td>" + telephone + "</td>"+"<td>" + email + "</td>"+"<td>" + username + "</td>"+"<td>" + password + "</td>" +"</tr>";
                        $("#myTb").append(tbBody);
                    },
                    error: function (xhr, message) {

                        console.log("访问失败");
                        
                    }
                });
        alert("添加成功！！"); 
    }
    

}




function deleteInputText()
{
     document.getElementById("i1").value="";
      document.getElementById("i2").value="";
       document.getElementById("i3").value="";
        document.getElementById("i4").value="";
}

//网页加载完成后执行该onload事件 
/*onload = function( ){ 
    var tbBody="";

    for(var i=0;i<data.length;i++){ 
   
    tbBody = "<tr><td>" + data[i].firstName + "</td>"+"<td>" + data[i].lastName + "</td>"+"<td>" + data[i].telephone + "</td>"+"<td>" + data[i].email + "</td>"+"<td>" + data[i].username + "</td>"+"<td>" + data[i].password + "</td>" +"</tr>";
   
    $("#myTb").append(tbBody);
} 
    
}; */