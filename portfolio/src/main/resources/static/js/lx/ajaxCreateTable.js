ajaxPost({  
         type:"",  
         url:"",  
         data:"datas="+datas;//要发送的数据
         dataType:"json"  
                                
                 //object是后台传过来的java list数据集合  
                   success:function(objects){                         
                                        
                      //1,获取上面id为cloneTr的tr元素  
                       var tr = $("#cloneTr");  
  
                       $.each(objects, function(index,item){                              
                             //克隆tr，每次遍历都可以产生新的tr                              
                               var clonedTr = tr.clone();  
                               var _index = index;  
                              
                               //循环遍历cloneTr的每一个td元素，并赋值  
                               clonedTr.children("td").each(function(inner_index){  
                                  
                                      //根据索引为每一个td赋值  
                                            switch(inner_index){  
                                                  case(0):   
                                                     $(this).html(_index + 1);  
                                                     break;  
                                                  case(1):  
                                                     $(this).html(item.caller);  
                                                     break;  
                                                 case(2):  
                                                     $(this).html(item.imsi);  
                                                     break;  
                                                 case(3):  
                                                     $(this).html(item.imei);  
                                                     break;  
                                                 case(4):  
                                                     $(this).html(item.osid);  
                                                     break;  
                                
                                           }//end switch                          
                            });//end children.each  
                          
                           //把克隆好的tr追加原来的tr后面  
                           clonedTr.insertAfter(tr);  
                        });//end $each  
                        $("#cloneTr").hide();//隐藏id=clone的tr，因为该tr中的td没有数据，不隐藏起来会在生成的table第一行显示一个空行  
                        $("#generatedTable").show();  
         }//end success  
    });//end ajaxpost      