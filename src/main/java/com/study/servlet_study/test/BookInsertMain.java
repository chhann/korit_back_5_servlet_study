package com.study.servlet_study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.sql.StatementEvent;

import com.study.servlet_study.config.DBConnectionMgr;
import com.study.servlet_study.entity.Author;
import com.study.servlet_study.entity.Book;
import com.study.servlet_study.entity.Publisher;

public class BookInsertMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String bookName = null;
		String authorName = null;
		String publisherName = null;
		
		System.out.print("도서명 >>>");
		bookName = scanner.nextLine();
		System.out.print("저자명 >>>");
		authorName = scanner.nextLine();
		System.out.print("출판사 >>>");
		publisherName = scanner.nextLine();
		
		Book book = Book.builder()
				.bookName(bookName)
				.author(Author.builder().authorName(authorName).build())
				.publisher(Publisher.builder().publisherName(publisherName).build())
				.build();
		
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		
		Connection con =null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			con = pool.getConnection();
			String sql = "insert into author_tb values (0, ?)";
			// RETURN_GENERATED_KEYS : insert 할 때만 넣어줌
			//						 : Auto Increment  의 아이디 번호 찾는 함수
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, book.getAuthor().getAuthorName());
			
			// executeUpdate() : insert, update, delete 에 서만 사용됨
			//                 : 몇건이 insert, update, delete 되었는지 횟수를 가져옴
			pstmt.executeUpdate();
			
			// getGeneratedKeys() : 자동증가된 키값( ai primary key)을 가져오겠다
			// ResultSet : 커서가 움직이면서 select 한다
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				book.getAuthor().setAuthorId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		
		
		try {
			con = pool.getConnection();
			String sql = "insert into publisher_tb values (0, ?)";
			
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, book.getPublisher().getPublisherName());
			
			
			pstmt.executeUpdate();
			

			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				book.getPublisher().setPublisherId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		try {
			con = pool.getConnection();
			String sql = "insert into book_tb values (0, ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, book.getBookName());
			pstmt.setInt(2, book.getAuthor().getAuthorId());
			pstmt.setInt(3, book.getPublisher().getPublisherId());
			
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				book.setBookId(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt);
		}
		
		System.out.println("추가된 도서 정보");
		System.out.println(book);
		
		
		
		
		
		
		
		
	}
}
