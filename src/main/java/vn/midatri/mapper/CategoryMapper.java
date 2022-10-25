package vn.midatri.mapper;

import org.springframework.stereotype.Component;
import vn.midatri.dto.category.CategoryParam;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.category.CreateCategory;
import vn.midatri.repository.model.Category;

@Component
public class CategoryMapper {

    public CategoryResult toDTO(Category category) {
        return new CategoryResult()
                .setId(category.getId())
                .setCategoryName(category.getCategoryName())
                .setParentId(category.getParentId());
    }

    public Category toModel(CreateCategory createCategory) {
        Category category = new Category()
                .setCategoryName(createCategory.getCategoryName());
        category.setParentId(createCategory.getParentId());
        return category;
    }

    public Category toModel(CategoryParam categoryParam) {
        Category category = new Category()
                .setCategoryName(categoryParam.getCategoryName());
        category.setParentId(categoryParam.getParentId());
        return category;
    }

}
