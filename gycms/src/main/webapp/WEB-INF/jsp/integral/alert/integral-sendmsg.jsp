<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title></title>
    <link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
    <script type="text/javascript" src="static/js/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="layui-fluid" style="background-color: white; padding: 10px">
    <div class="layui-row" >
        <form class="layui-form" action="" method="post" accept-charset="UTF-8">
            <div class="layui-form-item">
                <label class="layui-form-labels">用户名:</label>
                <div class="layui-input-inline">
                    <input type="text" name="username"  value="${user.username}" readonly="readonly" placeholder="请输入用户名" lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-labels">昵称:</label>
                <div class="layui-input-inline">
                    <input type="text" name="ucname" placeholder="请输入手机号" value="${user.ucname}" readonly="readonly" lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
			    <label class="layui-form-label">消息内容（不超过300字）</label>
			    <div class="layui-input-block">
			      <textarea placeholder="请输入内容" class="layui-textarea" name="titel"></textarea>
			    </div>
			 </div>
 			<input type="hidden" name="userid" value="${user.id}">
            <div class="layui-layer-btn layui-layer-btn-">
                <button class="layui-btn layui-btn-sm" lay-submit
                        lay-filter="formDemo">确定</button>
                <button type="button" class="layui-btn layui-btn-sm  layui-btn-primary Cancel">取消</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base:'static/layui/lay/extension/'
    }).use(['jclAdd','laydate'], function() {
        var jclAdd =layui.jclAdd,laydate =layui.laydate;
        jclAdd.init('user/sendMsg');
			lay.msg();
    });

</script>

</body>
</html>