package com.example.LMS.service;

import com.example.LMS.entity.Publisher;
import com.example.LMS.repo.PublisherRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

  private PublisherRepo publisherRepo;

  public PublisherService(PublisherRepo publisherRepo) {
    this.publisherRepo = publisherRepo;
  }

  public List<Publisher> findAllPublishers() {
    return this.publisherRepo.findAll();
  }

  public Publisher findPublisherById(Long id) {
    return publisherRepo.findById(id)
            .orElse(null);
    /*
    Publisher toReturn = null;
    try {
      toReturn = publisherRepo.getReferenceById(id);
    }
    catch(EntityNotFoundException e) {
      return toReturn;
    }
    return toReturn;*/
  }

  public Publisher saveOrUpdatePublisher(Publisher publisher) {
    return publisherRepo.save(publisher);
  }

  public void deletePublisherById(Long id) {
    publisherRepo.findById(id).orElse(null);
    publisherRepo.deleteById(id);
    /*
    try {
      this.publisherRepo.deleteById(id);
    } catch (Exception e) {
      return;
    }*/
  }

}
