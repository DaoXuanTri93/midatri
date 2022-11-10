package vn.midatri.service;

import vn.midatri.dto.orderItem.OrderItemParam;
import vn.midatri.dto.orderItem.OrderItemResult;
import vn.midatri.dto.report.Goods;

import java.util.Date;
import java.util.List;

public interface IOrderItemService {
    OrderItemResult create(OrderItemParam orderItemParam);
    long findMaxId();
    List<OrderItemResult> findAllByOrderId ( Long id);

    List<OrderItemResult> findAll();
    List<Goods> findAllByToDay();
    List<Goods> findAllByLastDay();
    List<Goods> findAllByToMonth();
    List<Goods> findAllByLastMonth();
    List<Goods> findAllBySevenDay();
    List<Goods> findAllByCreateAtBetween(Date ToDay , Date FromDay);
}
