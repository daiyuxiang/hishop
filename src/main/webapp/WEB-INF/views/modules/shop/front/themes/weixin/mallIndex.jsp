<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>区域商铺列表</title>
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

<body class="pb-105">
<div class="pb-105"> 

<c:forEach items="${areaList}" var="area">
<div class="yr-pudong" style="height:7.2rem;">	
    <div class="area-title" onclick="mallAnalysis('${area.id}');">
            <h3>${area.name}</h3>
            <span>${area.mallNum}个shopping mall</span>
    </div>
    <a href="javascript:void(0);"> <input type="button" class="look-area" value="查看" onclick="mallAnalysis('${area.id}');"></a>
    
    <div class="area-img-lis clearfix">
    	<c:forEach items="${area.mallList}" var="mall">
	        <div class="area-img">
	            <img src="${mall.mallUrl}" alt="" onclick="mallInfo('${mall.id}');">
	            <p>${mall.mallName}</p>
	        </div>
       	</c:forEach>
    </div>
</div>
</c:forEach>
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
	
	function mallAnalysis(id) {
		window.location.href = '${ctx}/wx/mall/mallAnalysis?id='+id;
	}
	
	function mallInfo(id) {
		window.location.href = '${ctx}/wx/mall/mallInfo?shoppingMall='+id;
	}

</script>
</html>



