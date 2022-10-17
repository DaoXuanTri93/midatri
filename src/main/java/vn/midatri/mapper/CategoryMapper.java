package vn.midatri.mapper;

import org.springframework.stereotype.Component;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.repository.model.Category;

@Component
public class CategoryMapper {
    public CategoryResult toDTO(Category category){
        return new CategoryResult()
        .setId(category.getId())
        .setCategoryName(category.getCategoryName());
    }
    public Category toModel(CategoryResult categoryResult){
        return new Category()
                .setId(categoryResult.getId())
                .setCategoryName(categoryResult.getCategoryName());
    }

}
