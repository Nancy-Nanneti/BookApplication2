package com.bookstore.controller.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.model.dao.user.User;
import com.bookstore.model.dao.user.UserDao;
import com.bookstore.model.dao.user.UserDaoImpl;
import com.bookstore.model.doa.exceptions.BookNotFoundException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserDao userdao=new UserDaoImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		try {
			User user=userdao.getUser(username,password);
			HttpSession httpsession=request.getSession();
			httpsession.setAttribute("user", user);
			response.sendRedirect("bookController.do?action=showallbooks");
		}catch(BookNotFoundException e) {
             response.sendRedirect("login.jsp?message=login failed");
		}
	}
}
