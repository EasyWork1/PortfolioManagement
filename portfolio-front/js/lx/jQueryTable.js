$(document).ready(function(){  
    var tb = $("#tb_fundmanager");  
      
    //添加  
    $("#add_btn").click(function(){  
        var hideTr = $("#hide_tbody",tb).children().first();  
        var newTr = hideTr.clone().show();  
        $("#show_tbody",tb).append(newTr);  
    });  
      
    //保存  
    $("#save_btn",tb).die('click').live('click',function(){  
            var tr = $(this).parent().parent();  
        $("input[type='text']",tr).each(function(i,el){  
            el = $(el);  
            el.parent().text(el.val());  
            el.remove();  
        });  
        $("#save_btn",tr).hide();  
        $("#edit_btn",tr).show();  
    });  
      
    //修改  
    $("#edit_btn",tb).die('click').live('click',function(){  
        var tr = $(this).parent().parent();  
        $("td:not('#a')",tr).each(function(i,el){  
            el = $(el);  
            var html = "<input value='"+el.text()+"' type='text'>";  
            el.html(html);  
        });  
        $("#edit_btn",tr).hide();  
        $("#save_btn",tr).show();  
    });  
      
    //删除  
    $("#del_btn",tb).die('click').live('click',function(){  
        $(this).parent().parent().remove();  
    });  
      
  
});  