package libManagement.service;

import libManagement.model.Book;
import libManagement.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService{
    
    private final BookRepository repository;

    public BookService(BookRepository repository){
        this.repository = repository;
    }

    @Override
    public void addBook(Book book){
        this.repository.save(book);
    }

    @Override
    public void removeBook(Long bookID) {
        this.repository.deleteById(bookID);
    }

    @Override
    public Book getBook(Long bookID) {
        Optional<Book> book = this.repository.findById(bookID);
        return book.orElse(null);
    }

    @Override
    public List<Book> getAllBooks() {
        return (List<Book>) this.repository.findAll();
    }

    @Override
    public List<Book> getBooksByKeyword(String keyword) {
        return this.repository.findByKeyword(keyword);
    }
}
