<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合作品牌管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/shop/cooperationBrand/">合作品牌列表</a></li>
		<li><a href="${ctx}/shop/cooperationBrand/form">合作品牌添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="cooperationBrand" action="${ctx}/shop/cooperationBrand/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>经营业态：</label>
				<form:select path="formatId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_management_format')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnReset" class="btn btn-primary" type="button" value="重置" onclick="return resetting();"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>品牌名称</th>
				<th>经营业态</th>
				<th>运营公司</th>
				<th>推荐标志</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="cooperationBrand" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><a href="${ctx}/shop/cooperationBrand/form?id=${cooperationBrand.id}">
					${cooperationBrand.brandName}
				</a></td>
				<td>
					${fns:getDictLabel(cooperationBrand.formatId, 'shop_management_format', '')}
				</td>
				<td>
					${cooperationBrand.company}
				</td>
				<td>
					${fns:getDictLabel(cooperationBrand.topFlag, 'yes_no', '')}
				</td>
				<td>
    				<a href="${ctx}/shop/cooperationBrand/form?id=${cooperationBrand.id}">修改</a>
					<a href="${ctx}/shop/cooperationBrand/delete?id=${cooperationBrand.id}" onclick="return confirmx('确认要删除该合作品牌吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>