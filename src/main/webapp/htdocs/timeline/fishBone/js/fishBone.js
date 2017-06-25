$.fn.fishBone = function(data) {
    var colors = ['#F89782', '#1A84CE', '#F7A259', '#43A6DA', '#F9BF3B','#88C7CC','#EF6D5F','#60A96E','#F03852','#3A9284'];

    /**入口*/
    //1.创建dom
    $(this).children().remove();
    $(this).append(creataFishBone(data));
    //2.自适应
    var rowcount = fixWindow();
    //3.翻页滚动效果
    jQuery(".fishBone").slide({
        titCell: ".hd ul",
        mainCell: ".bd>ul",
        autoPage: true,
        effect: "left",
        autoPlay: false,
        scroll: rowcount,
        vis: rowcount
    });
    
    
    /**自适应 平均分布*/
    function fixWindow() {
        //item所占的宽度 = 自身宽度+marginleft
        var item = $(".fishBone .bd .item");
        var marginleft = parseInt(item.css('margin-left'))
        var item_w = item.width() + marginleft;

        //显示区域
        var bd_w = $(".fishBone .bd").width();
        //能显示的个数 取整
        var rowcount = parseInt(bd_w / item_w);
        if (rowcount > item.size()) {
            //rowcount = item.size();
            $(".fishBone .prev,.fishBone .next").hide()
        }
        //设置新的宽度使其平均分布
        var item_w_temp = bd_w / rowcount - marginleft;
        item.width(item_w_temp);
        return rowcount;
    };
    /**li左边框线颜色 border-left-color 动态获取*/
    function getColor(i) {
        var length = colors.length;
        var color = 'gray';
        if (i <= length - 1) {
            color = colors[i];
        } else {
            color = colors[i % length];
        }
        return color;
    };
    /**轴线上圆点位置纵坐标，见图片line-point.png*/
    function getLinePointY(i) {
        var length = colors.length;
        var y = 0;
        if (i <= length - 1) {
            y = -i * 20;
        } else {
            y = -(i % length) * 20;
        }
        return y + "px";
    };
    /**第一行日期圆点位置纵坐标，图片line-first.png*/
    function getLineFirstY(i) {
        var length = colors.length;
        var y = 0;
        if (i <= length - 1) {
            y = -i * 60;
        } else {
            y = -(i % length) * 60;
        }
        return y + "px";
    };
    /** .title-left背景纵坐标，0px开始，见图片title.png*/
    function getTitleLeftY(i) {
        var length = colors.length;
        var y = 0;//图片位置
        if (i <= length - 1) {
            y += -i * 60;
        } else {
            y += -(i % length) * 60;
        }
        return y + "px";
    };
    /** .title-center背景纵坐标，600px开始，见图片title.png*/
    function getTitleCenterY(i) {
        var length = colors.length;
        var y = -598;//图片位置
        if (i <= length - 1) {
            y += -i * 60;
        } else {
            y += -(i % length) * 60;
        }
        return y + "px";
    };
    /**.title-right背景纵坐标，1200px开始，见图片title.png*/
    function getTitleRightY(i) {
        var length = colors.length;
        var y = -1200;//图片位置
        if (i <= length - 1) {
            y += -i * 60;
        } else {
            y += -(i % length) * 60;
        }
        return y + "px";
    };
    /**创建dom结构*/
    function creataFishBone(data) {
        var fishBone = $("<div class='fishBone'/>");
        var wrapper = $("<div class='wrapper'></div>");
        var bd = $("<div class='bd'></div>");
        var ul_item = $("<ul/>");
        //遍历数据
        $(data).each(function(index) {
            var itemclass=itemClass(index);//显示在轴上方或下方标识 top/bottom

            var color = getColor(index);
            var lineFirstY = getLineFirstY(index);
            
            var titleLeftY = getTitleLeftY(index);
            var titleCenterY = getTitleCenterY(index);
            var titleRightY = getTitleRightY(index);
            
            var ul = $("<ul></ul>");
            //遍历封装属性
            //封装审理时间和案号
            if(itemclass=='top'){
                $.each(this, function(name, value) {
                    if (name == '日期') {
                        var li = $("<li class='line-first'>" + value + "</li>")
                                    .css('background-position-y', (parseInt(lineFirstY)+9)+"px");//9是原计算结果的偏移量，显示位置正合适
                        li.appendTo(ul);
                        return;
                    }
                });
                $.each(this, function(name, value) {
                    if (name == '目标') {
                        var li = $("<li class='title'></li>");
                        var titleLeft =  $("<span class='title-left'>&nbsp;</span>").css('background-position-y',titleLeftY);
                        var titleCenter =  $("<span class='title-center'>"+value+"</span>").css('background-position-y',titleCenterY);
                        var titleRight =  $("<span class='title-right'>&nbsp;</span>").css('background-position-y',titleRightY);
                        li.append(titleLeft).append(titleCenter).append(titleRight);
                        li.appendTo(ul);
                        return;
                    }
                });
            }
            //封装其他属性
            $.each(this, function(name, value) {
                if (name != '目标' && name != '日期') {
                    var li = $("<li>" + name + "：" + value + "</li>").css("border-left","1px solid "+color);
                    li.appendTo(ul);
                }
            });
          //封装审理时间和案号
            if(itemclass=="bottom"){
                $.each(this, function(name, value) {
                    if (name == '目标') {
                        var li = $("<li class='title'></li>");
                        var titleLeft =  $("<span class='title-left'>&nbsp;</span>").css('background-position-y',titleLeftY);
                        var titleCenter =  $("<span class='title-center'>"+value+"</span>").css('background-position-y',titleCenterY);
                        var titleRight =  $("<span class='title-right'>&nbsp;</span>").css('background-position-y',titleRightY);
                        li.append(titleLeft).append(titleCenter).append(titleRight);
                        li.appendTo(ul);
                        return;
                    }
                });
                $.each(this, function(name, value) {
                    if (name == '日期') {
                        var li = $("<li class='line-first'>" + value + "</li>")
                        .css('background-position-y', (parseInt(lineFirstY)-33)+"px");//30是原计算结果的偏移量
                        li.appendTo(ul);
                        return;
                    }
                });
            }
            //封装轴线上的圆点
            var linePointY = getLinePointY(index);
            var point = $("<li class='line-last line-point'></li>").css('background-position', '0px ' + linePointY);
            point.appendTo(ul);
            
            //生成一个item（一个完整的案件）
            var li_item = $("<li class='item'></li>");
            var content = $("<div class='content'></div>");
            ul.appendTo(content);
            content.appendTo(li_item);
            li_item.addClass(itemClass(index)).appendTo(ul_item);
        });
        ul_item.appendTo(bd);
        bd.appendTo(wrapper);

        var prev = $("<a class='prev' onclick='toChangeDB();'></a>");
        var next = $("<a class='next'></a>");
        var line = $("<div class='line'/>")

        fishBone.append(wrapper).append(prev).append(next).append(line);
        return fishBone;
    };
    /**item添加样式，显示在上方或下方*/
    function itemClass(index) {
        index += 1;
        if (index % 2 == 0) {
            //偶数显示到下方
            return "bottom";
        } else {
            //奇数显示到上方
            return "top";
        }
    }
    
	function toChangeDB(){
		alert("ssssssss");
		var db = obj.value;
		var itemId = document.getElementById("itemId").value;
		
		if(db != ""){
			$.ajax({
			    url: '/smart-api/data/ajaxChangeDB22',
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
}