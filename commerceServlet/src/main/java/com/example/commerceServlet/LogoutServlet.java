package com.example.commerceServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<html><body>");

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
			out.println("<h1>잘가세요</h1>");
			response.sendRedirect("index.jsp");

		} else {
			out.println("<h1>로그인한적도없음</h1>");
		}

		out.println("</body></html>");
	}
}
