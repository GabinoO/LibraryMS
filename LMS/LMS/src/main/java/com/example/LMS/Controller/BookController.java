package com.example.LMS.Controller;

import com.example.LMS.entity.Author;
import com.example.LMS.entity.Book;
import com.example.LMS.entity.Category;
import com.example.LMS.entity.Publisher;
import com.example.LMS.service.AuthorService;
import com.example.LMS.service.BookService;
import com.example.LMS.service.CategoryService;
import com.example.LMS.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

  private BookService bookService;

  private AuthorService authorService;

  private CategoryService categoryService;

  private PublisherService publisherService;

  public BookController(BookService bookService, AuthorService authorService,
                        CategoryService categoryService, PublisherService publisherService) {

    this.bookService = bookService;
    this.authorService = authorService;
    this.categoryService = categoryService;
    this.publisherService = publisherService;
  }

  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    List<Book> allBooks = bookService.findAllBooks();
    return ResponseEntity.ok(allBooks);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    Book myBook = bookService.getBookById(id);
    if (myBook == null) {
      return ResponseEntity.notFound().build();
    }
    else {
      return ResponseEntity.ok(myBook);
    }
  }

  @PostMapping
  public ResponseEntity<Book> saveBook(@RequestBody Book book) {

    List<Author> authorList = new ArrayList<>();
    for (Author currAuthor : book.getAuthors()) {
      Author toAdd = authorService.getAuthorById(currAuthor.getId());
      if (toAdd == null) {
        return ResponseEntity.notFound().build();
      }
      authorList.add(toAdd);
    }
    book.setAuthors(authorList);

    List<Category> categoryList = new ArrayList<>();
    for (Category currCategory : book.getCategories()) {
      Category toAdd = categoryService.findCategoryById(currCategory.getId());
      if (toAdd == null) {
        return ResponseEntity.notFound().build();
      }
      categoryList.add(toAdd);
    }
    book.setCategories(categoryList);



    List<Publisher> publisherList = new ArrayList<>();
    for (Publisher currPublisher : book.getPublishers()) {
      Publisher toAdd = publisherService.findPublisherById(currPublisher.getId());
      if (toAdd == null) {
        return ResponseEntity.notFound().build();
      }
      publisherList.add(toAdd);
    }
    book.setPublishers(publisherList);

    Book savedBook = bookService.saveOrUpdateBook(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
    Book updatedBook = bookService.getBookById(id);
    if (updatedBook == null) {
      return ResponseEntity.notFound().build();
    } else {

      List<Author> authorList = new ArrayList<>();
      for (Author currAuthor : book.getAuthors()) {
        Author toAdd = authorService.getAuthorById(currAuthor.getId());
        if (toAdd == null) {
          return ResponseEntity.notFound().build();
        }
        authorList.add(toAdd);
      }
      book.setAuthors(authorList);

      List<Category> categoryList = new ArrayList<>();
      for (Category currCategory : book.getCategories()) {
        Category toAdd = categoryService.findCategoryById(currCategory.getId());
        if (toAdd == null) {
          return ResponseEntity.notFound().build();
        }
        categoryList.add(toAdd);
      }
      book.setCategories(categoryList);

      List<Publisher> publisherList = new ArrayList<>();
      for (Publisher currPublisher : book.getPublishers()) {
        Publisher toAdd = publisherService.findPublisherById(currPublisher.getId());
        if (toAdd == null) {
          return ResponseEntity.notFound().build();
        }
        publisherList.add(toAdd);
      }
      book.setPublishers(publisherList);

      
      book.setId(id);
      bookService.saveOrUpdateBook(book);
      return ResponseEntity.ok(book);
    }
  }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
      Book toDelete = bookService.getBookById(id);
      if (toDelete == null) {
        return ResponseEntity.notFound().build();
      }
      else {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
      }
    }



}
