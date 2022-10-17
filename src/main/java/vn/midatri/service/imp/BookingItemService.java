package vn.midatri.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.midatri.dto.bookingItem.BookingItemResult;
import vn.midatri.mapper.BookingItemMapper;
import vn.midatri.repository.BookingItemRepository;
import vn.midatri.repository.model.BookingItem;
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

    @Override
    public List<BookingItemResult> findAllByBookingId(Long bookingId) {
        return bookingItemRepository.findAllByBookingId(bookingId)
                .stream()
                .map(bookingItem -> bookingItemMapper.toDTO(bookingItem))
                .collect(Collectors.toList());
    }

    @Override
    public BookingItem save(BookingItem bookingItem) {
        return bookingItemRepository.save(bookingItem);
    }

    @Override
    public BookingItemResult findById(Long id) {
        return bookingItemMapper.toDTO(bookingItemRepository.findById(id).get());
    }

    @Override
    public void deletedBookingItem(Long id) {
        bookingItemRepository.deleteById(id);
    }


}
