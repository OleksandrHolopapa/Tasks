package WesterosLibraryTask4;

import java.util.HashMap;
import java.util.Map;

public class Library {
    private final Map<Book, String> books = new HashMap<>();

    public boolean addBook(String bookTitle, String bookAuthor, int bookPublicationYear, Book.Epoch epoch, String bookLocation) {
        Book book = new Book(bookTitle, bookAuthor, bookPublicationYear, epoch);
        String existingLocation = books.putIfAbsent(book, bookLocation);
        if (existingLocation != null) {
            System.out.println("This book is already in the library behind this location: " + existingLocation);
            return false;
        }
        return true;
    }

    public boolean removeBook(String bookTitle, String bookAuthor, int bookPublicationYear, Book.Epoch epoch) {
        Book book = new Book(bookTitle, bookAuthor, bookPublicationYear, epoch);
        return books.remove(book) != null;
    }

    public String getBookLocation(String bookTitle, String bookAuthor, int bookPublicationYear, Book.Epoch epoch) {
        Book book = new Book(bookTitle, bookAuthor, bookPublicationYear, epoch);
        return "The book "+book + " is located at " +books.get(book);
    }

    public void printAllBooks() {
        books.forEach((book, location) -> System.out.println("The book "+book + " is located at " + location));
    }
}