package com.arroganteIT.app.persistence.service.serviceImpl;

import com.arroganteIT.app.persistence.entity.Category;
import com.arroganteIT.app.persistence.repository.CategoryRepository;
import com.arroganteIT.app.persistence.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @Override
    public void save(Category category) {

        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void update(Category newCategory) {

        Category existingCategory = categoryRepository.findByName(newCategory.getName());

        if (existingCategory == null) {
            categoryRepository.save(newCategory);
        }else {
            existingCategory.setName(newCategory.getName());
            existingCategory.setDescription(newCategory.getDescription());

            categoryRepository.save(existingCategory);
        }
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

        categoryRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteByName(String name) {

        categoryRepository.deleteByName(name);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Category> findById(Long id) {

        return categoryRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAllCategories() {

        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Category findByName(String name) {

        return categoryRepository.findByName(name);
    }
}
