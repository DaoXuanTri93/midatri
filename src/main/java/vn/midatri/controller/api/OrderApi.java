package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.repository.model.OrderStatus;
import vn.midatri.service.IOrderService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderApi {

    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public ResponseEntity<?>findAllByCreateAt(String createAt) throws ParseException {

        SimpleDateFormat formatter2 =new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter2.parse(createAt);

        List<OrderResult> orderResults = orderService.findAllByCreateAt(date);
        return new ResponseEntity<>(orderResults,HttpStatus.OK);
    }
    @GetMapping("/sevenDay")
    public ResponseEntity<?>findSevenDay() {
        List<OrderResult> orderResults = orderService.findSevenDay();
        return new ResponseEntity<>(orderResults,HttpStatus.OK);
    }
    @GetMapping("/thisMonth")
    public ResponseEntity<?>findThisMonth(String createAt)throws ParseException{
        SimpleDateFormat formatter2 =new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter2.parse(createAt);
        List<OrderResult> orderResults = orderService.findThisMonth(date);
        return new ResponseEntity<>(orderResults,HttpStatus.OK);
    }
    @GetMapping("/findByBetween")
    public ResponseEntity<?>findThisMonth(String toDay,String fromDay)throws ParseException{
        SimpleDateFormat formatter2 =new SimpleDateFormat("dd-MM-yyyy");
        Date fistDate = formatter2.parse(toDay);
        Date secondDate = formatter2.parse(fromDay);
        List<OrderResult> orderResults = orderService.findByCreateAtBetween(fistDate,secondDate);
        return new ResponseEntity<>(orderResults,HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderParam orderParam){
        orderParam.setCreateAt(Instant.now());
        orderParam.setStatus(OrderStatus.NEW);
        return new ResponseEntity<>(orderService.create(orderParam), HttpStatus.OK);
    }

    @GetMapping("/chart")
    public ResponseEntity<?> orderBarchart(){
        return new ResponseEntity<>(orderService.chartBar(), HttpStatus.OK);
    }


}
