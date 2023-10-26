package libManagement.service;

import libManagement.model.Book;
import java.util.List;

public interface IBookService {

    public void addBook(Book book);
    public void removeBook(Long bookID);
    public Book getBook(Long bookID);
    public List<Book> getAllBooks();
    public List<Book> getBooksByKeyword(String keyword);
}
