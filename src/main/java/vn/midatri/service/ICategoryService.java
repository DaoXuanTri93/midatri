package vn.midatri.service;

import vn.midatri.dto.category.CategoryParam;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.category.CreateCategory;

import java.util.List;

public interface ICategoryService {
    List<CategoryResult> findAll();

    CategoryResult findById(Long id);
    CategoryResult create(CreateCategory createCategory);

    CategoryResult update(CategoryParam categoryParam);

    List<CategoryResult> findAllByParentId(Long parentId);
}
