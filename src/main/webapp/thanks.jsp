<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib prefix="tranb" uri="/WEB-INF/tld/tranb-ocr.tld"%>

<%@ include file="/inc-common.jsp" %>

<body>
    <div id="page-loading-mask"></div>

    <div class="wrapper">
        

	<%@ include file="/inc-menu-head.jsp" %>


<div class="container workspace">

    <div class="page">


    <div class="page-inner" id="page-new-project" data-page-name="捐赠"  nav="present">
            <div class="form-item" style="margin-top: 20px;margin-bottom: 20px;text-align: center;">
            	<table style='margin: 0 auto;'>
            		<tr>
            			<td>
            				 <img id="wx_qr_code" alt="微信支付" height="198px" width="198px"  src="/smart-api/assets/pay/wx8.88.jpg">
            				 <br/>
                 			 <span>微信捐赠</span>
            			</td>
            			<td>
            			
            			<select id='changeQr' name='changeQr' onchange="changeQr();">
            				<option value='1.47'>一四七</option>
            				<option value='2.58'>贰伍捌</option>
            				<option value='3.69'>三六九</option>
            				<option value='5.20'>我爱零</option>
            				<option value='6.66'>六六六</option>
            				<option value='7.45'>气死我</option>
            				<option value='8.88' selected="selected">发发发</option>
            				<option value='9.99'>九九九</option>
            				<option value='13.14'>一生一世</option>
            				<option value='99.99'>我是土豪</option>
            				<option value='199'>我是土豪他爹</option>
            			</select>
            			
            			<br/><br/>
            			
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<span id='moneyText' style='font-size: 20px;font-family: sans-serif;font-style:italic;'>发发发</span>
            			（<font color='blue'><span id='moneyShow'>8.88</span></font>）
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			
            			</td>
            			<td>
            				<img id="ali_qr_code" alt="支付宝支付" height="198px" width="198px" src="/smart-api/assets/pay/ali8.88.jpg">
            				<br/>
                 			<span>支付宝捐赠</span>
            			</td>
            		</tr>
            	</table>
            </div>

		<table border="1" cellpadding="0" cellspacing="0" width="100%"	bordercolor="gray">
			
			<c:forEach var="obj" items="${pagerecords.records}" varStatus="v">
				<tr id="${v.count }" align="center" style="top: 3; bottom: 3;background-color:${v.count%2==0?'#FFFFFF':'#F8F8F8'}" onmouseover="this.style.backgroundColor='#00FF00';" onmouseout="if(this.id % 2==0){this.style.backgroundColor='#EFEFEF';}else{this.style.backgroundColor='#F8F8F8';}">
					<td width="5%">
						<font color='blue'>￥${obj.money }</font>
					</td>
					<td width="5%">
						${obj.userName }
					</td>
					<td width="5%">
						${obj.payType == 1 ? '支付宝捐赠' : '微信捐赠' }
					</td>
					<td width="5%">
						${tranb:longToDateString(obj.createTime) }
					</td>
				</tr>
			</c:forEach>
		</table>
		<c:url var="paginationUrlPattern" value="/present/thanks">
				<c:param name="page" value="_page_" />
				</c:url>
				<tranb:pagination pagination="${pagerecords}" urlPattern="${paginationUrlPattern}" />
    </div>

    </div>
</div>
	<%@ include file="/inc-content-foot.jsp" %>


    </div>

	<script type="text/javascript">
		function changeQr() {
			var csi = $("#changeQr").val();
			var text= $("#changeQr option:selected").text();
			$("#wx_qr_code").attr("src", "/smart-api/assets/pay/wx" + csi + ".jpg");
			$("#ali_qr_code").attr("src", "/smart-api/assets/pay/ali" + csi + ".jpg");

			$("#moneyText").text(text);
			$("#moneyShow").text(csi);

			// 改用微信支付来做
			//$("#wx_qr_code").attr("src", "/weixin/pay/genQrCode?cash=" + csi + "00&v=" +Math.random());
		}
	</script>


   <%@ include file="/inc-foot.jsp" %>


    
</body>
</html>
