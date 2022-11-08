package vn.midatri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.midatri.dto.bookingItem.BookingIitemKitChen;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.repository.model.BookingItemStatus;
import vn.midatri.repository.model.BookingStatus;

import java.util.List;


@Repository
public interface BookingItemRepository extends JpaRepository<BookingItem, Long> {
    @Query("SELECT NEW vn.midatri.dto.bookingItem.BookingIitemKitChen(" +
            "bi.id, " +
            "bi.quantity, " +
            "bi.status, " +
            "bi.createAt, " +
            "bi.itemId, " +
            "t.title) " +
            "FROM BookingItem AS bi LEFT JOIN Booking AS b " +
            "ON bi.booking.id = b.id LEFT JOIN TableTop AS t ON b.tableTop.id = t.id")
    List<BookingIitemKitChen> findAllJoinTable();

    List<BookingItem> findAllByBookingId(Long bookingId);

    BookingItem findByBookingIdAndItemId(long bookingId, long itemId);

    List<BookingItem> findAllByStatus(BookingItemStatus status);

    void deleteAllByBookingId(long bookingId);
}
