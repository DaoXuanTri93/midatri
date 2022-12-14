package vn.midatri.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.booking.BookingCustomerParam;
import vn.midatri.dto.booking.CreateBookingParam;
import vn.midatri.dto.booking.BookingResult;
import vn.midatri.exceptions.NotFoundException;
import vn.midatri.mapper.BookingMapper;
import vn.midatri.repository.*;
import vn.midatri.repository.model.*;
import vn.midatri.service.IBookingService;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
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
    private UserRepository userRepository;
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
        if (!tableTopOptional.isPresent())
            throw new NotFoundException("B??n  " + param.getTabletopId() + " kh??ng t??m th???y !!!");
        tableTopOptional.ifPresent(tableTop -> {
            if (tableTop.getStatus() != TabletopStatus.AVAILABLE)
                throw new IllegalArgumentException("B??n " + param.getTabletopId() + " ??ang s???a ch???a !!!");
        });


        TableTop tabletop = tableTopOptional.get();
        List<Booking> bookings = bookingRepository.findAllByTableTopIdAndStatusNot(param.getTabletopId(), BookingStatus.COMPLETE);
        if (bookings.isEmpty()) {
            User user = userRepository.findUserById(param.getUserId());
            if (Objects.isNull(user)) {
                throw new NotFoundException("Kh??ng th??? t??m th???y user b???ng th??ng tin ???? cho.");
            }

            Booking newBooking = new Booking(param.getTabletopId(), user.getId());
            newBooking.setCreateAt(Instant.now());
            newBooking.setStatus(BookingStatus.NEW);
            Booking booking = bookingRepository.save(newBooking);

            tabletop.setStatus(TabletopStatus.ACTIVE);
            tableTopRepository.save(tabletop);

            return bookingMapper.toDTO(booking);
        }
        throw new RuntimeException("Tabletop exists Booking");
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookingResult> findAllByStatusNotComplete() {
        return bookingRepository.findAllByStatusNot(BookingStatus.COMPLETE)
                .stream()
                .map(booking -> bookingMapper.toDTO(booking))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void saveCustomer(long id, BookingCustomerParam bookingCustomerParam) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (!bookingOptional.isPresent()) {
            throw new NotFoundException("Not found id");
        }
        Booking booking = bookingOptional.get();
        booking.setId(id);
        booking.setFullName(bookingCustomerParam.getFullName());
        booking.setPhone(bookingCustomerParam.getPhone());
        booking.setEmail(bookingCustomerParam.getEmail());
        booking.setAddress(bookingCustomerParam.getAddress());
        booking.setContent(bookingCustomerParam.getContent());
        bookingRepository.save(booking);
    }
}

