package com.arroganteIT.app.controller;

import com.arroganteIT.app.persistence.entity.Category;
import com.arroganteIT.app.persistence.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Category category) {

        categoryService.save(category);
    }

    @PatchMapping(value = "/update")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Category category) {

        categoryService.update(category);
    }

    @DeleteMapping(value = "/delete-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable("id") Long id) {

        categoryService.deleteById(id);
    }

    @DeleteMapping(value = "/delete-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByName(@PathVariable("name") String name) {

        categoryService.deleteByName(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> findById(@PathVariable("id") Long id) {

        Optional<Category> category = categoryService.findById(id);

        if (category.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(category);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Category>> findByAll() {

        List<Category> categoryList = categoryService.findAllCategories();

        if (categoryList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(categoryList);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Category> findByName(@PathVariable("name") String name) {

        Category category = categoryService.findByName(name);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(category);
    }

}
