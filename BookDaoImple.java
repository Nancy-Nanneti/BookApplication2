package com.bookstore.model.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bookstore.model.doa.exceptions.BookNotFoundException;

public class BookDaoImple implements BookDao {
	private Connection connection;

	public BookDaoImple() {
		connection = ConnectionFactory.getConnection();
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		Book tempBook;
		try {
			String all_books_query = "select * from books";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(all_books_query);
			while (rs.next()) {
				tempBook = new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("title"),
						rs.getString("author"), rs.getDate("pubDate"), rs.getDouble("price"));
				books.add(tempBook);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public Book addBook(Book book) {
		try {
		String add_book_query = "insert into books(isbn,title,author,pubDate,price) values (?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(add_book_query, Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, book.getIsbn());
		pstmt.setString(2, book.getAuthor());
		pstmt.setString(3, book.getAuthor());
		pstmt.setDate(4, new Date(book.getPubDate().getTime()));
		pstmt.setDouble(5, book.getPrice());
		int noOfRowsEffected = pstmt.executeUpdate();
		if (noOfRowsEffected > 0) {
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			book.setId(rs.getInt(1));
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	@Override
	public Book delBook(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book updateBook (int id, double price) {
		Book booktoBeUpdated=getBookById(id);
		String update_book_query="update books set price=? where id=?";
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(update_book_query);
        pstmt.setDouble(1, price);
        pstmt.setInt(2, id);
        int noOfRowsEffected=pstmt.executeUpdate();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        booktoBeUpdated.setPrice(price);
        
        return  booktoBeUpdated;
		
	}

	@Override
	public Book getBookById(int id) {
		Book book = null;
		String get_book_by_id = "select * from books where id=?";
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement(get_book_by_id);
	      pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			book = new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("title"), rs.getString("author"),
					rs.getDate("pubDate"), rs.getDouble("price"));
		} else {
			throw new BookNotFoundException("book is not found");
		}
		}
		 catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return book;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		Book book=null;
		try {
		String get_book_by_isbn="select * from books where isbn=?";
		PreparedStatement pstmt=connection.prepareStatement(get_book_by_isbn);
		
			pstmt.setString(1, isbn);
			ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			book=new Book(rs.getInt("id"), rs.getString("isbn"), rs.getString("title"),
                  rs.getString("author"), rs.getDate("pubDate"), rs.getDouble("price"));
		}

		else {
			System.out.println("Not found");
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return book;
	}

}
