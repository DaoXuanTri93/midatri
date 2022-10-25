package vn.midatri.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.midatri.repository.model.Booking;
import vn.midatri.repository.model.BookingStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByTableTopId(Long id);

    Optional<Booking> findByTableTopId(Long id);

    Optional<Booking> findAllByTableTopIdAndStatus(long tableTopId, BookingStatus bookingStatus);

}
