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
	<!-- <div style="height: 5rem"></div> -->
    <div class="layui-row" >
        <form class="layui-form" action="" method="post" accept-charset="UTF-8">
 			<div class="layui-upload">
			  <button type="button" class="layui-btn layui-btn-normal" id="testList">选择文件</button> 
			  <div class="layui-upload-list">
			    <table class="layui-table">
			      <thead>
			        <tr><th>文件名</th>
			        <th>大小</th>
			        <th>状态</th>
			        <th>操作</th>
			      </tr></thead>
			      <tbody id="demoList"></tbody>
			    </table>
			  </div>
			  <input type="hidden" value="${region.id}" name="regionid" id="regionid">
			  <button type="button" class="layui-btn" id="testListAction">开始上传</button>
			</div> 
        </form>
    </div>

</div>
<script type="text/javascript" src="static/layui/layui.js"></script>
<script type="text/javascript">
    layui.config({
        base:'static/layui/lay/extension/'
    }).use(['jclAdd','laydate','jclUpload'], function() {
        var jclAdd =layui.jclAdd,laydate =layui.laydate,jclUpload=layui.jclUpload;
        jclUpload.init('region/importdata/'+$("#regionid").val());
    });  	  
</script>

</body>
</html>