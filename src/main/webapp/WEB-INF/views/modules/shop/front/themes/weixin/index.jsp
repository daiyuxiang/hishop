<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Hi铺首页</title>
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
</head>

<body>
<div class="pb-105">
    <div class="swiper-container index-banner" id="banner-swiper-container">
        <div class="swiper-wrapper" id="banner_image">
            <c:forEach items="${sliderImgList}" var="sliderImg">
	            <div class="swiper-slide">
	                <img src="${sliderImg.url}" imgType="${sliderImg.imgType}" btnUrl="${sliderImg.btnUrl}"  showUrl="${sliderImg.showUrl}" class="swiper-image">
	            </div>
           	</c:forEach>
        </div>
        <div class="pagination"></div>
    </div>
    <div class="popup-img" style="display: none">
        <span class="xx" onclick="closePopup();"></span>
        <a href="javascript:void(0);"><img src="" alt="" ></a>
    </div>
    <div class="shop-index-detail ">
        <ul class="clearfix">
            <li>
                <h1 class="h1">今日新铺</h1>
                <p><b>${countToday}</b>套</p>
                <a href="${ctx}/wx/shop/shopIndex?todayFlag=1" class="button btn-a">查看</a>
            </li>
            <li>
                <h1 class="h1">当前商铺数</h1>
                <p><b>${countAll}</b>套</p>
                <a href="${ctx}/wx/findShop/form" class="button btn-a">帮我找铺</a>
            </li>
            <li>
                <h1 class="h1">正在找铺</h1>
                <p><b>${countFind}</b>人</p>
                <a href="${ctx}/wx/shop/saleShopForm" class="button btn-a">帮我转铺</a>
            </li>
        </ul>
    </div>
    <div class="shopping-mall p-lr-30 bt-7">
        <h1 class="h1" onclick="mallIndex();">SHOPPING MALL专区<a href="${ctx}/wx/mall/mallIndex" class="more">查看更多>></a></h1>
        <div class="x-gundong">
            <ul class="clearfix gun-lis">
            	<c:forEach items="${shoppingMallList}" var="mall">
                	<li><a href="${ctx}/wx/mall/mallInfo?shoppingMall=${mall.id}"><img src="${mall.mallUrl}" alt=""><p>${mall.mallName}</p></a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="shop-recommend p-lr-30 bt-7">
        <h1 class="h1" onclick="shopIndex();">优质店铺推荐 <a href="${ctx}/wx/shop/shopIndex" class="more">查看更多>></a></h1>
        <ul class="shop-list">
            <c:forEach items="${page.list}" var="shop">
	            <li class="clearfix">
	                <div class="shop-logo fl gold-shop">
	                    <a href="${ctx}/wx/shop/shopInfo?id=${shop.id}"><img src="${shop.image1}"></a>
	                </div>
	                <div class="shop-info fr">
	                    <a href="${ctx}/wx/shop/shopInfo?id=${shop.id}" class="shop-name">${shop.shopName}</a>
	                    <div class="shop-flag">
	                    	<c:forEach items="${shop.hotList}" var="hot">
	                        	<span class="button flag1">${hot.hotValue}</span>
	                        </c:forEach>
	                        <span class="flag3">${shop.useArea}㎡</span>
	                        <a href="javascript:void(0);" class="collect collect-y fr" onclick="hobby('${shop.id}',this);">
	                        	<c:choose>		
	                        		<c:when test="${shop.hobbyFlag==1}">
	                        		    <i class="addHobby"></i> 
	                        		</c:when>
	                        		<c:otherwise>
	                        			<i></i>
	                        		</c:otherwise>
	                        	</c:choose>
	                        	收藏 		
	                       	</a>
	                    </div>
	                    <div class="shop-price clearfix">
                    		<span class="fl">${shop.area.name}<c:if test="${not empty shop.town}">-${shop.town.name}</c:if></span>
	                        <span class="fl">&nbsp;&nbsp;${fns:differentTime(shop.updateDate)}</span>
	                        <span class="fr">${shop.fee}万元/月</span>
	                    </div>
	                </div>
	            </li>
	        </c:forEach>
        </ul>
    </div>
</div>

<div class="top-warp">
    <img src="${ctxStatic}/web/images/top2.png" alt="" class="top-icon" onclick="goTop()">
</div>

<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>


<script>
	$(function() {
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-index').addClass('footer-act');
		
		var li_width=$('.gun-lis li').length;
        var list_width=parseFloat(li_width*'2.4'-'0.52')+'rem';
        $('.gun-lis').css('width',list_width);
        
        var swiper = new Swiper('.swiper-container', {
            pagination: '.pagination',
            paginationClickable: true,
            spaceBetween: 30,
            autoplay : 3000,//可选选项，自动滑动
            loop : true,//可选选项，开启循环
            onClick: function(swiper,event){     
               if($(event.target).attr('imgType')=='2') {
            	   window.location.href =  $(event.target).attr('btnUrl');
               } else {
            	   $('.popup-img').find('img').attr('src',$(event.target).attr('showUrl'));          	  
               	   $('.popup-img').show();
               }               
            }
        });
	});
	
	function mallIndex(){
		window.location.href="${ctx}/wx/mall/mallIndex";
	}
	
	function shopIndex() {
		window.location.href="${ctx}/wx/shop/shopIndex";
	}
    
    function hobby(shopId,obj) {    	
    	$.ajax({
			url : "${ctx}/wx/member/setHobby",
			type : "post",
			dataType : 'json',
			async : false,
			data : {"shopId":shopId},
			success : function(data) {
				if(data.status=="1") {
					if($(obj).find('i').hasClass('addHobby')) {
						$(obj).find('i').removeClass('addHobby');
					} else {
						$(obj).find('i').addClass('addHobby');
					}
				}
			}
		});
    }
    function closePopup() {
        $('.popup-img').hide();
    }
    
    function goTop() {
		$("html,body").animate({scrollTop:0}, 0);
	}
</script>
</body>
</html>



