<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>店铺管理</title>
	<meta name="decorator" content="default"/>
	<style>
	
	.form-search .ul-form li label {
	    width: 120px;
	}
	
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出商铺数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/shop/shop/export");
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
			$("#searchForm").attr("action","${ctx}/shop/shop/");
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/shop/shop/">店铺列表</a></li>
		<li><a href="${ctx}/shop/shop/form">店铺添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="shop" action="${ctx}/shop/shop/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<label>月租金：</label>
				<form:select path="feeString" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_fee')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li>
				<label>转让费：</label>
				<form:select path="transferFeeString" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_transfer_fee')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>镇：</label>
				<sys:treeselect id="town" name="town.id" value="${shop.town.id}" labelName="town.name" labelValue="${shop.town.name}"
					title="镇" url="/sys/area/treeData" cssClass="input-small" allowClear="true"/>
			</li>
			<li><label>使用面积：</label>
				<form:select path="useAreaString" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_area')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>金牌店铺：</label>
				<form:select path="goldFlag" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
				
			<li class="clearfix"></li> 
			<li><label>店铺状态：</label>
				<form:select path="showFlag" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_show_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>		
			<li><label>电话：</label>
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>	
			<li><label>发布类型：</label>
				<form:select path="publishType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_publish_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>	
			<li><label>发布时间：</label>
				<input id="passDateBegin"  name="passDateBegin"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${shop.passDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
				<input id="passDateEnd" name="passDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${shop.passDateEnd}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			
			<li><label>商铺类型：</label>
				<form:select path="shopType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>	
						
			
			<li class="btns">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
				<input id="btnImport" class="btn btn-primary" type="button" value="导入"/>
				<input id="btnReset" class="btn btn-primary" type="button" value="重置" onclick="return resetting();"/>
			</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>店铺标题</th>
				<th>商铺编号</th>
				<th>电话</th>
				<th>区域</th>
				<th>店铺地址</th>
				<th>商铺类型</th>
				<th>月租金(万元)</th>
				<th>转让费(万元)</th>
				<th>建筑面积</th>
				<th>金牌店铺</th>
				<th>推荐人</th>
				<th>发布时间</th>
				<th>发布类型</th>
				<th>店铺状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="shop" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td><a href="${ctx}/shop/shop/form?id=${shop.id}">
					${shop.shopName}
				</a></td>
				<td>
					${shop.shopNo}
				</td>
				<td>
					${shop.mobile}
				</td>
				<td>
					${shop.area.name}
				</td>
				<td>
					${shop.shopAddress}
				</td>
				<td>
					${fns:getDictLabel(shop.shopType, 'shop_shop_type', '')}
				</td>
				<td>
					${shop.fee}
				</td>
				<td>
					${shop.transferFee}
				</td>
				<td>
					${shop.useArea}
				</td>
				<td>
					${fns:getDictLabel(shop.goldFlag, 'yes_no', '')}
				</td>
				<td>
					${shop.referrer}
				</td>
				<td>
					<fmt:formatDate value="${shop.passDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(shop.publishType, 'shop_publish_type', '')}
				</td>
				<td>
					${fns:getDictLabel(shop.showFlag, 'shop_show_flag', '')}
				</td>
				<td>
					<c:if test="${shop.showFlag == 1}">
						<a href="${ctx}/shop/shop/unSale?id=${shop.id}" onclick="return confirmx('确认要下架该店铺吗？', this.href)">下架</a>
					</c:if>
    				<a href="${ctx}/shop/shop/form?id=${shop.id}">修改</a>
					<a href="${ctx}/shop/shop/delete?id=${shop.id}" onclick="return confirmx('确认要删除该店铺吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/shop/shop/import" method="post" enctype="multipart/form-data"
			class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/shop/shop/import/template">下载模板</a>
		</form>
	</div>
</body>
</html>