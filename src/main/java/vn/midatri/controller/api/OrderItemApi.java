package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.category.CategoryResult;
import vn.midatri.dto.orderItem.OrderItemParam;
import vn.midatri.dto.orderItem.OrderItemResult;
import vn.midatri.dto.report.Goods;
import vn.midatri.exceptions.NotFoundException;
import vn.midatri.repository.OrderRepository;
import vn.midatri.repository.model.Order;
import vn.midatri.repository.model.OrderItem;
import vn.midatri.service.IOrderItemService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/orderItem")
public class OrderItemApi {
    @Autowired
    private IOrderItemService orderItemService;
    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("")
    public ResponseEntity<?> findAll() {
        List<OrderItemResult> orderItemResults = orderItemService.findAll();
        return new ResponseEntity<>(orderItemResults, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> findAllByOrderId(@PathVariable Long orderId) {
        List<OrderItemResult> orderItemResults = orderItemService.findAllByOrderId(orderId);
        return new ResponseEntity<>(orderItemResults, HttpStatus.OK);
    }

    @GetMapping("/findToday")
    public ResponseEntity<?> findAllByToDay() {
        List<Goods> goods = orderItemService.findAllByToDay();
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @GetMapping("/findLastDay")
    public ResponseEntity<?> findAllByLastDay() {
        List<Goods> goods = orderItemService.findAllByLastDay();
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @GetMapping("/findToMonth")
    public ResponseEntity<?> findAllByToMonth() {
        List<Goods> goods = orderItemService.findAllByToMonth();
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @GetMapping("/findLastMonth")
    public ResponseEntity<?> findAllByLastMonth() {
        List<Goods> goods = orderItemService.findAllByLastMonth();
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @GetMapping("/findSevenDay")
    public ResponseEntity<?> findAllBySevenDay() {
        List<Goods> goods = orderItemService.findAllBySevenDay();
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @GetMapping("/findByBetween")
    public ResponseEntity<?> findAllByBetween(String ToDay, String FromDay) throws ParseException {
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        Date fistDate = formatter2.parse(ToDay);
        Date secondDate = formatter2.parse(FromDay);
        List<Goods> goods = orderItemService.findAllByCreateAtBetween(fistDate, secondDate);
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody OrderItemParam[] orderItemParamArr) {
        OrderItemParam orderItemParam = new OrderItemParam();
        long orderId = orderItemService.findMaxId();
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("not found id");
        }
        Order order = orderOptional.get();
        BigDecimal total_amount = new BigDecimal(0);
        int total_quantity = 0;

        for (OrderItemParam itemParam : orderItemParamArr) {
            orderItemParam.setOrderId(orderId);
            orderItemParam.setItemId(itemParam.getItemId());
            orderItemParam.setCreateAt(Instant.now());
            orderItemParam.setContent(itemParam.getContent());
            orderItemParam.setPrice(itemParam.getPrice());
            orderItemParam.setQuantity(itemParam.getQuantity());
            orderItemParam.setDiscount(itemParam.getDiscount());
            total_amount = total_amount.add(new BigDecimal(itemParam.getQuantity()).multiply(itemParam.getPrice()));
            total_quantity += itemParam.getQuantity();
            orderItemService.create(orderItemParam);
        }
        order.setGrandTotal(total_amount);
        order.setTotalQuantity(total_quantity);
        orderRepository.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/billToDay")
    public ResponseEntity<?> billToDay() {
        return new ResponseEntity<>(orderRepository.totalOrderItem(), HttpStatus.OK);
    }

}
