package com.example.LMS.Controller;

import com.example.LMS.entity.Author;
import com.example.LMS.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

  private AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping
  public ResponseEntity<List<Author>> getAllAuthors() {
    List<Author> allAuthors = authorService.findAllAuthors();
    return ResponseEntity.ok(allAuthors);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
    Author author = authorService.getAuthorById(id);
    if (author == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(author);
  }

  @PostMapping
  public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
    Author savedAuthor = authorService.saveOrUpdateAuthor(author);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);

  }

  @PutMapping("/{id}")
  public ResponseEntity<Author> updateAuthor(@PathVariable Long id, @RequestBody Author author) {
    Author updatedAuthor = authorService.getAuthorById(id);
    if (updatedAuthor == null) {
      return ResponseEntity.notFound().build();
    } else {
      author.setId(id);
      authorService.saveOrUpdateAuthor(author);
      return ResponseEntity.ok(author);
    }
  }

    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
      Author toDelete = authorService.getAuthorById(id);
      if (toDelete == null) {
        return ResponseEntity.notFound().build();
      }
      else {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
      }

    }

  }

