package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.mapper.BookingMapper;
import vn.midatri.repository.BookingRepository;
import vn.midatri.repository.model.Booking;
import vn.midatri.service.IBookingService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookingService implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingMapper bookingMapper;
    @Override

    public List<BookingResult> findAllByTableTopId(Long id) {
        return bookingRepository.findAllByTableTopId(id)
                .stream()
                .map(booking -> bookingMapper.toDTO(booking))
                .collect(Collectors.toList());
    }

    @Override
    public Booking create(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> findByTableTopId(Long id) {
        return bookingRepository.findByTableTopId(id);
    }

    @Override
    public BookingResult findById(Long id) {
        return bookingMapper.toDTO(bookingRepository.findById(id).get());
    }


}
