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
<link rel="stylesheet" href="static/layuiadmin/layui/css/layui.css"
	media="all">
<link rel="stylesheet" href="static/layuiadmin/style/admin.css"
	media="all">

</head>
<body>
	<div class="layui-fluid">
		<iframe id="baiduframe" marginwidth="0" marginheight="0"
			scrolling="yes" framespacing="0" vspace="0" hspace="0" frameborder="0"
			 width="100%" height="600px"
			src="https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=${keywords}&oq=12&rsv_pq=84c126c80000dca8&rsv_t=3605tdXFFa5j%2FsrJMr7z5H6RVzMTb2HIy4SBk0UFp89eRZuAzt%2FwSONGj9o&rqlang=cn&rsv_enter=0&inputT=9628&rsv_sug3=42&rsv_sug1=8&rsv_sug7=100&rsv_sug2=0&rsv_sug4=9628">
		</iframe>
	</div>
</body>
</html>