<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>完善信息</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta name="applicable-device" content="mobile">
    <link rel="stylesheet" href="${ctxStatic}/web/css/style.css" media="screen">
    <script src="${ctxStatic}/web/js/auto.js"></script>
    <script src="${ctxStatic}/web/js/jquery-3.1.1.min.js"></script>
    
    <link href="${ctxStatic}/layer/skin/layer.css" rel="stylesheet" />
	<script src="${ctxStatic}/layer/layer.js" type="text/javascript"></script>
	<script src="${ctxStatic}/web/js/re.js"></script>
</head>

<body>
   <form:form id="goldForm" modelAttribute="shop" action="${ctx}/wx/shop/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="goldFlag" value="1">
	</form:form>
<div class="pb-105">
    <div class="fb-hd fb-hd-con ">
      <p>预约成为金牌店铺，可免填下面信息，由小Hi一对一帮您进一步完善店铺详情哦</p>
        <a href="javascript:void(0);" class="btn-orange btn-orange-big w180" onclick="setApplicationFlag()">预约成为金牌店铺</a>
    </div>
    <form:form id="inputForm" modelAttribute="shop" action="${ctx}/wx/shop/saveMore" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <div class="form-list1">
    <h3>位置信息</h3>
    <div class="form-list p15">
        <ul>
            <li class="clearfix">
                <span class="f-title">位置</span>
                <span class="f-con"><input type="text" placeholder="例如拐角和人行道中间" name="position" id="position"></span>
            </li>
        </ul>
    </div>
    </div>
    <div class="form-list1">
        <h3>建筑信息</h3>
        <div class="form-list p15">
            <ul>
                <li class="clearfix">
                    <span class="f-title">进深</span>
                    <span class="f-con"><input type="text" name="depth" id="depth"></span>
                    <span>米</span>
                </li>
                <li class="clearfix">
                    <span class="f-title">层高</span>
                    <span class="f-con"><input type="text" name="floorHeight" id="floorHeight"></span>
                    <span>米</span>
                </li>
                <li class="clearfix">
                    <span class="f-title">是否分割</span>
	                <span class="f-con">
	                	<form:select path="segmentation" class="input-xlarge ">
							<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
	                </span>
                </li>
                <li class="clearfix">
                    <span class="f-title">面宽</span>
                    <span class="f-con"><input type="text" name="faceWidth" id="faceWidth"></span>
                    <span>米</span>
                </li>
                <li class="clearfix">
                    <span class="f-title">楼层</span>
                    <span class="f-con">
	                    <form:select path="floor">
							<form:options items="${fns:getDictList('shop_floor')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</span>
                    <span>层</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="form-list1">
        <h3>经营状况</h3>
        <div class="form-list p15">
            <ul>
                <li class="clearfix">
                    <span class="f-title">经营状态</span>
                <span class="f-con">
	               	<form:select path="managementStatus" class="input-xlarge ">
						<form:options items="${fns:getDictList('shop_management_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
                </span>
                </li>
                <li class="clearfix">
                    <span class="f-title">经营年限</span>
                    <span class="f-con"><input type="text" name="operatingLife" id="operatingLife"></span>
                </li>
                <li class="clearfix">
                    <span class="f-title">品牌情况</span>
                    <span class="f-con"><input type="text" name="brandInfo" id="brandInfo"></span>
                </li>
            </ul>
        </div>
    </div>
    <div class="form-list1">
        <h3>租约相关</h3>
        <div class="form-list p15">
            <ul>
            	<li class="clearfix">
                    <span class="f-title">租赁状态</span>
	                <span class="f-con">
	                	<form:select path="leaseStatus" class="input-xlarge ">
							<form:options items="${fns:getDictList('shop_lease_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
	                </span>
                </li>
                <li class="clearfix">
                    <span class="f-title">支付方式</span>
                    <span class="f-con"><input type="text" name="paymentMethod" id="paymentMethod" placeholder="例如押一付三"></span>
                </li>
                <li class="clearfix">
                    <span class="f-title">签约年限</span>
                    <span class="f-con"><input type="text" name="maximumLease" id="maximumLease"></span>
                    <span>年</span>
                </li>
            </ul>
            
            <button type="button" class="btn-orange btn-orange-big p34" onclick="submitClick();">提交</button>
        </div>
    </div>
	
	</form:form>

</div>

<div class="ok-popup" id="saveSuccessPopup" style="background-color:rgba(0,0,0,0.3);" >
     <div class="popup-con">
          <div class="popup-con1">
         <p class="text-orange"><img src="${ctxStatic}/web/images/popup-ok.jpg" alt="">恭喜您已提交店铺信息<br>
        小Hi会在最短的时间内审核上线<br>请耐心等待</p>
         <a href="${ctx}/wx/shop/shopIndex" class="btn-orange btn-orange-big change-w">确定</a>
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
		
		$('.footer-release').addClass('footer-act');	
		
	});
	
	
	function submitClick() {	
		if (!floatRe.test($('#depth').val())) {
			layer.msg("请输入正确的进深");
		} else if(!floatRe.test($('#floorHeight').val())) {
			layer.msg("请输入正确的层高");
		} else if(!floatRe.test($('#faceWidth').val())) {
			layer.msg("请输入正确的面宽");
		} else if(!integerRe.test($('#maximumLease').val())) {
			layer.msg("请输入正确的签约年限");
		} else {
			var data = $('#inputForm').serialize()
			
			$.ajax({
				  url: "${ctx}/wx/shop/saveMore",
		  	      type: "post",
		  	      dataType:'json',  
		  	      async:false,
		  	      data:data,
		  	      success: function(data) {
		  	    	 if(data.status='1') {
		  	    		$('#saveSuccessPopup').show();
		  	    	 }
		  	      }
			});
			
		}
	}
		
	function setApplicationFlag() {
		$('#goldForm').submit();
		return false;
	}
</script>

</html>



