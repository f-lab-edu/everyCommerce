<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>

</h1>
<br/>
<a href="login">로그인이 필요한 페이지입니다</a>
<body>
<h1>Login Page</h1>
<form action="login" method="post">
  <label for="username">아이디:</label>
  <input type="text" id="username" name="username" required><br><br>

  <label for="password">패스워드:</label>
  <input type="password" id="password" name="password" required><br><br>

  <input type="submit" value="Login">
</form>
</body>
</body>
</html>
