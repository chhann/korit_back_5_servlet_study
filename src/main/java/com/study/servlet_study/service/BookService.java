package com.study.servlet_study.service;

import java.util.HashMap;
import java.util.Map;

import com.study.servlet_study.entity.Book;
import com.study.servlet_study.repository.BookRepository;
import com.study.servlet_study.repository.ProductRepository;

public class BookService {

	private BookRepository bookRepository;
	private static BookService instance;
	
	private BookService () {
		bookRepository = BookRepository.getInstance();
	}
	
	
	public static BookService getInstance() {
		if(instance == null) {
			instance = new BookService();
		}
		return instance;
	}
	
	
	
	public boolean addBook(Book book) {
		return bookRepository.saveBook(book) > 0;
	}
	
	public Book getBook(int bookId) {
		return bookRepository.findBookByBookId(bookId);
	}
	
//	public Map<String, String> searchBook(Map<String, String> params) {
//		return bookRepository.searchBookList(params);
//	}
	
}
