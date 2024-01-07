/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Parham Alvani
 */
public class BookList {

    private final List<Book> books;
    private final Map<Integer, Book> bookMap;

    public BookList() {
        books = new ArrayList<>();
        bookMap = new HashMap<>();
    }

    public void addBook(Book newBook) {
        books.add(newBook);
        bookMap.put(newBook.getIndex(), newBook);
    }

    public Book getBook(int index) {
        return bookMap.get(index);
    }

    public Book findByTitle(String title) {
        for (Book findedBook : books) {
            if (findedBook.getTitle().equals(title)) {
                return findedBook;
            }
        }
        return null;
    }

    public Book findByAuthor(String author) {
        for (Book findedBook : books) {
            if (findedBook.getAuthor().equals(author)) {
                return findedBook;
            }
        }
        return null;
    }
}
