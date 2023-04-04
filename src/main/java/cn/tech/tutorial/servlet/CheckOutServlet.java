package cn.tech.tutorial.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.tech.tutorial.connection.DBConnection;
import cn.tech.tutorial.dao.OrderDao;
import cn.tech.tutorial.model.Cart;
import cn.tech.tutorial.model.Order;
import cn.tech.tutorial.model.User;

@WebServlet("/cartcheckout")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckOutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			User auth = (User) request.getSession().getAttribute("auth");
			if (cart_list != null && auth != null) {
				for (Cart c : cart_list) {
					Order order = new Order();
					order.setId(c.getId());
					order.setUid(auth.getId());
					order.setQunatity(c.getQuantity());
					order.setDate(formatter.format(date));

					OrderDao oDao = new OrderDao(DBConnection.getConnection());
					boolean result = oDao.insertOrder(order);
					if (!result)
						break;
				}
				cart_list.clear();
				response.sendRedirect("orders.jsp");
			} else {
				if (auth == null) {
					response.sendRedirect("login.jsp");
				}
				response.sendRedirect("cart.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
