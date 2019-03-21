<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>欢迎页</title>
<link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">

    <script src="static/js/jquery/jquery-1.9.1.min.js"></script>
<script src="static/js/echarts.common.min.js"></script>
    <script src="static/js/time/digit.js"></script>

    <script src="static/js/time/time.js"></script>
    <script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card" style="min-height: 600px">
        <div class="layui-card-header">欢迎页面</div>
        <div class="layui-card-body">
            <iframe scrolling="no" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&id=36&icon=5&num=5"></iframe>
            <hr/>
            <div style="height: 450px">
            <div id="system" style="height: 450px;width: 70%;float: left">

            </div>
            <div style="width: 30%;float: left">
                <canvas id="canvas"></canvas>
                <center>
                    <H3 >
                        登录点:<font id="ip" size="5" style="color: green"></font>
                    </H3>
                </center>
                <img src="static/img/home.png" style="width: 100%">
            </div>
            </div>
        </div>
    </div>
</div>
<script src="static/js/layer/layer.js"></script>
</body>
</html>