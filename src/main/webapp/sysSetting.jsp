<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>
<script src="/smart-api/htdocs/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
function addRow2(name,value){
		     var root = document.getElementById("db_table")
		     var newRow = root.insertRow();
		     var newCell0 = newRow.insertCell();
		     var newCell1 = newRow.insertCell();
		     var newCell2 = newRow.insertCell();
		     var newCell3 = newRow.insertCell();
		     var newCell4 = newRow.insertCell();
		     var newCell5 = newRow.insertCell();
		     var newCell6 = newRow.insertCell();
		     
		     
		     newCell0.innerHTML = "<input type='text' style='width:150px;' placeholder='数据库中文名' autofocus='' data-validate='required;length:1,255' data-validate-msg='请填写数据库中文名;数据库中文名最长255个字符' name='dbChineseName' id='dbChineseName' autocomplete='off' value='' />";
		     newCell1.innerHTML = "<input type='text' style='width:140px;' placeholder='主机IP' autofocus='' data-validate='required;length:1,15' data-validate-msg='请填写主机IP;主机IP最长15个字符' name='host' id='host' autocomplete='off' value='' />";
		     newCell2.innerHTML = "<input type='text' style='width:80px;'  placeholder='端口' autofocus='' data-validate='required;length:1,10' data-validate-msg='请填写数据库端口;数据库端口最长10个数字' name='port' id='port' autocomplete='off' value='' />";
		     newCell3.innerHTML = "<input type='text' style='width:140px;' placeholder='数据库' autofocus='' data-validate='required;length:1,50' data-validate-msg='请填写数据库;数据库最长50个字符' name='dbName' id='dbName' autocomplete='off' value='' />";
		     newCell4.innerHTML = "<input type='text' style='width:140px;' placeholder='用户名' autofocus='' data-validate='required;length:1,50' data-validate-msg='请填写用户名;用户名最长50个字符' name='testName' id='testName' autocomplete='off' value='' />";
		     newCell5.innerHTML = "<input type='text' style='width:135px;' placeholder='密码' autofocus='' data-validate='required;length:1,50' data-validate-msg='请填写密码;密码最长50个字符' name='testPassword' id='testPassword' autocomplete='off' value='' />";
		     newCell6.innerHTML = "<a href='javaScript:void(0);' onclick='removeRow(this.parentNode.parentNode);'>删除</a>";
	
		  }

		function addRow3(name,value){
		     var root = document.getElementById("test_table")
		     var newRow = root.insertRow();
		     var newCell0 = newRow.insertCell();
		     var newCell1 = newRow.insertCell();
		     var newCell2 = newRow.insertCell();
		     
		     newCell0.innerHTML = "<input type='text' style='width:150px;' placeholder='服务名称,如：谷歌测试服务器' autofocus='' name='hostName' id='dbChineseName' autocomplete='off' value='' data-validate='required;length:1,255' data-validate-msg='请填写服务名称;服务名称最长255个字符'/>";
		     newCell1.innerHTML = "<input type='text' style='width:650px;' placeholder='服务地址,如:http://www.google.com/' autofocus='' name='hostUrl' id='host' autocomplete='off' value='' data-validate='required;length:1,255' data-validate-msg='请填写服务地址;服务地址最长255个字符'/>";
		     newCell2.innerHTML = "<a href='javaScript:void(0);' onclick='removeRow(this.parentNode.parentNode);'>删除</a>";
	
		  }

		function addRowVersion(name,value){
			var root = document.getElementById("version_table")
			var newRow = root.insertRow();
			var newCell0 = newRow.insertCell();
			var newCell1 = newRow.insertCell();
			var newCell2 = newRow.insertCell();

			newCell0.innerHTML = "<input type='text' style='width:150px;' placeholder='版本号' autofocus='' name='version' id='version' autocomplete='off' value='' data-validate='required;length:1,255' data-validate-msg='请填写版本;最长10个字符'/>";
			newCell2.innerHTML = "<a href='javaScript:void(0);' onclick='removeRow(this.parentNode.parentNode);'>删除</a>";

		}

	
		
		function removeRow(r) {
			var root = r.parentNode;
		    var allRows = root.getElementsByTagName('tr')
		    if(allRows.length>1){
		        root.removeChild(r);
		    }
		}

		function changeItem(selectElement) {
			var selectedItemId = $(selectElement).val();
			$.get("sys/settingsByItemId?itemId="+selectedItemId,function (html) {
                var $form = $("#formFirst");
                $(".form-item.sub").remove();
                $form.after(html);
            },"text");
        }

        function changeFloatInput(e) {
		    var str = $(e).val();
		    if(str == ''){
                alert("请输入浮点数");
                return;
			}
            var b = /^\d+(\.\d+)?$/.test(str);
            if(!b){
                $(e).val("");
                alert("请输入浮点数");
			}

        }

        $(function () {
            $("#itemId option:first").prop("selected", 'selected');
            $('#itemId').trigger('change');
            <%--$.get("sys/settingsByItemId?itemId=${itemId}",function (html) {--%>
                <%--var $form = $("#formFirst");--%>
                <%--$(".form-item.sub").remove();--%>
                <%--$form.after(html);--%>
            <%--});--%>
        });

</script>

<%@ include file="/inc-common.jsp" %>


<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        

	<%@ include file="/inc-menu-head.jsp" %>


<div class="container workspace">
    <div class="page">
	
	    <div class="page-inner page-member-settings" id="page-sys-settings" data-page-name="配置中心">
            <p class="page-tip moveout" style="display:none;"></p>
            <h3>配置中心</h3>

			<!-- <B>1.数据源</B> -->
            <form class="form settings-form" action="sys/saveSettings" method="post" >
	                <div id="formFirst" class="form-item">
	                    <div class="form-label">
	                        <label for="txt-email">产&nbsp;&nbsp;&nbsp;品</label>
	                    </div>
	                    <div class="form-field">
	                        <select id="itemId" name="itemId" autofocus data-validate="required;" onchange="changeItem(this)">
								<c:forEach var="obj" items="${allItem }">
									<option value="${obj.id }" ${obj.id == user.itemId ? "selected" : "" }>${obj.name }</option>
								</c:forEach>
							</select>
	                    </div>
	                </div>

					<!--sysSettingItem.jsp -->

	                <div class="form-buttons">
	                    <button class="btn" id="btn-save" data-disable-with="正在保存..."
	                        data-success-text="保存成功">保存</button>
	                </div>
            </form>

			
    </div>
</div>
	<%@ include file="/inc-content-foot.jsp" %>


    </div>

   <%@ include file="/inc-foot.jsp" %>

    
</body>
</html>
