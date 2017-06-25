<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>


<link rel="stylesheet" href="/smart-api/htdocs/timeline/fishBone/css/fishBone.css" />
<script type="text/javascript" src="/smart-api/htdocs/timeline/fishBone/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/smart-api/htdocs/timeline/fishBone/js/fishBone.js"></script>
<script type="text/javascript" src="/smart-api/htdocs/timeline/fishBone/js/jquery.SuperSlide.2.1.1.js"></script>

<script type="text/javascript">
		
		function del(obj,id){
			if(window.confirm("确认删除此足迹？")){
				$.ajax({
				    url: '/smart-api/track/delTrack',
				    type: 'GET',
				    data:{id: id},
				    dataType:"json",
				    error: function(){
				        alert('Error loading json data document');
				    },
				    success: function(resData){
			            removeRow(obj.parentNode.parentNode);
				    }
				});
			}
		}
		
		function removeRow(r) {
				var root = r.parentNode;
			    var allRows = root.getElementsByTagName('tr')
			    if(allRows.length>1){
			        root.removeChild(r);
			    }
		}
		
		</script>

<script>
	data = ${data};//[{'审理时间':'2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号(当前案件)'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号(当前案件)'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号(当前案件)'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号(当前案件)'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号(当前案件)'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号(当前案件)'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号'},{'审理时间': '2016-12-20 至 2016-12-20','承办庭室':'XXXX','承办法官':'XXX','承办法院':'XXXXXXX法院','案件状态':'XX','案号':'(XXXX)XXXXXX第XXXX号(当前案件)'}];
	$(function() {
		//创建案件历史
		if(data == "" || data.size < 5){
			data = "暂无足迹，请保留足迹";
			document.getElementById("fishBone").innerHTML=data;
		}else{
			$(".fishBone").fishBone(data);
		}
	});
	
</script>


<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        



 	<%@ include file="/inc-menu-head.jsp" %>



		<div class="container workspace">
		    <div class="page">
		
		        <div class="page-inner" id="page-projects" data-page-name="${empty itemName ? '所有' : itemName }的足迹" nav="track">
		                <div class="tools">
		                    <div class="switch-view">
		                        <a href="javascript:;" class="link-view link-grid-view active" title="网格视图">
		                            <i class="twr twr-grid-view"></i>
		                        </a>
		                        <a href="javascript:;" class="link-view link-list-view" title="列表视图">
		                            <i class="twr twr-list-view"></i>
		                        </a>
		                    </div>
		
		                    <a class="link-create-project new "
		                        href="/smart-api/track/create/" data-stack-root data-nocache>
		                        	新建足迹
		                    </a>
		                </div>
						
						
						<div style=" padding: 10px 10px; ">
						<form action="track/searchTrack" name="form" id="form" method="post">
						<a>搜索足迹</a>
							<select name="belongItemId" >
								<option disabled="disabled">-------------------</option>
								<option value=""></option>
								<c:forEach var="obj" items="${allItem }">
									<option value="${obj.id }" ${obj.id == belongItemId ? 'selected' : ''}>${obj.name }</option>
								</c:forEach>	
							</select>
							&nbsp;&nbsp;
							<input type="text" id="keyword" name="keyword" value="${ keyword}" style='display: inline-block;   padding: 0;  height: 28px;'/>
							&nbsp;&nbsp; 
							<input type="submit" value="查&nbsp&nbsp询" /> <font color="red">${message }</font>
						</form>
					</div>
						<div class="container">
							<div id ="fishBone" class="fishBone" />
						</div>
		        </div>
		
		    </div>
		</div>
	
	
		<%@ include file="/inc-content-foot.jsp" %>

    </div>

    <%@ include file="/inc-foot.jsp" %>

</body>
</html>