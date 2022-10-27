package vn.midatri.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.category.CategoryParam;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.category.CreateCategory;
import vn.midatri.mapper.CategoryMapper;
import vn.midatri.repository.CategoryRepository;
import vn.midatri.service.ICategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResult> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper.toDTO(category))
                .collect(Collectors.toList());
    }

    public List<CategoryResult> findAllByParentId(Long parentId) {
        return categoryRepository.findAllByParentId(parentId)
                .stream()
                .map(category -> categoryMapper.toDTO(category))
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<CategoryResult> findAllHasParentId(boolean hasParentId) {
        return (hasParentId ?
                categoryRepository.findAllByParentIdIsNotNull() :
                categoryRepository.findAllByParentIdIsNull()
        )
                .stream()
                .map(category -> categoryMapper.toDTO(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResult findById(Long id) {
        return categoryMapper.toDTO(categoryRepository.findById(id).get());
    }

//    @Override
//    public List<CategoryResult> findAllByParentIsNull() {
//        return categoryRepository.findAllByParentIsNull()
//                .stream()
//                .map(category -> categoryMapper.toDTO(category))
//                .collect(Collectors.toList());
//    }

    @Override
    public CategoryResult create(CreateCategory createCategory) {
        createCategory.setId(0L);

        return categoryMapper.toDTO(categoryRepository.save(categoryMapper.toModel(createCategory)));
    }

    @Override
    public CategoryResult update(CategoryParam categoryParam) {
        return categoryMapper.toDTO(categoryRepository.save(categoryMapper.toModel(categoryParam)));
    }
}
