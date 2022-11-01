package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    

}
