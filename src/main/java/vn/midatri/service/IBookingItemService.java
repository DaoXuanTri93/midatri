package vn.midatri.service;


import vn.midatri.dto.bookingItem.BookingIitemKitChen;
import vn.midatri.dto.bookingItem.BookingItemCreate;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.dto.bookingItem.BookingItemUpdateStatus;
import vn.midatri.repository.model.BookingItemStatus;

import java.util.List;

public interface IBookingItemService {
    List<BookingItemResult> findAll();

    List<BookingIitemKitChen> findAllJoinTable();
    List<BookingItemResult> findAllByBookingId(Long bookingId);
    BookingItemResult findAllByBookingIdAndItemId(long bookingId, long itemId);
    void deleteAllByBookingId(long bookingId);
    BookingItemResult create(BookingItemCreate bookingItemCreate);

    BookingItemResult findById(Long id);

    void deletedBookingItem(Long id);

    int increaseQuantity(long id);

    int decreaseQuantity(long id);

    int updateQuantity(Long id, int quantity);

    String updateNote(Long id, String content);

    void updateStatus(Long id);

    void removeItem(long id);

    void updateAllStatus(BookingItemUpdateStatus[] bookingItemUpdateStatusArr);

    List<BookingItemResult> findAllByStatus(BookingItemStatus status);
}
