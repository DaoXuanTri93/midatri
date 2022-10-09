package vn.midatri.mapper;

import org.springframework.stereotype.Component;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.repository.model.Category;

@Component
public class CategoryMapper {


    public CategoryResult toDTO(Category category){
        CategoryResult categoryResult = new CategoryResult();
        categoryResult.setId(category.getId());
        categoryResult.setCategoryName(category.getCategoryName());
        return categoryResult;
    }

    public Category toCategory(CategoryResult categoryResult) {
        Category category = new Category();
        category.setId(categoryResult.getId());
        category.setCategoryName(categoryResult.getCategoryName());

        return category;

    }
}
