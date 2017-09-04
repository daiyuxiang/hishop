<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>个人中心</title>
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

<body style="background-color: #ebebeb;">
<div class="pb-105">
    <div class="yr-banner">
        <span class="img-tx">
             <img src="${user.headimgurl}" alt="" >
        </span>
        <p>${user.nickname}</p>
    </div>
    
    <div class="yr-set bg-white">
        <ul class="set-lis">
            <li class="next-rig bb" id="myPublish">
                <a href="${ctx}/wx/my/myPublish">
                    <img src="${ctxStatic}/web/images/my-icon3.png" alt="">我的发布
                </a>
            </li>
            <li class="next-rig bb" id="help">
                <a href="${ctx}/wx/my/help">
                    <img src="${ctxStatic}/web/images/my-icon2.png" alt="">帮助中心
                </a>
            </li>
            <li class="next-rig" id="myHobby">
                <a href="${ctx}/wx/my/myHobby">
                    <img src="${ctxStatic}/web/images/my-icon1.png" alt="">我的收藏
                </a>
            </li>
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
		
		$('.footer-my').addClass('footer-act');
		
		
		$('#myPublish').on('click',function() {
			window.location.href  = '${ctx}/wx/my/myPublish';
		});
		
		$('#help').on('click',function() {
			window.location.href  = '${ctx}/wx/my/help';
		});
		
		$('#myHobby').on('click',function() {
			window.location.href  = '${ctx}/wx/my/myHobby';
		});
	});
	
</script>

</html>



