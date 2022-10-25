package vn.midatri.service;


import vn.midatri.dto.bookingItem.BookingItemCreate;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.repository.model.BookingItem;

import java.util.List;

public interface IBookingItemService {
    List<BookingItemResult> findAll();

    List<BookingItemResult> findAllByBookingId(Long bookingId);
    BookingItemResult findAllByBookingIdAndItemId(long bookingId, long itemId);
    void deleteAllByBookingId(long id);
    BookingItemResult create(BookingItemCreate bookingItemCreate);
    BookingItemResult findById(Long id);

    void deletedBookingItem(Long id);
    int increaseQuantity(long id);

    int reduceQuantity(long id);
}
