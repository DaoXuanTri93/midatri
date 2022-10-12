package vn.midatri.service;

import vn.midatri.dto.category.CategoryResult;
import vn.midatri.repository.model.Category;
import vn.midatri.service.imp.CategoryService;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    CategoryResult save(Category category);

    CategoryResult findById(Long id);
}
