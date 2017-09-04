<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>shopping mall</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta name="applicable-device" content="mobile">
    <link rel="stylesheet" href="${ctxStatic}/web/css/style.css" media="screen">
    <script src="${ctxStatic}/web/js/auto.js"></script>
    <script src="${ctxStatic}/web/js/jquery-3.1.1.min.js"></script>
	<script src="${ctxStatic}/web/js/echarts.min.js"></script>
</head>

<body >
<form:form id="searchForm" modelAttribute="shop" action="${ctx}/wx/mall/mallInfo" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo+1}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="20"/>
	<input id="shoppingMall" name="shoppingMall" type="hidden" value="${shop.shoppingMall}">
	<input id="useAreaString" name="useAreaString" type="hidden" value="${shop.useAreaString}">
	<input id="feeString" name="feeString" type="hidden" value="${shop.feeString}">
	<input id="shopName" name="shopName" type="hidden" value="${shop.shopName}">
	<input id="hotString" name="hotString" type="hidden" value="${shop.hotString}">		
	<input id="shopTypeString" name="shopTypeString" type="hidden" value="${shop.shopTypeString}">
</form:form>

<div class="shop-mall-detail pb-105">
    <div class="yr-shop-mall">
        <h3>${shoppingMall.mallName}</h3>
        <div class="mall-con">
            <div class="mall-his " >
                <div class="chart-legend chart-legend1">
                    <ul>
                        <li>
                            <i class="bg-color1"></i>餐饮
                        </li>
                        <li>
                            <i class="bg-color2"></i>购物
                        </li>
                        <li>
                            <i class="bg-color3"></i>休闲娱乐
                        </li>
                        <li>
                            <i class="bg-color4"></i>教育培训
                        </li>
                    </ul>
                </div>
                <div id="mychart" class="mychart"></div>
                <div class="chart-title">
                    业态分布图
                </div>
            </div>
            <div class="mall-data " >
                <p>购物中心：${shoppingMall.shoppingArea}万㎡</p>
                <p>动态客流：${shoppingMall.dynamicFlow}万人</p>
                <p>静态客流：${shoppingMall.staticFlow}万人</p>
                <p>写&nbsp;&nbsp;字 楼：${shoppingMall.officeArea}万㎡</p>
            </div>
        </div>
    </div>
    <div class="popup-search " >        
        <nav class="screen clearfix" id="popupNav">
	        <div onclick="showPopup('useAreaPopup')" >
	            <a href="javascript:void(0);">面积</a>
	            <span class="sanjiao" id="useAreaPopupSpan"></span>
	        </div>
	        <div onclick="showPopup('feePopup')" >
	            <a href="javascript:void(0);">费用</a>
	            <span class="sanjiao" id="feePopupSpan"></span>
	        </div>
	        <div style="border: none" onclick="showPopup('morePopup')">
	            <a href="javascript:void(0);">筛选</a>
	            <span class="sanjiao" id="morePopupSpan"></span>
	        </div>
   	  	</nav>
        
       
        <div class="screen-lis clearfix popup-class" id="useAreaPopup" >
            <ul class="screen-area bg-white" style="width:100%">
                		<c:choose>
						   <c:when test="${shop.useAreaString==''}">  
						   		<li class="screen-area-ok" onclick="selectQuery('',this,'useArea')"><a href="javascript:void(0);"  class="t-screen">不限选择</a></li>
						   </c:when>
						   <c:otherwise>
						   		<li onclick="selectQuery('',this,'useArea')"><a href="javascript:void(0);" >不限选择</a></li>
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
        
        <div class="screen-lis clearfix popup-class" id="feePopup">
            <ul class="screen-area bg-white" style="width:100%">
                <c:choose>
						   <c:when test="${shop.feeString==''}">  
						   		<li class="screen-area-ok" onclick="selectQuery('',this,'fee')"><a href="javascript:void(0);"  class="t-screen">不限选择</a></li>
						   </c:when>
						   <c:otherwise>
                				<li onclick="selectQuery('',this,'fee')"><a href="javascript:void(0);" >不限选择</a></li>
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
        
        
        
        <div class="screen-con  bg-white popup-class" id="morePopup" style="display: none;overflow-y: scroll;">
            <div class="p-con" style="padding-bottom: 1rem;">
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
                <h3 style="padding-top: 0.21rem">商铺类型</h3>
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
           	<a href="javascript:void(0);"  style="position: absolute;bottom: 0;" onclick="moreQuery();">确定</a>
        </div>
    </div>

    <div class="shop-recommend p-lr-30 ">
    
        <ul class="shop-list">
            <c:forEach items="${shopPage.list}" var="shop">
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
                        <span class="fl">${shop.area.name}<c:if test="${not empty shop.street}">-${shop.street}</c:if></span>
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
		var h1=$(".yr-shop-mall").height();
        var h2=$(".screen").height();
        var h3= $(window).height();
        var h4=parseInt(h3-h1-h2-3);
        $(".screen-con").css("height",h4)
		
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-shop').addClass('footer-act');
		
		
		$(window).scroll(function () {
	            var top = $(window).scrollTop();
	            var mt= parseInt($(".yr-shop-mall").css('height'));
	            alert(mt);
	            if(top>mt){
	                $(".popup-search").addClass("head-top");
	                $(".shop-recommend").addClass('pt-72');
	            }else {
	                $(".popup-search").removeClass("head-top");
	                $(".shop-recommend").removeClass('pt-72');
	            }
	    });
	});
	
	function showPopup(popup) {
		var $div = $('#'+popup);

		$('.popup-class').each(function(index,obj) {
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
		$('#pageSize').val(20);
	
		$('#'+hiddenId+'String').val(value);
		
		$('#searchForm').submit();
	}
	
	function deleteQuery(hiddenId) {
		$('#pageNo').val(0);
		$('#pageSize').val(20);
		
		$('#'+hiddenId+'String').val('');
		$('#searchForm').submit();
	}
	
	function deleteQuery2(hiddenId,obj) {		
		$('#pageNo').val(0);
		$('#pageSize').val(20);
		
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
		$('#pageSize').val(20);
		$('#searchForm').submit();
	}
	
	function mallInfo() {
		window.location.href = '${ctx}/mall/mallInfo';
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

    var myChart = echarts.init($('#mychart')[0]);
    // 指定图表的配置项和数据
    var option = {
        title:{
            text:''
        },
        tooltip: {
            /*trigger: 'axis',
            //在这里设置
            formatter: '{c0}%'*/
            show:false
        },
        legend: {
            show:false
        },
        grid: {
            left: '-10px',
            right: '0',
            bottom: '-19px',
            x2:'0',
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data : ["","","",""],
                axisTick:{
                    show:false
                },
                axisLabel:{
                    interval:0
                },
                barGap:'5%'
            }
        ],
        yAxis:{
          show : false

        },
        series: [
            {
                name: '',
                type: 'bar',
                barWidth: '60%',
                data: [
                    {
                        value: '${shoppingMall.percent1}',
                        itemStyle: {
                            normal:{
                                label:{
                                    show:true,position:'top', formatter: '{c0}%',textStyle:{color:'#000',fontSize:8}
                                },
                                color:'#f8b551'
                            }
                        }
                    },
                    {
                        value: '${shoppingMall.percent2}',
                        itemStyle: {normal:{label:{show:true,position:'top',formatter: '{c0}%',textStyle:{color:'#000',fontSize:8}},color:'#ae5da1'}}
                    },
                    {
                        value: '${shoppingMall.percent3}',
                        itemStyle: {normal:{label:{show:true,position:'top',formatter: '{c0}%',textStyle:{color:'#000',fontSize:8}},color:'#e85559'}}
                    },
                    {
                        value: '${shoppingMall.percent4}',
                        itemStyle: {normal:{label:{show:true,position:'top',formatter: '{c0}%',textStyle:{color:'#000',fontSize:8}},color:'#89c997'}}
                    }
                ]
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>

</html>



