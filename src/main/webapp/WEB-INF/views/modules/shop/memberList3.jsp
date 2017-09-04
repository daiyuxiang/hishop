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
			$("#searchForm").attr("action","${ctx}/shop/member/list?sourceType=3");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shop/member/list?sourceType=1">转铺会员</a></li>
		<li><a href="${ctx}/shop/member/list?sourceType=2">找铺会员</a></li>
		<li><a href="${ctx}/shop/member/list?sourceType=4">金牌店铺会员</a></li>
		<li class="active"><a href="${ctx}/shop/member/list?sourceType=3">游客会员</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="member" action="${ctx}/shop/member/list?sourceType=3" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>昵称：</label>
				<form:input path="nickName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
				<li><label style="width:120px">最后登录时间：</label>
				<input id="lastDateBegin"  name="lastDateBegin"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${member.lastDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
				<input id="lastDateEnd" name="lastDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${member.lastDateEnd}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnReset" class="btn btn-primary" type="button" value="重置" onclick="return resetting();"/>
				<!-- <input id="btnExport" class="btn btn-primary" type="button" value="导出"/> -->
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>昵称</th>
				<th>最后登录时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><a href="${ctx}/shop/member/form?id=${member.id}">
					${member.nickName}
				</a></td>
				<td>
					<fmt:formatDate value="${member.lastDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>