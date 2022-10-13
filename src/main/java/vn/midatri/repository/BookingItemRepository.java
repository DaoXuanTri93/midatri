package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.BookingItem;

@Repository
public interface BookingItemRepository extends JpaRepository<BookingItem, Long> {

//    List<BookingItem> findAllByBookingId(Long id);
}
