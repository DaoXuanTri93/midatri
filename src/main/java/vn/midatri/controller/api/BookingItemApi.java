package vn.midatri.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.bookingItem.BookingItemCreate;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.mapper.BookingMapper;
import vn.midatri.repository.BookingItemRepository;
import vn.midatri.service.IBookingItemService;
import vn.midatri.service.IBookingService;
import vn.midatri.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/api/bookingItem")
public class BookingItemApi {
    @Autowired
    private IBookingItemService bookingItemService;

    @Autowired
    BookingItemRepository bookingItemRepository;
//    @GetMapping
//    public ResponseEntity<?> findAll() {
//        List<BookingItemResult> bookingItemResults = bookingItemService.findAll();
//        return new ResponseEntity<>(bookingItemResults, HttpStatus.OK);
//    }

    @GetMapping
    public ResponseEntity<?> findAllByBookingId(Long bookingId) {
        List<BookingItemResult> bookingItems = bookingItemService.findAllByBookingId(bookingId);
        return new ResponseEntity<>(bookingItems, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        BookingItemResult bookingItemResult = bookingItemService.findById(id);
        return new ResponseEntity<>(bookingItemResult, HttpStatus.OK);
    }

        @PatchMapping("/{id}/quantity")
    public ResponseEntity<Integer> updateQuantity(@PathVariable Long id, @RequestBody int quantity) {
        return new ResponseEntity<>(bookingItemService.updateQuantity(id, quantity), HttpStatus.OK);
    }
    @PatchMapping("/{id}/increaseQuantity")
    public ResponseEntity<Integer> increaseQuantity(@PathVariable Long id) {
        return new ResponseEntity<>(bookingItemService.increaseQuantity(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/decreaseQuantity")
    public ResponseEntity<Integer> decreaseQuantity(@PathVariable Long id) {
        return new ResponseEntity<>(bookingItemService.decreaseQuantity(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addBookingItem(@RequestBody BookingItemCreate bookingItemCreate) {
        return new ResponseEntity<>( bookingItemService.create(bookingItemCreate), HttpStatus.OK);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?> deletedBookingItem(@PathVariable Long id) {
        bookingItemService.deletedBookingItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deletedByBooking")
    public ResponseEntity<?> deletedAllByBookingId(long bookingId) {
        bookingItemService.deleteAllByBookingId(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
