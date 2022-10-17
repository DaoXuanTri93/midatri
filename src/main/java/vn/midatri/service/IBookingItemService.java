package vn.midatri.service;


import vn.midatri.dto.booking.BookingResult;
import vn.midatri.dto.bookingItem.BookingItemAdd;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.repository.model.BookingItem;

import java.util.List;

public interface IBookingItemService {
    List<BookingItemResult> findAll();

    List<BookingItemResult> findAllByBookingId(Long bookingId);

    BookingItem save (BookingItem bookingItem);

    BookingItemResult findById(Long id);

    void deletedBookingItem(Long id);

}
