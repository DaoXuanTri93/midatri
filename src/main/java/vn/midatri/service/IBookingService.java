package vn.midatri.service;

import vn.midatri.dto.booking.BookingResult;
import vn.midatri.dto.booking.CreateBookingParam;
import vn.midatri.repository.model.Booking;

import java.util.List;
import java.util.Optional;


public interface IBookingService {
    List<BookingResult> findAllByTableTopId(Long id);
    Booking create(Booking booking);
    Optional<Booking> findByTableTopId(Long tableTopId);
    BookingResult findById(Long id);

    BookingResult booking(CreateBookingParam createBookingParam);
}
