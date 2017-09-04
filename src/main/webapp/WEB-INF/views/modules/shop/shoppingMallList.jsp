<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商场管理</title>
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
		<li class="active"><a href="${ctx}/shop/shoppingMall/">商场列表</a></li>
		<li><a href="${ctx}/shop/shoppingMall/form">商场添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="shoppingMall" action="${ctx}/shop/shoppingMall/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商场名称：</label>
				<form:input path="mallName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>区域：</label>
				<sys:treeselect id="area" name="area.id" value="${shoppingMall.area.id}" labelName="area.name" labelValue="${shoppingMall.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true" notAllowSelectParent="false"/>
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
				<th>商场名称</th>
				<th>省</th>
				<th>城市</th>
				<th>区域</th>
				<th>镇</th>
				<th>首页置顶</th>
				<th>区域置顶</th>
				<th>轮播置顶</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shoppingMall" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><a href="${ctx}/shop/shoppingMall/form?id=${shoppingMall.id}">
					${shoppingMall.mallName}
				</a></td>
				<td>
					${shoppingMall.provinceName}
				</td>
				<td>
					${shoppingMall.cityName}
				</td>
				<td>
					${shoppingMall.area.name}
				</td>
				<td>
					${shoppingMall.town.name}
				</td>
				<td>
					${fns:getDictLabel(shoppingMall.pageTop, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(shoppingMall.areaTop, 'yes_no', '')}
				</td>
				<td>
					${fns:getDictLabel(shoppingMall.scrollTop, 'yes_no', '')}
				</td>
				<td>
    				<a href="${ctx}/shop/shoppingMall/form?id=${shoppingMall.id}">修改</a>
					<a href="${ctx}/shop/shoppingMall/delete?id=${shoppingMall.id}" onclick="return confirmx('确认要删除该商场吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>