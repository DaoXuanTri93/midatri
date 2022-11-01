package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.repository.model.BookingItemStatus;
import vn.midatri.repository.model.BookingStatus;

import java.util.List;


@Repository
public interface BookingItemRepository extends JpaRepository<BookingItem, Long> {
    List<BookingItem> findAllByBookingId(Long bookingId);

    BookingItem findByBookingIdAndItemId(long bookingId, long itemId);
    List<BookingItem> findAllByStatus(BookingItemStatus status);
    void deleteAllByBookingId(long bookingId);
}
