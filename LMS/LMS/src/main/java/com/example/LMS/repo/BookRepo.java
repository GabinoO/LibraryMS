package com.example.LMS.repo;

import com.example.LMS.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Long> {

}
