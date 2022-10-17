package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.mapper.BookingMapper;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.service.IBookingItemService;

import java.util.List;

@RestController
@RequestMapping("/api/bookingItem")
public class BookingItemApi {
    @Autowired
    private IBookingItemService bookingItemService;
    @Autowired
    private BookingMapper bookingMapper;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<BookingItemResult> bookingItemResults = bookingItemService.findAll();

        return new ResponseEntity<>(bookingItemResults, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllBookingId(@PathVariable Long id) {
        List<BookingItemResult> bookingItems = bookingItemService.findAllByBookingId(id);
        return new ResponseEntity<>(bookingItems, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        BookingItemResult bookingItemResult = bookingItemService.findById(id);
        return new ResponseEntity<>(bookingItemResult, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addBookingItem(@RequestBody BookingItem bookingItem) {
        bookingItemService.save(bookingItem);
        return new ResponseEntity<>(bookingItem, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> deletedBookingItem(@PathVariable Long id) {
        bookingItemService.deletedBookingItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
