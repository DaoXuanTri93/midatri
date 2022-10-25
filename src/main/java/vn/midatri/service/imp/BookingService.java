package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.booking.CreateBookingParam;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.exceptions.NotFoundException;
import vn.midatri.mapper.BookingMapper;
import vn.midatri.repository.BookingItemRepository;
import vn.midatri.repository.BookingRepository;
import vn.midatri.repository.ItemRepository;
import vn.midatri.repository.TableTopRepository;
import vn.midatri.repository.model.*;
import vn.midatri.service.IBookingService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookingService implements IBookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TableTopRepository tableTopRepository;

    @Autowired
    private BookingItemRepository bookingItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BookingMapper bookingMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookingResult> findAllByTableTopId(Long tableTopId) {
        return bookingRepository.findAllByTableTopId(tableTopId)
                .stream()
                .map(booking -> bookingMapper.toDTO(booking))
                .collect(Collectors.toList());
    }


//    @Override
//    public Booking booking(Booking booking) {
//        return bookingRepository.save(booking);
//    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Booking> findByTableTopId(Long id) {
        return bookingRepository.findByTableTopId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResult findById(Long id) {
        return bookingMapper.toDTO(bookingRepository.findById(id).get());
    }

    @Override
    @Transactional
    public BookingResult booking(CreateBookingParam param) {
        Optional<TableTop> tableTopOptional = tableTopRepository.findById(param.getTabletopId());
        if (tableTopOptional.isEmpty())
            throw new NotFoundException("NotFound tabletop " + param.getTabletopId());
        tableTopOptional.ifPresent(tableTop -> {
            if (tableTop.getStatus() != TabletopStatus.AVAILABLE)
                throw new IllegalArgumentException("tabletop" + param.getTabletopId() + " Not availalbe");
        });


        TableTop tabletop = tableTopOptional.get();
        List<Booking> bookings = bookingRepository.findAllByTableTopIdAndStatusNot(param.getTabletopId(), BookingStatus.COMPLETE);
        if (bookings.isEmpty()) {
            Booking newBooking = new Booking(param.getTabletopId(), 1L);
            newBooking.setStatus(BookingStatus.NEW);
            Booking booking = bookingRepository.save(newBooking);

            tabletop.setStatus(TabletopStatus.ACTIVE);
            tableTopRepository.save(tabletop);
            return bookingMapper.toDTO(booking);
        }
        throw new RuntimeException("Tabletop exists Booking");
    }

    @Override
    public List<BookingResult> findAllByStatusNotComplete() {
        return bookingRepository.findAllByStatusNot(BookingStatus.COMPLETE)
                .stream()
                .map(booking -> bookingMapper.toDTO(booking))
                .collect(Collectors.toList());
    }
}

