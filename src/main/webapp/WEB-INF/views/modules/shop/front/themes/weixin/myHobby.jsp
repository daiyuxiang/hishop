<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>我的收藏</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta name="applicable-device" content="mobile">
    <link rel="stylesheet" href="${ctxStatic}/web/css/style.css" media="screen">
    <script src="${ctxStatic}/web/js/auto.js"></script>
    <script src="${ctxStatic}/web/js/jquery-3.1.1.min.js"></script>
</head>

<body id="popup-lis">
<div class=" p-lr-30 pb-105">
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
<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>

<script>
	$(function() {
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-my').addClass('footer-act');
		
	});

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

</script>
</body>
</html>



