package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findCategoryByParentIsNotNull();
    List<Category> findCategoryByParentIsnull();
}
