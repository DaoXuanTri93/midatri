package vn.midatri.service;


import vn.midatri.dto.bookingItem.BookingItemAdd;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.repository.model.BookingItem;

import java.util.List;

public interface IBookingItemService {
    List<BookingItemResult> findAll();
//    BookingItem save (BookingItemAdd bookingItemAdd);
//    List<BookingItem> findAllByBookingId(Long id);
}
