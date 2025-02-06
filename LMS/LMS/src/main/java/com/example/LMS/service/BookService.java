package com.example.LMS.service;

import com.example.LMS.entity.Book;
import com.example.LMS.repo.AuthorRepo;
import com.example.LMS.repo.BookRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  private final AuthorRepo authorRepo;
  private BookRepo bookRepo;

  public BookService(BookRepo bookRepo, AuthorRepo authorRepo) {
    this.bookRepo = bookRepo;
    this.authorRepo = authorRepo;
  }

  public List<Book> findAllBooks() {
    return bookRepo.findAll();
  }

  public Book getBookById(long id) {
    return bookRepo.findById(id)
            .orElse(null);
    /*Book toReturn = null;
    try {
      toReturn = bookRepo.getReferenceById(id);
    }
    catch(EntityNotFoundException e) {
      return toReturn;
    }
    return toReturn;*/
  }

  public Book saveOrUpdateBook(Book book) {
    return bookRepo.save(book);
  }

  public void deleteBookById(Long id) {
    bookRepo.findById(id).orElse(null);
    authorRepo.deleteById(id);
    /*try {
      bookRepo.deleteById(id);
    } catch (Exception e) {
      return;
    }*/
  }
}
