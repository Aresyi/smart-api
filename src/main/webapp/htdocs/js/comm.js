function format(txt,compress/*是否为压缩模式*/){/* 格式化JSON源码(对象转换为JSON文本) */  
		//alert("sxxxxxxxxx");
        var indentChar = '    ';   
        if(/^\s*$/.test(txt)){   
            //alert('数据为空,无法格式化! 请自行填写此接口返回的结果示例');   
            return 0;   
        }   
        try{var data=eval('('+txt+')');}   
        catch(e){   
            //alert('请自行填写此接口返回的结果示例.数据源语法错误,格式化失败! 错误信息: '+e.description,'err');   
            return 1;   
        };   
        var draw=[],last=false,This=this,line=compress?'':'\n',nodeCount=0,maxDepth=0;   
           
        var notify=function(name,value,isLast,indent/*缩进*/,formObj){   
            nodeCount++;/*节点计数*/  
            for (var i=0,tab='';i<indent;i++ )tab+=indentChar;/* 缩进HTML */  
            tab=compress?'':tab;/*压缩模式忽略缩进*/  
            maxDepth=++indent;/*缩进递增并记录*/  
            if(value&&value.constructor==Array){/*处理数组*/  
                draw.push(tab+(formObj?('"'+name+'":'):'')+'['+line);/*缩进'[' 然后换行*/  
                for (var i=0;i<value.length;i++)   
                    notify(i,value[i],i==value.length-1,indent,false);   
                draw.push(tab+']'+(isLast?line:(','+line)));/*缩进']'换行,若非尾元素则添加逗号*/  
            }else   if(value&&typeof value=='object'){/*处理对象*/  
                    draw.push(tab+(formObj?('"'+name+'":'):'')+'{'+line);/*缩进'{' 然后换行*/  
                    var len=0,i=0;   
                    for(var key in value)len++;   
                    for(var key in value)notify(key,value[key],++i==len,indent,true);   
                    draw.push(tab+'}'+(isLast?line:(','+line)));/*缩进'}'换行,若非尾元素则添加逗号*/  
                }else{   
                        if(typeof value=='string')value='"'+value+'"';   
                        draw.push(tab+(formObj?('"'+name+'":'):'')+value+(isLast?'':',')+line);   
                };   
        };   
        var isLast=true,indent=0;   
        notify('',data,isLast,indent,false);   
        return draw.join('');   
    }
	
	
	var JsonUti = {  
		    //定义换行符  
		    n: "\n",  
		    //定义制表符  
		    t: "\t",  
		    //转换String  
		    convertToString: function(obj) {  
		    	//alert("sssssssssssssssssssssssssssss");
		        return JsonUti.__writeObj(obj, 1);  
		    },  
		    //写对象  
		    __writeObj: function(obj //对象  
		    , level //层次（基数为1）  
		    , isInArray) { //此对象是否在一个集合内  
		        //如果为空，直接输出null  
		        if (obj == null) {  
		            return "null";  
		        }  
		        //为普通类型，直接输出值  
		        if (obj.constructor == Number || obj.constructor == Date || obj.constructor == String || obj.constructor == Boolean) {  
		            var v = obj.toString();  
		            var tab = isInArray ? JsonUti.__repeatStr(JsonUti.t, level - 1) : "";  
		            if (obj.constructor == String || obj.constructor == Date) {  
		                //时间格式化只是单纯输出字符串，而不是Date对象  
		                return tab + ("\"" + v + "\"");  
		            }  
		            else if (obj.constructor == Boolean) {  
		                return tab + v.toLowerCase();  
		            }  
		            else {  
		                return tab + (v);  
		            }  
		        }  
		        //写Json对象，缓存字符串  
		        var currentObjStrings = [];  
		        //遍历属性  
		        for (var name in obj) {  
		            var temp = [];  
		            //格式化Tab  
		            var paddingTab = JsonUti.__repeatStr(JsonUti.t, level);  
		            temp.push(paddingTab);  
		            //写出属性名  
		            temp.push("\"" + name + "\" : ");  
		            var val = obj[name];  
		            if (val == null) {  
		                temp.push("null");  
		            }  
		            else {  
		                var c = val.constructor;  
		                if (c == Array) { //如果为集合，循环内部对象  
		                    temp.push(JsonUti.n + paddingTab + "[" + JsonUti.n);  
		                    var levelUp = level + 2; //层级+2  
		                    var tempArrValue = []; //集合元素相关字符串缓存片段  
		                    for (var i = 0; i < val.length; i++) {  
		                        //递归写对象  
		                        tempArrValue.push(JsonUti.__writeObj(val[i], levelUp, true));  
		                    }  
		                    temp.push(tempArrValue.join("," + JsonUti.n));  
		                    temp.push(JsonUti.n + paddingTab + "]");  
		                }  
		                else if (c == Function) {  
		                    temp.push("[Function]");  
		                }  
		                else {  
		                    //递归写对象  
		                    temp.push(JsonUti.__writeObj(val, level + 1));  
		                }  
		            }  
		            //加入当前对象“属性”字符串  
		            currentObjStrings.push(temp.join(""));  
		        }  
		        return (level > 1 && !isInArray ? JsonUti.n: "") //如果Json对象是内部，就要换行格式化  
		        + JsonUti.__repeatStr(JsonUti.t, level - 1) + "{" + JsonUti.n //加层次Tab格式化  
		        + currentObjStrings.join("," + JsonUti.n) //串联所有属性值  
		        + JsonUti.n + JsonUti.__repeatStr(JsonUti.t, level - 1) + "}"; //封闭对象  
		    },  
		    __isArray: function(obj) {  
		        if (obj) {  
		            return obj.constructor == Array;  
		        }  
		        return false;  
		    },  
		    __repeatStr: function(str, times) {  
		        var newStr = [];  
		        if (times > 0) {  
		            for (var i = 0; i < times; i++) {  
		                newStr.push(str);  
		            }  
		        }  
		        return newStr.join("");  
		    }  
		}; 
	
	
	
	
	
	
	/********************************************************************
	**
	**比较通用的正则表达式，捕获url各个部分。
	**注意各部分基本上都包含了相应的符号，例如端口号如果捕获成功，那就是':80'
	**函数返回一个正则表达式捕获数组。
	**注意，现在获得的是一个数组，所以需要通过arr[i]的方式引用。
	**正则表达式所有的匹配说明::.........
	**$0
	**整个url本身。如果$0==null，那就是我的正则有意外，未捕获的可能。
	**有一种未捕获的情况已经被发现，那就是域名后面没有以'/'结尾，如：'http://localhost'
	**但是经过我的测试，IE和firefox会自动把域名后面加上'/'的。
	**$1-$4  协议，域名，端口号，还有最重要的路径path！
	**$5-$7  文件名，锚点(#top)，query参数(?id=55)
	**
	*********************************************************************/ 
	function urlRegEx(url)
	 { 
	  //如果加上/g参数，那么只返回$0匹配。也就是说arr.length = 0
	//  var re = new RegExp("/(w+)://([^:|/]+)(:d*)?(.*/)([^#|?|]+)?(#.*)?(?.*)?$/") ;
	  var re = new RegExp(/(?:http|ftp|rtsp):\/\/[^\/]+\/([^\.]*)\/([^\/]+\.[^\/\.]+)$/);  
	  //re.exec(url);
	  var arr = url.match(re);
	  return arr;
	 
	 }
	 //--------示例代码：
	 function getPath()
	 {
	  return urlRegEx(location.href)[4]; 
	 }