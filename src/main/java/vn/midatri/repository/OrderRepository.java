package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.Chart;
import vn.midatri.repository.model.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(nativeQuery = true, name ="sp_chart")
    List<Chart> chartBar();

}
