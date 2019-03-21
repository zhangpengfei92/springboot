<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<meta charset="utf-8">
<title>登入</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
<link rel="stylesheet" href="static/layuiadmin/style/login.css" media="all">
<script src="static/js/jquery/jquery-1.7.1.js"></script>
<script src="static/js/layer/layer.js"></script>
<style type="text/css">
   .bg{
      background-image: url("static/img/bg.png");
      background-repeat: no-repeat;
      background-position-x: center;
      background-position-y: center;
      background-size: 100% 80%;
   }

</style>
</head>
<body class="bg">

<div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <p></p>
            <p></p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                <input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
            </div>
            <div class="layui-form-item">
                <div class="layui-row">
                    <div class="layui-col-xs7">
                        <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
                        <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input">
                    </div>
                    <div class="layui-col-xs5">
                        <div style="margin-left: 10px;">
                            <img src="" onclick="getVerify(this)" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item" style="margin-bottom: 20px;">
                <%--<input type="checkbox" name="remember" lay-skin="primary" title="记住密码">--%>
                <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;"><%--忘记密码？--%></a>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" onclick="btn_submit()" lay-submit lay-filter="LAY-user-login-submit">登 入</button>
            </div>

        </div>
    </div>

    <div class="layui-trans layadmin-user-login-footer">
        <p>© 2018 <a href="http://www.djrj.cn/" target="_blank">djrj.cn</a></p>
    </div>

</div>

<script src="static/layui/layui.js"></script>
<script type="text/javascript">
    if (window != top) {
        top.location.href = location.href;
    }

    document.onkeydown = function (event) {
        var e = event || window.event;
        if (e && e.keyCode == 13) { //回车键的键值为13
            //$(".line2 button").click(); //调用登录按钮的登录事件
            btn_submit();
        }
    };


    function getVerify(obj){
        obj.src = "getVerify?"+Math.random();
    }
    $(function(){
       $("#LAY-user-get-vercode") .attr("src","getVerify");
    });
    function btn_submit(){
        $.ajax({
            type:"post",
            url:"adminLogin",
            dataType : "json",
            async : false,
            data:{
                "username":$("#LAY-user-login-username").val(),
                "password":$("#LAY-user-login-password").val(),
                "code":$("#LAY-user-login-vercode").val()
            },
            success:function(result){
                if(result.code == 200){
                    window.location.href="index";
                }else{
                    layer.msg(result.msg, {time:1000, icon:5, shift:6});
                    $("#LAY-user-get-vercode").click();
                }
            },
            error:function(){
                layer.msg("请稍后登录!", {time:1000, icon:5, shift:6});
            }
        });
    }
</script>
</body>
</html>

