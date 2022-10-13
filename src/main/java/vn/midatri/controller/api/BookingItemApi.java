package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.service.IBookingItemService;

import java.util.List;

@RestController
@RequestMapping("/api/bookingItem")
public class BookingItemApi {
    @Autowired
    private IBookingItemService bookingItemService;


    @GetMapping
    public ResponseEntity<?> findAll(){
        List<BookingItemResult> bookingItemResults = bookingItemService.findAll();

        return new ResponseEntity<>(bookingItemResults, HttpStatus.OK);
    }

//    @GetMapping(("/{id}"))
//    public ResponseEntity<?> findAllBookingId(@PathVariable Long id){
//        List<BookingItem> bookingItems = bookingItemService.findAllByBookingId(id);
//        return new ResponseEntity<>(bookingItems, HttpStatus.OK);
//    }

//    @PostMapping("/addBookingItem")
//    public ResponseEntity<?> addBookingItem(){
//
//        return new ResponseEntity<>(null,HttpStatus.OK);
//    }

}
