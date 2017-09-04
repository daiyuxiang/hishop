<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电话记录管理</title>
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
		<li class="active"><a href="${ctx}/shop/tel/">电话记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="tel" action="${ctx}/shop/tel/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>			
		<ul class="ul-form">
			<li>
				<label>拨打时间：</label>
				<input id="callTimeBegin"  name="callTimeBegin"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${tel.callTimeBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
				<input id="callTimeEnd" name="callTimeEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${tel.callTimeEnd}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			<li>
				<label>主叫号码：</label>
				<form:input path="caller" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li>
				<label>被叫号码：</label>
				<form:input path="callee" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th>拨打时间</th>
				<th>主叫号码</th>
				<th>被叫号码</th>
				<th>通话时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tel" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>
					<fmt:formatDate value="${tel.callTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${tel.caller}
				</td>
				<td>
					${tel.callee}
				</td>
				<td>
					${tel.talkTime} 秒
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>