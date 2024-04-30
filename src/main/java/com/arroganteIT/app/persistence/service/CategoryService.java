package com.arroganteIT.app.persistence.service;

import com.arroganteIT.app.persistence.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    void save(Category category);

    void update(Category newCategory);

    void deleteById(Long id);

    void deleteByName(String name);

    Optional<Category> findById(Long id);

    List<Category> findAllCategories();

    Category findByName(String name);
}
