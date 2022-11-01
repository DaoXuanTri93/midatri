package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {
    @Query("SELECT MAX(id) FROM Order")
    long findMaxId();
}
