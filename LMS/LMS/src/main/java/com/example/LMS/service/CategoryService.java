package com.example.LMS.service;

import com.example.LMS.entity.Category;
import com.example.LMS.repo.CategoryRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

  private CategoryRepo categoryRepo;

  public CategoryService(CategoryRepo categoryRepo) {
    this.categoryRepo = categoryRepo;
  }

  public List<Category> findAllCategories() {
    return this.categoryRepo.findAll();
  }

  public Category findCategoryById(Long id) {
    return categoryRepo.findById(id)
            .orElse(null);
    /*Category toReturn = null;
    try {
      toReturn = this.categoryRepo.getReferenceById(id);
    }
    catch(EntityNotFoundException e) {
      return toReturn;
    }
    return toReturn;*/

  }

  public Category saveOrUpdateCategory(Category category) {
    return this.categoryRepo.save(category);
  }

  public void deleteCategoryById(Long id) {
    categoryRepo.findById(id).orElse(null);
    categoryRepo.deleteById(id);
    /*
    try {
      this.categoryRepo.deleteById(id);
    } catch (Exception e) {
      return;
    }*/
  }

}
