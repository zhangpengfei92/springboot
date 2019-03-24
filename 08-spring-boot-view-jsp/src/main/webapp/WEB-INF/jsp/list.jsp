<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" align="center" width="50%">
		<tr>
			<td>id</td>
			<td>用户姓名</td>
			<td>密码</td>
		</tr>
		<c:forEach items="${list}" var="user">
			<tr>
				<th>${user.id}</th>
				<th>${user.username}</th>
				<th>${user.password}</th>
			</tr>
		</c:forEach>
		
	</table>
</body>
</html>