package vn.midatri.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.midatri.dto.booking.CreateBookingParam;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.service.IBookingService;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingApi {
    @Autowired
    private IBookingService bookingService;

    @GetMapping
    public ResponseEntity<?> findAllByTabletopId(long tabletopId) {
        List<BookingResult> bookingList = bookingService.findAllByTableTopId(tabletopId);
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @GetMapping("findAllByStatusNotComplete")
    public ResponseEntity<?> findAllByStatusNotComplete() {
        List<BookingResult> bookingList = bookingService.findAllByStatusNotComplete();
        return new ResponseEntity<>(bookingList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> booking(@RequestBody CreateBookingParam createBookingParam) {
        BookingResult booking = bookingService.booking(createBookingParam);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }


}
