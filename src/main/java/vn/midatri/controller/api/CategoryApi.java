package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.repository.model.Category;
import vn.midatri.repository.model.Item;
import vn.midatri.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryApi {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    public ResponseEntity<?> renderCategory(){
        List<CategoryResult> categoryResults = categoryService.findAll();
        return new ResponseEntity<>(categoryResults, HttpStatus.OK);
    }
}
