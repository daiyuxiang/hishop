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

<body class="h100">

<div class="pb-105" >
	<c:forEach items="${oneMallList}" var="shoppingMall">
    <a href="javascript:void(0);" onclick="mallInfo('${shoppingMall.id}')">
        <div class="yr-shop-mall" style="background-image: url(${ctxStatic}/web/images/mall-1.jpg);">
            <h3>${shoppingMall.mallName}</h3>
            <div class="mall-con">
                <div class="mall-his " >
                    <div class="chart-legend chart-legend1">
                        <ul>
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
                        </ul>
                    </div>
                    <div class="mychart"  data1="${shoppingMall.percent1}" data2="${shoppingMall.percent2}" data3="${shoppingMall.percent3}" data4="${shoppingMall.percent4}"></div>
                    <div class="chart-title">
                        业态分布图
                    </div>
                </div>
                <div class="mall-data " >
                	<p>百货/购物中心：${shoppingMall.shoppingArea}万㎡</p>
                    <p>动态客流：${shoppingMall.dynamicFlow}万人</p>
                    <p>静态客流：${shoppingMall.staticFlow}万人</p>
                    <p>写&nbsp;&nbsp;字 楼：${shoppingMall.officeArea}万㎡</p>
                </div>
            </div>
        </div>
    </a>
	</c:forEach>
</div>

<c:forEach items="${twoMallList}" var="two">
<div class="pb-105 mt--105">
	<c:forEach items="${two}" var="shoppingMall">
    <a href="javascript:void(0);" onclick="mallInfo('${shoppingMall.id}')">
        <div class="yr-shop-mall" style="background-image: url(${ctxStatic}/web/images/mall-1.jpg);">
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
	                        </li>                        </ul>
                    </div>
                    <div class="mychart" data1="${shoppingMall.percent1}" data2="${shoppingMall.percent2}" data3="${shoppingMall.percent3}" data4="${shoppingMall.percent4}"></div>
                    <div class="chart-title">
                        业态分布图
                    </div>
                </div>
                <div class="mall-data " >
	                <p>百货/购物中心：${shoppingMall.shoppingArea}万㎡</p>
	                <p>动态客流：${shoppingMall.dynamicFlow}万人</p>
	                <p>静态客流：${shoppingMall.staticFlow}万人</p>
	                <p>写&nbsp;&nbsp;字 楼：${shoppingMall.officeArea}万㎡</p>
	            </div>
            </div>


        </div>
    </a>
    </c:forEach>
</div>
</c:forEach>
<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>


</body>

<script>
	$(function() {
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-shop').addClass('footer-act');
		

		var $lastDiv = $('.mt--105:last');
		
		if($lastDiv.find('a').length==1) {
			$lastDiv.addClass("height55");
			$lastDiv.find('.yr-shop-mall').addClass("height100");
		}		
		
		$.each($('.mychart'),function(index,obj) {
			var percent1 = $(obj).attr('data1');
			var percent2 = $(obj).attr('data2');
			var percent3 = $(obj).attr('data3');
			var percent4 = $(obj).attr('data4');
			
			var myChart = echarts.init(obj);
			
			 // 指定图表的配置项和数据
		    var option = {
		        title:{
		            text:''
		        },
		        tooltip: {
		            show:false
		            /*trigger: 'axis',
		            //在这里设置
		            formatter: '{c0}%'*/
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
		                        value: percent1,
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
		                        value: percent2,
		                        itemStyle: {normal:{label:{show:true,position:'top',formatter: '{c0}%',textStyle:{color:'#000',fontSize:8}},color:'#ae5da1'}}
		                    },
		                    {
		                        value: percent3,
		                        itemStyle: {normal:{label:{show:true,position:'top',formatter: '{c0}%',textStyle:{color:'#000',fontSize:8}},color:'#e85559'}}
		                    },
		                    {
		                        value: percent4,
		                        itemStyle: {normal:{label:{show:true,position:'top',formatter: '{c0}%',textStyle:{color:'#000',fontSize:8}},color:'#89c997'}}
		                    }
		                ]
		            }
		        ]
		    };
				    
		    // 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
		});
		
		
	});
	
	function mallInfo(id) {
		window.location.href = '${ctx}/wx/mall/mallInfo?shoppingMall='+id;
	}

   

  
   
</script>
</body>
</html>



