<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/shop/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>帮助中心</title>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="email=no">
    <meta name="applicable-device" content="mobile">
    <link rel="stylesheet" href="${ctxStatic}/web/css/style.css" media="screen">
    <script src="${ctxStatic}/web/js/auto.js"></script>
    <script src="${ctxStatic}/web/js/jquery-3.1.1.min.js"></script>
</head>

<body>
<div class="pb-105">
    <h3 class="text-orange  help-cen bb2">常见问题</h3>
    <img src="${ctxStatic}/web/images/help-icon.png" alt="" class="fr help-cen-img" onclick="tel()">
    <ul class="help-lis bg-white">
        <li class="next-rig1 p-help bb2" id="why" onclick="question()" >
                为什么只能选择上海的店铺？
        </li>
        <li class="bb2 answers" id="answers">
                <p class="help-cen-p">Hi铺目前专注于上海地区商铺的租赁、转让服务，未来会开通更多城市，敬请期待</p>
        </li>
        <li class="next-rig1 p-help bb2" id="why2">
                怎么发布需求？
        </li>
        <li class="bb2 answers" id="answers2">
                <p class="help-cen-p">进入页面底部，点击“发布”，详情填写您的找铺、转铺信息并提交即可发布。</p>
        </li>
        <li class="next-rig1 p-help bb2" id="why3">
                怎样更快的把我的商铺转让出去？
        </li>
        <li class="bb2 answers" id="answers3">
                <p class="help-cen-p">加入Hi铺金牌店铺，将有专业的客服全程一对一为您完善相关信息，优化展示效果，提高展示机率，祝您快速转让店铺。</p>
        </li>


    </ul>

    <div class="help-tel">
    <a onclick="tel()" class="text-orange" id="tel">客服电话：021-80344870</a>
        <p>上述解答没能帮您解答疑惑？那就直呼小HI吧</p>
    </div>
</div>
<div class="popup-tel popup-z" id="popup-tel">
    <div class="popup-tel-con bg-white">
        <p>021-80344870</p>
        <a href="" class="bg-white text-orange">取消</a>  <a href="tel:021-80344870" class="tel-yes">呼叫</a>
    </div>
</div>
<%@ include file="/WEB-INF/views/modules/shop/front/include/footer.jsp"%>

</body>


<script>
	$(function() {
		$.each($('.footer').find('li'),function(){
			$(this).removeClass('footer-act');
		});
		
		$('.footer-my').addClass('footer-act');
	});
	
//  var a =document.getElementById("tel");
    var b =document.getElementById("popup-tel");
    function tel (){
        b.style.display="block"
    }
    var a=true;
    var why =document.getElementById("why");
    var c=document.getElementById("answers");//图片标签
    var why2 =document.getElementById("why2");
    var c2=document.getElementById("answers2");//图片标签
    var why3 =document.getElementById("why3");
    var c3=document.getElementById("answers3");//图片标签


//        var why =document.getElementsByClassName("answers");
//        for (var i = 0; i<why.length;i++) {
//            why[i].style.display="block";
//        };

        why.onclick=function(){
            if(a){c.style.display=("block");
              why.setAttribute("class","next-rig2 + next-rig1 + p-help + bb2");
                a=false;}
            else{c.style.display=("none");
                why.setAttribute("class"," next-rig1 + p-help + bb2");
                a=true;}
    };


        why2.onclick=function(){
            if(a){c2.style.display=("block");
                why2.setAttribute("class","next-rig2 + next-rig1 + p-help + bb2");
                a=false;}
            else{c2.style.display=("none");
                why2.setAttribute("class"," next-rig1 + p-help + bb2");
                a=true;}
        };
        why3.onclick=function(){
            if(a){c3.style.display=("block");
                why3.setAttribute("class","next-rig2 + next-rig1 + p-help + bb2");
                a=false;}
            else{c3.style.display=("none");
                why3.setAttribute("class"," next-rig1 + p-help + bb2");
                a=true;}
        }
	
</script>

</html>



