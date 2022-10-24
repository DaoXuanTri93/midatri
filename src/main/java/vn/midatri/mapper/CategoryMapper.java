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

    public CategoryParam toParamDTO(Category category) {
        return new CategoryParam()
                .setId(category.getId())
                .setCategoryName(category.getCategoryName())
                .setParentId(category.getParentId());
    }

    public CreateCategory toCreateDTO(Category category){
        return new CreateCategory()
                .setId(category.getId())
                .setCategoryName(category.getCategoryName())
                .setParentId(category.getParentId());
    }

    public Category toModel(CreateCategory createCategory){
        return new Category()
                .setCategoryName(createCategory.getCategoryName())
                .setParentId(createCategory.getParentId())
                .setParent(toModel(createCategory.getParent()));
    }
    public Category toModel(CategoryParam categoryParam){
        return new Category()
                .setId(categoryParam.getId())
                .setCategoryName(categoryParam.getCategoryName())
                .setParentId(categoryParam.getParentId())
                .setParent(toModel(categoryParam.getParent()));
    }


    public Category toModel(CategoryResult categoryResult) {

        return new Category()
                .setId(categoryResult.getId())
                .setCategoryName(categoryResult.getCategoryName())
                .setParentId(categoryResult.getParentId());
    }



}
