package com.bookstore.controller.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.model.dao.book.Book;
import com.bookstore.model.dao.book.BookDao;
import com.bookstore.model.dao.book.BookDaoImple;

@WebServlet("/bookController.do")
public class bookController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private BookDao dao=new BookDaoImple();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action.equals("showallbooks")) {
			request.setAttribute("books", dao.getAllBooks());
			RequestDispatcher rd=request.getRequestDispatcher("show.jsp");
			rd.forward(request, response);
		}
		else if(action.equals("addbook")) {
			request.getRequestDispatcher("addbook.jsp").forward(request,response);	
		}
		else if(action.equals("delbook")) {
			int id=Integer.parseInt(request.getParameter("id").trim());
			dao.delBook(id);
			response.sendRedirect("bookController?action=showallbooks");
		}
		else if(action.equals("updatebook")) {
			int id=Integer.parseInt(request.getParameter("id").trim());
			Book book=dao.getBookById(id);
			request.setAttribute("book", book);
			request.getRequestDispatcher("updatebook.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id").trim());
		String isbn=request.getParameter("isbn");
		String titile=request.getParameter("title");
		String author=request.getParameter("author");
		String pubDateString=request.getParameter("pubDate");
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
		String priceStr=request.getParameter("price");
			Date pubDate=null;
			try {
				pubDate=format.parse(pubDateString);
			}catch(ParseException e) {
				e.printStackTrace();
			}
			Double price=Double.parseDouble(priceStr);
			Book book=new Book();
			if(id==0)
				dao.addBook(book);
				
			
		response.sendRedirect("bookController?action=showallbooks");

		
		
		
	}

}
