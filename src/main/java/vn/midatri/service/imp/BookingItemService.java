package vn.midatri.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.mapper.BookingItemMapper;
import vn.midatri.repository.BookingItemRepository;
import vn.midatri.service.IBookingItemService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingItemService implements IBookingItemService {
    @Autowired
    private BookingItemRepository bookingItemRepository;

    @Autowired
    private BookingItemMapper bookingItemMapper;

    @Override
    public List<BookingItemResult> findAll() {
        return bookingItemRepository.findAll()
                .stream()
                .map(bookingItem -> bookingItemMapper.toDTO(bookingItem))
                .collect(Collectors.toList());
    }


//    @Override
//    public BookingItem save(BookingItemAdd bookingItemAdd) {
//        BookingItem bookingItem = bookingItemMapper.toModel(bookingItemAdd);
//        bookingItemRepository.save(bookingItem);
//        return bookingItem;
//    }

//    @Override
//    public List<BookingItem> findAllByBookingId(Long id) {
//        return bookingItemRepository.findAllByBookingId(id);
//    }

}
