<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出会员数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/shop/member/export");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/shop/member/");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/shop/member/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/shop/member/import/template">下载模板</a>
		</form>
	</div>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/shop/member/">会员列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="member" action="${ctx}/shop/member/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>电话：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>微信ID</th>
				<th>昵称</th>
				<th>电话</th>
				<th>转铺申请</th>
				<th>找铺申请</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="member">
			<tr>
				<td><a href="${ctx}/shop/member/form?id=${member.id}">
					${member.openId}
				</a></td>
				<td>
					${member.nickName}
				</td>
				<td>
					${member.mobile}
				</td>
				<td>	
					<c:choose>
						   <c:when test="${member.saleFlag=='1'}">  
						   		<a href="${ctx}/shop/member/save?id=${member.id}&saleFlag=2" onclick="return confirmx('确认已经处理完成了吗？', this.href)">${fns:getDictLabel(member.saleFlag, 'shop_process_flag', '')}</a>
						   </c:when>
						   <c:otherwise>
						   		${fns:getDictLabel(member.saleFlag, 'shop_process_flag', '')}
						   </c:otherwise>
					</c:choose>	
				</td>
				<td>	
					<c:choose>
						   <c:when test="${member.findFlag=='1'}">  
						   		<a href="${ctx}/shop/member/save?id=${member.id}&findFlag=2" onclick="return confirmx('确认已经处理完成了吗？', this.href)">${fns:getDictLabel(member.findFlag, 'shop_process_flag', '')}</a>
						   </c:when>
						   <c:otherwise>
						   		${fns:getDictLabel(member.findFlag, 'shop_process_flag', '')}
						   </c:otherwise>
					</c:choose>
				</td>
				<td>
    				<a href="${ctx}/shop/member/form?id=${member.id}">查看</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>