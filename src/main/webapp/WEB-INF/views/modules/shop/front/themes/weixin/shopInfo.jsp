<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>商铺详情</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta name="applicable-device" content="mobile">
    <link rel="stylesheet" href="${ctxStatic}/web/css/style.css" media="screen">
    <script src="${ctxStatic}/web/js/auto.js"></script>
    <script src="${ctxStatic}/web/js/jquery-3.1.1.min.js"></script>
    
    <link rel="stylesheet" href="${ctxStatic}/web/css/swiper.min.css" media="screen">
	<script src="${ctxStatic}/web/js/swiper.min.js"></script>
	<script src="${ctxStatic}/web/js/tab.js"></script>
	
	<link href="${ctxStatic}/layer/skin/layer.css" rel="stylesheet" />
	<script src="${ctxStatic}/layer/layer.js" type="text/javascript"></script>
</head>

<body>
<div class="popup-tel popup-z" id="shop-popup-tel">
    <div class="popup-tel-con bg-white">
   		
        <p></p>
        <a href="" class="bg-white text-orange">取消</a>  <a href="" class="tel-yes" id="shop-tel">呼叫</a>
    </div>
</div>

<div class="popup-tel popup-z" id="hi-popup-tel">
    <div class="popup-tel-con bg-white">
        <p>021-80344870</p>
        <a href="" class="bg-white text-orange">取消</a>  <a href="tel:021-80344870" class="tel-yes">呼叫</a>
    </div>
</div>

<div class="pb-105">
		<c:choose>		
	    	<c:when test="${shop.goldFlag=='1'}">
    			<div class="swiper-container index-banner shop-banner gold-shop-detail" id="banner-swiper-container">
	        </c:when>
	        <c:otherwise>
    			<div class="swiper-container index-banner shop-banner" id="banner-swiper-container">
	        </c:otherwise>
	    </c:choose>
        <div class="swiper-wrapper" id="banner_image">
            <c:if test="${not empty shop.image1}">
	            <div class="swiper-slide">
	                <img src="${shop.image1}" class="swiper-image">
	            </div>
            </c:if>
            <c:if test="${not empty shop.image2}">
	            <div class="swiper-slide">
	                <img src="${shop.image2}" class="swiper-image">
	            </div>
            </c:if>
            <c:if test="${not empty shop.image3}">
	            <div class="swiper-slide">
	                <img src="${shop.image3}" class="swiper-image">
	            </div>
            </c:if>
            <c:if test="${not empty shop.image4}">
	            <div class="swiper-slide">
	                <img src="${shop.image4}" class="swiper-image">
	            </div>
            </c:if>
            <c:if test="${not empty shop.image5}">
	            <div class="swiper-slide">
	                <img src="${shop.image5}" class="swiper-image">
	            </div>
            </c:if>
            <c:if test="${not empty shop.image6}">
	            <div class="swiper-slide">
	                <img src="${shop.image6}" class="swiper-image">
	            </div>
            </c:if>
        </div>
        <div class="pagination"></div>
    </div>
    <div class="shop-d-box1 clearfix">
        <div class="shop-d-name fl">
            <h1 class="h1">${shop.shopName}</h1>
            <div class="shop-flag">
            	<c:forEach items="${shop.hotList}" var="hot">
	            	<span class="button flag1">${hot.hotValue}</span>
	            </c:forEach>
            </div>
        </div>
        <div class="shop-d-collect fr" onclick="hobby('${shop.id}',this);">
        	<c:choose>		
	            <c:when test="${shop.hobbyFlag==1}">
	            	<img src="${ctxStatic}/web/images/icon-collect.png" alt="" data='1'>
	            </c:when>
	            <c:otherwise>
	            	<img src="${ctxStatic}/web/images/icon-collect-no.png" alt="" data='0'>
	            </c:otherwise>
	        </c:choose>
            <p>收藏</p>
        </div>
    </div>
    <div class="shop-rent">
        <ul class="clearfix">
            <li>
                <p>月租金</p>
                <span>${shop.fee}万元/月</span>
            </li>
            <li>
                <p>转让费</p>
                <span>${shop.transferFee}万元</span>
            </li>
            <li>
                <p>使用面积</p>
                <span>${shop.useArea}㎡</span>
            </li>
        </ul>
    </div>
    <div class="shop-add">
        地址：${shop.shopAddress}
    </div>
    <div class="shop-d-content" id="tabs">
        <nav class="tab-nav">
            <ul class="clearfix">
                <li class="tab-current">
                    <a href="#tab1" class="tab-nav1"><i></i><p>铺源信息</p></a>
                </li>
                <li class="">
                    <a href="#tab2" class="tab-nav2"><i></i><p>经营情况</p></a>
                </li>
                <li>
                    <a href="javascript:void(0);" class="tab-nav3" onclick="showShopTel()"><i></i><p>联系房东</p></a>
                </li>
                <li>
                    <a href="javascript:void(0);" class="tab-nav4" onclick="showHiTel()"><i></i><p>直联小Hi</p></a>
                </li>
            </ul>
        </nav>
        <div class="tab-content">
            <section class="con-current" id="tab1">
                <div class="shop-d-info">
                	<p>实勘详情</p>
                	${shop.shopContent}
                </div>
                <div class="shop-d-info bt-4">
                    <p>铺源信息</p>
                    <ul class="info-list">
                        <li class="clearfix">
                            <span class="info-title">位置：</span>
                            <span class="info-con">${shop.position}</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">进深：</span>
                            <span class="info-con">${shop.depth}米</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">层高：</span>
                            <span class="info-con">${shop.floorHeight}米</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">面宽：</span>
                            <span class="info-con">${shop.faceWidth}米</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">楼层：</span>
                            <span class="info-con">${fns:getDictLabel(shop.floor, 'shop_floor', '')}</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">是否分割：</span>
                            <span class="info-con">${fns:getDictLabel(shop.segmentation, 'yes_no', '')}</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">配套：</span>
                            <c:forEach items="${shop.matingList}" var="mating">
                            	<span style="width:2rem;">${fns:getDictLabel(mating.matingId, 'shop_mating', '')}</span>
                           	</c:forEach>
                        </li>
                    </ul>
                </div>
            </section>
            <section class="" id="tab2">
                <div class="shop-d-info ">
                    <p>经营状况</p>
                    <ul class="info-list">
                        <li class="clearfix">
                            <span class="info-title">经营状态：</span>
                            <span class="info-con">营业中</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">经营年限：</span>
                            <span class="info-con">${shop.operatingLife} 年</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">经营业态：</span>
                            <span class="info-con">${fns:getDictLabel(shop.managementFormat, 'shop_management_format', '')}</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">当前经营：</span>
                            <span class="info-con">${shop.currentManagement}</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">品牌情况：</span>
                            <span class="info-con">${shop.brandInfo}</span>
                        </li>
                    </ul>
                </div>
                <div class="shop-d-info bt-4">
                    <p>租约相关</p>
                    <ul class="info-list">
                        <li class="clearfix">
                            <span class="info-title">支付方式：</span>
                            <span class="info-con">${shop.paymentMethod}</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">租赁状态：</span>
                            <span class="info-con">${fns:getDictLabel(shop.leaseStatus, 'shop_lease_status', '')}</span>
                        </li>
                        <li class="clearfix">
                            <span class="info-title">签约年限：</span>
                            <span class="info-con">${shop.maximumLease} 年</span>
                        </li>
                    </ul>
                </div>
            </section>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>


</body>

<script>
	$(function() {
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-shop').addClass('footer-act');
	});
	
	var swiper = new Swiper('.swiper-container', {
        pagination: '.pagination',
        paginationClickable: true,
        spaceBetween: 30,
        autoplay : 3000,//可选选项，自动滑动
        loop : true//可选选项，开启循环
    });
    new CBPFWTabs( document.getElementById( 'tabs' ) );
    
    
    function hobby(shopId,obj) {    	
    	$.ajax({
			url : "${ctx}/wx/member/setHobby",
			type : "post",
			dataType : 'json',
			async : false,
			data : {"shopId":shopId},
			success : function(data) {
				if(data.status=="1") {
					if($(obj).find('img').attr('data')=='1') {
						$(obj).find('img').attr('data','0').attr('src','${ctxStatic}/web/images/icon-collect-no.png');
					} else {
						$(obj).find('img').attr('data','1').attr('src','${ctxStatic}/web/images/icon-collect.png');
					}
				}
			}
		});
    }
    
    function showHiTel() {
    	$('#hi-popup-tel').show();
    }
    
    var telFlag = false;
    function showShopTel() {
    	if(telFlag) {
    		return;
    	}
    	telFlag = true;
    	
    	$.ajax({
			url : "${ctx}/wx/tel/bindAx",
			type : "post",
			dataType : 'json',
			async : false,
			data : {"caller":${shop.mobile}},
			success : function(data) {
				if(data.status=='1') {					
					$('#shop-tel').attr('href',"tel:"+data.data);
					$('#shop-popup-tel').find('p').text("为了保护房东的隐私，本号码为虚拟号，仅用于本次拨打 "+ data.data);
			    	$('#shop-popup-tel').show();
				} else {
					layer.msg("呼叫失败，请稍后再试");
				}
			}
		});
    }
    
</script>

</html>



