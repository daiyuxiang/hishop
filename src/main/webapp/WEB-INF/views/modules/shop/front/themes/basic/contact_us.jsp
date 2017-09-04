<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Hi铺</title>
    <link rel="stylesheet" href="${ctxStatic}/website/css/main.css">
    <style>

	</style>
</head>
<body>
	<div class="header">
		<div class="container">
			<div class="logo"></div>
			<a href="${pageContext.request.contextPath}/contact_us" class="active">联系我们</a>
			<a href="${pageContext.request.contextPath}/" style="margin-right: 60px;">首页</a>
		</div>
	</div>
	<div class="contact-body">
		<div class="contact-content container">
			<div class="about-us">
				<div class="about-us-content">
					<div class="about-title">
						<div class="left-border-btn"></div>
						<div class="about-title-text">
							<h2>关于我们</h2>
							<span>About us</span>
						</div>
						<div class="right-border-btn"></div>
					</div>
					<div class="about-text">
						<p>上海市亦寻信息技术有限公司成立于2014年5月,总部在上海。公司立志于做一站式、全流程店铺经营服务商，从选址、推荐项目、到店铺转让的全流程经营服务。</p>
						<br />
						<p>上海市亦寻信息技术有限公司旗下的“Hi铺”是致力于商业地产移动互联网+的实践者,基于大数据精准匹配找铺、转铺需求,实现转让方与找铺方无缝对接,坚持"让开店更简单"的理念,为房东、商家搭建高效可信赖的商铺交易服务平台。希望通过我们的专业水平与不懈努力，为广大商家提供更便捷、高效的商铺地产流转服务</p>
					</div>
				</div>
			</div>
			<div class="contact-us">
				<div class="contact-left">
					<div class="contact-title">
						<div class="left-border-btn"></div>
						<div class="contact-title-text">
							<h2>联系我们</h2>
							<span>Contact us</span>
						</div>
						<div class="right-border-btn"></div>
					</div>
					<p>电话：021-8344870</p>
					<p>邮箱：hipu@f2service.cn</p>
					<p>地址：上海市浦东新区碧波路456号2楼Vπ中心</p>
					<p style="text-indent: 3em;">上海市浦东新区张江郭守敬路498号10号楼301室</p>				
				</div>
				<div class="contact-right">
					<div id="wrap" class="my-map">
						<div id="mapContainer"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script src="http://webapi.amap.com/maps?v=1.3&key=8325164e247e15eea68b59e89200988b"></script>
	<script>
	!function(){
		var infoWindow, map, level = 16,
			center = {lng: 121.597001, lat: 31.209187},
			features = [{type: "Marker", name: "上海市浦东新区碧波路456号2楼Vπ中心", desc: "上海市浦东新区碧波路456号2楼Vπ中心", color: "red", icon: "cir", offset: {x: -9, y: -31}, lnglat: {lng: 121.582105, lat: 31.204125}},
				{type: "Marker", name: "上海市浦东新区张江郭守敬路498号10号楼301室", desc: "上海市浦东新区张江郭守敬路498号10号楼301室", color: "red", icon: "cir", offset: {x: -9, y: -31}, lnglat: {lng: 121.599158, lat: 31.209325}}];

		function loadFeatures(){
			for(var feature, data, i = 0, len = features.length, j, jl, path; i < len; i++){
				data = features[i];
				switch(data.type){
					case "Marker":
						feature = new AMap.Marker({ map: map, position: new AMap.LngLat(data.lnglat.lng, data.lnglat.lat),
							zIndex: 3, extData: data, offset: new AMap.Pixel(data.offset.x, data.offset.y), title: data.name,
							content: '<div class="icon icon-' + data.icon + ' icon-'+ data.icon +'-' + data.color +'"></div>' });
						break;
					case "Polyline":
						for(j = 0, jl = data.lnglat.length, path = []; j < jl; j++){
							path.push(new AMap.LngLat(data.lnglat[j].lng, data.lnglat[j].lat));
						}
						feature = new AMap.Polyline({ map: map, path: path, extData: data, zIndex: 2,
							strokeWeight: data.strokeWeight, strokeColor: data.strokeColor, strokeOpacity: data.strokeOpacity });
						break;
					case "Polygon":
						for(j = 0, jl = data.lnglat.length, path = []; j < jl; j++){
							path.push(new AMap.LngLat(data.lnglat[j].lng, data.lnglat[j].lat));
						}
						feature = new AMap.Polygon({ map: map, path: path, extData: data, zIndex: 1,
							strokeWeight: data.strokeWeight, strokeColor: data.strokeColor, strokeOpacity: data.strokeOpacity,
							fillColor: data.fillColor, fillOpacity: data.fillOpacity });
						break;
					default: feature = null;
				}
				if(feature){ AMap.event.addListener(feature, "click", mapFeatureClick); }
			}
		}

		function mapFeatureClick(e){
			if(!infoWindow){ infoWindow = new AMap.InfoWindow({autoMove: true}); }
			var extData = e.target.getExtData();
			infoWindow.setContent("<h5>" + extData.name + "</h5><div>" + extData.desc + "</div>");
			infoWindow.open(map, e.lnglat);
		}

		map = new AMap.Map("mapContainer", {center: new AMap.LngLat(center.lng, center.lat), level: level});
		
		loadFeatures();

		map.on('complete', function(){
			map.plugin(["AMap.ToolBar"], function(){
				map.addControl(new AMap.ToolBar);
			});	
		})
		
	}();
	</script>
</body>
</html>