<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>店铺管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		 $(document).ready(function() {	
			showImg();
			 
			$("#inputForm").validate({
				submitHandler: function(form){
					$.ajax({
						url: "${ctx}/shop/shop/checkMobile",
						type: "post",
						dataType: "text",
						data: {"mobile":$('#mobile').val()},
						success: function(data){	
							if(data=="success"){	
								loading('正在提交，请稍等...');
								form.submit();
							}else{
								top.$.jBox.confirm('本号码已发布店铺，是否继续?','系统提示',function(v,h,f){
									if(v==true){
										loading('正在提交，请稍等...');
										top.$.jBox.close(true);
										form.submit();
									} else {
										window.location.href = '${ctx}/shop/shop/list?mobile='+$('#mobile').val();
									}
								},{buttons:{'是': true, '否': false}}); 
								top.$('.jbox-body .jbox-icon').css('top','55px');
							}
						}
					});
					
				},
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
                },
                onfocusout: false,
                rules : {
                	maximumLease : {
                		digits : true
                	},
                	operatingLife : {
               	 		minNumber: true 
               		},
               		mobile : {
               			isMobile : true
               		},
               		fee :{
               			number : true
               		},
               		useArea : {
               			number : true
               		},
               		transferFee : {
               			number : true
               		},
               		faceWidth : {
               			number : true
               		},
               		floorHeight : {
               			number : true
               		},
               		deposit : {
               			number : true
               		}
                },
                messages : {
                	maximumLease : {
                	 digits : "必须是整数"
                	},
                	mobile : {
                		isMobile : "请输入正确的手机号"
                	},
                	fee : {
                		number : "请输入正确的数字"
                	},
                	useArea : {
                		number : "请输入正确的数字"
                	},
                	transferFee : {
                		number : "请输入正确的数字"
                	},
                	faceWidth : {
                		number : "请输入正确的数字"
                	},
                	floorHeight : {
                		number : "请输入正确的数字"
                	},
                	deposit : {
                		number : "请输入正确的数字"
                	}                	
                }
			});
				
			
			$('#sellType').change(function() { 
				if($(this).val()=='1') {
					$('#transferFee').attr("disabled", false);
				} else { 
					$('#transferFee').val('');
					$('#transferFee').attr("disabled", true);
				}
			}); 
		 
			$('#shopType').change(function() {
				if($(this).val()=='10') {
					$('#shoppingMall').prop("disabled", false);
					selectShoppingMall($('#townId').val());
				}  else {
					$('#shoppingMall').val(null).trigger("change");
					$('#shoppingMall').prop("disabled", true);
				}
			});
			
		});
		 
		function selectShoppingMall(townId) {
			$.ajax({
				  url: "${ctx}/shop/shop/getShoppingMallByArea",
		  	      type: "post",
		  	      dataType:'json',  
		  	      async:false,
		  	      data:{townId:townId},
		  	      success: function(data) {
		  	    	 $('#shoppingMall').val('');
		  	       	 $('#shoppingMall').html('');
		  	         
		  	         $.each(data,function(index,val){
		  	        	$('#shoppingMall').append('<option value="'+val.id+'">'+val.mallName+'</option>');
		  	         });
		  	    
		  	      }
			});
		}	
		
		function publish(){
			$("#inputForm").submit();
		}		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/shop/shop/">店铺列表</a></li>
		<li class="active"><a href="${ctx}/shop/shop/form?id=${shop.id}">店铺${not empty shop.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="shop" action="${ctx}/shop/shop/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<fieldset>
			<table class="table-form">
				<!-- 用户信息 -->
				<tr>
					<td class="tit">用户信息</td>
					<td class="tit">电话</td>
					<td><form:input path="mobile" htmlEscape="false" maxlength="20" class="input-xlarge required"/><span class="help-inline"><font color="red">*</font></span></td>
					<td class="tit">会员名</td>
					<td><form:input path="memberName" htmlEscape="false" maxlength="50" class="input-xlarge"/></td>
				</tr>
				<!-- 基本信息 -->
				<tr>
					<td class="tit" rowspan="4">基本信息</td>
					<td class="tit">商铺类型</td>
					<td><form:select path="shopType" class="input-xlarge required" >
					<form:options items="${fns:getDictList('shop_shop_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					<span class="help-inline"><font color="red">*</font></span>
					</td>
					<td class="tit">店铺名称</td>
					<td><form:input path="shopName" htmlEscape="false" maxlength="100" class="input-xlarge"/></td>
				</tr>
				<tr>
					<td class="tit">镇</td>
					<td>
					<sys:treeselect id="town" name="town.id" value="${shop.town.id}" labelName="town.name" labelValue="${shop.town.name}"
					title="镇" url="/sys/area/treeData" cssClass="required" notAllowSelectParent="true" callBack="true"/>
					<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit">出让类型</td>
					<td ><form:select path="sellType" class="input-xlarge" >
							<form:options items="${fns:getDictList('shop_sell_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					<span class="help-inline"><font color="red">*</font></span>
					</td>
					
				</tr>
				<tr>
					<td class="tit">月租金</td>
					<td><form:input path="fee" htmlEscape="false" class="input-xlarge required"/><span class="help-inline">万元<font color="red">*</font></span></td>
					<td class="tit">转让费</td>
					<td><form:input path="transferFee" htmlEscape="false" class="input-xlarge "/><span class="help-inline">万元</span></td>
				</tr>
				<tr>
					<td class="tit">店铺地址</td>
					<td><form:input path="shopAddress" htmlEscape="false" maxlength="500" class="input-xlarge required"/><span class="help-inline"><font color="red">*</font></span></td>
					<td class="tit">推荐人</td>
					<td><form:input path="referrer" htmlEscape="false" maxlength="50" class="input-xlarge"/></td>
				</tr>
				<!-- 经营状况 -->
				<tr>
					<td class="tit" rowspan="3">经营状况</td>
					<td class="tit">经营状态</td>
					<td><form:select path="managementStatus" class="input-xlarge required">
					<form:options items="${fns:getDictList('shop_management_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select><span class="help-inline"><font color="red">*</font> </span></td>
					<td class="tit">经营年限</td>
					<td><form:input path="operatingLife" htmlEscape="false" maxlength="50" class="input-xlarge"/><span class="help-inline">年</span></td>
					
				</tr>
				<tr>
					<td class="tit">经营业态</td>
					<td><form:select path="managementFormat" class="input-xlarge required">
					<form:options items="${fns:getDictList('shop_management_format')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</form:select>
					<span class="help-inline"><font color="red">*</font> </span>
					</td>
					<td class="tit">当前经营</td>
					<td><form:input path="currentManagement" htmlEscape="false" maxlength="500" class="input-xlarge required"/>
					<span class="help-inline"><font color="red">*</font> </span>
					</td>
					
				</tr>
				<tr>
					<td class="tit">品牌情况</td>
					<td  colspan="4"><form:input path="brandInfo" htmlEscape="false" maxlength="200" class="input-xlarge "/></td>
					
				</tr>
				<!-- 位置信息 -->
				<tr>
					<td class="tit" rowspan="2">位置信息</td>
					<td class="tit">商场名称</td>
					<td><form:select path="shoppingMall" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${shoppingMallList}" itemLabel="mallName" itemValue="id" htmlEscape="false"/>
				</form:select>
					</td>
					<td class="tit">位置</td>
					<td colspan="4"><form:input path="position" htmlEscape="false" maxlength="500" class="input-xlarge " placeholder="例如拐角和人行道中间"/></td>
				</tr>
				<tr>
						</tr>
				<!-- 建筑信息 -->
				<tr>
					<td class="tit" rowspan="3">建筑信息</td>
					<td class="tit">使用面积</td>
					<td><form:input path="useArea" htmlEscape="false" maxlength="6" class="input-xlarge required"/><span class="help-inline">㎡<font color="red">*</font></span></td>
					<td class="tit">面宽</td>
					<td><form:input path="faceWidth" htmlEscape="false" maxlength="6" class="input-xlarge "/><span class="help-inline">米</span></td>
				</tr>
				<tr>
					<td class="tit">进深</td>
					<td><form:input path="depth" htmlEscape="false" maxlength="6" class="input-xlarge "/><span class="help-inline">米</span></td>
					<td class="tit">层高</td>
					<td><form:input path="floorHeight" htmlEscape="false" maxlength="6" class="input-xlarge "/><span class="help-inline">米</span></td>
				</tr>
				<tr>
					<td class="tit">是否分割</td>
					<td ><form:select path="segmentation" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
				<td class="tit">楼层</td>
					<td><form:select path="floor" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_floor')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
				</tr>
				
				<!-- 租约相关-->
				<tr>
					<td class="tit" rowspan="2">租约相关</td>
					<td class="tit">支付方式</td>
					<td><form:input path="paymentMethod" htmlEscape="false" maxlength="200" class="input-xlarge" placeholder="例如押一付三"/></td>
					<td class="tit">签约年限</td>
					<td><form:input path="maximumLease" htmlEscape="false" maxlength="200" class="input-xlarge "/><span class="help-inline">年</span></td>
				</tr>
				<tr>
					<td class="tit">租赁状态</td>
					<td><form:select path="leaseStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('shop_lease_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
					</td>
				</tr>
				<!-- 上传照片 -->
				<tr>
					<td class="tit" rowspan="3">上传照片</td>
					<td class="tit">图片1</td>
					<td><form:hidden id="image1" path="image1" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
					<sys:ckfinder input="image1" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
					<span class="help-inline">图片只能以 bmp、jpeg、jpg、gif 格式上传,图片大小不超过2M<font color="red">*</font></span>
					</td>
					<td class="tit">图片2</td>
					<td><form:hidden id="image2" path="image2" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
					<sys:ckfinder input="image2" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100" />
					<span class="help-inline">图片只能以 bmp、jpeg、jpg、gif 格式上传,图片大小不超过2M<font color="red">*</font></span>
					</td>
				</tr>
				<tr>
					<td class="tit">图片3</td>
					<td><form:hidden id="image3" path="image3" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
					<sys:ckfinder input="image3" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
					<span class="help-inline">图片只能以 bmp、jpeg、jpg、gif 格式上传,图片大小不超过2M<font color="red">*</font></span>
					</td>
					<td class="tit">图片4</td>
					<td><form:hidden id="image4" path="image4" htmlEscape="false" maxlength="200" class="input-xlarge "/>
					<sys:ckfinder input="image4" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
					<span class="help-inline">图片只能以 bmp、jpeg、jpg、gif 格式上传,图片大小不超过2M</span>
					</td>
				</tr>
				<tr>
					<td class="tit">图片5</td>
					<td><form:hidden id="image5" path="image5" htmlEscape="false" maxlength="200" class="input-xlarge "/>
					<sys:ckfinder input="image5" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
					<span class="help-inline">图片只能以 bmp、jpeg、jpg、gif 格式上传,图片大小不超过2M</span>
					</td>
					<td class="tit">图片6</td>
					<td><form:hidden id="image6" path="image6" htmlEscape="false" maxlength="200" class="input-xlarge "/>
					<sys:ckfinder input="image6" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
					<span class="help-inline">图片只能以 bmp、jpeg、jpg、gif 格式上传,图片大小不超过2M</span>
					</td>
				</tr>
				<!-- 其他-->
				<tr>
					<td class="tit" rowspan="3">其他</td>
				</tr>
				<tr>
					<td class="tit">实勘详情</td>
					<td colspan="4"><form:textarea path="shopContent" htmlEscape="false" maxlength="200" class="input-xlarge " style="height:120px;width:600px;"/>
					<span class="help-inline">字数不超过200字</span>
					</td>
				</tr>
				<tr>
					<td class="tit">备注</td>
					<td colspan="4"><form:textarea path="remarks" htmlEscape="false" maxlength="200" class="input-xlarge " style="height:120px;width:600px;"/>
					<span class="help-inline">字数不超过200字</span>
					</td>	
				</tr>
				<tr>
					<td class="tit">标记为金牌店铺</td>
					<td colspan="4"><form:checkbox path="goldFlag" value="1"/></td>
				</tr>	
				<!-- 配套 -->
				<tr>
					<td class="tit">配套</td>
					<td colspan="4">
						<form:checkboxes path="matingIdList" items="${fns:getDictList('shop_mating')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
					</td>
				</tr>
				<!-- 热门推荐 -->
				<tr>
					<td class="tit">热门推荐</td>
					<td colspan="4">
						<form:checkboxes path="hotValueList" items="${fns:getDictList('shop_hot')}" itemLabel="label" itemValue="label" htmlEscape="false"/>
					</td>
				</tr>
				
				
			</table>
		</fieldset>
		
		<div class="form-actions">
			<input class="btn btn-primary" type="button" value="发布" onclick="publish();"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>