package vn.midatri.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.bookingItem.BookingIitemKitChen;
import vn.midatri.dto.bookingItem.BookingItemCreate;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.dto.bookingItem.BookingItemUpdateStatus;
import vn.midatri.exceptions.NotFoundException;
import vn.midatri.repository.BookingItemRepository;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.service.IBookingItemService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static vn.midatri.repository.model.BookingItemStatus.*;

@RestController
@RequestMapping("/api/bookingItem")
public class BookingItemApi {
    private Boolean hasKitchenItem = false;

    @Autowired
    private IBookingItemService bookingItemService;

    @Autowired
    BookingItemRepository bookingItemRepository;
    @GetMapping("/findAll")
    public ResponseEntity<?> findAllBookingItem() {
        List<BookingIitemKitChen> bookingItems = bookingItemService.findAllJoinTable();
        return new ResponseEntity<>(bookingItems, HttpStatus.OK);
    }

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

    @GetMapping("/findAllBookingItemStatusKitchen")
    public ResponseEntity<?> findAllBookingItemStatus() {
        List<BookingItemResult> bookingItems = bookingItemService.findAllByStatus(KITCHEN);
        return new ResponseEntity<>(bookingItems, HttpStatus.OK);
    }
    @GetMapping("/findAllBookingItemStatusCooking")
    public ResponseEntity<?> findAllBookingItemStatusCooked() {
        List<BookingItemResult> bookingItems = bookingItemService.findAllByStatus(COOKING);
        return new ResponseEntity<>(bookingItems, HttpStatus.OK);
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
        return new ResponseEntity<>(bookingItemService.create(bookingItemCreate), HttpStatus.OK);
    }

    @PatchMapping("/updateNote/{id}")
    public ResponseEntity<String> updateNote(@PathVariable Long id, @RequestBody String content) {
        return new ResponseEntity<>(bookingItemService.updateNote(id, content), HttpStatus.OK);
    }

    @PostMapping("/updateStatusCooking/{id}")
    public ResponseEntity<String> updateStatusCooking(@PathVariable Long id) {
        bookingItemService.updateStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/removeItem/{id}")
    public ResponseEntity<String> removeItem(@PathVariable Long id) {
        bookingItemService.removeItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateStatus(@RequestBody BookingItemUpdateStatus[] bookingItemUpdateStatusArr) {
        if (Objects.nonNull(bookingItemUpdateStatusArr) && bookingItemUpdateStatusArr.length > 0) {
            this.hasKitchenItem = true;
        }

        bookingItemService.updateAllStatus(bookingItemUpdateStatusArr);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/hasKitchenItem")
    public ResponseEntity<Boolean> getHasKitchenItem() {
        return new ResponseEntity<>(this.hasKitchenItem, HttpStatus.OK);
    }

    @PostMapping("/resetKitchenItem")
    public ResponseEntity<String> resetKitchenItem() {
        this.hasKitchenItem = false;
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleted/{id}")
    public ResponseEntity<?> deletedBookingItem(@PathVariable Long id) {
        bookingItemService.deletedBookingItem(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/deletedByBooking/{bookingId}")
    public ResponseEntity<?> deletedAllByBookingId(@PathVariable long bookingId) {
        bookingItemService.deleteAllByBookingId(bookingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
