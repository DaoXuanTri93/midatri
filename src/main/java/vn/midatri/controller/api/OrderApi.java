package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.repository.model.Order;
import vn.midatri.repository.model.OrderStatus;
import vn.midatri.service.IOrderService;

import java.sql.Time;
import java.time.Instant;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderParam orderParam){
        orderParam.setCreateAt(Instant.now());
        orderParam.setStatus(OrderStatus.NEW);
        return new ResponseEntity<>(orderService.create(orderParam), HttpStatus.OK);
    }
}
