package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.Order;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o where" +
            " (day (o.createAt) = day (:createAt)) " +
            "AND month (o.createAt) = month (:createAt) " +
            "AND year (o.createAt) = year (:createAt)" )
    List<Order> findAllByCreateAt(@Param("createAt")Date createAt);


}
