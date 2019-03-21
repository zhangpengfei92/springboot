<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <meta charset="utf-8">
    <title>国元后台管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="static/layuiadmin/style/admin.css" media="all">
    <script type="text/javascript" src="static/js/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="static/js/layer/layer.js"></script>
    <script type="text/javascript">
        function btn_updatePsw() {
            layer.open({
                type: 2,
                title: "修改密码",
                shadeClose: true,
                shade: 0.8,
                area: ['487px', '279px'],
                fix: false,
                maxmin: false,
                content: "comm/goUpdatePsw",
                closeBtn: 1,
                btn: ['确定', '取消'],
                yes: function (index, layero) {
                    var body = layer.getChildFrame('body', index);
                    var oldPassword = body.find("input[name='oldPassword']").val();
                    var newPassword = body.find("input[name='newPassword']").val();
                    var affirmPassword = body.find("input[name='affirmPassword']").val();
                    var username = body.find("input[name='username']").val();
                    $.ajax({
                        type: "POST",
                        url: "comm/updatePsw",
                        data: {
                            "oldPassword": oldPassword,
                            "newPassword": newPassword,
                            "affirmPassword": affirmPassword,
                            "username": username
                        },
                        success: function (result) {

                            if (result.code == 200) {
                                layer.msg("密码修改成功！", {time: 1000, icon: 6, shift: 6}, function () {
                                    layer.close(index);
                                });
                            }
                            if (result.code == 400) {
                                layer.msg(result.msg, {time: 2000, icon: 5, shift: 6});
                            }
                        },
                        error: function () {
                            layer.msg("系统异常", {time: 2000, icon: 5, shift: 6});
                        }
                    });
                }
            });
        }
    </script>
    <script>
        /^http(s*):\/\//.test(location.href) || alert('请先部署到 localhost 下再访问');
    </script>
</head>
<body class="layui-layout-body">

<div id="LAY_app">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header">
            <!-- 头部区域 -->
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item layadmin-flexible" lay-unselect>
                    <a href="javascript:;" layadmin-event="flexible" title="侧边伸缩">
                        <i class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="https://www.baidu.com/" target="_blank" title="百度一下你就知道!">
                        <i class="layui-icon layui-icon-website"></i>
                    </a>
                </li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;" layadmin-event="refresh" title="刷新">
                        <i class="layui-icon layui-icon-refresh-3"></i>
                    </a>
                </li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <input type="text" placeholder="百度一下..." autocomplete="off" class="layui-input layui-input-search"
                           layadmin-event="serach" lay-action="serach?keywords=">
                </li>
            </ul>
            <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">


                <li class="layui-nav-item layui-hide-xs" lay-unselect><a
                        href="javascript:;" layadmin-event="theme"> <i
                        class="layui-icon layui-icon-theme"></i>
                </a></li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect><a
                        href="javascript:;" layadmin-event="note"> <i
                        class="layui-icon layui-icon-note"></i>
                </a></li>
                <li class="layui-nav-item layui-hide-xs" lay-unselect><a
                        href="javascript:;" layadmin-event="fullscreen"> <i
                        class="layui-icon layui-icon-screen-full"></i>
                </a></li>
                <li class="layui-nav-item" lay-unselect>
                    <a href="javascript:;">
                        <cite>管理员</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <hr>
                        <dd style="text-align: center;"><a href="logout">退出</a></dd>
                    </dl>
                </li>

                <li class="layui-nav-item layui-hide-xs" lay-unselect>
                    <a href="javascript:void(0);"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
                <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-unselect>
                    <a href="javascript:;" layadmin-event="more"><i class="layui-icon layui-icon-more-vertical"></i></a>
                </li>
            </ul>
        </div>

        <!-- 侧边菜单 -->
        <div class="layui-side layui-side-menu">
            <div class="layui-side-scroll">
                <div class="layui-logo">
                    <span>国元后台管理系统</span>

                </div>

                <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu"
                    lay-filter="layadmin-system-side-menu">

                    <li data-name="accountmanagement" class="layui-nav-item"><a href="javascript:;" lay-tips="模块管理"
                                                                                lay-direction="1">
                        <i class="layui-icon layui-icon-home"></i> <cite>账户管理</cite>
                    </a>
                        <dl class="layui-nav-child">
                            <dd data-name="list"><a lay-href="user/skip"><i class="layui-icon layui-icon-username"></i>账户管理</a></dd>
                        </dl>
                        <dl class="layui-nav-child">
                            <dd data-name="list"><a lay-href="integral/skip"><i class="layui-icon layui-icon-username"></i>积分管理</a></dd>
                        </dl>
                        <dl class="layui-nav-child">
                            <dd data-name="list"><a lay-href="region/skip"><i class="layui-icon layui-icon-username"></i>比赛管理</a></dd>
                        </dl>
                        <dl class="layui-nav-child">
                            <dd data-name="list"><a lay-href="keyword/skip"><i class="layui-icon layui-icon-username"></i>关键字管理</a></dd>
                        </dl>
                    </li>
                    <c:if test="${!empty doclist}">
                    	  <li data-name="accountmanagement" class="layui-nav-item"><a href="javascript:;" lay-tips="模块管理"
                                                                                lay-direction="1">
                        <i class="layui-icon layui-icon-home"></i> <cite>公告管理</cite>
                    </a>
                    <c:forEach items="${doclist}" var="doc"> 
                    	<dl class="layui-nav-child">
                            <dd data-name="list"><a lay-href="doc/skip/${doc.id}"><i class="layui-icon layui-icon-username"></i>${doc.description}</a></dd>
                        </dl>
                    </c:forEach>
                    </li>
                    </c:if>                  
                </ul>
            </div>
        </div>

        <!-- 页面标签 -->
        <div class="layadmin-pagetabs" id="LAY_app_tabs">
            <div class="layui-icon layadmin-tabs-control layui-icon-prev" layadmin-event="leftPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-next" layadmin-event="rightPage"></div>
            <div class="layui-icon layadmin-tabs-control layui-icon-down">
                <ul class="layui-nav layadmin-tabs-select" lay-filter="layadmin-pagetabs-nav">
                    <li class="layui-nav-item" lay-unselect>
                        <a href="javascript:;"></a>
                        <dl class="layui-nav-child layui-anim-fadein">
                            <dd layadmin-event="closeThisTabs"><a href="javascript:;">关闭当前标签页</a></dd>
                            <dd layadmin-event="closeOtherTabs"><a href="javascript:;">关闭其它标签页</a></dd>
                            <dd layadmin-event="closeAllTabs"><a href="javascript:;">关闭全部标签页</a></dd>
                        </dl>
                    </li>
                </ul>
            </div>
            <div class="layui-tab" lay-unauto lay-allowClose="true" lay-filter="layadmin-layout-tabs">
                <ul class="layui-tab-title" id="LAY_app_tabsheader">
                    <li lay-id="home/console.html" lay-attr="home/console.html" class="layui-this"><i
                            class="layui-icon layui-icon-home"></i></li>
                </ul>
            </div>
        </div>


        <!-- 控制台内容 -->
        <div class="layui-body" id="LAY_app_body">
            <div class="layadmin-tabsbody-item layui-show">
                <iframe src="home" frameborder="0" class="layadmin-iframe"></iframe>
            </div>
        </div>

        <!-- 辅助元素，一般用于移动设备下遮罩 -->
        <div class="layadmin-body-shade" layadmin-event="shade"></div>
    </div>
</div>

<script src="static/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: 'static/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index'], function () {
    		
		
    });
</script>

</body>
</html>


