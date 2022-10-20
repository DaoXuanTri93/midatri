package vn.midatri.mapper;

import org.springframework.stereotype.Component;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.category.CategoryResultName;
import vn.midatri.repository.model.Category;

@Component
public class CategoryMapper {

    public CategoryResult toDTO(Category category) {
        return new CategoryResult()
                .setId(category.getId())
                .setCategoryName(category.getCategoryName())
                .setParentId(category.getParentId());
    }


    public Category toModel(CategoryResult categoryResult) {
        Category category = new Category(categoryResult.getId());
        return new Category()
                .setId(categoryResult.getId())
                .setCategoryName(categoryResult.getCategoryName())
                .setParent(category);
    }

    public Category toModel(CategoryResultName categoryResultName) {
        Category category = new Category(categoryResultName.getId());
        return new Category()
                .setId(categoryResultName.getId())
                .setCategoryName(categoryResultName.getCategoryName())
                .setParent(category);
    }

}
