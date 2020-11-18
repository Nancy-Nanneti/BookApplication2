package com.bookstore.model.dao.user;

public class Main {
	public static void main(String[] args) {
		UserDao dao=new UserDaoImpl();
		
		User user=new User("keshav", "keshav123", "emp");
		dao.addUser(user);
		
		
	}
}
