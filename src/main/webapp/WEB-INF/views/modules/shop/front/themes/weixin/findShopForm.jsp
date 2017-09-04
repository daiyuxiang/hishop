<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>发布找铺需求</title>
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
</head>

<body>
<div class="popup popup1" id="errorPopup">
    <div class="popup-content">
        <p>请输入正确的手机号码</p>
        <a href="javascript:void(0);" class="btn-orange btn-orange-big " onclick="closePopup('errorPopup')">确定</a>
    </div>
</div>

<div class="pb-105">
    <div class="fb-hd">
        <img src="${ctxStatic}/web/images/fb-hd1.jpg" alt="">
    </div>
    <form:form id="inputForm" modelAttribute="findShop" action="${ctx}/wx/findShop/save" method="post" class="form-horizontal">
    	<input type="hidden" name="matingIdList" id="matingIdList">
    <div class="form-list">
        <ul>
            <li class="clearfix">
                <span class="f-title">您的电话</span>
                <span class="f-con"><input type="text" name="mobile" id="mobile"></span>
            </li>
            <li class="clearfix">
                <span class="f-title">你要开什么店？</span>
                <span class="f-con"><input type="text" name="purpose"></span>
            </li>
            <li class="clearfix">
                <span class="f-title">区域</span>
                <span class="f-con" id="areaSpan">
                    <form:select path="areaId">
                    	<option value="">全部</option>
						<form:options items="${areaList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
					</form:select>
                </span>
            </li>
            <li class="clearfix">
                <span class="f-title">镇</span>
                <span class="f-con" id="townSpan">
                    <form:select path="town.id" id="town">
						<form:options items="${townList}" itemLabel="name" itemValue="id" htmlEscape="false"/>
					</form:select>
                </span>
            </li>
            <li class="clearfix">
                <span class="f-title">面积</span>
                <span class="f-con">
                    <form:select path="coveredArea">
						<form:options items="${fns:getDictList('shop_area')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
                </span>
            </li>
            <li class="clearfix">
                <span class="f-title">租金范围</span>
                <span class="f-con">
                    <form:select path="fee">
						<form:options items="${fns:getDictList('shop_fee')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
                </span>
            </li>
            <li class="clearfix">
                <span class="f-title">商铺类型</span>
                <span class="f-con">                	
	                <form:select path="shopType">
						<option value="">不限</option>						
						<form:options items="${fns:getDictList('shop_shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
                </span>
            </li>
            <li class="clearfix">
                <span class="f-title">经营业态</span>
                <span class="f-con">
                    <form:select path="managementFormat">
						<form:options items="${fns:getDictList('shop_management_format')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
                </span>
            </li>
             <li class="clearfix">
                <span class="f-title">推荐人</span>
                <span class="f-con"><input type="text" name="referrer" id="referrer"></span>
            </li>
            <li class="clearfix">
                <span class="f-title">配套要求</span>
                <span class="f-con" id="matingSpan">
                	<c:forEach items="${fns:getDictList('shop_mating')}" var="mating">
	                    <a class="auto" onclick="setSelect(this)" data="${mating.value}">${mating.label}</a>
                    </c:forEach>
                </span>
            </li>
        </ul>
        <button type="button" class="btn-orange btn-orange-big" onclick="submitClick();">立即提交</button>
    </div>
    </form:form>
</div>
<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>

</body>


<script>
	$(function() {
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-release').addClass('footer-act');	
		
		$('#areaSpan').find('select').change(function() {
			selectTown($(this).val());
		});
	});
	
	
	function submitClick() {	
		var telRe = /^0?(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$/;
		
		var matingArray =  new Array();
		$.each($('#matingSpan').find('.select-span'),function(index,obj) {
			matingArray[index] = $(obj).attr('data');
		});
		$('#matingIdList').val(matingArray);
		
		
		if (!telRe.test($('#mobile').val())) {
			layer.msg("请输入正确的手机号");
		} else {
			$("#inputForm").submit();
		}
	}
	
	function closePopup(objId) {
		$('#'+objId).hide();
	}
	
	function setSelect(obj) {
		if($(obj).hasClass('select-span')) {
			$(obj).removeClass('select-span');
		} else {
			$(obj).addClass('select-span');
		}
	}
	
	function selectTown(areaId) {
		$.ajax({
			  url: "${ctx}/wx/shop/getTownByArea",
	  	      type: "post",
	  	      dataType:'json',  
	  	      async:false,
	  	      data:{areaId:areaId},
	  	      success: function(data) {
	  	    	 var $select =  $('#townSpan').find('select');
	  	    	 
	  	    	 $select.val(''); 
	  	    	 $select.html(''); 
	  	         
	  	         $.each(data,function(index,val){
	  	        	$select.append('<option value="'+val.id+'">'+val.name+'</option>');
	  	         });
	  	    
	  	      }
		});
	}
</script>

</html>



