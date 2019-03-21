<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <input type="hidden" name="id" value="${user.id}"/>
            <div class="layui-form-item">
                <label class="layui-form-labels">用户名:</label>
                <div class="layui-input-inline">
                    <input type="text" name="username" value="${user.username}" readonly autocomplete="off" class="layui-input" >
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-labels">手机号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="telephone" value="${user.telephone}" placeholder="请输入手机号" lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-labels">密码:</label>
                <div class="layui-input-inline">
                    <input type="password" name="password" value="${user.password}" placeholder="请输入密码" lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-labels">开始时间:</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="startDate" id="startTime" value="<fmt:formatDate type="date"
            value="${user.starttime}" pattern="yyyy-MM-dd"/>" lay-verify="required">

                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-labels">结束时间:</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="endDate" id="endTime" value="<fmt:formatDate type="date"
            value="${user.endtime}" pattern="yyyy-MM-dd"/>" lay-verify="required">

                </div>
            </div>
            <br/>
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
        jclAdd.init('user/add');
        laydate.render({
            elem: '#startTime'
        });
        laydate.render({
            elem: '#endTime'
        });
    });

</script>

</body>
</html>