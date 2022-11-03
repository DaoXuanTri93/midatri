package vn.midatri.service;

import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.repository.model.Chart;
import vn.midatri.repository.model.Order;

import java.util.List;

public interface IOrderService {
    OrderResult create(OrderParam orderParam);

    List<Chart> chartBar();
}
