<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/shop/member/list?sourceType=1");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/shop/member/list?sourceType=1">转铺会员</a></li>
		<li><a href="${ctx}/shop/member/list?sourceType=2">找铺会员</a></li>
		<li><a href="${ctx}/shop/member/list?sourceType=4">金牌店铺会员</a></li>
		<li><a href="${ctx}/shop/member/list?sourceType=3">游客会员</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="member" action="${ctx}/shop/member/list?sourceType=1" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>电话：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>推荐人：</label>
				<form:input path="referrer" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:select path="saleType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_process_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>转铺类型：</label>
				<form:select path="saleMember" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_sale_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="clearfix"></li> 
			<li><label>转铺时间：</label>
				<input id="shopDateBegin"  name="shopDateBegin"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${member.shopDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
				<input id="shopDateEnd" name="shopDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${member.shopDateEnd}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnReset" class="btn btn-primary" type="button" value="重置" onclick="return resetting();"/>
				
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/> -->
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>电话</th>
				<th>昵称</th>
				<th>推荐人</th>
				<th>自行转铺</th>
				<th>一键转铺</th>
				<th>转铺时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><a href="${ctx}/shop/shop/list?mobile=${member.mobile}">
					${member.mobile}
				</a></td>
				<td>
					${member.nickName}
				</td>
				<td>
					${member.referrer}
				</td>
				<td>	
					${fns:getDictLabel(member.saleFlag, 'shop_process_flag', '')}
				</td>
				<td>	
					${fns:getDictLabel(member.publishSaleFlag, 'shop_process_flag', '')}
				</td>
				<td>	
					<fmt:formatDate value="${member.shopDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${member.saleFlag=='1' or member.publishSaleFlag=='1'}">
						<a href="${ctx}/shop/member/auditSaleForm?id=${member.id}&shopId=${member.shopId}">审核</a>
					</c:if>					
					<c:if test="${member.saleFlag=='3' or member.publishSaleFlag=='3'}">
						<a href="${ctx}/shop/member/auditSaleForm?id=${member.id}&shopId=${member.shopId}">再次审核</a>
					</c:if>					
					<a href="${ctx}/shop/member/form?id=${member.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>