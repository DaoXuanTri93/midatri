package vn.midatri.mapper;

import org.springframework.stereotype.Component;
import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.repository.model.Order;


@Component
public class OrderMapper {

//    public Order toModel(OrderParam orderParam) {
//        return new Order()
//                .setStatus(orderParam.getStatus())
//                .setGrandTotal(orderParam.getGrandTotal())
//                .setDiscount(orderParam.getDiscount())
//                .setFullName(orderParam.getFullName())
//                .setPhone(orderParam.getPhone())
//                .setEmail(orderParam.getEmail())
//                .setAddress(orderParam.getAddress())
//                .setContent(orderParam.getContent())
//                .setUserId(orderParam.getUserId());
//    }

    public OrderResult toDTO(Order order) {
        return new OrderResult()
                .setStatus(order.getStatus())
                .setGrandTotal(order.getGrandTotal())
                .setDiscount(order.getDiscount())
                .setFullName(order.getFullName())
                .setPhone(order.getPhone())
                .setEmail(order.getEmail())
                .setAddress(order.getAddress())
                .setContent(order.getContent())
                .setCreateAt(order.getCreateAt());
    }

    public Order toModel(OrderParam orderParam) {
        return new Order(orderParam.getUserId())
                .setStatus(orderParam.getStatus())
                .setGrandTotal(orderParam.getGrandTotal())
                .setDiscount(orderParam.getDiscount())
                .setFullName(orderParam.getFullName())
                .setPhone(orderParam.getPhone())
                .setEmail(orderParam.getEmail())
                .setAddress(orderParam.getAddress())
                .setContent(orderParam.getContent())
                .setCreateAt(orderParam.getCreateAt());
    }
}
