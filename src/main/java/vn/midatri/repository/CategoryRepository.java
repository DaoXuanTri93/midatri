package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.midatri.repository.model.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByParentId(Long parentId);
    List<Category> findAllByParentIdIsNull();
    List<Category> findAllByParentIdIsNotNull();

}
