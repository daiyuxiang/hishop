<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>轮播图管理</title>
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
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			if(${sliderImg.imgType}=='1') {
				$("input[name='btnUrl']").attr("disabled", true);
			}
			
			
			$("input[name='imgType']").change(function() { 
				if($(this).val()=='1') {
					$("input[name='btnUrl']").val('');
					$("input[name='btnUrl']").attr("disabled", true);
				} else {
					$("input[name='btnUrl']").attr("disabled", false);
				}
			}); 
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shop/sliderImg/">轮播图列表</a></li>
		<li class="active"><a href="${ctx}/shop/sliderImg/form?id=${sliderImg.id}">轮播图${not empty sliderImg.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sliderImg" action="${ctx}/shop/sliderImg/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">图片标题：</label>
			<div class="controls">
				<form:input path="imgTitle" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">略缩图：</label>
			<div class="controls">
				<form:hidden id="url" path="url" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<sys:ckfinder input="url" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">大图：</label>
			<div class="controls">
				<form:hidden id="showUrl" path="showUrl" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<sys:ckfinder input="showUrl" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100" />			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">显示内容：</label>
			<div class="controls">
				<form:radiobuttons path="imgType" items="${fns:getDictList('shop_img_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class=""/>			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">页面地址：</label>
			<div class="controls">
				<form:input path="btnUrl" htmlEscape="false" maxlength="200" class="input-xlarge "/>			
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>