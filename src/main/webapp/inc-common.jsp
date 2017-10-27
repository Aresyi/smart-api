<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page import="com.ydj.smart.common.tools.*"%>
<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="net.sf.json.JSONArray"%>
<%@ page import="java.util.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<script type="text/javascript">window.NREUM||(NREUM={});NREUM.info={"beacon":"bam.nr-data.net","errorBeacon":"bam.nr-data.net","licenseKey":"e644b8e723","applicationID":"17322787","transactionName":"el8PEkJZWg4BR04WQlZaBAVERRkLClEEHg==","queueTime":0,"applicationTime":139,"agent":""}</script>
<script type="text/javascript">window.NREUM||(NREUM={}),__nr_require=function(e,n,t){function r(t){if(!n[t]){var o=n[t]={exports:{}};e[t][0].call(o.exports,function(n){var o=e[t][1][n];return r(o||n)},o,o.exports)}return n[t].exports}if("function"==typeof __nr_require)return __nr_require;for(var o=0;o<t.length;o++)r(t[o]);return r}({1:[function(e,n,t){function r(e,n){return function(){o(e,[(new Date).getTime()].concat(a(arguments)),null,n)}}var o=e("handle"),i=e(2),a=e(3);"undefined"==typeof window.newrelic&&(newrelic=NREUM);var c=["setPageViewName","setCustomAttribute","finished","addToTrace","inlineHit"],u=["addPageAction"],f="api-";i(c,function(e,n){newrelic[n]=r(f+n,"api")}),i(u,function(e,n){newrelic[n]=r(f+n)}),n.exports=newrelic,newrelic.noticeError=function(e){"string"==typeof e&&(e=new Error(e)),o("err",[e,(new Date).getTime()])}},{}],2:[function(e,n,t){function r(e,n){var t=[],r="",i=0;for(r in e)o.call(e,r)&&(t[i]=n(r,e[r]),i+=1);return t}var o=Object.prototype.hasOwnProperty;n.exports=r},{}],3:[function(e,n,t){function r(e,n,t){n||(n=0),"undefined"==typeof t&&(t=e?e.length:0);for(var r=-1,o=t-n||0,i=Array(0>o?0:o);++r<o;)i[r]=e[n+r];return i}n.exports=r},{}],ee:[function(e,n,t){function r(){}function o(e){function n(e){return e&&e instanceof r?e:e?c(e,a,i):i()}function t(t,r,o){e&&e(t,r,o);for(var i=n(o),a=l(t),c=a.length,u=0;c>u;u++)a[u].apply(i,r);var s=f[m[t]];return s&&s.push([w,t,r,i]),i}function p(e,n){g[e]=l(e).concat(n)}function l(e){return g[e]||[]}function d(e){return s[e]=s[e]||o(t)}function v(e,n){u(e,function(e,t){n=n||"feature",m[t]=n,n in f||(f[n]=[])})}var g={},m={},w={on:p,emit:t,get:d,listeners:l,context:n,buffer:v};return w}function i(){return new r}var a="nr@context",c=e("gos"),u=e(2),f={},s={},p=n.exports=o();p.backlog=f},{}],gos:[function(e,n,t){function r(e,n,t){if(o.call(e,n))return e[n];var r=t();if(Object.defineProperty&&Object.keys)try{return Object.defineProperty(e,n,{value:r,writable:!0,enumerable:!1}),r}catch(i){}return e[n]=r,r}var o=Object.prototype.hasOwnProperty;n.exports=r},{}],handle:[function(e,n,t){function r(e,n,t,r){o.buffer([e],r),o.emit(e,n,t)}var o=e("ee").get("handle");n.exports=r,r.ee=o},{}],id:[function(e,n,t){function r(e){var n=typeof e;return!e||"object"!==n&&"function"!==n?-1:e===window?0:a(e,i,function(){return o++})}var o=1,i="nr@id",a=e("gos");n.exports=r},{}],loader:[function(e,n,t){function r(){if(!b++){var e=y.info=NREUM.info,n=s.getElementsByTagName("script")[0];if(e&&e.licenseKey&&e.applicationID&&n){u(m,function(n,t){e[n]||(e[n]=t)});var t="https"===g.split(":")[0]||e.sslForHttp;y.proto=t?"https://":"http://",c("mark",["onload",a()],null,"api");var r=s.createElement("script");r.src=y.proto+e.agent,n.parentNode.insertBefore(r,n)}}}function o(){"complete"===s.readyState&&i()}function i(){c("mark",["domContent",a()],null,"api")}function a(){return(new Date).getTime()}var c=e("handle"),u=e(2),f=window,s=f.document,p="addEventListener",l="attachEvent",d=f.XMLHttpRequest,v=d&&d.prototype;NREUM.o={ST:setTimeout,CT:clearTimeout,XHR:d,REQ:f.Request,EV:f.Event,PR:f.Promise,MO:f.MutationObserver},e(1);var g=""+location,m={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",agent:"js-agent.newrelic.com/nr-952.min.js"},w=d&&v&&v[p]&&!/CriOS/.test(navigator.userAgent),y=n.exports={offset:a(),origin:g,features:{},xhrWrappable:w};s[p]?(s[p]("DOMContentLoaded",i,!1),f[p]("load",r,!1)):(s[l]("onreadystatechange",o),f[l]("onload",r)),c("mark",["firstbyte",a()],null,"api");var b=0},{}]},{},["loader"]);</script>
<title>SmartAPI - Smart</title>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<script type="text/javascript">window.NREUM||(NREUM={});NREUM.info={"beacon":"bam.nr-data.net","errorBeacon":"bam.nr-data.net","licenseKey":"e644b8e723","applicationID":"17322787","transactionName":"el8PEkJZWg4BR04WQlZaBAVERRkLClEEHg==","queueTime":0,"applicationTime":139,"agent":""}</script>
<script type="text/javascript">window.NREUM||(NREUM={}),__nr_require=function(e,n,t){function r(t){if(!n[t]){var o=n[t]={exports:{}};e[t][0].call(o.exports,function(n){var o=e[t][1][n];return r(o||n)},o,o.exports)}return n[t].exports}if("function"==typeof __nr_require)return __nr_require;for(var o=0;o<t.length;o++)r(t[o]);return r}({1:[function(e,n,t){function r(e,n){return function(){o(e,[(new Date).getTime()].concat(a(arguments)),null,n)}}var o=e("handle"),i=e(2),a=e(3);"undefined"==typeof window.newrelic&&(newrelic=NREUM);var c=["setPageViewName","setCustomAttribute","finished","addToTrace","inlineHit"],u=["addPageAction"],f="api-";i(c,function(e,n){newrelic[n]=r(f+n,"api")}),i(u,function(e,n){newrelic[n]=r(f+n)}),n.exports=newrelic,newrelic.noticeError=function(e){"string"==typeof e&&(e=new Error(e)),o("err",[e,(new Date).getTime()])}},{}],2:[function(e,n,t){function r(e,n){var t=[],r="",i=0;for(r in e)o.call(e,r)&&(t[i]=n(r,e[r]),i+=1);return t}var o=Object.prototype.hasOwnProperty;n.exports=r},{}],3:[function(e,n,t){function r(e,n,t){n||(n=0),"undefined"==typeof t&&(t=e?e.length:0);for(var r=-1,o=t-n||0,i=Array(0>o?0:o);++r<o;)i[r]=e[n+r];return i}n.exports=r},{}],ee:[function(e,n,t){function r(){}function o(e){function n(e){return e&&e instanceof r?e:e?c(e,a,i):i()}function t(t,r,o){e&&e(t,r,o);for(var i=n(o),a=l(t),c=a.length,u=0;c>u;u++)a[u].apply(i,r);var s=f[m[t]];return s&&s.push([w,t,r,i]),i}function p(e,n){g[e]=l(e).concat(n)}function l(e){return g[e]||[]}function d(e){return s[e]=s[e]||o(t)}function v(e,n){u(e,function(e,t){n=n||"feature",m[t]=n,n in f||(f[n]=[])})}var g={},m={},w={on:p,emit:t,get:d,listeners:l,context:n,buffer:v};return w}function i(){return new r}var a="nr@context",c=e("gos"),u=e(2),f={},s={},p=n.exports=o();p.backlog=f},{}],gos:[function(e,n,t){function r(e,n,t){if(o.call(e,n))return e[n];var r=t();if(Object.defineProperty&&Object.keys)try{return Object.defineProperty(e,n,{value:r,writable:!0,enumerable:!1}),r}catch(i){}return e[n]=r,r}var o=Object.prototype.hasOwnProperty;n.exports=r},{}],handle:[function(e,n,t){function r(e,n,t,r){o.buffer([e],r),o.emit(e,n,t)}var o=e("ee").get("handle");n.exports=r,r.ee=o},{}],id:[function(e,n,t){function r(e){var n=typeof e;return!e||"object"!==n&&"function"!==n?-1:e===window?0:a(e,i,function(){return o++})}var o=1,i="nr@id",a=e("gos");n.exports=r},{}],loader:[function(e,n,t){function r(){if(!b++){var e=y.info=NREUM.info,n=s.getElementsByTagName("script")[0];if(e&&e.licenseKey&&e.applicationID&&n){u(m,function(n,t){e[n]||(e[n]=t)});var t="https"===g.split(":")[0]||e.sslForHttp;y.proto=t?"https://":"http://",c("mark",["onload",a()],null,"api");var r=s.createElement("script");r.src=y.proto+e.agent,n.parentNode.insertBefore(r,n)}}}function o(){"complete"===s.readyState&&i()}function i(){c("mark",["domContent",a()],null,"api")}function a(){return(new Date).getTime()}var c=e("handle"),u=e(2),f=window,s=f.document,p="addEventListener",l="attachEvent",d=f.XMLHttpRequest,v=d&&d.prototype;NREUM.o={ST:setTimeout,CT:clearTimeout,XHR:d,REQ:f.Request,EV:f.Event,PR:f.Promise,MO:f.MutationObserver},e(1);var g=""+location,m={beacon:"bam.nr-data.net",errorBeacon:"bam.nr-data.net",agent:"js-agent.newrelic.com/nr-952.min.js"},w=d&&v&&v[p]&&!/CriOS/.test(navigator.userAgent),y=n.exports={offset:a(),origin:g,features:{},xhrWrappable:w};s[p]?(s[p]("DOMContentLoaded",i,!1),f[p]("load",r,!1)):(s[l]("onreadystatechange",o),f[l]("onload",r)),c("mark",["firstbyte",a()],null,"api");var b=0},{}]},{},["loader"]);</script>
<meta name="renderer" content="webkit">
<meta name="baidu-site-verification" content="qLDoHdGnb64RHlkm" />
<meta name="alexaVerifyID" content="SIgQikd9LazsFz9M1vPBaQyC4Gw" />

<link rel="shortcut icon" href="/smart-api/assets/favicon.ico" type="image/x-icon" />
<link rel="icon" href="/smart-api/assets/favicon.ico" sizes="32x32" />
<link rel="icon" href="/smart-api/assets/favicon.ico" sizes="64x64" />
<link rel="icon" href="/smart-api/assets/favicon.ico" sizes="128x128" />
<link rel="apple-touch-icon-precomposed" href="/smart-api/assets/logo-sign-dff6b8806ae5cfa80c84502bb5e07c17.png" />
<link rel="stylesheet" href="/smart-api/assets/font-awesome/css/font-awesome.css">
<link
	href="/smart-api/assets/application-39d12b990af1fdb03ea3fd8788854c4d.css"
	media="all" rel="stylesheet" type="text/css" />
<script
	src="/smart-api/assets/application-74c92b03cc0c853fd2e79cbf39f635a4.js"
	type="text/javascript"></script>
	<script src="/smart-api/assets/common.js"></script>
<style type="text/css">
html, body, div, span, object, iframe, h1, h2, h3, h4, h5, h6, p,
	blockquote, pre, abbr, address, cite, code, del, dfn, em, img, ins, kbd,
	q, samp, small, strong, sub, sup, var, b, i, dl, dt, dd, ol, ul, li,
	fieldset, form, label, legend, input, textarea, table, caption, tbody,
	tfoot, thead, tr, th, td, article, aside, canvas, details, figcaption,
	figure, footer, header, hgroup, menu, nav, section, summary, time, mark,
	audio, video {
	font-size: 14px;
	font-family: "微软雅黑";
}

th, td {
	padding: 10px 2px;
	vertical-align: middle;
}

input, select, input, input[type='text'] {
	border: 1px solid #ddd;
	border-radius: 5px;
	height: auto;
}

.pagenum {
	text-align: right;
	padding: 0px 20px 20px 0;
}

.pagenum a {
	display: inline-block;
	padding: 5px 10px;
	border: 1px solid transparent;
	border-radius: 5px;
}

.pagenum a:hover {
	border-color: #ddd;
}

.simple-stack {
	min-width: 1000px;
	width: 60%;
}
</style>





<meta content="authenticity_token" name="csrf-param" />
<meta content="c7U5kEauvgTW+DTJYDNc3LpykVHnGZQnFdmZ0B+C0Tk="
	name="csrf-token" />


</head>