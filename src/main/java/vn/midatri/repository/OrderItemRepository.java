package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.midatri.dto.report.Goods;
import vn.midatri.repository.model.OrderItem;

import java.util.List;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem , Long> {
    @Query("SELECT MAX(id) FROM Order")
    long findMaxId();

    List<OrderItem> findAllByOrderId(Long id);

    @Query(value = "SELECT" +
            " ANY_VALUE(oi.create_at) AS createAt," +
            "ANY_VALUE(oi.item_id) AS itemId," +
            "ANY_VALUE(oi.price ) AS price," +
            "SUM(oi.quantity) as quantity," +
            "i.title AS title " +
            " FROM `order_item` as oi" +
            " inner join item as i on i.id = oi.item_id" +
            " WHERE date(oi.create_at) = date(DATE_SUB(now(), INTERVAL 1 day))" +
            " GROUP BY i.title order by ANY_VALUE(oi.item_id) asc",nativeQuery = true)
    List<Goods> findAllByLastDay();
    @Modifying
    @Query(value = "SELECT NEW vn.midatri.dto.report.Goods(" +
            "ANY_VALUE(oi.create_at) AS createAt," +
            "ANY_VALUE(oi.item_id) AS itemId," +
            "ANY_VALUE(oi.price ) AS price," +
            "SUM(oi.quantity) as quantity," +
            "i.title AS title )" +
            " FROM `order_item` as oi" +
            " join item as i on i.id = oi.item_id" +
            " WHERE date(oi.create_at) = date(NOW())" +
            " GROUP BY i.title order by ANY_VALUE(oi.item_id) asc",nativeQuery = true)
    List<Goods> findAllByToDay();

}
