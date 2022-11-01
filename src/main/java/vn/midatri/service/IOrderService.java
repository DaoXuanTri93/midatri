package vn.midatri.service;

import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.repository.model.Order;

public interface IOrderService {
    OrderResult create(OrderParam orderParam);
}
