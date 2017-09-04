<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>轮播图管理</title>
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
		<li class="active"><a href="${ctx}/shop/sliderImg/">轮播图列表</a></li>
		<li><a href="${ctx}/shop/sliderImg/form">轮播图添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sliderImg" action="${ctx}/shop/sliderImg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>图片标题：</label>
				<form:input path="imgTitle" htmlEscape="false" maxlength="200" class="input-medium"/>
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
				<th>图片标题</th>
				<th>图片地址</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sliderImg" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><a href="${ctx}/shop/sliderImg/form?id=${sliderImg.id}">
					${sliderImg.imgTitle}
				</a></td>
				<td>
					<img src="${sliderImg.url}" style="max-width:50px;max-height:50px;_height:100px;">
				</td>
				<td>
					<fmt:formatDate value="${sliderImg.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
    				<a href="${ctx}/shop/sliderImg/form?id=${sliderImg.id}">修改</a>
					<a href="${ctx}/shop/sliderImg/delete?id=${sliderImg.id}" onclick="return confirmx('确认要删除该轮播图吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>