package com.minh.zingmp3.controller;

import com.minh.zingmp3.model.Category;
import com.minh.zingmp3.request.CategoryRequest;
import com.minh.zingmp3.request.category.AddPlaylistToCategoryRequest;
import com.minh.zingmp3.request.category.CreateCategoryRequest;
import com.minh.zingmp3.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
    @Autowired

    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CreateCategoryRequest categoryRequest) throws Exception {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) throws Exception {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer>  size) throws Exception {
        int pageResult = page.orElse(1);
        int sizeResult = size.orElse(10);
        return ResponseEntity.ok(categoryService.getAllCategory(pageResult,sizeResult));
    }

    @PostMapping("/add-playlist")
    public ResponseEntity<?> addPlayListToCategory(@RequestBody AddPlaylistToCategoryRequest requestAddPlaylistToCategory) throws Exception {
        return new ResponseEntity<>(categoryService.addPlaylistToCategory(requestAddPlaylistToCategory), HttpStatus.OK);
    }
}
