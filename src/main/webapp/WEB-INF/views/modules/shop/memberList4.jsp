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
			$("#searchForm").attr("action","${ctx}/shop/member/list?sourceType=4");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shop/member/list?sourceType=1">转铺会员</a></li>
		<li><a href="${ctx}/shop/member/list?sourceType=2">找铺会员</a></li>
		<li class="active"><a href="${ctx}/shop/member/list?sourceType=4">金牌店铺会员</a></li>
		<li><a href="${ctx}/shop/member/list?sourceType=3">游客会员</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="member" action="${ctx}/shop/member/list?sourceType=4" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>区域：</label>
				<sys:treeselect id="area" name="area.id" value="${member.area.id}" labelName="area.name" labelValue="${member.area.name}"
					title="区域" url="/sys/area/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li>
				<label>店铺类型：</label>
				<form:select path="shopType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>电话：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label style="width:120px">金牌通过时间：</label>
				<input id="goldDateBegin"  name="goldDateBegin"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${member.goldDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
				<input id="goldDateEnd" name="goldDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${member.goldDateEnd}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			<li><label>昵称：</label>
				<form:input path="nickName" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li>
				<label>审核状态：</label>
				<form:select path="showFlag" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_show_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="clearfix"></li> 
		
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
				<th>店铺名称</th>
				<th>电话</th>
				<th>昵称</th>
				<th>推荐人</th>
				<th>金牌通过时间</th>
				<th>审核状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><a href="${ctx}/shop/shop/form?id=${member.shopId}">${member.shopName}</a></td>
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
					<fmt:formatDate value="${member.goldDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(member.showFlag, 'shop_show_flag', '')}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>