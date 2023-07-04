<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
</head>
<body>
<h1> 반갑습니다 </h1>
<p><%= session.getAttribute("username") %>회원님 반갑습니다</p>
<p><a href="logout">로그아웃</a></p>
</body>
</html>
