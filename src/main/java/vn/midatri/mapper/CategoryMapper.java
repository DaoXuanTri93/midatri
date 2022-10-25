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

    public Category toModel(CreateCategory createCategory){
        return new Category()
                .setCategoryName(createCategory.getCategoryName())
                .setParentId(createCategory.getParentId());
    }
    public Category toModel(CategoryParam categoryParam){
        return new Category()
                .setId(categoryParam.getId())
                .setCategoryName(categoryParam.getCategoryName())
                .setParentId(categoryParam.getParentId());
    }

}
