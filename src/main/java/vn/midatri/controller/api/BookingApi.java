package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.repository.model.Booking;
import vn.midatri.service.IBookingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/booking")
public class BookingApi {
    @Autowired
    private IBookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findAll(@PathVariable Long id) {
        List<BookingResult> bookingList = bookingService.findAllByTableTopId(id);
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking, @PathVariable Long id) {
        Optional<Booking> bookingResults = bookingService.findByTableTopId(id);
        if (bookingResults.isEmpty()){
            bookingService.create(booking);
        }else {
            booking.setId(bookingResults.get().getId());
        }
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }


}
