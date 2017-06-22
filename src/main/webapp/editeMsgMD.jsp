<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">window.NREUM||(NREUM={});NREUM.info={"beacon":"bam.nr-data.net","errorBeacon":"bam.nr-data.net","licenseKey":"e644b8e723","applicationID":"17322787","transactionName":"el8PEkJZWg4BR04CX1pDTghVQQ==","queueTime":0,"applicationTime":29,"agent":""}</script>
<script type="text/javascript">window.NREUM||(NREUM={}),__nr_require=function(t,e,n){function r(n){if(!e[n]){var o=e[n]={exports:{}};t[n][0].call(o.exports,function(e){var o=t[n][1][e];return r(o||e)},o,o.exports)}return e[n].exports}if("function"==typeof __nr_require)return __nr_require;for(var o=0;o<n.length;o++)r(n[o]);return r}({1:[function(t,e,n){function r(){}function o(t,e,n){return function(){return i(t,[(new Date).getTime()].concat(u(arguments)),e?null:this,n),e?void 0:this}}var i=t("handle"),a=t(2),u=t(3),c=t("ee").get("tracer"),f=NREUM;"undefined"==typeof window.newrelic&&(newrelic=f);var s=["setPageViewName","setCustomAttribute","finished","addToTrace","inlineHit"],p="api-",l=p+"ixn-";a(s,function(t,e){f[e]=o(p+e,!0,"api")}),f.addPageAction=o(p+"addPageAction",!0),e.exports=newrelic,f.interaction=function(){return(new r).get()};var d=r.prototype={createTracer:function(t,e){var n={},r=this,o="function"==typeof e;return i(l+"tracer",[Date.now(),t,n],r),function(){if(c.emit((o?"":"no-")+"fn-start",[Date.now(),r,o],n),o)try{return e.apply(this,arguments)}finally{c.emit("fn-end",[Date.now()],n)}}}};a("setName,setAttribute,save,ignore,onEnd,getContext,end,get".split(","),function(t,e){d[e]=o(l+e)}),newrelic.noticeError=function(t){"string"==typeof t&&(t=new Error(t)),i("err",[t,(new Date).getTime()])}},{}],2:[function(t,e,n){function r(t,e){var n=[],r="",i=0;for(r in t)o.call(t,r)&&(n[i]=e(r,t[r]),i+=1);return n}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],3:[function(t,e,n){function r(t,e,n){e||(e=0),"undefined"==typeof n&&(n=t?t.length:0);for(var r=-1,o=n-e||0,i=Array(o<0?0:o);++r<o;)i[r]=t[e+r];return i}e.exports=r},{}],ee:[function(t,e,n){function r(){}function o(t){function e(t){return t&&t instanceof r?t:t?u(t,a,i):i()}function n(n,r,o){t&&t(n,r,o);for(var i=e(o),a=l(n),u=a.length,c=0;c<u;c++)a[c].apply(i,r);var s=f[m[n]];return s&&s.push([w,n,r,i]),i}function p(t,e){g[t]=l(t).concat(e)}function l(t){return g[t]||[]}function d(t){return s[t]=s[t]||o(n)}function v(t,e){c(t,function(t,n){e=e||"feature",m[n]=e,e in f||(f[e]=[])})}var g={},m={},w={on:p,emit:n,get:d,listeners:l,context:e,buffer:v};return w}function i(){return new r}var a="nr@context",u=t("gos"),c=t(2),f={},s={},p=e.exports=o();p.backlog=f},{}],gos:[function(t,e,n){function r(t,e,n){if(o.call(t,e))return t[e];var r=n();if(Object.defineProperty&&Object.keys)try{return Object.defineProperty(t,e,{value:r,writable:!0,enumerable:!1}),r}catch(i){}return t[e]=r,r}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],handle:[function(t,e,n){function r(t,e,n,r){o.buffer([t],r),o.emit(t,e,n)}var o=t("ee").get("handle");e.exports=r,r.ee=o},{}],id:[function(t,e,n){function r(t){var e=typeof t;return!t||"object"!==e&&"function"!==e?-1:t===window?0:a(t,i,function(){return o++})}var o=1,i="nr@id",a=t("gos");e.exports=r},{}],loader:[function(t,e,n){function r(){if(!h++){var t=y.info=NREUM.info,e=s.getElementsByTagName("script")[0];if(t&&t.licenseKey&&t.applicationID&&e){c(m,function(e,n){t[e]||(t[e]=n)});var n="https"===g.split(":")[0]||t.sslForHttp;y.proto=n?"https://":"http://",u("mark",["onload",a()],null,"api");var r=s.createElement("script");r.src=y.proto+t.agent,e.parentNode.insertBefore(r,e)}}}function o(){"complete"===s.readyState&&i()}function i(){u("mark",["domContent",a()],null,"api")}function a(){return(new Date).getTime()}var u=t("handle"),c=t(2),f=window,s=f.document,p="addEventListener",l="attachEvent",d=f.XMLHttpRequest,v=d&&d.prototype;NREUM.o={ST:setTimeout,CT:clearTimeout,XHR:d,REQ:f.Request,EV:f.Event,PR:f.Promise,MO:f.MutationObserver},t(1);var g=""+location,m={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",agent:"js-agent.newrelic.com/nr-963.min.js"},w=d&&v&&v[p]&&!/CriOS/.test(navigator.userAgent),y=e.exports={offset:a(),origin:g,features:{},xhrWrappable:w};s[p]?(s[p]("DOMContentLoaded",i,!1),f[p]("load",r,!1)):(s[l]("onreadystatechange",o),f[l]("onload",r)),u("mark",["firstbyte",a()],null,"api");var h=0},{}]},{},["loader"]);</script>
<title>编辑文档 - 17Smart_API</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script type="text/javascript">window.NREUM||(NREUM={});NREUM.info={"beacon":"bam.nr-data.net","errorBeacon":"bam.nr-data.net","licenseKey":"e644b8e723","applicationID":"17322787","transactionName":"el8PEkJZWg4BR04CX1pDTghVQQ==","queueTime":0,"applicationTime":29,"agent":""}</script>
<script type="text/javascript">window.NREUM||(NREUM={}),__nr_require=function(t,e,n){function r(n){if(!e[n]){var o=e[n]={exports:{}};t[n][0].call(o.exports,function(e){var o=t[n][1][e];return r(o||e)},o,o.exports)}return e[n].exports}if("function"==typeof __nr_require)return __nr_require;for(var o=0;o<n.length;o++)r(n[o]);return r}({1:[function(t,e,n){function r(){}function o(t,e,n){return function(){return i(t,[(new Date).getTime()].concat(u(arguments)),e?null:this,n),e?void 0:this}}var i=t("handle"),a=t(2),u=t(3),c=t("ee").get("tracer"),f=NREUM;"undefined"==typeof window.newrelic&&(newrelic=f);var s=["setPageViewName","setCustomAttribute","finished","addToTrace","inlineHit"],p="api-",l=p+"ixn-";a(s,function(t,e){f[e]=o(p+e,!0,"api")}),f.addPageAction=o(p+"addPageAction",!0),e.exports=newrelic,f.interaction=function(){return(new r).get()};var d=r.prototype={createTracer:function(t,e){var n={},r=this,o="function"==typeof e;return i(l+"tracer",[Date.now(),t,n],r),function(){if(c.emit((o?"":"no-")+"fn-start",[Date.now(),r,o],n),o)try{return e.apply(this,arguments)}finally{c.emit("fn-end",[Date.now()],n)}}}};a("setName,setAttribute,save,ignore,onEnd,getContext,end,get".split(","),function(t,e){d[e]=o(l+e)}),newrelic.noticeError=function(t){"string"==typeof t&&(t=new Error(t)),i("err",[t,(new Date).getTime()])}},{}],2:[function(t,e,n){function r(t,e){var n=[],r="",i=0;for(r in t)o.call(t,r)&&(n[i]=e(r,t[r]),i+=1);return n}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],3:[function(t,e,n){function r(t,e,n){e||(e=0),"undefined"==typeof n&&(n=t?t.length:0);for(var r=-1,o=n-e||0,i=Array(o<0?0:o);++r<o;)i[r]=t[e+r];return i}e.exports=r},{}],ee:[function(t,e,n){function r(){}function o(t){function e(t){return t&&t instanceof r?t:t?u(t,a,i):i()}function n(n,r,o){t&&t(n,r,o);for(var i=e(o),a=l(n),u=a.length,c=0;c<u;c++)a[c].apply(i,r);var s=f[m[n]];return s&&s.push([w,n,r,i]),i}function p(t,e){g[t]=l(t).concat(e)}function l(t){return g[t]||[]}function d(t){return s[t]=s[t]||o(n)}function v(t,e){c(t,function(t,n){e=e||"feature",m[n]=e,e in f||(f[e]=[])})}var g={},m={},w={on:p,emit:n,get:d,listeners:l,context:e,buffer:v};return w}function i(){return new r}var a="nr@context",u=t("gos"),c=t(2),f={},s={},p=e.exports=o();p.backlog=f},{}],gos:[function(t,e,n){function r(t,e,n){if(o.call(t,e))return t[e];var r=n();if(Object.defineProperty&&Object.keys)try{return Object.defineProperty(t,e,{value:r,writable:!0,enumerable:!1}),r}catch(i){}return t[e]=r,r}var o=Object.prototype.hasOwnProperty;e.exports=r},{}],handle:[function(t,e,n){function r(t,e,n,r){o.buffer([t],r),o.emit(t,e,n)}var o=t("ee").get("handle");e.exports=r,r.ee=o},{}],id:[function(t,e,n){function r(t){var e=typeof t;return!t||"object"!==e&&"function"!==e?-1:t===window?0:a(t,i,function(){return o++})}var o=1,i="nr@id",a=t("gos");e.exports=r},{}],loader:[function(t,e,n){function r(){if(!h++){var t=y.info=NREUM.info,e=s.getElementsByTagName("script")[0];if(t&&t.licenseKey&&t.applicationID&&e){c(m,function(e,n){t[e]||(t[e]=n)});var n="https"===g.split(":")[0]||t.sslForHttp;y.proto=n?"https://":"http://",u("mark",["onload",a()],null,"api");var r=s.createElement("script");r.src=y.proto+t.agent,e.parentNode.insertBefore(r,e)}}}function o(){"complete"===s.readyState&&i()}function i(){u("mark",["domContent",a()],null,"api")}function a(){return(new Date).getTime()}var u=t("handle"),c=t(2),f=window,s=f.document,p="addEventListener",l="attachEvent",d=f.XMLHttpRequest,v=d&&d.prototype;NREUM.o={ST:setTimeout,CT:clearTimeout,XHR:d,REQ:f.Request,EV:f.Event,PR:f.Promise,MO:f.MutationObserver},t(1);var g=""+location,m={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",agent:"js-agent.newrelic.com/nr-963.min.js"},w=d&&v&&v[p]&&!/CriOS/.test(navigator.userAgent),y=e.exports={offset:a(),origin:g,features:{},xhrWrappable:w};s[p]?(s[p]("DOMContentLoaded",i,!1),f[p]("load",r,!1)):(s[l]("onreadystatechange",o),f[l]("onload",r)),u("mark",["firstbyte",a()],null,"api");var h=0},{}]},{},["loader"]);</script>

<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="icon" href="/smart-api/assets/favicon.ico" sizes="32x32" />
<link rel="icon" href="/smart-api/assets/favicon.ico" sizes="64x64" />
<link rel="icon" href="/smart-api/assets/favicon.ico" sizes="128x128" />
<link rel="apple-touch-icon-precomposed"
	href="/smart-api/assets/logo-sign-dff6b8806ae5cfa80c84502bb5e07c17.png" />

<link
	href="/smart-api/assets/application-39d12b990af1fdb03ea3fd8788854c4d.css"
	media="all" rel="stylesheet" type="text/css" />
<link
	href="/smart-api/assets/doc-editor-9eb6c4f0fcc04e29cb80546f1dea1737.css"
	media="all" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="/smart-api/htdocs/mdeditor/css/editormd.css" />

<script src="/smart-api/htdocs/mdeditor/js/jquery.min.js"></script>
<script src="/smart-api/htdocs/mdeditor/js/editormd.min.js"></script>

<script type="text/javascript">
	function checkForm(obj){
		if(document.getElementById("doc_title").value==""){
			alert("请填写文档标题");
			return false;
		}		
	}
</script>

</head>
<body>


	<div class="wrapper">

		<div class="container" style='width: 90%;'>
			<div class="page">
				<div class="page-inner" id="page-doc-new">
					<div class="doc-wrap">
						<form class="form form-edit-doc" action="/smart-api/message/${message.id}/saveEditeMsg/" method="post" style='width: 100%;' onsubmit="return checkForm(this);">

							<input type="hidden" name="markdown" id="is-markdown" value="1" /> 
							<input type="hidden" name="is_html" id="is_html" value="1" />
							
							<div class="form-item doc-title-wrap">
								<div class="form-field">
									<input id="doc_title" name="doc_title" class="doc-title" placeholder="${message.title }" />
								</div>
							</div>
							<div class="form-item wmd-panel-wrap">
									<div id="my-editormd" >
										<textarea id="my-editormd-markdown-doc" name="my-editormd-markdown-doc" style="display:none;">${message.detail }</textarea>
										<textarea id="my-editormd-html-code" name="my-editormd-html-code" style="display:none;"></textarea>
									</div>
							</div>
							<div class="doc-bottom-bar" style='width: 84%;'>
								<div class="form-item visitor-lock"></div>
								<div class="form-item save-btns-wrap">
									<div class="form-field">
										<button type="submit" class="btn btn-mini btn-create-doc" data-disable-with="保存中..." data-success-text="保存成功">发布</button>
										<button type="button" class="btn btn-x btn-cancel-quit">取消</button>
									</div>
								</div>
							</div>
						</form>

					</div>
				</div>
			</div>
		</div>

	</div>











	<script type="text/javascript">
			var myEditor;

            $(function() {
                myEditor = editormd("my-editormd", {
                    width   : "90%",
                    height  : 800,
                    syncScrolling : "single",
                    path    : "/smart-api/htdocs/mdeditor/lib/",
                    saveHTMLToTextarea : true,
                    
                    emoji: true,//emoji表情，默认关闭
                    taskList: true,
            		tocm: true, // Using [TOCM]
            		tex: true,// 开启科学公式TeX语言支持，默认关闭
            		flowChart: true,// 开启流程图支持，默认关闭
            		sequenceDiagram: true,// 开启时序/序列图支持，默认关闭,
		            //dialogLockScreen : false,   // 设置弹出层对话框不锁屏，全局通用，默认为true
		            //dialogShowMask : false,     // 设置弹出层对话框显示透明遮罩层，全局通用，默认为true
		            //dialogDraggable : false,    // 设置弹出层对话框不可拖动，全局通用，默认为true
		            //dialogMaskOpacity : 0.4,    // 设置透明遮罩层的透明度，全局通用，默认值为0.1
		            //dialogMaskBgColor : "#000", // 设置透明遮罩层的背景颜色，全局通用，默认为#fff
                    codeFold: true,
            		
            		imageUpload : true,
					imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
					imageUploadURL : "/smart-api/upload/editormdPic/",
					
					/*
					 注意：editor.md期望得到一个json格式的上传后的返回值，格式如下：
					{
					    success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
					    message : "提示的信息，上传成功或上传失败及错误信息等。",
					    url     : "图片地址"        // 上传成功时才返回
					}
					*/
					
					/*上传图片成功后可以做一些自己的处理*/
					onload: function () {
		                //console.log('onload', this);
		                //this.fullscreen();
		                //this.unwatch();
		                //this.watch().fullscreen();
		                //this.width("100%");
		                //this.height(480);
		                //this.resize("100%", 640);
            		},
            		
            		editorTheme: "pastel-on-dark",
                    theme: "gray",
            		previewTheme: "dark"
                });
       
            });
        </script>
</body>
</html>
