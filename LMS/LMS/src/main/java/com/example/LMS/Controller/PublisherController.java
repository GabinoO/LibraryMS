package com.example.LMS.Controller;

import com.example.LMS.entity.Publisher;
import com.example.LMS.service.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
  private PublisherService publisherService;

  public PublisherController(PublisherService publisherService) {
    this.publisherService = publisherService;
  }

  @GetMapping
  public ResponseEntity<List<Publisher>> getAllPublishers() {
    List<Publisher> allPublishers = publisherService.findAllPublishers();
    return ResponseEntity.ok(allPublishers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Publisher> findPublisherById(@PathVariable Long id) {
    Publisher myPublisher = publisherService.findPublisherById(id);
    if (myPublisher == null) {
      return ResponseEntity.notFound().build();
    }
    else {
      return ResponseEntity.ok(myPublisher);
    }
  }

  @PostMapping
  public ResponseEntity<Publisher> savePublisher(@RequestBody Publisher publisher) {
    Publisher savedPublisher = publisherService.saveOrUpdatePublisher(publisher);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedPublisher);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Publisher> updatePublisher(@PathVariable Long id, @RequestBody Publisher publisher) {
    Publisher toUpdate = publisherService.findPublisherById(id);
    if (toUpdate == null) {
      return ResponseEntity.notFound().build();
    }
    else {
      publisher.setId(id);
      publisherService.saveOrUpdatePublisher(publisher);
      return ResponseEntity.ok(publisher);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Publisher> deletePublisher(@PathVariable Long id) {
    Publisher toDelete = publisherService.findPublisherById(id);
    if (toDelete == null) {
      return ResponseEntity.notFound().build();
    }
    else {
      publisherService.deletePublisherById(id);
      return ResponseEntity.noContent().build();
    }
  }


}
