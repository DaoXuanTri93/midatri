package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.order.OrderParam;
import vn.midatri.dto.order.OrderResult;
import vn.midatri.repository.model.Chart;
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
    public ResponseEntity<?> findAllByCreateAt(String createAt) throws ParseException {

        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter2.parse(createAt);

        List<OrderResult> orderResults = orderService.findAllByCreateAt(date);
        return new ResponseEntity<>(orderResults, HttpStatus.OK);
    }

    @GetMapping("/sevenDay")
    public ResponseEntity<?> findSevenDay() {
        List<OrderResult> orderResults = orderService.findSevenDay();
        return new ResponseEntity<>(orderResults, HttpStatus.OK);
    }

    @GetMapping("/thisMonth")
    public ResponseEntity<?> findThisMonth(String createAt) throws ParseException {
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter2.parse(createAt);
        List<OrderResult> orderResults = orderService.findThisMonth(date);
        return new ResponseEntity<>(orderResults, HttpStatus.OK);
    }

    @GetMapping("/findByBetween")
    public ResponseEntity<?>findByBetween(String toDay,String fromDay)throws ParseException{
        SimpleDateFormat formatter2 =new SimpleDateFormat("dd-MM-yyyy");
        Date fistDate = formatter2.parse(toDay);
        Date secondDate = formatter2.parse(fromDay);
        List<OrderResult> orderResults = orderService.findByCreateAtBetween(fistDate, secondDate);
        return new ResponseEntity<>(orderResults, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderParam orderParam) {
        orderParam.setCreateAt(Instant.now());
        orderParam.setStatus(OrderStatus.NEW);
        return new ResponseEntity<>(orderService.create(orderParam), HttpStatus.OK);
    }

    @GetMapping("/chart")
    public ResponseEntity<?> orderBarchart() {
        return new ResponseEntity<>(orderService.chartBar(), HttpStatus.OK);
    }

    @GetMapping("/chartByDay")
    public ResponseEntity<?> chartByDay() {

        return new ResponseEntity<>(orderService.chartDay(), HttpStatus.OK);
    }


    @GetMapping("/chartLastDay")
    public ResponseEntity<?> chartLastDay() {
        return new ResponseEntity<>(orderService.chartLastDay(), HttpStatus.OK);
    }

    @GetMapping("/chartLast7Day")
    public ResponseEntity<?> chartLast7Day() {
        return new ResponseEntity<>(orderService.chartLast7Day(), HttpStatus.OK);
    }

    @GetMapping("/chartByMonth")
    public ResponseEntity<?> charByMonth() {
        return new ResponseEntity<>(orderService.chartByMonth(), HttpStatus.OK);
    }

    @GetMapping("/chartByLastMonth")
    public ResponseEntity<?> charByLastMonth() {
        return new ResponseEntity<>(orderService.chartByLastMonth(), HttpStatus.OK);
    }

    @GetMapping("/totalOneDay")
    public ResponseEntity<?> totalOneDay() {
        return new ResponseEntity<>(orderService.totalOneDay(), HttpStatus.OK);
    }

    @GetMapping("/totalLastDay")
    public ResponseEntity<?> totalLastDay() {
        return new ResponseEntity<>(orderService.totalLastDay(), HttpStatus.OK);
    }
    @GetMapping("/totalLastMonth")
    public ResponseEntity<?> totalLastMonth() {
        return new ResponseEntity<>(orderService.totalLastMonth(), HttpStatus.OK);
    }
    @GetMapping("/totalMonth")
    public ResponseEntity<?> totalMonth() {
        return new ResponseEntity<>(orderService.totalMonth(), HttpStatus.OK);
    }

    @GetMapping("/allTotalToDay")
    public ResponseEntity<?> allTotalToDay(){
        return new ResponseEntity<>(orderService.allTotalToDay(),HttpStatus.OK);
    }
    @GetMapping("/allTotalMonth")
    public ResponseEntity<?> allTotalMonth(){
        return new ResponseEntity<>(orderService.allTotalMonth(),HttpStatus.OK);
    }


}
