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
                .setParentId(category.getParentId())
                ;
    }




    public Category toModel(CategoryResult categoryResult) {

        return new Category()
                .setId(categoryResult.getId())
                .setCategoryName(categoryResult.getCategoryName())
                .setParentId(categoryResult.getParentId());
    }



}
