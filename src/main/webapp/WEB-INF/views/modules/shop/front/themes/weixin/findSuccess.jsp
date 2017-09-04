<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>找铺成功</title>
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

<body>
<div class="pb-105">
    <div class="fb-hd fb-hd1" style="height:2rem;">
        <h1 class="h1 text-white">发布成功！</h1>
        <!-- <p class="text-white">一共为您找到个${shopCount}商铺在租商铺满足您的需求</p>
        <a href="javascript:void(0);" class="btn-white">查看</a> -->
    </div>
    
    <!-- <div class="xhi-report bt-7 p-lr-30">
        <div class="text-orange">小Hi报告：</div>
        <p class="text-indent">
            在您搜索的区域内，约${shopNum}家商铺，x个购物中
            心，x间学校
        </p>
    </div> -->
    <div class="xhi-report  p-lr-30">
        <div class="text-orange">推荐加盟品牌：</div>
        <div class="brand-label clearfix">
            <c:forEach items="${cbList}" var="cb">
            	<label>${cb.brandName}</label>
          	</c:forEach>
        </div>
    </div>
    <div class="shop-recommend">
        <div class="shop-hd">
            <div class="line-fl"></div>
            优质店铺推荐
            <div class="line-fr"></div>
        </div>
        <ul class="shop-list p-lr-30">
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
<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>
</body>


<script>
	$(function() {
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-release').addClass('footer-act');	
		
	});
</script>

</html>



