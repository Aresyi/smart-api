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


    <div class="page-inner" id="page-new-project" data-page-name="创建新项目" >
        <h3>创建新项目</h3>
        <form class="form form-invite" action="item/addItem" method="post"  novalidate>
            <div class="form-item">
                <div class="form-field">
                    <input type="text" name="project_name" id="project-name" placeholder="项目名称" 
                        autofocus data-validate="required;length:1,255" data-validate-msg="请填写项目名称;项目名称最长255个字符" />
                    <c:if test="${not empty message }">
            			<font color='red'>${message }</font>
            		</c:if>    
                </div>
            </div>

            <div class="form-item">
                <div class="form-field">
                    <textarea name="project_desc" id="project-desc" placeholder="简单描述项目，便于其他人理解（选填）" data-validate="required;length:1,1000" data-validate-msg="项目描述最长1000个字符"></textarea>
                </div>
            </div>
            
            <div class="form-buttons">
                <button type="submit" class="btn" id="btn-create-project"
                    data-disable-with="正在创建..." data-success-text="创建成功">创建项目</button>
            </div>
        </form>


		<table border="1" cellpadding="0" cellspacing="0" width="100%"
			bordercolor="gray">
			<tr align="center" height="30px" bgcolor="#F8F8FF">
				<th width="5%">
					编号
				</th>
				<th width="10%">
					项目名称
				</th>
				<th>
					项目描述
				</th>
				<th width="20%">
					录入时间
				</th>
				<th width="10%">
					录入人
				</th>
				<th width="10%">
					操作
				</th>
			</tr>
			<c:forEach var="obj" items="${allItem}" varStatus="v">
				<tr id="${v.count }" align="center" style="top: 3; bottom: 3;background-color:${v.count%2==0?'#FFFFFF':'#F8F8F8'}" onmouseover="this.style.backgroundColor='#00FF00';" onmouseout="if(this.id % 2==0){this.style.backgroundColor='#EFEFEF';}else{this.style.backgroundColor='#F8F8F8';}">
					<td>
						${v.count }
					</td>
					<td>
						${obj.name }
					</td>
					<td>
						${obj.des }
					</td>
					<td>
						${tranb:longToDateString(obj.createTime) }
					</td>
					<td>
						${obj.creater }
					</td>
					<td>
						<a href='/smart-api/item/${obj.id }/createModule/'>添加模块</a>
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
