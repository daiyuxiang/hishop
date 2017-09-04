<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>转铺</title>
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
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
</head>

<body>
<div class="pb-105">
    <div class="fb-hd fb-hd3">
       <p>本月内<span class="text-orange">${saleSucessNum}</span>位客户在Hi铺转让成功</p>
    </div>
   	<form:form id="inputForm" modelAttribute="shop" action="${ctx}/wx/shop/save" method="post" class="form-horizontal">
    <form:hidden path="goldFlag" value='0'/>
    <form:hidden path="showFlag" value='0'/>
    <form:hidden path="PublishType" value='2'/>
    <input type="hidden" name="image1"/>
    <input type="hidden" name="image2"/>
    <input type="hidden" name="image3"/>
    <input type="hidden" name="image4"/>
    <input type="hidden" name="image5"/>
    <input type="hidden" name="image6"/>
    
    <div class="form-list" style="padding: 0.5rem;">
        <ul>
            <li class="clearfix">
                <span class="f-title">您的电话<font color="red">*</font></span>
                <span class="f-con"><input type="text" name="mobile" id="mobile" class="require">
                
                </span>
            </li>
               <li class="clearfix">
                <span class="f-title">您的名字</span>
                <span class="f-con"><input type="text" name="memberName" id="memberName">
                </span>
            </li>
            <li class="clearfix">
                <span class="f-title">店铺地址<font color="red">*</font></span>
                <span class="f-con"><input type="text" name="shopAddress" id="shopAddress"></span>
            </li>
            <li class="clearfix">
                <span class="f-title">区域</span>
                <span class="f-con" id="areaSpan">
                    <form:select path="area.id">
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
                <span class="f-title">使用面积<font color="red">*</font></span>
                <span class="f-con">
                	<input type="text" name="useArea" id="useArea">
                </span>
                <span>㎡</span>
            </li>
            <li class="clearfix">
                <span class="f-title">月租金<font color="red">*</font></span>
                <span class="f-con"><input type="text" name="fee" id="fee"></span>
                <span>万元</span>
            </li>
            <li class="clearfix">
                <span class="f-title">商铺类型</span>
                <span class="f-con">
                    <form:select path="shopType">
						<form:options items="${fns:getDictList('shop_shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
                </span>
            </li>
            <li class="clearfix" id="shoppingMallLi">
                <span class="f-title">商场名称</span>
                <span class="f-con">
                	<form:select path="shoppingMall" class="input-xlarge ">
						<form:options items="${shoppingMallList}" itemLabel="mallName" itemValue="id" htmlEscape="false"/>
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
                <span class="f-title">当前经营<font color="red">*</font></span>
                <span class="f-con"><input type="text"  name=currentManagement  id="currentManagement"></span>
            </li>
            <li class="clearfix">
                <span class="f-title">出让类型<font color="red">*</font></span>
                <span class="f-con">
                    <form:select path="sellType">
						<form:options items="${fns:getDictList('shop_sell_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
                </span>
            </li>
            <li class="clearfix" id="transferFeeLi">
                <span class="f-title">转让费</span>
                <span class="f-con"><input type="text" name="transferFee" id="transferFee"></span>
                <span>万元</span>
            </li>
            <li class="clearfix">
                <span class="f-title">推荐人</span>
                <span class="f-con"><input type="text" name="referrer" id="referrer"></span>
            </li>
             <li class="clearfix">
                <span class="f-title">备注</span>
                <span class="f-con"><input type="text" name="remarks" id="remarks"></span>
            </li>
            <li class="clearfix">
                <span class="f-title" style="text-align: right;line-height: 0.5rem">上传照片<br><em style="font-size: 0.31rem;color: #999;word-break:break-all;text-align: left;display:inline-block;width:3.1rem;">
                    	请至少上传三张图片，图片只能以 bmp、jpeg、jpg、gif 格式上传,图片大小2M以内</em></span>
                <span class="f-con" style="width: 5.8rem">
                    <img src="${ctxStatic}/web/images/sc-img.png" class="yr-form-list uploadImg" id="image1">
                    <img src="${ctxStatic}/web/images/sc-img.png" class="yr-form-list uploadImg" id="image2">
                    <img src="${ctxStatic}/web/images/sc-img.png" class="yr-form-list uploadImg" id="image3">
                    <img src="${ctxStatic}/web/images/sc-img.png" class="yr-form-list uploadImg" id="image4">
                    <img src="${ctxStatic}/web/images/sc-img.png" class="yr-form-list uploadImg" id="image5">
                    <img src="${ctxStatic}/web/images/sc-img.png" class="yr-form-list uploadImg" id="image6">
                </span>
            </li>
            <li class="clearfix">
                <span class="f-title" id="goldApplication" style="text-align:left">
                  <img src="${ctxStatic}/web/images/no-dianji.png" alt="" id="goldImg">预约成为金牌店铺
                </span>
                <span class="f-con">
                    预约成为金牌店铺，Hi铺会尽快与您联系，一对一专人服务，拥有更多展示机会，享60天内急速转铺！
                </span>
            </li>
        </ul>
        <button type="button" class="btn-orange btn-orange-big" onclick="submitClick();">立即提交</button>
    </div>
    </form:form>
</div>
<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>


<div class="popup-warp" id="phonePopup">
    <div class="popup-box">
        <h2>本号码已发布店铺，是否继续?</h2>
        <p class="clearfix">
            <a href="javascript:void(0);" class="popup-btn fl" onclick="submitForm()">是</a>
            <a href="javascript:void(0);" class="popup-btn fr" onclick="shopIndex()">否</a>
        </p>
    </div>
</div>

</body>


<script>
	$(function() {
		initWx();
		
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-release').addClass('footer-act');	
		
		var a = true;
		$('#goldApplication').on('click',function() {
			if(a) {
				$('#goldImg').attr('src','${ctxStatic}/web/images/dianji.png');
				a=false;
				$('#goldFlag').val('1');
			} else {
				$('#goldImg').attr('src','${ctxStatic}/web/images/no-dianji.png');
				a=true;
				$('#goldFlag').val('0');
			}
		});
		
		
		$('#sellType').change(function() { 
			if($(this).val()=='1') {
				$('#transferFeeLi').show();
			} else { 
				$('#transferFee').val('');
				$('#transferFeeLi').hide();
			}
		}); 
		
		$('#shopType').change(function() {
			if($(this).val()=='10') {
				$('#shoppingMallLi').show();
				$('#shoppingMall option:nth-child(1)').attr("selected" , true);  
			}  else {
				$('#shoppingMallLi').hide();
				$('#shoppingMall').val('');
			}
		});
		
		$('#areaSpan').find('select').change(function() {
			selectShoppingMall($(this).val());
			selectTown($(this).val());
		});
		
	});
	
	function selectShoppingMall(areaId) {
		$.ajax({
			  url: "${ctx}/wx/shop/getShoppingMallByArea",
	  	      type: "post",
	  	      dataType:'json',  
	  	      async:false,
	  	      data:{areaId:areaId},
	  	      success: function(data) {
	  	    	 $('#shoppingMall').val('');
	  	       	 $('#shoppingMall').html('');
	  	         
	  	         $.each(data,function(index,val){
	  	        	$('#shoppingMall').append('<option value="'+val.id+'">'+val.mallName+'</option>');
	  	         });
	  	    
	  	      }
		});
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
	
	
	function initWx() {
		$.ajax({
			url : "${ctx}/wx/wxcommon/shareApi",
			type : "post",
			data : {
				"url" : location.origin + location.pathname + location.search
			},
			success : function(data) {
				wx.config({
					debug : true,
					appId : data.appid,
					timestamp : data.timestamp,
					nonceStr : data.nonceStr,
					signature : data.signature,
					jsApiList : [ 'chooseImage','uploadImage']
				});

				wx.ready(function() {
					$('.uploadImg').on('click', function() {
						var dom = $(this);
						wx.chooseImage({
							success : function(res) {
								var localIds = res.localIds;
								if(localIds.length>1) {
									layer.msg("仅需上传一张外观图");
								} else {
									uploadToWx(localIds[0],dom);
								}
							}
						});
					});
				});
				
				wx.error(function(res){
				   // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
					layer.alert(JSON.stringify(res));
				});
			}
		});
	}
	
	function uploadToWx(localId,dom) {
		wx.uploadImage({
			localId:localId,
			isShowProgressTips: 1,
			success: function (res) {
			   var serverId = res.serverId; // 返回图片的服务器端ID
			   uploadToServer(serverId,dom);
			}
		});
	}
	
	function uploadToServer(serverId,dom) {
		$.ajax({
			url : "${ctx}/wx/wxcommon/uploadMedia",
			type : "post",
			data : {"mediaId":serverId},
			success : function(data) {
				if(data.status=="1") {
					dom.attr('src',data.url);
					var domId = dom.attr('id');
					$('input[name="'+domId+'"]').val(data.url);
				} else {
					layer.msg("上传失败");
				}
			}
		});
	}
	
	
	function submitClick() {	
		if (!telRe.test($('#mobile').val())) {
			layer.msg("请输入正确的手机号");
		} else if($('#shopAddress').val()==''){
			layer.msg("店铺地址不能为空");
			console.log($('#town').val());
		} else if($('#town').val()==null || $('#town').val()==''){
			layer.msg("镇不能为空");
		}else if(!floatRe.test($('#useArea').val())) {
			layer.msg("请输入正确的使用面积");
		} else if(!floatRe.test($('#fee').val())) {
			layer.msg("请输入正确的月租金");
		} else if($('#currentManagement').val()==''){
			layer.msg("当前经营不能为空");
		} else if($('#sellType').val()=='1'&&!floatRe.test($('#transferFee').val())) {
			layer.msg("请输入正确的转让费");	
		}   else if($('input[name="image1"]').val()=='') {
			layer.msg("至少3张为必传项");	
		} else if($('input[name="image2"]').val()=='') {
			layer.msg("至少3张为必传项");	
		} else if($('input[name="image3"]').val()=='') {
			layer.msg("至少3张为必传项");	
		}  else {
			$.ajax({
				url: "${ctx}/wx/shop/checkMobile",
				type: "post",
				dataType: "text",
				data: {"mobile":$('#mobile').val()},
				success: function(data){	
					if(data=="success"){	
						submitForm();
					}else{
						$('#phonePopup').show();
					}
				}
			});
			
			
		}
	}
	
	function submitForm() {
		$("#inputForm").submit();
	}
	
	function shopIndex() {
		window.location.href = '${ctx}/wx/shop/shopIndex?mobile='+$('#mobile').val();
	}
	
</script>

</html>



