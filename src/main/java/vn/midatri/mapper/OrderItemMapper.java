package vn.midatri.mapper;

import org.springframework.stereotype.Component;
import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.dto.orderItem.OrderItemParam;
import vn.midatri.dto.orderItem.OrderItemResult;
import vn.midatri.repository.model.Order;
import vn.midatri.repository.model.OrderItem;


@Component
public class OrderItemMapper {

    public OrderItemResult toDTO(OrderItem orderItem) {
        return new OrderItemResult()
                .setItemId(orderItem.getItemId())
                .setOrderId(orderItem.getOrderId())
                .setPrice(orderItem.getPrice())
                .setDiscount(orderItem.getDiscount())
                .setCreateAt(orderItem.getCreateAt())
                .setQuantity(orderItem.getQuantity())
                .setContent(orderItem.getContent());
    }

    public OrderItem toModel(OrderItemParam orderItemParam) {
        return new OrderItem(orderItemParam.getItemId(), orderItemParam.getOrderId())
                .setPrice(orderItemParam.getPrice())
                .setDiscount(orderItemParam.getDiscount())
                .setCreateAt(orderItemParam.getCreateAt())
                .setQuantity(orderItemParam.getQuantity())
                .setContent(orderItemParam.getContent());
    }
}
