package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.category.CategoryParam;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.category.CreateCategory;
import vn.midatri.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryApi {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("parent/{parentId}")
    public ResponseEntity<?> findAllByParentId(@PathVariable Long parentId) {
        List<CategoryResult> categories = categoryService.findAllByParentId(parentId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("parent")
    public ResponseEntity<?> findAllByParentId(boolean hasParentId) {
        List<CategoryResult> categories = categoryService.findAllHasParentId(hasParentId);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<CategoryResult> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        CategoryResult categoryResults = categoryService.findById(id);
        return new ResponseEntity<>(categoryResults, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> doEdit(@RequestBody CategoryParam categoryParam, @PathVariable Long id) {
        categoryParam.setId(id);
        categoryService.update(categoryParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody CreateCategory createCategory) {

        CategoryResult categoryResult = categoryService.create(createCategory);
        return new ResponseEntity<>(categoryResult, HttpStatus.OK);
    }
}
