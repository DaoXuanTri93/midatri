package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.Chart;
import vn.midatri.repository.model.Order;

import javax.validation.constraints.Max;
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

    @Query(nativeQuery = true,name = "sp_chartbyday")
    List<Chart> chartDay();
    @Query(nativeQuery = true,name = "sp_chartbymonth")
    List<Chart> chartMonth();
    @Query(nativeQuery = true,name = "sp_chartlastday")
    List<Chart> chartLastDay();
    @Query(nativeQuery = true,name = "sp_last7day")
    List<Chart> chartLast7Day();
    @Query(nativeQuery = true,name = "sp_chartbymonth")
    List<Chart> chartByMonth();
    @Query(nativeQuery = true,name = "sp_last1monht")
    List<Chart> chartByLastMonth();

    @Query(nativeQuery = true,name = "sp_billtoday")
    List<Chart> totalOrderItem();
    @Query(nativeQuery = true,name = "sp_totalOneDay")
    List<Chart> totalOneDay();
    @Query(nativeQuery = true,name = "sp_totalLastDay")
    List<Chart> totalLastDay();
    @Query(nativeQuery = true,name = "sp_totalLastMonth")
    List<Chart> totalLastMonth();
    @Query(nativeQuery = true,name = "sp_totalMonth")
    List<Chart> totalMonth();
    @Query(nativeQuery = true,name = "sp_allTotalToDay")
    List<Chart> allTotalToDay();
    @Query(nativeQuery = true,name = "sp_allTotalMonth")
    List<Chart> allTotalMonth();
}
