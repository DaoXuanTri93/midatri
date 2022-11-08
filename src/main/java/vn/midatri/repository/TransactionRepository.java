package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.midatri.dto.transaction.Information;
import vn.midatri.repository.model.Order;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Order, Long> {
    @Query("SELECT New vn.midatri.dto.transaction.Information(o.id,o.createAt,o.grandTotal,o.status,o.totalQuantity,i.id,i.title,oi.quantity,oi.price) " +
            " FROM Order As o" +
            " INNER JOIN OrderItem AS oi ON oi.order.id= o.id " +
            " INNER JOIN Item AS i ON oi.item.id=i.id " +
            "WHERE o.id= :orderId")
    List<Information> finAllByOrderIdAndItemId(Long orderId);

}
