
	function toChangeItem(obj){
			var itemId = obj.value;
			
			
			if(itemId != ""){
				$.ajax({
				    url: '/smart-api/data/ajaxChangeItem',
				    type: 'GET',
				    data:{itemId: itemId},
				    dataType:"json",
				    error: function(){
				        alert('Error loading json data document');
				    },
				    success: function(resData){
			            var o = eval(resData);//将json字符串转换成js对象
						
						if(o.length > 0){
				            var sel = document.getElementById("db");
				            sel.options.length=0;
				            sel.options.add(new Option("",""));
							for (var i in o) { //循环json对象数组
								var dbChineseName = o[i].dbChineseName ;
								var dbName = o[i].dbName ;
								sel.options.add(new Option(dbChineseName,dbName));
							}
						}else{
							clear();
						}
				    }
				});
				
			}else{
				clear();
			}
		}

		function clear(){
			 var sel = document.getElementById("db");
		     sel.options.length=0;
		         
		     var sel = document.getElementById("table");
		     sel.options.length=0;
		         
		     document.getElementById("tr_span").innerHTML="";
		}

		function toChangeDB(obj){
			var db = obj.value;
			var itemId = document.getElementById("itemId").value;
			
			if(db != ""){
				$.ajax({
				    url: '/smart-api/data/ajaxChangeDB',
				    type: 'GET',
				    data:{itemId: itemId,db: db},
				    dataType:"json",
				    error: function(){
				        alert('Error loading json data document');
				    },
				    success: function(resData){
			            var o = eval(resData);//将json字符串转换成js对象
			            var sel = document.getElementById("table");
			            sel.options.length=0;
			            sel.options.add(new Option("",""));
						for (var i in o) { //循环json对象数组
							var tableName = o[i].tableName ;
							var tableComment = o[i].tableComment ;
							
							if(typeof(tableName) == "undefined" || tableName === "undefined"){
								continue;
							}
							
							var dis = tableName+" ["+tableComment+"]"
							sel.options.add(new Option(dis,tableName));
						}
				    }
				});
				
			}else{
				 var sel = document.getElementById("table");
		         sel.options.length=0;
			}
		}
		
		
		function toChangeTable(obj){
			var table = obj.value;
			var itemId = document.getElementById("itemId").value;
			var db = document.getElementById("db").value;
			
			if(table != ""){
				$.ajax({
				    url: '/smart-api/data/ajaxChangeTable',
				    type: 'GET',
				    data:{itemId:itemId,db: db,table:table},
				    dataType:"json",
				    error: function(){
				        alert('Error loading json data document');
				    },
				    success: function(resData){
			            var o = eval(resData);//将json字符串转换成js对象
			           
			            var content = "<table cellpadding='1' cellspacing='0' class='Requestparameters' style='width: 100%'>";
			            content +="<tr style='text-align: center; font-weight: bold;'>";
			            content +="	<td width='18%'>名称</td>";
			            content +="	<td width='10%'>类型</td>";
			            content +="	<td width='8%'>长度</td>";
			            content +="	<td width='65%'>描述</td>";
			            content +="</tr>";
						for (var i in o) { //循环json对象数组
							var column = o[i].column ;
							var type = o[i].type ;
							var comment = o[i].comment ;
							var length = o[i].length ;
							if(typeof(length) == "undefined"){
								length = "-";
							}
							
							
							if(typeof(column) == "undefined" || column === "undefined"){
								continue;
							}
							
							content+="<tr>";
							content+="<td>"+column+"</td>";
							content+="<td>"+type+"</td>";
							content+="<td>"+length+"</td>";
							content+="<td>"+comment+"&nbsp;</td>";
							content+="</tr>";
						}
						
						content+="</table>";
						
						document.getElementById("tr_span").innerHTML=content;
				    }
				});
				
			}
		}
