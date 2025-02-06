package com.example.LMS.service;

import com.example.LMS.entity.Author;
import com.example.LMS.repo.AuthorRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

  private AuthorRepo authorRepo;

  public AuthorService(AuthorRepo authorRepo) {
    this.authorRepo = authorRepo;
  }

  public List<Author> findAllAuthors() {
    return authorRepo.findAll();
  }

  public Author getAuthorById(Long id) {
    return authorRepo.findById(id)
            .orElse(null);
    /*
    Author toReturn = null;
    try {
      toReturn = authorRepo.getReferenceById(id);
    }
    catch(EntityNotFoundException e) {
      return toReturn;
    }
    return toReturn;*/
  }

  public Author saveOrUpdateAuthor(Author author) {
    return authorRepo.save(author);
  }

  public void deleteAuthorById(Long id) {
    authorRepo.findById(id).orElse(null);
    authorRepo.deleteById(id);
    /*try {
      this.authorRepo.deleteById(id);
    }
    catch(Exception e) {
      return;
    }*/
  }

}
