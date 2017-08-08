
$(function(){

    getAllManagerInfo();
    //jsonInfo();

});

function getAllManagerInfo() {
      var http = 'http://localhost:8080/'; 
      $.ajax({
        type: "POST",
        dataType: "json",
        url: http+"selectAllFundManagers",
        success: function(json) {
          //打印信息
        console.log("fundmanager查询返回的数据:"+json);
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
       //var data = [{"id":15,"username":"1","email":"1","lastname":"1","firstname":"1","telephone":"1","password":"xMpCOKC5I4INzFCab3WEmw=="},{"id":16,"username":"2","email":"2","lastname":"2","firstname":"2","telephone":"2","password":"yB5yjZ1ML2NvBn+JzBSGLA=="},{"id":17,"username":"pyr","email":"385121039@qq.com","lastname":"Pan","firstname":"Youran","telephone":"17621587563","password":"ICy5YqxZB1uWSwcVLSNLcA=="},{"id":18,"username":"zjh","email":"385121038@qq.com","lastname":"Zhou","firstname":"Jiehui","telephone":"18428367563","password":"ICy5YqxZB1uWSwcVLSNLcA=="}];
        
        for(var i=0;i<data.length;i++){ 
             addRow(data[i].id,data[i].firstname,data[i].lastname,data[i].telephone,data[i].email,data[i].username,data[i].password);
        } 
}




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
    var ID = r.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;
    console.log("ready to delete a manager id："+ID);
    
   // var arr = queryInfoByRow(r);
    //console.log("ready to delete a manager:id"+arr[0]+"/name:"+arr[1]);

   //ajax,向后台发送请求
   var http = 'http://localhost:8080/';  
                $.ajax({  
                    type: "POST",  
                    url: http+"deleteFundManager",  
                    data: { id:ID},  
                    dataType: "json",  
                    timeout: 15000,  
                    success: function (data) {  
                        console.log("删除后台返回的数据："+data);
                        //解析数据成功才上传
                       // document.getElementById('tb_manager').deleteRow(getRow(r));
                        var json = eval(data);
                        if (json.resultCode == 1) {
                            self.location = "showFundManagerForm";
                            alert("Delete Success!"); 
                        } else {
                            alert(json.errorMessage);
                        }
                        
                    },
                    error: function (xhr, message) {
                        alert("Access Failure!");

                        console.log("访问失败");
                        
                    }
                });
        // alert("删除成功！！"); 
}


//----根据行号查信息----没用-----------
function queryInfoByRow(r){
  
 var arr = new Array();
 for(var m=0 ; m<6;m++){

  //这里row要加1，否则就是表头
  arr[m] = document.getElementById('tb_manager').rows[2+1].cells[m].innerText;
  console.log("Elaine:"+arr[m]);
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
        //console.log("input correct："+firstName+"/"+lastName+"/"+telephone+"/"+email+"/"+username+"/"+password);

        //ajax向后台发送请求，参数要改
        var http = 'http://localhost:8080/';  
                $.ajax({  
                    type: "POST",  
                    url: http+"register",  
                    data: {firstName:firstName ,lastName:lastName,telephone:telephone,email:email,username:username,password:password},  
                    dataType: "json",  
                    timeout: 15000,  
                    success: function (data) {  
                        console.log("增加后台返回的数据："+data);
                       // addRow(id,firstName,lastName,telephone,email,username,password);
                        var json = eval(data);
                        if (json.resultCode == 1) {
                            self.location = "showFundManagerForm";
                            alert("Add Success！"); 
                        } else {
                            alert(json.errorMessage);
                        }
                    },
                    error: function (xhr, message) {
                        alert("Access Failure!");
                        console.log("访问失败");
                        
                    }
                });
        
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

function addRow(id,firstName,lastName,telephone,email,username,password)
{
    console.log("add a new row to table");
   
    var tbBody = "<tr><td>" + id + "</td>"+"<td>" + firstName + "</td>"+"<td>" + lastName + "</td>"+"<td>" + telephone + "</td>"+"<td>" + email + "</td>"+"<td>" + username + "</td>"+"<td>" + password + "</td>" ;

    var buttontd = "<td>"+'<input type="button" value="delete" class="btn btn-primary btn-sm" onclick="deleteManager(this);"/><input type="button" value="modify" class="btn btn-primary btn-sm" onclick="modManager(this);"/>'+"</td></tr>";
    tbBody += buttontd;
    $("#myTb").append(tbBody);

}

function modManager(obj){
    
    var operatorCell1 = obj.parentNode.parentNode.getElementsByTagName("td")[1]; //取到要操作的td对象
    var operatorCell2 = obj.parentNode.parentNode.getElementsByTagName("td")[2]; //取到要操作的td对象
    var operatorCell3 = obj.parentNode.parentNode.getElementsByTagName("td")[3]; //取到要操作的td对象
    var operatorCell4 = obj.parentNode.parentNode.getElementsByTagName("td")[4]; //取到要操作的td对象
   
    if(obj.value == "modify"){
                    obj.value = "ok";
                    operatorCell1.innerHTML ="<input value='"+operatorCell1.innerHTML+"'/>";//把内容变成文本框
                    operatorCell2.innerHTML ="<input value='"+operatorCell2.innerHTML+"'/>";
                    operatorCell3.innerHTML ="<input value='"+operatorCell3.innerHTML+"'/>";
                    operatorCell4.innerHTML ="<input value='"+operatorCell4.innerHTML+"'/>";
                    //做修改操作
            }else{

                    var firstname = operatorCell1.getElementsByTagName("input")[0].value;
                    var lastname = operatorCell2.getElementsByTagName("input")[0].value;
                    var telephone = operatorCell3.getElementsByTagName("input")[0].value;
                    var email = operatorCell4.getElementsByTagName("input")[0].value;
                    operatorCell1.innerHTML = firstname;
                    operatorCell2.innerHTML = lastname;//把文本框变成内容
                    operatorCell3.innerHTML = telephone;//把文本框变成内容
                    operatorCell4.innerHTML = email;//把文本框变成内容

                    var ID = obj.parentNode.parentNode.getElementsByTagName("td")[0].innerHTML;

                    console.log("ready to modify a fundmanager, ID:"+ID);

                    //ajax,向后台发送请求
                    var http = 'http://localhost:8080/';  
                    $.ajax({  
                    type: "POST",  
                    url: http+"updateFundManager",  
                    data: { id:ID, firstName:firstname,lastName:lastname,telephone:telephone,email:email},  
                    dataType: "json",  
                    timeout: 15000,  
                    success: function (data) {  
                        console.log("修改后台返回的数据："+data);
                        //解析数据成功才上传
                       // document.getElementById('tb_manager').deleteRow(getRow(r));
                        var json = eval(data);
                        if (json.resultCode == 1) {
                            alert("Modify Success!"); 
                            self.location = "showFundManagerForm";
                            //obj.value = "modify";                          
                        } else {
                            alert(json.errorMessage);
                        }
                        
                    },
                    error: function (xhr, message) {
                        alert("Access Failure!");

                        console.log("访问失败");
                        
                    }
                });
       
                }

     
     
}
