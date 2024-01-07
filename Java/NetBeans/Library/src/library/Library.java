/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

/**
 *
 * @author Parham Alvani
 */
public class Library {

    private BookList books;
    private PersonList members;

    public void addBook(Book newBook) {
        books.addBook(newBook);
    }

    public void addMember(Person newPerson) {
        members.addPerson(newPerson);
    }

    public boolean borrowBook(Book book,Person person){
        return false;
    }
}
