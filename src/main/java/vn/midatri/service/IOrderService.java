package vn.midatri.service;

import org.springframework.data.repository.query.Param;
import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.repository.model.Chart;
import vn.midatri.repository.model.Order;

import java.util.Date;
import java.util.List;

public interface IOrderService {
    OrderResult create(OrderParam orderParam);

    List<Chart> chartBar();
    List<OrderResult> findAllByCreateAt(Date createAt);
    List<OrderResult> findSevenDay();
    List<OrderResult> findThisMonth(@Param("createAt")Date createAt);
    List<OrderResult> findByCreateAtBetween(Date toDay , Date fromDay);
}
