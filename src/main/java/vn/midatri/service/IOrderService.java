package vn.midatri.service;

import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.repository.model.Chart;

import java.util.Date;
import java.util.List;

public interface IOrderService {
    OrderResult create(OrderParam orderParam);

    List<Chart> chartBar();
    List<OrderResult> findAllByCreateAt(Date createAt);

    List<Chart> chartDay();

    List<Chart> chartMonth();

    List<Chart> chartLastDay();

    List<Chart> chartLast7Day();

    List<Chart> chartByMonth();
    List<Chart> chartByLastMonth();


}
