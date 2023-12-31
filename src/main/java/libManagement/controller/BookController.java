package libManagement.controller;

import libManagement.model.Book;
import libManagement.service.IBookService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

   private final IBookService bookService;

   public BookController(IBookService bookService){
      this.bookService = bookService;

   }

   //Helper function for borrowing and returning books
   private void setBorrowedStatusOfBook(Long bookID, boolean borrowedStatus){
      //get book from repository and change its values
      Book book = bookService.getBook(bookID);
      book.setBorrowed(borrowedStatus);

      //save changes back to repository
      bookService.addBook(book);
   }

   @GetMapping("/management")
   public String showManagement(Model model, String keyword){

      List<Book> bookList;
      
      //if no search was queried
      if(keyword == null){
         //get all saved books
         bookList = bookService.getAllBooks();
      }else{
         //otherwise only show the books found by the keyword
         bookList = bookService.getBooksByKeyword(keyword);
      }
      //add them to the model for display
      model.addAttribute("bookList", bookList);

      return "management";
   }

   @GetMapping("/addBook")
   public String showAddBook(Model model){
      Book toBeAddedBook = new Book();
      model.addAttribute("toBeAddedBook", toBeAddedBook);

      return "addBook";
   }

   @PostMapping("/addBook")
   public String processAddBook(Book book){
      bookService.addBook(book);

      return "redirect:management";
   }

   @GetMapping("/editBook")
   public String showEditBook(@RequestParam(name="bookID", required = true) Long bookID, Model model){
      Book toBeEditedBook = bookService.getBook(bookID);
      model.addAttribute("toBeEditedBook", toBeEditedBook);

      return "editBook";
   }

   @PostMapping("/editBook")
   public String processEditBook(@RequestParam(name="bookID", required = true) Long bookID, Book book){
      //get book from repository and change its values
      Book toBeEditedBook = bookService.getBook(bookID);
      toBeEditedBook.setTitle(book.getTitle());
      toBeEditedBook.setVersion(book.getVersion());

      //save changes back to repository
      bookService.addBook(toBeEditedBook);

      return "redirect:management";
   }

   @GetMapping("/removeBook")
   public String showRemoveBook(@RequestParam(name="bookID", required = true) Long bookID, Model model){
      Book toBeRemovedBook = bookService.getBook(bookID);
      model.addAttribute("toBeRemovedBook", toBeRemovedBook);

      return "removeBook";
   }

   @PostMapping("/removeBook")
   public String processRemoveBook(@RequestParam(name="bookID", required = true) Long bookID){
      bookService.removeBook(bookID);

      return "redirect:management";
   }

   @GetMapping("/borrowBook")
   public String showBorrowBook(Model model, String keyword){
      //get all books by the given keyword
      List<Book> bookList = bookService.getBooksByKeyword(keyword);
      //add them to the model for display
      model.addAttribute("bookList", bookList);

      return "borrowBook";
   }

   @PostMapping("/borrowBook")
   public String processBorrowBook(@RequestParam(name="bookID", required = true) Long bookID){
      setBorrowedStatusOfBook(bookID, true);

      return "redirect:";
   }

   @GetMapping("/returnBook")
   public String showReturnBook(Model model, String keyword){
      //get all books by the given keyword
      List<Book> bookList = bookService.getBooksByKeyword(keyword);
      //add them to the model for display
      model.addAttribute("bookList", bookList);

      return "returnBook";
   }

   @PostMapping("/returnBook")
   public String processReturnBook(@RequestParam(name="bookID", required = true) Long bookID){
      setBorrowedStatusOfBook(bookID, false);

      return "redirect:";
   }
}
