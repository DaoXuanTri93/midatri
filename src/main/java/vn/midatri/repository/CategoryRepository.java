package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
