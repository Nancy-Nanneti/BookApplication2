package com.bookstore.model.dao.user;

import com.bookstore.model.dao.book.*;

import java.sql.*;

import com.bookstore.model.dao.book.ConnectionFactory;
import com.bookstore.model.doa.exceptions.BookNotFoundException;
import com.bookstore.model.doa.exceptions.DataAccessException;

import com.bookstore.model.doa.exceptions.*;

public class UserDaoImpl implements UserDao {

	private Connection connection;

	public UserDaoImpl() {
		connection = ConnectionFactory.getConnection();
	}

	@Override
	public void addUser(User user) {
		// id | username | password | prfile
		String add_customer = "insert into user(username,password, prfile) values (?,?,?)";
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(add_customer);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getProfile());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public User getUser(String username, String password) {
		User user = null;
		String get_user = "select * from user where username=? and password=?";
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(get_user);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"),username, password, rs.getString("profile"));
				//user = new User(username, password, rs.getString("prfile"));
			} else {
				throw new BookNotFoundException("user with username " + username + " is not found");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e.getMessage());
		}
		return user;

	}

}
