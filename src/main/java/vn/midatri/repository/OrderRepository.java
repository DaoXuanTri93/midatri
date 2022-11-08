package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.Chart;
import vn.midatri.repository.model.Order;
import java.util.List;
import java.util.Date;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o where" +
            " (day (o.createAt) = day (:createAt)) " +
            "AND month (o.createAt) = month (:createAt) " +
            "AND year (o.createAt) = year (:createAt)" )
    List<Order> findAllByCreateAt(@Param("createAt")Date createAt);
    @Query("SELECT o FROM Order o where" +
            " month (o.createAt) = month (:createAt) " +
            "AND year (o.createAt) = year (:createAt)" )
    List<Order> findThisMonth(@Param("createAt")Date createAt);


    @Query( value = "SELECT * FROM midatri.order AS o where DATE(o.create_at) > (NOW() - INTERVAL 7 DAY)",
    nativeQuery = true)
    List<Order> findSevenDay();
    @Query( value = "SELECT * FROM midatri.order AS o where o.create_at BETWEEN :toDay AND :fromDay ",
    nativeQuery = true)
    List<Order> findByCreateAtBetween(Date toDay , Date fromDay);

    @Query(nativeQuery = true, name ="sp_chart")
    List<Chart> chartBar();


}
