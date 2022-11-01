package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.orderItem.OrderItemParam;
import vn.midatri.exceptions.NotFoundException;
import vn.midatri.repository.OrderRepository;
import vn.midatri.repository.model.Order;
import vn.midatri.service.IOrderItemService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.Optional;


@RestController
@RequestMapping("/api/orderItem")
public class OrderItemApi {
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderItemParam[] orderItemParamArr) {
        OrderItemParam orderItemParam = new OrderItemParam();
        long orderId = orderItemService.findMaxId();
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isEmpty()){
            throw new NotFoundException("not found id");
        }
        Order order = orderOptional.get();
        BigDecimal total_amount = new BigDecimal(0);

        for (OrderItemParam itemParam : orderItemParamArr) {
            orderItemParam.setOrderId(orderId);
            orderItemParam.setItemId(itemParam.getItemId());
            orderItemParam.setCreateAt(Instant.now());
            orderItemParam.setContent(itemParam.getContent());
            orderItemParam.setPrice(itemParam.getPrice());
            orderItemParam.setQuantity(itemParam.getQuantity());
            orderItemParam.setDiscount(itemParam.getDiscount());
            total_amount = total_amount.add(new BigDecimal(itemParam.getQuantity()).multiply(itemParam.getPrice()));
            orderItemService.create(orderItemParam);
        }
        order.setGrandTotal(total_amount);
        orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
