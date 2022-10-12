package vn.midatri.util.service;

import vn.midatri.dto.category.CategoryResult;
import vn.midatri.repository.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    CategoryResult save(Category category);

    CategoryResult findById(Long id);
}
