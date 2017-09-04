<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>发布</title>
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
<div class="fb-box fb-box1" >
    <h1 class="h1">助您分析意向店铺周边业态，让数据帮你作店铺决策</h1>
    <a href="${ctx}/wx/findShop/form" class="btn-orange btn-orange-big">我要找铺</a>
    <div class="text-white">想犯个懒？输入电话，小Hi帮您一键找铺！</div>
    <div class="txt-box clearfix">
        <input type="text" placeholder="输入电话" class="ipt-text fl" id="findTel">
        <a href="javascript:void(0);" class="btn-orange btn-orange-small fr" onclick="publish('findTel','1');">一键发布</a>
    </div>
</div>
<div class="fb-box fb-box2" >
    <h1 class="h1">0元享受一对一专属于客服服务360度为您推广助您快速转让</h1>
    <a href="${ctx}/wx/shop/saleShopForm" class="btn-orange btn-orange-big">我要转铺</a>
    <div class="text-white">想犯个懒？输入电话，小Hi帮您一键转铺！</div>
    <div class="txt-box clearfix">
        <input type="text" placeholder="输入电话" class="ipt-text fl" id="saleTel">
        <a href="javascript:void(0);" class="btn-orange btn-orange-small fr" onclick="publish('saleTel','2');">一键发布</a>
    </div>
</div>
<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>

<div class="popup popup1" id="successPopup">
    <div class="popup-content">
        <p><img src="${ctxStatic}/web/images/icon-succ.png" alt="">恭喜您一键发布成功<br>
            小Hi会在最短的时间里与您联系<br>
            请您保持手机畅通哦！</p>
        <a href="${ctx}/wx/shop/shopIndex" class="btn-orange btn-orange-big " onclick="closePopup('successPopup')">确定</a>
    </div>
</div>

<div class="popup popup1" id="errorPopup">
    <div class="popup-content">
        <p>请输入正确的手机号码</p>
        <a href="javascript:void(0);" class="btn-orange btn-orange-big " onclick="closePopup('errorPopup')">确定</a>
    </div>
</div>


</body>
<script>
	$(function() {
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-release').addClass('footer-act');
	});

	function publish(objId,inputId) {
		var re = /^1\d{10}$/;
		var tel = $('#'+objId).val();
		
		var data = {"mobile":tel};
		
		if(inputId==1) {
			data.findFlag = "1";
		} else {
			data.publishSaleFlag = "1";
		}
		
		if (!re.test(tel)) {
			$('#errorPopup').show();
	    } else {
	    	$.ajax({
				url : "${ctx}/wx/member/publish",
				type : "post",
				dataType : 'json',
				async : false,
				data : data,
				success : function(data) {
					if(data.status=="1") {
						$('#successPopup').show();
					}
				}
			});
	    }
	}
	
	function closePopup(objId) {
		$('#'+objId).hide();
	}
	
</script>

</html>



