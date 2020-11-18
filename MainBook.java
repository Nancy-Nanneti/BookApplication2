package com.bookstore.model.dao.book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class MainBook {
	
	public static void main(String[] args) throws ParseException {
		BookDao dao=new BookDaoImple();
		
//		Book c=dao.getBookById(4);
//		
//		dao.delBook(4);
//		
		
//		c.setAddress("chennai");
//		c.setEmail("um@r.com");
//		dao.updateCustomer(4, c);
		
		
		
//		Customer customer=dao.getCustomerById(30);
//		System.out.println(customer);
		
		
//		
//		SimpleDateFormat formate=new SimpleDateFormat("dd/MM/yyyy");
//		Date dob=formate.parse("03/11/1960");
//		Customer customer=new Customer("umesh", "banglore", "u@r.com", "6560045454", dob , 32);
//		dao.addCustomer(customer);
//		System.out.println("----------------");
//		
	List<Book> books=dao.getAllBooks();
	books.forEach(c-> System.out.println(c));
	}

}
