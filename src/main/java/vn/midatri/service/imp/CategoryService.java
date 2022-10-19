package vn.midatri.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.mapper.CategoryMapper;
import vn.midatri.repository.CategoryRepository;
import vn.midatri.repository.model.Category;
import vn.midatri.service.ICategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService  implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResult> findCategoryByParentIsNotNull() {
        return categoryRepository.findCategoryByParentIsNotNull()
                .stream()
                .map(category -> categoryMapper.toDTO(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResult save(Category category) {
        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryResult findById(Long id) {
        return categoryMapper.toDTO(categoryRepository.findById(id).get());
    }

//    @Override
//    public List<CategoryResult> findCategoryByParentIsnull() {
//        return categoryRepository.findCategoryByParentIsnull()
//                .stream()
//                .map(category -> categoryMapper.toDTO(category))
//                .collect(Collectors.toList());
//    }
}
