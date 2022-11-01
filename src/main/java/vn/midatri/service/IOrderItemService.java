package vn.midatri.service;

import vn.midatri.dto.orderItem.OrderItemParam;
import vn.midatri.dto.orderItem.OrderItemResult;

public interface IOrderItemService {
    OrderItemResult create(OrderItemParam orderItemParam);
    long findMaxId();
}
