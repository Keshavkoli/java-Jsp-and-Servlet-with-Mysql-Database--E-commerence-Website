package cn.tech.tutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tech.tutorial.connection.DBConnection;
import cn.tech.tutorial.dao.UserDao;
import cn.tech.tutorial.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			// getParameter() means these are the input field name provided by the user
			String email = request.getParameter("login-email");
			System.out.println(email);
			String password = request.getParameter("login-password");
			System.out.println(password);
			try {
				UserDao udao = new UserDao(DBConnection.getConnection());
				User user = udao.userLogin(email, password);
				if (user != null) {
					request.getSession().setAttribute("auth", user);
					System.out.print("user logged in");
					response.sendRedirect("index.jsp");
					out.print("User Login");
				} else {
					out.print("user login failed");
				}

			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
