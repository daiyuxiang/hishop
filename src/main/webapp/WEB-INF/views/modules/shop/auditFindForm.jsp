<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#auditFindForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				showErrors: function (errorMap, errorList) {
                    var msg = "";
                    //下拉框必填显示有问题
                    $.each(errorList, function (i, v) {
                        layer.tips(v.message, v.element, { time: 2000 });
                        return false;
                    });  
                },
                onfocusout: false
			});	
		});
		
		
		function submitClick(auditFlag) {	
			$('#auditFlag').val(auditFlag);		
			$('#followNote').val($('#tempFollowNote').val().trim());		
			
			$("#auditFindForm").submit();	
		}
		
		function saveFollow() {
			$.ajax({
				url: "${ctx}/shop/follow/save",
				type: "post",
				dataType: "json",
				data: {"followNote":$('#followNote').val(),"memberId":'${memberId}'},
				success: function(data){	
					if(data.status=='1') {
						alertx("保存跟进记录成功");
					}
				}
			});	
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shop/member/list?sourceType=2">需求列表</a></li>
		<li class="active"><a href="${ctx}/shop/member/memberForm2?id=${member.id}">会员查看</a></li>
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
					<td class="tit">跟进内容</td>
					<td colspan="4"><form:textarea path="tempFollowNote" htmlEscape="false" maxlength="100" class="input-xlarge " style="height:120px;width:600px;"/>
					<span class="help-inline">字数不超过100字</span>
					<input id="btnFollow" class="btn btn-primary" type="button"  onclick="saveFollow();" value="保存"/>
					</td>	
				</tr>			
			</table>
		</fieldset>
		
		
		
		<div class="form-actions">
			<input class="btn btn-primary" type="button"  onclick="submitClick('3');" value="通过"/>&nbsp;
			<input class="btn btn-primary" type="button" onclick="submitClick('2');"  value="拒绝"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>