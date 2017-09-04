<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>我的发布</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta name="applicable-device" content="mobile">
    <link rel="stylesheet" href="${ctxStatic}/web/css/style.css" media="screen">
    <script src="${ctxStatic}/web/js/auto.js"></script>
    <script src="${ctxStatic}/web/js/jquery-3.1.1.min.js"></script>
    <script src="${ctxStatic}/web/js/tab.js"></script>
    
</head>

<body>
<div class="  pt-88 ">
    <div id="tabs" class="public-cus-tab p-34">
        <nav class="tab-nav p-cus-nav">
            <ul class="p-34 clearfix bb">
                <li class="tab-current">
                    <a href="#p-cus1" >
                       我的转铺
                    </a>
                </li>
                <li>
                    <a href="#p-cus2">
                        我的找铺
                    </a>
                </li>
            </ul>
        </nav>
        <div class="tab-content p-cus-content">
            <section class="con-current" id="p-cus1">
                <ul class="shop-list">
                    <c:forEach items="${shopList}" var="shop">
	                    <li class="clearfix">
	                    	<c:choose>		
				               <c:when test="${shop.goldFlag=='1'}">
				                    <div class="shop-logo fl gold-shop">
				               </c:when>
				               <c:otherwise>
				                  	<div class="shop-logo fl">
				               </c:otherwise>
				            </c:choose>
	        						<a href="${ctx}/wx/shop/shopInfo?id=${shop.id}"><img src="${shop.image1}"></a>
	                        </div>
	                        <div class="shop-info fr">
	                            <a href="${ctx}/wx/shop/shopInfo?id=${shop.id}" class="shop-name">${shop.shopName}</a>
	                            <div class="shop-flag">
	                            	<c:forEach items="${shop.hotList}" var="hot">
	                                	<span class="button flag2">${hot.hotValue}</span>
	                                </c:forEach>
	                                <span class="flag3">${shop.useArea}㎡</span>
	                            </div>
	                            <div class="shop-price clearfix">
	                   			    <span class="fl">${shop.area.name}<c:if test="${not empty shop.town}">-${shop.town.name}</c:if></span>
	                                <span class="fr">${shop.fee}万元/月</span>
	                            </div>
	                        </div>
	                    </li>
                    </c:forEach>
                </ul>
            </section>
            <section id="p-cus2">
                <ul class="shop-list">
                	<c:forEach items="${findList}" var="find">
	                    <li class="clearfix">
	                        <!-- <div class="shop-logo fl">
	                            <img src="images/shop1.jpg" alt="">
	                        </div> -->
	                        <div class="shop-info" style="width:auto;">
	                            <a href="javascript:void(0);" class="shop-name">${find.purpose}</a>
	                            <div class="shop-flag">
	                                <span class="button flag1">${find.areaName}</span>
	                                <span class="button flag1">${fns:getDictLabel(find.shopType, 'shop_shop_type', '')}</span>
	                                <span class="button flag1">${fns:getDictLabel(find.managementFormat, 'shop_management_format', '')}</span>
	                                <span class="flag3">${find.coveredArea}㎡</span>
	                            </div>
	                            <div class="shop-price clearfix">
	                                <span class="fr">${fns:getDictLabel(find.fee, 'shop_fee', '')}</span>
	                            </div>
	                        </div>
	                    </li>
                   	</c:forEach>
                </ul>
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
		
		$('.footer-my').addClass('footer-act');
		
	});
	
    new CBPFWTabs( document.getElementById( 'tabs' ) );

	
</script>

</html>



