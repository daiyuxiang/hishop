<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Hi铺</title>
    <link rel="stylesheet" href="${ctxStatic}/website/css/main.css">
    <!-- script src="${ctxStatic}/web/js/auto.js"></script -->
	<script src="http://g.alicdn.com/sj/lib/jquery/dist/jquery.min.js "></script>
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="logo"></div>
			<a href="${pageContext.request.contextPath}/contact_us">联系我们</a>
			<a href="${pageContext.request.contextPath}/" class="active" style="margin-right: 60px;">首页</a>
		</div>
	</div>
    <div class="jumbotron">
		<div class="container">    
			<p class="textdiv1">Hi铺，开店更简单!</p>
			<div class="animation">
				<div id="text" class="textdiv2"><div id="concern" class="concern">马上关注</div>
			    <div id="QRcode" class="WX-code"></div>
			    </div>
		    </div>
		</div>
		<div class="jumbotron-btn">
			<div class="container">
				<div class="jumbotron-icon icon1"></div>
				<div class="jumbotron-btn-text">
					<p>累计商业体量&nbsp;(m²)</p>
					<span id="count1">${countArea}</span>
				</div>
				<div class="jumbotron-icon icon2" style="margin-left: 120px;"></div>
				<div class="jumbotron-btn-text">
					<p>累计商铺总数&nbsp;(套)</p>
					<span id="count2">${countAll}</span>
				</div>
				<div class="jumbotron-icon icon3" style="margin-left: 120px;"></div>
				<div class="jumbotron-btn-text">
					<p>累计成交总数&nbsp;(套)</p>
					<span id="count3">${countSale}</span>
				</div>
			</div>
		</div>
    </div>
    <div class="content">
    	<div class="content-nav container">
    		<div class="content-left border-btn" style="margin-top: 60px;">
				<div class="content-icon icon4"></div>
				<p>依据大数据智能匹配店铺</p>
    		</div>
    		<div class="content-right border-btn" style="margin-top: 60px;">
				<div class="content-icon icon5"></div>
				<p>海量真实铺源提供</p>
    		</div>
    		<div class="content-left border-btn">
				<div class="content-icon icon6"></div>
				<p>60天快速转铺服务</p>
    		</div>
    		<div class="content-right border-btn">
				<div class="content-icon icon7"></div>
				<p>金牌会员1对1专人服务</p>
    		</div>
    		<div class="content-left">
				<div class="content-icon icon8"></div>
				<p>30天迅速选址服务</p>
    		</div>
    		<div class="content-right">
				<div class="content-icon icon9"></div>
				<p>无中介不成交不收费</p>
    		</div>
    	</div>
    </div>
    <div class="choose">
    	<div class="container">
	    	<div class="choose-title">
	    		<p>选择我们</p>
	    	</div>
	    	<div class="choose-nav">
	    		<div class="choose-img1">
	    			<p>专业</p>
	    		</div>
	    		<div class="choose-img2">
	    			<p>高效</p>
	    		</div>
	    		<div class="choose-img3">
	    			<p>安全</p>
	    		</div>
	    		<div class="choose-img4">
	    			<p>海量</p>
	    		</div>
	    	</div>
	    </div>
    </div>
    <div class="partner">
    	<div class="container">
			<div class="partner-title">
				<p>合作伙伴</p>
			</div>
			<ul class="partner-nav">
				<li><img src="${ctxStatic}/website/images/partner/cot_27.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_20.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_3.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_4.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_5.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_6.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_7.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_8.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_9.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_10.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_11.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_12.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_13.jpg"></li>
				<li><img src="${ctxStatic}/website/images/partner/cot_14.jpg"></li>
				<li class="img-bottom"><img src="${ctxStatic}/website/images/partner/cot_37.jpg"></li>
			</ul>
		</div>
    </div>

<script> 
 $(document).ready(function(){
 	$("#concern").click(function(){
		$("#text").animate({
			height:'168px'
		},"slow"); 		
		$("#concern").fadeOut("fast")
		$("#QRcode").animate({
			height:'168px',
			opacity:'1'
		},"slow");
		event.stopPropagation();
 	});
 	$("#QRcode").click(function(){
		$("#text").animate({
			height:'38px'
		},"slow"); 		
		$("#concern").fadeIn("fast")
		$("#QRcode").animate({
			height:'38px',
			opacity:'0'
		},"slow");
		event.stopPropagation();
 	});	
 });
</script> 
</body>
</html>



