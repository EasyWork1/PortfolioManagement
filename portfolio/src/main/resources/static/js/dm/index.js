window.onload= function(){
	var url=location.search;
	var Request = new Object();
	if(url.indexOf("?")!=-1)
	{
		var str = url.substr(1)　//去掉?号
   　　　strs = str.split("&");
	  　for(var i=0;i<strs.length;i++){  　　　 
	  		Request[strs[i ].split("=")[0]]=unescape(strs[i].split("=")[1]);
	  	}
	  	var userdom = $('#user-box');
        var username= Request["username"];
        userdom.html(username);
　　　}

}