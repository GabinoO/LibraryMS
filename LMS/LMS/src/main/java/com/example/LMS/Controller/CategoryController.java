package com.example.LMS.Controller;

import com.example.LMS.entity.Category;
import com.example.LMS.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
  private CategoryService categoryService;

  public CategoryController(CategoryService categoryservice) {
    this.categoryService = categoryservice;
  }

  @GetMapping
  public ResponseEntity<List<Category>> findAllCategories() {
    List<Category> allCategories = categoryService.findAllCategories();
    return ResponseEntity.ok(allCategories);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
    Category myCategory = categoryService.findCategoryById(id);
    if (myCategory == null) {
      return ResponseEntity.notFound().build();
    }
    else {
      return ResponseEntity.ok(myCategory);
    }
  }

  @PostMapping
  public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
    Category savedCategory = categoryService.saveOrUpdateCategory(category);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
    Category toUpdate = categoryService.findCategoryById(id);
    if (toUpdate == null) {
      return ResponseEntity.notFound().build();
    }
    else {
      category.setId(id);
      categoryService.saveOrUpdateCategory(category);
      return ResponseEntity.ok(category);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
    Category toDelete = categoryService.findCategoryById(id);
    if (toDelete == null) {
      return ResponseEntity.notFound().build();
    }
    else {
      categoryService.deleteCategoryById(id);
      return ResponseEntity.noContent().build();
    }
  }


}
