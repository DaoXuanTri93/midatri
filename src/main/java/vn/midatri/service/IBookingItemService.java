package vn.midatri.service;


import vn.midatri.dto.bookingItem.BookingItemCreate;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.repository.model.BookingItem;

import java.util.List;

public interface IBookingItemService {
    List<BookingItemResult> findAll();

    List<BookingItemResult> findAllByBookingId(Long bookingId);
    BookingItemResult findByBookingIdAndItemId(long bookingId, long itemId);
    BookingItemResult create(BookingItemCreate bookingItemCreate);

    BookingItemResult findById(Long id);

    void deletedBookingItem(Long id);

    int increaseQuantity(long id, int quantity);
    int increaseQuantity(long id);

    int reduceQuantity(long id);
}
