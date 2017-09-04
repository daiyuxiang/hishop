<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>转铺成功</title>
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

<body>
   	<form:form id="goldForm" modelAttribute="shop" action="${ctx}/wx/shop/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="goldFlag" value="1">
	</form:form>

<div class="pb-105">
	<c:if test="${shop.goldFlag=='0'}">
	    <div class="fb-hd fb-hd1 fb-hd2">
	        <h1 class="h1 text-white">发布成功！</h1>
	        <p class="text-white">但为了您能更快转让，建议您完善更多信息</p>
	        <a href="${ctx}/wx/shop/saleMoreForm?id=${shop.id}" class="btn-white btn-white2">进一步完善信息</a>
	        <a href="${ctx}/wx/shop/shopIndex" class="btn-b">算了，再看看>></a>
	    </div>
    </c:if>
   <div class="xhi-report bt-7 p-lr-30">
        <div class="text-cen">
        <c:if test="${shop.goldFlag=='0'}">
       		<a href="javascript:void(0);" class="btn-orange btn-c" onclick="setApplicationFlag()">急速转铺！立即预约成为金牌店铺</a>
        </c:if>
      	<c:if test="${shop.goldFlag=='1'}">
        	<a href="javascript:void(0);" class="btn-orange onclick-cen" style="background:none;">
            <img src="${ctxStatic}/web/images/popup-ok.jpg" alt="">恭喜你成功预约成为金牌店铺<br>
            小Hi将尽快联系您，助您火速转让店铺！</a>
        </c:if>
        <p>快速、省心、优先</p>
        </div>
    </div>
    <div class="fb-chart  p-lr-30 bt-7 clearfix">
        <div class="chart-legend">
            <ul>
                <li>
                    <i class="bg-color5"></i>金牌店铺                
                </li>
                <li>
                    <i class="bg-color6"></i>普通店铺
                </li>
            </ul>
        </div>
        <div class="chart-box">
            <div id="mychart1" class="mychart1"></div>
            <div class="c-title">店铺浏览数量</div>
        </div>
        <div class="chart-box">
            <div id="mychart2" class="mychart1"></div>
            <div class="c-title">店铺成交数量</div>
        </div>
        <div class="chart-box">
            <div id="mychart3" class="mychart1"></div>
            <div class="c-title">店铺收藏数量</div>
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
	
	function setApplicationFlag() {
		$('#goldForm').submit();
		return false;
	}
	
	
	var myChart1 = echarts.init(document.getElementById('mychart1'));
    var myChart2 = echarts.init(document.getElementById('mychart2'));
    var myChart3 = echarts.init(document.getElementById('mychart3'));
    // 指定图表的配置项和数据
    var option1 = {
        title:{
            text:''
        },
        tooltip: {
            show:false
        },
        legend: {
            show:false
        },
        grid: {
            left: '-20px',
            right: '0',
            bottom: '-10px',
            top:'20px',
            x:0,
            y:0,
            x2:0,
            y2:0,
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data : ["",""],
                axisTick:{
                    show:false
                },
                axisLabel:{
                    interval:0
                },
                barGap:'5%',
                borderColor:'#999999',
                textStyle:{
                    borderColor:'#999999'
                }
            }
        ],

        yAxis:{
            show : false
        },
        calculable:false,
        series: [
            {
                name: '',
                type: 'bar',
                barWidth: '70%',
                data: [
                    {
                        value: 40,
                        itemStyle: {
                            normal:{
                                color:'#f8b551'
                            }
                        }
                    },
                    {
                        value: 20,
                        itemStyle: {normal:{color:'#e85559'}}
                    }
                ]
            }
        ]
    };
    var option2 = {
        title:{
            text:''
        },
        tooltip: {
            show:false
        },
        legend: {
            show:false
        },
        grid: {
            left: '-20px',
            right: '0',
            bottom: '-10px',
            top:'20px',
            x:0,
            y:0,
            x2:0,
            y2:0,
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data : ["",""],
                axisTick:{
                    show:false
                },
                axisLabel:{
                    interval:0
                },
                barGap:'5%',
                borderColor:'#999999',
                textStyle:{
                    borderColor:'#999999'
                }
            }
        ],

        yAxis:{
            show : false
        },
        calculable:false,
        series: [
            {
                name: '',
                type: 'bar',
                barWidth: '70%',
                data: [
                    {
                        value: 40,
                        itemStyle: {
                            normal:{
                                color:'#f8b551'
                            }
                        }
                    },
                    {
                        value: 20,
                        itemStyle: {normal:{color:'#e85559'}}
                    }
                ]
            }
        ]
    };
    var option3 = {
        title:{
            text:''
        },
        tooltip: {
            show:false
        },
        legend: {
            show:false
        },
        grid: {
            left: '-20px',
            right: '0',
            bottom: '-10px',
            top:'20px',
            x:0,
            y:0,
            x2:0,
            y2:0,
            containLabel: true
        },
        xAxis: [
            {
                type: 'category',
                data : ["",""],
                axisTick:{
                    show:false
                },
                axisLabel:{
                    interval:0
                },
                barGap:'5%',
                borderColor:'#999999',
                textStyle:{
                    borderColor:'#999999'
                }
            }
        ],

        yAxis:{
            show : false
        },
        calculable:false,
        series: [
            {
                name: '',
                type: 'bar',
                barWidth: '70%',
                data: [
                    {
                        value: 40,
                        itemStyle: {
                            normal:{
                                color:'#f8b551'
                            }
                        }
                    },
                    {
                        value: 20,
                        itemStyle: {normal:{color:'#e85559'}}
                    }
                ]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option1);
    myChart2.setOption(option2);
    myChart3.setOption(option3);
</script>

</html>



