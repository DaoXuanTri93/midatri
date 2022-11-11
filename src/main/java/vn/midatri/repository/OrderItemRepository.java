package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.midatri.dto.report.Goods;
import vn.midatri.repository.model.OrderItem;

import java.util.Date;
import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {
    @Query("SELECT MAX(id) FROM Order")
    long findMaxId();

    List<OrderItem> findAllByOrderId(Long id);

    @Query(name = "sp_getallproductlastday",
            nativeQuery = true)
    List<Goods> findAllByLastDay();
    @Query(nativeQuery = true , name = "sp_getallproducttoday")
    List<Goods> findAllByToDay();
    @Query(nativeQuery = true , name = "sp_getallproducttomonth")
    List<Goods> findAllByToMonth();
    @Query(nativeQuery = true , name = "sp_getallproductlastmonth")
    List<Goods> findAllByLastMonth();
    @Query(nativeQuery = true , name = "sp_getallproductsevenday")
    List<Goods> findAllBySevenDay();
    @Query(nativeQuery = true , name = "sp_getallproduct")
    List<Goods> findAllByCreateAtBetween(Date ToDay , Date FromDay);

}
