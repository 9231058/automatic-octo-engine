package com.mftvanak.wdd.java.advanced.fourth.session.zero.library;

import java.util.ArrayList;
import java.util.List;

public class Book {

	private String name;
	private String isbn;
	private final List<Author> authors;

	public Book() {
		authors = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void addAuthor(Author a) {
		authors.add(a);
	}

	public void removeAuthor(Author a) {
		authors.remove(a);
	}

	public Author getAuthor(int index) {
		return authors.get(index);
	}
}
