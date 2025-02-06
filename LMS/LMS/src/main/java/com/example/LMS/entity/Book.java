package com.example.LMS.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


@Table(name = "books")
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToMany
  @JoinTable(name = "books_authors",
          joinColumns = {@JoinColumn(name = "book_id")},
          inverseJoinColumns = {@JoinColumn(name = "author_id")})
  private List<Author> authors;

  @ManyToMany
  @JoinTable(name = "books_catagories",
          joinColumns = {@JoinColumn(name = "book_id")},
          inverseJoinColumns = {@JoinColumn(name = "category_id")})
  private List<Category> categories;

  @ManyToMany
  @JoinTable(name = "books_publishers",
          joinColumns = {@JoinColumn(name = "book_id")},
          inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
  private List<Publisher> publishers;

  public Book() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public List<Publisher> getPublishers() {
    return publishers;
  }

  public void setPublishers(List<Publisher> publishers) {
    this.publishers = publishers;
  }
}
