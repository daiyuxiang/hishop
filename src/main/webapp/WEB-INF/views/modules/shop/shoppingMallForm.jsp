<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商场管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				showErrors: function (errorMap, errorList) {
                    var msg = "";
                    //下拉框必填显示有问题
                    $.each(errorList, function (i, v) {
                    	// 图片显示位置
                    	if($(v.element).is(':hidden')) {
                        	layer.tips(v.message, $(v.element).parent(), { time: 2000 });
                    	} else {
                            layer.tips(v.message, v.element, { time: 2000 });
                    	}
                        return false;
                    });  
                }
			});
			
			if(${shoppingMall.pageTop}=='1') {
				$("input[name='scrollTop']").attr("disabled", true);
			}
			
			
			$("input[name='pageTop']").change(function() { 
				if($(this).val()=='1') {
					$("input[name='scrollTop']:eq(1)").attr("checked",'checked'); 
					$("input[name='scrollTop']").attr("disabled", true);
				} else {
					$("input[name='scrollTop']").attr("disabled", false);
				}
			}); 
			
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shop/shoppingMall/">商场列表</a></li>
		<li class="active"><a href="${ctx}/shop/shoppingMall/form?id=${shoppingMall.id}">商场${not empty shoppingMall.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shoppingMall" action="${ctx}/shop/shoppingMall/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">商场名称：</label>
			<div class="controls">
				<form:input path="mallName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">镇：</label>
			<div class="controls">
				<sys:treeselect id="town" name="town.id" value="${shoppingMall.town.id}" labelName="town.name" labelValue="${shoppingMall.town.name}"
					title="镇" url="/sys/area/treeData" cssClass="required" notAllowSelectParent="true" />
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商场图片：</label>
			<div class="controls">
				<form:hidden id="mallUrl" path="mallUrl" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<sys:ckfinder input="mallUrl" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>			
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">首页置顶：</label>
			<div class="controls">
				<form:radiobuttons path="pageTop" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
				<span class="help-inline">首页最多置顶数量为4个</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">区域置顶：</label>
			<div class="controls">
				<form:radiobuttons path="areaTop" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
				<span class="help-inline">同区域最多置顶数量为4个</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">轮播置顶：</label>
			<div class="controls">
				<form:radiobuttons path="scrollTop" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>
				<span class="help-inline">除首页置顶外,轮播最多置顶数量为4个</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">百货/购物：</label>
			<div class="controls">
				<form:input path="shoppingArea" htmlEscape="false" maxlength="10" class="input-xlarge"/>
				<span class="help-inline">万㎡</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">动态客流：</label>
			<div class="controls">
				<form:input path="dynamicFlow" htmlEscape="false" maxlength="10" class="input-xlarge"/>
				<span class="help-inline">万人</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">静态客流：</label>
			<div class="controls">
				<form:input path="staticFlow" htmlEscape="false" maxlength="10" class="input-xlarge"/>
				<span class="help-inline">万人</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">写字楼面积：</label>
			<div class="controls">
				<form:input path="officeArea" htmlEscape="false" maxlength="10" class="input-xlarge"/>
				<span class="help-inline">万㎡</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">餐饮店铺百分比：</label>
			<div class="controls">
				<form:input path="percent1" htmlEscape="false" maxlength="10" class="input-xlarge"/>
				<span class="help-inline">%</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">购物店铺百分比：</label>
			<div class="controls">
				<form:input path="percent2" htmlEscape="false" maxlength="10" class="input-xlarge"/>
				<span class="help-inline">%</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">休闲娱乐百分比：</label>
			<div class="controls">
				<form:input path="percent3" htmlEscape="false" maxlength="10" class="input-xlarge"/>
				<span class="help-inline">%</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">教育培训百分比：</label>
			<div class="controls">
				<form:input path="percent4" htmlEscape="false" maxlength="10" class="input-xlarge"/>
				<span class="help-inline">%</span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>