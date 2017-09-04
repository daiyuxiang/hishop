<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>商铺列表</title>
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

<body id="popup-lis">

<form:form id="searchForm" modelAttribute="shop" action="${ctx}/wx/shop/shopIndex" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo+1}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="12"/>
	<input id="shopName" name="shopName" type="hidden" value="${shop.shopName}">
	<input id="areaString" name="area.id" type="hidden" value="${shop.area.id}">
	<input id="areaLabel" name="area.name" type="hidden" value="${shop.area.name}">
	<input id="useAreaString" name="useAreaString" type="hidden" value="${shop.useAreaString}">
	<input id="feeString" name="feeString" type="hidden" value="${shop.feeString}">
	<input id="hotString" name="hotString" type="hidden" value="${shop.hotString}">		
	<input id="shopTypeString" name="shopTypeString" type="hidden" value="${shop.shopTypeString}">		
</form:form>
<div class="popup-search">
    <header class="search">
        <input type="text" placeholder="关键字" id="keyWord" value="${shop.shopName}">
        <img src="${ctxStatic}/web/images/search-f.png" class="search-f" onclick="keyWordQuery();">
        <div class="search-area fr">
            <a href="${ctx}/wx/mall/mallIndex">
                <img src="${ctxStatic}/web/images/search-icon.png" alt="" class="search-icon">
                <p>上海</p>
            </a>
        </div>
    </header>
    <nav class="screen clearfix" id="popupNav">
        <div onclick="showPopup('areaPopup');" >
            <a href="javascript:void(0);">区域</a>
            <span class="sanjiao" id="areaPopupSpan"></span>
        </div>
        <div onclick="showPopup('useAreaPopup');" >
            <a href="javascript:void(0);">面积</a>
            <span class="sanjiao" id="useAreaPopupSpan"></span>
        </div>
        <div onclick="showPopup('feePopup');" >
            <a href="javascript:void(0);">费用</a>
            <span class="sanjiao" id="feePopupSpan"></span>
        </div>
        <div style="border: none" onclick="showPopup('morePopup');">
            <a href="javascript:void(0);">筛选</a>
            <span class="sanjiao" id="morePopupSpan"></span>
        </div>
    </nav>
</div>
<div class="shop-recommend1">
     <!-- 	<div class="popup-tel" style="display: none" id="areaPopup">
        <div class="screen-p">
            <div class="screen-lis clearfix" style="display:block;top: 2.3rem;">
              <ul class="screen-area bg-white">
                    <li><a href="javascript:void(0);">不限选择</a></li>   
                    <c:forEach items="${areaList}" var="area" >
                    	 <li><a href="javascript:void(0);" onclick="selectArea('${area.id}',this)">${area.name}</a></li>
                    </c:forEach>                    
                </ul>
                <ul class="screen-area-black">
                    <li><a href="javascript:void(0);"></a></li>
                </ul>
				
            </div>
        </div>
    </div> -->
     
     <div class="popup-tel" style="display: none" id="areaPopup">
        <div class="screen-p">
            <div class="screen-lis2 bg-white" style="display:block;top: 2.3rem;">
              	<ul>
              			<c:choose>
						   <c:when test="${shop.area.id==''}">  
						   		<li class="screen-area-ok" onclick="selectQuery('',this,'area')"><a href="javascript:void(0);"  class="t-screen">不限选择</a></li>
						   </c:when>
						   <c:otherwise>
						   		<li onclick="selectQuery('',this,'area')"><a href="javascript:void(0);">不限选择</a></li>
						   </c:otherwise>
						</c:choose>
                    <c:forEach items="${areaList}" var="area" >
                    	<c:choose>
						   <c:when test="${shop.area.id==area.id}">  
						   		<li class="screen-area-ok" onclick="selectQuery('${area.id}',this,'area')"><a href="javascript:void(0);"  class="t-screen">${area.name}</a></li>
						   </c:when>
						   <c:otherwise>
						   		<li onclick="selectQuery('${area.id}',this,'area')"><a href="javascript:void(0);">${area.name}</a></li>
						   </c:otherwise>
						</c:choose>
                    </c:forEach>                    
                </ul>
            </div>
        </div>
    </div> 
     
    <div class="popup-tel" style="display:none" id="useAreaPopup">
        <div class="screen-p">
            <div class="screen-lis2 bg-white">
                <ul>
                		<c:choose>
						   <c:when test="${shop.useAreaString==''}">  
						   		<li class="screen-area-ok" onclick="selectQuery('',this,'useArea')"><a href="javascript:void(0);" class="t-screen">不限选择</a></li>
						   </c:when>
						   <c:otherwise>
						   		<li onclick="selectQuery('',this,'useArea')"><a href="javascript:void(0);">不限选择</a></li>
						   </c:otherwise>
						</c:choose>
                    <c:forEach items="${fns:getDictList('shop_area')}" var="useArea" >
						<c:choose>
						   <c:when test="${shop.useAreaString==useArea.value}">  
						   		<li class="screen-area-ok" onclick="selectQuery('${useArea.value}',this,'useArea')"><a href="javascript:void(0);"  class="t-screen">${useArea.label}</a></li>
						   </c:when>
						   <c:otherwise>
						   		<li onclick="selectQuery('${useArea.value}',this,'useArea')"><a href="javascript:void(0);" >${useArea.label}</a></li>
						   </c:otherwise>
						</c:choose>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
    
    <div class="popup-tel" style="display:none" id="feePopup">
        <div class="screen-p">
            <div class="screen-lis2 bg-white">
                <ul>
                	<c:choose>
						   <c:when test="${shop.feeString==''}">  
						   		<li class="screen-area-ok" onclick="selectQuery('',this,'fee')"><a href="javascript:void(0);"  class="t-screen">不限选择</a></li>
						   </c:when>
						   <c:otherwise>
                				<li onclick="selectQuery('',this,'fee')"><a href="javascript:void(0);">不限选择</a></li>
						   </c:otherwise>
					</c:choose>
                    <c:forEach items="${fns:getDictList('shop_fee')}" var="fee" >
                    	<c:choose>
						   <c:when test="${shop.feeString==fee.value}">  
						   		<li class="screen-area-ok" onclick="selectQuery('${fee.value}',this,'fee')"><a href="javascript:void(0);"  class="t-screen">${fee.label}</a></li>
						   </c:when>
						   <c:otherwise>
						   		<li onclick="selectQuery('${fee.value}',this,'fee')"><a href="javascript:void(0);" >${fee.label}</a></li>
						   </c:otherwise>
						</c:choose>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
     
    <div class="popup-tel" style="display:none" id="morePopup">
        <div class="screen-p">
            <div class="screen-con  bg-white">
                <div class="p-con">
                    <h3>推荐商铺</h3>
                    <p class="clearfix" id="hotSpan">
                    	<c:forEach items="${fns:getDictList('shop_hot')}" var="hot" >
                    	<c:choose>	
                    		<c:when test="${fn:contains(shop.hotString,hot.label)}">
						    	<span data="${hot.label}" onclick="setSelect(this)" class="select-span">${hot.label}</span>
						    </c:when>
						    <c:otherwise>
						    	<span data="${hot.label}" onclick="setSelect(this)">${hot.label}</span>
						    </c:otherwise>
						</c:choose>
						</c:forEach>
                    </p>
                    <h3>商铺类型</h3>
                    <p class="clearfix" id="shopTypeSpan">
                    	<c:forEach items="${fns:getDictList('shop_shop_type')}" var="shopType" >
                    	<c:choose>		
                    		<c:when test="${fn:contains(shop.shopTypeString,shopType.value)}">
                    			<span data="${shopType.value}" onclick="setSelect(this)" class="select-span">${shopType.label}</span>
                    		</c:when>
                    		<c:otherwise>
                    		   	<span data="${shopType.value}" onclick="setSelect(this)">${shopType.label}</span>
                    		</c:otherwise>
                    	</c:choose>
                    	</c:forEach>
                    </p>
                </div>
                <a href="javascript:void(0);" onclick="moreQuery();">确定</a>
            </div>

        </div>
    </div>	
    
    <div class="xuanze">
	    <div class="screen-select p-lr-30" id="screen-select">
	    	<c:if test="${ not empty shop.area.id}"><span onclick="deleteQuery('area')">${shop.area.name}x</span></c:if>
	        <c:if test="${ not empty shop.useAreaString}"><span onclick="deleteQuery('useArea')">${fns:getDictLabel(shop.useAreaString,'shop_area','')}x</span></c:if>
	        <c:if test="${ not empty shop.feeString}"><span onclick="deleteQuery('fee')">${fns:getDictLabel(shop.feeString,'shop_fee','')}x</span></c:if>
	       	<c:if test="${ not empty shop.hotArray}">
	       		<c:forEach items="${shop.hotArray}" var="hot">
	       			<span onclick="deleteQuery2('hot',this)" data="${hot}">${hot}x</span>
	       		</c:forEach>
	       	</c:if>
	       		<c:if test="${ not empty shop.shopTypeArray}">
	       		<c:forEach items="${shop.shopTypeArray}" var="shopType">
	       			<span onclick="deleteQuery2('shopType',this)" data="${shopType}">${fns:getDictLabel(shopType,'shop_shop_type','')}x</span>
	       		</c:forEach>
	       	</c:if>
	    </div>
	</div>
</div>
<div id="wrapper">
<div class=" p-lr-30 pb-105" id="scroller">
    <ul class="shop-list">
   	<c:forEach items="${page.list}" var="shop">
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
                    <span class="fl">&nbsp;&nbsp;${shop.updateDateStr}</span>
                    <span class="fr">${shop.fee}万元/月</span>
                </div>
            </div>
        </li>
     </c:forEach>
     
     	<c:if test="${moreFlag==true}">
			<li class="clearfix" id="pull_icon_li">
	            <a href="javascript:void(0);" style="text-align: center;display:block" onclick="loadMore(${page.pageNo+1},12,'');" id="pull_icon">加载更多</a>
	        </li>
        </c:if>
    </ul>
    
</div>
</div>

<div class="top-warp">
    <img src="${ctxStatic}/web/images/top2.png" alt="" class="top-icon" onclick="goTop()">
</div>

<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>

<script>
	
	var tempLi = '<li class="clearfix">'+
					'<div class="shop-logo fl @goldFlag">'+
					    '<a href="@infoUrl"><img src="@image1"></a>'+
						'<div class="shop-info fr">'+  
	    					'<a href="@infoUrl" class="shop-name">@shopName</a>'+
	    					'<div class="shop-flag">'+
	    						'@hotSpan'+
	    						'<span class="flag3">@useArea㎡</span>'+
	    						'<a href="javascript:void(0);" class="collect collect-y fr" onclick="hobby('+'\'@id\''+',this);"><i @hobbyFlag></i>收藏</a>'+
	    				'</div>'+
	    				'<div class="shop-price clearfix">'+
	    				 	'<span class="fl">@areaName@townName</span>'+
	    				 	'<span class="fl">&nbsp;&nbsp;@updateDateStr</span>'+ 
	    				 	'<span class="fr">@fee万元/月</span>'+
	    				'</div>'+
	    			'</div>'+
	    		 '</li>';
	    		 
	$(function() {
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-shop').addClass('footer-act');
		
		if($('.xuanze').find('div').html().trim()=='') {
			$('.xuanze').hide();
		} else {
			$('.xuanze').show();
		}
		
		var clientHeight = $('#popup-lis').height();
		console.log(clientHeight);
		
		
	    $(window).scroll(function(event){
	    	var offsetHeight = $('#scroller').height();
	    	
	    	if($(window).scrollTop()>=offsetHeight-clientHeight) {
				
	    		if ($('#pull_icon').hasClass('flip')) {
	    			
	    		} else {
	    			$('#pull_icon').addClass('flip');
	    			$('#pull_icon').text('加载中...');
					pullUpAction();
	    		}
	    		
			
	    	}	    	
	    	//787
	    });
		
		
	
		/*
		if(${page.pageNo}>1) {
			var height = $('.shop-list').find('li').eq(0).outerHeight()*12;
			$("html,body").animate({scrollTop:height}, 0);
		}
		*/
		
		/*
		var myscroll = new iScroll("wrapper",{
			onScrollMove:function(){
				//layer.alert("init==="+this.y+"==="+this.maxScrollY);
				if (this.y<(this.maxScrollY)) {
					$('#pull_icon').addClass('flip');
					$('#pull_icon').removeClass('loading');
					$('#pull_icon').text('释放加载...');
				}else{
					
					$('#pull_icon').removeClass('flip loading');
					$('#pull_icon').text('加载更多')
				}
			},
			onScrollEnd:function(){
				if ($('#pull_icon').hasClass('flip')) {
					$('#pull_icon').addClass('loading');
					$('#pull_icon').text('加载中...');
					pullUpAction();
				}
				
			},
			onRefresh:function(){
				$('#pull_icon').removeClass('flip');
			}
			
		});
		*/
		function refresh() {
			$('#pull_icon').removeClass('flip');
		}
				
		function pullUpAction(){
			var data = $('#searchForm').serialize();
			
			setTimeout(function(){
				$.ajax({
					url:'${ctx}/wx/shop/moreShop',
					type:'post',
					data: data,
					dataType:'json',
					success:function(data){		
						$('#pageNo').val(data.pageNo+1);
						
						$.each(data.list,function(index,obj) {
							var temp = tempLi;
							
							if(obj.goldFlag=='1') {
								temp = temp.replace(/@goldFlag/g,'gold-shop');
							} else {
								temp = temp.replace(/@goldFlag/g,'');
							}
							if(obj.hobbyFlag=='1') {
								temp = temp.replace(/@hobbyFlag/g,'class="addHobby"');
							} else {
								temp = temp.replace(/@hobbyFlag/g,'');
							}
							
							if(obj.hotList!=null) {
								var hotTemp = "";
								
								for(var i=0;i<obj.hotList.length;i++) {
									var hot = obj.hotList[i];
									hotTemp += '<span class="button flag1">'+hot.hotValue+'</span> ';
								}
								
								temp = temp.replace(/@hotSpan/g,hotTemp);
							} else {
								temp = temp.replace(/@hotSpan/g,'');
							}
							
														
							var url = '${ctx}/wx/shop/shopInfo?id='+obj.id;
								 
							temp = temp.replace(/@infoUrl/g,url);
							temp = temp.replace(/@image1/g,obj.image1);
							temp = temp.replace(/@shopName/g,obj.shopName);
							temp = temp.replace(/@useArea/g,obj.useArea);
							temp = temp.replace(/@id/g,obj.id);
							temp = temp.replace(/@areaName/g,obj.area.name);
							temp = temp.replace(/@townName/g,obj.town.name);
							temp = temp.replace(/@updateDateStr/g,obj.updateDateStr);
							temp = temp.replace(/@fee/g,obj.fee);
							
							$('#pull_icon_li').before(temp);
						});
						
						//myscroll.refresh();
						refresh();
						
						if(data.count <= data.pageNo*12) {
							$('#pull_icon_li').remove();
						} else {
							$('#pull_icon').text('加载更多');
						}
						
						
					},
					error:function(){
					},
				});
			}, 1000);
		}
		
	});
	
	function showPopup(popup) {
		var $div = $('#'+popup);

		$('.popup-tel').each(function(index,obj) {
			if($(obj).attr('id')!==popup) {
				$(obj).hide();
			}
		});
		
		$('#popupNav').find('span').each(function(index,obj) {
			$(obj).removeClass('sanjiao-ok');
		});
		
		if($div.is(':visible')) {
			$div.hide();
			$('#'+popup+'Span').removeClass('sanjiao-ok');
			$('#popup-lis').attr('style',"backgroundColor:rgba(0,0,0,0)");
		} else {
			$div.show();
			$('#'+popup+'Span').addClass('sanjiao-ok');
			$('#popup-lis').attr('style',"backgroundColor:rgba(0,0,0,0.2)");
		}
	}
	
	function selectQuery(value,obj,hiddenId) {
		var $ul = $(obj).parent().parent();
		$ul.find('li').removeClass('screen-area-ok');
		$ul.find('a').removeClass('t-screen');
		
		$(obj).parent().addClass('screen-area-ok');
		$(obj).addClass('t-screen');
		
		$('#pageNo').val(0);
		$('#pageSize').val(12);
	
		$('#'+hiddenId+'String').val(value);
		if(hiddenId=='area') {
			$('#'+hiddenId+'Label').val($(obj).text());
		}
		
		$('#searchForm').submit();
	}
	
	function deleteQuery(hiddenId) {
		$('#pageNo').val(0);
		$('#pageSize').val(12);
		
		$('#'+hiddenId+'String').val('');
		$('#searchForm').submit();
	}
	
	function deleteQuery2(hiddenId,obj) {		
		$('#pageNo').val(0);
		$('#pageSize').val(12);
		
		var value = $(obj).attr('data');
		
		var array =  new Array();
		var count = 0;
		$.each($('#'+hiddenId+'Span').find('.select-span'),function(index,obj) {
			if(!($(obj).attr('data')==value)) {
				array[count] = $(obj).attr('data');
				count++;
			}
		});
		$('#'+hiddenId+'String').val(array);
		
		$('#searchForm').submit();
	}
		
	function keyWordQuery() {
		var keyWord = $('#keyWord').val();
		
		$('#pageNo').val(0);
		$('#pageSize').val(12);
		$('#shopName').val(keyWord);
		$('#searchForm').submit();
	}
	
	function setSelect(obj) {
		if($(obj).hasClass('select-span')) {
			$(obj).removeClass('select-span');
		} else {
			$(obj).addClass('select-span');
		}
	}
	
	function moreQuery() {
		var hotArray =  new Array();
		$.each($('#hotSpan').find('.select-span'),function(index,obj) {
			hotArray[index] = $(obj).attr('data');
		});
		$('#hotString').val(hotArray);

		var shopTypeArray =  new Array();
		$.each($('#shopTypeSpan').find('.select-span'),function(index,obj) {
			shopTypeArray[index] = $(obj).attr('data');
		});
		$('#shopTypeString').val(shopTypeArray);
		
		$('#pageNo').val(0);
		$('#pageSize').val(12);
		$('#searchForm').submit();
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
	
	function goTop() {
		$("html,body").animate({scrollTop:0}, 0);
	}
</script>
</body>
</html>



