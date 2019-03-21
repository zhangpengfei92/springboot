<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
    <link rel="stylesheet" type="text/css" href="static/js/ueditor/themes/default/css/ueditor.css" />
	<link rel="stylesheet" type="text/css" href="static/js/ueditor/dialogs/emotion/emotion.css" />
	<script type="text/javascript" src="static/js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="static/js/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="static/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="static/js/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">

    </script>
</head>
<body>
<div class="layui-fluid" style="background-color: white; padding: 10px">
    <div class="layui-row" >
        <form class="layui-form" action="" method="post" accept-charset="UTF-8">
        <input type="hidden" name="id" id="" value="">
            <div class="layui-form-item">
                <label class="layui-form-labels">标题:</label>
                <div class="layui-input-inline">
                    <input type="text" name="title" value="${doc.title}" placeholder="请输入公告标题" lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>
			<input type="hidden" id="id" name="id" value="${doc.id}">
			<input type="hidden" id="columnid" name="columnid" value="${doccolumnid}">
			 <div class="layui-form-item">
                <label class="layui-form-labels">简介:</label>
                <div class="layui-input-inline">
                    <input type="text" name="summary" value="${doc.summary}" placeholder="请输入公告简介"   lay-verify="required" autocomplete="off" class="layui-input" >
                </div>
            </div>
            
	      <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">公告内容</label>
		    <div class="layui-input-block">
		       <script id="editor" name="editor" type="text/plain" style="height:300px;">${doc.content}</script>
		    </div>
		  </div>
            	 
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

	var ue = UE.getEditor('editor');

    layui.config({
        base:'static/layui/lay/extension/'
    }).use(['jclAdd','laydate','jclUpload'], function() {
        var jclAdd =layui.jclAdd,laydate =layui.laydate;
        jclAdd.init('doc/add');      
        		
    });   
</script>

</body>
</html>