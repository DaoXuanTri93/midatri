package vn.midatri.service;


import vn.midatri.dto.bookingItem.BookingItemCreate;
import vn.midatri.dto.bookingItem.BookingItemResult;

import java.util.List;

public interface IBookingItemService {
    List<BookingItemResult> findAll();

    List<BookingItemResult> findAllByBookingId(Long bookingId);
    BookingItemResult findAllByBookingIdAndItemId(long bookingId, long itemId);
    void deleteAllByBookingId(long bookingId);
    BookingItemResult create(BookingItemCreate bookingItemCreate);
    BookingItemResult findById(Long id);

    void deletedBookingItem(Long id);
    int increaseQuantity(long id);

    int decreaseQuantity(long id);

    int updateQuantity(Long id, int quantity);
}
