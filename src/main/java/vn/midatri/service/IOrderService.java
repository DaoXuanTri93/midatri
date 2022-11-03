package vn.midatri.service;

import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.repository.model.Order;

import java.time.Instant;
import java.util.Date;
import java.util.List;

public interface IOrderService {
    OrderResult create(OrderParam orderParam);
    List<OrderResult> findAllByCreateAt(Date createAt);
}
