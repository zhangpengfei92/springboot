<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath %>">
<meta charset="utf-8">
<title>消息中心</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
</head>
<body>

  <div class="layui-fluid" id="LAY-app-message">
    <div class="layui-card">
      <div class="layui-tab layui-tab-brief">
        <ul class="layui-tab-title">
          <li class="layui-this">消息</li>
        </ul>
        <div class="layui-tab-content">
        
          <div class="layui-tab-item layui-show">
          
            <div class="LAY-app-message-btns" style="margin-bottom: 10px;">
              <button class="layui-btn layui-btn-primary layui-btn-sm" data-type="all" data-events="del">删除</button>
              <button class="layui-btn layui-btn-primary layui-btn-sm" data-type="all" data-events="ready">标记已读</button>
              <button class="layui-btn layui-btn-primary layui-btn-sm" data-type="all" data-events="readyAll">全部已读</button>
            </div>
          </div>
          
        </div>
      </div>
    </div>
  </div>

  <script src="static/layui/layui.js"></script>
  <script>

  </script>
</body>
</html>