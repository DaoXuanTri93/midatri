package vn.midatri.service;

import vn.midatri.dto.category.CategoryParam;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.category.CreateCategory;
import vn.midatri.dto.item.CreateItem;
import vn.midatri.dto.item.ItemResult;
import vn.midatri.repository.model.Category;

import java.util.List;

public interface ICategoryService {
    List<CategoryResult> findCategoryByParentIsNotNull();
    List<CategoryResult> findAllByParentIsNull();
    CategoryResult findById(Long id);
    CategoryResult create(CreateCategory createCategory);

    CategoryResult update(CategoryParam categoryParam);
}
