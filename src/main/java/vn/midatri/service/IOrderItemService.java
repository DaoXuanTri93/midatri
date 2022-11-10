package vn.midatri.service;

import vn.midatri.dto.orderItem.OrderItemParam;
import vn.midatri.dto.orderItem.OrderItemResult;
import vn.midatri.repository.model.Chart;

import java.util.List;

public interface IOrderItemService {
    OrderItemResult create(OrderItemParam orderItemParam);
    long findMaxId();
    List<OrderItemResult> findAllByOrderId ( Long id);

    List<OrderItemResult> findAll();


}
