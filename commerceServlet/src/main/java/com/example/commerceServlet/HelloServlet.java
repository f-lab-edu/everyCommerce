package com.example.commerceServlet;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/*
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
	private String message;

	public void init() {
		message = "Hello World!";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");

		// Hello
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h1>" + message + "</h1>");
		out.println("</body></html>");
	}

	public void destroy() {
	}
}
*/
@WebServlet("/login")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<html><body>");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UserService.addUser(new User("123", "123"));

		User user = UserService.getUser(username);
		if (user != null && user.getPassword().equals(password)) {
			// 로그인 성공
			out.println("<h1>로그인대성공</h1>");
			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			//로그인페이지로 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else {
			// 로그인 실패
			out.println("<h1>로그인대실패</h1>");
		}
		out.println("</body></html>");
	}

}
