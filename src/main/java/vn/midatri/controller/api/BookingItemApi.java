//package vn.midatri.controller.api;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import vn.midatri.repository.model.BookingItem;
//import vn.midatri.service.IBookingItemService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/bookingitem")
//public class BookingItemApi {
//    @Autowired
//    private IBookingItemService bookingItemService;
//
//
//    @GetMapping
//    public ResponseEntity<?> findAll(){
//        List<BookingItem> bookingItems = bookingItemService.findAll();
//
//        return new ResponseEntity<>(bookingItems, HttpStatus.OK);
//    }
//    @PostMapping("/addBookingItem")
//    public ResponseEntity<?> addBookingItem(@RequestBody BookingItem bookingItem) {
//        bookingItemService.addBookingItem(bookingItem);
//        return new ResponseEntity<>(bookingItem, HttpStatus.OK);
//    }
//}
