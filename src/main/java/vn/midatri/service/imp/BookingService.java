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
import vn.midatri.repository.model.Booking;
import vn.midatri.repository.model.BookingItem;
import vn.midatri.repository.model.BookingStatus;
import vn.midatri.repository.model.TableTop;
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
    private TableTopRepository tableTopRepository;

    @Autowired
    private BookingItemRepository bookingItemRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BookingMapper bookingMapper;

    @Override

    public List<BookingResult> findAllByTableTopId(Long id) {
        return bookingRepository.findAllByTableTopId(id)
                .stream()
                .map(booking -> bookingMapper.toDTO(booking))
                .collect(Collectors.toList());
    }


//    @Override
//    public Booking booking(Booking booking) {
//        return bookingRepository.save(booking);
//    }

    @Override
    public Optional<Booking> findByTableTopId(Long id) {
        return bookingRepository.findByTableTopId(id);
    }

    @Override
    public BookingResult findById(Long id) {
        return bookingMapper.toDTO(bookingRepository.findById(id).get());
    }

    @Override
    public BookingResult booking(CreateBookingParam param) {
        if (!tableTopRepository.existsById(param.getTabletopId()))
            throw new NotFoundException("Invalid tabletop" + param.getTabletopId());
        if (!itemRepository.existsById(param.getItemId()))
            throw new NotFoundException("Invalid item" + param.getItemId());
        Optional<Booking> opBooking = bookingRepository.findAllByTableTopIdAndStatus(param.getTabletopId(), BookingStatus.ACTIVE);
        if (opBooking.isEmpty()) {
            Booking newBooking = new Booking(param.getTabletopId(), BookingStatus.NEW);
            Booking booking = bookingRepository.save(newBooking);
            BookingItem newBookingItem = new BookingItem();
         //   newBookingItem.setBooking()
            bookingItemRepository.save(newBookingItem);

        }
        //    bookingRepository.existsById()
        return null;
    }


}
