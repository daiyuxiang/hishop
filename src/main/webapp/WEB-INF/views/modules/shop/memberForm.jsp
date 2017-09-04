<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="javascript:void(0)" onclick="history.go(-1)">需求列表</a></li>
		<li class="active"><a href="${ctx}/shop/member/form?id=${member.id}">会员<shiro:hasPermission name="shop:member:edit">${not empty member.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="shop:member:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="member" action="${ctx}/shop/member/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<fieldset>
			<table class="table-form">
				<tr>
					<td class="tit" rowspan="2">基本信息</td>
					<td class="tit">微信ID</td>
					<td><form:input path="openId" htmlEscape="false" maxlength="64" class="input-xlarge "/></td>
					<td class="tit">昵称</td>
					<td><form:input path="nickName" htmlEscape="false" maxlength="64" class="input-xlarge "/></td>
				</tr>
				<tr>
					<td class="tit">电话</td>
					<td colspan="4"><form:input path="mobile" htmlEscape="false" maxlength="20" class="input-xlarge"/></td>
				</tr>
				<tr>
					<td class="tit">找铺信息</td>
					<td colspan="4">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>电话</th>
									<th>开什么店</th>
									<th>区域</th>
									<th>面积</th>
									<th>租金范围</th>
									<th>商铺类型</th>
									<th>经营业态</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${findShopList}" var="findShop">
								<tr>
									<td>
										${findShop.mobile}
									</td>
									<td>
										${findShop.purpose}
									</td>
									<td>				
										<c:if test="${empty findShop.areaName}">
										  	 全部
										</c:if>		
										<c:if test="${not empty findShop.areaName}">
											${findShop.areaName}
										</c:if>		
									</td>
									<td>
										${fns:getDictLabel(findShop.coveredArea, 'shop_area', '')}
									</td>
									<td>
										${fns:getDictLabel(findShop.fee, 'shop_fee', '')}
									</td>
									<td>
										<c:if test="${empty findShop.shopType}">
										  	 不限
										</c:if>
										<c:if test="${not empty findShop.shopType}">
										   ${fns:getDictLabel(findShop.shopType, 'shop_shop_type', '')}
										</c:if>
									</td>
									<td>
										${fns:getDictLabel(findShop.managementFormat, 'shop_management_format', '')}
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td class="tit">转铺信息</td>
					<td colspan="4">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>电话</th>
									<th>区域</th>
									<th>面积</th>
									<th>月租金</th>
									<th>商铺类型</th>
									<th>经营业态</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${shopList}" var="shop">
								<tr>
									<td>
										${shop.mobile}
									</td>
									<td>
										${shop.area.name}
									</td>
									<td>
										${shop.useArea}
									</td>
									<td>
										${shop.fee}
									</td>
									<td>
										${fns:getDictLabel(shop.shopType, 'shop_shop_type', '')}
									</td>
									<td>
										${fns:getDictLabel(shop.managementFormat, 'shop_management_format', '')}
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td class="tit">收藏信息</td>
					<td colspan="4">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>商铺名称</th>
									<th>面积</th>
									<th>月租金</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${hobbyList}" var="hobby">
								<tr>
									<td>
										${hobby.shopName}
									</td>
									<td>
										${hobby.useArea}
									</td>
									<td>									
										${hobby.fee}
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td class="tit">跟进信息</td>
					<td colspan="4">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>跟进内容</th>
									<th>跟进时间</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${followList}" var="follow">
								<tr>
									<td>
										${follow.followNote}
									</td>
									<td>
										<fmt:formatDate value="${follow.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>									
									</td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</td>
				</tr>
				
			</table>
		</fieldset>
		
		
		
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>