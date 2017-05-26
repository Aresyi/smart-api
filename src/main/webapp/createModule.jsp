<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>

<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        

	<%@ include file="/inc-menu-head.jsp" %>


<div class="container workspace">
 <!--
    <div class="page">
       <a href="/smart-api/index/home/" class="link-page-behind" >所有项目</a>   
    </div>
 -->
    <div class="page">
    
    <script type="text/javascript">
			function checkForm(obj){
				var itemId = document.getElementById("itemId").value;
				if(itemId == null || itemId == ""){
					alert("请选取项目名称");
					return false;
				}
				
				var name = document.getElementById("name").value;
				if(name == null || name == ""){
					alert("请填写模块名称");
					return false;
				}
				
			}
		</script>

    <div class="page-inner" id="page-new-project" data-page-name="创建新项目" >
        <h3>创建新模块</h3>
        <form class="form form-invite2" action="item/addModule" method="post"  novalidate >
        	
        	<div class="form-item">
        		<div class="form-field">
	        		<select id="itemId" name="itemId" autofocus data-validate="required;">
						<option value=""></option>
						<c:forEach var="obj" items="${allItem }">
							<option value="${obj.id }" ${obj.id == itemId ? "selected" : "" }>${obj.name }</option>
						</c:forEach>
					</select>
					<c:if test="${not empty message }">
            			<font color='red'>${message }</font>
            		</c:if>
				</div>
        	</div>
        
            <div class="form-item">
                <div class="form-field">
                    <input type="text" name="name" id="name" placeholder="模块名称" 
                        autofocus data-validate="required;length:1,255" data-validate-msg="请填写模块名称;模块名称最长255个字符" />
                </div>
            </div>

            <div class="form-buttons">
                <button type="submit" class="btn" id="btn-create-project"
                    data-disable-with="正在创建..." data-success-text="创建成功">创建模块</button>
            </div>
        </form>


		<table border="1" cellpadding="0" cellspacing="0" width="100%"
			bordercolor="gray">
			<tr align="center" height="30px" bgcolor="#F8F8FF">
				<th width="5%">
					编号
				</th>
				<th width="15%">
					项目名称
				</th>
				<th width="50%">
					模块名称
				</th>
				<th width="20%">
					录入时间
				</th>
				<th width="10%">
					录入人
				</th>
			</tr>
			<c:forEach var="obj" items="${moduleList}" varStatus="v">
				<tr id="${v.count }" align="center" style="top: 3; bottom: 3;background-color:${v.count%2==0?'#FFFFFF':'#F8F8F8'}" onmouseover="this.style.backgroundColor='#00FF00';" onmouseout="if(this.id % 2==0){this.style.backgroundColor='#EFEFEF';}else{this.style.backgroundColor='#F8F8F8';}">
					<td>
						${v.count }
					</td>
					<td>
						${obj.itemName }
					</td>
					<td>
						${obj.name }
					</td>
					<td>
						${tranb:longToDateString(obj.createTime) }
					</td>
					<td>
						${obj.creater }
					</td>
				</tr>
			</c:forEach>
		</table>
    </div>

    </div>
</div>
	<%@ include file="/inc-content-foot.jsp" %>


    </div>

   <%@ include file="/inc-foot.jsp" %>

    
</body>
</html>
